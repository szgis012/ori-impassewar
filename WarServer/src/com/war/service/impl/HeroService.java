package com.war.service.impl;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.RandomService;
import com.war.common.TemplateService;
import com.war.constant.BuildingConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityHeroStateConstant;
import com.war.constant.CityMilitaryStateConstant;
import com.war.constant.HeroConstant;
import com.war.constant.HeroMilitarySpiritConstant;
import com.war.constant.HeroStarConstant;
import com.war.constant.OperationLogConstant;
import com.war.constant.QueueTypeConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.ICityCandidacyHeroDAO;
import com.war.dao.ICityHeroDAO;
import com.war.dao.ICityHeroExtDAO;
import com.war.dao.ICityHeroLevelupLogDAO;
import com.war.dao.ICityMilitaryDAO;
import com.war.dao.ICityMilitarySuccorDAO;
import com.war.dao.IEquipmentDAO;
import com.war.dao.IGuildDAO;
import com.war.dao.IHeroSkillDAO;
import com.war.dao.IPlayerEquipmentDAO;
import com.war.dao.ISkillDAO;
import com.war.dao.ITreasureQueueDAO;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.CityCandidacyHero;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.CityHeroLevelupLog;
import com.war.domain.CityResource;
import com.war.domain.Equipment;
import com.war.domain.HeroSkill;
import com.war.domain.PlayerEquipment;
import com.war.domain.PlayerTreasure;
import com.war.domain.ProcessQueue;
import com.war.domain.Skill;
import com.war.domain.Treasure;
import com.war.domain.TreasureQueue;
import com.war.exception.GameException;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IDepoyQueueService;
import com.war.service.IEquipmentService;
import com.war.service.IHeroService;
import com.war.service.IMilitaryService;
import com.war.service.INameService;
import com.war.service.IOperationLogService;
import com.war.service.IPlayerService;
import com.war.service.IProcessQueueService;
import com.war.service.IReportService;
import com.war.service.ITreasureService;
import com.war.socket.game.GameSocketService;
import com.war.util.ResourceCalculateUtil;

public class HeroService implements IHeroService {

	private ICityHeroDAO cityHeroDAO;

	private ICityHeroExtDAO cityHeroExtDAO;
	
	private ICityCandidacyHeroDAO cityCandidacyHeroDAO;
	
	private ISkillDAO skillDAO;
	
	private ICityMilitaryDAO cityMilitaryDAO;
	
	private IHeroSkillDAO heroSkillDAO;
	
	private IEquipmentDAO equipmentDAO;
	
	private IPlayerEquipmentDAO playerEquipmentDAO;
	
	private ICityMilitarySuccorDAO cityMilitarySuccorDAO;
	
	private ICityHeroLevelupLogDAO cityHeroLevelupLogDAO;
	
	private ITreasureQueueDAO treasureQueueDAO;
	
	private IGuildDAO guildDAO;
	
	private INameService nameService;
	
	private IBuildingService buildingService;
	
	private ICityService cityService;
	
	private IEquipmentService equipmentService;

	private IMilitaryService militaryService;
	
	private IReportService reportService;
	
	private ITreasureService treasureService;
	
	private IProcessQueueService processQueueService;
	
	private IPlayerService playerService;
	
	private IOperationLogService operationLogService;
	
	private IDepoyQueueService depoyQueueService;
	
	private static Logger logger = Logger.getLogger(HeroService.class);
	
	private final Lock getCityCandidacyHeroListLock = new ReentrantLock();
	
	private final Lock cityHeroTrainingFinishedLock = new ReentrantLock();
	
	
	public Map<Integer, Map<Integer, Skill>> initSkillsMap() {
		Map<Integer, Map<Integer, Skill>> skillsMap = new HashMap<Integer, Map<Integer, Skill>>();
		List<Integer> skillIDList = skillDAO.getSkillIDList();
		for (int i=0;i<skillIDList.size();i++) {
			List<Skill> skillList = skillDAO.getSkillListBySkillID(skillIDList.get(i));
			Map<Integer, Skill> skillMap = new HashMap<Integer, Skill>();
			for (int j=0;j<skillList.size();j++) {
				skillMap.put(skillList.get(j).getLevel(), skillList.get(j));
			}
			skillsMap.put(skillIDList.get(i), skillMap);
		}
		return skillsMap;
	}
	
	public void cleanCityCandidacyList(){
		cityCandidacyHeroDAO.deleteCityCandidacyHeroList();
	}
	
	public void refreshCityCandidacyHeroList(Integer cityID){
		
		cityCandidacyHeroDAO.deleteCityCandidacyHeroListByCityID(cityID);
		
		this.generateCityCandidacyHeroList(cityID);
	}
	
	public void addCityHeroStamina(Integer cityHeroID, Integer addStamina) {
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if(cityHero.getStamina()+addStamina>cityHero.getStaminaMax()) {
			cityHeroDAO.updateStaminaByCityHeroID(cityHeroID, cityHero.getStaminaMax());
		}else{
			cityHeroDAO.updateStaminaByCityHeroID(cityHeroID, cityHero.getStamina()+addStamina);
		}
		
	}
	
	public void updateCityHeroStamina(Integer cityHeroID, Integer stamina) {
		cityHeroDAO.updateStaminaByCityHeroID(cityHeroID, stamina);
	}
	
	public void generateCityCandidacyHeroList(Integer cityID){
		
		CityBuilding cityBuilding = buildingService.getCityBuilding(cityID, BuildingConstant.MILITARY_COLLEGE);
		if (cityBuilding==null) {
			throw new GameException("请先建造军事学院。");
		}
		int level = cityBuilding.getLevel();
		cityBuilding = null;
		
		//计算生成英雄数量
		int amount;
		if(level==1){
			amount = 1;
		}else{
			amount = level/HeroConstant.HERO_AMOUNT_MULTIPLE==0?level/HeroConstant.HERO_AMOUNT_MULTIPLE:level/HeroConstant.HERO_AMOUNT_MULTIPLE+1;
		}
		
		//英雄姓名数组
		String[] nameArray = nameService.generateNameArray(amount);
		
		CityCandidacyHero[] cityCandidacyHeroArray = new CityCandidacyHero[amount];
		
		Random random = new Random();
		
		for(int i=0;i<amount;i++){
			
			// 英雄军魂点数
			int militarySpirit = 0;
			
			// 英雄等级 (范围 1 ~ 学院等级*HERO_LEVEL_MULTIPLE )
			int heroLevel;
			
			if (RandomService.isInTheLimits(1,400)) {
				// 高级军魂(四百分之一的几率)
				militarySpirit = random.nextInt(6) + 30;
				heroLevel = 1;
			} else {
				// 初级军魂
				militarySpirit = random.nextInt(15) + 15;
				heroLevel = 1 + random.nextInt(level*HeroConstant.HERO_LEVEL_MULTIPLE);
			}
			
			// 英雄性别
			int gender =  (( (int)(Math.random() * 2) ) + 1) == HeroConstant.MALE ? HeroConstant.MALE : HeroConstant.FEMALE;
			
			// 随机英雄点数总和(范围 1*英雄等级 - 6*英雄等级)
			int totalPoint = heroLevel*(random.nextInt(HeroConstant.HERO_POINT_MULTIPLE_MAX)+1) + HeroConstant.HERO_BASE_POINT;
			
			// 英雄属性点数数组
			int[] pointArray = new int[4];
			
			for(int j=0;j<totalPoint;j++){
				pointArray[random.nextInt(4)] += 1;
			}
			
			cityCandidacyHeroArray[i] = new CityCandidacyHero();
			cityCandidacyHeroArray[i].setCityID(cityID);
			cityCandidacyHeroArray[i].setName(nameArray[i]);
			cityCandidacyHeroArray[i].setGender(gender);
			cityCandidacyHeroArray[i].setHead(String.valueOf(random.nextInt(20)+1));
			cityCandidacyHeroArray[i].setLevel(heroLevel);
			cityCandidacyHeroArray[i].setCommand(pointArray[0]);
			cityCandidacyHeroArray[i].setDefense(pointArray[1]);
			cityCandidacyHeroArray[i].setMind(pointArray[2]);
			cityCandidacyHeroArray[i].setExecutivepower(pointArray[3]);
			cityCandidacyHeroArray[i].setMilitarySpirit(militarySpirit);
			cityCandidacyHeroArray[i].setState(1);
		}
		
		try {
			cityCandidacyHeroDAO.createCityCandidacyHeroArray(cityCandidacyHeroArray);
		} catch (SQLException e) {
			logger.error("异常：", e);
		}
		
	}
		
	public List<CityCandidacyHero> getCityCandidacyHeroList(Integer cityID){
		
		try {
			getCityCandidacyHeroListLock.lock();
			
			List<CityCandidacyHero> cityCandidacyHeroList = null;
			
			if(cityCandidacyHeroDAO.getCityCandidacyHeroNum(cityID) == 0){
				// 当前城市没有候选英雄，生成城市候选英雄列表
				generateCityCandidacyHeroList(cityID);
				cityCandidacyHeroList = cityCandidacyHeroDAO.getCityCandidacyHeroListByCityID(cityID);
			}else{
				cityCandidacyHeroList = cityCandidacyHeroDAO.getCityCandidacyHeroListByCityID(cityID);
			}
			
			return cityCandidacyHeroList;
			
		} finally {
			getCityCandidacyHeroListLock.unlock();
		}
	}

	public void handleBatchAddCityHeroStamina() {
		cityHeroDAO.batchAddCityHeroStamina();
	}
	
