package com.war.service.building.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.war.common.DateService;
import com.war.constant.ProductionQueueTypeConstant;
import com.war.domain.City;
import com.war.domain.CityOrdnance;
import com.war.domain.CityResource;
import com.war.domain.ConstraintDepend;
import com.war.domain.Ordnance;
import com.war.domain.ProductionQueue;
import com.war.exception.GameException;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IOrdnanceService;
import com.war.service.IProductionQueueService;
import com.war.service.building.IArmoryService;
import com.war.util.ConstraintDependUtil;

/**
 * 兵工厂service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class ArmoryService implements IArmoryService {

	private IOrdnanceService ordnanceService;
	
	private IProductionQueueService productionQueueService;
	
	private ICityService cityService;
	
	private IBuildingService buildingService;
	
	/**
	 * 该锁主要为了解决服务端，客户端同时进行完成处理而导致的并发问题
	 * ReentrantLock比synchronized效率更高
	 */
	private final Lock lock = new ReentrantLock();
	
	
	/**
	 * 拆卸指定数量的军械
	 * 
	 * @param cityOrdnanceID 城市军械编号
	 * @param num 军械数量
	 */
	public void backoutOrdnance(int cityOrdnanceID, int num) {
		
		CityOrdnance cityOrdnance = ordnanceService.getCityOrdnanceByID(cityOrdnanceID);
		
		if(cityOrdnance == null || num <= 0)
			throw new GameException("无效操作。");
		
		if(cityOrdnance.getNum() < num){
			throw new GameException("军械数量不足。");
		}
	
		//获得军械信息
		Ordnance ordnance = ordnanceService.getOrdnanceByID(cityOrdnance.getOrdnanceID());
		
		if(ordnance == null)
			throw new GameException("无效操作。");
		
		cityOrdnance.setNum(Math.max(0,cityOrdnance.getNum() - num));
		ordnanceService.updateCityOrdnance(cityOrdnance);
		
		CityResource cityResource = cityService.getCityResourceByCityID(cityOrdnance.getCityID());
		//获得军械依赖信息
		ConstraintDepend depend = ordnance.getConstraintDepend();
		Map<String,Object> params = ConstraintDependUtil.getIncreaseHalfResourceParams(cityResource, depend,num);
		cityService.updateCityResource(params);
	}

	/**
	 * 生产指定军械
	 * 
	 * @param cityID 城市编号
	 * @param ordnanceID 军械编号
	 * @param num 军械数量
	 */
	public ProductionQueue produceOrdnance(int cityID, int ordnanceID, int num) {
		// 获得军械信息
		Ordnance ordnance = ordnanceService.getOrdnanceByID(ordnanceID);
		
		if(ordnance == null || num <= 0)
			throw new GameException("无效操作！");
		
		
		// 验证资源和扣除资源
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		ConstraintDepend depend = ordnance.getConstraintDepend();
		buildingService.checkAllConstraintDepend(city, depend,num);
		Map<String,Object> params = ConstraintDependUtil.getDecreaseResourceParams(cityResource, depend,num);
		cityService.updateCityResource(params);
		
		CityOrdnance cityOrdnance = ordnanceService.getCityOrdnance(cityID, ordnanceID);
		
		// 如果还没有记录,先插入一条记录
		if(cityOrdnance == null){
			cityOrdnance = new CityOrdnance();
			cityOrdnance.setCityID(cityID);
			cityOrdnance.setOrdnanceID(ordnanceID);
			cityOrdnance.setNum(0);
			
			cityOrdnance.setCityOrdnanceID(ordnanceService.createCityOrdnance(cityOrdnance));
		}
		
		// 获得所有生产中和待生产的军械队列（结果按照时间的顺序排列）
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(cityID, ProductionQueueTypeConstant.PROCESS_PRODUCE_ORDNANCE);
		Date finishTime ;
		
		// 如果有队列，计算结束时间就依最后的记录作为参考
		if(plist.size() >0 ){
			ProductionQueue p = plist.get(plist.size()-1);
			finishTime = p.getFinishTime();
		}else{
			finishTime = new Date();
		}
		
		// 计算结束时间
		finishTime.setTime(finishTime.getTime() + depend.getCostTime() * num * 1000);
		ProductionQueue productionQueue = new ProductionQueue();
		productionQueue.setAmount(num);
		productionQueue.setCityID(cityID);
		productionQueue.setStartTime(DateService.getCurrentUtilDate());
		productionQueue.setFinishTime(finishTime);
		productionQueue.setType(ProductionQueueTypeConstant.PROCESS_PRODUCE_ORDNANCE);
		productionQueue.setTargetID(cityOrdnance.getCityOrdnanceID());
		productionQueue.setProductionQueueID(productionQueueService.createProductionQueue(productionQueue));
		
		return productionQueue;
	}
	
	/** 完成军械生产时的处理函数
	 * @param pq
	 */
	public void finishProduceOrdnance(ProductionQueue pq){
		//保证后面的操作是同步的
		lock.lock();
		try{
			if(productionQueueService.getProductionQueueByID(pq.getProductionQueueID()) == null){
				return;
			}
			//删除进程
			productionQueueService.deleteProductionQueueByID(pq.getProductionQueueID());
			
			City city = cityService.getCityByID(pq.getCityID());
			
			if(city == null)
				throw new GameException("城市不存在！");
			
			CityOrdnance co =  ordnanceService.getCityOrdnanceByID(pq.getTargetID());
			
			if(co == null)
				throw new GameException("无效操作！");
			
			co.setNum(co.getNum() + pq.getAmount());
			//更新军械数量
			ordnanceService.updateCityOrdnance(co);
			
		}finally{
			lock.unlock();
		}
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
			finishProduceOrdnance(pq);
		}
	}
	
	/**
	 * 取消军械生产
	 * @param productionQueueID
	 */
	public void cancelProduceOrdnance(Integer productionQueueID){
		ProductionQueue pq = productionQueueService.getProductionQueueByID(productionQueueID);
		//已经被处理
		if(pq == null)
			throw new GameException("已完成该军械生产。");

		//获得所有该类下的进程	
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(pq.getCityID(), ProductionQueueTypeConstant.PROCESS_PRODUCE_ORDNANCE);
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
		
		//TODO 返回部分资源
		//获得军械信息
		Ordnance ordnance = ordnanceService.getOrdnanceByID(ordnanceService.getCityOrdnanceByID(pq.getTargetID()).getOrdnanceID());
		
		if(ordnance == null)
			throw new GameException("无效操作！");
		
		CityResource cityResource = cityService.getCityResourceByCityID(pq.getCityID());
		//获得军械依赖信息
		ConstraintDepend depend = ordnance.getConstraintDepend();
		Map<String,Object> params = ConstraintDependUtil.getIncreaseHalfResourceParams(cityResource, depend,pq.getAmount());
		cityService.updateCityResource(params);
		
		productionQueueService.deleteProductionQueueByID(productionQueueID);
	}
	
	/**
	 * 立即完成所有的军械生产进程(需要道具)
	 * @param cityID
	 */
	public void finishAllProduceQueue(Integer cityID){
		//TODO 这里检查是否有宝物，没有就抛出异常
		
		//获得所有军械生产进程
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(cityID, ProductionQueueTypeConstant.PROCESS_PRODUCE_ORDNANCE);
		
		for(ProductionQueue p : plist){
			finishProduceOrdnance(p);
		}
	}
	
	public static void finishAllProduceQueue(ArmoryService armoryService,
			Integer cityID) {
		armoryService.finishAllProduceQueue(cityID);
	}
	

	public IOrdnanceService getOrdnanceService() {
		return ordnanceService;
	}

	public void setOrdnanceService(IOrdnanceService ordnanceService) {
		this.ordnanceService = ordnanceService;
	}

	public IProductionQueueService getProductionQueueService() {
		return productionQueueService;
	}

	public void setProductionQueueService(
			IProductionQueueService productionQueueService) {
		this.productionQueueService = productionQueueService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
}
