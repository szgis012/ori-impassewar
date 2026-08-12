package com.war.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.SystemConfig;
import com.war.constant.ArmyTypeConstant;
import com.war.constant.BattleConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityMilitaryStateConstant;
import com.war.constant.CityWoundedArmyConstant;
import com.war.constant.DefenseConstant;
import com.war.constant.DepoyTypeConstant;
import com.war.constant.HeroStarConstant;
import com.war.constant.MapConstant;
import com.war.constant.MilitaryConstant;
import com.war.constant.MonsterConstant;
import com.war.constant.PagingConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.IBattleArmyDAO;
import com.war.dao.IBattleDAO;
import com.war.dao.IBattleDetailDAO;
import com.war.dao.IBattleLogDAO;
import com.war.dao.IBattleQueueDAO;
import com.war.dao.IBattleWaitDAO;
import com.war.dao.IBuildingDAO;
import com.war.dao.ICityMilitaryDAO;
import com.war.dao.ICityMilitarySuccorDAO;
import com.war.dao.ICityResourceDAO;
import com.war.dao.IDefenseDAO;
import com.war.domain.Army;
import com.war.domain.Battle;
import com.war.domain.BattleArmy;
import com.war.domain.BattleDetail;
import com.war.domain.BattleLog;
import com.war.domain.BattleMilitary;
import com.war.domain.BattleQueue;
import com.war.domain.BattleWait;
import com.war.domain.City;
import com.war.domain.CityDefense;
import com.war.domain.CityExt;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.CityMilitary;
import com.war.domain.CityMilitarySuccor;
import com.war.domain.CityResource;
import com.war.domain.CityWoundedArmy;
import com.war.domain.Defense;
import com.war.domain.DepoyQueue;
import com.war.domain.GuildExt;
import com.war.domain.HeroSkill;
import com.war.domain.PlayerEquipment;
import com.war.domain.PlayerTreasure;
import com.war.domain.Skill;
import com.war.exception.GameException;
import com.war.service.IArmyService;
import com.war.service.IBattleService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IColonizationService;
import com.war.service.IDefenseService;
import com.war.service.IDepoyQueueService;
import com.war.service.IEquipmentService;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.IMapService;
import com.war.service.IMilitaryService;
import com.war.service.IPlayerService;
import com.war.service.IReportService;
import com.war.service.ITechnologyService;
import com.war.service.ITreasureQueueService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityDefenseService;
import com.war.service.building.IMarketService;
import com.war.socket.battle.BattleSocketService;
import com.war.socket.game.GameSocketService;
import com.war.util.CostTimeCalculateUtil;

public class BattleService implements IBattleService {

	private IBattleDAO battleDAO;
	
	private IBattleLogDAO battleLogDAO;
	
	private IBattleArmyDAO battleArmyDAO;
	
	private IBattleDetailDAO battleDetailDAO;
	
	private IBattleQueueDAO battleQueueDAO;
	
	private IBattleWaitDAO battleWaitDAO;
	
	private IBuildingDAO buildingDAO;
	
	private ICityResourceDAO cityResourceDAO;
	
	private ICityMilitarySuccorDAO cityMilitarySuccorDAO;
	
	private ICityMilitaryDAO cityMilitaryDAO;
	
	private IDefenseDAO defenseDAO;
	
	private IBuildingService buildingService;
	
	private IMilitaryService militaryService;
	
	private IHeroService heroService;
	
	private IDepoyQueueService depoyQueueService;
	
	private ICityDefenseService cityDefenseService;
	
	private ICityService cityService;
	
	private IPlayerService playerService;
	
	private IGuildService guildService;
	
	private ITreasureService treasureService;
	
	private ITreasureQueueService treasureQueueService;
	
	private IEquipmentService equipmentService;
	
	private IReportService reportService;
	
	private IMapService mapService;
	
	private IMarketService marketService;
	
	private IColonizationService colonizationService;
	
	private IArmyService armyService;
	
	private ITechnologyService technologyService;
	
	private IDefenseService defenseService;
	
	private static Logger logger = Logger.getLogger(BattleService.class);
	
	private final Lock roundFinishedLock = new ReentrantLock();
	
	private final Lock battleFinishedLock = new ReentrantLock();
	
	private final Lock armyAttackLock = new ReentrantLock();
	
	private final Lock initBattleInfoLock = new ReentrantLock();
	
	
	public Integer addBattle(Battle battle){
		return battleDAO.createBattle(battle);
	}
	
	public void updateBattle(Integer battleID,Integer round){
		battleDAO.updateBattleRoundAndPreRoundFinishTime(battleID, round);
	}
	
	public void armyMove(JSONObject json){
		try {
			
			Battle battle = getBattleFromCache(json.getInt("battleID"));
			
			int armyNO = json.getInt("armyNO");
			
			if(json.getInt("operator") == 1){
				
				if (battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).getHaveMoved() == 1) { 
					json.put("type", -1);
					return;
				}
				
				// 设置障碍数组 
				battle.getBarrierArray()[battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).getPosY()][battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).getPosX()] = 0;
				battle.getBarrierArray()[json.getInt("posY")][json.getInt("posX")] = 1;
				
				battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).setPosX(json.getInt("posX"));
				battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).setPosY(json.getInt("posY"));
				battle.getMilitaryAttacker().getBattleArmyList().get(armyNO).setHaveMoved(1);
				
				// 设置攻击方战场详情
				JSONArray attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getAttackerOperation());
				net.sf.json.JSONObject attackerOperationJSON = new net.sf.json.JSONObject();
				attackerOperationJSON.put("action", 1);
				attackerOperationJSON.put("armyIndex", armyNO);
				attackerOperationJSON.put("posX", json.getInt("posX"));
				attackerOperationJSON.put("posY", json.getInt("posY"));
				
				attackerOperationJSONArray.add(attackerOperationJSON);
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setAttackerOperation(attackerOperationJSONArray.toString());
				
			} else if(json.getInt("operator") == 2) {
				
				if (battle.getMilitaryDefender().getBattleArmyList().get(armyNO).getHaveMoved() == 1) {
					json.put("type", -1);
					return;
				}
				
				// 设置障碍数组
				battle.getBarrierArray()[battle.getMilitaryDefender().getBattleArmyList().get(armyNO).getPosY()][battle.getMilitaryDefender().getBattleArmyList().get(armyNO).getPosX()] = 0;
				battle.getBarrierArray()[json.getInt("posY")][json.getInt("posX")] = 1;
				
				battle.getMilitaryDefender().getBattleArmyList().get(armyNO).setPosX(json.getInt("posX"));
				battle.getMilitaryDefender().getBattleArmyList().get(armyNO).setPosY(json.getInt("posY"));
				battle.getMilitaryDefender().getBattleArmyList().get(armyNO).setHaveMoved(1);
				
				// 设置防守方战场详情
				JSONArray defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getDefenderOperation());
				net.sf.json.JSONObject defenderOperationJSON = new net.sf.json.JSONObject();
				defenderOperationJSON.put("action", 1);
				defenderOperationJSON.put("armyIndex", armyNO);
				defenderOperationJSON.put("posX", json.getInt("posX"));
				defenderOperationJSON.put("posY", json.getInt("posY"));
				
				defenderOperationJSONArray.add(defenderOperationJSON);
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setDefenderOperation(defenderOperationJSONArray.toString());
			}
			
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	public void armyDefense(JSONObject json){
		try {
			Battle battle = getBattleFromCache(json.getInt("battleID"));

			if (json.getInt("operator")==1) {
				battle.getMilitaryAttacker().getBattleArmyList().get(json.getInt("armyNO")).setAttackType(2);
				
				// 设置攻击方战场详情
				JSONArray attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getAttackerOperation());
				net.sf.json.JSONObject attackerOperationJSON = new net.sf.json.JSONObject();
				attackerOperationJSON.put("action", 3);
				attackerOperationJSON.put("armyIndex", json.getInt("armyNO"));
				
				attackerOperationJSONArray.add(attackerOperationJSON);
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setAttackerOperation(attackerOperationJSONArray.toString());
				
			} else if(json.getInt("operator")==2) {
				battle.getMilitaryDefender().getBattleArmyList().get(json.getInt("armyNO")).setAttackType(2);
				
				// 设置防守方战场详情
				JSONArray defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getDefenderOperation());
				net.sf.json.JSONObject defenderOperationJSON = new net.sf.json.JSONObject();
				defenderOperationJSON.put("action", 3);
				defenderOperationJSON.put("armyIndex", json.getInt("armyNO"));
				
				defenderOperationJSONArray.add(defenderOperationJSON);
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setDefenderOperation(defenderOperationJSONArray.toString());
			}
			
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	public JSONObject armyAttack(JSONObject json){
		
		try{
			armyAttackLock.lock();
			
			int battleID = 0;
			int operator = 0;
			int armyNO = 0;
			int targetArmyNO = 0;
			try {
				battleID = json.getInt("battleID");
				operator = json.getInt("operator");
				armyNO = json.getInt("armyNO");
				targetArmyNO = json.getInt("targetArmyNO");
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			
			Battle battle = getBattleFromCache(battleID);
			
			// 原始部队
			Army armyAttacker = null;
			Army armyDefender = null;
			
			// 加了状态的部队
			BattleArmy battleArmyAttacker = null;
			BattleArmy battleArmyDefender = null;
			
			BattleMilitary battleMilitaryAttacker = null;
			BattleMilitary battleMilitaryDefender = null;
			
			// 伤害值
			int damage = 0;
			// 剩余军队数量
			int surplusArmyAmount = 0;
			// 阵亡士兵数量
			int deadArmyAmount = 0;
			// 己方士气高涨触发伤害加成
			int attackPlusWithLeadership = 0;
			// 敌方士气低迷触发伤害加成
			int beAttackedPlusWithLeadership = 0;
			
			if(operator==1){
				// 进攻方攻击
				
				battleArmyAttacker = battle.getMilitaryAttacker().getBattleArmyList().get(armyNO);
				battleArmyDefender = battle.getMilitaryDefender().getBattleArmyList().get(targetArmyNO);
				
				armyAttacker = armyService.getArmyByID(battleArmyAttacker.getArmyID());
				armyDefender = armyService.getArmyByID(battleArmyDefender.getArmyID());
				
				battleMilitaryAttacker = battle.getMilitaryAttacker();
				battleMilitaryDefender = battle.getMilitaryDefender();
				
				//攻击计算(如果进攻方数量大于防守方数量，则按防守方数量计算防御；如果进攻方数量小于防守方数量，则按进攻方数量计算防御)
				if(battleArmyAttacker.getAmount()>=battleArmyDefender.getAmount()){
					//有效攻击((进攻方攻击力*进攻方数量-防守方防御力*防守方数量)*兵种相克影响)
					damage = (battleArmyAttacker.getArmy().getAttack()*battleArmyAttacker.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyAttacker.getArmyID()][battleArmyDefender.getArmy().getType()-1])/100 - (battleArmyDefender.getArmy().getDefense()*battleArmyDefender.getAmount());
				}else{
					//有效攻击((进攻方攻击力*进攻方数量-防守方防御力*进攻方数量)*兵种相克影响)
					damage = (battleArmyAttacker.getArmy().getAttack()*battleArmyAttacker.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyAttacker.getArmyID()][battleArmyDefender.getArmy().getType()-1])/100 - (battleArmyDefender.getArmy().getDefense()*battleArmyAttacker.getAmount());
				}
				
				// 有效攻击 +　己方士气高涨触发伤害加成 + 敌方士气低落受到的伤害加成
				attackPlusWithLeadership = militaryService.getCityMilitaryAttackWithLeadership(battleMilitaryAttacker.getCityHero().getLeadership(), armyAttacker.getAttack()) * battleArmyAttacker.getAmount();
				if (battle.getType() == 1) {
					beAttackedPlusWithLeadership = militaryService.getCityMilitaryBeAttackWithLeadership(MonsterConstant.MONSTER_COMMANDER_LEADERSHIP, armyDefender.getAttack()) * battleArmyDefender.getAmount();
				} else if (battle.getType() == 2) {
					beAttackedPlusWithLeadership = militaryService.getCityMilitaryBeAttackWithLeadership(battleMilitaryDefender.getCityHero().getLeadership(), armyDefender.getAttack()) * battleArmyDefender.getAmount();
				}
				damage += attackPlusWithLeadership + beAttackedPlusWithLeadership;
				
				if(damage<0){
					damage = 0;
				}
				
				deadArmyAmount = damage/battleArmyDefender.getArmy().getLife();
				if(deadArmyAmount>battleArmyDefender.getAmount()){
					deadArmyAmount = battleArmyDefender.getAmount();
				}
				
				// 更新进攻方经验
				battle.setAttackerExp(battle.getAttackerExp()+deadArmyAmount*BattleConstant.ARMY_EXP[battleArmyDefender.getArmyID()]);
				
				// 防守方剩余部队数量
				surplusArmyAmount = battleArmyDefender.getAmount() - deadArmyAmount;
				
				// 设置防守方部队数量
				battleArmyDefender.setAmount(surplusArmyAmount);
				
				// 设置攻击状态为已攻击
				battleArmyAttacker.setAttackType(1);
				
				// 设置战斗详情
				JSONArray attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getAttackerOperation());
				net.sf.json.JSONObject attackerOperationJSON = new net.sf.json.JSONObject();
				attackerOperationJSON.put("action", 2);
				attackerOperationJSON.put("armyIndex", armyNO);
				attackerOperationJSON.put("targetArmyIndex", targetArmyNO);
				attackerOperationJSON.put("damage", damage);
				attackerOperationJSON.put("dead", deadArmyAmount);
				if (attackPlusWithLeadership > 0) {
					attackerOperationJSON.put("attackerPlusWithLeadership", attackPlusWithLeadership);
				}
				if (beAttackedPlusWithLeadership > 0) {
					attackerOperationJSON.put("beAttackedPlusWithLeadership", beAttackedPlusWithLeadership);
				}
				
				attackerOperationJSONArray.add(attackerOperationJSON);	
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setAttackerOperation(attackerOperationJSONArray.toString());
				
				//如果对方已经没有兵力，结束战斗
				if(this.haveLeftArmy(battle, 1) == false){
					try {
						json.put("damage", damage);
						json.put("surplusArmyAmount", surplusArmyAmount);
						json.put("deadArmyAmount", deadArmyAmount);
						json.put("attackerPlusWithLeadership", attackPlusWithLeadership);
						json.put("beAttackedPlusWithLeadership", beAttackedPlusWithLeadership);
						
					} catch (JSONException e) {
						logger.error("异常：", e);
					}
					BattleSocketService.sendDataToClient(battleID, json);
					
					this.battleFinished(battleID,1);
					try {
						json.put("type", 33);
					} catch (JSONException e) {
						logger.error("异常：", e);
					}
					return json;
				}
				
			} else if (operator == 2) {
			//防守方进攻
				
				battleArmyDefender = battle.getMilitaryDefender().getBattleArmyList().get(armyNO);
				battleArmyAttacker = battle.getMilitaryAttacker().getBattleArmyList().get(targetArmyNO);
				
				armyDefender = armyService.getArmyByID(battleArmyDefender.getArmyID());
				armyAttacker = armyService.getArmyByID(battleArmyAttacker.getArmyID());
				
				battleMilitaryDefender = battle.getMilitaryDefender();
				battleMilitaryAttacker = battle.getMilitaryAttacker();
				
				//攻击计算(如果进攻方数量大于防守方数量，则按防守方数量计算防御；如果进攻方数量小于防守方数量，则按进攻方数量计算防御)
				if(battleArmyDefender.getAmount()>=battleArmyAttacker.getAmount()){
					//有效攻击((进攻方攻击力*进攻方数量-防守方防御力*防守方数量)*兵种相克影响)
					damage = (battleArmyDefender.getArmy().getAttack()*battleArmyDefender.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyDefender.getArmyID()][battleArmyAttacker.getArmy().getType()-1])/100 - (battleArmyAttacker.getArmy().getDefense()*battleArmyAttacker.getAmount());
				} else {
					//有效攻击((进攻方攻击力*进攻方数量-防守方防御力*进攻方数量)*兵种相克影响)
					damage = (battleArmyDefender.getArmy().getAttack()*battleArmyDefender.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyDefender.getArmyID()][battleArmyAttacker.getArmy().getType()-1])/100 - (battleArmyAttacker.getArmy().getDefense()*battleArmyDefender.getAmount());
				}
				
				// 有效攻击 +　己方士气高涨触发伤害加成 + 敌方士气低落受到的伤害加成 
				if (battle.getType() == 1) {
					beAttackedPlusWithLeadership = militaryService.getCityMilitaryBeAttackWithLeadership(MonsterConstant.MONSTER_COMMANDER_LEADERSHIP, armyDefender.getAttack()) * battleArmyDefender.getAmount();
					attackPlusWithLeadership = militaryService.getCityMilitaryAttackWithLeadership(MonsterConstant.MONSTER_COMMANDER_LEADERSHIP, armyDefender.getAttack()) * battleArmyDefender.getAmount();
				} else if (battle.getType() == 2) {
					beAttackedPlusWithLeadership = militaryService.getCityMilitaryBeAttackWithLeadership(battleMilitaryDefender.getCityHero().getLeadership(), armyDefender.getAttack()) * battleArmyDefender.getAmount();
					attackPlusWithLeadership = militaryService.getCityMilitaryAttackWithLeadership(battleMilitaryDefender.getCityHero().getLeadership(), armyDefender.getAttack()) * battleArmyDefender.getAmount();
				}
				damage += attackPlusWithLeadership + beAttackedPlusWithLeadership;
				
				if(damage<0){
					damage = 0;
				}
				
				deadArmyAmount = damage/battleArmyAttacker.getArmy().getLife();
				if(deadArmyAmount > battleArmyAttacker.getAmount()){
					deadArmyAmount = battleArmyAttacker.getAmount();
				}
				
				//更新防守方经验
				battle.setDefenderExp(battle.getDefenderExp() + deadArmyAmount * BattleConstant.ARMY_EXP[battleArmyDefender.getArmyID()]);
				
				//防守方剩余部队数量
				surplusArmyAmount = battleArmyAttacker.getAmount() - deadArmyAmount;
				
				//进攻方剩余部队数量
				if(surplusArmyAmount<0){
					surplusArmyAmount = 0;
				}
				
				//设置进攻方部队数量
				battleArmyAttacker.setAmount(surplusArmyAmount);
				
				//设置攻击状态为已攻击
				battleArmyDefender.setAttackType(1);
				
				// 设置战斗详情
				JSONArray defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getDefenderOperation());
				net.sf.json.JSONObject defenderOperationJSON = new net.sf.json.JSONObject();
				defenderOperationJSON.put("action", 2);
				defenderOperationJSON.put("armyIndex", armyNO);
				defenderOperationJSON.put("targetArmyIndex", targetArmyNO);
				defenderOperationJSON.put("damage", damage);
				defenderOperationJSON.put("dead", deadArmyAmount);
				if (attackPlusWithLeadership > 0) {
					defenderOperationJSON.put("attackerPlusWithLeadership", attackPlusWithLeadership);
				}
				if (beAttackedPlusWithLeadership > 0) {
					defenderOperationJSON.put("beAttackedPlusWithLeadership", beAttackedPlusWithLeadership);
				}
				
				defenderOperationJSONArray.add(defenderOperationJSON);
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setDefenderOperation(defenderOperationJSONArray.toString());
				
				//如果对方已经没有兵力，结束战斗
				if(this.haveLeftArmy(battle, 2) == false){
					try {
						json.put("damage", damage);
						json.put("surplusArmyAmount", surplusArmyAmount);
						json.put("deadArmyAmount", deadArmyAmount);
						json.put("attackerPlusWithLeadership", attackPlusWithLeadership);
						json.put("beAttackedPlusWithLeadership", beAttackedPlusWithLeadership);
					} catch (JSONException e) {
						logger.error("异常：", e);
					}
					BattleSocketService.sendDataToClient(battleID, json);
					
					this.battleFinished(battleID,2);
					try {
						json.put("type", 33);
					} catch (JSONException e) {
						logger.error("异常：", e);
					}
					return json;
				}
			}
			
			try {
				json.put("damage", damage);
				json.put("surplusArmyAmount", surplusArmyAmount);
				json.put("deadArmyAmount", deadArmyAmount);
				json.put("attackerPlusWithLeadership", attackPlusWithLeadership);
				json.put("beAttackedPlusWithLeadership", beAttackedPlusWithLeadership);
				
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			
		} catch(Exception e) {
			logger.error("异常：", e);
			
		} finally {
			armyAttackLock.unlock();
		}
		
		return json;
	}
	
	public JSONObject armyAttackCityDefense(JSONObject json){
		
		int battleID = 0;
		int armyNO = 0;
		int cityDefenseNO = 0;
		int cityDefenseType = 0;
		try {
			battleID = json.getInt("battleID");
			armyNO = json.getInt("armyNO");
			cityDefenseNO = json.getInt("cityDefenseNO");
			cityDefenseType = json.getInt("cityDefenseType");
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		Battle battle = getBattleFromCache(battleID);
		
		Defense defense = null;
		for (CityDefense cd : battle.getCityDefenseList()) {
			if (cd.getDefenseID().intValue() == cityDefenseType) {
				defense = cd.getDefense();
			}
		}
		
		BattleArmy battleArmyAttacker = battle.getMilitaryAttacker().getBattleArmyList().get(armyNO);

		//城市防御数量
		int cityDefenseAmount = battle.getCityDefenseAmountArray()[cityDefenseNO];
		
		int damage = 0;
		int surplusCityDefenseAmount = 0;
		
		//攻击计算(如果进攻方数量大于城市防御数量，则按城市防御数量计算防御；如果进攻方数量小于城市防御数量，则按进攻方数量计算防御)
		if(battleArmyAttacker.getAmount()>=cityDefenseAmount){
			//有效攻击((进攻方攻击力*进攻方数量-城市防御防御力*城市防御数量)*兵种相克影响)
			damage = (battleArmyAttacker.getArmy().getAttack()*battleArmyAttacker.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyAttacker.getArmyID()][3])/100 - (defense.getDefense()*cityDefenseAmount);
		}else{
			//有效攻击((进攻方攻击力*进攻方数量-城市防御防御力*进攻方数量)*兵种相克影响)
			damage = (battleArmyAttacker.getArmy().getAttack()*battleArmyAttacker.getAmount())*(BattleConstant.ARMY_ATTACK_RELATIONSHIP[battleArmyAttacker.getArmyID()][3])/100 - (defense.getDefense()*battleArmyAttacker.getAmount());
		}
		
		if(damage<0){
			damage = 0;
		}
		
		//摧毁城市防御数量
		int destoryCityDefenseAmount = damage/defense.getLife();
		if(destoryCityDefenseAmount>cityDefenseAmount){
			destoryCityDefenseAmount = cityDefenseAmount;
		}
		
		//剩余城市防御数量
		surplusCityDefenseAmount = cityDefenseAmount - destoryCityDefenseAmount;
		
		//设置战斗城市防御数量
		battle.getCityDefenseAmountArray()[cityDefenseNO] = surplusCityDefenseAmount;
		if (surplusCityDefenseAmount==0) {
			// 设置障碍数组
			switch (cityDefenseNO) {
				case 0:
					// 围墙
					battle.getBarrierArray()[DefenseConstant.FIRST_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.SECOND_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.THIRD_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.FOURTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.FIFTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.SIXTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.SEVENTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					battle.getBarrierArray()[DefenseConstant.EIGHTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 0;
					break;
				case 1:
					battle.getBarrierArray()[DefenseConstant.ABOVE_BUNKER_BARRIER_POSY][DefenseConstant.BUNKER_BARRIER_POSX] = 0;
					// 上碉堡
					break;
				case 2:
					battle.getBarrierArray()[DefenseConstant.AFTER_BUNKER_BARRIER_POSY][DefenseConstant.BUNKER_BARRIER_POSX] = 0;
					// 下碉堡
					break;
				case 3:
					battle.getBarrierArray()[DefenseConstant.ABOVE_GUN_BARRIER_POSY][DefenseConstant.GUN_BARRIER_POSX] = 0;
					// 上火炮
					break;
				case 4:
					battle.getBarrierArray()[DefenseConstant.AFTER_GUN_BARRIER_POSY][DefenseConstant.GUN_BARRIER_POSX] = 0;
					// 下火炮
					break;
				case 5:
					battle.getBarrierArray()[DefenseConstant.ABOVE_ANTIGUN_BARRIER_POSY][DefenseConstant.ANTIGUN_BARRIER_POSX] = 0;
					// 上防空炮
					break;
				case 6:
					battle.getBarrierArray()[DefenseConstant.AFTER_ANTIGUN_BARRIER_POSY][DefenseConstant.ANTIGUN_BARRIER_POSX] = 0;
					// 下防空炮
					break;
			}
		}
		
		//设置攻击状态为已攻击
		battleArmyAttacker.setAttackType(1);
		
		// 设置攻击方战场详情
		try {
			JSONArray attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getAttackerOperation());
			net.sf.json.JSONObject attackerOperationJSON = new net.sf.json.JSONObject();
			attackerOperationJSON.put("action", 6);
			attackerOperationJSON.put("armyIndex", json.getInt("armyNO"));
			attackerOperationJSON.put("targetCityDefenseNo", cityDefenseNO);
			attackerOperationJSON.put("damage", damage);
			attackerOperationJSON.put("destory", destoryCityDefenseAmount);
			
			attackerOperationJSONArray.add(attackerOperationJSON);
			battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setAttackerOperation(attackerOperationJSONArray.toString());
		} catch (JSONException e1) {
			logger.error("异常", e1);
		}	
		
		//如果防守方已经没有兵力，结束战斗
		if(this.haveLeftArmy(battle, 1)==false){
			try {
				json.put("damage", damage);
				json.put("surplusCityDefenseAmount", surplusCityDefenseAmount);
				json.put("destoryCityDefenseAmount", destoryCityDefenseAmount);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			BattleSocketService.sendDataToClient(battleID, json);
			
			this.battleFinished(battleID,1);
			try {
				json.put("type", 33);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			return json;
		}
		
		try {
			json.put("damage", damage);
			json.put("surplusCityDefenseAmount", surplusCityDefenseAmount);
			json.put("destoryCityDefenseAmount", destoryCityDefenseAmount);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		return json;
	}
	
	public JSONObject cityDefenseAttackArmy(JSONObject json){
		
		int battleID = 0;
		int cityDefenseNO = 0;
		int cityDefenseType = 0;
		int targetArmyNO = 0;
		try {
			battleID = json.getInt("battleID");
			cityDefenseNO = json.getInt("cityDefenseNO");
			cityDefenseType = json.getInt("cityDefenseType");
			targetArmyNO = json.getInt("targetArmyNO");
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		Battle battle = getBattleFromCache(battleID);
		
		if(battle.getCityDefenseHaveAttackedArray()[cityDefenseNO]==1){
			
		}

		Defense defense = null;
		for (CityDefense cd : battle.getCityDefenseList()) {
			if (cd.getDefenseID().intValue() == cityDefenseType) {
				defense = cd.getDefense();
			}
		}
		
		//城市防御数量
		int cityDefenseAmount = battle.getCityDefenseAmountArray()[cityDefenseNO];
		
		BattleArmy battleArmyDefender = battle.getMilitaryAttacker().getBattleArmyList().get(targetArmyNO);
		
		
		int damage = 0;
		int surplusArmyAmount = 0;
		
		// 攻击计算(如果城市防御数量大于城市防守方数量，则按防守方数量计算防御；如果城市防御数量小于防守方数量，则按进城市防御数量计算防御)
		if(cityDefenseAmount>battleArmyDefender.getAmount()){
			// 有效攻击((城市防御攻击力*城市防御数量-防守方防御力*防守方数量)*兵种相克影响)
			damage = (defense.getAttack()*cityDefenseAmount)*(BattleConstant.CITY_DEFENSE_ATTACK_RELATIONSHIP[cityDefenseType][battleArmyDefender.getArmy().getType()-1])/100 - (battleArmyDefender.getArmy().getDefense()*battleArmyDefender.getAmount());
		}else{
			// 有效攻击((城市防御攻击力*城市防御数量-防守方防御力*城市防御数量)*兵种相克影响)
			damage = (defense.getAttack()*cityDefenseAmount)*(BattleConstant.CITY_DEFENSE_ATTACK_RELATIONSHIP[cityDefenseType][battleArmyDefender.getArmy().getType()-1])/100 - (battleArmyDefender.getArmy().getDefense()*cityDefenseAmount);
		}
		
		if(damage<0){
			damage = 0;
		}
		
		// 阵亡士兵数量
		int deadArmyAmount = damage/battleArmyDefender.getArmy().getLife();
		if(deadArmyAmount>battleArmyDefender.getAmount()){
			deadArmyAmount = battleArmyDefender.getAmount();
		}
		
		// 更新防守方经验
		battle.setDefenderExp(battle.getDefenderExp()+deadArmyAmount*BattleConstant.ARMY_EXP[targetArmyNO]);
		
		// 防守方剩余部队数量
		surplusArmyAmount = battleArmyDefender.getAmount() - deadArmyAmount;
		
		// 设置防守士兵数量
		battleArmyDefender.setAmount(surplusArmyAmount);
		
		// 设置城市防御已攻击
		battle.getCityDefenseHaveAttackedArray()[cityDefenseNO] = 1;
		
		JSONArray defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getDefenderOperation());
		net.sf.json.JSONObject defenderOperationJSON = new net.sf.json.JSONObject();
		defenderOperationJSON.put("action", 7);
		defenderOperationJSON.put("cityDefenseNo", cityDefenseNO);
		defenderOperationJSON.put("targetArmyIndex", targetArmyNO);
		defenderOperationJSON.put("damage", damage);
		defenderOperationJSON.put("dead", deadArmyAmount);
		
		defenderOperationJSONArray.add(defenderOperationJSON);
		battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setDefenderOperation(defenderOperationJSONArray.toString());
		
		// 如果进攻方已经没有兵力，结束战斗
		if(this.haveLeftArmy(battle, 2)==false){
			try {
				json.put("damage", damage);
				json.put("surplusArmyAmount", surplusArmyAmount);
				json.put("deadArmyAmount", deadArmyAmount);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			BattleSocketService.sendDataToClient(battleID, json);
			
			this.battleFinished(battleID,2);
			try {
				json.put("type", 33);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			return json;
		}
		
		try {
			json.put("damage", damage);
			json.put("surplusArmyAmount", surplusArmyAmount);
			json.put("deadArmyAmount", deadArmyAmount);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		return json;
	}
	
	public JSONObject castSkill(JSONObject json){
		
		int battleID = 0;
		int operator = 0;
		int heroSkillID = 0;
		int targetArmyForce = 0;
		int targetArmyNO = 0;
		try {
			battleID = json.getInt("battleID");
			operator = json.getInt("operator");
			heroSkillID = json.getInt("heroSkillID");
			targetArmyForce = json.getInt("targetArmyForce");
			targetArmyNO = json.getInt("targetArmyNO");
		} catch (JSONException e) {
			logger.error("异常：", e);
			if(operator==1){
				try {
					json.put("type", 37);
					json.put("message", "技能释放失败。");
				} catch (JSONException e1) {
					logger.error("异常：", e);
				}
			}else if(operator==2){
				try {
					json.put("type", 38);
					json.put("message", "技能释放失败。");
				} catch (JSONException e1) {
					logger.error("异常：", e);
				}
			}
			return json;
		}
		
		Battle battle = this.getBattleFromCache(battleID);
		
		HeroSkill heroSkill = heroService.getHeroSkill(heroSkillID);
		Skill skill = heroSkill.getSkill();
		
		CityHero cityHero = null;
		
		if(operator==1){
			cityHero = battle.getMilitaryAttacker().getCityHero();
			if(battle.getMilitaryAttacker().getHaveCastedSkill()==true){
				try {
					json.put("type", 37);
					json.put("message", "我方当前回合已经释放过技能。");
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				return json;
			}else{
				battle.getMilitaryAttacker().setHaveCastedSkill(true);
			}
		}else if(operator==2){
			cityHero = battle.getMilitaryDefender().getCityHero();
			if(battle.getMilitaryDefender().getHaveCastedSkill()==true){
				try {
					json.put("type", 38);
					json.put("message", "我方当前回合已经释放过技能。");
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				return json;
			}else{
				battle.getMilitaryDefender().setHaveCastedSkill(true); 
			}
		}
		
		if(cityHero.getStamina()<skill.getCostStamina()){
			if(operator==1){
				try {
					json.put("type", 37);
					json.put("message", "指挥官体力不足");
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
			}else if(operator==2){
				try {
					json.put("type", 38);
					json.put("message", "指挥官体力不足");
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
			}
			return json; 
		}
		
		JSONArray operationJSONArray = null;
		if (operator == 1) {
			operationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getAttackerOperation());
		} else if (operator == 2) {
			operationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).getDefenderOperation());
		}
		
		//更新指挥官体力
		cityHero.setStamina(cityHero.getStamina()-skill.getCostStamina());
		heroService.updateCityHeroStamina(cityHero.getCityHeroID(), cityHero.getStamina());
		
		//更新技能熟练度(1~3)
		int proficiency = heroSkill.getProficiency() + new Random().nextInt(3)+1;
		heroService.updateHeroSkillProficiency(heroSkillID, proficiency);
		
		BattleArmy targetBattleArmy = null;
		if(targetArmyForce==1){
			targetBattleArmy = battle.getMilitaryAttacker().getBattleArmyList().get(targetArmyNO);
		}else if(targetArmyForce==2){
			targetBattleArmy = battle.getMilitaryDefender().getBattleArmyList().get(targetArmyNO);
		}
		
		Army targetArmy = targetBattleArmy.getArmy();
		int skillType = 0;
		
		try {
			// 战场详情JSON
			net.sf.json.JSONObject operationJSON = new net.sf.json.JSONObject();
			
			json.put("heroSkillName", skill.getName());
			json.put("skillID", skill.getSkillID());
			
			if(skill.getLife()!=0){
	
				//伤害值
				int damage = -skill.getLife();
				//阵亡士兵数量
				int deadArmyAmount = damage/targetArmy.getLife();
				
				if(deadArmyAmount>targetBattleArmy.getAmount()){
					deadArmyAmount = targetBattleArmy.getAmount();
				}
				
				//剩余军队数量
				int surplusArmyAmount = targetBattleArmy.getAmount()-deadArmyAmount;
				//设置数量
				targetBattleArmy.setAmount(surplusArmyAmount);
				
				
				if(operator==1){
					battle.setAttackerExp(battle.getAttackerExp()+deadArmyAmount*BattleConstant.ARMY_EXP[targetBattleArmy.getArmyID()]);
					//判断战斗是否结束
					if(this.haveLeftArmy(battle, 1) == false){
						json.put("skillType", skillType);
						BattleSocketService.sendDataToClient(battleID, json);
						this.battleFinished(battleID,1);
						try {
							json.put("type", 33);
						} catch (JSONException e) {
							logger.error("异常：", e);
						}
						return json;
					}
					if(this.haveLeftArmy(battle, 2)==false){
						json.put("skillType", skillType);
						BattleSocketService.sendDataToClient(battleID, json);
						this.battleFinished(battleID,2);
						try {
							json.put("type", 33);
						} catch (JSONException e) {
							logger.error("异常：", e);
						}
						return json;
					}
				}else if(operator==2){
					battle.setDefenderExp(battle.getDefenderExp()+deadArmyAmount*BattleConstant.ARMY_EXP[targetBattleArmy.getArmyID()]);
					//判断战斗是否结束
					if(this.haveLeftArmy(battle, 1)==false){
						json.put("skillType", skillType);
						BattleSocketService.sendDataToClient(battleID, json);
						this.battleFinished(battleID,1);
						try {
							json.put("type", 33);
						} catch (JSONException e) {
							logger.error("异常：", e);
						}
						return json;
					}
					if(this.haveLeftArmy(battle, 2)==false){
						json.put("skillType", skillType);
						BattleSocketService.sendDataToClient(battleID, json);
						this.battleFinished(battleID,2);
						try {
							json.put("type", 33);
						} catch (JSONException e) {
							logger.error("异常：", e);
						}
						return json;
					}
				}
				
				json.put("damage", damage);
				json.put("deadArmyAmount", deadArmyAmount);
				json.put("surplusArmyAmount", surplusArmyAmount);
				operationJSON.put("damage", damage);
				operationJSON.put("dead", deadArmyAmount);
				skillType = 1;
			}else if(skill.getAttack()!=0){
				targetArmy.setAttack(targetArmy.getAttack()+skill.getAttack());
				if(targetArmy.getAttack()<0){
					targetArmy.setAttack(0);
				}
				json.put("attack", targetArmy.getAttack());
				operationJSON.put("attack", targetArmy.getAttack());
				skillType = 2;
			}else if(skill.getDefense()!=0){
				targetArmy.setDefense(targetArmy.getDefense()+skill.getDefense());
				if(targetArmy.getDefense()<0){
					targetArmy.setDefense(0);
				}
				json.put("defense", targetArmy.getDefense());
				operationJSON.put("attack", targetArmy.getAttack());
				skillType = 3;
			}else if(skill.getSpeed()!=0){
				targetArmy.setSpeed(targetArmy.getSpeed()+skill.getSpeed());
				if(targetArmy.getSpeed()<0){
					targetArmy.setSpeed(0);
				}
				json.put("speed", targetArmy.getSpeed());
				operationJSON.put("attack", targetArmy.getAttack());
				skillType = 4;
			}else if(skill.getRange()!=0){
				targetArmy.setRange(targetArmy.getRange()+skill.getRange());
				if(targetArmy.getRange()<0){
					targetArmy.setRange(0);
				}
				json.put("range", targetArmy.getRange());
				operationJSON.put("attack", targetArmy.getAttack());
				skillType = 5;
			}
			
			// 技能持续回合
			if (skill.getLastRound() > 0) {
				targetBattleArmy.getSkillMap().put(heroSkillID, skill.getLastRound());
			}
			
			// 记录持续技能的开启动作
			net.sf.json.JSONObject skillJSON = new net.sf.json.JSONObject();
			skillJSON.put("action", 101);
			skillJSON.put(heroSkillID, heroSkillID);
			skillJSON.put("armyIndex", targetArmyNO);
			skillJSON.put("force", targetArmyForce);
			
			// 设置指挥官释放技能战场详情
			operationJSON.put("action", 8);
			operationJSON.put("skillID", heroSkillID);
			operationJSON.put("skillName", skill.getName());
			operationJSON.put("skillType", skillType);
			operationJSON.put("targetArmyForce", targetArmyForce);
			operationJSON.put("targetArmyIndex", targetArmyNO);
			
			operationJSONArray.add(operationJSON);
			operationJSONArray.add(skillJSON);
			
			if (operator == 1) {
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setAttackerOperation(operationJSONArray.toString());
			} else if(operator == 2) {
				battle.getBattleDetailList().get(((battle.getRound()+1)/2) - 1).setDefenderOperation(operationJSONArray.toString());
			}
		
			json.put("skillType", skillType);
			
			// 持续性技能效果
			json.put("skillEffectAvaliable", heroSkillID + ";" + targetArmyForce + ";" + targetArmyNO);
			
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		return json;
	}
	
	public JSONObject militaryRetreat(JSONObject json){
		
		int battleID = 0;
		
		try {
			battleID = json.getInt("battleID");
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		Battle battle = this.getBattleFromCache(battleID);
		
		//逃跑方士兵数量减少一半
		for(int i=0;i<battle.getMilitaryAttacker().getBattleArmyList().size();i++){
			if(battle.getMilitaryAttacker().getBattleArmyList().get(i) != null){
				battle.getMilitaryAttacker().getBattleArmyList().get(i).setAmount(battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount()/2);
			}
		}
		
		this.battleFinished(battleID, 3);
		
		try {
			json.put("type", 34);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		return json;
	}
	
	public void roundFinished(Integer battleID, Integer operator) {

		try{
			roundFinishedLock.lock();
			
			if(battleDAO.getBattleByID(battleID) == null){
				return;
			}
			
			Battle battle = this.getBattleFromCache(battleID);
			
			// 战斗信息未被初始化
			if (battle == null) {
				try {
					battle = this.initBattleInfo(battleID);
				} catch (GameException e) {
					logger.error("异常：", e);
				}
				
				if(battle == null){
					return;
				}
			}
			
			int round = battle.getRound() + 1;
			
			// 如果大于30回合则结束战斗
			if (round > 30 * 2) {
				JSONObject json = new JSONObject();
				try {
					json.put("type", 33);
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				BattleSocketService.sendDataToClient(battleID, json);
				this.battleFinished(battleID,0);
				
				return ;
			}
			
			// 对方是否已经没有兵力
			if(this.haveLeftArmy(battle, operator) == false){
				JSONObject json = new JSONObject();
				try {
					json.put("type", 33);
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				BattleSocketService.sendDataToClient(battleID, json);
				this.battleFinished(battleID,operator);
				
				return ;
			}
			
			// 是否是连续多次请求
			switch (operator) {
				case 1:
					if (round%2 == 1)
						return;
					break;
				case 2:
					if (round%2 == 0)
						return;
					break;
			}
			
			// 是否同一回合结束请求
			if (round%2 == 1 && round/2 <= battle.getBattleDetailList().size() - 1 )
				return ;
			
			if (round%2 == 0 && round/2 != battle.getBattleDetailList().size())
				return ;
			
			// 自动战斗
			if (round % 2 == 1) {
				if(!BattleSocketService.isBattleSessionExist(battleID, 2)){
					autoBattleBatch(battle, 2);
				}
				
			} else if (round % 2 == 0) {
				if(!BattleSocketService.isBattleSessionExist(battleID, 1)){
					autoBattleBatch(battle, 1);
				}
			}
			
			BattleDetail battleDetail = null;
			if (round > 0 && round % 2 == 1) {
				battleDetail = battleDetailDAO.getBattleDetailByID(battle.getBattleDetailList().get(0).getBattleLogID(), round/2);
				if (battleDetail != null)
					return;
			}
			
		
			int i = 0;
			List<Map<String,Object>> paramsListAttacker = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> paramsListDefender = new ArrayList<Map<String,Object>>();
			
			// 逃跑的士兵数量
			int armyTurnTailNum = 0;
			// 逃跑的士兵的索引集合( ; 分隔)
			StringBuffer armyTurnTailIndexes = new StringBuffer("");
			// 战斗详情索引号
			int battleDetailIndex = (round/2 - 1) < 0 ? 0 : round/2 - 1;
			
			if(round%2 == 1){
			// 攻击方行动
				
				JSONArray attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(battleDetailIndex).getAttackerOperation());
				net.sf.json.JSONObject attackerOperationJSON = null;
				if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
					attackerOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(battleDetailIndex).getAttackerOperation());
					attackerOperationJSON = new net.sf.json.JSONObject();
					attackerOperationJSON.put("action", 11);	// 11 -> 回合结束
				}
				
				for(i=0;i<battle.getMilitaryAttacker().getBattleArmyList().size();i++){
					if(battle.getMilitaryAttacker().getBattleArmyList().get(i) != null){
						battle.getMilitaryAttacker().getBattleArmyList().get(i).setHaveMoved(0);
						battle.getMilitaryAttacker().getBattleArmyList().get(i).setAttackType(0);
						
						// 士气低落士兵逃跑
						if (battle.getMilitaryAttacker().getCityHero() != null) {
							armyTurnTailNum = militaryService.getCityMilitaryTurnTailNumWithLeadership(battle.getMilitaryAttacker().getCityHero().getLeadership(), battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount());
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setAmount(battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount() - armyTurnTailNum);
							if (armyTurnTailNum > 0) {
								
								if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
									// 设置战斗详情
									if (armyTurnTailIndexes.length() == 0) {
										armyTurnTailIndexes.append(i);
									} else {
										armyTurnTailIndexes.append(";").append(i);
									}
									attackerOperationJSON.put(i, armyTurnTailNum);
								}
								
							}
						}
						
						// 添加战斗士兵参数列表
						Map<String,Object> params = new HashMap<String,Object>();
						params.put("battleID", battle.getBattleID());
						params.put("armyForce", 1);
						params.put("armyIndex", i);
						params.put("amount", battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount());
						paramsListAttacker.add(params);
					}
				}
				
				// 设置战斗详情
				if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
					if (armyTurnTailIndexes.length() > 0) {
						attackerOperationJSON.put("armyTurnTailIndexes", armyTurnTailIndexes);
						attackerOperationJSONArray.add(attackerOperationJSON);	
						battle.getBattleDetailList().get(battleDetailIndex).setAttackerOperation(attackerOperationJSONArray.toString());
					}
				}
				
				//更新进攻方战斗士兵
				battleArmyDAO.updateBattleArmyByParamsBatch(paramsListAttacker);
				
				//更新防守方战斗士兵状态
				for(i=0;i<battle.getMilitaryDefender().getBattleArmyList().size();i++){
					if(battle.getMilitaryDefender().getBattleArmyList().get(i) != null){
						
						if(battle.getMilitaryDefender().getBattleArmyList().get(i).getAttackType()==0){
							battle.getMilitaryDefender().getBattleArmyList().get(i).setAttackType(2);
						}
						
						// 设置战斗详情
						if (armyTurnTailIndexes.length() == 0) {
							armyTurnTailIndexes.append(i);
						} else {
							armyTurnTailIndexes.append(";").append(i);
						}
						attackerOperationJSON.put(i, armyTurnTailNum);
						
						//添加战斗士兵参数列表
						Map<String,Object> params = new HashMap<String,Object>();
						params.put("battleID", battle.getBattleID());
						params.put("armyForce", 2);
						params.put("armyIndex", i);
						params.put("posX", battle.getMilitaryDefender().getBattleArmyList().get(i).getPosX());
						params.put("posY", battle.getMilitaryDefender().getBattleArmyList().get(i).getPosY());
						paramsListDefender.add(params);
					}
				}
				
				//更新防守方战斗士兵
				battleArmyDAO.updateBattleArmyByParamsBatch(paramsListDefender);
				
				battle.getMilitaryAttacker().setHaveCastedSkill(false);
				
			} else if (round % 2 == 0) {
			// 防守方行动
				
				JSONArray defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(battleDetailIndex).getDefenderOperation());
				net.sf.json.JSONObject defenderOperationJSON = null;
				if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
					defenderOperationJSONArray = JSONArray.fromObject(battle.getBattleDetailList().get(battleDetailIndex).getDefenderOperation());
					defenderOperationJSON = new net.sf.json.JSONObject();
					defenderOperationJSON.put("action", 11);	// 11 -> 回合结束
				}
				
					
				for(i=0;i<battle.getMilitaryDefender().getBattleArmyList().size();i++){
					if(battle.getMilitaryDefender().getBattleArmyList().get(i) != null){
						battle.getMilitaryDefender().getBattleArmyList().get(i).setHaveMoved(0);
						battle.getMilitaryDefender().getBattleArmyList().get(i).setAttackType(0);
						
						// 士气低落士兵逃跑
						if (battle.getMilitaryDefender().getCityHero() != null) {
							armyTurnTailNum = militaryService.getCityMilitaryTurnTailNumWithLeadership(battle.getMilitaryDefender().getCityHero().getLeadership(), battle.getMilitaryDefender().getBattleArmyList().get(i).getAmount());
							battle.getMilitaryDefender().getBattleArmyList().get(i).setAmount(battle.getMilitaryDefender().getBattleArmyList().get(i).getAmount() - armyTurnTailNum);
							if (armyTurnTailNum > 0) {
								
								// 设置战斗详情：士兵逃跑效果
								if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
									if (armyTurnTailIndexes.length() == 0) {
										armyTurnTailIndexes.append(i);
									} else {
										armyTurnTailIndexes.append(";").append(i);
									}
									defenderOperationJSON.put(i, armyTurnTailNum);
								}
									
							}
						}
						
						//添加战斗士兵参数列表
						Map<String,Object> params = new HashMap<String,Object>();
						params.put("battleID", battle.getBattleID());
						params.put("armyForce", 2);
						params.put("armyIndex", i);
						params.put("amount", battle.getMilitaryDefender().getBattleArmyList().get(i).getAmount());
						paramsListDefender.add(params);
					}
				}
				
				// 设置战斗详情
				if (battle.getBattleDetailList() != null && battle.getBattleDetailList().size() > 0) {
					if (armyTurnTailIndexes.length() > 0) {
						defenderOperationJSON.put("armyTurnTailIndexes", armyTurnTailIndexes);
						defenderOperationJSONArray.add(defenderOperationJSON);	
						battle.getBattleDetailList().get(battleDetailIndex).setDefenderOperation(defenderOperationJSONArray.toString());
					}
				}
				
				if(battle.getType()==2){
					// 初始化城市防御攻击状态
					for(i=0;i<battle.getCityDefenseHaveAttackedArray().length;i++){
						battle.getCityDefenseHaveAttackedArray()[i] = 0;
					}
				}
				
				// 更新防守方战斗士兵
				battleArmyDAO.updateBattleArmyByParamsBatch(paramsListDefender);
				
				// 更新进攻方战斗士兵状态
				for(i=0;i<battle.getMilitaryAttacker().getBattleArmyList().size();i++){
					if(battle.getMilitaryAttacker().getBattleArmyList().get(i) != null){
						if(battle.getMilitaryAttacker().getBattleArmyList().get(i).getAttackType() == 0){
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setAttackType(2);
						}
						
						// 添加战斗士兵参数列表
						Map<String,Object> params = new HashMap<String,Object>();
						params.put("battleID", battle.getBattleID());
						params.put("armyForce", 1);
						params.put("armyIndex", i);
						params.put("posX", battle.getMilitaryAttacker().getBattleArmyList().get(i).getPosX());
						params.put("posY", battle.getMilitaryAttacker().getBattleArmyList().get(i).getPosY());
						paramsListAttacker.add(params);
					}
				}
				
				// 更新进攻方战斗士兵
				battleArmyDAO.updateBattleArmyByParamsBatch(paramsListAttacker);
				
				battle.getMilitaryAttacker().setHaveCastedSkill(false);
			}
			
			// 更新战斗经验
			battleDAO.updateBattleExp(battleID, battle.getAttackerExp(), battle.getDefenderExp());
			
			// 如果是攻城战则更新战斗城市防御数量
			if(battle.getType()==2){
				StringBuffer cityDefenseAmountBuffer = new StringBuffer();
				for(i=0;i<battle.getCityDefenseAmountArray().length;i++){
					cityDefenseAmountBuffer.append(battle.getCityDefenseAmountArray()[i]);
					if (i != battle.getCityDefenseAmountArray().length - 1) {
						cityDefenseAmountBuffer.append(",");
					}
				}
				battleDAO.updateBattleCityDefenseAmount(battleID, cityDefenseAmountBuffer.toString());
			}
			
			// 向客户端发送新回合信息
			JSONObject json = new JSONObject();
			try {
				json.put("type", 31);
				json.put("round", round);
				json.put("armyTurnTailNum", armyTurnTailNum);
				if (round%2 == 1) {			// 大回合的结束
					json.put("operator", 1);
					
					if (round > 0) {
						
						net.sf.json.JSONObject battleDetailJSON = new net.sf.json.JSONObject();
						
						// 刷新技能持续效果
						this.refreshSkillPresistenceEffect(battle.getMilitaryAttacker().getBattleArmyList(),json,battleDetailJSON,battle.getBattleDetailList().get((round/2) - 1),1);
						this.refreshSkillPresistenceEffect(battle.getMilitaryDefender().getBattleArmyList(),json,battleDetailJSON,battle.getBattleDetailList().get((round/2) - 1),2);
						
						// 上一个大回合结束
						battleDetailDAO.createBattleDetail(battle.getBattleDetailList().get((round/2) - 1));
						
						// 下一个大回合开始
						battleDetail = new BattleDetail();
						battleDetail.setBattleLogID(battle.getBattleDetailList().get(0).getBattleLogID());
						battleDetail.setRound((round+1)/2);
						battleDetail.setState(0);
						battleDetail.setAttackerOperation("[]");
						battleDetail.setDefenderOperation("[]");
						
						battle.getBattleDetailList().add(battleDetail);
					}
					
				} else if (round%2 == 0) {
					json.put("operator", 2);
				}
				
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			
			battle.setRound(round);
			battle.setPreRoundFinishTime(new Date());
			this.putBattleToCache(battle);
			
			// 更新战斗回合
			battleDAO.updateBattleRoundAndPreRoundFinishTime(battle.getBattleID(), round);
			
			BattleSocketService.sendDataToClient(battle.getBattleID(), json);
			
		}catch(Exception e){
			logger.error("异常：", e);
			
		} finally {
			roundFinishedLock.unlock();
		}
	}
	
	/**
	 * 刷新指挥官技能对士兵的持续效果
	 * @param battleArmyList
	 * @param socketJSON
	 * @param battleDetailJSON
	 * @param battleDetail
	 * @param operator
	 */ 
	private void refreshSkillPresistenceEffect(List<BattleArmy> battleArmyList, JSONObject socketJSON, net.sf.json.JSONObject battleDetailJSON, BattleDetail battleDetail, Integer operator) {
		
		int skillType = 0;
		int value = 0;	// 技能效果值
		
		JSONArray operationJSONArray = null;
		if (operator == 1) {
			operationJSONArray = JSONArray.fromObject(battleDetail.getAttackerOperation());
		} else if (operator == 2) {
			operationJSONArray = JSONArray.fromObject(battleDetail.getDefenderOperation());
		}
		Map<Integer, Integer> battleArmySkillMap = null;
		HeroSkill heroSkill = null;
		Skill skill = null;
		
		List<Integer> battleArmySkillMapKeyList = new ArrayList<Integer>();
		for(int i=0;i<battleArmyList.size();i++) {
			if(battleArmyList.get(i) != null) {
				battleArmySkillMap = battleArmyList.get(i).getSkillMap();
				if (battleArmySkillMap != null && !battleArmySkillMap.isEmpty()) {
					for (Integer key : battleArmySkillMap.keySet()) {
						if (battleArmySkillMap.get(key) <= 1) {
							battleArmySkillMapKeyList.add(key);
							// battleArmySkillMap.remove(key);
							
							heroSkill = heroService.getHeroSkill(key);
							skill = heroSkill.getSkill();
							
							// 去除持续效果
							if (skill.getAttack() != 0) {
								battleArmyList.get(i).getArmy().setAttack(battleArmyList.get(i).getArmy().getAttack() - skill.getAttack());
								value = skill.getAttack();
								skillType = 2;
							} else if (skill.getDefense() != 0) {
								battleArmyList.get(i).getArmy().setDefense(battleArmyList.get(i).getArmy().getDefense() - skill.getDefense());
								value = skill.getDefense();
								skillType = 3;
							} else if (skill.getSpeed() != 0) {
								battleArmyList.get(i).getArmy().setSpeed(battleArmyList.get(i).getArmy().getSpeed() - skill.getSpeed());
								value = skill.getSpeed();
								skillType = 4;
							} else if (skill.getRange() != 0) {
								battleArmyList.get(i).getArmy().setRange(battleArmyList.get(i).getArmy().getRange() - skill.getRange());
								value = skill.getRange();
								skillType = 5;
							}
							
							// 发送给客户端
							try {
								
								StringBuffer skillEffectInvalidated = null;
								// ^技能类型; 技能效果值; 操作方; 士兵索引$
								if (!socketJSON.has("skillEffectInvalidated")) {
									skillEffectInvalidated = new StringBuffer("");
									socketJSON.put("skillEffectInvalidated", skillEffectInvalidated.append("^").append(skillType).append(";").append(value).append(";").append(operator).append(";").append(i).append("$").toString());
									
								} else {
									skillEffectInvalidated = new StringBuffer(socketJSON.getString("skillEffectInvalidated"));
									socketJSON.put("skillEffectInvalidated", skillEffectInvalidated.append("^").append(skillType).append(";").append(value).append(";").append(operator).append(";").append(i).append("$").toString());
								}
								
							} catch (JSONException e) {
								logger.error("异常：", e);
							}
							
							// 记录战斗详情
							battleDetailJSON.put("action", 102);
							battleDetailJSON.put(heroSkill.getHeroSkillID(), heroSkill.getHeroSkillID());
							battleDetailJSON.put("armyIndex", i);
							battleDetailJSON.put("force", operator);
							battleDetailJSON.put("flag", 0);
							
							operationJSONArray.add(battleDetailJSON);
							
							if (operator == 1) {
								battleDetail.setAttackerOperation(operationJSONArray.toString());
							} else if (operator == 2) {
								battleDetail.setDefenderOperation(operationJSONArray.toString());
							}
							
						} else {
							battleArmySkillMap.put(key, battleArmySkillMap.get(key) - 1);
						}
					}
				}
				
				if (battleArmySkillMapKeyList.size() > 0) {
					for (Integer key : battleArmySkillMapKeyList) {
						battleArmySkillMap.remove(key);
					}
				}
			}
		}
		
	}
	
	public boolean haveLeftArmy(Battle battle,Integer operator){
		
		List<BattleArmy> battleArmyList = null;
		
		if(operator == 1){
			battleArmyList = battle.getMilitaryDefender().getBattleArmyList();
		}else if(operator == 2){
			battleArmyList = battle.getMilitaryAttacker().getBattleArmyList();
		}
		
		for(int i=0;i<battleArmyList.size();i++){
			if(battleArmyList.get(i)!=null){
				if(battleArmyList.get(i).getAmount()>0){
					return true;
				}
			}
		}
		
		//判断城防是否被完全摧毁
		if(operator == 1 && battle.getType() == 2){
			for(int i=0;i<battle.getCityDefenseAmountArray().length;i++){
				if(battle.getCityDefenseAmountArray()[i]>0){
					return true;
				}
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void battleFinished(Integer battleID,Integer winner) {
		
		try {
			battleFinishedLock.lock();
			
			Battle battle = getBattleFromCache(battleID);
			if (battle == null) {
				logger.info("战斗已结束或战斗信息不存在。战斗编号：" + battleID);
				return;
			}
			
			// 从缓存中删除战斗信息
			Map<Integer,Battle> battleMap = ((Map<Integer,Battle>)CacheService.getFromCache(CacheConstant.BATTLE_CACHE));
			battleMap.remove(battleID);
			CacheService.putToCache(CacheConstant.BATTLE_CACHE, battleMap);
			
			// 删除战斗士兵列表
			battleArmyDAO.deleteBattleArmyByBattleID(battleID);
			// 删除战斗信息
			battleDAO.deleteBattleByID(battleID);
			
			// 自家守军和援军全被消灭则为true，否则为false 
			boolean isCompleteWin = false;
			
			// 地图是否需要回复到正常状态(true: 回复正常, 此时代表战事已经结束，即攻击者胜利或者全部攻击者败退)
			// boolean mapStateFlag = false;
			
			// 军队返回备注信息
			JSONObject attackerResultJSON = new JSONObject();
			
			// 战斗派遣军队信息
			BattleMilitary militaryAttacker = militaryService.getCityMilitaryBattleMilitary(battle.getMilitaryAttackerID());
			BattleMilitary militaryDefender = null;
			if(battle.getType()==1){
				militaryDefender = militaryService.getMapMonsterBattleMilitary(battle.getMilitaryDefenderID());
			}else if(battle.getType()==2){
				militaryDefender = militaryService.getCityMilitaryBattleMilitary(battle.getMilitaryDefenderID());
			}
			
			// 地图信息
			com.war.domain.Map map = mapService.getMapByPos(battle.getStagePosX(), battle.getStagePosY());
			
			int[] attackerDeadNum = {0,0,0,0,0,0,0,0}, defenderDeadNum = {0,0,0,0,0,0,0,0}, destoryCityDefenseNum = {0,0,0,0};
			
			// 战报参数
			//Map<String,Object> reportAttackerParams = new HashMap<String,Object>();
			//Map<String,Object> reportDefenderParams = new HashMap<String,Object>();
			
			// ---------------------------- 计算添加装备、宝物掉落 (Start) --------------------------------------------------------
			
			List<PlayerTreasure> droppedTreasureList = new ArrayList<PlayerTreasure>();
			List<PlayerEquipment> droppedEquipmentList = new ArrayList<PlayerEquipment>();
			List<PlayerTreasure> droppedTaskItemList = new ArrayList<PlayerTreasure>();
			List<Integer> droppedResourceList = new ArrayList<Integer>();
			
			if(winner==1){
				// 如果攻击野怪，则计算进攻方获得装备、宝物
				if(battle.getType()==1){
		
					java.util.Map<String,List<java.util.Map<String, Integer>>> monsterDropMap = ((java.util.Map<Integer,java.util.Map<String,List<java.util.Map<String, Integer>>>>)CacheService.getFromCache(CacheConstant.MONSTER_DROP)).get(battle.getMilitaryDefender().getLevel());
					List<java.util.Map<String, Integer>> treasurePercentList = monsterDropMap.get("treasurePercentList");
					List<java.util.Map<String, Integer>> treasureNumList = monsterDropMap.get("treasureNumList");
					List<java.util.Map<String, Integer>> equipmentList = monsterDropMap.get("equipmentList");
					List<java.util.Map<String, Integer>> resourceList = monsterDropMap.get("resourceList");
					
					int i,minPercent,maxPercent,min,max,num;
					Random random = new Random();
					
					int count = 0;
					for(i=0;i<treasurePercentList.size();i++){
						minPercent = random.nextInt(treasurePercentList.get(i).get("minPercent"))+1;
						maxPercent = random.nextInt(treasurePercentList.get(i).get("maxPercent"))+1;
						if (maxPercent <= minPercent){
							count++;
							
							PlayerTreasure playerTreasure = new PlayerTreasure();
							playerTreasure.setPlayerID(battle.getMilitaryAttacker().getPlayerID());
							playerTreasure.setTreasureID(treasurePercentList.get(i).get("ID"));
							playerTreasure.setNum(1);
							playerTreasure.setTreasure(treasureService.getTreasureByID(playerTreasure.getTreasureID()));
							droppedTreasureList.add(playerTreasure);
							
							if (attackerResultJSON.opt("PTs") == null) {
								attackerResultJSON.put("PTs", "PT" + count);
							} else {
								attackerResultJSON.put("PTs", attackerResultJSON.getString("PTs") + ";PT" + count);
							}
							attackerResultJSON.put("PT" + count, treasurePercentList.get(i).get("ID") + ";1");
						}
					}
					
					count = 0;
					for (i = 0; i < equipmentList.size(); i++) {
						minPercent = random.nextInt(equipmentList.get(i).get("minPercent"))+1;
						maxPercent = random.nextInt(equipmentList.get(i).get("maxPercent"))+1;
						if (maxPercent <= minPercent){
							count++;
							
							PlayerEquipment playerEquipment = new PlayerEquipment();
							playerEquipment.setPlayerID(battle.getMilitaryAttacker().getPlayerID());
							playerEquipment.setEquipmentID(equipmentList.get(i).get("ID"));
							playerEquipment.setEquipment(equipmentService.getEquipmentByID(playerEquipment.getEquipmentID()));
							droppedEquipmentList.add(playerEquipment);
							
							if (attackerResultJSON.opt("Es") == null) {
								attackerResultJSON.put("Es", "E" + count);
							} else {
								attackerResultJSON.put("Es", attackerResultJSON.getString("Es") + ";E" + count);
							}
							attackerResultJSON.put("E" + count, equipmentList.get(i).get("ID"));
						}
						
						
					}
					
					count = 0;
					for (i = 0; i < treasureNumList.size(); i++) {
						count++;
						
						min = treasureNumList.get(i).get("min");
						max = treasureNumList.get(i).get("max");
						num = min + random.nextInt(max-min+1);
						
						PlayerTreasure playerTreasure = new PlayerTreasure();
						playerTreasure.setPlayerID(battle.getMilitaryAttacker().getPlayerID());
						playerTreasure.setTreasureID(treasureNumList.get(i).get("ID"));
						playerTreasure.setNum(num);
						playerTreasure.setTreasure(treasureService.getTreasureByID(playerTreasure.getTreasureID()));
						droppedTaskItemList.add(playerTreasure);
						
						if (attackerResultJSON.opt("Ts") == null) {
							attackerResultJSON.put("Ts", "T" + count);
						} else {
							attackerResultJSON.put("Ts", attackerResultJSON.getString("Ts") + ";T" + count);
						}
						attackerResultJSON.put("T" + count, treasureNumList.get(i).get("ID") + ";" + num);
					}
					
					for(i=0;i<resourceList.size();i++){
						count++;
						
						min = resourceList.get(i).get("min");
						max = resourceList.get(i).get("max");
						num = min + random.nextInt(max-min+1);
						droppedResourceList.add(num);
					}
					
				}
			}
			
			//---------------------------- 计算添加装备、宝物掉落 (End) --------------------------------------------------------
			
			// 更新军队信息
			this.setBattleFinishedMilitaryArmy(battle);
			                       
			// 进攻方战斗军队信息结果
			BattleArmy battleArmyResult = null;
			List<Map<String,Object>> attackerMilitaryResultList = new ArrayList<Map<String,Object>>();
			for(int i=0;i<militaryAttacker.getBattleArmyList().size();i++){
				battleArmyResult = militaryAttacker.getBattleArmyList().get(i);
				if(battleArmyResult != null){
					Map<String,Object> armyMap = new HashMap<String,Object>();
					armyMap.put("armyID", battleArmyResult.getArmy().getArmyID());
					armyMap.put("name", battleArmyResult.getArmy().getName());
					armyMap.put("dispatchNum", battleArmyResult.getAmount());
					attackerDeadNum[i] = battleArmyResult.getAmount() - battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount();
					armyMap.put("deadNum", attackerDeadNum[i]);
					attackerMilitaryResultList.add(armyMap);
				}
			}
			
			// 防守方战斗军队信息结果
			List<Map<String,Object>> defenderMilitaryResultList = new ArrayList<Map<String,Object>>();
			for (int i=0;i<militaryDefender.getBattleArmyList().size();i++) {
				battleArmyResult = militaryDefender.getBattleArmyList().get(i);
				if(battleArmyResult!=null){
					Map<String,Object> armyMap = new HashMap<String,Object>();
					armyMap.put("armyID", battleArmyResult.getArmy().getArmyID());
					armyMap.put("name", battleArmyResult.getArmy().getName());
					armyMap.put("dispatchNum", battleArmyResult.getAmount());
					defenderDeadNum[i] = battleArmyResult.getAmount() - battle.getMilitaryDefender().getBattleArmyList().get(i).getAmount();
					armyMap.put("deadNum", defenderDeadNum[i]);
					defenderMilitaryResultList.add(armyMap);
				}
			}
			
			// 城防信息结果
			if(battle.getType()==2){
				String[] cityDefenseNum = battle.getCityDefenseAmount().split(","); 
				destoryCityDefenseNum[0] = Integer.parseInt(cityDefenseNum[0])-battle.getCityDefenseAmountArray()[0];
				destoryCityDefenseNum[1] = Integer.parseInt(cityDefenseNum[1])+Integer.parseInt(cityDefenseNum[2])-(battle.getCityDefenseAmountArray()[1]+battle.getCityDefenseAmountArray()[2]);
				destoryCityDefenseNum[2] = Integer.parseInt(cityDefenseNum[3])+Integer.parseInt(cityDefenseNum[4])-(battle.getCityDefenseAmountArray()[3]+battle.getCityDefenseAmountArray()[4]);
				destoryCityDefenseNum[3] = Integer.parseInt(cityDefenseNum[5])+Integer.parseInt(cityDefenseNum[6])-(battle.getCityDefenseAmountArray()[5]+battle.getCityDefenseAmountArray()[6]);
			}
			
			// 如果是攻城战，更新防守方城防数量
			if (battle.getType()==2) {
				cityDefenseService.minusCityDefense(battle.getMilitaryDefender().getCityID(), DefenseConstant.FENCE, destoryCityDefenseNum[0]);
				cityDefenseService.minusCityDefense(battle.getMilitaryDefender().getCityID(), DefenseConstant.BUNKER, destoryCityDefenseNum[1]);
				cityDefenseService.minusCityDefense(battle.getMilitaryDefender().getCityID(), DefenseConstant.GUN, destoryCityDefenseNum[2]);
				cityDefenseService.minusCityDefense(battle.getMilitaryDefender().getCityID(), DefenseConstant.ANTIGUN, destoryCityDefenseNum[3]);
			}
			
			// 更新攻击方军队兵数
			militaryService.updateCityMilitaryArmy(battle.getMilitaryAttacker());
			// 更新攻击方军队资源消耗值
			int[] attackerMilitaryResourceConsume = militaryService.getConsumeOfCityMilitary(
					battle.getMilitaryAttacker().getArmy1(), 
					battle.getMilitaryAttacker().getArmy2(), 
					battle.getMilitaryAttacker().getArmy3(), 
					battle.getMilitaryAttacker().getArmy4(), 
					battle.getMilitaryAttacker().getArmy5(), 
					battle.getMilitaryAttacker().getArmy6(), 
					battle.getMilitaryAttacker().getArmy7(), 
					battle.getMilitaryAttacker().getArmy8()
					);
			
			militaryService.updateCityMilitaryConsume(battle.getMilitaryAttackerID(), attackerMilitaryResourceConsume[1], attackerMilitaryResourceConsume[0], attackerMilitaryResourceConsume[2]);
			
			if (battle.getType() == 1) {
				if (winner == 1) {
					// 如果进攻方胜利，则已将地图野怪全部消灭，获得奖励并删除地图野怪信息

					// 删除地图野怪信息
					militaryService.deleteMapMonster(battle.getMilitaryDefender().getMapMonsterID());
					
					List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID());
					
					// 删除此地图对应的所有战斗队列信息
					battleQueueDAO.deleteBattleQueueByMapID(map.getMapID());
					
					if (battleQueueList!=null && battleQueueList.size() > 1) {
						int[] cityMilitaryIDs = new int[battleQueueList.size() - 1];
						for (int i = 1; i < battleQueueList.size(); i++) {
							cityMilitaryIDs[i - 1] = battleQueueList.get(i).getCityMilitaryID();
						}
						
						for (int i = 0; i < cityMilitaryIDs.length; i++) {
							cityMilitaryDAO.updateCityMilitaryState(cityMilitaryIDs[i], CityMilitaryStateConstant.MARCH);
							militaryService.nextAttackerAttack(cityMilitaryIDs[i], map.getMapID());
						}
					}
					
					// 修改Map类别Category
					map.setTargetID(null);
					map.setCategory(MapConstant.CATEGORY_BLANK_FIELD);
					// mapStateFlag = true;
					
					map.setState(MapConstant.STATE_NORMAL);
					mapService.updateMap(map);
					
				} else {
					// 如果未将地图野怪全部消灭，更新地图野怪士兵信息
					militaryService.updateMapMonsterArmy(battle.getMilitaryDefender());
					
					battleQueueDAO.deleteBattleQueueByID(battleQueueDAO.getBattleQueueIDByCityMilitaryID(militaryAttacker.getCityMilitaryID()));
					battleQueueDAO.refreshBattleQueue(map.getMapID());	// 重新排序
					
					List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID());
					if (battleQueueList != null && !battleQueueList.isEmpty()) {
						cityMilitaryDAO.updateCityMilitaryState(battleQueueList.get(0).getCityMilitaryID(), CityMilitaryStateConstant.MARCH);
						militaryService.nextAttackerAttack(battleQueueList.get(0).getCityMilitaryID(), map.getMapID());
					} else {	
						// mapStateFlag = true;
						
						map.setState(MapConstant.STATE_NORMAL);
						mapService.updateMap(map);
					}
				}
				
			} else if (battle.getType() == 2) {
				militaryService.updateCityMilitaryArmy(battle.getMilitaryDefender());
				// 更新防守方军队资源消耗值
				int[] defenderMilitaryResourceConsume = militaryService.getConsumeOfCityMilitary(
						battle.getMilitaryDefender().getArmy1(), 
						battle.getMilitaryDefender().getArmy2(), 
						battle.getMilitaryDefender().getArmy3(), 
						battle.getMilitaryDefender().getArmy4(), 
						battle.getMilitaryDefender().getArmy5(), 
						battle.getMilitaryDefender().getArmy6(), 
						battle.getMilitaryDefender().getArmy7(), 
						battle.getMilitaryDefender().getArmy8()
						);
				
				militaryService.updateCityMilitaryConsume(battle.getMilitaryDefenderID(), defenderMilitaryResourceConsume[1], defenderMilitaryResourceConsume[0], defenderMilitaryResourceConsume[2]);
			}
			
			// 进攻方伤兵概率
			float attackerWoundedRate = 0f;  
			// 防守方伤兵概率
			float defenderWoundedRate = 0f;
			
			if (battle.getType() == 1) {
				attackerWoundedRate = 1.0F * cityService.getCityExt(battle.getMilitaryAttacker().getCityID()).getTechWoundedArmyRate() / 100;
			} else if (battle.getType() == 2) {
				attackerWoundedRate = 1.0F * cityService.getCityExt(battle.getMilitaryAttacker().getCityID()).getTechWoundedArmyRate() / 100 * CityWoundedArmyConstant.CITY_WOUNDED_ARMY_MULTIPLE;
				defenderWoundedRate = 1.0F * cityService.getCityExt(battle.getMilitaryDefender().getCityID()).getTechWoundedArmyRate() / 100 * CityWoundedArmyConstant.CITY_WOUNDED_ARMY_MULTIPLE;
			}
			
			// 更新城市资源消耗
			int attackerPopulation = 0,attackerOilConsume = 0,attackerFoodConsume = 0,attackerMoneyConsume = 0;
			for (int i = 0; i < attackerDeadNum.length; i++) {
				if (attackerDeadNum[i] > 0) {
					attackerPopulation += militaryAttacker.getBattleArmyList().get(i).getArmy().getPopulation()*attackerDeadNum[i];
					attackerOilConsume += militaryAttacker.getBattleArmyList().get(i).getArmy().getCostOil()*attackerDeadNum[i];
					attackerFoodConsume += militaryAttacker.getBattleArmyList().get(i).getArmy().getCostFood()*attackerDeadNum[i];
					attackerMoneyConsume += militaryAttacker.getBattleArmyList().get(i).getArmy().getCostMoney()*attackerDeadNum[i];
					// 去除伤兵对人口下降的影响
					attackerPopulation -= militaryAttacker.getBattleArmyList().get(i).getArmy().getPopulation() * (int)Math.ceil(attackerDeadNum[i] * attackerWoundedRate) ;
				}
			}
			
			City attackerCity = cityService.getCityByID(battle.getMilitaryAttacker().getCityID());
			CityResource attackerCityResource = cityService.getCityResourceByCityID(battle.getMilitaryAttacker().getCityID());
			Map<String,Object> attackerCityParams = new HashMap<String,Object>();
			java.util.Map<String, Object> attackerCityResourceParams = new HashMap<String, Object>();
			attackerCityParams.put("cityID", battle.getMilitaryAttacker().getCityID());
			attackerCityParams.put("populationTotal", attackerCity.getPopulationTotal()-attackerPopulation);
			attackerCityResourceParams.put("cityID", battle.getMilitaryAttacker().getCityID());
			attackerCityResourceParams.put("oilConsume", attackerCityResource.getOilConsume()-attackerOilConsume);
			attackerCityResourceParams.put("foodConsume", attackerCityResource.getFoodConsume()-attackerFoodConsume);
			attackerCityResourceParams.put("moneyConsume", attackerCityResource.getMoneyConsume()-attackerMoneyConsume);
			cityService.updateCity(attackerCityParams);
			cityService.updateCityResource(attackerCityResourceParams);
			
			City defenderCity = null;
			CityResource defenderCityResource = null;
			if (battle.getType() == 2) {
				int defenderPopulation = 0, defenderOilConsume = 0,defenderFoodConsume = 0,defenderMoneyConsume = 0;
				for (int i = 0; i < defenderDeadNum.length; i++) {
					if (defenderDeadNum[i] > 0) {
						defenderPopulation += militaryDefender.getBattleArmyList().get(i).getArmy().getPopulation()*defenderDeadNum[i];
						defenderOilConsume += militaryDefender.getBattleArmyList().get(i).getArmy().getCostOil()*defenderDeadNum[i];
						defenderFoodConsume += militaryDefender.getBattleArmyList().get(i).getArmy().getCostFood()*defenderDeadNum[i];
						defenderMoneyConsume += militaryDefender.getBattleArmyList().get(i).getArmy().getCostMoney()*defenderDeadNum[i];
						// 去除伤兵对人口下降的影响
						defenderPopulation -= militaryDefender.getBattleArmyList().get(i).getArmy().getPopulation()*(int)Math.ceil(defenderDeadNum[i] * defenderWoundedRate) ;
					}
				}
				defenderCity = cityService.getCityByID(cityService.getCityIDByCityPos(map.getPosX(), map.getPosY()));
				defenderCityResource = cityService.getCityResourceByCityID(battle.getMilitaryDefender().getCityID());
				Map<String,Object> defenderCityParams = new HashMap<String,Object>();
				defenderCityParams.put("cityID", battle.getMilitaryDefender().getCityID());
				defenderCityParams.put("populationTotal", defenderCity.getPopulationTotal() - defenderPopulation);
				defenderCityParams.put("oilConsume", defenderCityResource.getOilConsume() - defenderOilConsume);
				defenderCityParams.put("foodConsume", defenderCityResource.getFoodConsume() - defenderFoodConsume);
				defenderCityParams.put("moneyConsume", defenderCityResource.getMoneyConsume() - defenderMoneyConsume);
				cityService.updateCity(defenderCityParams);
			}
			
			// 更新指挥官经验 判断是否使用经验加成道具、是否有军团增加经验的效果
			CityHeroExt attackerCityHeroExt = heroService.getCityHeroExtByCityHeroID(battle.getMilitaryAttacker().getCityHeroID());
			if (attackerCityHeroExt.getExpTreasureAdd()!=0) {
				battle.setAttackerExp(battle.getAttackerExp() + battle.getAttackerExp() * (attackerCityHeroExt.getExpTreasureAdd() + attackerCityHeroExt.getExpGuildAdd()) / 100);
				battle.setAttackerExp(Math.round(battle.getAttackerExp()*1.0));
			}
			
			heroService.addHeroExp(battle.getMilitaryAttacker().getCityHeroID(), battle.getAttackerExp());
			if (battle.getType()==2) {
				CityHeroExt defenderCityHeroExt = heroService.getCityHeroExtByCityHeroID(battle.getMilitaryDefender().getCityHeroID());
				if (defenderCityHeroExt.getExpTreasureAdd()!=0) {
					battle.setDefenderExp(battle.getDefenderExp() + battle.getDefenderExp() * (defenderCityHeroExt.getExpTreasureAdd() + defenderCityHeroExt.getExpGuildAdd()) / 100);
				}
				battle.setDefenderExp(Math.round(battle.getDefenderExp()*1.0));
				heroService.addHeroExp(battle.getMilitaryDefender().getCityHeroID(), battle.getDefenderExp());
			}
			
			//军队遣返
			DepoyQueue depoyQueue = new DepoyQueue();
			depoyQueue.setCityID(battle.getMilitaryAttacker().getCityID());
			depoyQueue.setCityMilitaryID(battle.getMilitaryAttackerID());
			Map<String,Integer> posMap = cityService.getCityPosByCityID(battle.getMilitaryAttacker().getCityID());
			depoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
			depoyQueue.setType(DepoyTypeConstant.RETURN);
			CityMilitary attackMilitary = militaryService.getCityMilitaryByID(militaryAttacker.getCityMilitaryID());
			int minSpeed = MilitaryConstant.MILITARY_DEFAULT_SPEED;
			for(int i=0;i<attackMilitary.getBattleArmyList().size();i++){
				if(attackMilitary.getBattleArmyList().get(i) == null){
					continue;
				}
				
				if(minSpeed>attackMilitary.getBattleArmyList().get(i).getArmy().getSpeed()){
					minSpeed = attackMilitary.getBattleArmyList().get(i).getArmy().getSpeed();
				}
			}
			depoyQueue.setFinishTime(new Date(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(battle.getStagePosX(), battle.getStagePosY(), battle.getMilitaryAttacker().getCityInfo().getPosX(), battle.getMilitaryAttacker().getCityInfo().getPosY(), minSpeed)*1000));
			
			// 用于战斗日志的JSON
			net.sf.json.JSONObject attainedResourceJSON = new net.sf.json.JSONObject();
			net.sf.json.JSONObject remarkJSON = null;
			
			// 计算掠夺资源
			if (battle.getType()==1) {
				// 掠夺战
				if (winner == 1) {
					
					long carryTotal = 0L;
					for (int i=0;i<8;i++) {
						if (militaryAttacker.getBattleArmyList().get(i)!=null) {
							carryTotal += militaryAttacker.getBattleArmyList().get(i).getAmount() * militaryAttacker.getBattleArmyList().get(i).getArmy().getCarry();
						}
					}
					long maxCarryResourceNum = carryTotal/5;

					long getWoodNum = Math.min(maxCarryResourceNum, droppedResourceList.get(0));
					long getSteelNum = Math.min(maxCarryResourceNum, droppedResourceList.get(1));
					long getOilNum = Math.min(maxCarryResourceNum, droppedResourceList.get(2));
					long getFoodNum = Math.min(maxCarryResourceNum, droppedResourceList.get(3));
					long getMoneyNum = Math.min(maxCarryResourceNum, droppedResourceList.get(4)); 
					
					attainedResourceJSON.put("wood", getWoodNum);
					attainedResourceJSON.put("steel", getSteelNum);
					attainedResourceJSON.put("oil", getOilNum);
					attainedResourceJSON.put("food", getFoodNum);
					attainedResourceJSON.put("money", getMoneyNum);
					
					attackerResultJSON.put("wood", getWoodNum);
					attackerResultJSON.put("steel", getSteelNum);
					attackerResultJSON.put("oil", getOilNum);
					attackerResultJSON.put("food", getFoodNum);
					attackerResultJSON.put("money", getMoneyNum);
						
				}
				
				if (winner != 3) {
					
					StringBuffer woundedArmyTypes = new StringBuffer("");	// 伤兵类型信息, 以 ; 号分隔
					for (Map<String, Object> m : attackerMilitaryResultList) {
						if (!m.get("deadNum").toString().equals("0")) {
							
							if (attackerWoundedRate != 0) {
								attackerResultJSON.put(m.get("armyID").toString(), (int)Math.ceil(Integer.parseInt(m.get("deadNum").toString()) * attackerWoundedRate));
								woundedArmyTypes.append(m.get("armyID")).append(";");
								
								m.put("woundedNum", attackerResultJSON.getInt(m.get("armyID").toString()));
							}
							
						} else {
							
							m.put("woundedNum", 0);
						}
					}
					
					if (woundedArmyTypes.length() > 0) {
						woundedArmyTypes.setCharAt(woundedArmyTypes.length() - 1 , ' ');
						attackerResultJSON.put("woundedArmyTypes", woundedArmyTypes.toString());
					}
					
				}
				
			} else if(battle.getType()==2) {
				
				// 攻城战
				if (winner == 1) {
					
					List<CityMilitarySuccor> cityMilitarySuccorList = cityMilitarySuccorDAO.getCityMilitarySuccorActiveListByTargetCityIDOrderByBattleOrder(militaryDefender.getCityID());
					BattleWait previousBattleWait = battleWaitDAO.getBattleWaitByCityMilitaryID(militaryDefender.getCityMilitaryID());
					
					// 上场战斗为支援军队战斗
					if ( ( militaryDefender.getCityInfo().getPosX().intValue() != battle.getStagePosX().intValue() ) && ( militaryDefender.getCityInfo().getPosY().intValue() != battle.getStagePosY().intValue() ) ) {
						
						cityMilitaryDAO.updateCityMilitaryState(militaryDefender.getCityMilitaryID(), CityMilitaryStateConstant.RESIDE);
						cityMilitarySuccorDAO.deleteCityMilitarySuccorByID(cityMilitarySuccorDAO.getCityMilitarySuccorIDByCityMilitaryID(militaryDefender.getCityMilitaryID()));
						
						// 援军遣返
						DepoyQueue succorDepoyQueue = new DepoyQueue();
						succorDepoyQueue.setCityID(battle.getMilitaryDefender().getCityID());
						succorDepoyQueue.setCityMilitaryID(battle.getMilitaryDefenderID());
						Map<String,Integer> succorPosMap = cityService.getCityPosByCityID(battle.getMilitaryDefender().getCityID());
						succorDepoyQueue.setMapID(mapService.getMapByPos(succorPosMap.get("posX"), succorPosMap.get("posY")).getMapID());
						succorDepoyQueue.setType(DepoyTypeConstant.RETURN);
						int succorMinSpeed = 99;
	
						Date succorDate = new Date();
						succorDate.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(battle.getStagePosX(), battle.getStagePosY(), battle.getMilitaryAttacker().getCityInfo().getPosX(), battle.getMilitaryAttacker().getCityInfo().getPosY(), succorMinSpeed)*1000);
						succorDepoyQueue.setFinishTime(succorDate);
						
						depoyQueueService.createDepoyQueue(succorDepoyQueue);
						
						// 更新援军出战顺序
						cityMilitarySuccorDAO.refreshSuccorOrder(defenderCity.getCityID());
					}
					
					// 清除上一场的战斗等待记录
					if (previousBattleWait != null) {
						battleWaitDAO.deleteBattleWaitByID(previousBattleWait.getBattleWaitID());
					}
					
					if (cityMilitarySuccorList != null && !cityMilitarySuccorList.isEmpty()) {
						isCompleteWin = false;
						
						// 更新支援军队状态
						cityMilitaryDAO.updateCityMilitaryState(cityMilitarySuccorList.get(0).getCityMilitaryID(), CityMilitaryStateConstant.FIGHTING);

						// 加入新的等待战斗
						BattleWait battleWait = new BattleWait();
						battleWait.setAttackerCityMilitaryID(previousBattleWait.getAttackerCityMilitaryID());
						battleWait.setDefenderCityMilitaryID(cityMilitarySuccorList.get(0).getCityMilitaryID());
						battleWait.setMapID(map.getMapID());
						battleWait.setStartTime(new Date(System.currentTimeMillis()  + BattleConstant.INTERVAL_OF_BATTLE * 60 * 1000));
						
						battleWaitDAO.createBattleWait(battleWait);
						
					} else {	// 城市中没有任何援军
						isCompleteWin = true;
						
						List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID()); 
						
						// 删除此地图对应的所有战斗队列信息
						battleQueueDAO.deleteBattleQueueByMapID(map.getMapID());
						
						if (battleQueueList!=null && battleQueueList.size() > 1) {
							int[] cityMilitaryIDs = new int[battleQueueList.size() - 1];
							for (int i = 1; i < battleQueueList.size(); i++) {
								cityMilitaryIDs[i - 1] = battleQueueList.get(i).getCityMilitaryID();
							}
							
							for (int i = 0; i < cityMilitaryIDs.length; i++) {
								cityMilitaryDAO.updateCityMilitaryState(cityMilitaryIDs[i], CityMilitaryStateConstant.MARCH);
								militaryService.nextAttackerAttack(cityMilitaryIDs[i], map.getMapID());
							}
						}
						
						// mapStateFlag = true;
						
						map.setState(MapConstant.STATE_NORMAL);
						mapService.updateMap(map);
					}
					
				} else {	// 进攻方失败
					
					isCompleteWin = false;
					
					BattleWait previousBattleWait = battleWaitDAO.getBattleWaitByCityMilitaryID(militaryDefender.getCityMilitaryID());
					if (previousBattleWait != null) {
						battleWaitDAO.deleteBattleWaitByID(previousBattleWait.getBattleWaitID());
					}
					
					battleQueueDAO.deleteBattleQueueByID(battleQueueDAO.getBattleQueueIDByCityMilitaryID(militaryAttacker.getCityMilitaryID()));
					
					// 更新战斗队列
					battleQueueDAO.refreshBattleQueue(map.getMapID());
					
					List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID());
					if (battleQueueList!=null && !battleQueueList.isEmpty()) {
						cityMilitaryDAO.updateCityMilitaryState(battleQueueList.get(0).getCityMilitaryID(), CityMilitaryStateConstant.MARCH);

						// 加入新的等待战斗
						BattleWait nextBattleWait = new BattleWait();
						nextBattleWait.setAttackerCityMilitaryID(battleQueueList.get(0).getCityMilitaryID());
						nextBattleWait.setDefenderCityMilitaryID(militaryDefender.getCityMilitaryID());
						nextBattleWait.setMapID(map.getMapID());
						nextBattleWait.setStartTime(new Date(System.currentTimeMillis()  + BattleConstant.INTERVAL_OF_BATTLE * 60 * 1000));
						
						battleWaitDAO.createBattleWait(nextBattleWait);
					} else {
						// mapStateFlag = true;
						
						map.setState(MapConstant.STATE_NORMAL);
						mapService.updateMap(map);
					}
					
				}
	
				// 设置进攻方军队伤兵
				if (winner != 3) {
					try {
						StringBuffer woundedArmyTypes = new StringBuffer("");	// 伤兵类型信息, 以 ; 号分隔
						for (Map<String, Object> m : attackerMilitaryResultList) {
							if (!m.get("deadNum").toString().equals("0")) {	// 进攻方逃跑没有伤兵
								
								if (attackerWoundedRate != 0) {
									attackerResultJSON.put(m.get("armyID").toString(), (int)Math.ceil(Integer.parseInt(m.get("deadNum").toString()) * attackerWoundedRate));
									woundedArmyTypes.append(m.get("armyID")).append(";");
									
									m.put("woundedNum", attackerResultJSON.getInt(m.get("armyID").toString()));
								}
								
							} else {
								m.put("woundedNum", 0);
							}
						}
						
						if (woundedArmyTypes.length() > 0) {
							woundedArmyTypes.setCharAt(woundedArmyTypes.length() - 1, ' ');
							attackerResultJSON.put("woundedArmyTypes", woundedArmyTypes);
						}
						
					} catch (JSONException e1) {
						logger.error("异常：", e1);
					}
				}
				
				// 添加防守城市伤兵信息
				if (defenderWoundedRate != 0) {
					for (Map<String, Object> m : defenderMilitaryResultList) {
						if (!m.get("deadNum").toString().equals("0")) {
							CityWoundedArmy cityWoundedArmy = new CityWoundedArmy();
							cityWoundedArmy.setCityID(battle.getMilitaryDefender().getCityID());
							cityWoundedArmy.setArmyID(Integer.parseInt(m.get("armyID").toString()));
							cityWoundedArmy.setNum((int)Math.ceil(Integer.parseInt(m.get("deadNum").toString()) * defenderWoundedRate));
							
							armyService.createCityWoundedArmy(cityWoundedArmy);
							
							m.put("woundedNum", cityWoundedArmy.getNum());
						} else {
							m.put("woundedNum", 0);
						}
					}
				}
			}
			
			// 添加进攻方声望
			playerService.addPlayerRenown(battle.getMilitaryAttacker().getPlayerID(), battle.getAttackerExp()/10);
			
			// 更新指挥官忠诚
			if (battle.getType() == 1){
				if (winner == 2 || winner == 0){
					heroService.updateCityHeroLoyalty(battle.getMilitaryAttacker().getCityHeroID(), battle.getMilitaryAttacker().getCityHero().getLoyalty()-BattleConstant.FAIL_MINUT_HERO_LOYALTY);
				} else if (winner == 3) {
					heroService.updateCityHeroLoyalty(battle.getMilitaryAttacker().getCityHeroID(), battle.getMilitaryAttacker().getCityHero().getLoyalty()-BattleConstant.RETREAT_MINUT_HERO_LOYALTY);
				}
			} else if (battle.getType() == 2){
				if (winner == 1) {
					heroService.updateCityHeroLoyalty(battle.getMilitaryDefender().getCityHeroID(), battle.getMilitaryDefender().getCityHero().getLoyalty()-BattleConstant.FAIL_MINUT_HERO_LOYALTY);
				} else if (winner == 2 || winner == 0){
					heroService.updateCityHeroLoyalty(battle.getMilitaryAttacker().getCityHeroID(), battle.getMilitaryAttacker().getCityHero().getLoyalty()-BattleConstant.FAIL_MINUT_HERO_LOYALTY);
				} else if (winner == 3) {
					heroService.updateCityHeroLoyalty(battle.getMilitaryAttacker().getCityHeroID(), battle.getMilitaryAttacker().getCityHero().getLoyalty()-BattleConstant.RETREAT_MINUT_HERO_LOYALTY);
				}
			}
			
			// 计算所有派遣的兵力和所有死亡的兵力
			int allDispatchNum = 0, allDeadNum = 0;
			for (Map<String, Object> m : attackerMilitaryResultList) {
				allDispatchNum += Integer.parseInt(m.get("dispatchNum").toString());
				allDeadNum += Integer.parseInt(m.get("deadNum").toString());
			}
			
			// 更新指挥官领导力(士气)
			if (battle.getType() == 1) {
				if (winner == 1) {
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-getHeroNeedReducedLeadership(allDispatchNum, allDeadNum), 0));
				} else if (winner == 2 || winner == 0) {
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-BattleConstant.FAIL_MINUT_HERO_LEADERSHIP, 0));
				} else if (winner == 3) {
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-BattleConstant.RETREAT_MINUT_HERO_LEADERSHIP, 0));
				}
			} else if (battle.getType() == 2) {
				if (winner == 1) {
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-getHeroNeedReducedLeadership(allDispatchNum, allDeadNum), 0));
					heroService.updateCityHeroLeadership(battle.getMilitaryDefender().getCityHeroID(), Math.max(battle.getMilitaryDefender().getCityHero().getLeadership()-BattleConstant.FAIL_MINUT_HERO_LEADERSHIP, 0));
				} else if (winner == 2 || winner == 0) {
					heroService.updateCityHeroLeadership(battle.getMilitaryDefender().getCityHeroID(), Math.max(battle.getMilitaryDefender().getCityHero().getLeadership()-getHeroNeedReducedLeadership(allDispatchNum, allDeadNum), 0));
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-BattleConstant.FAIL_MINUT_HERO_LEADERSHIP, 0));
				} else if (winner == 3) {
					heroService.updateCityHeroLeadership(battle.getMilitaryAttacker().getCityHeroID(), Math.max(battle.getMilitaryAttacker().getCityHero().getLeadership()-BattleConstant.RETREAT_MINUT_HERO_LEADERSHIP, 0));
				}
			}
			
			if (isCompleteWin && battle.getType() == 2) {
				
				// -------------------------------------  资源处理（start） -------------------------------------
				
				CityExt cityExt = cityService.getCityExt(battle.getMilitaryAttacker().getCityID());
				int carryAdd = cityExt.getTechCarryAdd();
				
								
				long carryTotal = 0L;
				for(int i=0;i<8;i++){
					if(battle.getMilitaryAttacker().getBattleArmyList().get(i)!=null){
						carryTotal += battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount() * battle.getMilitaryAttacker().getBattleArmyList().get(i).getArmy().getCarry();
					}
				}
				
				if (carryAdd > 0)
					carryTotal = carryTotal + carryTotal * carryAdd / 100;
				
				long maxCarryResourceNum = carryTotal/5;
				
				// 资源保护
				long cityResourceMaxNum = cityResourceDAO.getCityResourceByCityID(battle.getMilitaryDefender().getCityID()).getResourceNumMax();
				double resourceProtectPercent = cityService.getCityExt(battle.getMilitaryDefender().getCityID()).getTechProtectResourcePercent() / 100.0 ;
				long resourceProtectNum = (long)resourceProtectPercent * cityResourceMaxNum;
				
				long getWoodNum = 0;
				long getSteelNum = 0;
				long getOilNum = 0;
				long getFoodNum = 0;
				long getMoneyNum = 0;
				
				if (maxCarryResourceNum > resourceProtectNum) {
					
					Map<String,Long> cityResources = cityService.getCityResourcesNum(battle.getMilitaryDefender().getCityID());
					long cityWoodNum = cityResources.get("woodNum");
					long citySteelNum = cityResources.get("steelNum");
					long cityOilNum = cityResources.get("oilNum");
					long cityFoodNum = cityResources.get("foodNum");
					long cityMoneyNum = cityResources.get("moneyNum");
				
					// 城市当前资源不足
					if(maxCarryResourceNum>cityWoodNum || maxCarryResourceNum>citySteelNum || maxCarryResourceNum>cityOilNum || maxCarryResourceNum>cityFoodNum || maxCarryResourceNum>cityMoneyNum){
						if(winner==1){
							// 如果是进攻方胜利，取消防守城市所有挂单资源
							try {
								marketService.cancelCityAllResourceSales(battle.getMilitaryDefender().getCityID());
							} catch (GameException e) {
								// Nothing to do..
								// 因为marketService.cancelCityAllResourceSales()中会抛出条件检测的GameException
							}
						}
						
						cityResources = cityService.getCityResourcesNum(battle.getMilitaryDefender().getCityID());
						cityWoodNum = cityResources.get("woodNum");
						citySteelNum = cityResources.get("steelNum");
						cityOilNum = cityResources.get("oilNum");
						cityFoodNum = cityResources.get("foodNum");
						cityMoneyNum = cityResources.get("moneyNum");
					}
					
					// 资源保护
					getWoodNum = Math.max(Math.min(cityWoodNum - resourceProtectNum, maxCarryResourceNum), 0);
					getSteelNum = Math.max(Math.min(citySteelNum - resourceProtectNum, maxCarryResourceNum), 0);
					getOilNum = Math.max(Math.min(cityOilNum - resourceProtectNum, maxCarryResourceNum), 0);
					getFoodNum = Math.max(Math.min(cityFoodNum - resourceProtectNum, maxCarryResourceNum), 0);
					getMoneyNum = Math.max(Math.min(cityMoneyNum - resourceProtectNum, maxCarryResourceNum), 0);
					
					// 扣减城市资源
					cityService.minusCityResourcesClear(battle.getMilitaryDefender().getCityID(), getWoodNum, getSteelNum, getOilNum, getFoodNum, getMoneyNum);
					
				}
				
				// 设置军队携带资源
				try {
					
					attackerResultJSON.put("wood", getWoodNum);
					attackerResultJSON.put("steel", getSteelNum);
					attackerResultJSON.put("oil", getOilNum);
					attackerResultJSON.put("food", getFoodNum);
					attackerResultJSON.put("money", getMoneyNum);
					
					attainedResourceJSON.put("wood", getWoodNum);
					attainedResourceJSON.put("steel", getSteelNum);
					attainedResourceJSON.put("oil", getOilNum);
					attainedResourceJSON.put("food", getFoodNum);
					attainedResourceJSON.put("money", getMoneyNum);
					
				} catch (JSONException jsonEx) {
					logger.error("异常：", jsonEx);
				}
				
				// -------------------------------------  资源处理（end） ---------------------------------------
				
				// -------------------------------------  治安处理（start） -------------------------------------
				
				// 如果进攻方胜利并且是攻城战则降低城市治安
				if (winner == 1 && battle.getType() == 2){
					int defenderSecurity = cityService.getCityTaxAndSecurity(battle.getMilitaryDefender().getCityID()).get("security");
					
					long attackerRenown = playerService.getPlayerByID(battle.getMilitaryAttacker().getPlayerID()).getRenown();
					long denfederRenown = playerService.getPlayerByID(battle.getMilitaryDefender().getPlayerID()).getRenown();
					int minusSecurity = (int) (denfederRenown / attackerRenown) * 5;
					//减少治安最小为1，最大为10
					if (minusSecurity < 1) {
						minusSecurity = 1;
					} else if (minusSecurity > 10) {
						minusSecurity = 10;
					}
					
					if (minusSecurity > defenderSecurity){
						minusSecurity = defenderSecurity;
					}
					defenderSecurity = defenderSecurity - minusSecurity;
					
					// 更新防守城市治安
					Map<String,Object> defenderCityParams = new HashMap<String,Object>();
					defenderCityParams.put("cityID", battle.getMilitaryDefender().getCityID());
					defenderCityParams.put("security", defenderSecurity);
					cityService.updateCity(defenderCityParams);
					
					// 殖民
					if (defenderSecurity < 20) {
						// 无法殖民
						if(!colonizationService.canCityColonize(battle.getMilitaryAttacker().getCityID())){
							//reportAttackerParams.put("haveColonized", 3);
							//reportDefenderParams.put("haveColonized", 3);
						} else {
							if(colonizationService.haveColonized(battle.getMilitaryAttacker().getCityID(), battle.getMilitaryDefender().getCityID())){
								// 已经殖民该城市
								//reportAttackerParams.put("haveColonized", 2);
								//reportDefenderParams.put("haveColonized", 2);
							} else {
								colonizationService.addColonization(battle.getMilitaryAttacker().getCityID(), battle.getMilitaryDefender().getCityID());
								//reportAttackerParams.put("haveColonized", 1);
								//reportDefenderParams.put("haveColonized", 1);
							}
						}
					} else {
						//reportAttackerParams.put("haveColonized", 0);
						//reportDefenderParams.put("haveColonized", 0);
					}
					
					//reportAttackerParams.put("minusSecurity", minusSecurity);
					//reportDefenderParams.put("minusSecurity", minusSecurity);
					
					// 记入战斗日志
					remarkJSON = new net.sf.json.JSONObject();
					remarkJSON.put("minusSecurity", minusSecurity);
				}else{
					//reportAttackerParams.put("haveColonized", 0);
					//reportDefenderParams.put("haveColonized", 0);
				}
				
				// -------------------------------------  治安处理（end） ---------------------------------------
				
			} else if (!isCompleteWin && battle.getType() == 2) {
				
				//设置军队携带资源
				try {
					
					attackerResultJSON.put("wood", 0);
					attackerResultJSON.put("steel", 0);
					attackerResultJSON.put("oil", 0);
					attackerResultJSON.put("food", 0);
					attackerResultJSON.put("money", 0);

				} catch (JSONException e1) {
					logger.error("异常：", e1);
				}
				
			}
			
			// 创建军队返回队列
			depoyQueue.setRemark(attackerResultJSON.toString());
			depoyQueueService.createDepoyQueue(depoyQueue);
			
			int round = battle.getRound()%2==0 ? battle.getRound()/2 : battle.getRound()/2+1;
			
			//---------------------------- 记录战斗日志 (Start) ----------------------------
			
			net.sf.json.JSONObject attackerMilitaryInfoJSON = new net.sf.json.JSONObject();
			net.sf.json.JSONObject defenderMilitaryInfoJSON = new net.sf.json.JSONObject();
			JSONArray attackerArmyInfoJSONArray = new JSONArray();
			JSONArray defenderArmyInfoJSONArray = new JSONArray();
			JSONArray cityDefenseInfoJSONArray = null;
			// JSONObject = new JSONObject();
			// JSONObject attainedResourceJSON = new JSONObject();
			JSONArray attainedEquipmentJSONArray = new JSONArray();
			JSONArray attainedTreasureJSONArray = new JSONArray();
			
			BattleLog battleLog = battleLogDAO.getBattleLogByID(battle.getBattleDetailList().get(0).getBattleLogID());
			battleLog.setType(battle.getType());
			battleLog.setAttackerPlayerID(battle.getMilitaryAttacker().getPlayerID());
			battleLog.setMapName(map.getName());
			battleLog.setPosX(battle.getStagePosX());
			battleLog.setPosY(battle.getStagePosY());
			battleLog.setDurativeRound(round);
			battleLog.setStartTime(battle.getStartTime());
			battleLog.setEndTime(new Date(System.currentTimeMillis()));
			battleLog.setAttackerExp(battle.getAttackerExp().intValue());
			battleLog.setAttackerRenown(battle.getAttackerExp().intValue() / 10);
			
			attackerMilitaryInfoJSON.put("heroName", battle.getMilitaryAttacker().getCityHero().getName());
			attackerMilitaryInfoJSON.put("heroLevel", battle.getMilitaryAttacker().getCityHero().getLevel());
			attackerMilitaryInfoJSON.put("heroHead", battle.getMilitaryAttacker().getCityHero().getHead());
			
			if (!droppedTreasureList.isEmpty()) {
				for (PlayerTreasure treasure : droppedTreasureList) {
					net.sf.json.JSONObject treasureJSON = new net.sf.json.JSONObject();
					treasureJSON.put("id", treasure.getTreasureID());
					treasureJSON.put("num", treasure.getNum());
					attainedTreasureJSONArray.add(treasureJSON);
				}
			}
			
			if (!droppedTaskItemList.isEmpty()) {
				for (PlayerTreasure taskItem : droppedTaskItemList) {
					net.sf.json.JSONObject taskItemJSON = new net.sf.json.JSONObject();
					taskItemJSON.put("id", taskItem.getTreasureID());
					taskItemJSON.put("num", taskItem.getNum());
					attainedTreasureJSONArray.add(taskItemJSON);
				}
			}
			
			if (!droppedEquipmentList.isEmpty()) {
				for (PlayerEquipment equipment : droppedEquipmentList) {
					net.sf.json.JSONObject equipmentJSON = new net.sf.json.JSONObject();
					equipmentJSON.put("id", equipment.getEquipmentID());
					equipmentJSON.put("num", 1);
					attainedEquipmentJSONArray.add(equipmentJSON);
				}
			}
			
			for (Map<String, Object> m : attackerMilitaryResultList) {
				net.sf.json.JSONObject attackerArmyInfoJSON = new net.sf.json.JSONObject();
				attackerArmyInfoJSON.put("id", m.get("armyID"));
				attackerArmyInfoJSON.put("dispatchNum", m.get("dispatchNum"));
				attackerArmyInfoJSON.put("deadNum", m.get("deadNum"));
				attackerArmyInfoJSON.put("woundedNum", m.containsKey("woundedNum") ? m.get("woundedNum") : 0);
				
				attackerArmyInfoJSONArray.add(attackerArmyInfoJSON);
			}
			
			for (Map<String, Object> m : defenderMilitaryResultList) {
				net.sf.json.JSONObject defenderArmyInfoJSON = new net.sf.json.JSONObject();
				defenderArmyInfoJSON.put("id", m.get("armyID"));
				defenderArmyInfoJSON.put("dispatchNum", m.get("dispatchNum"));
				defenderArmyInfoJSON.put("deadNum", m.get("deadNum"));
				defenderArmyInfoJSON.put("woundedNum", m.containsKey("woundedNum") ? m.get("woundedNum") : 0);
				
				defenderArmyInfoJSONArray.add(defenderArmyInfoJSON);
			}
			
			if (battle.getType() == 1) {
				defenderMilitaryInfoJSON.put("heroName", battle.getMilitaryDefender().getCmderName());
				defenderMilitaryInfoJSON.put("heroLevel", battle.getMilitaryDefender().getCmderLevel());
				defenderMilitaryInfoJSON.put("heroHead", battle.getMilitaryDefender().getCmderHead());
				
				// 记录野怪等级
				battleLog.setLevel(militaryDefender.getLevel());
				
			} else if (battle.getType() == 2) {
				
				cityDefenseInfoJSONArray = new JSONArray();
				
				// 防守玩家编号
				battleLog.setDefenderPlayerID(battle.getMilitaryDefender().getPlayerID());
				battleLog.setDefenderExp(battle.getDefenderExp().intValue());
				battleLog.setDefenderRenown(battle.getDefenderExp().intValue()/10);
				
				defenderMilitaryInfoJSON.put("heroName", battle.getMilitaryDefender().getCityHero().getName());
				defenderMilitaryInfoJSON.put("heroLevel", battle.getMilitaryDefender().getCityHero().getLevel());
				defenderMilitaryInfoJSON.put("heroHead", battle.getMilitaryDefender().getCityHero().getHead());
				
				// 防御工事
				String[] cityDefenseNum = battle.getCityDefenseAmount().split(","); 
				
				// 围墙
				net.sf.json.JSONObject fenceJSON = new net.sf.json.JSONObject();
				fenceJSON.put("id", DefenseConstant.FENCE);
				fenceJSON.put("joinNum", Integer.parseInt(cityDefenseNum[0]));
				fenceJSON.put("destoryNum", destoryCityDefenseNum[0]);
				cityDefenseInfoJSONArray.add(fenceJSON);
				// 碉堡
				net.sf.json.JSONObject bunkerJSON = new net.sf.json.JSONObject();
				bunkerJSON.put("id", DefenseConstant.BUNKER);
				bunkerJSON.put("joinNum", Integer.parseInt(cityDefenseNum[1])+Integer.parseInt(cityDefenseNum[2]));
				bunkerJSON.put("destoryNum", destoryCityDefenseNum[1]);
				cityDefenseInfoJSONArray.add(bunkerJSON);
				// 火炮
				net.sf.json.JSONObject gunJSON = new net.sf.json.JSONObject();
				gunJSON.put("id", DefenseConstant.GUN);
				gunJSON.put("joinNum", Integer.parseInt(cityDefenseNum[3])+Integer.parseInt(cityDefenseNum[4]));
				gunJSON.put("destoryNum", destoryCityDefenseNum[2]);
				cityDefenseInfoJSONArray.add(gunJSON);
				// 防空炮
				net.sf.json.JSONObject antigunJSON = new net.sf.json.JSONObject();
				antigunJSON.put("id", DefenseConstant.ANTIGUN);
				antigunJSON.put("joinNum", Integer.parseInt(cityDefenseNum[5])+Integer.parseInt(cityDefenseNum[6]));
				antigunJSON.put("destoryNum", destoryCityDefenseNum[3]);
				cityDefenseInfoJSONArray.add(antigunJSON);
			}
			
			// 战斗日志记录的战斗结果
			battleLog.setResult(winner);
			
			battleLog.setAttackerMilitaryInfo(attackerMilitaryInfoJSON.toString());
			battleLog.setDefenderMilitaryInfo(defenderMilitaryInfoJSON.toString());
			battleLog.setAttackerArmyInfo(attackerArmyInfoJSONArray.toString());
			battleLog.setDefenderArmyInfo(defenderArmyInfoJSONArray.toString());
			if ( cityDefenseInfoJSONArray != null ) {
				battleLog.setCityDefenceInfo(cityDefenseInfoJSONArray.toString());
			}
			battleLog.setAttainedResource(attainedResourceJSON.toString());
			battleLog.setAttainedTreasure(attainedTreasureJSONArray.toString());
			battleLog.setAttainedEquipment(attainedEquipmentJSONArray.toString());
			if (remarkJSON != null) {
				battleLog.setRemark(remarkJSON.toString());
			}
			
			battleLogDAO.updateBattleLog(battleLog);
			
			// 向客户端发送战斗日志编号
			try {
				JSONObject json = new JSONObject();
				json.put("type", 6);
				json.put("battleLogID", battleLog.getBattleLogID());
				
				GameSocketService.sendDataToClient(battleLog.getAttackerPlayerID(), json);
				if (battle.getType() == 2) {
					GameSocketService.sendDataToClient(battleLog.getDefenderPlayerID(), json);
				}
				
			} catch (Exception e) {
				logger.error("异常：", e);
			}
			
			//---------------------------- 记录战斗日志 (End) ------------------------------
			
			// 结束战斗详情的记录
			BattleDetail battleDetail = battle.getBattleDetailList().get(battle.getBattleDetailList().size() - 1);
			if (winner == 0) {
				battleDetail.setState(4);
			} else {
				battleDetail.setState(winner);
			}
			battleDetailDAO.createBattleDetail(battleDetail);
			
			
			//如果加入军团添加军团攻击历史
			Integer attackerGuildID = playerService.getPlayerByID(battle.getMilitaryAttacker().getPlayerID()).getGuildID();
			if(attackerGuildID!=null){
				if(battle.getType()==1){
					guildService.addGuildAttack(attackerGuildID, null, attackerCity.getName() + " 攻击 " + map.getName(), 1);
				}else if(battle.getType()==2){
					guildService.addGuildAttack(attackerGuildID, playerService.getPlayerByID(battle.getMilitaryAttacker().getPlayerID()).getGuildID(), attackerCity.getName() + " 攻击 " + map.getName(), 1);
				}
			}
			if(battle.getType()==2){
				Integer defenderGuildID = playerService.getPlayerByID(battle.getMilitaryAttacker().getPlayerID()).getGuildID();
				if(defenderGuildID!=null){
					guildService.addGuildAttack(defenderGuildID, attackerGuildID, map.getName() + " 防御 " + attackerCity.getName(), 2);
				}
			}
			
			/*
			if (mapStateFlag) {
				//更新地图状态为正常
				map.setState(MapConstant.STATE_NORMAL);
				mapService.updateMap(map);
			}
			*/	
			
		} catch(Exception e) {
			logger.error("异常：", e);
		} finally {
			battleFinishedLock.unlock();
		}
		
	}

	/**
	 * 设置战斗完成时军队兵力信息
	 * @param battle
	 */
	private void setBattleFinishedMilitaryArmy(Battle battle){
		
		BattleArmy currentBattleArmy = null;
		
		//更新进攻方战斗后兵力
		for(int i=0;i<battle.getMilitaryAttacker().getBattleArmyList().size();i++){
			currentBattleArmy = battle.getMilitaryAttacker().getBattleArmyList().get(i);
			if(currentBattleArmy==null){
				continue;
			}
			
			switch(i){
				case 0:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy1(null);
					}else{
						battle.getMilitaryAttacker().setArmy1(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 1:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy2(null);
					}else{
						battle.getMilitaryAttacker().setArmy2(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 2:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy3(null);
					}else{
						battle.getMilitaryAttacker().setArmy3(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 3:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy4(null);
					}else{
						battle.getMilitaryAttacker().setArmy4(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 4:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy5(null);
					}else{
						battle.getMilitaryAttacker().setArmy5(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 5:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy6(null);
					}else{
						battle.getMilitaryAttacker().setArmy6(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 6:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy7(null);
					}else{
						battle.getMilitaryAttacker().setArmy7(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 7:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryAttacker().setArmy8(null);
					}else{
						battle.getMilitaryAttacker().setArmy8(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				default:
					break;
			}
		}
		
		//更新防守方战斗后兵力
		for(int i=0;i<battle.getMilitaryDefender().getBattleArmyList().size();i++){
			currentBattleArmy = battle.getMilitaryDefender().getBattleArmyList().get(i);
			if(currentBattleArmy==null){
				continue;
			}
			
			switch(i){
				case 0:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy1(null);
					}else{
						battle.getMilitaryDefender().setArmy1(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 1:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy2(null);
					}else{
						battle.getMilitaryDefender().setArmy2(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 2:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy3(null);
					}else{
						battle.getMilitaryDefender().setArmy3(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 3:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy4(null);
					}else{
						battle.getMilitaryDefender().setArmy4(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 4:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy5(null);
					}else{
						battle.getMilitaryDefender().setArmy5(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 5:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy6(null);
					}else{
						battle.getMilitaryDefender().setArmy6(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 6:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy7(null);
					}else{
						battle.getMilitaryDefender().setArmy7(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				case 7:
					if(currentBattleArmy.getAmount()==0){
						battle.getMilitaryDefender().setArmy8(null);
					}else{
						battle.getMilitaryDefender().setArmy8(currentBattleArmy.getArmyID() + ":" + currentBattleArmy.getAmount());
					}
					break;
				default:
					break;
			}
		}
	}
	
	public Battle initBattleInfo(Integer battleID){
		
		try {
			initBattleInfoLock.lock();
			
			Battle battle = battleDAO.getBattleByID(battleID);
			
			if (battle == null) {
				throw new GameException("战斗信息不存在。");
			} else {
				
				int i = 0;
				
				// 初始化战斗详情
				BattleLog battleLog = null;
				List<BattleLog> battleLogList = battleLogDAO.getBattleLogListByPosXAndPosYOrderByStartTime(battle.getStagePosX(), battle.getStagePosY());
				List<BattleDetail> battleDetailList = null;
				
				if (battleLogList != null && !battleLogList.isEmpty()) {
					battleLog = battleLogList.get(battleLogList.size() - 1); 
				}
				
				if (battleLog == null || battleLog.getResult() != Integer.MIN_VALUE) {
					
					// 初始化战斗日志
					battleLog = new BattleLog();
					battleLog.setMapName("");
					battleLog.setPosX(battle.getStagePosX());
					battleLog.setPosY(battle.getStagePosY());
					battleLog.setAttackerPlayerID(0);
					// battleLog.setDefenderPlayerID(0);
					battleLog.setAttackerMilitaryInfo("");
					battleLog.setDefenderMilitaryInfo("");
					battleLog.setAttackerArmyInfo("");
					battleLog.setDefenderArmyInfo("");
					// battleLog.setCityDefenceInfo("");
					battleLog.setAttackerExp(0);
					battleLog.setDefenderExp(0);
					battleLog.setAttackerRenown(0);
					battleLog.setDefenderRenown(0);
					battleLog.setAttainedResource("");
					battleLog.setAttainedEquipment("");
					battleLog.setAttainedTreasure("");
					// battleLog.setRemark("");
					battleLog.setResult(Integer.MIN_VALUE);
					battleLog.setDurativeRound(0);
					battleLog.setStartTime(new Date());
					battleLog.setEndTime(new Date());
					battleLog.setType(0);
					Integer battleLogID = battleLogDAO.createBattleLog(battleLog);
					
					// 战斗详情
					BattleDetail battleDetail = new BattleDetail();
					battleDetail.setBattleLogID(battleLogID);
					battleDetail.setRound(1);
					battleDetail.setState(0);
					battleDetail.setAttackerOperation("[]");
					battleDetail.setDefenderOperation("[]");
					
					battleDetailList = new ArrayList<BattleDetail>();
					battleDetailList.add(battleDetail);
					
				} else {
				
					// 战斗详情
					battleDetailList = battleDetailDAO.getBattleDetailListByBattleLogID(battleLog.getBattleLogID());
					
					BattleDetail battleDetail = new BattleDetail();
					battleDetail.setBattleLogID(battleLog.getBattleLogID());
					battleDetail.setRound(battleDetailList.size() + 1);
					battleDetail.setState(0);
					battleDetail.setAttackerOperation("[]");
					battleDetail.setDefenderOperation("[]");
					battleDetailList.add(battleDetail);
					
				}
				
				battle.setBattleDetailList(battleDetailList);
				
				int[][] barrierArray = new int[BattleConstant.BATTLE_V_GRID_NUM][BattleConstant.BATTLE_H_GRID_NUM];
				
				// 初始化障碍数组
				for (i=0;i<BattleConstant.BATTLE_V_GRID_NUM;i++) {
					for (int j=0;j<BattleConstant.BATTLE_H_GRID_NUM;j++) {
						barrierArray[i][j] = 0;
					}
				}
				
				// 初始化进攻军队信息
				battle.setMilitaryAttacker(militaryService.getCityMilitaryBattleMilitary(battle.getMilitaryAttackerID()));
				
				// 进攻军队战斗士兵列表
				List<BattleArmy> battleArmyListAttacker = battleArmyDAO.getBattleArmyListByBattleIDAndArmyForce(battleID, 1);
					
				if(battleArmyListAttacker.size()!=0){
					for(i=0;i<battleArmyListAttacker.size();i++){
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setPosX(battleArmyListAttacker.get(i).getPosX());
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setPosY(battleArmyListAttacker.get(i).getPosY());
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setAmount(battleArmyListAttacker.get(i).getAmount());
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setHaveMoved(0);
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setAttackType(0);
						battle.getMilitaryAttacker().getBattleArmyList().get(battleArmyListAttacker.get(i).getArmyIndex()).setSkillMap(new HashMap<Integer, Integer>());
						
						barrierArray[battleArmyListAttacker.get(i).getPosY()][battleArmyListAttacker.get(i).getPosX()] = 1;
					}
				}else{
					for(i=0;i<battle.getMilitaryAttacker().getBattleArmyList().size();i++){
						if(battle.getMilitaryAttacker().getBattleArmyList().get(i) != null){
							//创建数据库数据并初始化缓存数据
							BattleArmy battleArmy = new BattleArmy();
							battleArmy.setBattleID(battleID);
							battleArmy.setArmyForce(1);
							battleArmy.setArmyIndex(i);
							battleArmy.setAmount(battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount());
							battleArmy.setPosX(0);
							battleArmy.setPosY(i);
							battleArmy.setHaveMoved(0);
							battleArmy.setAttackType(0);
							battleArmyDAO.createBattleArmy(battleArmy);
							
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setPosX(battleArmy.getPosX());
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setPosY(battleArmy.getPosY());
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setHaveMoved(battleArmy.getHaveMoved());
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setAttackType(battleArmy.getAttackType());
							battle.getMilitaryAttacker().getBattleArmyList().get(i).setSkillMap(new HashMap<Integer, Integer>());
							
							barrierArray[battleArmy.getPosY()][battleArmy.getPosX()] = 1;
						}
					}
				}
					
				// 初始化防守军队信息
				if(battle.getType() == 1){
					// 掠夺战
					battle.setMilitaryDefender(militaryService.getMapMonsterBattleMilitary(battle.getMilitaryDefenderID()));
				}else if(battle.getType() == 2){
					// 攻城战
					battle.setMilitaryDefender(militaryService.getCityMilitaryBattleMilitary(battle.getMilitaryDefenderID()));
				}
				
				// 防守军队战斗士兵列表
				List<BattleArmy> battleArmyListDefender = battleArmyDAO.getBattleArmyListByBattleIDAndArmyForce(battleID, 2);
				if(battleArmyListDefender.size()!=0){
					for(i=0;i<battleArmyListDefender.size();i++){
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setPosX(battleArmyListDefender.get(i).getPosX());
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setPosY(battleArmyListDefender.get(i).getPosY());
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setAmount(battleArmyListDefender.get(i).getAmount());
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setHaveMoved(0);
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setAttackType(0);
						// TODO jiaHL : [技能持续状态]从数据库中拿出数据
						battle.getMilitaryDefender().getBattleArmyList().get(battleArmyListDefender.get(i).getArmyIndex()).setSkillMap(new HashMap<Integer, Integer>());
						
						barrierArray[battleArmyListDefender.get(i).getPosY()][battleArmyListDefender.get(i).getPosX()] = 1;
					}
					
				} else {
					for (i=0;i<battle.getMilitaryDefender().getBattleArmyList().size();i++) {
						if(battle.getMilitaryDefender().getBattleArmyList().get(i)!=null){
							// 创建数据库数据并初始化缓存数据
							BattleArmy battleArmy = new BattleArmy();
							battleArmy.setBattleID(battleID);
							battleArmy.setArmyForce(2);
							battleArmy.setArmyIndex(i);
							battleArmy.setAmount(battle.getMilitaryDefender().getBattleArmyList().get(i).getAmount());
							battleArmy.setPosX(BattleConstant.BATTLE_H_GRID_NUM - 1);
							battleArmy.setPosY(i);
							battleArmy.setHaveMoved(0);
							battleArmy.setAttackType(0);
							// TODO jiaHL : [技能持续状态]HashMap数据添加到数据库
							battleArmyDAO.createBattleArmy(battleArmy);
							
							battle.getMilitaryDefender().getBattleArmyList().get(i).setPosX(battleArmy.getPosX());
							battle.getMilitaryDefender().getBattleArmyList().get(i).setPosY(battleArmy.getPosY());
							battle.getMilitaryDefender().getBattleArmyList().get(i).setHaveMoved(battleArmy.getHaveMoved());
							battle.getMilitaryDefender().getBattleArmyList().get(i).setAttackType(battleArmy.getAttackType());
							battle.getMilitaryDefender().getBattleArmyList().get(i).setSkillMap(new HashMap<Integer, Integer>());
							
							barrierArray[battleArmy.getPosY()][battleArmy.getPosX()] = 1;
						}
					}
				}
				
				// 初始化双方军队的信息
				if (battle.getType() == 1) {
					setBattleMilitaryArmyAddAndMinus(1, battle.getMilitaryDefender(), battle.getMilitaryAttacker());
					setBattleMilitaryArmyAddAndMinus(2, battle.getMilitaryAttacker(), null);
				} else if (battle.getType() == 2) {
					setBattleMilitaryArmyAddAndMinus(2, battle.getMilitaryAttacker(), battle.getMilitaryDefender());
					setBattleMilitaryArmyAddAndMinus(2, battle.getMilitaryDefender(), battle.getMilitaryAttacker());
				}
				
				//如果是攻城战，初始化城市防御信息
				if(battle.getType()==2){
					battle.setCityDefenseList(cityDefenseService.getCityDefenseList(battle.getMilitaryDefender().getCityID()));
					
					Integer[] cityDefenseAmountArray = new Integer[7];
					String[] cityDefenseAmountStringArray = battle.getCityDefenseAmount().split(",");
					for(i=0;i<cityDefenseAmountArray.length;i++){
						cityDefenseAmountArray[i] = Integer.valueOf(cityDefenseAmountStringArray[i]);
						if (cityDefenseAmountArray[i]>0) {
							// 设置障碍数组
							switch (i) {
								case 0:
									// 围墙
									barrierArray[DefenseConstant.FIRST_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.SECOND_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.THIRD_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.FOURTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.FIFTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.SIXTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.SEVENTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									barrierArray[DefenseConstant.EIGHTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = 1;
									break;
								case 1:
									barrierArray[DefenseConstant.ABOVE_BUNKER_BARRIER_POSY][DefenseConstant.BUNKER_BARRIER_POSX] = 1;
									// 上碉堡
									break;
								case 2:
									barrierArray[DefenseConstant.AFTER_BUNKER_BARRIER_POSY][DefenseConstant.BUNKER_BARRIER_POSX] = 1;
									// 下碉堡
									break;
								case 3:
									barrierArray[DefenseConstant.ABOVE_GUN_BARRIER_POSY][DefenseConstant.GUN_BARRIER_POSX] = 1;
									// 上火炮
									break;
								case 4:
									barrierArray[DefenseConstant.AFTER_GUN_BARRIER_POSY][DefenseConstant.GUN_BARRIER_POSX] = 1;
									// 下火炮
									break;
								case 5:
									barrierArray[DefenseConstant.ABOVE_ANTIGUN_BARRIER_POSY][DefenseConstant.ANTIGUN_BARRIER_POSX] = 1;
									// 上防空炮
									break;
								case 6:
									barrierArray[DefenseConstant.AFTER_ANTIGUN_BARRIER_POSY][DefenseConstant.ANTIGUN_BARRIER_POSX] = 1;
									// 下防空炮
									break;
							}
						}
					}
					battle.setCityDefenseAmountArray(cityDefenseAmountArray);
					
					//初始化城市防御是否攻击
					Integer[] cityDefenseHaveAttackedArray = new Integer[7];
					for(i=0;i<cityDefenseHaveAttackedArray.length;i++){
						cityDefenseHaveAttackedArray[i] = 0;
					}
					battle.setCityDefenseHaveAttackedArray(cityDefenseHaveAttackedArray);
				}
				
				battle.setBarrierArray(barrierArray);
				
				//保存至缓存中
				putBattleToCache(battle);
				
				return battle;
			}
			
		} catch (Exception e) {
			logger.error("异常：", e);
		} finally {
			initBattleInfoLock.unlock();
		}
		
		return null;
	}
	
	/**
	 * 设置战场上军队互相之间的增益与减益效果加成
	 * 此方法中的"左右"并不与战斗中的双方有直接关联，而是"左"表示有减益效果的一方，"右"表示有增益效果的一方
	 * forceRightType == 1时表示右方为野怪；forceRightType == 2表示右方为玩家
	 * @param forceRightType
	 * @param forceRightBattleMilitary
	 * @param forceLeftBattleMilitary
	 */
	private void setBattleMilitaryArmyAddAndMinus(Integer forceRightType, BattleMilitary forceRightBattleMilitary, BattleMilitary forceLeftBattleMilitary) {
		
		// 右方
		CityExt forceRightCityExt = null;
		CityHeroExt forceRightCityHeroExt = null;
		GuildExt forceRightGuildExt = null;
		Integer cityHeroStarMinus = null;
		
		// 是否使用了加攻道具
		boolean isUsedAddAttackTreasure = false ;
		// 是否使用了加防道具
		boolean isUsedAddDefenseTreasure = false ;
		
		if (forceRightType == 1) {	// 设置野怪的增益信息
			
			forceRightCityExt = SystemConfig.defaultCityExt;
			forceRightCityHeroExt = SystemConfig.defaultCityHeroExt;
			forceRightGuildExt = SystemConfig.defaultGuildExt;
			cityHeroStarMinus = 0;
			
			forceRightCityHeroExt.setMilitaryAttackAdd(forceRightBattleMilitary.getCmderCommand()/10);
			forceRightCityHeroExt.setMilitaryDefenseAdd(forceRightBattleMilitary.getCmderDefense()/10);
			
		} else if (forceRightType == 2) {	// 设置玩家的增益信息
			
			forceRightCityExt = cityService.getCityExt(forceRightBattleMilitary.getCityID());
			forceRightCityHeroExt = heroService.getCityHeroExtByCityHeroID(forceRightBattleMilitary.getCityHeroID());
			forceRightGuildExt = guildService.getGuildExt(playerService.getPlayerByID(forceRightBattleMilitary.getPlayerID()).getGuildID());
			cityHeroStarMinus = heroService.getCityHeroStar(forceRightBattleMilitary.getCityHeroID()) - 1 > 0 ? (heroService.getCityHeroStar(forceRightBattleMilitary.getCityHeroID()) - 1) : 0;
			
			if (forceRightGuildExt == null) {	// 玩家没有加入任何军团
				forceRightGuildExt =SystemConfig.defaultGuildExt;
			}
					
			// 是否使用了加攻道具
			isUsedAddAttackTreasure = ( treasureQueueService.getTreasureQueueByType(forceRightBattleMilitary.getCityID(), TreasureCategoryConstant.MILITARY, TreasureTypeConstant.MILITARY_ATTACK_ADD) != null );
			// 是否使用了加防道具
			isUsedAddDefenseTreasure = ( treasureQueueService.getTreasureQueueByType(forceRightBattleMilitary.getCityID(), TreasureCategoryConstant.MILITARY, TreasureTypeConstant.MILITARY_DEFENSE_ADD) != null );
		}
		
		
		// 左方 : 暂只有军团科技中有减益效果
		GuildExt forceLeftGuildExt = null;
		if (forceLeftBattleMilitary != null) {
			forceLeftGuildExt = guildService.getGuildExt(playerService.getPlayerByID(forceLeftBattleMilitary.getPlayerID()).getGuildID());
			if (forceLeftGuildExt == null) {
				forceLeftGuildExt = SystemConfig.defaultGuildExt;
			}
		} else {
			forceLeftGuildExt = SystemConfig.defaultGuildExt;
		}
		
		int attack = 0, defense = 0, speed = 0, range = 0, life = 0;
		int basicAttack = 0, basicDefense = 0, basicSpeed = 0, basicRange = 0, basicLife = 0;
		
		Army militaryArmy = null ;
		
		//初始化军队士兵信息
		for(int i=0;i<forceRightBattleMilitary.getBattleArmyList().size();i++){
			
			if(forceRightBattleMilitary.getBattleArmyList().get(i) != null){
				
				militaryArmy = forceRightBattleMilitary.getBattleArmyList().get(i).getArmy();
				
				basicAttack = attack = militaryArmy.getAttack();
				basicDefense = defense = militaryArmy.getDefense();
				basicSpeed = speed = militaryArmy.getSpeed();
				basicRange = range = militaryArmy.getRange();
				basicLife = life = militaryArmy.getLife();
				
				// 初始化科技加成(百分比)
				switch (militaryArmy.getType()) {
					case ArmyTypeConstant.TYPE_SOLDIER:
						// 攻击 = 单位基础攻击 * (100 + 指挥官星级加成 +　己方军团科技增益 - 敌方军团科技减益)/100
						attack = (int)(basicAttack * ((100 + HeroStarConstant.HERO_STAR_EFFECT_OF_ATTACK[cityHeroStarMinus] + forceRightGuildExt.getMilitaryAttackAdd() - forceLeftGuildExt.getMilitaryAttackMinus()) / 100.0F));
						// 速度 = 单位基础速度 * (100 + 己方军团科技增益)/100
						speed = (int)(basicSpeed * ((100 + forceRightGuildExt.getMilitarySpeedAdd()) / 100.0F));
						// 生命 = 单位生命 * (100 + 己方军团科技增益 +　指挥官星级加成)/100
						life = (int)(basicLife * ((100 + forceRightGuildExt.getArmyLifeAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_LIFE[cityHeroStarMinus]) / 100.0F));
						break;
					case ArmyTypeConstant.TYPE_TRUCK:
						attack = (int)(basicAttack * ((100 + HeroStarConstant.HERO_STAR_EFFECT_OF_ATTACK[cityHeroStarMinus] + forceRightGuildExt.getMilitaryAttackAdd() - forceLeftGuildExt.getMilitaryAttackMinus()) / 100.0F));
						speed = (int)(basicSpeed * ((100 + forceRightGuildExt.getMilitarySpeedAdd()) / 100.0F));
						life = (int)(basicLife * ((100 + forceRightGuildExt.getTruckLifeAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_LIFE[cityHeroStarMinus]) / 100.0F));
						break;
					case ArmyTypeConstant.TYPE_AIRPLANE:
						attack = (int)(basicAttack * ((100 + HeroStarConstant.HERO_STAR_EFFECT_OF_ATTACK[cityHeroStarMinus] + forceRightGuildExt.getMilitaryAttackAdd() - forceLeftGuildExt.getMilitaryAttackMinus()) / 100.0F));
						speed = (int)(basicSpeed * ((100 + forceRightGuildExt.getMilitarySpeedAdd()) / 100.0F));
						life = (int)(basicLife * ((100 + forceRightGuildExt.getAirplaneLifeAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_LIFE[cityHeroStarMinus]) / 100.0F));
						break;
					default:
						break;
				}
				
				// 初始化科技加成(整数值)
				switch(militaryArmy.getType()){
					case ArmyTypeConstant.TYPE_SOLDIER:
						// 攻击 = 攻击 +　城市科技加成 +　指挥官属性加成 - 敌方军团效果
						attack += forceRightCityExt.getTechArmyAttack() + forceRightCityHeroExt.getMilitaryAttackAdd() - forceLeftGuildExt.getArmyAttackMinus();
						// 防御 = 防御 + 城市科技加成 + 指挥官属性加成 + 指挥官星级加成
						defense += forceRightCityExt.getTechArmyDefense() + forceRightCityHeroExt.getMilitaryDefenseAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_DEFENSE[cityHeroStarMinus];
						// 速度 = 速度 + 城市科技加成 - 敌方军团科技减益
						speed += forceRightCityExt.getTechArmySpeed() - forceLeftGuildExt.getArmySpeedMinus();
						// 攻击范围 = 攻击范围 + 城市科技加成 - 敌方军团科技减益
						range += forceRightCityExt.getTechArmyRange() - forceLeftGuildExt.getArmyRangeMinus();
						break;
					case ArmyTypeConstant.TYPE_TRUCK:
						attack += forceRightCityExt.getTechTruckAttack() + forceRightCityHeroExt.getMilitaryAttackAdd() - forceLeftGuildExt.getTruckAttackMinus();
						defense += forceRightCityExt.getTechTruckDefense() + forceRightCityHeroExt.getMilitaryDefenseAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_DEFENSE[cityHeroStarMinus];
						speed += forceRightCityExt.getTechTruckSpeed() - forceLeftGuildExt.getTruckSpeedMinus();
						range += forceRightCityExt.getTechTruckRange() - forceLeftGuildExt.getTruckRangeMinus();
						break;
					case ArmyTypeConstant.TYPE_AIRPLANE:
						attack += forceRightCityExt.getTechAirplaneAttack() + forceRightCityHeroExt.getMilitaryAttackAdd() - forceLeftGuildExt.getAirplaneAttackMinus();
						defense += forceRightCityExt.getTechAirplaneDefense() + forceRightCityHeroExt.getMilitaryDefenseAdd() + HeroStarConstant.HERO_STAR_EFFECT_OF_DEFENSE[cityHeroStarMinus];
						speed += forceRightCityExt.getTechAirplaneSpeed() - forceLeftGuildExt.getAirplaneSpeedMinus();
						range += forceRightCityExt.getTechAirplaneRange() - forceLeftGuildExt.getAirplaneRangeMinus();
						break;
					default:
						break;
				}
				
				// 指挥官加成
				attack += forceRightCityHeroExt.getMilitaryAttackAdd();
				defense += forceRightCityHeroExt.getMilitaryDefenseAdd();
				
				// 科技增加负重加成
				militaryArmy.setCarry( militaryArmy.getCarry()+militaryArmy.getCarry()*forceRightCityExt.getTechCarryAdd()/100 );
				
				// 道具攻击加成
				if ( isUsedAddAttackTreasure ) {
					attack += (int)(basicAttack * 0.2);
				}
				
				// 道具防御加成
				if ( isUsedAddDefenseTreasure ) {
					defense += (int)(basicDefense * 0.2);
				}
				
				militaryArmy.setAttack(attack);
				militaryArmy.setDefense(defense);
				militaryArmy.setRange(range);
				militaryArmy.setSpeed(speed);
				militaryArmy.setLife(life);
				
			}
		}
	}
	
	public Battle getBattleInfo(Integer battleID) {

		Battle battle;
		
		//在缓存中查找战斗信息
		battle = this.getBattleFromCache(battleID);
		
		if(battle == null){
			//初始化战斗信息
			this.initBattleInfo(battleID);
			battle = this.getBattleFromCache(battleID);
		}
		
		return battle;
	}
	
	public List<Battle> getCityAttackBattleList(Integer cityID){
		return battleDAO.getAttackBattleListByCityID(cityID);
	}
	
	public List<Battle> getCityDefenseBattleList(Integer cityID){
		return battleDAO.getDefenseBattleListByCityID(cityID);
	}
	
	public List<Battle> getCityBattleList(Integer cityID){
		
		List<Battle> battleList = battleDAO.getBattleListByCityID(cityID);
		for(int i=0;i<battleList.size();i++){
			
			battleList.get(i).setMilitaryAttacker(militaryService.getCityMilitaryBattleMilitary(battleList.get(i).getMilitaryAttackerID()));
			if(battleList.get(i).getType() == 1){
				battleList.get(i).setMilitaryDefender(militaryService.getMapMonsterBattleMilitary(battleList.get(i).getMilitaryDefenderID()));
			}else if(battleList.get(i).getType() == 2){
				battleList.get(i).setMilitaryDefender(militaryService.getCityMilitaryBattleMilitary(battleList.get(i).getMilitaryDefenderID()));
			}
			
		}
		
		return battleList;
	}
	
	/**
	 * 将战斗信息保存至缓存
	 * @param battle
	 */
	@SuppressWarnings("unchecked")
	private void putBattleToCache(Battle battle){

		Map<Integer,Battle> battleCache = (Map<Integer,Battle>)CacheService.getFromCache(CacheConstant.BATTLE_CACHE);
		
		if(battleCache==null){
			battleCache = new HashMap<Integer,Battle>();
		}
		
		battleCache.put(battle.getBattleID(), battle);
		
		CacheService.putToCache(CacheConstant.BATTLE_CACHE, battleCache);
	}
	
	/**
	 * 从缓存中获取战斗信息
	 * @param roundTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Battle getBattleFromCache(Integer battleID){
		
		Map<Integer,Battle> battleCache = (Map<Integer,Battle>)CacheService.getFromCache(CacheConstant.BATTLE_CACHE);
		
		if(battleCache == null){
			return null;
		}
		
		return battleCache.get(battleID);
	}

	public List<Battle> getRoundFinishedBattleList(Integer roundTime){
		return battleDAO.getRoundFinishedBattleList(roundTime);
	}
	
	public Map<Integer,Battle> getBattleMap(){
		
		List<Battle> battleList = battleDAO.getBattleList();
		
		Map<Integer,Battle> battleMap = new HashMap<Integer,Battle>();
		
		for(int i=0;i<battleList.size();i++){
			battleMap.put(battleList.get(i).getBattleID(), battleList.get(i));
		}
		
		return battleMap;
	}
	
	public void autoBattleBatch(Battle battle, Integer operator) {
		
		List<BattleArmy> battleArmyList = null;
		
		if (operator == 1) {
			battleArmyList = battle.getMilitaryAttacker().getBattleArmyList();
		} else if(operator == 2) {
			battleArmyList = battle.getMilitaryDefender().getBattleArmyList();
		}
		
		for (int i=0;i<battleArmyList.size();i++) {
			if (battleArmyList.get(i) != null && battleArmyList.get(i).getAttackType()==0 && battleArmyList.get(i).getAmount()>0) {
				JSONObject json = new JSONObject();
				
				try {
					json.put("battleID", battle.getBattleID());
					json.put("operator", operator);
					json.put("armyNO", i);
				} catch (JSONException e) {
					logger.error("异常：", e);
				}

				BattleSocketService.sendDataToClient(battle.getBattleID(), this.autoBattle(json));
			}
		}
	}
	
	public JSONObject openAndCloseTheGate(JSONObject json) {
		int battleID = 0;
		// int operator = 0;
		int action = 0;
		try {
			battleID = json.getInt("battleID");
			action = json.getInt("action");
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		Battle battle = getBattleFromCache(battleID);
		battle.getBarrierArray()[DefenseConstant.FOURTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = action;
		battle.getBarrierArray()[DefenseConstant.FIFTH_FENCE_BARRIER_POSY][DefenseConstant.FENCE_BARRIER_POSX] = action;
		
		return json;
		
	}
	
	public JSONObject autoBattle(JSONObject json) {
		
		int battleID = 0;
		int operator = 0;
		int armyNO = 0;
		try {
			battleID = json.getInt("battleID");
			operator = json.getInt("operator");
			armyNO = json.getInt("armyNO");
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		Battle battle = getBattleInfo(battleID);
		if (battle == null) {
			try {
				json.put("type", 39);
				json.put("message", "战斗信息不存在，自动战斗失败。");
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			return json;
		}
		
		//进攻士兵
 		BattleArmy attackerBattleArmy = null;
		List<BattleArmy> defenderBattleArmyList = null;
		
		//距进攻士兵最短距离
		double minDistance = 99;
		int minDistanceIndex = 0;
		double currentDistance = 0;
		
		if (operator==1) {
			//进攻方
			attackerBattleArmy = battle.getMilitaryAttacker().getBattleArmyList().get(armyNO);
			defenderBattleArmyList = battle.getMilitaryDefender().getBattleArmyList();
		} else if (operator==2) {
			//防守方
			attackerBattleArmy = battle.getMilitaryDefender().getBattleArmyList().get(armyNO);
			defenderBattleArmyList = battle.getMilitaryAttacker().getBattleArmyList();
		}
		
		//获得距离进攻士兵距离最短的士兵
		for (int i=0;i<defenderBattleArmyList.size();i++) {
			if (defenderBattleArmyList.get(i)!=null) {
				currentDistance = Math.sqrt(Math.pow(Math.abs(attackerBattleArmy.getPosX()-defenderBattleArmyList.get(i).getPosX()), 2) + Math.pow(Math.abs(attackerBattleArmy.getPosY()-defenderBattleArmyList.get(i).getPosY()), 2));
				
				if (currentDistance<minDistance && defenderBattleArmyList.get(i).getAmount()>0) {
					//如果当前距离小于最小距离 并且 防守方士兵数量大于0 并且 当前士兵可攻击目标士兵
					boolean isTargetAvailable = true;
					
					if (attackerBattleArmy.getArmy().getAttackType()==1 && defenderBattleArmyList.get(i).getArmy().getType()==3)
						//判断地面单位是否可以对空
						isTargetAvailable = false;
					if (attackerBattleArmy.getArmy().getAttackType()==2 && (defenderBattleArmyList.get(i).getArmy().getType()==1 || defenderBattleArmyList.get(i).getArmy().getType()==2))
						//判断空中单位是否可以对地
						isTargetAvailable = false;
					
					if (isTargetAvailable) {
						minDistance = currentDistance;
						minDistanceIndex = i;
					}
				}
			}
		}

		if (minDistance==99) {
			//无可攻击目标 防御
			try {
				json.put("type", 3);
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
			this.armyDefense(json);
			return json;
		}
		
		//防守士兵
		BattleArmy defenderBattleArmy = defenderBattleArmyList.get(minDistanceIndex);
		
		//防守士兵行动方向 X方向(-1.左 1.右)
		int directionX = attackerBattleArmy.getPosX()-defenderBattleArmy.getPosX()>=0?-1:1;
		//防守士兵行动方向 Y方向(-1.上 1.下)
		int directionY = attackerBattleArmy.getPosY()-defenderBattleArmy.getPosY()>=0?-1:1;
		
		//X距离
		int distanceX = Math.abs(attackerBattleArmy.getPosX()-defenderBattleArmy.getPosX());
		//Y距离
		int distanceY = Math.abs(attackerBattleArmy.getPosY()-defenderBattleArmy.getPosY());
		
		boolean is1 = false, is2 = false;
		Map<String, Integer> availablePosMap = null;
		
		/*
		 * 示例
		 * 说明：■代表进攻方 ★代表防守方 ●代表网格
		 *      假设进攻方 速度=2 攻击范围=2
		 *      以下示例以X为参考，Y同理
		 * 情况：①■★ ■●★  ▏X距离<=攻击范围 并且在一条支线上 直接攻击 
		 * 
		 * 		②■●●★ ■●●●★  ▏距离<=速度+攻击范围 并且在一条支线上 X方向行动后X方向攻击 
		 * 
		 * 		③■●● ■●●  ▏
		 * 		  ●●★ ●●●  ▏
		 * 		  	    ●●★  ▏不在一条直线上 但X距离<=速度 并且 Y距离<=攻击范围 X方向行动后Y方向攻击
		 * 
		 * 		④■●●●●★ ■●●●●●★ ■●●●●●●★ ▏…… X距离>速度+攻击范围 并且在一条直线上 X方向行动后防御
		 * 
		 * 		⑤■●●●●● ▏
		 * 		  ●●●●●● ▏
		 * 		  ●●●●●● ▏
		 * 		  ●●●●●★ ▏ …… X距离>速度 Y距离>攻击范围  或者  X距离>攻击范围 Y距离>速度 X方向行动后防御
		 * 	
		 * 		⑥防御
		 * 
		 */
		try{
			if ((distanceX<=attackerBattleArmy.getArmy().getRange() && distanceY==0) || (distanceY<=attackerBattleArmy.getArmy().getRange() && distanceX==0)) {
				//①
				
				json.put("type", 23);
				json.put("targetArmyNO", minDistanceIndex);
				
				this.armyAttack(json);
			} else if ((is1=(distanceX<=(attackerBattleArmy.getArmy().getSpeed()+attackerBattleArmy.getArmy().getRange()) && distanceY==0)) || (is2=(distanceY<=(attackerBattleArmy.getArmy().getSpeed()+attackerBattleArmy.getArmy().getRange()) && distanceX==0))) {
				//②
				
				if (is1) {
					//X方向移动
					int posX = attackerBattleArmy.getPosX() + directionX*attackerBattleArmy.getArmy().getSpeed();
					int posY = attackerBattleArmy.getPosY();
					
					if (!this.isPositionAvailable(battle, posX, posY, operator)) {
						availablePosMap = this.getAvailablePosition(battle, posX, posY, 1, directionX, attackerBattleArmy.getArmy().getSpeed(), operator);
						if (availablePosMap==null) {
							//防御
							json.put("type", 3);
							this.armyDefense(json);
							return json;
						} else if (distanceX-Math.abs(posX-availablePosMap.get("posX"))>attackerBattleArmy.getArmy().getRange()) {
							//攻击范围不足
							json.put("type", 21);
							json.put("posX", availablePosMap.get("posX"));
							json.put("posY", availablePosMap.get("posY"));
							
							this.armyMove(json);
							this.armyDefense(json);
							return json;
						} else {
							posX = availablePosMap.get("posX");
							posY = availablePosMap.get("posY");
							
						}
					}
					
					json.put("type", 22);
					json.put("posX", posX);
					json.put("posY", posY);
					json.put("targetArmyNO", minDistanceIndex);
					
					this.armyMove(json);
					this.armyAttack(json);
				} else if (is2) {
					//Y方向移动
					int posX = attackerBattleArmy.getPosX();
					int posY = attackerBattleArmy.getPosY() + directionY*attackerBattleArmy.getArmy().getSpeed();
					
					if (!this.isPositionAvailable(battle, posX, posY, operator)) {
						availablePosMap = this.getAvailablePosition(battle, posX, posY, 2, directionY, attackerBattleArmy.getArmy().getSpeed(), operator);
						if (availablePosMap==null) {
							//防御
							json.put("type", 3);
							this.armyDefense(json);
							return json;
						} else if (distanceY-Math.abs(posY-availablePosMap.get("posY"))>attackerBattleArmy.getArmy().getRange()) {
							//攻击范围不足
							json.put("type", 21);
							json.put("posX", availablePosMap.get("posX"));
							json.put("posY", availablePosMap.get("posY"));
							
							this.armyMove(json);
							this.armyDefense(json);
							return json;
						} else {
							posX = availablePosMap.get("posX");
							posY = availablePosMap.get("posY");
						}
					}
					
					json.put("type", 22);
					json.put("posX", posX);
					json.put("posY", posY);
					json.put("targetArmyNO", minDistanceIndex);
					
					this.armyMove(json);
					this.armyAttack(json);
				}
			} else if ((is1=(distanceX<=attackerBattleArmy.getArmy().getSpeed() && distanceY<=attackerBattleArmy.getArmy().getRange())) || (is2=(distanceX<=attackerBattleArmy.getArmy().getRange() && distanceY<=attackerBattleArmy.getArmy().getSpeed()))) {
				//③
				
				if (is1) {
					int posX = defenderBattleArmy.getPosX();
					int posY = attackerBattleArmy.getPosY();
					
					if (!this.isPositionAvailable(battle, posX, posY, operator)) {
						availablePosMap = this.getAvailablePosition(battle, posX, posY, 1, directionX, attackerBattleArmy.getArmy().getSpeed(), operator);
						if (availablePosMap==null) {
							//防御
							json.put("type", 3);
							this.armyDefense(json);
							return json;
						} else {
							//与目标士兵不在X坐标不相同
							json.put("type", 21);
							json.put("posX", availablePosMap.get("posX"));
							json.put("posY", availablePosMap.get("posY"));
							
							this.armyMove(json);
							this.armyDefense(json);
							return json;
						}
					}
					
					json.put("type", 22);
					json.put("posX", posX);
					json.put("posY", posY);
					json.put("targetArmyNO", minDistanceIndex);
					
					this.armyMove(json);
					this.armyAttack(json);
				} else if (is2) {
					int posX = attackerBattleArmy.getPosX();
					int posY = defenderBattleArmy.getPosY();
					
					if (!this.isPositionAvailable(battle, posX, posY, operator)) {
						availablePosMap = this.getAvailablePosition(battle, posX, posY, 2, directionY, attackerBattleArmy.getArmy().getSpeed(), operator);
						if (availablePosMap == null) {
							//防御
							json.put("type", 3);
							this.armyDefense(json);
							return json;
						} else {
							//与目标士兵不在Y坐标不相同
							json.put("type", 21);
							json.put("posX", availablePosMap.get("posX"));
							json.put("posY", availablePosMap.get("posY"));
							
							this.armyMove(json);
							this.armyDefense(json);
							return json;
						}
					}
					
					json.put("type", 22);
					json.put("posX", posX);
					json.put("posY", posY);
					json.put("targetArmyNO", minDistanceIndex);
					
					this.armyMove(json);
					this.armyAttack(json);
				}
			} else if ((is1=(distanceX>(attackerBattleArmy.getArmy().getSpeed()+attackerBattleArmy.getArmy().getRange())) && distanceY==0) || (is2=(distanceY>(attackerBattleArmy.getArmy().getSpeed()+attackerBattleArmy.getArmy().getRange())) && distanceX==0)) {
				//④
				
				if (is1) {
					int posX = attackerBattleArmy.getPosX() + directionX*attackerBattleArmy.getArmy().getSpeed();
					int posY = attackerBattleArmy.getPosY();
					
					availablePosMap = this.getAvailablePosition(battle, posX, posY, 1, directionX, attackerBattleArmy.getArmy().getSpeed(), operator);
					if (availablePosMap==null) {
						//防御
						json.put("type", 3);
						this.armyDefense(json);
						return json;
					} else {
						posX = availablePosMap.get("posX");
						posY = availablePosMap.get("posY");
					}
					
					json.put("type", 21);
					json.put("posX", posX);
					json.put("posY", posY);
					
					this.armyMove(json);
					this.armyDefense(json);
				} else if (is2) {
					int posX = attackerBattleArmy.getPosX();
					int posY = attackerBattleArmy.getPosY() + directionY*attackerBattleArmy.getArmy().getSpeed();
					
					availablePosMap = this.getAvailablePosition(battle, posX, posY, 2, directionY, attackerBattleArmy.getArmy().getSpeed(), operator);
					if (availablePosMap==null) {
						//防御
						json.put("type", 3);
						this.armyDefense(json);
						return json;
					} else {
						posX = availablePosMap.get("posX");
						posY = availablePosMap.get("posY");
					}
					
					json.put("type", 21);
					json.put("posX", posX);
					json.put("posY", posY);
	
					this.armyMove(json);
					this.armyDefense(json);
				}
			} else if ((is1=(distanceX>attackerBattleArmy.getArmy().getSpeed() || distanceY>attackerBattleArmy.getArmy().getRange())) || (is2=(distanceY>attackerBattleArmy.getArmy().getSpeed() || distanceX>attackerBattleArmy.getArmy().getRange()))) {
				//⑤
				
				if (is1) {
					int posX = attackerBattleArmy.getPosX() + directionX*attackerBattleArmy.getArmy().getSpeed();
					int posY = attackerBattleArmy.getPosY();
					
					availablePosMap = this.getAvailablePosition(battle, posX, posY, 1, directionX, attackerBattleArmy.getArmy().getSpeed(), operator);
					if (availablePosMap==null) {
						//防御
						json.put("type", 3);
						this.armyDefense(json);
						return json;
					} else {
						posX = availablePosMap.get("posX");
						posY = availablePosMap.get("posY");
					}
					
					json.put("type", 21);
					json.put("posX", posX);
					json.put("posY", posY);
					
					this.armyMove(json);
					this.armyDefense(json);
				} else if (is2) {
					int posX = attackerBattleArmy.getPosX();
					int posY = attackerBattleArmy.getPosY() + directionY*attackerBattleArmy.getArmy().getSpeed();
					
					availablePosMap = this.getAvailablePosition(battle, posX, posY, 2, directionY, attackerBattleArmy.getArmy().getSpeed(), operator);
					if (availablePosMap==null) {
						//防御
						json.put("type", 3);
						this.armyDefense(json);
						return json;
					} else {
						posX = availablePosMap.get("posX");
						posY = availablePosMap.get("posY");
					}
					
					json.put("type", 21);
					json.put("posX", posX);
					json.put("posY", posY);
					
					this.armyMove(json);
					this.armyDefense(json);
				}
			} else {
				//⑥
				json.put("type", 3);
				this.armyDefense(json);
			}
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
		return json;
	}

	/**
	 * 检查位置是否合法
	 * @param battle
	 * @param posX
	 * @param posY
	 * @param xy
	 * @param direction
	 * @param speed
	 * @param playerForce 玩家势力(1.进攻方 2.防守方)
	 * @return 若合法则返回坐标Map，不合法则则尝试寻找合法坐标并返回。若全部非法则返回null。
	 */
	private Map<String,Integer> getAvailablePosition(Battle battle, int posX, int posY, int xy, int direction, int speed, int playerForce) {
		
		boolean isAvailable = false;
		Map<String, Integer> posMap = new HashMap<String, Integer>();
		
		if (!this.isPositionAvailable(battle, posX, posY, playerForce)) {
			for (int i=1;i<speed;i++) {
				if (xy==1) {
					//横向
					posX = posX - i*direction;
					if(this.isPositionAvailable(battle, posX, posY, playerForce)){
						isAvailable = true;
						break;
					}
				} else if (xy==2) {
					//纵向
					posY = posY - i*direction;
					if(this.isPositionAvailable(battle, posX, posY, playerForce)){
						isAvailable = true;
						break;
					}
				}
			}
			
			if (isAvailable) {
				posMap.put("posX", posX);
				posMap.put("posY", posY);
				return posMap;
			} else {
				return null;
			}
		} else {
			posMap.put("posX", posX);
			posMap.put("posY", posY);
			return posMap;
		}
		
	}

	/**
	 * 判断位置是否合法
	 * @param battle
	 * @param posX
	 * @param posY
	 * @param playerForce 玩家势力(1.进攻方 2.防守方)
	 * @return 若合法则返回true，不合法则返回false 
	 */
	private boolean isPositionAvailable(Battle battle, int posX, int posY, int playerForce) {
		
		// 坐标是否超出边界
		if(posX<0 || posY<0 || posX>=BattleConstant.BATTLE_H_GRID_NUM || posY>=BattleConstant.BATTLE_V_GRID_NUM)
			return false;
		
		// 坐标是否为障碍
		if (battle.getBarrierArray()[posY][posX]==1) {
			return false;
		}
		
		if (battle.getType()==BattleConstant.TYPE_SIEGE_WARFARE && playerForce==BattleConstant.FORCE_ATTACKER) {
			if (posX>=12 && battle.getBarrierArray()[0][12]==1) {
				// X坐标大于等于城墙坐标 且 城墙存在  则 被阻拦
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 获得指挥官需要减少的士气值
	 * @param militaryDispatchNum
	 * @param militaryDeadNum
	 * @return
	 */
	private int getHeroNeedReducedLeadership(Integer militaryDispatchNum, Integer militaryDeadNum) {
		
		double losingRate = 1.0 * militaryDeadNum / militaryDispatchNum ;
		if (losingRate <= BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[0][0]/100.0) {
			return 0;
		} else if (losingRate > BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[0][0]/100.0 && losingRate <= BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[1][0]/100.0) {
			return BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[0][1];
		} else if (losingRate > BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[1][0]/100.0 && losingRate <= BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[2][0]/100.0) {
			return BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[1][1];
		} else {
			return BattleConstant.MILITARY_LOSING_MINUT_HERO_LEADERSHIP[2][1];
		}
	}
	
	public BattleLog getBattleLogByID(Integer battleLogID) {
		BattleLog battleLog = battleLogDAO.getBattleLogByID(battleLogID);
		return this.setBattleLogList(battleLog);
	}

	public List<BattleLog> getBattleLogListByPlayerID(Integer playerID) {
		
		List<BattleLog> battleLogList = battleLogDAO.getBattleLogListByPlayerID(playerID);
		
		if (battleLogList != null && !battleLogList.isEmpty()) {
			
			JSONArray attackerArmyInfoJSONArray = null;
			JSONArray defenderArmyInfoJSONArray = null;
			JSONArray cityDefenseInfoJSONArray = null;
			JSONArray attainedTreasureJSONArray = null;
			JSONArray attainedEquipmentJSONArray = null;
			
			for (BattleLog battleLog : battleLogList) {
				attackerArmyInfoJSONArray = JSONArray.fromObject(battleLog.getAttackerArmyInfo());
				defenderArmyInfoJSONArray = JSONArray.fromObject(battleLog.getDefenderArmyInfo());
				cityDefenseInfoJSONArray = battleLog.getCityDefenceInfo() == null ? null : JSONArray.fromObject(battleLog.getCityDefenceInfo());
				attainedTreasureJSONArray = JSONArray.fromObject(battleLog.getAttainedTreasure());
				attainedEquipmentJSONArray = JSONArray.fromObject(battleLog.getAttainedEquipment());
				
				battleLog.setAttackArmyList(new ArrayList<Map<String,Object>>());
				battleLog.setDefenderArmyList(new ArrayList<Map<String,Object>>());
				battleLog.setCityDefenseList(new ArrayList<Map<String,Object>>());
				battleLog.setAttainedTreasureList(new ArrayList<Map<String,Object>>());
				battleLog.setAttainedEquipmentList(new ArrayList<Map<String,Object>>());
				
				for (int i = 0; i < attackerArmyInfoJSONArray.size(); i++) {
					Map<String, Object> attackerArmyInfoMap = new HashMap<String, Object>();
					attackerArmyInfoMap.put("army", armyService.getArmyByID(attackerArmyInfoJSONArray.getJSONObject(i).getInt("id")));
					attackerArmyInfoMap.put("dispatchNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
					attackerArmyInfoMap.put("deadNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
					attackerArmyInfoMap.put("woundedNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
					
					battleLog.getAttackArmyList().add(attackerArmyInfoMap);
				}
				
				for (int i = 0; i < defenderArmyInfoJSONArray.size(); i++) {
					Map<String, Object> defenderArmyInfoMap = new HashMap<String, Object>();
					defenderArmyInfoMap.put("army", armyService.getArmyByID(defenderArmyInfoJSONArray.getJSONObject(i).getInt("id")));
					defenderArmyInfoMap.put("dispatchNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
					defenderArmyInfoMap.put("deadNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
					defenderArmyInfoMap.put("woundedNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
					
					battleLog.getDefenderArmyList().add(defenderArmyInfoMap);
				}
				
				if (cityDefenseInfoJSONArray != null) {
					for (int i = 0; i < cityDefenseInfoJSONArray.size(); i++) {
						Map<String, Object> cityDefenseInfoMap = new HashMap<String, Object>();
						cityDefenseInfoMap.put("defense", defenseService.getDefenseByID(cityDefenseInfoJSONArray.getJSONObject(i).getInt("id")));
						cityDefenseInfoMap.put("joinNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("joinNum"));
						cityDefenseInfoMap.put("destoryNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("destoryNum"));
						
						battleLog.getCityDefenseList().add(cityDefenseInfoMap);
					}
				}
				
				for (int i = 0; i < attainedTreasureJSONArray.size(); i++) {
					Map<String, Object> attainedTreasureMap = new HashMap<String, Object>();
					attainedTreasureMap.put("treasure", treasureService.getTreasureByID(attainedTreasureJSONArray.getJSONObject(i).getInt("id")));
					attainedTreasureMap.put("num", attainedTreasureJSONArray.getJSONObject(i).getInt("num"));

					battleLog.getAttainedTreasureList().add(attainedTreasureMap);
				}
				
				for (int i = 0; i < attainedEquipmentJSONArray.size(); i++) {
					Map<String, Object> attainedEquipmentMap = new HashMap<String, Object>();
					attainedEquipmentMap.put("equipment", equipmentService.getEquipmentByID(attainedEquipmentJSONArray.getJSONObject(i).getInt("id")));
					attainedEquipmentMap.put("num", attainedEquipmentJSONArray.getJSONObject(i).getInt("num"));
					
					battleLog.getAttainedEquipmentList().add(attainedEquipmentMap);
				}
			}
			
		}
			
		return battleLogList;
	}
	
	/**
	 * 设置战斗日志
	 * @param battleLog
	 * @return
	 */
	private BattleLog setBattleLogList(BattleLog battleLog) {
		
		JSONArray attackerArmyInfoJSONArray = JSONArray.fromObject(battleLog.getAttackerArmyInfo());
		JSONArray defenderArmyInfoJSONArray = JSONArray.fromObject(battleLog.getDefenderArmyInfo());
		JSONArray cityDefenseInfoJSONArray = battleLog.getCityDefenceInfo() == null ? null : JSONArray.fromObject(battleLog.getCityDefenceInfo());
		JSONArray attainedTreasureJSONArray = JSONArray.fromObject(battleLog.getAttainedTreasure());
		JSONArray attainedEquipmentJSONArray = JSONArray.fromObject(battleLog.getAttainedEquipment());
		
		battleLog.setAttackArmyList(new ArrayList<Map<String,Object>>());
		battleLog.setDefenderArmyList(new ArrayList<Map<String,Object>>());
		battleLog.setCityDefenseList(new ArrayList<Map<String,Object>>());
		battleLog.setAttainedTreasureList(new ArrayList<Map<String,Object>>());
		battleLog.setAttainedEquipmentList(new ArrayList<Map<String,Object>>());
		
		for (int i = 0; i < attackerArmyInfoJSONArray.size(); i++) {
			Map<String, Object> attackerArmyInfoMap = new HashMap<String, Object>();
			attackerArmyInfoMap.put("army", armyService.getArmyByID(attackerArmyInfoJSONArray.getJSONObject(i).getInt("id")));
			attackerArmyInfoMap.put("dispatchNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
			attackerArmyInfoMap.put("deadNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
			attackerArmyInfoMap.put("woundedNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
			
			battleLog.getAttackArmyList().add(attackerArmyInfoMap);
		}
		
		for (int i = 0; i < defenderArmyInfoJSONArray.size(); i++) {
			Map<String, Object> defenderArmyInfoMap = new HashMap<String, Object>();
			defenderArmyInfoMap.put("army", armyService.getArmyByID(defenderArmyInfoJSONArray.getJSONObject(i).getInt("id")));
			defenderArmyInfoMap.put("dispatchNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
			defenderArmyInfoMap.put("deadNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
			defenderArmyInfoMap.put("woundedNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
			
			battleLog.getDefenderArmyList().add(defenderArmyInfoMap);
		}
		
		if (cityDefenseInfoJSONArray != null) {
			for (int i = 0; i < cityDefenseInfoJSONArray.size(); i++) {
				Map<String, Object> cityDefenseInfoMap = new HashMap<String, Object>();
				cityDefenseInfoMap.put("defense", defenseService.getDefenseByID(cityDefenseInfoJSONArray.getJSONObject(i).getInt("id")));
				cityDefenseInfoMap.put("joinNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("joinNum"));
				cityDefenseInfoMap.put("destoryNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("destoryNum"));
				
				battleLog.getCityDefenseList().add(cityDefenseInfoMap);
			}
		}
		
		for (int i = 0; i < attainedTreasureJSONArray.size(); i++) {
			Map<String, Object> attainedTreasureMap = new HashMap<String, Object>();
			attainedTreasureMap.put("treasure", treasureService.getTreasureByID(attainedTreasureJSONArray.getJSONObject(i).getInt("id")));
			attainedTreasureMap.put("num", attainedTreasureJSONArray.getJSONObject(i).getInt("num"));

			battleLog.getAttainedTreasureList().add(attainedTreasureMap);
		}
		
		for (int i = 0; i < attainedEquipmentJSONArray.size(); i++) {
			Map<String, Object> attainedEquipmentMap = new HashMap<String, Object>();
			attainedEquipmentMap.put("equipment", equipmentService.getEquipmentByID(attainedEquipmentJSONArray.getJSONObject(i).getInt("id")));
			attainedEquipmentMap.put("num", attainedEquipmentJSONArray.getJSONObject(i).getInt("num"));
			
			battleLog.getAttainedEquipmentList().add(attainedEquipmentMap);
		}
		
		return battleLog;
		
	}
	
	public void finishBattleIntervalWait(Integer battleWaitID) {
		BattleWait battleWait = battleWaitDAO.getBattleWaitByID(battleWaitID);
		battleWaitDAO.deleteBattleWaitByID(battleWaitID);
		militaryService.nextAttackerAttack(battleWait.getAttackerCityMilitaryID(), battleWait.getMapID());
	}

	public List<BattleWait> getIntervalFinishedBattleWaitList() {
		return battleWaitDAO.getIntervalFinishedBattleWaitList();
	}
	
	public Integer getPlayerAttackBattleLogNum(Integer playerID) {
		return battleLogDAO.getBattleLogNumByAttackerPlayerID(playerID);
	}

	public List<Battle> getBattleList(){
		return battleDAO.getBattleList();
	}
	
	public Integer getBattleLogNumForAttackTask(Integer playerID, Integer level, Date time) {
		return battleLogDAO.getBattleLogNumForAttackTask(playerID, level, DateService.changeDateFormat(time, "yyyy-MM-dd"));
	}
	
	public List<BattleLog> getPlayerBattleLogPagingList(Integer playerID, Integer page) {
		int start = (page - 1) * PagingConstant.BATTLELOG_PAGE_SIZE ;
		int offset = PagingConstant.BATTLELOG_PAGE_SIZE;

		List<BattleLog> battleLogList = battleLogDAO.getBattleLogPagingListByPlayerID(playerID, start, offset);

		if (battleLogList != null && !battleLogList.isEmpty()) {
			
			JSONArray attackerArmyInfoJSONArray = null;
			JSONArray defenderArmyInfoJSONArray = null;
			JSONArray cityDefenseInfoJSONArray = null;
			JSONArray attainedTreasureJSONArray = null;
			JSONArray attainedEquipmentJSONArray = null;
			
			for (BattleLog battleLog : battleLogList) {
				attackerArmyInfoJSONArray = JSONArray.fromObject(battleLog.getAttackerArmyInfo());
				defenderArmyInfoJSONArray = JSONArray.fromObject(battleLog.getDefenderArmyInfo());
				cityDefenseInfoJSONArray = battleLog.getCityDefenceInfo() == null ? null : JSONArray.fromObject(battleLog.getCityDefenceInfo());
				attainedTreasureJSONArray = JSONArray.fromObject(battleLog.getAttainedTreasure());
				attainedEquipmentJSONArray = JSONArray.fromObject(battleLog.getAttainedEquipment());
				
				battleLog.setAttackArmyList(new ArrayList<Map<String,Object>>());
				battleLog.setDefenderArmyList(new ArrayList<Map<String,Object>>());
				battleLog.setCityDefenseList(new ArrayList<Map<String,Object>>());
				battleLog.setAttainedTreasureList(new ArrayList<Map<String,Object>>());
				battleLog.setAttainedEquipmentList(new ArrayList<Map<String,Object>>());
				
				for (int i = 0; i < attackerArmyInfoJSONArray.size(); i++) {
					Map<String, Object> attackerArmyInfoMap = new HashMap<String, Object>();
					attackerArmyInfoMap.put("army", armyService.getArmyByID(attackerArmyInfoJSONArray.getJSONObject(i).getInt("id")));
					attackerArmyInfoMap.put("dispatchNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
					attackerArmyInfoMap.put("deadNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
					attackerArmyInfoMap.put("woundedNum", attackerArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
					
					battleLog.getAttackArmyList().add(attackerArmyInfoMap);
				}
				
				for (int i = 0; i < defenderArmyInfoJSONArray.size(); i++) {
					Map<String, Object> defenderArmyInfoMap = new HashMap<String, Object>();
					defenderArmyInfoMap.put("army", armyService.getArmyByID(defenderArmyInfoJSONArray.getJSONObject(i).getInt("id")));
					defenderArmyInfoMap.put("dispatchNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("dispatchNum"));
					defenderArmyInfoMap.put("deadNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("deadNum"));
					defenderArmyInfoMap.put("woundedNum", defenderArmyInfoJSONArray.getJSONObject(i).getInt("woundedNum"));
					
					battleLog.getDefenderArmyList().add(defenderArmyInfoMap);
				}
				
				if (cityDefenseInfoJSONArray != null) {
					for (int i = 0; i < cityDefenseInfoJSONArray.size(); i++) {
						Map<String, Object> cityDefenseInfoMap = new HashMap<String, Object>();
						cityDefenseInfoMap.put("defense", defenseService.getDefenseByID(cityDefenseInfoJSONArray.getJSONObject(i).getInt("id")));
						cityDefenseInfoMap.put("joinNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("joinNum"));
						cityDefenseInfoMap.put("destoryNum", cityDefenseInfoJSONArray.getJSONObject(i).getInt("destoryNum"));
						
						battleLog.getCityDefenseList().add(cityDefenseInfoMap);
					}
				}
				
				for (int i = 0; i < attainedTreasureJSONArray.size(); i++) {
					Map<String, Object> attainedTreasureMap = new HashMap<String, Object>();
					attainedTreasureMap.put("treasure", treasureService.getTreasureByID(attainedTreasureJSONArray.getJSONObject(i).getInt("id")));
					attainedTreasureMap.put("num", attainedTreasureJSONArray.getJSONObject(i).getInt("num"));

					battleLog.getAttainedTreasureList().add(attainedTreasureMap);
				}
				
				for (int i = 0; i < attainedEquipmentJSONArray.size(); i++) {
					Map<String, Object> attainedEquipmentMap = new HashMap<String, Object>();
					attainedEquipmentMap.put("equipment", equipmentService.getEquipmentByID(attainedEquipmentJSONArray.getJSONObject(i).getInt("id")));
					attainedEquipmentMap.put("num", attainedEquipmentJSONArray.getJSONObject(i).getInt("num"));
					
					battleLog.getAttainedEquipmentList().add(attainedEquipmentMap);
				}
			}
			
		}
		
		return battleLogList;
	}
	
	public Integer getPlayerBattleLogNum(Integer playerID) {
		return battleLogDAO.getBattleLogNumByPlayerID(playerID);
	}
	
	
	public IBattleDAO getBattleDAO() {
		return battleDAO;
	}

	public void setBattleDAO(IBattleDAO battleDAO) {
		this.battleDAO = battleDAO;
	}
	
	public IBattleLogDAO getBattleLogDAO() {
		return battleLogDAO;
	}

	public void setBattleLogDAO(IBattleLogDAO battleLogDAO) {
		this.battleLogDAO = battleLogDAO;
	}
	
	public IBattleArmyDAO getBattleArmyDAO() {
		return battleArmyDAO;
	}

	public void setBattleArmyDAO(IBattleArmyDAO battleArmyDAO) {
		this.battleArmyDAO = battleArmyDAO;
	}
	
	public IBattleDetailDAO getBattleDetailDAO() {
		return battleDetailDAO;
	}

	public void setBattleDetailDAO(IBattleDetailDAO battleDetailDAO) {
		this.battleDetailDAO = battleDetailDAO;
	}
	
	public IBattleQueueDAO getBattleQueueDAO() {
		return battleQueueDAO;
	}

	public void setBattleQueueDAO(IBattleQueueDAO battleQueueDAO) {
		this.battleQueueDAO = battleQueueDAO;
	}
	
	public IBattleWaitDAO getBattleWaitDAO() {
		return battleWaitDAO;
	}

	public void setBattleWaitDAO(IBattleWaitDAO battleWaitDAO) {
		this.battleWaitDAO = battleWaitDAO;
	}

	public IBuildingDAO getBuildingDAO() {
		return buildingDAO;
	}

	public void setBuildingDAO(IBuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}
	
	public ICityResourceDAO getCityResourceDAO() {
		return cityResourceDAO;
	}

	public void setCityResourceDAO(ICityResourceDAO cityResourceDAO) {
		this.cityResourceDAO = cityResourceDAO;
	}
	
	public ICityMilitarySuccorDAO getCityMilitarySuccorDAO() {
		return cityMilitarySuccorDAO;
	}

	public void setCityMilitarySuccorDAO(
			ICityMilitarySuccorDAO cityMilitarySuccorDAO) {
		this.cityMilitarySuccorDAO = cityMilitarySuccorDAO;
	}

	public ICityMilitaryDAO getCityMilitaryDAO() {
		return cityMilitaryDAO;
	}

	public void setCityMilitaryDAO(ICityMilitaryDAO cityMilitaryDAO) {
		this.cityMilitaryDAO = cityMilitaryDAO;
	}

	public IDefenseDAO getDefenseDAO() {
		return defenseDAO;
	}

	public void setDefenseDAO(IDefenseDAO defenseDAO) {
		this.defenseDAO = defenseDAO;
	}

	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public IMilitaryService getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(IMilitaryService militaryService) {
		this.militaryService = militaryService;
	}

	public IHeroService getHeroService() {
		return heroService;
	}

	public void setHeroService(IHeroService heroService) {
		this.heroService = heroService;
	}
	
	public IDepoyQueueService getDepoyQueueService() {
		return depoyQueueService;
	}

	public void setDepoyQueueService(IDepoyQueueService depoyQueueService) {
		this.depoyQueueService = depoyQueueService;
	}

	public ICityDefenseService getCityDefenseService() {
		return cityDefenseService;
	}

	public void setCityDefenseService(ICityDefenseService cityDefenseService) {
		this.cityDefenseService = cityDefenseService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}
	
	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}
	
	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}
	
	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}

	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public IMapService getMapService() {
		return mapService;
	}

	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}

	public IMarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}

	public IColonizationService getColonizationService() {
		return colonizationService;
	}

	public void setColonizationService(IColonizationService colonizationService) {
		this.colonizationService = colonizationService;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

	public ITechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(ITechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public IDefenseService getDefenseService() {
		return defenseService;
	}

	public void setDefenseService(IDefenseService defenseService) {
		this.defenseService = defenseService;
	}

}