	public void handleCityHeroRunAway() {
		
		try {
			List<CityHero> cityHeroList = cityHeroDAO.getCityHeroList();
			
			for(int i=0;i<cityHeroList.size();i++) {
				
				if (cityHeroList.get(i).getState() != CityHeroStateConstant.FREE)
					return;
				
				//如果忠诚小于20则逃跑
				if(cityHeroList.get(i).getLoyalty()<20) {
					
					Integer playerID = cityService.getPlayerIDByCityID(cityHeroList.get(i).getCityID());
					Integer cityMilitaryID = militaryService.getCityMilitaryIDByCityHeroID(cityHeroList.get(i).getCityHeroID());
					String content = null;
					Map<String, Object> reportParams = new HashMap<String, Object>();
					reportParams.put("name", cityHeroList.get(i).getName());
					reportParams.put("time", DateService.parseDateToReportTimeString(DateService.getCurrentUtilDate()));
					try {
						content = TemplateService.format("Hero_RunAway.ftl", reportParams);
					} catch (Exception e) {
						logger.error("异常：", e);
					}
					reportService.sendOtherReport(playerID, "指挥官逃跑报告", content);
					
					cityHeroExtDAO.deleteCityHeroExtByID(cityHeroList.get(i).getCityHeroID());
					treasureQueueDAO.deleteTreasureQueueByCityHeroID(cityHeroList.get(i).getCityHeroID());
					heroSkillDAO.deleteHeroSkillListByCityHeroID(cityHeroList.get(i).getCityHeroID()); 
					cityHeroDAO.deleteCityHeroByID(cityHeroList.get(i).getCityHeroID());
					
					militaryService.dismissCityMilitaryForCityHeroRunAway(cityMilitaryID);
					
					//更新城市金钱消耗及产量
					CityResource cityResource = cityService.getCityResourceByCityID(cityHeroList.get(i).getCityID());
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("cityID", cityResource.getCityID());
					params.put("moneyConsume", cityResource.getMoneyConsume() - cityHeroList.get(i).getLevel()*10);
					cityService.updateCityResource(params);
					
				}
			}
			
		} catch (RuntimeException e) {
			logger.error("异常：", e);
		}
		
	}
	
	public void addCityHeroMaxSkillNum(Integer cityHeroID, Integer num){
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if(cityHero.getMaxSkillNum()+num>6){
			num = 6-cityHero.getMaxSkillNum();
		}
		
		cityHeroDAO.updateMaxSkillNumByCityHeroID(cityHeroID, cityHero.getMaxSkillNum()+num);
	}

	public List<CityHero> getFreeCityHeroList(Integer cityID) {
		return cityHeroDAO.getCityHeroListByCityIDAndState(cityID, 1);
	}
	
	public List<CityHero> getCityHeroList(Integer cityID){
		List<CityHero> cityHeroList = cityHeroDAO.getCityHeroListByCityID(cityID);
		
		for (CityHero cityHero:cityHeroList) {
			
			if(cityHero.getEquipmentEpaulet() != null){
				cityHero.setEquipmentEpauletObject(equipmentService.getEquipmentByID(cityHero.getEquipmentEpaulet()));
			}
			if(cityHero.getEquipmentCap() != null){
				cityHero.setEquipmentCapObject(equipmentService.getEquipmentByID(cityHero.getEquipmentCap()));
			}
			if(cityHero.getEquipmentClothes() != null){
				cityHero.setEquipmentClothesObject(equipmentService.getEquipmentByID(cityHero.getEquipmentClothes()));
			}
			if(cityHero.getEquipmentShoe() != null){
				cityHero.setEquipmentShoeObject(equipmentService.getEquipmentByID(cityHero.getEquipmentShoe()));
			}
			if(cityHero.getEquipmentWeapon() != null){
				cityHero.setEquipmentWeaponObject(equipmentService.getEquipmentByID(cityHero.getEquipmentWeapon()));
			}
			
			// cityHero.setSkillList(heroSkillDAO.getHeroSkillListByCityHeroID(cityHero.getCityHeroID()));
			
			cityHero.setCityHeroExt(cityHeroExtDAO.getCityHeroExtByID(cityHero.getCityHeroID()));
		}
		
		return cityHeroList;
	}
	
	public String getCityHeroNameByCityHeroID(Integer cityHeroID){
		return cityHeroDAO.getCityHeroNameByCityHeroID(cityHeroID);
	}
	
