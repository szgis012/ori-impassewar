package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.war.constant.HeroConstant;
import com.war.constant.PlayerStateConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.ICityHeroDAO;
import com.war.dao.ICityHeroExtDAO;
import com.war.dao.IPlayerDAO;
import com.war.dao.ITreasureQueueDAO;
import com.war.domain.City;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.CityResource;
import com.war.domain.Player;
import com.war.domain.TreasureQueue;
import com.war.service.ICityService;
import com.war.service.ITreasureQueueService;
import com.war.util.ResourceCalculateUtil;

/**
 * 宝物效果持续时间队列service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class TreasureQueueService implements ITreasureQueueService {
	
	private ITreasureQueueDAO treasureQueueDAO ;
	
	private ICityService cityService;
	
	private IPlayerDAO playerDAO;
	
	private ICityHeroDAO cityHeroDAO;
	
	private ICityHeroExtDAO cityHeroExtDAO;
	
	private static Logger logger = Logger.getLogger(TreasureQueueService.class);
	
	public Integer createTreasureQueue(TreasureQueue treasureQueue) {
		return treasureQueueDAO.createTreasureQueue(treasureQueue);
	}

	public TreasureQueue getTreasureQueueByID(Integer treasureQueueID) {
		return treasureQueueDAO.getTreasureQueueByID(treasureQueueID);
	}

	public List<TreasureQueue> getTreasureQueueList() {
		return treasureQueueDAO.getTreasureQueueList();
	}

	public void updateTreasureQueue(TreasureQueue treasureQueue) {
		treasureQueueDAO.updateTreasureQueue(treasureQueue);
	}

	public void cancelTreasureQueue(Integer treasureQueueID){
		TreasureQueue treasureQueue = this.getTreasureQueueByID(treasureQueueID);
		
		//如果队列还在
		if(treasureQueue != null){
			handleTreasureQueue(treasureQueue);
		}
	}
	
	public void handleTreasureQueue(TreasureQueue treasureQueue){
		
		try {
			//对不同的效果进行不同的处理
			switch (treasureQueue.getType()) {
				//对四种资源加成型宝物处理
				case TreasureTypeConstant.TREASURE_FOOD_ADD:
				case TreasureTypeConstant.TREASURE_OIL_ADD:
				case TreasureTypeConstant.TREASURE_STEEL_ADD:
				case TreasureTypeConstant.TREASURE_WOOD_ADD:
				case TreasureTypeConstant.TREASURE_MONEY_ADD:
					updateCityResourceTreasureAdd(treasureQueue);
					break;
					
				case TreasureTypeConstant.STORAGE_ADD:
					updateStorage(treasureQueue);
					break;
					
				//免战
				case TreasureTypeConstant.AVOID_WAR:
					cancelAvoidWar(treasureQueue.getTargetID());
					break;
					
				//平息暴乱 ■
				case TreasureTypeConstant.SUPPRESS_RIOT:
					break;
					
				//战略欺骗 ■
				case TreasureTypeConstant.STRATEGY_CHEAT:
					break;
					
				//战略伪装 ■
				case TreasureTypeConstant.STRATEGY_CAMOUFLAGE:
					break;
					
				//军队攻击加成 ■
				case TreasureTypeConstant.MILITARY_ATTACK_ADD:
					break;
					
				//军队防御加成 ■
				case TreasureTypeConstant.MILITARY_DEFENSE_ADD:
					break;
					
				//指挥官获得经验加成
				case TreasureTypeConstant.COMMANDER_EXP_ADD:
					cancelCityHeroExpAdd(treasureQueue.getTargetID());
					break;	
					
				//人口上限加成
				case TreasureTypeConstant.POPULATION_MAX_ADD:
					cancelAddCityPopulationMax(treasureQueue.getTargetID(),10);
					break;
					
				// 指挥官统御加成
				case TreasureTypeConstant.COMMANDER_REIN_ADD:
					cancelCityHeroReinAdd(treasureQueue.getTargetID(),10);
					break;
					
				// 指挥官指挥加成
				case TreasureTypeConstant.COMMANDER_COMMAND_ADD:
					cancelCityHeroCommandAdd(treasureQueue.getTargetID(), 20);
					break;
					
				// 指挥官防御加成
				case TreasureTypeConstant.COMMANDER_DEFENSE_ADD:
					cancelCityHeroDefenseAdd(treasureQueue.getTargetID(), 20);
					break;
					
				// 指挥官思维加成	
				case TreasureTypeConstant.COMMANDER_MIND_ADD:
					cancelCityHeroMindAdd(treasureQueue.getTargetID(), 20);
					break;
					
				// 指挥官行政加成
				case TreasureTypeConstant.COMMANDER_EXECUTIVEPOWER_ADD:
					cancelCityHeroExecutivepowerAdd(treasureQueue.getTargetID(), 20);
					break;
				
			}
			
			//删除队列
			treasureQueueDAO.deleteTreasureQueueByID(treasureQueue.getTreasureQueueID());
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 更新仓储信息
	 * @param treasureQueue
	 */
	private void updateStorage(TreasureQueue treasureQueue){
		//仓储增加的上限,目前为25%
		int num = 25;
		CityResource cityResource = cityService.getCityResourceByCityID(treasureQueue.getTargetID());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", treasureQueue.getTargetID());
		params.put("resourceNumMax", (long)(cityResource.getResourceNumMax() / (100 + num) * 100));
		//更新仓储上限
		cityService.updateCityResource(params);
	}
	
	/**
	 * 资源生产型宝物效果结束时更新资源生产信息
	 * @param treasureQueue
	 */
	private void updateCityResourceTreasureAdd(TreasureQueue treasureQueue){
		City city = cityService.getCityByID(treasureQueue.getTargetID());
		CityResource cityResource = cityService.getCityResourceByCityID(treasureQueue.getTargetID());
		Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityResourceParams.put("cityID", city.getCityID());
		Integer treasureAdd ;
		Long output;
		
		//加成百分比,目前四种资源加成的宝物都具有相同的加成值
		int num = 25;
		
		switch(treasureQueue.getType()){
			//食物生产加成
			case TreasureTypeConstant.TREASURE_FOOD_ADD:
				treasureAdd = getTreasureAdd(cityResource.getFoodTreasureAdd(),num);
				output = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), treasureAdd);
				cityResourceParams.put("foodOutput", output);
				cityResourceParams.put("foodTreasureAdd", treasureAdd);
				break;
			//木材生产加成	
			case TreasureTypeConstant.TREASURE_WOOD_ADD:
				treasureAdd = getTreasureAdd(cityResource.getWoodTreasureAdd(),num);
				output = ResourceCalculateUtil.calculateWoodOutput(city.getTax(), cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), treasureAdd);
				cityResourceParams.put("woodOutput", output);
				cityResourceParams.put("woodTreasureAdd", treasureAdd);
				break;
			//钢铁生产加成	
			case TreasureTypeConstant.TREASURE_STEEL_ADD:
				treasureAdd = getTreasureAdd(cityResource.getSteelTreasureAdd(),num);
				output = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), treasureAdd);
				cityResourceParams.put("steelOutput", output);
				cityResourceParams.put("steelTreasureAdd", treasureAdd);
				break;
			//石油生产加成	
			case TreasureTypeConstant.TREASURE_OIL_ADD:
				treasureAdd = getTreasureAdd(cityResource.getOilTreasureAdd(),num);
				output = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), treasureAdd);
				cityResourceParams.put("oilOutput", output);
				cityResourceParams.put("oilTreasureAdd", treasureAdd);
				break;
			case TreasureTypeConstant.TREASURE_MONEY_ADD:
				treasureAdd = getTreasureAdd(cityResource.getMoneyTreasureAdd(),num);
				output = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
				cityResourceParams.put("moneyOutput", output);
				cityResourceParams.put("moneyTreasureAdd", treasureAdd);
				break;
		}
		
		cityService.updateCityResource(cityResourceParams);
	}
	
	/**
	 * 获得宝物效果结束时的加成值
	 * @param oldValue 当前城市的宝物加成值
	 * @param value 宝物的加成值
	 * @return 返回取消宝物效果后的加成值
	 */
	private Integer getTreasureAdd(Integer oldValue,Integer value){
		return Math.max(oldValue - value, 0);
	}
	
	/**
	 * 取消玩家的免战状态
	 * @param playerID
	 */
	private void cancelAvoidWar(Integer playerID) {
		Player player = playerDAO.getPlayerByID(playerID);
		player.setState(PlayerStateConstant.NORMAL);
		playerDAO.updatePlayer(player);
	}
	
	/**
	 * 取消城市人口上限加成效果
	 * @param cityID
	 * @param rate 需要被减少的比例
	 */
	private void cancelAddCityPopulationMax(Integer cityID, Integer rate) {
		City city = cityService.getCityByID(cityID);
		long populationMax = (city.getPopulationMax() * 100) / (rate + 100);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityID);
		params.put("populationMax", populationMax);
		cityService.updateCity(params);
	}
	
	/**
	 * 取消指挥官统御加成效果
	 * @param cityID
	 * @param rate 需要被减少的比例
	 */
	private void cancelCityHeroReinAdd(Integer cityHeroID, Integer rate) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		int rein = 0;
		switch (cityHero.getQuality()) {
			case HeroConstant.QUALITY_NORMAL:
				rein =  200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[0];
				break;
			case HeroConstant.QUALITY_SINGULARITY:
				rein = 200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[1];
				break;
			case HeroConstant.QUALITY_EPIC:
				rein = 200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[2];
				break;
		}
		rein = cityHero.getRein() - rein * 110 / 100;
		
		cityHeroDAO.updateReinByCityHeroID(cityHeroID, rein);
	}
	
	private void cancelCityHeroExpAdd(Integer cityHeroID) {
		// 更新指挥官扩展信息
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		try {
			cityHeroExt.setExpTreasureAdd(0);
		} catch (NullPointerException e) {
			logger.error("异常：" + e + "\n指挥官编号：" + cityHeroID);
		}
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);   
	}
	
	/**
	 * 取消道具到指挥官指挥的加成
	 * @param cityHeroID
	 * @param num 扣除点数
	 */
	private void cancelCityHeroCommandAdd(Integer cityHeroID, Integer num) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		cityHero.setCommand(cityHero.getCommand() - num);
		cityHeroDAO.updateCityHero(cityHero);
		
		// 更新指挥官扩展信息
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
		cityHeroExt.setCommandTreasureAdd(0);
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	
	/**
	 * 取消道具到指挥官防御的加成
	 * @param cityHeroID
	 * @param num 扣除点数
	 */
	private void cancelCityHeroDefenseAdd(Integer cityHeroID, Integer num) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		cityHero.setDefense(cityHero.getDefense() - num);
		cityHeroDAO.updateCityHero(cityHero);
		
		// 更新指挥官扩展信息
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
		cityHeroExt.setDefenseTreasureAdd(0);
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	
	/**
	 * 取消道具到指挥官思维的加成
	 * @param cityHeroID
	 * @param num 扣除点数
	 */
	private void cancelCityHeroMindAdd(Integer cityHeroID, Integer num) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		cityHero.setMind(cityHero.getMind() - num);
		cityHero.setStaminaMax(cityHero.getStaminaMax() - num * HeroConstant.HERO_MIND_ADD_STAMINA);
		
		cityHeroDAO.updateCityHero(cityHero);
		
		// 更新指挥官扩展信息
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		cityHeroExt.setMindTreasureAdd(0);
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	
	/**
	 * 取消道具到指挥官行政的加成
	 * @param cityHeroID
	 * @param num 扣除点数
	 */
	private void cancelCityHeroExecutivepowerAdd(Integer cityHeroID, Integer num) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		cityHero.setExecutivepower(cityHero.getExecutivepower() - num);
		
		City city = cityService.getCityByID(cityHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		
		//资源生产的执政官加成值
		int officerAdd = cityHero.getExecutivepower() / 10;
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
								
		Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("woodOutput", woodOutput);
		cityResourceParams.put("foodOutput", foodOutput);
		cityResourceParams.put("steelOutput", steelOutput);
		cityResourceParams.put("oilOutput", oilOutput);
		cityResourceParams.put("moneyOutput", moneyOutput); 
		cityResourceParams.put("foodOfficerAdd", officerAdd);
		cityResourceParams.put("oilOfficerAdd", officerAdd);
		cityResourceParams.put("steelOfficerAdd", officerAdd);
		cityResourceParams.put("woodOfficerAdd", officerAdd);
		cityResourceParams.put("moneyOfficerAdd", officerAdd);
		
		cityService.updateCityResource(cityResourceParams);
		cityHeroDAO.updateCityHero(cityHero);
		
		// 更新指挥官扩展信息
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		cityHeroExt.setExecutivepowerTreasureAdd(0);
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
	
	public ITreasureQueueDAO getTreasureQueueDAO() {
		return treasureQueueDAO;
	}

	public void setTreasureQueueDAO(ITreasureQueueDAO treasureQueueDAO) {
		this.treasureQueueDAO = treasureQueueDAO;
	}

	public List<TreasureQueue> getFinishedTreasureQueueList(){
		return treasureQueueDAO.getFinishedTreasureQueueList();
	}
	
	public TreasureQueue getTreasureQueueByType(Integer targetID,Integer category,Integer type){
		return treasureQueueDAO.getTreasureQueueByType(targetID,category,type);
	}

	public List<TreasureQueue> getTreasureQueueListByCityID(Integer cityID){
		return treasureQueueDAO.getTreasureQueueListByCityID(cityID);
	}
	
	public List<TreasureQueue> getTreasureQueueListByCityHeroID(Integer cityHeroID) {
		return treasureQueueDAO.getTreasureQueueListByCityHeroID(cityHeroID);
	}

	
	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public ICityHeroDAO getCityHeroDAO() {
		return cityHeroDAO;
	}

	public void setCityHeroDAO(ICityHeroDAO cityHeroDAO) {
		this.cityHeroDAO = cityHeroDAO;
	}

	public ICityHeroExtDAO getCityHeroExtDAO() {
		return cityHeroExtDAO;
	}

	public void setCityHeroExtDAO(ICityHeroExtDAO cityHeroExtDAO) {
		this.cityHeroExtDAO = cityHeroExtDAO;
	}

}
