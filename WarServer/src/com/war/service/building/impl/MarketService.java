package com.war.service.building.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.DateService;
import com.war.common.TemplateService;
import com.war.constant.TradeConstant;
import com.war.dao.IResTradeDAO;
import com.war.dao.IResTransportationDAO;
import com.war.dao.ITradeQueueDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.domain.ResTrade;
import com.war.domain.ResTransportation;
import com.war.domain.TradeQueue;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IPlayerService;
import com.war.service.IReportService;
import com.war.service.building.IMarketService;
import com.war.socket.game.GameSocketService;
import com.war.util.CostTimeCalculateUtil;

public class MarketService implements IMarketService {

	private IResTransportationDAO resTransportationDAO;
	
	private IResTradeDAO resTradeDAO;
	
	private ITradeQueueDAO tradeQueueDAO; 
	
	private IPlayerService playerService;
	
	private ICityService cityService;
	
	private IReportService reportService;
	
	private static Logger logger = Logger.getLogger(MarketService.class);
	
	private final Lock transportResourceLock = new ReentrantLock();
	
	private final Lock sellResourceLock = new ReentrantLock();
	
	private final Lock cancelResourceLock = new ReentrantLock();
	
	private final Lock buyResourceLock = new ReentrantLock();
	
	private final Lock finishResourceTransportationLock = new ReentrantLock();
	
	private final Lock resourceTransportationReturnLock = new ReentrantLock();
	