	public void recruitHero(Integer cityCandidacyHeroID){
		
		CityCandidacyHero cityCandidacyHero = cityCandidacyHeroDAO.getCityCandidacyHeroByID(cityCandidacyHeroID);
		
		// 判断是否达到当前最多英雄数量
		int cityHeroNum = cityHeroDAO.getCityHeroNumByCityID(cityCandidacyHero.getCityID());
		int availableCityHeroNum = buildingService.getCityBuilding(cityCandidacyHero.getCityID(), BuildingConstant.MILITARY_COLLEGE).getLevel()*HeroConstant.MILITARY_COLLEGE_HERO_NUM_MULTIPLE;
		if (cityHeroNum>=availableCityHeroNum) {
			throw new GameException("当前指挥官数量已超过军事学院可容纳最大数量。");
		}
		
		if (cityCandidacyHero.getState()==2) {
			throw new GameException("当前指挥官已被招募。");
		}
		
		// 扣除城市金钱
		cityService.minusCityResources(cityCandidacyHero.getCityID(), 0L, 0L, 0L, 0L, cityCandidacyHero.getLevel() * 100L);
		
		// 更新城市候选英雄状态为已招募
		cityCandidacyHeroDAO.updateCityCandidacyHeroState(cityCandidacyHeroID, 2);
		
		// 创建城市英雄
		CityHero cityHero = new CityHero();
		cityHero.setCityID(cityCandidacyHero.getCityID());
		cityHero.setName(cityCandidacyHero.getName());
		cityHero.setGender(cityCandidacyHero.getGender());
		cityHero.setHead(cityCandidacyHero.getHead());
		cityHero.setLevel(cityCandidacyHero.getLevel());
		cityHero.setExp(0L);
		cityHero.setStar(0);
		cityHero.setStamina(HeroConstant.HERO_BASE_STAMINA+cityCandidacyHero.getMind()*HeroConstant.HERO_MIND_ADD_STAMINA);
		cityHero.setStaminaMax(cityHero.getStamina());
		cityHero.setCommand(cityCandidacyHero.getCommand());
		cityHero.setDefense(cityCandidacyHero.getDefense());
		cityHero.setMind(cityCandidacyHero.getMind());
		cityHero.setExecutivepower(cityCandidacyHero.getExecutivepower());
		cityHero.setUnsetPoint(0);
		cityHero.setMilitarySpirit(cityCandidacyHero.getMilitarySpirit());
		cityHero.setMilitarySoul(0);
		cityHero.setAddedMilitarySpirit(0);
		
		cityHero.setLeadership(HeroConstant.DEFAULT_HERO_LEADERSHIP);
		cityHero.setLoyalty(HeroConstant.DEFAULT_HERO_LOYALTY);
		cityHero.setMaxSkillNum(HeroConstant.DEFAULT_MAX_SKILL_NUM);
		
		if (cityHero.getMilitarySpirit() >= HeroMilitarySpiritConstant.PRIMARY_MILITARY_SPIRIT_SCOPE[0] && cityHero.getMilitarySpirit() <= HeroMilitarySpiritConstant.PRIMARY_MILITARY_SPIRIT_SCOPE[1]) {
			cityHero.setRein(200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[0]);
			cityHero.setQuality(HeroConstant.QUALITY_NORMAL);
			
		} else if (cityHero.getMilitarySpirit() >= HeroMilitarySpiritConstant.ADVANCE_MILITARY_SPIRIT_SCOPE[0] && cityHero.getMilitarySpirit() <= HeroMilitarySpiritConstant.ADVANCE_MILITARY_SPIRIT_SCOPE[1]) {
			cityHero.setRein(200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[1]);
			cityHero.setQuality(HeroConstant.QUALITY_SINGULARITY);
			
		} else if (cityHero.getMilitarySpirit() >= HeroMilitarySpiritConstant.EPIC_MILITARY_SPIRIT_SCOPE[0] && cityHero.getMilitarySpirit() <= HeroMilitarySpiritConstant.EPIC_MILITARY_SPIRIT_SCOPE[1]) {
			cityHero.setRein(200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[2]);
			cityHero.setQuality(HeroConstant.QUALITY_EPIC);
		}
		cityHero.setState(CityHeroStateConstant.FREE);
		
		Integer cityHeroID = cityHeroDAO.createCityHero(cityHero);
		
		// 创建城市英雄扩展信息
		CityHeroExt cityHeroExt = new CityHeroExt();
		cityHeroExt.setCityHeroID(cityHeroID);
		cityHeroExt.setCommandEquipmentAdd(0);
		cityHeroExt.setCommandTreasureAdd(0);
		cityHeroExt.setDefenseEquipmentAdd(0);
		cityHeroExt.setDefenseTreasureAdd(0);
		cityHeroExt.setMindEquipmentAdd(0);
		cityHeroExt.setMindTreasureAdd(0);
		cityHeroExt.setExecutivepowerEquipmentAdd(0);
		cityHeroExt.setExecutivepowerTreasureAdd(0);
		cityHeroExt.setReinGuildAdd(0);
		cityHeroExt.setReinTreasureAdd(0);
		cityHeroExt.setExpGuildAdd(0);
		cityHeroExt.setExpTreasureAdd(0);
		cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
		cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
		cityHeroExt.setMilitaryLifeAdd(0);
		cityHeroExtDAO.createCityHeroExt(cityHeroExt);
		
		//更新城市金钱消耗及产量
		City city = cityService.getCityByID(cityCandidacyHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityCandidacyHero.getCityID());
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityID", city.getCityID());
		params.put("moneyConsume", cityResource.getMoneyConsume() + cityHero.getLevel()*10);
		cityService.updateCityResource(params);
		
		// 创建英雄的军队
		militaryService.createCityMilitary(cityCandidacyHero.getCityID(), cityHero.getName() + "的军队", cityHeroID); 
		
		// 记录英雄升级日志
		CityHeroLevelupLog cityHeroLevelupLog = new CityHeroLevelupLog();
		cityHeroLevelupLog.setCityHeroID(cityHeroID);
		cityHeroLevelupLog.setLevel(cityHero.getLevel());
		cityHeroLevelupLog.setAddCommand(cityHero.getCommand());
		cityHeroLevelupLog.setAddDefense(cityHero.getDefense());
		cityHeroLevelupLog.setAddMind(cityHero.getMind());
		cityHeroLevelupLog.setAddExecutivepower(cityHero.getExecutivepower());
		cityHeroLevelupLogDAO.createCityHeroLevelupLog(cityHeroLevelupLog);
		
		if (cityHero.getMilitarySpirit()>=HeroMilitarySpiritConstant.ADVANCE_MILITARY_SPIRIT_SCOPE[0]) {
			JSONObject json = new JSONObject();
			try {
				json.put("type", 9);
				json.put("message", "市长" + playerService.getPlayerNameByPlayerID(city.getPlayerID()) + "，竟然在军事学院中成功招募到稀有指挥官" + cityHero.getName() + "，真是太幸运了！");
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			GameSocketService.sendToAllClient(json);
		}
	}
	
	public void dismissHero(Integer cityHeroID){
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if(cityHero.getState() != CityHeroStateConstant.FREE){
			throw new GameException("您只能解雇空闲的指挥官。");
		}
		
		// 删除英雄升级日志信息
		cityHeroLevelupLogDAO.deleteCityHeroLevelupLogByCityHeroID(cityHeroID);

		treasureQueueDAO.deleteTreasureQueueByCityHeroID(cityHeroID);
		cityHeroExtDAO.deleteCityHeroExtByID(cityHeroID);
		heroSkillDAO.deleteHeroSkillListByCityHeroID(cityHeroID);
		cityHeroDAO.deleteCityHeroByID(cityHeroID);
		
		//更新城市金钱消耗及产量
		City city = cityService.getCityByID(cityHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityID", city.getCityID());
		params.put("moneyConsume", cityResource.getMoneyConsume() - cityHero.getLevel()*10);
		cityService.updateCityResource(params);
		
		// 解散英雄的军队
		militaryService.dismissCityMilitary(militaryService.getCityMilitaryIDByCityHeroID(cityHeroID));
		
	}
	
	public void changeHeroState(Integer cityHeroID,Integer state){
		cityHeroDAO.updateStateByCityHeroID(cityHeroID, state);
	}
	
	public void heroRename(Integer cityHeroID,String name){
		cityHeroDAO.updateNameByCityHeroID(cityHeroID, name);
		
		// 记录用户操作日志
		 CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		 operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.CHANGE_HERO_NAME);
	}
	
	public Integer getHeroUnsetPoint(Integer cityHeroID){
		return cityHeroDAO.getUnsetPointByCityHeroID(cityHeroID);
	}
	
	public void changeHeroEquipment(Integer cityHeroID,Integer playerEquipmentID){
		
		CityHero cityHero = this.getCityHero(cityHeroID);
		PlayerEquipment playerEquipment = equipmentService.getPlayerEquipment(playerEquipmentID);
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		
		if(cityHero.getLevel()<playerEquipment.getEquipment().getRequiredLevel()){
			throw new GameException("指挥官等级不足，无法更换装备");
		}
		
		// 英雄装备参数
		Map<String,Integer> heroEquipmentParams = new HashMap<String,Integer>();
		heroEquipmentParams.put("cityHeroID", cityHeroID);
		// 英雄装备增加的属性参数
		Map<String, Object> heroExtEquipmentParams = new HashMap<String, Object>();
		heroExtEquipmentParams.put("cityHeroID", cityHeroID);
		
		Integer heroEquipmentID = null;
		
		switch(playerEquipment.getEquipment().getCategory()){
			case 1:
				heroEquipmentID = cityHero.getEquipmentEpaulet();
				cityHero.setEquipmentEpaulet(playerEquipment.getEquipmentID());
				heroEquipmentParams.put("equipmentEpaulet", playerEquipment.getEquipmentID());
				break;
			case 2:
				heroEquipmentID = cityHero.getEquipmentCap();
				cityHero.setEquipmentEpaulet(playerEquipment.getEquipmentID());
				heroEquipmentParams.put("equipmentCap", playerEquipment.getEquipmentID());
				break;
			case 3:
				heroEquipmentID = cityHero.getEquipmentClothes();
				cityHero.setEquipmentEpaulet(playerEquipment.getEquipmentID());
				heroEquipmentParams.put("equipmentClothes", playerEquipment.getEquipmentID());
				break;
			case 4:
				heroEquipmentID = cityHero.getEquipmentShoe();
				cityHero.setEquipmentEpaulet(playerEquipment.getEquipmentID());
				heroEquipmentParams.put("equipmentShoe", playerEquipment.getEquipmentID());
				break;
			case 5:
				heroEquipmentID = cityHero.getEquipmentWeapon();
				cityHero.setEquipmentEpaulet(playerEquipment.getEquipmentID());
				heroEquipmentParams.put("equipmentWeapon", playerEquipment.getEquipmentID());
				break;
			default:
				break;
		}
		
		//如果英雄身上已有装备，则减去原有装备添加属性并添加至玩家装备列表中
		if(heroEquipmentID!=null){
			Equipment heroEquipment = equipmentService.getEquipmentByID(heroEquipmentID);
			cityHero.setCommand(cityHero.getCommand()-heroEquipment.getCommand());
			cityHero.setDefense(cityHero.getDefense()-heroEquipment.getDefense());
			cityHero.setMind(cityHero.getMind()-heroEquipment.getMind());
			cityHero.setExecutivepower(cityHero.getExecutivepower()-heroEquipment.getExecutivepower());
			
			//如果原装备影响思维则改变体力上限
			if(heroEquipment.getMind()>0){
				cityHero.setStaminaMax(cityHero.getStaminaMax()-heroEquipment.getMind()*HeroConstant.HERO_MIND_ADD_STAMINA);
				cityHeroDAO.updateStaminaMaxByCityHeroID(cityHeroID, cityHero.getStaminaMax());
			}
			
			//如果原装备影响行政则改变城市资源指挥官加成及计算城市资源产量
			if(heroEquipment.getExecutivepower()>0 && cityHero.getState()==CityHeroStateConstant.REIGN){
				
				City city = cityService.getCityByID(cityHero.getCityID());
				CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
				
				//资源生产的执政官加成值
				int officerAdd = cityHero.getExecutivepower() / 10;
				long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
				long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
				long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
				long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
				long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("cityID", city.getCityID());
				params.put("woodOutput", woodOutput);
				params.put("foodOutput", foodOutput);
				params.put("steelOutput", steelOutput);
				params.put("oilOutput", oilOutput);
				params.put("moneyOutput", moneyOutput); 
				params.put("foodOfficerAdd", officerAdd);
				params.put("oilOfficerAdd", officerAdd);
				params.put("steelOfficerAdd", officerAdd);
				params.put("woodOfficerAdd", officerAdd);
				params.put("moneyOfficerAdd", officerAdd);
				
				cityService.updateCityResource(params);
			}
			
			PlayerEquipment newPlayerEquipment = new PlayerEquipment();
			newPlayerEquipment.setPlayerID(playerEquipment.getPlayerID());
			newPlayerEquipment.setEquipmentID(heroEquipmentID);
			equipmentService.addPlayerEquipment(newPlayerEquipment);
			
			cityHeroExt.setCommandEquipmentAdd(cityHeroExt.getCommandEquipmentAdd() - heroEquipment.getCommand());
			cityHeroExt.setDefenseEquipmentAdd(cityHeroExt.getDefenseEquipmentAdd() - heroEquipment.getDefense());
			cityHeroExt.setMindEquipmentAdd(cityHeroExt.getMindEquipmentAdd() - heroEquipment.getMind());
			cityHeroExt.setExecutivepowerEquipmentAdd(cityHeroExt.getExecutivepowerEquipmentAdd() - heroEquipment.getExecutivepower());
		}
		
		// 添加新装备属性
		cityHero.setCommand(cityHero.getCommand()+playerEquipment.getEquipment().getCommand());
		cityHero.setDefense(cityHero.getDefense()+playerEquipment.getEquipment().getDefense());
		cityHero.setMind(cityHero.getMind()+playerEquipment.getEquipment().getMind());
		cityHero.setExecutivepower(cityHero.getExecutivepower()+playerEquipment.getEquipment().getExecutivepower());
		
		// 添加新装备对城市英雄扩展信息的影响
		cityHeroExt.setCommandEquipmentAdd(cityHeroExt.getCommandEquipmentAdd() + playerEquipment.getEquipment().getCommand());
		cityHeroExt.setDefenseEquipmentAdd(cityHeroExt.getDefenseEquipmentAdd() + playerEquipment.getEquipment().getDefense());
		cityHeroExt.setMindEquipmentAdd(cityHeroExt.getMindEquipmentAdd() + playerEquipment.getEquipment().getMind());
		cityHeroExt.setExecutivepowerEquipmentAdd(cityHeroExt.getExecutivepowerEquipmentAdd() + playerEquipment.getEquipment().getExecutivepower());
		cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
		cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
		
		// 更新指挥官属性
		cityHeroDAO.updatePointByCityHeroID(cityHeroID, cityHero.getCommand(), cityHero.getDefense(), cityHero.getMind(), cityHero.getExecutivepower(), cityHero.getUnsetPoint());
		
		// 如果新装备影响思维则改变体力上限
		if(playerEquipment.getEquipment().getMind()>0){
			cityHero.setStaminaMax(cityHero.getStaminaMax()+playerEquipment.getEquipment().getMind() * HeroConstant.HERO_MIND_ADD_STAMINA);
			cityHeroDAO.updateStaminaMaxByCityHeroID(cityHeroID, cityHero.getStaminaMax());
		}
		
		// 如果新装备影响行政则改变城市资源指挥官加成及计算城市资源产量
		if( playerEquipment.getEquipment().getExecutivepower()>0 && cityHero.getState()==CityHeroStateConstant.REIGN ){
			City city = cityService.getCityByID(cityHero.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
			// 资源生产的执政官加成值
			int officerAdd = cityHero.getExecutivepower() / 10;
			long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
			long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
			long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
			long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
									
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cityID", city.getCityID());
			params.put("woodOutput", woodOutput);
			params.put("foodOutput", foodOutput);
			params.put("steelOutput", steelOutput);
			params.put("oilOutput", oilOutput);
			params.put("moneyOutput", moneyOutput); 
			params.put("foodOfficerAdd", officerAdd);
			params.put("oilOfficerAdd", officerAdd);
			params.put("steelOfficerAdd", officerAdd);
			params.put("woodOfficerAdd", officerAdd);
			params.put("moneyOfficerAdd", officerAdd);
			
			cityService.updateCityResource(params);
		}
		
		// 更新指挥官装备
		cityHeroDAO.updateHeroEquipmentByCityHeroID(heroEquipmentParams);
		// 删除原有装备
		equipmentService.deletePlayerEquipment(playerEquipmentID);
		
	}
	
	public void offloadHeroEquipment(Integer playerID,Integer cityHeroID,Integer category){
		
		CityHero cityHero = this.getCityHero(cityHeroID);
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		
		//英雄装备参数
		Map<String,Integer> heroEquipmentParams = new HashMap<String,Integer>();
		heroEquipmentParams.put("cityHeroID", cityHeroID);
		
		Integer heroEquipmentID = null;
		
		switch(category){
			case 1:
				heroEquipmentID = cityHero.getEquipmentEpaulet();
				cityHero.setEquipmentEpaulet(null);
				heroEquipmentParams.put("equipmentEpaulet", null);
				break;
			case 2:
				heroEquipmentID = cityHero.getEquipmentCap();
				cityHero.setEquipmentEpaulet(null);
				heroEquipmentParams.put("equipmentCap", null);
				break;
			case 3:
				heroEquipmentID = cityHero.getEquipmentClothes();
				cityHero.setEquipmentEpaulet(null);
				heroEquipmentParams.put("equipmentClothes", null);
				break;
			case 4:
				heroEquipmentID = cityHero.getEquipmentShoe();
				cityHero.setEquipmentEpaulet(null);
				heroEquipmentParams.put("equipmentShoe", null);
				break;
			case 5:
				heroEquipmentID = cityHero.getEquipmentWeapon();
				cityHero.setEquipmentEpaulet(null);
				heroEquipmentParams.put("equipmentWeapon", null);
				break;
			default:
				break;
		}
		
		//减去原有装备添加属性并添加至玩家装备列表中
		Equipment heroEquipment = equipmentService.getEquipmentByID(heroEquipmentID);
		cityHero.setCommand(cityHero.getCommand()-heroEquipment.getCommand());
		cityHero.setDefense(cityHero.getDefense()-heroEquipment.getDefense());
		cityHero.setMind(cityHero.getMind()-heroEquipment.getMind());
		cityHero.setExecutivepower(cityHero.getExecutivepower()-heroEquipment.getExecutivepower());
		
		PlayerEquipment newPlayerEquipment = new PlayerEquipment();
		newPlayerEquipment.setPlayerID(playerID);
		newPlayerEquipment.setEquipmentID(heroEquipmentID);
		equipmentService.addPlayerEquipment(newPlayerEquipment);
		
		if (heroEquipment.getCommand() > 0) {
			cityHeroExt.setCommandEquipmentAdd(cityHeroExt.getCommandEquipmentAdd() - heroEquipment.getCommand());
			cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
		}
		
		if (heroEquipment.getDefense() > 0) {
			cityHeroExt.setDefenseEquipmentAdd(cityHeroExt.getDefenseEquipmentAdd() - heroEquipment.getDefense());
			cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
		}
		
		//如果原装备影响思维则改变体力上限
		if(heroEquipment.getMind()>0){
			cityHero.setStaminaMax(cityHero.getStaminaMax()-heroEquipment.getMind()*HeroConstant.HERO_MIND_ADD_STAMINA);
			cityHeroDAO.updateStaminaMaxByCityHeroID(cityHeroID, cityHero.getStaminaMax());
			
			cityHeroExt.setMindEquipmentAdd(cityHeroExt.getMindEquipmentAdd() - heroEquipment.getMind());
		}
		
		if (heroEquipment.getExecutivepower() > 0) {
			cityHeroExt.setExecutivepowerEquipmentAdd(cityHeroExt.getExecutivepowerEquipmentAdd() - heroEquipment.getExecutivepower());
		}
		
		//如果原装备影响行政则改变城市资源指挥官加成及计算城市资源产量
		if(heroEquipment.getExecutivepower()>0 && cityHero.getState() == CityHeroStateConstant.REIGN){
			City city = cityService.getCityByID(cityHero.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
			//资源生产的执政官加成值
			int officerAdd = cityHero.getExecutivepower() / 10;
			long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
			long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
			long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
			long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
									
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cityID", city.getCityID());
			params.put("woodOutput", woodOutput);
			params.put("foodOutput", foodOutput);
			params.put("steelOutput", steelOutput);
			params.put("oilOutput", oilOutput);
			params.put("moneyOutput", moneyOutput); 
			params.put("foodOfficerAdd", officerAdd);
			params.put("oilOfficerAdd", officerAdd);
			params.put("steelOfficerAdd", officerAdd);
			params.put("woodOfficerAdd", officerAdd);
			params.put("moneyOfficerAdd", officerAdd);
			
			cityService.updateCityResource(params);
		}
		
		//更新指挥官属性
		cityHeroDAO.updatePointByCityHeroID(cityHeroID, cityHero.getCommand(), cityHero.getDefense(), cityHero.getMind(), cityHero.getExecutivepower(), cityHero.getUnsetPoint());
		
		//更新指挥官装备
		cityHeroDAO.updateHeroEquipmentByCityHeroID(heroEquipmentParams);
		
		// 更新指挥官扩展信息
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	
	public void addHeroPoint(Integer cityHeroID, Integer commandAdded, Integer defenseAdded, Integer mindAdded, Integer executivepowerAdded){
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		CityHeroExt cityHeroExt = cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
		
		int totalPoint = commandAdded + defenseAdded + mindAdded + executivepowerAdded;
		
		if(cityHero.getUnsetPoint() < totalPoint){
			throw new GameException("指挥官潜力不足");
		}
		
		cityHero.setUnsetPoint(cityHero.getUnsetPoint() - totalPoint);
		
		if(mindAdded>0){
			//更新指挥官体力及体力上限
			cityHero.setStaminaMax(cityHero.getStaminaMax() + mindAdded * HeroConstant.HERO_MIND_ADD_STAMINA);
			cityHero.setStamina(cityHero.getStaminaMax());
		}
		
		if(executivepowerAdded>0 && cityHero.getState() == CityHeroStateConstant.REIGN){
			City city = cityService.getCityByID(cityHero.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
			
			this.setCityHeroExecutivepowerEffectToCityResource(city, cityResource, cityHero);
		}
		
		cityHero.setCommand(cityHero.getCommand() + commandAdded);
		cityHero.setDefense(cityHero.getDefense() + defenseAdded);
		cityHero.setMind(cityHero.getMind() + mindAdded);
		cityHero.setExecutivepower(cityHero.getExecutivepower() + executivepowerAdded);
		
		if (commandAdded > 0)
			cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
		
		if (defenseAdded > 0)
			cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
		
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
		cityHeroDAO.updateCityHero(cityHero);
		
		// 加入城市英雄对军队的加成信息
		cityHeroExtDAO.updateCityHeroMilitaryAddByID(cityHero.getCityHeroID(), cityHero.getCommand()/10, cityHero.getDefense()/10, 0);
		
		// 记录指挥官升级日志
		CityHeroLevelupLog cityHeroLevelupLog = cityHeroLevelupLogDAO.getCityHeroLevelupLogByCityHeroIDAndLevel(cityHeroID, cityHero.getLevel());
		if (cityHeroLevelupLog != null) {
			cityHeroLevelupLog.setAddCommand(cityHeroLevelupLog.getAddCommand() + commandAdded);
			cityHeroLevelupLog.setAddDefense(cityHeroLevelupLog.getAddDefense() + defenseAdded);
			cityHeroLevelupLog.setAddMind(cityHeroLevelupLog.getAddMind() + mindAdded);
			cityHeroLevelupLog.setAddExecutivepower(cityHeroLevelupLog.getAddExecutivepower() + executivepowerAdded);
			cityHeroLevelupLogDAO.updateCityHeroLevelupLog(cityHeroLevelupLog);
			
		} else {
			
			cityHeroLevelupLog = new CityHeroLevelupLog();
			cityHeroLevelupLog.setCityHeroID(cityHeroID);
			cityHeroLevelupLog.setLevel(cityHero.getLevel());
			cityHeroLevelupLog.setAddCommand(cityHeroLevelupLog.getAddCommand() + commandAdded);
			cityHeroLevelupLog.setAddDefense(cityHeroLevelupLog.getAddDefense() + defenseAdded);
			cityHeroLevelupLog.setAddMind(cityHeroLevelupLog.getAddMind() + mindAdded);
			cityHeroLevelupLog.setAddExecutivepower(cityHeroLevelupLog.getAddExecutivepower() + executivepowerAdded);
			cityHeroLevelupLogDAO.createCityHeroLevelupLog(cityHeroLevelupLog);
		}
		
		// 记录用户操作日志
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.DISTRIBUTE_HERO_UNSET_POINT);
	}
	
	public void addHeroExp(Integer cityHeroID,Long exp){
		cityHeroDAO.addExpByCityHeroID(cityHeroID, exp);
	}
	
	public Integer heroLevelUp(Integer cityHeroID){
		return this.heroLevelUp(cityHeroID, false);
	}
	
	public Integer heroPerfectLevelUp(Integer cityHeroID) {
		return this.heroLevelUp(cityHeroID, true);
	}
	
	/**
	 * 指挥官升级
	 * @param cityHeroID
	 * @param isPerfect 是否是完美升级
		 **/
	private Integer heroLevelUp(Integer cityHeroID, boolean isPerfect) {
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		CityHeroExt cityHeroExt = this.getCityHeroExtByCityHeroID(cityHero.getCityHeroID());
		
		if(cityHero.getExp()<cityHero.getExpMax()){
			throw new GameException("指挥官经验不足");
		}
		
		// 是否有未分配点数
		if (cityHero.getUnsetPoint() > 0) {
			throw new GameException("该指挥官还有剩余潜力点未分配，请先将指挥官的潜力点分配并保存后，再进行升级操作。"); 
		}
		
		// 对道具【军神药水】的检查
		if (isPerfect) {
			int playerID = cityService.getPlayerIDByCityID(cityHero.getCityID());
			PlayerTreasure playerTreasure = treasureService.getPlayerTreasureByID(playerID, TreasureConstant.MILITARY_NUMEN_MEDICAMENT);
			if (playerTreasure == null || playerTreasure.getNum() < 1) {
				throw new GameException("您的军神药水数量不足，无法进行完美升级，请到道具商城的指挥官处购买。");
			} else {
				treasureService.decreasePlayerTreasure(playerID, TreasureConstant.MILITARY_NUMEN_MEDICAMENT, 1);
			}
		}
		
		cityHero.setExp(cityHero.getExp() - cityHero.getExpMax());
		cityHero.setLevel(cityHero.getLevel() + 1);
		
		// 设置体力为最大值
		cityHero.setStamina(cityHero.getStaminaMax());
		
		int unsetPoint = 0;
		if (isPerfect) {
			// 完美升级
			unsetPoint = cityHero.getUnsetPoint() + (cityHero.getMilitarySpirit() / 10) * 2;
			cityHero.setUnsetPoint(unsetPoint);
		} else {
			// 指挥官升级获得的新点数: 当前为分配点数 + (（10至当前军魂值 范围）/ 10 ) * 2
			unsetPoint = cityHero.getUnsetPoint() + (10 + (int)(Math.random() * (cityHero.getMilitarySpirit() - 10 + 1)))/10 * 2;
			cityHero.setUnsetPoint(unsetPoint);
		}
		
		// 设置指挥官统御力
		int basicRein = this.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
		int addedRein = basicRein;
		// 统御道具加成
		TreasureQueue treasureQueue = treasureQueueDAO.getTreasureQueueByType(cityHero.getCityHeroID(), TreasureCategoryConstant.COMMANDER, TreasureTypeConstant.COMMANDER_REIN_ADD);
		// 军团统御加成
		addedRein += cityHeroExt.getReinGuildAdd();
		
		if (treasureQueue != null)
			addedRein += basicRein * 10 / 100;
		// 统御星级加成   
		if (cityHero.getStar() == 5)
			addedRein += basicRein * 10 / 100;
		
		cityHero.setRein(addedRein);
		
		cityHeroDAO.updateCityHero(cityHero);
		
		//更新城市金钱消耗及产量
		City city = cityService.getCityByID(cityHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityID", city.getCityID());
		params.put("moneyConsume", cityResource.getMoneyConsume() + 10);
		cityService.updateCityResource(params);
		
		// 记录指挥官升级日志
		CityHeroLevelupLog cityHeroLevelupLog = cityHeroLevelupLogDAO.getCityHeroLevelupLogByCityHeroIDAndLevel(cityHeroID, cityHero.getLevel());
		if (cityHeroLevelupLog != null) {
			
			cityHeroLevelupLog.setAddCommand(cityHero.getCommand() - cityHeroExt.getCommandEquipmentAdd() - cityHeroExt.getCommandTreasureAdd());
			cityHeroLevelupLog.setAddDefense(cityHero.getDefense() - cityHeroExt.getDefenseEquipmentAdd() - cityHeroExt.getDefenseTreasureAdd());
			cityHeroLevelupLog.setAddMind(cityHero.getMind() - cityHeroExt.getMindEquipmentAdd() - cityHeroExt.getMindTreasureAdd());
			cityHeroLevelupLog.setAddExecutivepower(cityHero.getExecutivepower() - cityHeroExt.getExecutivepowerEquipmentAdd() - cityHeroExt.getExecutivepowerTreasureAdd());
			
			cityHeroLevelupLogDAO.updateCityHeroLevelupLog(cityHeroLevelupLog);
			
		} else {
			cityHeroLevelupLog = new CityHeroLevelupLog(); 	
			cityHeroLevelupLog.setCityHeroID(cityHeroID);
			cityHeroLevelupLog.setLevel(cityHero.getLevel());
			cityHeroLevelupLog.setAddCommand(cityHero.getCommand() - cityHeroExt.getCommandEquipmentAdd() - cityHeroExt.getCommandTreasureAdd());
			cityHeroLevelupLog.setAddDefense(cityHero.getDefense() - cityHeroExt.getDefenseEquipmentAdd() - cityHeroExt.getDefenseTreasureAdd());
			cityHeroLevelupLog.setAddMind(cityHero.getMind() - cityHeroExt.getMindEquipmentAdd() - cityHeroExt.getMindTreasureAdd());
			cityHeroLevelupLog.setAddExecutivepower(cityHero.getExecutivepower() - cityHeroExt.getExecutivepowerEquipmentAdd() - cityHeroExt.getExecutivepowerTreasureAdd());
			
			cityHeroLevelupLogDAO.createCityHeroLevelupLog(cityHeroLevelupLog);
		}
		
		// 记录用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.UPGRADE_HERO_LEVEL);
		
		return unsetPoint;
	}
	
	public void setCityOfficer(Integer cityHeroID){
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID); 
		City city = cityService.getCityByID(cityHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		
		//获得上任执政官的编号
		Integer officerID = city.getOfficer();
		
		//如果二者一致，则返回
		if(officerID != null && cityHero.getCityHeroID().equals(officerID)){
			return;
		}
		
		//资源生产的执政官加成值
		this.setCityHeroExecutivepowerEffectToCityResource(city, cityResource, cityHero);
		
		//如果之前已经设置执政官，就改变其状态。
		if(officerID != null){
			this.changeHeroState(officerID,CityHeroStateConstant.FREE);
			// 改变指挥官的军队状态
			cityMilitaryDAO.updateCityMilitaryState(cityMilitaryDAO.getCityMilitaryIDByCityHeroID(officerID), CityMilitaryStateConstant.NORMAL);
		}
		
		//设置指挥官的状态为执政
		this.changeHeroState(cityHero.getCityHeroID(),CityHeroStateConstant.REIGN);
		
		// 设置指挥官的军队的状态为留守    
		Integer cityMilitaryID = cityMilitaryDAO.getCityMilitaryIDByCityHeroID(cityHero.getCityHeroID());
		cityMilitaryDAO.updateCityMilitaryState(cityMilitaryID, CityMilitaryStateConstant.STAY);
		
		Map<String, Object> cityParams = new HashMap<String, Object>();
		cityParams.put("cityID", city.getCityID());
		cityParams.put("officer", cityHero.getCityHeroID());
		cityParams.put("defensiveMilitary", cityMilitaryID);
		
		cityService.updateCity(cityParams);
	}
	
	/**
	 * 设置指挥官行政属性对城市资源生产的影响
	 * @param city
	 * @param cityResource
	 * @param cityHero
	 */
	private void setCityHeroExecutivepowerEffectToCityResource(City city, CityResource cityResource, CityHero cityHero) {
		
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
	}
	
	public void cancelCityOfficer(Integer cityHeroID){
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		City city = cityService.getCityByID(cityHero.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		//获得执政官的编号
		Integer officerID = city.getOfficer();
		
		if(officerID == null){
			throw new GameException("城市未指定执政官");
		}
		
		//资源生产的执政官加成值为0
		int officerAdd = 0;
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd()
				, cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
								
		Map<String, Object> cityParams = new HashMap<String, Object>();
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
		cityParams.put("cityID", city.getCityID());
		cityParams.put("officer", null);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
		
		//设置指挥官的状态为空闲
		changeHeroState(cityHero.getCityHeroID(),CityHeroStateConstant.FREE);
		// 设置指挥官的军队的状态为空闲
		cityMilitaryDAO.updateCityMilitaryState(cityMilitaryDAO.getCityMilitaryIDByCityHeroID(cityHeroID), CityMilitaryStateConstant.NORMAL);
	}
	
	public void trainingCityHeroIncreaseLeadership(Integer cityHeroID, Integer hours) {
		
		if (hours <= 0) {
			return;
		}
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		switch (cityHero.getState()) {
		
			case CityHeroStateConstant.TRAINING:
				throw new GameException("指挥官已经在训练中。");
				
			case CityHeroStateConstant.RESIDE:
				throw new GameException("指挥官驻扎在外，无法进行训练。");
				
			case CityHeroStateConstant.DEPOY:
				throw new GameException("指挥官出征中，无法进行训练。");
				
			case CityHeroStateConstant.REIGN:
				throw new GameException("指挥官执政中，无法进行训练。");
	
			default:
				break;
		}
		
		// 训练英雄金钱消耗
		if (cityHero.getState() == CityHeroStateConstant.FREE) {
			if (cityService.getCityResourceByCityID(cityHero.getCityID()).getMoneyNum() < cityHero.getRein() * 10 * hours) {
				throw new GameException("金钱不足，无法进行训练。");
				
			} else {
				
				// 英雄消耗计算
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("cityID", cityHero.getCityID());
				params.put("moneyNum", cityService.getCityResourceByCityID(cityHero.getCityID()).getMoneyNum() - cityHero.getRein() * 10 * hours);
				cityService.updateCityResource(params);
				
				// 改变指挥官状态
				cityHeroDAO.updateStateByCityHeroID(cityHeroID, CityHeroStateConstant.TRAINING);
				// 改变军队状态
				cityMilitaryDAO.updateCityMilitaryState(cityMilitaryDAO.getCityMilitaryIDByCityHeroID(cityHeroID), CityMilitaryStateConstant.TRAINING);
				
				ProcessQueue processQueue = new ProcessQueue();
				processQueue.setCityID(cityHero.getCityID());
				processQueue.setTargetID(cityHeroID);
				processQueue.setStartTime(DateService.getCurrentUtilDate());
				processQueue.setFinishTime(new Date(System.currentTimeMillis() + hours * 60 * 60 * 1000));
				processQueue.setType(QueueTypeConstant.QUEUE_TRAIN_HREO);
				
				processQueueService.addProcessQueue(processQueue);
			}
		}
		
		// 记录用户操作日志
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.TRAINING_HERO);
	}
	
	public void cancelTrainingCityHero(Integer processQueueID) {
		ProcessQueue processQueue = processQueueService.getProcessQueueByID(processQueueID);
		processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
		cityHeroDAO.updateStateByCityHeroID(processQueue.getTargetID(), CityHeroStateConstant.FREE);
		cityMilitaryDAO.updateCityMilitaryState(cityMilitaryDAO.getCityMilitaryIDByCityHeroID(processQueue.getTargetID()), CityMilitaryStateConstant.NORMAL);
	}
	
	public void cityHeroTrainingFinished(ProcessQueue processQueue) {
		
		//保证后面的操作是同步的
		cityHeroTrainingFinishedLock.lock();
		try{
			if(processQueueService.getProcessQueueByID(processQueue.getProcessQueueID()) == null){
				return;
			}
			//删除进程队列
			processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
			
			int hours = (int) (( processQueue.getFinishTime().getTime() - processQueue.getStartTime().getTime() ) / ( 1000 * 60 * 60 ));
			
			CityHero cityHero = cityHeroDAO.getCityHeroByID(processQueue.getTargetID());
			cityHero.setState(CityHeroStateConstant.FREE);
			cityHero.setLeadership(Math.min(cityHero.getLeadership() + hours * HeroConstant.TRAINING_HERO_ADDED_LEADERSHIP_POINT, 100));
			
			// 改变指挥官状态
			this.updateCityHero(cityHero);
			// 改变军队状态
			cityMilitaryDAO.updateCityMilitaryState(cityMilitaryDAO.getCityMilitaryIDByCityHeroID(cityHero.getCityHeroID()), CityMilitaryStateConstant.NORMAL);
		
		} catch (Exception e) {
			logger.error("异常：", e);
			
		}finally{
			cityHeroTrainingFinishedLock.unlock();
		}
	}

	public void updateCityHeroLeadership(Integer cityHeroID, Integer leadership) {
		cityHeroDAO.updateLeadershipByCityHeroID(cityHeroID, leadership);
	}
	
	public void addCityHeroLoyalty(Integer cityHeroID, Integer addLoyalty) {
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if(addLoyalty>100-cityHero.getLoyalty()) {
			addLoyalty = 100-cityHero.getLoyalty();
		}
		
		cityService.minusCityResources(cityHero.getCityID(), 0, 0, 0, 0, cityHero.getLevel()*100*addLoyalty);
		
		cityHeroDAO.updateLoyaltyByCityHeroID(cityHeroID, cityHero.getLoyalty()+addLoyalty);
		
		// 记录用户操作日志
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.UPGRADE_HERO_LOYALTY);
	}
	
	public void updateCityHeroLoyalty(Integer cityHeroID,Integer loyalty) {
		
		if(loyalty>100){
			loyalty = 100;
		}
		
		if(loyalty<0){
			loyalty = 0;
		}
		
		cityHeroDAO.updateLoyaltyByCityHeroID(cityHeroID, loyalty);
	}
	
	public void studySkill(Integer cityHeroID,Integer skillID){
		
		if(heroSkillDAO.getHeroSkillByCityHeroIDAndSkillID(cityHeroID, skillID)!=null){
			throw new GameException("指挥官已学习过该技能。");
		}
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		//判断当前指挥官技能数量是否已达到上限
		if(heroSkillDAO.getHeroSkillListByCityHeroID(cityHeroID).size()>=cityHero.getMaxSkillNum()){
			throw new GameException("指挥官已学习技能数量超过可学习最大数量。");
		}
		
		Skill skill = this.getSkillByIDAndLevel(skillID, 1);
		
		//判断是否达到要求学习级别
		if(skill.getStudyLevel()>cityHero.getLevel()){
			throw new GameException("指挥官等级不足。");
		}
		
		//扣减城市金钱
		cityService.minusCityResources(cityHero.getCityID(), 0L, 0L, 0L, 0L, skill.getStudyMoney());
		
		//创建英雄技能
		HeroSkill heroSkill = new HeroSkill();
		heroSkill.setCityHeroID(cityHeroID);
		heroSkill.setSkillID(skillID);
		heroSkill.setLevel(1);
		heroSkill.setProficiency(0);
		heroSkillDAO.createHeroSkill(heroSkill);
		
		// 记录用户操作记录
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.STUDY_HERO_SKILL);
		
	}
	
	public void forgetSkill(Integer cityHeroID,Integer heroSkillID){
		heroSkillDAO.deleteHeroSkillByID(heroSkillID);
	}
	
	public void levelUpSkill(Integer cityHeroID,Integer heroSkillID){
		
		HeroSkill heroSkill = heroSkillDAO.getHeroSkillByID(heroSkillID);
		heroSkill.setSkill(this.getSkillByIDAndLevel(heroSkill.getSkillID(), heroSkill.getLevel()));
		heroSkill.setNextLevelSkill(this.getSkillByIDAndLevel(heroSkill.getSkillID(), heroSkill.getLevel()+1));
		
		//判断是否达到最高等级
		if(heroSkill.getLevel().intValue()==heroSkill.getSkill().getMaxLevel().intValue()){
			throw new GameException("当前技能已达到最高等级。");
		}
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);

		//判断是否达到要求学习级别
		if(heroSkill.getNextLevelSkill().getStudyLevel()>cityHero.getLevel()){
			throw new GameException("指挥官等级不足。");
		}
		
		if(heroSkill.getProficiency()<heroSkill.getNextLevelSkill().getStudyProficiency()){
			throw new GameException("技能熟练度不足。");
		}
		
		//扣减城市金钱
		cityService.minusCityResources(cityHero.getCityID(), 0L, 0L, 0L, 0L, heroSkill.getNextLevelSkill().getStudyMoney().longValue());
		
		//设置英雄技能等级及熟练度
		heroSkill.setLevel(heroSkill.getLevel()+1);
		heroSkill.setProficiency(heroSkill.getProficiency()-heroSkill.getNextLevelSkill().getStudyProficiency());
		
		//更新英雄技能
		heroSkillDAO.updateHeroSkill(heroSkill);
	}
	
	public void updateHeroSkillProficiency(Integer heroSkillID,Integer proficiency){
		heroSkillDAO.updateHeroSkillProficiency(heroSkillID, proficiency);
	}
	
	public HeroSkill getHeroSkill(Integer heroSkillID){
		
		HeroSkill heroSkill = heroSkillDAO.getHeroSkillByID(heroSkillID);
		heroSkill.setSkill(this.getSkillByIDAndLevel(heroSkill.getSkillID(), heroSkill.getLevel()));
		heroSkill.setNextLevelSkill(this.getSkillByIDAndLevel(heroSkill.getSkillID(), heroSkill.getLevel()+1));
		
		return heroSkill;
	}
	
	public List<HeroSkill> getHeroSkillList(Integer cityHeroID) {
		List<HeroSkill> heroSkillList = heroSkillDAO.getHeroSkillListByCityHeroID(cityHeroID);
		
		Skill skill = null;
		for (HeroSkill heroSkill : heroSkillList) {
			skill = this.getSkillByIDAndLevel(heroSkill.getSkillID(), heroSkill.getLevel());
			heroSkill.setSkill(skill);
		}
		
		return heroSkillList;
	}
	
	@SuppressWarnings("unchecked")
	public Skill getSkillByIDAndLevel(Integer skillID, Integer level) {
		return ((Map<Integer, Map<Integer, Skill>>)(CacheService.getFromCache(CacheConstant.SKILLS_MAP))).get(skillID).get(level);
	}
	
	public List<Skill> getLevel1SkillList(){
		return skillDAO.getLevel1SkillList();
	}
	
	public List<Skill> getSkillList(){
		return skillDAO.getSkillList();
	}
	
	public CityHero getCityHero(Integer cityHeroID){
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if(cityHero.getEquipmentEpaulet()!=null){
			cityHero.setEquipmentEpauletObject(equipmentService.getEquipmentByID(cityHero.getEquipmentEpaulet()));
		}
		if(cityHero.getEquipmentCap()!=null){
			cityHero.setEquipmentCapObject(equipmentService.getEquipmentByID(cityHero.getEquipmentCap()));
		}
		if(cityHero.getEquipmentClothes()!=null){
			cityHero.setEquipmentClothesObject(equipmentService.getEquipmentByID(cityHero.getEquipmentClothes()));
		}
		if(cityHero.getEquipmentShoe()!=null){
			cityHero.setEquipmentShoeObject(equipmentService.getEquipmentByID(cityHero.getEquipmentShoe()));
		}
		if(cityHero.getEquipmentWeapon()!=null){
			cityHero.setEquipmentWeaponObject(equipmentService.getEquipmentByID(cityHero.getEquipmentWeapon()));
		}
		
		List<HeroSkill> heroSkillList = heroSkillDAO.getHeroSkillListByCityHeroID(cityHero.getCityHeroID());
		for(int i=0;i<heroSkillList.size();i++){
			heroSkillList.get(i).setSkill(this.getSkillByIDAndLevel(heroSkillList.get(i).getSkillID(), heroSkillList.get(i).getLevel()));
			heroSkillList.get(i).setNextLevelSkill(this.getSkillByIDAndLevel(heroSkillList.get(i).getSkillID(), heroSkillList.get(i).getLevel()+1));
		}
		cityHero.setSkillList(heroSkillList);
		
		return cityHero;
	}
	
	public void addCityOfficerExp(){
		cityHeroDAO.addCityHeroExpBatch();
	}

	public void resetHeroPoint(Integer playerID, Integer cityHeroID, Integer command, Integer defense,Integer mind, Integer executivepower) {
		
		if (command < 0 || defense < 0 || mind < 0 || executivepower < 0)
			throw new GameException("属性值不能为负值。");
		
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		CityHeroExt cityHeroExt = this.getCityHeroExtByCityHeroID(cityHeroID);
		
		int commandExtAdd = cityHeroExt.getCommandEquipmentAdd() + cityHeroExt.getCommandTreasureAdd();
		int defenseExtAdd = cityHeroExt.getDefenseEquipmentAdd() + cityHeroExt.getDefenseTreasureAdd();
		int mindExtAdd = cityHeroExt.getMindEquipmentAdd() + cityHeroExt.getMindTreasureAdd();
		int executivepowerExtAdd = cityHeroExt.getExecutivepowerEquipmentAdd() + cityHeroExt.getExecutivepowerTreasureAdd();
		
		int changedPoint = 0;
		changedPoint += Math.max(cityHero.getCommand() - commandExtAdd - command, 0);
		changedPoint += Math.max(cityHero.getDefense() - defenseExtAdd - defense, 0);
		changedPoint += Math.max(cityHero.getMind() - mindExtAdd - mind, 0);
		changedPoint += Math.max(cityHero.getExecutivepower() - executivepowerExtAdd - executivepower, 0);
		
		// 扣除道具，道具不足则由treasureService抛出异常
		int costTreasureNum = (int)Math.ceil((float)changedPoint/10);
		treasureService.decreasePlayerTreasure(playerID, TreasureConstant.REMOLDING_LIQUID, costTreasureNum);
		
		cityHero.setCommand(command + commandExtAdd);
		cityHero.setDefense(defense + defenseExtAdd);
		cityHero.setMind(mind + mindExtAdd);
		cityHero.setExecutivepower(executivepower + executivepowerExtAdd);
		
		cityHero.setStaminaMax(HeroConstant.HERO_BASE_STAMINA + cityHero.getMind() * HeroConstant.HERO_MIND_ADD_STAMINA);
		if (cityHero.getStamina() > cityHero.getStaminaMax())
			cityHero.setStamina(cityHero.getStaminaMax());
		
		if (cityHero.getState() == CityHeroStateConstant.REIGN) {
			City city = cityService.getCityByID(cityHero.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
			this.setCityHeroExecutivepowerEffectToCityResource(city, cityResource, cityHero);
		}
		
		cityHeroExtDAO.updateCityHeroMilitaryAddByID(cityHero.getCityID(), cityHero.getCommand()/10, cityHero.getDefense()/10, 0);
		cityHeroDAO.updateCityHero(cityHero);
		
		// 记录指挥官升级日志
		CityHeroLevelupLog cityHeroLevelupLog = cityHeroLevelupLogDAO.getCityHeroLevelupLogByCityHeroIDAndLevel(cityHeroID, cityHero.getLevel());
		cityHeroLevelupLog.setAddCommand(command);
		cityHeroLevelupLog.setAddDefense(defense);
		cityHeroLevelupLog.setAddMind(mind);
		cityHeroLevelupLog.setAddExecutivepower(executivepower);
		cityHeroLevelupLogDAO.updateCityHeroLevelupLog(cityHeroLevelupLog);
	}
	
	public Integer getCityHeroStar(Integer cityHeroID) {
		return cityHeroDAO.getCityHeroByID(cityHeroID).getStar();
	}

	public boolean strengthenCityHeroStar(Integer playerID, Integer cityHeroID, Integer upgradeLuckTreasureID, Integer stimulateBloodTreasureID) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
		
		int oldStar = cityHero.getStar();
		// 加强星级基准概率
		int benchmarkRate = 0;
		// 是否强化成功
		boolean isSucceed = false;
		
		if (cityHero.getStar() == HeroStarConstant.HERO_STAR_MAX_LEVEL) {
			throw new GameException("指挥官星级已达最高级。");
		}
		
		int militarySoul = cityHero.getMilitarySoul();
		long exp = cityHero.getExp();
		long moneyNum = cityResource.getMoneyNum();
		
		// 玩家材料道具状况
		int star = cityHero.getStar();
		if ((militarySoul -= HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][0]) < 0) {
			throw new GameException("您的军魄不足" + HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][0] + "，无法进行指挥官强化。");
		} else if ((exp -= HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][1]) < 0) {
			throw new GameException("您的经验不足" + HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][1] + "，无法进行指挥官强化。");
		} else if ((moneyNum -= HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][2]) < 0) {
			throw new GameException("您的金钱不足" + HeroStarConstant.UPGRADE_HERO_STAR_NEEDED_STUFF[star][2] + "，无法进行指挥官强化。");
		}
		
		benchmarkRate = HeroStarConstant.SUCCESS_RATE_OF_UPGRADE_HERO_STAR[cityHero.getStar()];
		
		// 使用强运道具 
		if (upgradeLuckTreasureID != 0) {
			PlayerTreasure playerUpgradeLuckTreasure = treasureService.getPlayerTreasureByID(playerID, upgradeLuckTreasureID);
			Treasure upgradeLuckTreasure = treasureService.getTreasureByID(upgradeLuckTreasureID);
			
			if (playerUpgradeLuckTreasure == null) {
				throw new GameException(MessageFormat.format("您的{0}道具不足。",upgradeLuckTreasure.getName())); 
			} 
			
			switch(upgradeLuckTreasureID) {
				case TreasureConstant.UPGRADE_LUCK_SIGN_SMALL:
					benchmarkRate += 10;
					break;
				case TreasureConstant.UPGRADE_LUCK_SIGN_LARGE:
					benchmarkRate += 20;
					break;
			}
			
			// 扣除强运符
			treasureService.decreasePlayerTreasure(playerID, upgradeLuckTreasureID, 1);
		} 
		
		// 使用血激符
		if (stimulateBloodTreasureID != 0) { 	
			PlayerTreasure playerStimulateBloodTreasure = treasureService.getPlayerTreasureByID(playerID, stimulateBloodTreasureID);
			Treasure stimulateBloodTreasure = treasureService.getTreasureByID(upgradeLuckTreasureID);
			
			if (playerStimulateBloodTreasure == null) {
				throw new GameException(MessageFormat.format("您的{0}道具不足。",stimulateBloodTreasure.getName())); 
			} else if (stimulateBloodTreasureID == TreasureConstant.STIMULATE_BLOOD_SIGN_LARGE && cityHero.getStar() < 3) {
				throw new GameException("强化失败，1-3星强化需要道具：血激勋章(小)。");
			} else if (stimulateBloodTreasureID == TreasureConstant.STIMULATE_BLOOD_SIGN_SMALL && cityHero.getStar() >= 3) {
				throw new GameException("强化失败，4-5星强化需要道具：血激勋章(大)。");
			}
			
			// 使用血激符强化指挥官星级
			if (RandomService.isInTheLimits(benchmarkRate, 100)) {
				cityHero.setStar(cityHero.getStar() + 1);
				isSucceed = true;
			}
			
			// 扣除血激符
			treasureService.decreasePlayerTreasure(playerID, stimulateBloodTreasureID, 1);
		} else {	// 不使用道具
			if (!RandomService.isInTheLimits(benchmarkRate, 100)) {
				switch (cityHero.getStar()) {
					case 0:
					case 1:
						cityHero.setStar(0);
						break;
					case 2:
						cityHero.setStar(1);
						break;
					case 3:
					case 4:
						cityHero.setStar(3);
						break;
				}
				
			} else {
				
				cityHero.setStar(cityHero.getStar() + 1);
				isSucceed = true;
			}
		}
		
		// 5星级对指挥官统御的加成
		if (cityHero.getStar() == 5) {
			int basicRein = this.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
			this.updateReinByCityHeroID(cityHeroID, cityHero.getRein() + (basicRein * 110 / 100));
		}
		
		// 降星去除星级对指挥官统御的加成
		if (oldStar == 5 && cityHero.getStar() < 5) {
			int basicRein = this.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
			this.updateReinByCityHeroID(cityHeroID, cityHero.getRein() - (basicRein * 110 / 100));
		}
		
		// 扣除强化军官需要的材料：军魂点数，经验，金钱
		cityHero.setMilitarySoul(militarySoul);
		cityHero.setExp(exp);
		cityHeroDAO.updateCityHero(cityHero);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityHero.getCityID());
		params.put("moneyNum", moneyNum);
		cityService.updateCityResource(params);
		
		// 更新指挥官星级
		cityHeroDAO.updateStarByCityHeroID(cityHero.getCityHeroID(), cityHero.getStar());
		
		// 记录用户操作日志
		operationLogService.createOperationLog(playerID, OperationLogConstant.STRENGTHEN_HERO_STAR);
		
		return isSucceed;
	}
	
	public void addMilitarySpirit(Integer cityHeroID) {
		CityHero cityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		
		if (cityHero.getMilitarySoul() < HeroMilitarySpiritConstant.MILITARY_SPIRIT_UPGRADE_NEEDED_MILITARY_SOUL_POINT) {
			throw new GameException("提升失败，该指挥官的军魄点数不足100，您可以通过使用战魂章来提升军魄点数。");
		}
		
		// 是否超过了能加的最大军魂点数
		if (cityHero.getQuality() == HeroConstant.QUALITY_NORMAL && cityHero.getAddedMilitarySpirit() >= HeroMilitarySpiritConstant.MAX_MILITARY_SPIRIT_ADD_OF_NORMAL_HERO) {
			throw new GameException("提升失败，普通指挥官仅能提升2点军魂，该指挥官已无法继续提升。");
			
		} else if (cityHero.getQuality() == HeroConstant.QUALITY_SINGULARITY && cityHero.getAddedMilitarySpirit() >= HeroMilitarySpiritConstant.MAX_MILITARY_SPIRIT_ADD_OF_SINGULARITY_HERO) {
			throw new GameException("提升失败，稀有级指挥官仅能提升4点军魂，该指挥官已无法继续提升。");
			
		} else if (cityHero.getQuality() == HeroConstant.QUALITY_EPIC && cityHero.getAddedMilitarySpirit() >= HeroMilitarySpiritConstant.MAX_MILITARY_SPIRIT_ADD_OF_EPIC_HERO) {
			throw new GameException("提升失败，史诗级指挥官仅能提升6点军魂，该指挥官已无法继续提升。");
		}
		
		// 增加能加的最大军魂点数
		cityHeroDAO.updateAddedMilitarySpiritByCityHeroID(cityHeroID, cityHero.getAddedMilitarySpirit() + 1);
		
		// 扣除军魄
		cityHeroDAO.updateMilitarySoulByCityHeroID(cityHeroID, cityHero.getMilitarySoul() - HeroMilitarySpiritConstant.MILITARY_SPIRIT_UPGRADE_NEEDED_MILITARY_SOUL_POINT);
		
		// 加上军魂
		cityHeroDAO.updateMilitarySpiritByCityHeroID(cityHeroID, cityHero.getMilitarySpirit() + 1);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityHero.getCityID()), OperationLogConstant.UPGRADE_HERO_MILITARY_SPIRIT);
	}
	
	public void updateCityHeroMilitaryAdd(Integer cityHeroID, Integer militaryAttackAdd, Integer militaryDefenseAdd, Integer militaryLifeAdd) {
		cityHeroExtDAO.updateCityHeroMilitaryAddByID(cityHeroID, militaryAttackAdd, militaryDefenseAdd, militaryLifeAdd);
	}
	
	public CityHeroExt getCityHeroExtByCityHeroID(Integer cityHeroID) {
		return cityHeroExtDAO.getCityHeroExtByID(cityHeroID);
	}
	
	public void updateReinByCityHeroID(Integer cityHeroID, Integer rein) {
		cityHeroDAO.updateReinByCityHeroID(cityHeroID, rein);
	}

	public boolean existsCityOfficer(Integer cityID){
		return cityHeroDAO.existsCityOfficer(cityID);
	}
	
	public Integer getEquipedCityHeroNum(Integer cityID) {
		return cityHeroDAO.getEquipedCityHeroNumByCityID(cityID);
	}
	
	public void deleteCityHeroLevelupLog(Integer cityHeroID) {
		cityHeroLevelupLogDAO.deleteCityHeroLevelupLogByCityHeroID(cityHeroID);
	}
	
	public void deleteCityHeroLevelupLog(Integer cityHeroID, Integer level) {
		cityHeroLevelupLogDAO.deleteCityHeroLevelupLogByCityHeroIDAndLevel(cityHeroID, level);
	}
	
	public void deleteCityHeroLevelupLogByID(Integer cityHeroLevelupLogID) {
		cityHeroLevelupLogDAO.deleteCityHeroLevelupLogByID(cityHeroLevelupLogID);
	}
	
	public void updateCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog) {
		cityHeroLevelupLogDAO.updateCityHeroLevelupLog(cityHeroLevelupLog);
	}
	
	public CityHeroLevelupLog getCityHeroLevelupLog(Integer cityHeroID, Integer level) {
		return cityHeroLevelupLogDAO.getCityHeroLevelupLogByCityHeroIDAndLevel(cityHeroID, level);
	}
	
	public void createCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog) {
		cityHeroLevelupLogDAO.createCityHeroLevelupLog(cityHeroLevelupLog);
	}
	
	public Long getCityHeroExpMax(Integer level) {
		return (long)45 + level*(level-1)*10;
	}
	
	public void updateCityHero(CityHero cityHero) {
		if (cityHero == null)
			return;
		
		cityHeroDAO.updateCityHero(cityHero);
	}
	
	public List<ProcessQueue> getHeroTrainingProcessQueueList(Integer cityID) {
		return processQueueService.getProcessQueueList(cityID, QueueTypeConstant.QUEUE_TRAIN_HREO);
	}
	
	public Integer getCityHeroBasicRein(Integer quality, Integer level) {
		
		if (level < 1)
			throw new GameException("数据错误。");
		
		switch (quality) {
			case HeroConstant.QUALITY_NORMAL:
				return 200 + (level - 1) * HeroConstant.REIN_MUTIPLE[0];
			case HeroConstant.QUALITY_SINGULARITY:
				return 200 + (level - 1) * HeroConstant.REIN_MUTIPLE[1];		
			case HeroConstant.QUALITY_EPIC:
				return 200 + (level - 1) * HeroConstant.REIN_MUTIPLE[2];
			default:
				throw new GameException("数据错误。");
		}
		
	}
	
	public void updateCityHeroExt(CityHeroExt cityHeroExt) {
		cityHeroExtDAO.updateCityHeroExt(cityHeroExt);
	}
	
	public List<CityHero> getBugCityHeroList() {
		
		List<CityHero> cityHeroList = cityHeroDAO.getBugCityHeroList();
		
		for (CityHero cityHero:cityHeroList) {
			
			if(cityHero.getEquipmentEpaulet() != null){
				cityHero.setEquipmentEpauletObject(equipmentService.getEquipmentByID(cityHero.getEquipmentEpaulet()));
			}
			if(cityHero.getEquipmentCap() != null){
				cityHero.setEquipmentCapObject(equipmentService.getEquipmentByID(cityHero.getEquipmentCap()));
			}
			if(cityHero.getEquipmentClothes() != null){
				cityHero.setEquipmentClothesObject(equipmentService.getEquipmentByID(cityHero.getEquipmentClothes()));
			}
			if(cityHero.getEquipmentShoe() != null){
				cityHero.setEquipmentShoeObject(equipmentService.getEquipmentByID(cityHero.getEquipmentShoe()));
			}
			if(cityHero.getEquipmentWeapon() != null){
				cityHero.setEquipmentWeaponObject(equipmentService.getEquipmentByID(cityHero.getEquipmentWeapon()));
			}
		}
		
		return cityHeroList;
		
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
	
	public ICityCandidacyHeroDAO getCityCandidacyHeroDAO() {
		return cityCandidacyHeroDAO;
	}

	public void setCityCandidacyHeroDAO(ICityCandidacyHeroDAO cityCandidacyHeroDAO) {
		this.cityCandidacyHeroDAO = cityCandidacyHeroDAO;
	}
	
	public ISkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(ISkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	public ICityMilitaryDAO getCityMilitaryDAO() {
		return cityMilitaryDAO;
	}

	public void setCityMilitaryDAO(ICityMilitaryDAO cityMilitaryDAO) {
		this.cityMilitaryDAO = cityMilitaryDAO;
	}
	
	public IHeroSkillDAO getHeroSkillDAO() {
		return heroSkillDAO;
	}

	public void setHeroSkillDAO(IHeroSkillDAO heroSkillDAO) {
		this.heroSkillDAO = heroSkillDAO;
	}
	
	public IEquipmentDAO getEquipmentDAO() {
		return equipmentDAO;
	}

	public void setEquipmentDAO(IEquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}

	public IPlayerEquipmentDAO getPlayerEquipmentDAO() {
		return playerEquipmentDAO;
	}

	public void setPlayerEquipmentDAO(IPlayerEquipmentDAO playerEquipmentDAO) {
		this.playerEquipmentDAO = playerEquipmentDAO;
	}

	public INameService getNameService() {
		return nameService;
	}

	public ICityMilitarySuccorDAO getCityMilitarySuccorDAO() {
		return cityMilitarySuccorDAO;
	}

	public void setCityMilitarySuccorDAO(ICityMilitarySuccorDAO cityMilitarySuccorDAO) {
		this.cityMilitarySuccorDAO = cityMilitarySuccorDAO;
	}

	public ICityHeroLevelupLogDAO getCityHeroLevelupLogDAO() {
		return cityHeroLevelupLogDAO;
	}

	public void setCityHeroLevelupLogDAO(ICityHeroLevelupLogDAO cityHeroLevelupLogDAO) {
		this.cityHeroLevelupLogDAO = cityHeroLevelupLogDAO;
	}

	public IGuildDAO getGuildDAO() {
		return guildDAO;
	}

	public void setGuildDAO(IGuildDAO guildDAO) {
		this.guildDAO = guildDAO;
	}

	public void setNameService(INameService nameService) {
		this.nameService = nameService;
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

	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public IMilitaryService getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(IMilitaryService militaryService) {
		this.militaryService = militaryService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}
	
	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public IDepoyQueueService getDepoyQueueService() {
		return depoyQueueService;
	}

	public void setDepoyQueueService(IDepoyQueueService depoyQueueService) {
		this.depoyQueueService = depoyQueueService;
	}

	public ITreasureQueueDAO getTreasureQueueDAO() {
		return treasureQueueDAO;
	}

	public void setTreasureQueueDAO(ITreasureQueueDAO treasureQueueDAO) {
		this.treasureQueueDAO = treasureQueueDAO;
	}
}
