package com.war.service.building.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.war.common.DateService;
import com.war.constant.ProductionQueueTypeConstant;
import com.war.dao.ICityDefenseDAO;
import com.war.domain.City;
import com.war.domain.CityDefense;
import com.war.domain.CityResource;
import com.war.domain.ConstraintDepend;
import com.war.domain.Defense;
import com.war.domain.ProductionQueue;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IDefenseService;
import com.war.service.IProductionQueueService;
import com.war.service.building.ICityDefenseService;
import com.war.util.ConstraintDependUtil;

/**
 * 城市防御service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class CityDefenseService implements ICityDefenseService {
	
	private ICityDefenseDAO cityDefenseDAO;
	private IDefenseService defenseService;
	private IProductionQueueService productionQueueService;
	private ICityService cityService;
	
	/**
	 * 该锁主要为了解决服务端，客户端同时进行完成建造处理而导致的并发问题
	 * ReentrantLock比synchronized效率更高
	 */
	private final Lock lock = new ReentrantLock();
	
	/**
	 * 获得城市特定防御的信息
	 * 
	 * @param cityID 城市编号
	 * @param type 防御类型 CityDefenseTypeConstant中定义
	 * @return
	 */
	public CityDefense getCityDefense(Integer cityID,Integer defenseID){
		return cityDefenseDAO.getCityDefense(cityID, defenseID);
	}
	
	/**
	 * 获得城市所有防御的信息
	 * @param cityID
	 * @return
	 */
	public List<CityDefense> getCityDefenseList(Integer cityID){
		List<CityDefense> cityDefenseList = cityDefenseDAO.getCityDefenseList(cityID);
		for (CityDefense cityDefense : cityDefenseList) {
			cityDefense.setDefense(defenseService.getClonedDefenseByID(cityDefense.getDefenseID()));
		}
		
		return cityDefenseList;
	}
	
	/**
	 * 建造城市的防御
	 * @param cityID 城市编号
	 * @param type 防御类型
	 * @param num 数量
	 */
	public ProductionQueue buildCityDefense(Integer cityID,Integer defenseID,Integer num){
		if(num <= 0){
			throw new GameException("无效操作");
		}
		
		//获得城市信息
		//获得防御建筑信息
		Defense defense = defenseService.getDefenseByID(defenseID);
		//获得防御建筑依赖信息
		ConstraintDepend constraintDepend = defense.getConstraintDepend();

		cityService.minusCityResources(cityID, constraintDepend.getCostWood() * num, constraintDepend.getCostSteel() * num, constraintDepend.getCostOil() * num, constraintDepend.getCostFood() * num, constraintDepend.getCostMoney() * num);
		
		CityDefense cd = cityDefenseDAO.getCityDefense(cityID, defenseID);
		
		//如果还没有该防御的记录就创建
		if(cd == null){
			cd = new CityDefense();
			cd.setCityID(cityID);
			cd.setDefenseID(defenseID);
			cd.setNum(0);
			cd.setCityDefenseID(cityDefenseDAO.createCityDefense(cd));
		}
		
		//获得所有生产中和待生产的防御建筑队列（结果按照时间的顺序排列）
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(cityID, ProductionQueueTypeConstant.PROCESS_BUILD_DEFENSE);
		Date finishTime ;
		
		//如果有队列，计算结束时间就依最后的记录作为参考
		if(plist.size() >0 ){
			ProductionQueue p = plist.get(plist.size()-1);
			finishTime = p.getFinishTime();
		}else{
			finishTime = new Date();
		}
		
		//计算结束时间
		finishTime.setTime(finishTime.getTime() + constraintDepend.getCostTime() * num * 1000);
		ProductionQueue productionQueue = new ProductionQueue();
		productionQueue.setAmount(num);
		productionQueue.setCityID(cityID);
		productionQueue.setStartTime(DateService.getCurrentUtilDate());
		productionQueue.setFinishTime(finishTime);
		productionQueue.setType(ProductionQueueTypeConstant.PROCESS_BUILD_DEFENSE);
		productionQueue.setTargetID(cd.getCityDefenseID());
		productionQueue.setProductionQueueID(productionQueueService.createProductionQueue(productionQueue));
		
		return productionQueue;
	}
	
	/** 完成城防建造时的处理函数
	 * @param pq
	 */
	public void finishBuildDefense(ProductionQueue pq){
		//保证后面的操作是同步的
		lock.lock();
		
		try{
			//如果进程已经不存在就返回
			if(productionQueueService.getProductionQueueByID(pq.getProductionQueueID()) == null){
				return;
			}
			//删除进程
			productionQueueService.deleteProductionQueueByID(pq.getProductionQueueID());
		}finally{
			//保证锁会被释放
			lock.unlock();
		}
		
		City city = cityService.getCityByID(pq.getCityID());
		
		if(city == null)
			throw new GameException("城市不存在！");
		
		CityDefense cd = cityDefenseDAO.getCityDefenseByID(pq.getTargetID());
		
		if(cd == null)
			throw new GameException("无效操作！");
		
		cd.setNum(cd.getNum() + pq.getAmount());
		//更新军械数量
		cityDefenseDAO.updateCityDefense(cd);
	 }
	
	/**
	 * 客户端完成生产计算时调用该方法可以及时刷新信息
	 * @param productionQueueID
	 */
	public void clientProcessFinished(Integer productionQueueID) {
		ProductionQueue pq = productionQueueService.getProductionQueueByID(productionQueueID);
		//已经被处理
		if(pq == null)
			return;
		
		//进程结束时间和当前时间的间隔毫秒数
		long clips = pq.getFinishTime().getTime()-(new Date()).getTime();
		
		//如果过了完成时间就对该进程进行处理
		if(clips <= 0){
			finishBuildDefense(pq);
		}
	}
	
	/**
	 * 取消城防建造
	 * @param productionQueueID
	 */
	public void cancelBuildDefense(Integer productionQueueID){
		ProductionQueue pq = productionQueueService.getProductionQueueByID(productionQueueID);
		//已经被处理
		if(pq == null)
			throw new GameException("已完成该城防建造");

		//获得所有该类下的进程	
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(pq.getCityID(), ProductionQueueTypeConstant.PROCESS_BUILD_DEFENSE);
		ProductionQueue p,pp;
		int ind = 0;
		//上个进程的结束时间(作为后面进程结束时间的调整依据)
		Date endDate;
		
		//获得被取消进程的位置
		for(int i=0; i<plist.size() ; i++){
			p = plist.get(i);
			
			if(p.getProductionQueueID().equals(productionQueueID)){
				ind = i;
				break;
			}
		}
		
		//如果取消的是第一个进程，那么后面的进程就以当前时间作为开始
		if(ind == 0){
			endDate = new Date();
		}else{//否则就以被取消进程之前的一个进程结束时间作为开始
			endDate = plist.get(ind-1).getFinishTime();
		}
		
		//调整所有在取消进程之后的进程的结束时间
		for(int j=ind+1; j<plist.size(); j++){
			p = plist.get(j);//当前调整的进程
			pp = plist.get(j-1);//上一个进程
			//计算新时间
			endDate.setTime(endDate.getTime() + p.getFinishTime().getTime() - pp.getFinishTime().getTime());
			p.setFinishTime(endDate);
			
			productionQueueService.updateProductionQueue(p);
		}
		
		//返回资源
		CityResource cityResource = cityService.getCityResourceByCityID(pq.getCityID());
		CityDefense cd = cityDefenseDAO.getCityDefenseByID(pq.getTargetID());
		Defense defense = defenseService.getDefenseByID(cd.getDefenseID());
		Map<String,Object> params = ConstraintDependUtil.getIncreaseHalfResourceParams(cityResource, defense.getConstraintDepend(), pq.getAmount());
		cityService.updateCityResource(params);
		
		productionQueueService.deleteProductionQueueByID(productionQueueID);
	}
	
	/**
	 * 立即完成所有的城防建造进程(需要道具)
	 * @param cityID
	 */
	public void finishAllBuildProcess(Integer cityID){
		//TODO 这里检查是否有宝物，没有就抛出异常
		
		//获得所有城防建造进程
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(cityID, ProductionQueueTypeConstant.PROCESS_BUILD_DEFENSE);
		
		for(ProductionQueue p : plist){
			finishBuildDefense(p);
		}
	}
	
	/**
	 * 增加城市的防御(不需要建筑时间)
	 * @param cityID 城市编号
	 * @param type 防御类型
	 * @param num 数量
	 */
	public void addCityDefense(Integer cityID,Integer defenseID,Integer num){
		
		CityDefense cd = cityDefenseDAO.getCityDefense(cityID, defenseID);
		
		//如果还没有该防御的记录就创建
		if(cd == null){
			cd = new CityDefense();
			cd.setCityID(cityID);
			cd.setDefenseID(defenseID);
			cd.setNum(num);
			
			cityDefenseDAO.createCityDefense(cd);
		}else{
			cd.setNum(cd.getNum()+num);
			
			cityDefenseDAO.updateCityDefense(cd);
		}
	}
	
	/**
	 * 减少城市的防御
	 * @param cityID 城市编号
	 * @param defenseID 防御类型
	 * @param num 数量
	 */
	public void minusCityDefense(Integer cityID,Integer defenseID,Integer num){
		
		if(num==0){
			return;
		}
		
		if(num<0){
			throw new GameException("城市防御数量非法。");
		}
		
		CityDefense cd = cityDefenseDAO.getCityDefense(cityID, defenseID);
		
		//如果还没有该防御的记录就创建
		if(cd == null || cd.getNum() < num){
			throw new GameException("城市防御数量不足。");
		}else{
			cd.setNum(cd.getNum() - num);
			
			cityDefenseDAO.updateCityDefense(cd);
		}
	}
	
	public ICityDefenseDAO getCityDefenseDAO() {
		return cityDefenseDAO;
	}

	public void setCityDefenseDAO(ICityDefenseDAO cityDefenseDAO) {
		this.cityDefenseDAO = cityDefenseDAO;
	}

	public IDefenseService getDefenseService() {
		return defenseService;
	}

	public void setDefenseService(IDefenseService defenseService) {
		this.defenseService = defenseService;
	}

	public IProductionQueueService getProductionQueueService() {
		return productionQueueService;
	}

	public void setProductionQueueService(
			IProductionQueueService ProductionQueueService) {
		this.productionQueueService = ProductionQueueService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

}