	private final Lock finishResourceTradeLock = new ReentrantLock();
	
	
	public void transportResouce(Integer cityID, Integer targetPosX, Integer targetPosY, Long woodAmount, Long steelAmount, Long oilAmount, Long foodAmount, Long moneyAmount) {
		
		try {
			transportResourceLock.lock();

			City targetCity = cityService.getCityByPosXAndPosY(targetPosX, targetPosY);
			if (targetCity==null) {
				throw new GameException("目标城市不存在。");
			}

			City city = cityService.getCityByID(cityID);
			long resourceTotal = woodAmount + steelAmount + oilAmount + foodAmount + moneyAmount;
			
			
			// 判断商人是否足够
			if(resourceTotal>city.getBusinessmanFree()*1000){
				throw new GameException("城市空闲商人不足。");
			}
			
			// 扣减运送城市相关资源
			cityService.minusCityResources(city.getCityID(), woodAmount, steelAmount, oilAmount, foodAmount, moneyAmount);
			
			// 使用商人数量
			int businessmanNum = (int)Math.floor(resourceTotal/1000);
			
			// 更新城市商人信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cityID", city.getCityID());
			params.put("businessmanFree", city.getBusinessmanFree()-businessmanNum);
			cityService.updateCity(params);
			
			// 创建资源运输信息
			ResTransportation resTransportation = new ResTransportation();
			resTransportation.setWoodAmount(woodAmount);
			resTransportation.setSteelAmount(steelAmount);
			resTransportation.setOilAmount(oilAmount);
			resTransportation.setFoodAmount(foodAmount);
			resTransportation.setMoneyAmount(moneyAmount);
			Integer resTransportationID = resTransportationDAO.createResTransportation(resTransportation);
			
			// 创建交易队列
			TradeQueue tradeQueue = new TradeQueue();
			tradeQueue.setSellerID(city.getCityID());
			tradeQueue.setBuyerID(targetCity.getCityID());
			tradeQueue.setCityID(city.getCityID());
			tradeQueue.setTargetCityID(targetCity.getCityID());
			tradeQueue.setTargetID(resTransportationID);
			tradeQueue.setType(TradeConstant.RESOURCE_TRANSPORTATION);
			tradeQueue.setBusinessmanNum(businessmanNum);
			Date arriveTime = new Date();
			// 计算到达时间
			arriveTime.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateBusinessmanCostTime(city.getPosX(), city.getPosY(), targetCity.getPosX(), targetCity.getPosY())*1000);
			tradeQueue.setArriveTime(arriveTime);
			
			tradeQueueDAO.createTradeQueue(tradeQueue);
		} finally {
			transportResourceLock.unlock();
		}
	}
	
	public List<ResTrade> getResourceSalesList(Integer cityID,Integer resourceType,Integer start,Integer offset){
		
		Map<String,Integer> cityPosMap = cityService.getCityPosByCityID(cityID);
		
		List<ResTrade> resTradeList = resTradeDAO.getResTradePagingListByCityPosOrderByDistance(cityID,cityPosMap.get("posX"),cityPosMap.get("posY"), resourceType, start, offset);
		
		for(int i=0;i<resTradeList.size();i++){
			resTradeList.get(i).setCityInfo(cityService.getCityInfoByCityID(resTradeList.get(i).getCityID()));
		}
		
		return resTradeList;
	}
	
	public Integer getResourceSalesAmount(Integer cityID,Integer resourceType){
		return resTradeDAO.getResourceSalesAmount(cityID,resourceType);
	}
	
	public void sellResource(ResTrade resTrade){
		
		try {
			sellResourceLock.lock();
		
			long woodNumber = resTrade.getResourceType()==1?resTrade.getAmount()*1000:0L;
			long steelNumber = resTrade.getResourceType()==2?resTrade.getAmount()*1000:0L;
			long oilNumber = resTrade.getResourceType()==3?resTrade.getAmount()*1000:0L;
			long foodNumber = resTrade.getResourceType()==4?resTrade.getAmount()*1000:0L;
			
			int businessmanFree = cityService.getCityBusinessFree(resTrade.getCityID());
			
			//判断商人是否足够
			if(businessmanFree<1){
				throw new GameException("城市空闲商人不足。");
			}
			
			//扣减城市资源
			cityService.minusCityResources(resTrade.getCityID(), woodNumber, steelNumber, oilNumber, foodNumber, 0L);
			
			//更新城市商人信息
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cityID", resTrade.getCityID());
			params.put("businessmanFree", businessmanFree - 1);
			cityService.updateCity(params);
			
			resTradeDAO.createResTrade(resTrade);
		} finally {
			sellResourceLock.unlock();
		}
	}
	
	public void cancelResourceSale(Integer resTradeID){
		
		try {
			cancelResourceLock.lock();
		
			ResTrade resTrade = resTradeDAO.getResTradeByID(resTradeID);
			
			if(resTrade==null || resTrade.getState()==2){
				throw new GameException("资源已被售出，无法取消交易。");
			}
			
			long woodNumber = resTrade.getResourceType()==1?resTrade.getAmount()*1000:0L;
			long steelNumber = resTrade.getResourceType()==2?resTrade.getAmount()*1000:0L;
			long oilNumber = resTrade.getResourceType()==3?resTrade.getAmount()*1000:0L;
			long foodNumber = resTrade.getResourceType()==4?resTrade.getAmount()*1000:0L;
			
			cityService.addCityResources(resTrade.getCityID(), woodNumber, steelNumber, oilNumber, foodNumber, 0L);
			
			resTradeDAO.deleteResTradeByID(resTradeID);
			
			//更新城市商人信息
			int businessmanFree = cityService.getCityBusinessFree(resTrade.getCityID());
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cityID", resTrade.getCityID());
			params.put("businessmanFree", businessmanFree + 1);
			cityService.updateCity(params);
		} finally {
			cancelResourceLock.unlock();
		}
	}
	
	public void cancelCityAllResourceSales(Integer cityID) {
		List<ResTrade> resTradeList = this.getCityResourceSalesList(cityID);
		for(int i=0;i<resTradeList.size();i++){
			this.cancelResourceSale(resTradeList.get(i).getResTradeID());
		}
	}
	
	public void buyResource(Integer resTradeID, Integer cityID){
		
		try {
			buyResourceLock.lock();
			
			ResTrade resTrade = resTradeDAO.getResTradeByID(resTradeID);
			
			if(resTrade.getState() == 2){
				throw new GameException("资源已被售出。");
			}
			
			int businessFree = cityService.getCityBusinessFree(cityID);
			
			long money = resTrade.getAmount() * resTrade.getPrice();
			
			// 判断商人是否足够
			if(businessFree < 1)
				throw new GameException("城市空闲商人不足。");
			
			// 扣减金钱
			cityService.minusCityResources(cityID, 0L, 0L, 0L, 0L, money);
			
			// 更新城市商人信息
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cityID", cityID);
			params.put("businessmanFree", businessFree - 1);
			cityService.updateCity(params);
			
			// 更新资源交易状态为2(交易中)
			resTradeDAO.updateResTradeState(resTrade.getResTradeID(), 2);
	
			// 获得城市坐标
			Map<String,Integer> cityPosMap = cityService.getCityPosByCityID(resTrade.getCityID());
			int cityPosX = cityPosMap.get("posX");
			int cityPosY = cityPosMap.get("posY");
			
			Map<String,Integer> targetCityPosMap = cityService.getCityPosByCityID(cityID);
			int targetCityPosX = targetCityPosMap.get("posX");
			int targetCityPosY = targetCityPosMap.get("posY");
			
			long costTime = CostTimeCalculateUtil.calculateBusinessmanCostTime(cityPosX, cityPosY, targetCityPosX, targetCityPosY) * 1000;
			
			Date arriveTime = new Date();
			arriveTime.setTime(System.currentTimeMillis() + costTime);
			
			TradeQueue sellerTradeQueue = new TradeQueue();
			sellerTradeQueue.setSellerID(resTrade.getCityID());
			sellerTradeQueue.setBuyerID(cityID);
			sellerTradeQueue.setCityID(cityID);
			sellerTradeQueue.setTargetCityID(resTrade.getCityID());
			sellerTradeQueue.setTargetID(resTrade.getResTradeID());
			sellerTradeQueue.setType(TradeConstant.TARDE_RETURN);
			sellerTradeQueue.setBusinessmanNum(1);
			sellerTradeQueue.setArriveTime(arriveTime);
			tradeQueueDAO.createTradeQueue(sellerTradeQueue);
			
			TradeQueue buyerTradeQueue = new TradeQueue();
			buyerTradeQueue.setSellerID(resTrade.getCityID());
			buyerTradeQueue.setBuyerID(cityID);
			buyerTradeQueue.setCityID(resTrade.getCityID());
			buyerTradeQueue.setTargetCityID(cityID);
			buyerTradeQueue.setTargetID(resTrade.getResTradeID());
			buyerTradeQueue.setType(TradeConstant.TARDE_RETURN);
			buyerTradeQueue.setBusinessmanNum(1);
			buyerTradeQueue.setArriveTime(arriveTime);
			tradeQueueDAO.createTradeQueue(buyerTradeQueue);
			
			//发送报告
			Map<String,Object> reportParams = new HashMap<String,Object>();
			String resourceType;
			switch(resTrade.getResourceType()){
				case 1:
					resourceType = "木材";
					break;
				case 2:
					resourceType = "钢铁";
					break;
				case 3:
					resourceType = "石油";
					break;
				case 4:
					resourceType = "食物";
					break;
				default:
					resourceType = "未知";
					break;
			}
			reportParams.put("resourceType", resourceType);
			reportParams.put("num", resTrade.getAmount());
			reportParams.put("money", money);
			reportParams.put("arriveTime", DateService.parseDateToReportTimeString(arriveTime));
			
			String contentSeller = null,contentBuyer = null;
			try {
				contentSeller = TemplateService.format("Market_Seller.ftl", reportParams);
				contentBuyer = TemplateService.format("Market_Buyer.ftl", reportParams);
			} catch (Exception e) {
				logger.error("异常：", e);
			}
			
			//向出售方发送报告
			Integer sellerPlayerID = cityService.getPlayerIDByCityID(resTrade.getCityID());
			reportService.sendOtherReport(sellerPlayerID, "资源售出报告", contentSeller);
			
			//向购买方发送报告
			Integer buyerPlayerID = cityService.getPlayerIDByCityID(cityID);
			reportService.sendOtherReport(buyerPlayerID, "资源买入报告", contentBuyer);
		} finally {
			buyResourceLock.unlock();
		}
	}
	
	public List<ResTrade> getCityResourceSalesList(Integer cityID){
		return resTradeDAO.getResourceSalesListByCityID(cityID);
	}

	public List<TradeQueue> getCityResourceTransportationList(Integer cityID) {

		// 运输方运输途中
		List<TradeQueue> resourceTransportationList = tradeQueueDAO.getTradeQueueListBySellerIDAndType(cityID, TradeConstant.RESOURCE_TRANSPORTATION);
		// 运输方返回途中
		List<TradeQueue> resourceTransportationReturnList = tradeQueueDAO.getTradeQueueListBySellerIDAndType(cityID, TradeConstant.RESOURCE_TRANSPORTATION_RETURN);
		// 接受方接收途中
		List<TradeQueue> resourceTransportationReceiveList = tradeQueueDAO.getTradeQueueListByBuyerIDAndType(cityID, TradeConstant.RESOURCE_TRANSPORTATION);
		
		// 合并列表
		resourceTransportationList.addAll(resourceTransportationReturnList);
		resourceTransportationList.addAll(resourceTransportationReceiveList);
		
		for (TradeQueue tradeQueue:resourceTransportationList) {
			tradeQueue.setSellerCityInfo(cityService.getCityInfoByCityID(tradeQueue.getSellerID()));
			tradeQueue.setBuyerCityInfo(cityService.getCityInfoByCityID(tradeQueue.getBuyerID()));
			
			tradeQueue.setTargetObject(resTransportationDAO.getResTransportationByID(tradeQueue.getTargetID()));
		}
		
		return resourceTransportationList;
	}
	
	public List<TradeQueue> getCityTradeQueueList(Integer cityID){
		
		List<TradeQueue> tradeQueueList = tradeQueueDAO.getTradeQueueListByCityIDAndType(cityID, TradeConstant.TARDE_RETURN);
		
		for (TradeQueue tradeQueue:tradeQueueList) {
			tradeQueue.setSellerCityInfo(cityService.getCityInfoByCityID(tradeQueue.getSellerID()));
			tradeQueue.setBuyerCityInfo(cityService.getCityInfoByCityID(tradeQueue.getBuyerID()));
			
			tradeQueue.setTargetObject(resTradeDAO.getResTradeByID(tradeQueue.getTargetID()));
		}
		
		return tradeQueueList;
	}
	
	public void finishResourceTransportation(TradeQueue tradeQueue){
		
		try{
			finishResourceTransportationLock.lock();
			
			if(tradeQueueDAO.getTradeQueueByID(tradeQueue.getTradeQueueID()) == null){
				return;
			}
			//删除交易队列
			tradeQueueDAO.deleteTradeQueueByID(tradeQueue.getTradeQueueID());
			
			ResTransportation resTransportation = resTransportationDAO.getResTransportationByID(tradeQueue.getTargetID());
			
			//删除资源运输信息
			//resTransportationDAO.deleteResTransportationByID(tradeQueue.getTargetID());
			
			CityResource cityResource = cityService.getCityResourceByCityID(tradeQueue.getTargetCityID());
			
			Map<String,Object> cityResourceParams = new HashMap<String,Object>();
			cityResourceParams.put("cityID", tradeQueue.getTargetCityID());
			cityResourceParams.put("woodNum", Math.min(cityResource.getWoodNum()+resTransportation.getWoodAmount(), cityResource.getResourceNumMax()));
			cityResourceParams.put("steelNum", Math.min(cityResource.getSteelNum()+resTransportation.getSteelAmount(), cityResource.getResourceNumMax()));
			cityResourceParams.put("oilNum", Math.min(cityResource.getOilNum()+resTransportation.getOilAmount(), cityResource.getResourceNumMax()));
			cityResourceParams.put("foodNum", Math.min(cityResource.getFoodNum()+resTransportation.getFoodAmount(), cityResource.getResourceNumMax()));
			cityResourceParams.put("moneyNum", cityResource.getMoneyNum()+resTransportation.getMoneyAmount());
			cityService.updateCityResource(cityResourceParams);
			
			//获得城市坐标
			Map<String,Integer> cityPosMap = cityService.getCityPosByCityID(tradeQueue.getTargetCityID());
			int cityPosX = cityPosMap.get("posX");
			int cityPosY = cityPosMap.get("posY");
			
			Map<String,Integer> targetCityPosMap = cityService.getCityPosByCityID(tradeQueue.getCityID());
			int targetCityPosX = targetCityPosMap.get("posX");
			int targetCityPosY = targetCityPosMap.get("posY");
			
			//商人返回
			TradeQueue returnTradeQueue = new TradeQueue();
			returnTradeQueue.setSellerID(tradeQueue.getCityID());
			returnTradeQueue.setBuyerID(tradeQueue.getTargetCityID());
			returnTradeQueue.setCityID(tradeQueue.getTargetCityID());
			returnTradeQueue.setTargetCityID(tradeQueue.getCityID());
			returnTradeQueue.setTargetID(tradeQueue.getTargetID());
			returnTradeQueue.setType(TradeConstant.RESOURCE_TRANSPORTATION_RETURN);
			returnTradeQueue.setBusinessmanNum(tradeQueue.getBusinessmanNum());
			Date arriveTime = new Date();
			
			//计算到达时间
			arriveTime.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateBusinessmanCostTime(cityPosX, cityPosY, targetCityPosX, targetCityPosY)*1000);
			returnTradeQueue.setArriveTime(arriveTime);
			tradeQueueDAO.createTradeQueue(returnTradeQueue);
			
			int playerID = cityService.getPlayerIDByCityID(tradeQueue.getTargetCityID());
			String fromCityName = cityService.getCityNameByCityID(tradeQueue.getCityID());
			StringBuffer resourceNameSB = new StringBuffer();
			if(resTransportation.getWoodAmount()>0){
				resourceNameSB.append(" ");
				resourceNameSB.append("木材：");
				resourceNameSB.append(resTransportation.getWoodAmount());
				resourceNameSB.append(" ");
			}
			if(resTransportation.getSteelAmount()>0){
				resourceNameSB.append(" ");
				resourceNameSB.append("钢铁：");
				resourceNameSB.append(resTransportation.getSteelAmount());
				resourceNameSB.append(" ");
			}
			if(resTransportation.getOilAmount()>0){
				resourceNameSB.append(" ");
				resourceNameSB.append("石油：");
				resourceNameSB.append(resTransportation.getOilAmount());
				resourceNameSB.append(" ");
			}
			if(resTransportation.getFoodAmount()>0){
				resourceNameSB.append(" ");
				resourceNameSB.append("食物：");
				resourceNameSB.append(resTransportation.getFoodAmount());
				resourceNameSB.append(" ");
			}
			if(resTransportation.getMoneyAmount()>0){
				resourceNameSB.append(" ");
				resourceNameSB.append("金钱：");
				resourceNameSB.append(resTransportation.getMoneyAmount());
				resourceNameSB.append(" ");
			}
			
			reportService.sendOtherReport(playerID, "资源送达报告", "来自城市 " + fromCityName + " 运送的资源(" + resourceNameSB.toString() + ")已经运送至您的城市，请查收。");
			
			//向客户端push强制刷新城市资源
			JSONObject json = new JSONObject();
			try {
				json.put("type", 21);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			Integer targetPlayerID = cityService.getPlayerIDByCityID(tradeQueue.getTargetCityID());
			GameSocketService.sendDataToClient(targetPlayerID, json);
				
		}catch(Exception e){
			logger.error("异常：", e);
			
		}finally{
			finishResourceTransportationLock.unlock();
		}
	}
	
	public void resourceTransportationReturn(TradeQueue tradeQueue){
		
		try {
			resourceTransportationReturnLock.lock();
			
			if(tradeQueueDAO.getTradeQueueByID(tradeQueue.getTradeQueueID()) == null){
				return;
			}
			
			//删除交易队列
			tradeQueueDAO.deleteTradeQueueByID(tradeQueue.getTradeQueueID());
			
			try{
				//更新城市商人信息
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("cityID", tradeQueue.getTargetCityID());
				params.put("businessmanFree", cityService.getCityBusinessFree(tradeQueue.getTargetCityID()) + tradeQueue.getBusinessmanNum());
				cityService.updateCity(params);
				
				int playerID = cityService.getPlayerIDByCityID(tradeQueue.getTargetCityID());
				String fromCityName = cityService.getCityNameByCityID(tradeQueue.getCityID());
				reportService.sendOtherReport(playerID, "商人返回报告", "您的商人已从城市 " + fromCityName + " 返回。");
			}catch(Exception e){
				logger.error("异常：", e);
			}
			
		} finally {
			resourceTransportationReturnLock.unlock();
		}
		
	}
	
	public void finishResourceTrade(TradeQueue tradeQueue){
		
		try {
			finishResourceTradeLock.lock();
			
			if (tradeQueueDAO.getTradeQueueByID(tradeQueue.getTradeQueueID()) == null) {
				return;
			}
			// 删除交易队列
			tradeQueueDAO.deleteTradeQueueByID(tradeQueue.getTradeQueueID());
			
			ResTrade resTrade = resTradeDAO.getResTradeByID(tradeQueue.getTargetID());
	
			//删除资源交易信息
			//resTradeDAO.deleteResTradeByID(tradeQueue.getTargetID());
			
			if(tradeQueue.getSellerID().intValue() == tradeQueue.getTargetCityID().intValue()){
				//出售方
				
				// 更新出售方城市商人信息
				int sellerBusinessmanFree = cityService.getCityBusinessFree(tradeQueue.getTargetCityID());
				Map<String,Object> sellerParams = new HashMap<String,Object>();
				sellerParams.put("cityID", tradeQueue.getTargetCityID());
				sellerParams.put("businessmanFree", sellerBusinessmanFree + 1);
				cityService.updateCity(sellerParams);
				
				// 增加金钱
				cityService.addCityResources(tradeQueue.getTargetCityID(), 0L, 0L, 0L, 0L, resTrade.getPrice()*resTrade.getAmount());
				
				// 发送报告
				int playerID = cityService.getPlayerIDByCityID(tradeQueue.getTargetCityID());
				String fromCityName = cityService.getCityNameByCityID(tradeQueue.getCityID());
				reportService.sendOtherReport(playerID, "商人返回报告", "您的商人已从城市 " + fromCityName + " 返回，共带回 金钱" + resTrade.getPrice()*resTrade.getAmount() + "，请查收。");
				
				//向客户端push强制刷新城市资源
				JSONObject json = new JSONObject();
				json.put("type", 21);
				GameSocketService.sendDataToClient(playerID, json);
				
			} else if (tradeQueue.getBuyerID().intValue() == tradeQueue.getTargetCityID().intValue()){
				//购买方
				
				String resourceType = null;
				long woodNum=0L, steelNum=0L, oilNum=0L, foodNum=0L;
				
				if(resTrade.getResourceType()==1){
					woodNum = resTrade.getAmount() * 1000;
					resourceType = "木材";
				}
				if(resTrade.getResourceType()==2){
					steelNum = resTrade.getAmount() * 1000;
					resourceType = "钢铁";
				}
				if(resTrade.getResourceType()==3){
					oilNum = resTrade.getAmount() * 1000;
					resourceType = "石油";
				}
				if(resTrade.getResourceType()==4){
					foodNum = resTrade.getAmount() * 1000;
					resourceType = "食物";
				}
				
				// 更新购买方城市商人信息
				int buyerBusinessmanFree = cityService.getCityBusinessFree(tradeQueue.getTargetCityID());
				Map<String, Object> buyerCityParams = new HashMap<String,Object>();
				
				buyerCityParams.put("cityID", tradeQueue.getTargetCityID());
				buyerCityParams.put("businessmanFree", buyerBusinessmanFree + 1);
				cityService.updateCity(buyerCityParams);
				
				// 增加资源
				cityService.addCityResources(tradeQueue.getTargetCityID(), woodNum, steelNum, oilNum, foodNum, 0L);

				// 发送报告
				int playerID = cityService.getPlayerIDByCityID(tradeQueue.getTargetCityID());
				String fromCityName = cityService.getCityNameByCityID(tradeQueue.getCityID());
				reportService.sendOtherReport(playerID, "商人返回报告", "您的商人已从城市 " + fromCityName + " 返回，共带回 " + resourceType + resTrade.getAmount()+ "手，请查收。");
				
				// 向客户端push强制刷新城市资源
				JSONObject json = new JSONObject();
				json.put("type", 21);
				GameSocketService.sendDataToClient(playerID, json);
			}
			
			// 若交易已完成则删除交易信息
			
			
		} catch(Exception e) {
			logger.error("异常：", e);
		} finally {
			finishResourceTradeLock.unlock();
		}
		
	}
	
	
	public IResTransportationDAO getResTransportationDAO() {
		return resTransportationDAO;
	}

	public void setResTransportationDAO(IResTransportationDAO resTransportationDAO) {
		this.resTransportationDAO = resTransportationDAO;
	}

	public IResTradeDAO getResTradeDAO() {
		return resTradeDAO;
	}

	public void setResTradeDAO(IResTradeDAO resTradeDAO) {
		this.resTradeDAO = resTradeDAO;
	}
	
	public ITradeQueueDAO getTradeQueueDAO() {
		return tradeQueueDAO;
	}

	public void setTradeQueueDAO(ITradeQueueDAO tradeQueueDAO) {
		this.tradeQueueDAO = tradeQueueDAO;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

}
