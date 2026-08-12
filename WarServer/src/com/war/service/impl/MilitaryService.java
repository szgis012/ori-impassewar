package com.war.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.RandomService;
import com.war.common.TemplateService;
import com.war.constant.ArmyConstant;
import com.war.constant.BuildingConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityHeroStateConstant;
import com.war.constant.CityMilitaryStateConstant;
import com.war.constant.DefenseConstant;
import com.war.constant.DepoyTypeConstant;
import com.war.constant.GuildConstant;
import com.war.constant.MapConstant;
import com.war.constant.MilitaryConstant;
import com.war.constant.OperationLogConstant;
import com.war.constant.PlayerStateConstant;
import com.war.constant.SpyConstant;
import com.war.constant.SpyQueueStateConstant;
import com.war.constant.TechnologyConstant;
import com.war.constant.TechnologyTypeConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.IBattleDAO;
import com.war.dao.IBattleLogDAO;
import com.war.dao.IBattleQueueDAO;
import com.war.dao.IBattleWaitDAO;
import com.war.dao.ICityHeroDAO;
import com.war.dao.ICityMilitaryDAO;
import com.war.dao.ICityMilitarySuccorDAO;
import com.war.dao.IDepoyQueueDAO;
import com.war.dao.IFriendDAO;
import com.war.dao.IHeroSkillDAO;
import com.war.dao.IMapMonsterDAO;
import com.war.dao.IMilitaryDAO;
import com.war.dao.IProcessQueueDAO;
import com.war.dao.ISkillDAO;
import com.war.domain.Army;
import com.war.domain.Battle;
import com.war.domain.BattleArmy;
import com.war.domain.BattleMilitary;
import com.war.domain.BattleQueue;
import com.war.domain.City;
import com.war.domain.CityArmy;
import com.war.domain.CityBuilding;
import com.war.domain.CityDefense;
import com.war.domain.CityInfo;
import com.war.domain.CityMilitary;
import com.war.domain.CityMilitarySuccor;
import com.war.domain.CityResource;
import com.war.domain.CityTechnology;
import com.war.domain.CityWoundedArmy;
import com.war.domain.DeclareWar;
import com.war.domain.DepoyQueue;
import com.war.domain.GuildPlayer;
import com.war.domain.HeroSkill;
import com.war.domain.Map;
import com.war.domain.MapMonster;
import com.war.domain.Player;
import com.war.domain.PlayerEquipment;
import com.war.domain.Skill;
import com.war.domain.SpyQueue;
import com.war.exception.GameException;
import com.war.service.IArmyService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IColonizationService;
import com.war.service.IDeclareWarService;
import com.war.service.IDepoyQueueService;
import com.war.service.IEquipmentService;
import com.war.service.IGuildService;
import com.war.service.IMapService;
import com.war.service.IMilitaryService;
import com.war.service.IMonsterService;
import com.war.service.IOperationLogService;
import com.war.service.IPlayerService;
import com.war.service.IReportService;
import com.war.service.ISpyQueueService;
import com.war.service.IStrongholdService;
import com.war.service.ITechnologyService;
import com.war.service.ITreasureQueueService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityDefenseService;
import com.war.service.building.IMarketService;
import com.war.socket.game.GameSocketService;
import com.war.util.ArmyUtil;
import com.war.util.CostTimeCalculateUtil;

public class MilitaryService implements IMilitaryService {
	
	private IMilitaryDAO militaryDAO;
	
	private ICityMilitaryDAO cityMilitaryDAO;
	
	private IBattleDAO battleDAO;
	
	private IBattleLogDAO battleLogDAO;
	
	private IBattleWaitDAO battleWaitDAO;
	
	private IBattleQueueDAO battleQueueDAO;
						   
	private IMapMonsterDAO mapMonsterDAO;
	
	private IDepoyQueueDAO depoyQueueDAO;
	
	private ICityHeroDAO cityHeroDAO;
	
	private ICityMilitarySuccorDAO cityMilitarySuccorDAO;
	
	private IHeroSkillDAO heroSkillDAO;
	
	private ISkillDAO skillDAO;
	
	private IFriendDAO friendDAO;
	
	private IProcessQueueDAO processQueueDAO;
	
	private IArmyService armyService;
	
	private IDepoyQueueService depoyQueueService ;
	
	private IPlayerService playerService;
	
	private IMapService mapService;
	
	private ICityService cityService;
	
	private IGuildService guildService;
	
	private IDeclareWarService declareWarService;
	
	private IBuildingService buildingService;
	
	private ITechnologyService technologyService;
	
	private ICityDefenseService cityDefenseService;
	
	private ISpyQueueService spyQueueService;
	
	private IReportService reportService;
	
	private IMarketService marketService;

	private ITreasureQueueService treasureQueueService;
	
	private IColonizationService colonizationService;
	
	private IOperationLogService operationLogService;
	
	private IMonsterService monsterService;
	
	private ITreasureService treasureService;
	
	private IEquipmentService equipmentService;
	
	private IStrongholdService strongholdService;
	
	private static Logger logger = Logger.getLogger(MilitaryService.class);
	
	private final Lock finishAttackWaitLock = new ReentrantLock();
	
	private final Lock finishDispatchWaitLock = new ReentrantLock();
	
	private final Lock finishReturnWaitLock = new ReentrantLock();
	
	private final Lock clientMilitaryArrivedLock = new ReentrantLock();
	
	public Integer createCityMilitary(Integer cityID,String name,Integer cityHeroID){
		
		CityMilitary cityMilitary = new CityMilitary();
		cityMilitary.setCityID(cityID);
		cityMilitary.setName(name);
		cityMilitary.setCityHeroID(cityHeroID);
		cityMilitary.setCostOil(0);
		cityMilitary.setCostFood(0);
		cityMilitary.setCostMoney(0);
		cityMilitary.setState(CityMilitaryStateConstant.NORMAL);
		Integer cityMilitaryID = cityMilitaryDAO.createCityMilitary(cityMilitary);
		
		cityHeroDAO.updateStateByCityHeroID(cityHeroID, CityHeroStateConstant.FREE);
		
		return cityMilitaryID;
	}

	public void renameCityMilitary(Integer cityMilitaryID, String name) {
		cityMilitaryDAO.updateNameByCityMilitaryID(cityMilitaryID, name);
	}
	
	public void updateCityMilitaryArmy(BattleMilitary battleMilitary){
		cityMilitaryDAO.updateCityMilitaryArmyByBattleMilitary(battleMilitary);
	}
    
    public void updateMapMonsterArmy(BattleMilitary battleMilitary){
    	mapMonsterDAO.updateMapMonsterArmyByBattleMilitary(battleMilitary);
    }
    
	public void updateCityMilitary(CityMilitary cityMilitary){
		cityMilitaryDAO.updateCityMilitary(cityMilitary);
	}
	
	public void updateCityMilitaryConsume(Integer cityMilitaryID, Integer costOil, Integer costFood, Integer costMoney) {
		cityMilitaryDAO.updateCityMilitaryConsume(cityMilitaryID, costOil, costFood, costMoney);
	}
	
	public void deleteMapMonster(Integer mapMonsterID){
		mapMonsterDAO.deleteMapMonsterByID(mapMonsterID);
	}
	
	public void changeOfficer(Integer cityMilitaryID,Integer cityHeroID){
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		//将原英雄状态设置为空闲
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.FREE);
		//将新英雄装提案设置为编制
		cityHeroDAO.updateStateByCityHeroID(cityHeroID, 2);
		
		cityMilitaryDAO.updateCityHeroIDByCityMilitaryID(cityMilitaryID, cityHeroID);
	}
	
	/**
	 * 将军队中的某种兵返回到未编制状态
	 * @param army 
	 */
	private void returnCityArmy(String army,Integer cityID){
		
		if(army==null || army.equals("")){
			return;
		}
		
		String[] tmp = army.split(":");
		if(tmp.length != 2){
			return;
		}
		
		int armyID = Integer.parseInt(tmp[0]);
		int num = Integer.parseInt(tmp[1]);
		
		CityArmy cityArmy = armyService.getCityArmy(cityID,armyID);
		//如果没有该兵种的记录就创建它
		if(cityArmy == null){
			cityArmy = new CityArmy();
			cityArmy.setArmyID(armyID);
			cityArmy.setCityID(cityID);
			cityArmy.setNum(num);
			
			armyService.createCityArmy(cityArmy);
		}else{
			cityArmy.setNum(cityArmy.getNum()+num);
			
			armyService.updateCityArmy(cityArmy);
		}
	}
	
	public void dismissCityMilitary(Integer cityMilitaryID) {
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("指定的军队不存在。");
		
		if(cityMilitary.getState() != CityMilitaryStateConstant.NORMAL){
			throw new GameException("只能解除处在空闲状态下的军队。");
		}
		
		Integer cityID = cityMilitary.getCityID();
		
		//将军队删除并将军队中编制的士兵返回到未编制状态
		//军队1
		returnCityArmy(cityMilitary.getArmy1(),cityID);
		//军队2
		returnCityArmy(cityMilitary.getArmy2(),cityID);
		//军队3
		returnCityArmy(cityMilitary.getArmy3(),cityID);
		//军队4
		returnCityArmy(cityMilitary.getArmy4(),cityID);
		//军队5
		returnCityArmy(cityMilitary.getArmy5(),cityID);
		//军队6
		returnCityArmy(cityMilitary.getArmy6(),cityID);
		//军队7
		returnCityArmy(cityMilitary.getArmy7(),cityID);
		//军队8
		returnCityArmy(cityMilitary.getArmy8(),cityID);
		
		//更改英雄的状态
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), 1);
		cityMilitaryDAO.deleteCityMilitaryByID(cityMilitaryID);
	}

	public BattleMilitary getMapMonsterBattleMilitary(Integer mapMonsterID){
		
		BattleMilitary battleMilitary = mapMonsterDAO.getMapMonsterAsBattleMilitaryByID(mapMonsterID);
		
		if(battleMilitary == null){
			throw new GameException("中立军队不存在。");
		}
		
		//初始化军队列表
		battleMilitary.setBattleArmyList(new ArrayList<BattleArmy>(8));

		String[] army;
		
		//军队1
		if(battleMilitary.getArmy1()!=null){
			BattleArmy battleArmy1 = new BattleArmy();
			army = battleMilitary.getArmy1().split(":");
			battleArmy1.setArmyID(new Integer(army[0]));
			battleArmy1.setAmount(new Integer(army[1]));
			battleArmy1.setArmy(armyService.getClonedArmyByID(battleArmy1.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy1);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队2
		if(battleMilitary.getArmy2()!=null){
			BattleArmy battleArmy2 = new BattleArmy();
			army = battleMilitary.getArmy2().split(":");
			battleArmy2.setArmyID(new Integer(army[0]));
			battleArmy2.setAmount(new Integer(army[1]));
			battleArmy2.setArmy(armyService.getClonedArmyByID(battleArmy2.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy2);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队3
		if(battleMilitary.getArmy3()!=null){
			BattleArmy battleArmy3 = new BattleArmy();
			army = battleMilitary.getArmy3().split(":");
			battleArmy3.setArmyID(new Integer(army[0]));
			battleArmy3.setAmount(new Integer(army[1]));
			battleArmy3.setArmy(armyService.getClonedArmyByID(battleArmy3.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy3);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队4
		if(battleMilitary.getArmy4()!=null){
			BattleArmy battleArmy4 = new BattleArmy();
			army = battleMilitary.getArmy4().split(":");
			battleArmy4.setArmyID(new Integer(army[0]));
			battleArmy4.setAmount(new Integer(army[1]));
			battleArmy4.setArmy(armyService.getClonedArmyByID(battleArmy4.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy4);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队5
		if(battleMilitary.getArmy5()!=null){
			BattleArmy battleArmy5 = new BattleArmy();
			army = battleMilitary.getArmy5().split(":");
			battleArmy5.setArmyID(new Integer(army[0]));
			battleArmy5.setAmount(new Integer(army[1]));
			battleArmy5.setArmy(armyService.getClonedArmyByID(battleArmy5.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy5);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队6
		if(battleMilitary.getArmy6()!=null){
			BattleArmy battleArmy6 = new BattleArmy();
			army = battleMilitary.getArmy6().split(":");
			battleArmy6.setArmyID(new Integer(army[0]));
			battleArmy6.setAmount(new Integer(army[1]));
			battleArmy6.setArmy(armyService.getClonedArmyByID(battleArmy6.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy6);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队7
		if(battleMilitary.getArmy7()!=null){
			BattleArmy battleArmy7 = new BattleArmy();
			army = battleMilitary.getArmy7().split(":");
			battleArmy7.setArmyID(new Integer(army[0]));
			battleArmy7.setAmount(new Integer(army[1]));
			battleArmy7.setArmy(armyService.getClonedArmyByID(battleArmy7.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy7);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队8
		if(battleMilitary.getArmy8()!=null){
			BattleArmy battleArmy8 = new BattleArmy();
			army = battleMilitary.getArmy8().split(":");
			battleArmy8.setArmyID(new Integer(army[0]));
			battleArmy8.setAmount(new Integer(army[1]));
			battleArmy8.setArmy(armyService.getClonedArmyByID(battleArmy8.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy8);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		return battleMilitary;
	}
	
	public MapMonster getMapMonsterByID(Integer mapMonsterID){
		
		MapMonster mapMonster = mapMonsterDAO.getMapMonsterByID(mapMonsterID);
		
		//初始化军队列表
		mapMonster.setBattleArmyList(new ArrayList<BattleArmy>(8));

		String[] army;
		
		//军队1
		if(mapMonster.getArmy1()!=null){
			BattleArmy battleArmy1 = new BattleArmy();
			army = mapMonster.getArmy1().split(":");
			battleArmy1.setArmyID(new Integer(army[0]));
			battleArmy1.setAmount(new Integer(army[1]));
			battleArmy1.setArmy(armyService.getClonedArmyByID(battleArmy1.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy1);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队2
		if(mapMonster.getArmy2()!=null){
			BattleArmy battleArmy2 = new BattleArmy();
			army = mapMonster.getArmy2().split(":");
			battleArmy2.setArmyID(new Integer(army[0]));
			battleArmy2.setAmount(new Integer(army[1]));
			battleArmy2.setArmy(armyService.getClonedArmyByID(battleArmy2.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy2);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队3
		if(mapMonster.getArmy3()!=null){
			BattleArmy battleArmy3 = new BattleArmy();
			army = mapMonster.getArmy3().split(":");
			battleArmy3.setArmyID(new Integer(army[0]));
			battleArmy3.setAmount(new Integer(army[1]));
			battleArmy3.setArmy(armyService.getClonedArmyByID(battleArmy3.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy3);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队4
		if(mapMonster.getArmy4()!=null){
			BattleArmy battleArmy4 = new BattleArmy();
			army = mapMonster.getArmy4().split(":");
			battleArmy4.setArmyID(new Integer(army[0]));
			battleArmy4.setAmount(new Integer(army[1]));
			battleArmy4.setArmy(armyService.getClonedArmyByID(battleArmy4.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy4);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队5
		if(mapMonster.getArmy5()!=null){
			BattleArmy battleArmy5 = new BattleArmy();
			army = mapMonster.getArmy5().split(":");
			battleArmy5.setArmyID(new Integer(army[0]));
			battleArmy5.setAmount(new Integer(army[1]));
			battleArmy5.setArmy(armyService.getClonedArmyByID(battleArmy5.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy5);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队6
		if(mapMonster.getArmy6()!=null){
			BattleArmy battleArmy6 = new BattleArmy();
			army = mapMonster.getArmy6().split(":");
			battleArmy6.setArmyID(new Integer(army[0]));
			battleArmy6.setAmount(new Integer(army[1]));
			battleArmy6.setArmy(armyService.getClonedArmyByID(battleArmy6.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy6);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队7
		if(mapMonster.getArmy7()!=null){
			BattleArmy battleArmy7 = new BattleArmy();
			army = mapMonster.getArmy7().split(":");
			battleArmy7.setArmyID(new Integer(army[0]));
			battleArmy7.setAmount(new Integer(army[1]));
			battleArmy7.setArmy(armyService.getClonedArmyByID(battleArmy7.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy7);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		//军队8
		if(mapMonster.getArmy8()!=null){
			BattleArmy battleArmy8 = new BattleArmy();
			army = mapMonster.getArmy8().split(":");
			battleArmy8.setArmyID(new Integer(army[0]));
			battleArmy8.setAmount(new Integer(army[1]));
			battleArmy8.setArmy(armyService.getClonedArmyByID(battleArmy8.getArmyID()));
			mapMonster.getBattleArmyList().add(battleArmy8);
		}else{
			mapMonster.getBattleArmyList().add(null);
		}
		
		return mapMonster;
	}
	
	@SuppressWarnings("unchecked")
	public BattleMilitary getCityMilitaryBattleMilitary(Integer cityMilitaryID){
		
		BattleMilitary battleMilitary = cityMilitaryDAO.getCityMilitaryAsBattleMilitaryByID(cityMilitaryID);
		battleMilitary.setCityHero(cityHeroDAO.getCityHeroByID(battleMilitary.getCityHeroID()));
		
		//初始化城市信息
		battleMilitary.setCityInfo(cityService.getCityInfoByCityID(battleMilitary.getCityID()));
		 
		//初始化英雄信息
		battleMilitary.setCityHero(cityHeroDAO.getCityHeroByID(battleMilitary.getCityHeroID()));
		//初始化英雄技能列表
		List<HeroSkill> heroSkillList = heroSkillDAO.getHeroSkillListByCityHeroID(battleMilitary.getCityHeroID());
		java.util.Map<Integer, java.util.Map<Integer, Skill>> skillsMap = ((java.util.Map<Integer, java.util.Map<Integer, Skill>>)(CacheService.getFromCache(CacheConstant.SKILLS_MAP)));
		for(int i=0;i<heroSkillList.size();i++){
			heroSkillList.get(i).setSkill(skillsMap.get(heroSkillList.get(i).getSkillID()).get(heroSkillList.get(i).getLevel()));
		}
		battleMilitary.getCityHero().setSkillList(heroSkillList);
		
		//初始化军队列表
		battleMilitary.setBattleArmyList(new ArrayList<BattleArmy>(8));

		String[] army;

		//军队1
		if(battleMilitary.getArmy1()!=null){
			BattleArmy battleArmy1 = new BattleArmy();
			army = battleMilitary.getArmy1().split(":");
			battleArmy1.setArmyID(new Integer(army[0]));
			battleArmy1.setAmount(new Integer(army[1]));
			battleArmy1.setArmy(armyService.getClonedArmyByID(battleArmy1.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy1);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队2
		if(battleMilitary.getArmy2()!=null){
			BattleArmy battleArmy2 = new BattleArmy();
			army = battleMilitary.getArmy2().split(":");
			battleArmy2.setArmyID(new Integer(army[0]));
			battleArmy2.setAmount(new Integer(army[1]));
			battleArmy2.setArmy(armyService.getClonedArmyByID(battleArmy2.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy2);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队3
		if(battleMilitary.getArmy3()!=null){
			BattleArmy battleArmy3 = new BattleArmy();
			army = battleMilitary.getArmy3().split(":");
			battleArmy3.setArmyID(new Integer(army[0]));
			battleArmy3.setAmount(new Integer(army[1]));
			battleArmy3.setArmy(armyService.getClonedArmyByID(battleArmy3.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy3);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队4
		if(battleMilitary.getArmy4()!=null){
			BattleArmy battleArmy4 = new BattleArmy();
			army = battleMilitary.getArmy4().split(":");
			battleArmy4.setArmyID(new Integer(army[0]));
			battleArmy4.setAmount(new Integer(army[1]));
			battleArmy4.setArmy(armyService.getClonedArmyByID(battleArmy4.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy4);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队5
		if(battleMilitary.getArmy5()!=null){
			BattleArmy battleArmy5 = new BattleArmy();
			army = battleMilitary.getArmy5().split(":");
			battleArmy5.setArmyID(new Integer(army[0]));
			battleArmy5.setAmount(new Integer(army[1]));
			battleArmy5.setArmy(armyService.getClonedArmyByID(battleArmy5.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy5);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队6
		if(battleMilitary.getArmy6()!=null){
			BattleArmy battleArmy6 = new BattleArmy();
			army = battleMilitary.getArmy6().split(":");
			battleArmy6.setArmyID(new Integer(army[0]));
			battleArmy6.setAmount(new Integer(army[1]));
			battleArmy6.setArmy(armyService.getClonedArmyByID(battleArmy6.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy6);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队7
		if(battleMilitary.getArmy7()!=null){
			BattleArmy battleArmy7 = new BattleArmy();
			army = battleMilitary.getArmy7().split(":");
			battleArmy7.setArmyID(new Integer(army[0]));
			battleArmy7.setAmount(new Integer(army[1]));
			battleArmy7.setArmy(armyService.getClonedArmyByID(battleArmy7.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy7);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		//军队8
		if(battleMilitary.getArmy8()!=null){
			BattleArmy battleArmy8 = new BattleArmy();
			army = battleMilitary.getArmy8().split(":");
			battleArmy8.setArmyID(new Integer(army[0]));
			battleArmy8.setAmount(new Integer(army[1]));
			battleArmy8.setArmy(armyService.getClonedArmyByID(battleArmy8.getArmyID()));
			battleMilitary.getBattleArmyList().add(battleArmy8);
		}else{
			battleMilitary.getBattleArmyList().add(null);
		}
		
		return battleMilitary;
	}
	
	public Integer getCityMilitaryIDByCityHeroID(Integer cityHeroID) {
		return cityMilitaryDAO.getCityMilitaryIDByCityHeroID(cityHeroID);
	}
	
	public CityMilitary getCityMilitaryByID(Integer cityMilitaryID) {
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary==null){
			return null;
		}
		
		//初始化城市信息
		cityMilitary.setCityInfo(cityService.getCityInfoByCityID(cityMilitary.getCityID()));
		
		//初始化英雄信息
		cityMilitary.setCityHero(cityHeroDAO.getCityHeroByID(cityMilitary.getCityHeroID()));
		
		//初始化军队列表
		cityMilitary.setBattleArmyList(new ArrayList<BattleArmy>(8));

		String[] army;
		
		//军队1
		if(cityMilitary.getArmy1()!=null){
			BattleArmy battleArmy1 = new BattleArmy();
			army = cityMilitary.getArmy1().split(":");
			battleArmy1.setArmyID(new Integer(army[0]));
			battleArmy1.setAmount(new Integer(army[1]));
			battleArmy1.setArmy(armyService.getClonedArmyByID(battleArmy1.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy1);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队2
		if(cityMilitary.getArmy2()!=null){
			BattleArmy battleArmy2 = new BattleArmy();
			army = cityMilitary.getArmy2().split(":");
			battleArmy2.setArmyID(new Integer(army[0]));
			battleArmy2.setAmount(new Integer(army[1]));
			battleArmy2.setArmy(armyService.getClonedArmyByID(battleArmy2.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy2);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队3
		if(cityMilitary.getArmy3()!=null){
			BattleArmy battleArmy3 = new BattleArmy();
			army = cityMilitary.getArmy3().split(":");
			battleArmy3.setArmyID(new Integer(army[0]));
			battleArmy3.setAmount(new Integer(army[1]));
			battleArmy3.setArmy(armyService.getClonedArmyByID(battleArmy3.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy3);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队4
		if(cityMilitary.getArmy4()!=null){
			BattleArmy battleArmy4 = new BattleArmy();
			army = cityMilitary.getArmy4().split(":");
			battleArmy4.setArmyID(new Integer(army[0]));
			battleArmy4.setAmount(new Integer(army[1]));
			battleArmy4.setArmy(armyService.getClonedArmyByID(battleArmy4.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy4);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队5
		if(cityMilitary.getArmy5()!=null){
			BattleArmy battleArmy5 = new BattleArmy();
			army = cityMilitary.getArmy5().split(":");
			battleArmy5.setArmyID(new Integer(army[0]));
			battleArmy5.setAmount(new Integer(army[1]));
			battleArmy5.setArmy(armyService.getClonedArmyByID(battleArmy5.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy5);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队6
		if(cityMilitary.getArmy6()!=null){
			BattleArmy battleArmy6 = new BattleArmy();
			army = cityMilitary.getArmy6().split(":");
			battleArmy6.setArmyID(new Integer(army[0]));
			battleArmy6.setAmount(new Integer(army[1]));
			battleArmy6.setArmy(armyService.getClonedArmyByID(battleArmy6.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy6);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队7
		if(cityMilitary.getArmy7()!=null){
			BattleArmy battleArmy7 = new BattleArmy();
			army = cityMilitary.getArmy7().split(":");
			battleArmy7.setArmyID(new Integer(army[0]));
			battleArmy7.setAmount(new Integer(army[1]));
			battleArmy7.setArmy(armyService.getClonedArmyByID(battleArmy7.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy7);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		//军队8
		if(cityMilitary.getArmy8()!=null){
			BattleArmy battleArmy8 = new BattleArmy();
			army = cityMilitary.getArmy8().split(":");
			battleArmy8.setArmyID(new Integer(army[0]));
			battleArmy8.setAmount(new Integer(army[1]));
			battleArmy8.setArmy(armyService.getClonedArmyByID(battleArmy8.getArmyID()));
			cityMilitary.getBattleArmyList().add(battleArmy8);
		}else{
			cityMilitary.getBattleArmyList().add(null);
		}
		
		return cityMilitary;
	}
	
	public void tuneCityMilitary(Integer cityMilitaryID, String militaryArmyStr) {
		
		CityMilitary cityMilitary = this.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary==null) {
			throw new GameException("军队信息不存在。");
		}
		
		if(cityMilitary.getState() != CityMilitaryStateConstant.NORMAL) {
			throw new GameException("只有空闲状态的军队才可进行编制。");
		}
		
		String army;
		int armyID, armyNum;
		String[] tmp;
		int totalPopulationNum = 0;
		int armyPopulation = 0;
		//城市军队士兵信息Map<士兵编号, 数量>
		java.util.Map<Integer, Integer> cityMilitaryMap = new HashMap<Integer, Integer>();
		
		String[] militaryArmys = militaryArmyStr.split(",", 8);
		if(militaryArmys.length!=8)
			throw new GameException("无效数据。");
		
		//Army1
		army = militaryArmys[0].trim();
		if(army.length()==0){
			cityMilitary.setArmy1(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy1(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army2
		army = militaryArmys[1].trim();
		if(army.length()==0){
			cityMilitary.setArmy2(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy2(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army3
		army = militaryArmys[2].trim();
		if(army.length()==0){
			cityMilitary.setArmy3(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy3(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army4
		army = militaryArmys[3].trim();
		if(army.length()==0){
			cityMilitary.setArmy4(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy4(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army5
		army = militaryArmys[4].trim();
		if(army.length()==0){
			cityMilitary.setArmy5(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy5(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army6
		army = militaryArmys[5].trim();
		if(army.length()==0){
			cityMilitary.setArmy6(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy6(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army7
		army = militaryArmys[6].trim();
		if(army.length()==0){
			cityMilitary.setArmy7(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy7(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		//Army8
		army = militaryArmys[7].trim();
		if(army.length()==0){
			cityMilitary.setArmy8(null);
		}else{
			tmp = army.split(":");
			if(tmp.length!=2) {
				throw new GameException("无效数据。");
			}
			armyID = Integer.parseInt(tmp[0]);
			armyNum = Integer.parseInt(tmp[1]);
			cityMilitary.setArmy8(army);
			
			armyPopulation = armyService.getArmyPopulation(armyID);
			totalPopulationNum += armyPopulation * armyNum;
			
			if(cityMilitaryMap.containsKey(armyID)) {
				cityMilitaryMap.put(armyID, cityMilitaryMap.get(armyID)+armyNum);
			} else {
				cityMilitaryMap.put(armyID, armyNum);
			}
		}
		
		if(cityMilitary.getCityHero().getRein() < totalPopulationNum) {
			throw new GameException("军队人数超过指挥官当前统御值能够带领士兵的人口数。当前军队指挥官统御值：" + cityMilitary.getCityHero().getRein() + "，军队新编制总人数：" + totalPopulationNum + "。");
		}
		
		//数据合法性校验
		int i;
		//原始军队士兵信息Map<士兵编号, 数量>
		java.util.Map<Integer, Integer> originalCityMilitaryMap = new HashMap<Integer, Integer>();
		CityMilitary originalCityMilitary = this.getCityMilitaryByID(cityMilitaryID);
		for (i=0;i<originalCityMilitary.getBattleArmyList().size();i++) {
			if (originalCityMilitary.getBattleArmyList().get(i)!=null) {
				if (!originalCityMilitaryMap.containsKey(originalCityMilitary.getBattleArmyList().get(i).getArmyID())) {
					originalCityMilitaryMap.put(originalCityMilitary.getBattleArmyList().get(i).getArmyID(), originalCityMilitary.getBattleArmyList().get(i).getAmount());
				} else {
					originalCityMilitaryMap.put(originalCityMilitary.getBattleArmyList().get(i).getArmyID(), originalCityMilitaryMap.get(originalCityMilitary.getBattleArmyList().get(i).getArmyID())+originalCityMilitary.getBattleArmyList().get(i).getAmount());
				}
			}
		}
		
		//士兵变化数据比对
		java.util.Map<Integer, Integer> armyMap1 = new HashMap<Integer, Integer>();
		java.util.Map<Integer, Integer> armyMap2 = new HashMap<Integer, Integer>();
		
		//去除当前军队新编制士兵Map副本
		java.util.Map<Integer, Integer> clearCityMilitaryMap = new HashMap<Integer, Integer>(cityMilitaryMap);
		Iterator<Entry<Integer, Integer>> iterator;
		iterator = cityMilitaryMap.entrySet().iterator();
		Entry<Integer, Integer> tempEntry;
		while(iterator.hasNext()) {
			tempEntry = iterator.next();
			//如果原始军队士兵不包含当前士兵，则该士兵为新编制
			if(!originalCityMilitaryMap.containsKey(tempEntry.getKey())) {
				armyMap1.put(tempEntry.getKey(), tempEntry.getValue());
				clearCityMilitaryMap.remove(tempEntry.getKey());
			}
		}
		
		iterator = originalCityMilitaryMap.entrySet().iterator();
		while(iterator.hasNext()) {
			tempEntry = iterator.next();
			//如果当前军队士兵不包含原始军队士兵，则该士兵被分配回空闲士兵
			if(!cityMilitaryMap.containsKey(tempEntry.getKey())) {
				armyMap2.put(tempEntry.getKey(), tempEntry.getValue());
			}
		}
		
		int[] cityArmyIDs = new int[8];
		int[] cityArmyNums = new int[8];
		int k = 0;
		CityArmy cityArmy;
		//仅数量改变士兵
		//cityMilitaryMap.remove(key)
		int changedNum;
		iterator = clearCityMilitaryMap.entrySet().iterator();
		while(iterator.hasNext()) {
			tempEntry = iterator.next();
			changedNum = clearCityMilitaryMap.get(tempEntry.getKey())-originalCityMilitaryMap.get(tempEntry.getKey());
			if(changedNum!=0) {
				cityArmy = armyService.getCityArmy(cityMilitary.getCityID(), tempEntry.getKey());
				cityArmyIDs[k] = tempEntry.getKey();
				cityArmyNums[k] = cityArmy.getNum()-changedNum;
				k++;
			}
		}
		
		if(armyMap1.size()>0) {
			iterator = armyMap1.entrySet().iterator();
			while(iterator.hasNext()) {
				tempEntry = iterator.next();
				cityArmy = armyService.getCityArmy(cityMilitary.getCityID(), tempEntry.getKey());
				cityArmyIDs[k] = tempEntry.getKey();
				cityArmyNums[k] = cityArmy.getNum()-tempEntry.getValue();
				k++;
			}
		}
		
		if(armyMap2.size()>0) {
			iterator = armyMap2.entrySet().iterator();
			while(iterator.hasNext()) {
				tempEntry = iterator.next();
				cityArmy = armyService.getCityArmy(cityMilitary.getCityID(), tempEntry.getKey());
				cityArmyIDs[k] = tempEntry.getKey();
				cityArmyNums[k] = cityArmy.getNum()+tempEntry.getValue();
				k++;
			}
		}
		
		int[] validCityArmyIDs = new int[k];
		int[] validCityArmyNums = new int[k];
		for(i=0;i<k;i++) {
			validCityArmyIDs[i] = cityArmyIDs[i];
			if(cityArmyNums[i]<0) {
				throw new GameException("空闲士兵不足。");
			} else {
				validCityArmyNums[i] = cityArmyNums[i];
			}
		}
		
		//更新城市军队信息
		armyService.batchUpdateCityArmyNumByCityIDAndArmyIDs(cityMilitary.getCityID(), validCityArmyIDs, validCityArmyNums);
		
		int[] resourceConsume = this.getConsumeOfCityMilitary(
				cityMilitary.getArmy1(), 
				cityMilitary.getArmy2(), 
				cityMilitary.getArmy3(), 
				cityMilitary.getArmy4(), 
				cityMilitary.getArmy5(), 
				cityMilitary.getArmy6(), 
				cityMilitary.getArmy7(), 
				cityMilitary.getArmy8()
				);
		
		cityMilitary.setCostFood(resourceConsume[0]);
		cityMilitary.setCostOil(resourceConsume[1]);
		cityMilitary.setCostMoney(resourceConsume[2]);
		
		//更新军队士兵信息
		cityMilitaryDAO.updateCityMilitary(cityMilitary);
		
	}
	
	public List<CityMilitary> getCityMilitaryList(Integer cityID) {
		return cityMilitaryDAO.getCityMilitaryListByCityID(cityID);
	}

	public List<CityArmy> getCityArmyList(Integer cityID){
		return armyService.getCityArmyList(cityID);
	}
	
    public List<java.util.Map<String, Object>> getMilitaryActionList(Integer cityID){
    	
    	List<java.util.Map<String, Object>> militaryActionList = militaryDAO.getMilitaryActionList(cityID);
    	
    	for(int i=0;i<militaryActionList.size();i++) {
    		militaryActionList.get(i).put("map", mapService.getMapByPos((Integer)militaryActionList.get(i).get("posX"), (Integer)militaryActionList.get(i).get("posY")));
    	}
    	
    	return militaryActionList;
    }
    
    public List<DepoyQueue> getMilitaryDefenseList(Integer cityID) {
    	
    	List<DepoyQueue> depoyQueueList = militaryDAO.getMilitaryDefenseList(cityID);
    	for(int i=0;i<depoyQueueList.size();i++){
    		depoyQueueList.get(i).setCityMilitary(cityMilitaryDAO.getCityMilitaryByID(depoyQueueList.get(i).getCityMilitaryID()));
    		depoyQueueList.get(i).getCityMilitary().setCityInfo(cityService.getCityInfoByCityID(depoyQueueList.get(i).getCityMilitary().getCityID()));
    		//初始化英雄信息
    		//cityMilitary.setCityHero(heroService.getCityHero(cityMilitary.getCityHeroID()));
    		depoyQueueList.get(i).setMap(mapService.getMapByID(depoyQueueList.get(i).getMapID()));
    	}
    	
    	return depoyQueueList;
    }
    
    public SpyQueue getSpyDetail(Integer spyQueueID){
    	return spyQueueService.getSpyQueueByID(spyQueueID);
    }
    
    public java.util.Map<String, Object> getAttackDetail(Integer depoyQueueID){
    	return militaryDAO.getAttackDetail(depoyQueueID);
    }
    
    public void accelerateMilitaryRetruning(Integer depoyQueueID){
    	
    }
    
	public void finishAttackWait(DepoyQueue depoyQueue){
		
		try {
			// 保证同步
			finishAttackWaitLock.lock();
			
			if(depoyQueueService.getDepoyQueueByID(depoyQueue.getDepoyQueueID()) == null){
				return;
			}
			
			Integer battleQueueID = battleQueueDAO.getBattleQueueIDByCityMilitaryID(depoyQueue.getCityMilitaryID());
			if (battleQueueID != null) {
				battleQueueDAO.deleteBattleQueueByID(battleQueueID);
				// return;
			}
			
			depoyQueueService.deleteDepoyQueueByID(depoyQueue.getDepoyQueueID());
			
			Map map = mapService.getMapByID(depoyQueue.getMapID());
			
			// 军队到达时，地图已经是空地。
			if (map.getCategory() == MapConstant.CATEGORY_BLANK_FIELD) {
				
				//创建返回军队队列
				BattleMilitary militaryAttacker = this.getCityMilitaryBattleMilitary(depoyQueue.getCityMilitaryID());
				
				DepoyQueue returnDepoyQueue = new DepoyQueue();
				returnDepoyQueue.setCityID(militaryAttacker.getCityID());
				returnDepoyQueue.setCityMilitaryID(militaryAttacker.getCityMilitaryID());
				java.util.Map<String,Integer> posMap = cityService.getCityPosByCityID(militaryAttacker.getCityID());
				returnDepoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
				returnDepoyQueue.setType(DepoyTypeConstant.RETURN);
				
				int minSpeed = this.getMilitarySpeed(militaryAttacker);
				Date date = new Date();
				date.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(map.getPosX(), map.getPosY(), posMap.get("posX"), posMap.get("posY"), minSpeed)*1000);
				returnDepoyQueue.setFinishTime(date);
				depoyQueueService.createDepoyQueue(returnDepoyQueue);
				
				reportService.sendMilitaryReport(militaryAttacker.getPlayerID(), "军队返回报告", "您所前往的目的地" + map.getName() + "已无任何可攻击目标，现已从目的地返回。");
			  
				return;
			}

			// 创建战争等待队列
			BattleQueue battleQueue = new BattleQueue();
			
			if( map.getState() == MapConstant.STATE_FIGHTING ) {
				// 该地图正在战斗
				
				battleQueue.setMapID(map.getMapID());
				battleQueue.setCityMilitaryID(depoyQueue.getCityMilitaryID());
				battleQueue.setOrder(battleQueueDAO.getBattleQueueNumByMapID(map.getMapID()) + 1);
				
				battleQueueDAO.createBattleQueue(battleQueue);
				
				// 更新军队状态
				cityMilitaryDAO.updateCityMilitaryState(depoyQueue.getCityMilitaryID(), CityMilitaryStateConstant.WAITING);
				
				return ;
				
			} else {
				battleQueue.setMapID(map.getMapID());
				battleQueue.setCityMilitaryID(depoyQueue.getCityMilitaryID());
				battleQueue.setOrder(1);
				
				cityMilitaryDAO.updateCityMilitaryState(depoyQueue.getCityMilitaryID(), CityMilitaryStateConstant.MARCH);
				
				battleQueueDAO.createBattleQueue(battleQueue);
			}
			
			this.initBattleInfo(depoyQueue.getCityMilitaryID(), depoyQueue.getMapID());
				
		} catch (Exception e) {
			logger.error("异常：", e);
			
		} finally {
			finishAttackWaitLock.unlock();
		}
		
	}
	
	/**
	 * 初始化战斗信息
	 * @param attackerCityMilitaryID
	 * @param mapID
	 */
	private void initBattleInfo(Integer attackerCityMilitaryID, Integer mapID) {
		
		try {
			Map map = mapService.getMapByID(mapID);
			
			if(map.getCategory() == MapConstant.CATEGORY_CITY){
			// 攻击城市
				
				if (cityService.getCityByID(map.getTargetID()) == null) {
					// 创建返回军队队列
					CityMilitary militaryAttacker = this.getCityMilitaryByID(attackerCityMilitaryID);
					
					DepoyQueue returnDepoyQueue = new DepoyQueue();
					returnDepoyQueue.setCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setCityMilitaryID(militaryAttacker.getCityMilitaryID());
					java.util.Map<String,Integer> posMap = cityService.getCityPosByCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
					returnDepoyQueue.setType(DepoyTypeConstant.RETURN);
					
					int minSpeed = this.getMilitarySpeed(militaryAttacker);
					Date date = new Date();
					date.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(map.getPosX(), map.getPosY(), posMap.get("posX"), posMap.get("posY"), minSpeed)*1000);
					returnDepoyQueue.setFinishTime(date);
					depoyQueueService.createDepoyQueue(returnDepoyQueue);
					
					reportService.sendMilitaryReport(cityService.getPlayerIDByCityID(militaryAttacker.getCityID()), "军队返回报告", "您所前往的目的地" + map.getName() + "可能已经转移，现已从目的地返回。");
					
					return;
				}
				
				Battle battle = new Battle();
				
				// 设置进攻方军队信息
				battle.setMilitaryAttackerID(attackerCityMilitaryID);
				// 设置防守方军队信息
				CityInfo defenderCityInfo = cityService.getCityInfoByCityID(map.getTargetID());
				
				CityMilitary defenderMilitary = null;
				
				List<CityMilitarySuccor> cityMilitarySuccorList = null;
				
				CityMilitary cityDefensiveMilitary = this.getCityMilitaryByID(defenderCityInfo.getDefensiveMilitary());	
				
				if (cityDefensiveMilitary == null ||
					(cityDefensiveMilitary.getArmy1()==null && cityDefensiveMilitary.getArmy2()==null && 
					cityDefensiveMilitary.getArmy3()==null && cityDefensiveMilitary.getArmy4()==null &&
					cityDefensiveMilitary.getArmy5()==null && cityDefensiveMilitary.getArmy6()==null &&
					cityDefensiveMilitary.getArmy7()==null && cityDefensiveMilitary.getArmy8()==null)) {
					
					cityMilitarySuccorList = cityMilitarySuccorDAO.getCityMilitarySuccorActiveListByTargetCityIDOrderByBattleOrder(defenderCityInfo.getCityID());
					if (cityMilitarySuccorList != null && !cityMilitarySuccorList.isEmpty()) {
						defenderMilitary = this.getCityMilitaryByID(cityMilitarySuccorList.get(0).getCityMilitaryID());
						battle.setMilitaryDefenderID(cityMilitarySuccorList.get(0).getCityMilitaryID());
					}
					
				} else {
					defenderMilitary = cityDefensiveMilitary ;
					battle.setMilitaryDefenderID(defenderCityInfo.getDefensiveMilitary());
				}
				
				// 设置军队信息
				battle.setMilitaryAttacker(this.getCityMilitaryBattleMilitary(battle.getMilitaryAttackerID()));
				
				// 城市未设置留守军队以及支援部队，则进攻方胜利，军队遣返
				if(defenderMilitary == null ||
						(defenderMilitary.getArmy1()==null && defenderMilitary.getArmy2()==null && 
						defenderMilitary.getArmy3()==null && defenderMilitary.getArmy4()==null &&
						defenderMilitary.getArmy5()==null && defenderMilitary.getArmy6()==null &&
						defenderMilitary.getArmy7()==null && defenderMilitary.getArmy8()==null)) {
					
					CityMilitary militaryAttacker = this.getCityMilitaryByID(attackerCityMilitaryID);
					
					// 创建返回军队队列
					DepoyQueue returnDepoyQueue = new DepoyQueue();
					returnDepoyQueue.setCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setCityMilitaryID(militaryAttacker.getCityMilitaryID());
					java.util.Map<String,Integer> posMap = cityService.getCityPosByCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
					returnDepoyQueue.setType(DepoyTypeConstant.RETURN);
					
					int minSpeed = this.getMilitarySpeed(militaryAttacker);
					Date date = new Date();
					date.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(map.getPosX(), map.getPosY(), posMap.get("posX"), posMap.get("posY"), minSpeed)*1000);
					returnDepoyQueue.setFinishTime(date);
					
					// 掠夺资源
					long carryTotal = 0L;
					for(int i=0;i<8;i++){
						if(battle.getMilitaryAttacker().getBattleArmyList().get(i)!=null){
							carryTotal += battle.getMilitaryAttacker().getBattleArmyList().get(i).getAmount() * battle.getMilitaryAttacker().getBattleArmyList().get(i).getArmy().getCarry();
						}
					}
					
					long maxCarryResourceNum = carryTotal/5;
					
					// 资源保护
					long cityResourceMaxNum = cityService.getCityResourceByCityID(defenderCityInfo.getCityID()).getResourceNumMax();
					double resourceProtectPercent = cityService.getCityExt(defenderCityInfo.getCityID()).getTechProtectResourcePercent() / 100.0 ;
					long resourceProtectNum = (long)resourceProtectPercent * cityResourceMaxNum;
					
					long getWoodNum = 0;
					long getSteelNum = 0;
					long getOilNum = 0;
					long getFoodNum = 0;
					long getMoneyNum = 0;
					
					if (maxCarryResourceNum > resourceProtectNum) {
						
						java.util.Map<String,Long> cityResources = cityService.getCityResourcesNum(defenderCityInfo.getCityID());
						long cityWoodNum = cityResources.get("woodNum");
						long citySteelNum = cityResources.get("steelNum");
						long cityOilNum = cityResources.get("oilNum");
						long cityFoodNum = cityResources.get("foodNum");
						long cityMoneyNum = cityResources.get("moneyNum");
					
						// 城市当前资源不足
						if(maxCarryResourceNum>cityWoodNum || maxCarryResourceNum>citySteelNum || maxCarryResourceNum>cityOilNum || maxCarryResourceNum>cityFoodNum || maxCarryResourceNum>cityMoneyNum){
							// 进攻方胜利，取消防守城市所有挂单资源
							marketService.cancelCityAllResourceSales(defenderCityInfo.getCityID());
							cityResources = cityService.getCityResourcesNum(defenderCityInfo.getCityID());
							
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
						cityService.minusCityResourcesClear(defenderCityInfo.getCityID(), getWoodNum, getSteelNum, getOilNum, getFoodNum, getMoneyNum);

					}
					
					// 设置军队携带资源
					StringBuffer remark = new StringBuffer();
					JSONObject carry = new JSONObject();
					try {
						carry.put("wood", getWoodNum);
						carry.put("steel", getSteelNum);
						carry.put("oil", getOilNum);
						carry.put("food", getFoodNum);
						carry.put("money", getMoneyNum);
					} catch (JSONException e1) {
						logger.error("异常：", e1);
					}
					remark.append(carry.toString());
					
					returnDepoyQueue.setRemark(remark.toString());
					depoyQueueService.createDepoyQueue(returnDepoyQueue);
					
					Player attackerPlayer = playerService.getPlayerByID(cityService.getPlayerIDByCityID(militaryAttacker.getCityID()));
					Player defenderPlayer = playerService.getPlayerByID(cityService.getPlayerIDByCityID(map.getTargetID()));
					
					// 发送报告
					java.util.Map<String,Object> reportParams = new HashMap<String,Object>();
					reportParams.put("woodNum", getWoodNum);
					reportParams.put("steelNum", getSteelNum);
					reportParams.put("oilNum", getOilNum);
					reportParams.put("foodNum", getFoodNum);
					reportParams.put("moneyNum", getMoneyNum);
					String reportAttackerContent = null;
					String reportDefenderContent = null;
					
					// 计算防守城市治安并且处理殖民
					int defenderSecurity = cityService.getCityTaxAndSecurity(map.getTargetID()).get("security");
					
					int minusSecurity = (int)(defenderPlayer.getRenown()/attackerPlayer.getRenown())*5;
					
					// 减少治安最小为1，最大为10
					if(minusSecurity<1){
						minusSecurity = 1;
					}else if(minusSecurity>10){
						minusSecurity = 10;
					}
					
					if(minusSecurity > defenderSecurity){
						minusSecurity = defenderSecurity;
					}
					defenderSecurity = defenderSecurity - minusSecurity;
					
					// 更新防守城市治安
					java.util.Map<String,Object> defenderCityParams = new HashMap<String,Object>();
					defenderCityParams.put("cityID", map.getTargetID());
					defenderCityParams.put("security", defenderSecurity);
					cityService.updateCity(defenderCityParams);
					
					// 殖民
					if(defenderSecurity < 20){
						if(!colonizationService.canCityColonize(battle.getMilitaryAttacker().getCityID())){
							// 无法殖民
							reportParams.put("haveColonized", 3);
						}else{
							if(colonizationService.haveColonized(battle.getMilitaryAttacker().getCityID(), defenderCityInfo.getCityID())){
								// 已经殖民该城市
								reportParams.put("haveColonized", 2);
							}else{
								colonizationService.addColonization(battle.getMilitaryAttacker().getCityID(), defenderCityInfo.getCityID());
								reportParams.put("haveColonized", 1);
							}
						}
					}else{
						reportParams.put("haveColonized", 0);
					}
					
					reportParams.put("minusSecurity", minusSecurity);
					
					try {
						reportAttackerContent = TemplateService.format("BattleReport_NoDefensiveMilitary_Attacker.ftl", reportParams);
						reportDefenderContent = TemplateService.format("BattleReport_NoDefensiveMilitary_Defender.ftl", reportParams);
					} catch (Exception e) {
						logger.error("异常：", e);
					}

					String title = "发生在" + map.getName() + "(" + map.getPosX() + "," + map.getPosY() + ")的战斗报告";
					reportService.sendMilitaryReport(cityService.getPlayerIDByCityID(militaryAttacker.getCityID()), title, reportAttackerContent);
					reportService.sendMilitaryReport(defenderCityInfo.getPlayerID(), title, reportDefenderContent);
					
					String attackerCityName = cityService.getCityNameByCityID(militaryAttacker.getCityID());
					
					Integer attackerGuildID = attackerPlayer.getGuildID();
					Integer defenderGuildID = defenderPlayer.getGuildID();
					
					// 添加进攻方军团攻击历史
					if(attackerGuildID!=null){
						guildService.addGuildAttack(attackerGuildID, defenderGuildID, attackerCityName + " 攻击 " + map.getName(), 1);
					}
					// 添加防守方军团防御历史
					if(defenderGuildID!=null){
						guildService.addGuildAttack(defenderGuildID, attackerGuildID, map.getName() + " 防御 " + attackerCityName, 2);
					}
					
					// 删除战斗队列
					List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID()); 
					
					if (battleQueueList!=null && battleQueueList.size() > 1) {
						
						// 删除此地图对应的所有战斗队列信息
						battleQueueDAO.deleteBattleQueueByMapID(map.getMapID());
						
						int[] cityMilitaryIDs = new int[battleQueueList.size() - 1];
						for (int i = 1; i < battleQueueList.size(); i++) {
							cityMilitaryIDs[i - 1] = battleQueueList.get(i).getCityMilitaryID();
						}
						
						for (int i = 0; i < cityMilitaryIDs.length; i++) {
							cityMilitaryDAO.updateCityMilitaryState(cityMilitaryIDs[i], CityMilitaryStateConstant.MARCH);
							this.nextAttackerAttack(cityMilitaryIDs[i], map.getMapID());
						}
					}
					
					return;
					
				}
				
				battle.setAttackerExp(0L);
				battle.setDefenderExp(0L);
				
				// 初始化战斗城防数量
				List<CityDefense> cityDefenseList = cityDefenseService.getCityDefenseList(defenderCityInfo.getCityID());
				if(cityDefenseList.size()!=0){
					Integer[] cityDefenseAmountArray = new Integer[7];
					for(int i=0;i<cityDefenseList.size();i++){
						switch(cityDefenseList.get(i).getDefenseID()){
							case DefenseConstant.FENCE:
								//围墙
								cityDefenseAmountArray[0] = cityDefenseList.get(i).getNum();
								break;
							case DefenseConstant.BUNKER:
								//碉堡
								cityDefenseAmountArray[1] = (int)Math.ceil(((float)cityDefenseList.get(i).getNum())/2);
								cityDefenseAmountArray[2] = (int)Math.floor(((float)cityDefenseList.get(i).getNum())/2);
								break;
							case DefenseConstant.GUN:
								//火炮
								cityDefenseAmountArray[3] = (int)Math.ceil(((float)cityDefenseList.get(i).getNum())/2);
								cityDefenseAmountArray[4] = (int)Math.floor(((float)cityDefenseList.get(i).getNum())/2);
								break;
							case DefenseConstant.ANTIGUN:
								//防空炮
								cityDefenseAmountArray[5] = (int)Math.ceil(((float)cityDefenseList.get(i).getNum())/2);
								cityDefenseAmountArray[6] = (int)Math.floor(((float)cityDefenseList.get(i).getNum())/2);
								break;
						}
					}
					StringBuffer cityDefenseAmountBuffer = new StringBuffer();
					for(int j=0;j<cityDefenseAmountArray.length;j++){
						if(cityDefenseAmountArray[j] == null){
							cityDefenseAmountArray[j] = 0;
						}
						cityDefenseAmountBuffer.append(cityDefenseAmountArray[j]);
						if(j!=cityDefenseAmountArray.length-1){
							cityDefenseAmountBuffer.append(",");
						}
					}
					battle.setCityDefenseAmount(cityDefenseAmountBuffer.toString());
				}else{
					battle.setCityDefenseAmount("0,0,0,0,0,0,0");
				}
				
				battle.setStagePosX(map.getPosX());
				battle.setStagePosY(map.getPosY());
				battle.setRound(1);
				battle.setStartTime(DateService.getCurrentUtilDate());
				battle.setPreRoundFinishTime(DateService.getCurrentUtilDate());
				battle.setType(2);
				
				//创建战斗
				battleDAO.createBattle(battle);
				
				JSONObject json = new JSONObject();
				try {
					json.put("type", 5);
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				
				//向客户端push战斗信息
				GameSocketService.sendDataToClient(this.getCityMilitaryByID(battle.getMilitaryAttackerID()).getCityInfo().getPlayerID(), json);
				GameSocketService.sendDataToClient(this.getCityMilitaryByID(battle.getMilitaryDefenderID()).getCityInfo().getPlayerID(), json);
				
			} else if (map.getCategory() == MapConstant.CATEGORY_MONSTER) {
				//攻击野怪
				
				if (mapMonsterDAO.getMapMonsterByID(map.getTargetID()) == null) {
					//创建返回军队队列
					BattleMilitary militaryAttacker = this.getCityMilitaryBattleMilitary(attackerCityMilitaryID);
					
					DepoyQueue returnDepoyQueue = new DepoyQueue();
					returnDepoyQueue.setCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setCityMilitaryID(militaryAttacker.getCityMilitaryID());
					java.util.Map<String,Integer> posMap = cityService.getCityPosByCityID(militaryAttacker.getCityID());
					returnDepoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
					returnDepoyQueue.setType(DepoyTypeConstant.RETURN);
					
					int minSpeed = this.getMilitarySpeed(militaryAttacker);
					Date date = new Date();
					date.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(map.getPosX(), map.getPosY(), posMap.get("posX"), posMap.get("posY"), minSpeed)*1000);
					returnDepoyQueue.setFinishTime(date);
					depoyQueueService.createDepoyQueue(returnDepoyQueue);
					
					reportService.sendMilitaryReport(militaryAttacker.getPlayerID(), "军队返回报告", "您所前往的目的地" + map.getName() + "已无任何可攻击目标，现已从目的地返回。");
				  
					// 删除战斗队列
					List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueListByMapIDOrderByOrder(map.getMapID()); 
					
					if (battleQueueList!=null && battleQueueList.size() > 1) {
						
						// 删除此地图对应的所有战斗队列信息
						battleQueueDAO.deleteBattleQueueByMapID(map.getMapID());
						
						int[] cityMilitaryIDs = new int[battleQueueList.size() - 1];
						for (int i = 1; i < battleQueueList.size(); i++) {
							cityMilitaryIDs[i - 1] = battleQueueList.get(i).getCityMilitaryID();
						}
						
						for (int i = 0; i < cityMilitaryIDs.length; i++) {
							cityMilitaryDAO.updateCityMilitaryState(cityMilitaryIDs[i], CityMilitaryStateConstant.MARCH);
							this.nextAttackerAttack(cityMilitaryIDs[i], map.getMapID());
						}
					}
					
					return;
				}
				
				Battle battle = new Battle();
				
				// 设置进攻方军队信息
				battle.setMilitaryAttackerID(attackerCityMilitaryID);
				// 设置防守方军队信息
				battle.setMilitaryDefenderID(map.getTargetID());
				
				battle.setAttackerExp(0L);
				battle.setDefenderExp(0L);
				
				battle.setStagePosX(map.getPosX());
				battle.setStagePosY(map.getPosY());
				battle.setRound(1);
				battle.setStartTime(DateService.getCurrentUtilDate());
				battle.setPreRoundFinishTime(DateService.getCurrentUtilDate());
				battle.setType(1);
				
				// 创建战斗
				battleDAO.createBattle(battle);
				
				JSONObject json = new JSONObject();
				try {
					json.put("type", 5);
				} catch (JSONException e) {
					logger.error("异常：", e);
				}
				// 向客户端push战斗信息
				GameSocketService.sendDataToClient(this.getCityMilitaryByID(battle.getMilitaryAttackerID()).getCityInfo().getPlayerID(), json);
			}
			
			// 更新地图状态为正在战斗
			map.setState(MapConstant.STATE_FIGHTING);
			mapService.updateMap(map);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
	}
	
	public void finishDispatchWait(DepoyQueue depoyQueue){

		try{
			// 保证同步
			finishDispatchWaitLock.lock();
			depoyQueueService.deleteDepoyQueueByID(depoyQueue.getDepoyQueueID());
			
			Map map = mapService.getMapByID(depoyQueue.getMapID());
			
			// 向城市派遣，城市已转移
			if(map.getCategory() == MapConstant.CATEGORY_CITY){
				
				if(cityService.getCityByID(map.getTargetID()) == null){
					// 创建返回军队队列
					CityMilitary dispatchMilitary = this.getCityMilitaryByID(depoyQueue.getCityMilitaryID());
					
					DepoyQueue returnDepoyQueue = new DepoyQueue();
					returnDepoyQueue.setCityID(dispatchMilitary.getCityID());
					returnDepoyQueue.setCityMilitaryID(dispatchMilitary.getCityMilitaryID());
					java.util.Map<String,Integer> posMap = cityService.getCityPosByCityID(dispatchMilitary.getCityID());
					returnDepoyQueue.setMapID(mapService.getMapByPos(posMap.get("posX"), posMap.get("posY")).getMapID());
					returnDepoyQueue.setType(DepoyTypeConstant.RETURN);
					
					int minSpeed = this.getMilitarySpeed(dispatchMilitary);
					Date date = new Date();
					date.setTime(System.currentTimeMillis() + CostTimeCalculateUtil.calculateMilitaryCostTime(map.getPosX(), map.getPosY(), posMap.get("posX"), posMap.get("posY"), minSpeed)*1000);
					returnDepoyQueue.setFinishTime(date);
					depoyQueueService.createDepoyQueue(returnDepoyQueue);
					reportService.sendMilitaryReport(cityService.getPlayerIDByCityID(dispatchMilitary.getCityID()), "军队返回报告", "您所前往的目的地" + map.getName() + "可能已经转移，现已从目的地返回。");
					return;
				}
			}
			
			CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(depoyQueue.getCityMilitaryID());
			
			if (depoyQueue.getType() == DepoyTypeConstant.DISPATCH) {
				CityResource dispatchCityResource = cityService.getCityResourceByCityID(depoyQueue.getCityID());
				CityResource targetCityResource = cityService.getCityResourceByCityID(cityService.getCityIDByCityPos(map.getPosX(), map.getPosY()));
				
				// 添加支援军队信息到被支援城市
				CityMilitarySuccor cityMilitarySuccor = new CityMilitarySuccor();
				cityMilitarySuccor.setBattleOrder(cityMilitarySuccorDAO.getCityMilitarySuccorNumByTargetCityID(targetCityResource.getCityID()) + 1);
				cityMilitarySuccor.setCityID(targetCityResource.getCityID());
				cityMilitarySuccor.setCityMilitaryID(depoyQueue.getCityMilitaryID());
				cityMilitarySuccor.setTargetCityID(targetCityResource.getCityID());
				cityMilitarySuccor.setArriveTime(new Date(System.currentTimeMillis()));
				// 减去支援城市的消耗
				cityService.updateCityResourceConsumeByCityID(depoyQueue.getCityID(), dispatchCityResource.getOilConsume() - cityMilitary.getCostOil(), dispatchCityResource.getFoodConsume() - cityMilitary.getCostFood(), dispatchCityResource.getMoneyConsume() - cityMilitary.getCostMoney());
				// 加上被支援城市的消耗
				cityService.updateCityResourceConsumeByCityID(depoyQueue.getCityID(), targetCityResource.getOilConsume() + cityMilitary.getCostOil(), targetCityResource.getFoodConsume() + cityMilitary.getCostFood(), targetCityResource.getMoneyConsume() + cityMilitary.getCostMoney());
				// 更新军队以及指挥官状态
				cityMilitaryDAO.updateCityMilitaryState(depoyQueue.getCityMilitaryID(), CityMilitaryStateConstant.RESIDE);
				cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.RESIDE);
				
				
			} else if (depoyQueue.getType() == DepoyTypeConstant.RETURN) {
				cityMilitaryDAO.updateCityMilitaryState(depoyQueue.getCityMilitaryID(), CityMilitaryStateConstant.NORMAL);
				cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.FREE);
			}
			
			// 删除派遣队列
			depoyQueueService.deleteDepoyQueueByID(depoyQueue.getDepoyQueueID());
			
		} catch (Exception e) {
			logger.error("异常：", e);
		} finally {
			finishDispatchWaitLock.unlock();
		}
		
	}
	
	public void finishReturnWait(DepoyQueue depoyQueue){

		try {
			//保证同步
			finishReturnWaitLock.lock();
			depoyQueueService.deleteDepoyQueueByID(depoyQueue.getDepoyQueueID());
		
			CityMilitary cityMilitary = this.getCityMilitaryByID(depoyQueue.getCityMilitaryID());
			
			if (cityMilitary == null) {
				logger.error("军队返回，无对应军队信息。 DepoyQueueID:" + depoyQueue.getDepoyQueueID() + " CityID:" + depoyQueue.getCityID() + " CityMilitaryID:" + depoyQueue.getCityMilitaryID() + " MapID:" + depoyQueue.getMapID());
				return;
			}
			
			cityMilitaryDAO.updateCityMilitaryState(depoyQueue.getCityMilitaryID(), CityMilitaryStateConstant.NORMAL);
			cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.FREE);
			
			//向玩家发送报告
			Integer playerID = cityService.getPlayerIDByCityID(depoyQueue.getCityID());
			java.util.Map<String,Object> reportParams = new HashMap<String,Object>();
			reportParams.put("military", cityMilitary);
			
			if (depoyQueue.getRemark() != null) { 
				
				JSONObject attackerResultJSON = new JSONObject(depoyQueue.getRemark());
				
				// 添加城市伤兵信息
				if (attackerResultJSON.has("woundedArmyTypes") && !attackerResultJSON.getString("woundedArmyTypes").equals("")){
					for(String s : attackerResultJSON.getString("woundedArmyTypes").split(";")){
						CityWoundedArmy cityWoundedArmy = new CityWoundedArmy();
						cityWoundedArmy.setCityID(depoyQueue.getCityID());
						cityWoundedArmy.setArmyID(Integer.parseInt(s.trim()));
						cityWoundedArmy.setNum(attackerResultJSON.getInt(s.trim()));
						
						armyService.createCityWoundedArmy(cityWoundedArmy);
					}
				}
				
				// 是否携带宝物
				if (attackerResultJSON.has("PTs")) {
					String playerTreasureInfo = null;
					for (String key : attackerResultJSON.getString("PTs").split(";")) {
						playerTreasureInfo = attackerResultJSON.getString(key);
						treasureService.increasePlayerTreasure(playerID, Integer.parseInt(playerTreasureInfo.split(";")[0]), Integer.parseInt(playerTreasureInfo.split(";")[1]));
					}
				}
				
				// 是否携带装备
				if (attackerResultJSON.has("Es")) {
					PlayerEquipment playerEquipment = null;
					for (String key : attackerResultJSON.getString("Es").split(";")) {
						
						playerEquipment = new PlayerEquipment();
						playerEquipment.setPlayerID(playerID);
						playerEquipment.setEquipmentID(attackerResultJSON.getInt(key));
						
						equipmentService.addPlayerEquipment(playerEquipment);
					}
				}
				
				// 是否携带任务物品
				if (attackerResultJSON.has("Ts")) {
					String TaskItemInfo = null;
					for (String key : attackerResultJSON.getString("Ts").split(";")) {
						TaskItemInfo = attackerResultJSON.getString(key);
						treasureService.increasePlayerTreasure(playerID, Integer.parseInt(TaskItemInfo.split(";")[0]), Integer.parseInt(TaskItemInfo.split(";")[1]));
					}
				}
				
				// 是否携带资源
				if(attackerResultJSON.has("food") || attackerResultJSON.has("wood") || attackerResultJSON.has("steel") || attackerResultJSON.has("oil") || attackerResultJSON.has("money") ) {
					
					// 添加城市资源
					CityResource cityResource = cityService.getCityResourceByCityID(depoyQueue.getCityID());
					java.util.Map<String,Object> cityParams = new HashMap<String,Object>();
					cityParams.put("cityID", depoyQueue.getCityID());
					cityParams.put("woodNum", Math.min(cityResource.getWoodNum()+attackerResultJSON.optLong("wood"), cityResource.getResourceNumMax()));
					cityParams.put("steelNum", Math.min(cityResource.getSteelNum()+attackerResultJSON.optLong("steel"), cityResource.getResourceNumMax()));
					cityParams.put("oilNum", Math.min(cityResource.getOilNum()+attackerResultJSON.optLong("oil"), cityResource.getResourceNumMax()));
					cityParams.put("foodNum", Math.min(cityResource.getFoodNum()+attackerResultJSON.optLong("food"), cityResource.getResourceNumMax()));
					cityParams.put("moneyNum", cityResource.getMoneyNum()+attackerResultJSON.optLong("money"));
					cityService.updateCityResource(cityParams);
					
					reportParams.put("haveResource", true);
					reportParams.put("woodNum", attackerResultJSON.optLong("wood"));
					reportParams.put("steelNum", attackerResultJSON.optLong("steel"));
					reportParams.put("oilNum", attackerResultJSON.optLong("oil"));
					reportParams.put("foodNum", attackerResultJSON.optLong("food"));
					reportParams.put("moneyNum", attackerResultJSON.optLong("money"));
					
					//向客户端push强制刷新城市资源
					JSONObject json = new JSONObject();
					json.put("type", 21);
					GameSocketService.sendDataToClient(playerID, json);
					
					String content = TemplateService.format("Military_Return.ftl", reportParams);
					reportService.sendMilitaryReport(playerID, "军队返回报告", content);
				}
			}
			
		} catch(Exception e) {
			logger.error("异常：", e);
			
		} finally {
			finishReturnWaitLock.unlock();
		}
		
	}
	
	public void finishSpyWait(SpyQueue spyQueue){
		switch(spyQueue.getState()){
			case SpyQueueStateConstant.GOING:
				doSpyReturing(spyQueue);
				break;
			case SpyQueueStateConstant.RETURNING:
				doFinishSpyReturing(spyQueue);
				break;
		}
		
	}
	
	//侦察返回过程结束处理
	private void doFinishSpyReturing(SpyQueue spyQueue){
		try {
			CityArmy cityArmy = armyService.getCityArmy(spyQueue.getCityID(), ArmyConstant.SCOUT);
			
			//将侦察兵返回给城市
			if(cityArmy != null){
				cityArmy.setNum(cityArmy.getNum() + spyQueue.getNum());
				
				armyService.updateCityArmy(cityArmy);
			}else{
				cityArmy = new CityArmy();
				cityArmy.setArmyID(ArmyConstant.SCOUT);
				cityArmy.setCityID(spyQueue.getCityID());
				cityArmy.setNum(spyQueue.getNum());
				
				armyService.createCityArmy(cityArmy);
			}
			
			spyQueueService.deleteSpyQueueByID(spyQueue.getSpyQueueID());
			Map map = mapService.getMapByID(spyQueue.getMapID());
			
			//发送侦察完成报告
			Integer playerID = cityService.getPlayerIDByCityID(spyQueue.getMapID());
			String title = MessageFormat.format("对{0}({1},{2})进行侦察的部队已返回", map.getName(),map.getPosX(), map.getPosY());
			String reportContent = MessageFormat.format("对{0}({1},{2})进行侦察的军队已完成使命，共有{3}人返回。", map.getName(), map.getPosX(), map.getPosY(), spyQueue.getNum());
			
			reportService.sendMilitaryReport(playerID, title, reportContent);
			
		}catch(Exception e){
			logger.error("异常：", e);
		}
	}
	
	//侦察完成，开始返回
	private void doSpyReturing(SpyQueue spyQueue){
		
		try {
			CityInfo cityInfo = cityService.getCityInfoByCityID(spyQueue.getCityID());
			Map map = mapService.getMapByID(spyQueue.getMapID());
			
			long returnNum = 0L;
			switch(map.getCategory()){
				//对玩家城市进行的侦察
				case MapConstant.CATEGORY_CITY:
					//侦察返回的士兵数量
					returnNum = sendPlayerReport(spyQueue,map.getTargetID());
					//如果侦察失败给目标方发送报告
					if( returnNum == 0){
						Integer playerID = cityService.getPlayerIDByCityID(map.getTargetID());
						
						String title = MessageFormat.format("{0}({1},{2})对您城市的侦察被发现",map.getName(),cityInfo.getPosX(),cityInfo.getPosY());
						String reportContent = MessageFormat.format("{0}({1},{2})在{3}对您的城市进行了侦察，对方共派遣了{4}个侦察兵，但已全部阵亡。", map.getName(),cityInfo.getPosX(),cityInfo.getPosY(),DateService.parseDateToReportTimeString(new Date()),spyQueue.getNum());
						
						reportService.sendMilitaryReport(playerID,title,reportContent);
						//将侦察队列删除
						spyQueueService.deleteSpyQueueByID(spyQueue.getSpyQueueID());
						
						return;
					}else{
						//更新返回的侦察兵数量
						spyQueue.setNum((int)returnNum);
					}
				break;
				
				//对野怪所处的野地进行侦察	
				case MapConstant.CATEGORY_MONSTER:
					//侦察返回的士兵数量
					returnNum = sendMonsterReport(spyQueue,map.getTargetID());
					//如果侦察失败给目标方发送报告
					if( returnNum == 0){
						//将侦察队列删除
						spyQueueService.deleteSpyQueueByID(spyQueue.getSpyQueueID());
					}
					break;
			}
			
			int speed = armyService.getArmyByID(ArmyConstant.SCOUT).getSpeed();
			Date now = new Date();
			now.setTime(now.getTime()+1000 * CostTimeCalculateUtil.calculateMilitaryCostTime(cityInfo.getPosX(), cityInfo.getPosY(), map.getPosX(), map.getPosY(), speed));
			//设置返回需要的时间
			spyQueue.setFinishTime(now);
			//更改侦察状态为返回
			spyQueue.setState(SpyQueueStateConstant.RETURNING);
			
			spyQueueService.updateSpyQueue(spyQueue);
			
		}catch(Exception e){
			logger.error("异常：", e);
		}

	}
	
	//发送对玩家城市侦察的报告,返回剩余的侦察兵数量
	//注：这里的reportLevel等级0 1 2 3 4 5 6 7　分别对应科技等级0 1 5 10 15 20 25 30
	private long sendPlayerReport(SpyQueue spyQueue,Integer targetID){
		//报告详细等级
		int reportLevel = 0;
		//获得侦察方的侦察科技的等级
		CityTechnology ct = technologyService.getCityTechnologyByCityIDAndTechnologyID(spyQueue.getCityID(), TechnologyConstant.OTHER_SPY_ADD);
		//侦察方的科技等级
		int techLevel = 0;
		if(ct != null){
			techLevel = ct.getLevel();
		}
		//侦察目标的雷达信息
		CityBuilding targetCB = buildingService.getCityBuilding(targetID, BuildingConstant.RADAR);
		///侦察目标的雷达等级
		int radarLevel = 0;
		
		if(targetCB != null){
			radarLevel = targetCB.getLevel();
		}
		
		//侦察科技每5级确定一个报告等级
		if(techLevel == 0){
			reportLevel = 0;
		}else if(techLevel < 5){
			reportLevel = 1;
		}else{
			reportLevel = techLevel / 5 + 1;
		}
		
		//当对方雷达级别高于侦察能力时，效果降低一档
		if(techLevel < radarLevel){
			reportLevel = Math.max(0, reportLevel - 1);
		}
		
		//目标侦察兵的数量
		long scoutNum = getTotalArmyNumInCity(targetID,ArmyConstant.SCOUT);
		//侦察失败概率
		float failureOdds = (scoutNum - spyQueue.getNum()) / spyQueue.getNum();
		//失败时损失的侦察兵数量
		long lostNum = 0;
		
		//如果失败了
		if(failureOdds > 1){
			lostNum = Math.min((long) (failureOdds * spyQueue.getNum() / 2),spyQueue.getNum());
			
			//如果全军覆没就没有任何报告信息
			if(lostNum == spyQueue.getNum()){
				reportLevel = 0;
			}else{
				//失败后报告最高为一级
				if(reportLevel > 1){
					reportLevel = 1;
				}
			}
		}
		
		//构造报告内容
		java.util.Map<String,Object> contents = new HashMap<String, Object>();
		//目标城市信息
		City targetCity = cityService.getCityByID(targetID);
		
		//道具对侦察结果的影响：0为没有影响 1为战略欺骗效果 2为战略伪装效果
		int treasureEffect = 0;
		if(treasureQueueService.getTreasureQueueByType(targetCity.getCityID(), TreasureCategoryConstant.MILITARY, TreasureTypeConstant.STRATEGY_CHEAT) != null){
			treasureEffect = 1;
		}else if(treasureQueueService.getTreasureQueueByType(targetCity.getCityID(), TreasureCategoryConstant.MILITARY, TreasureTypeConstant.STRATEGY_CAMOUFLAGE) != null){
			treasureEffect = 2;
		}
		
		contents.put("cityName", cityService.getCityNameByCityID(targetID));
		contents.put("resourceInfo", getReportResourceInfo(reportLevel,targetCity));
		contents.put("defenseInfo", getReportDefenseInfo(reportLevel,targetID,treasureEffect));
		contents.put("armyInfo", getReportArmyInfo(reportLevel,targetID,treasureEffect));
		contents.put("buildingInfo", getReportBuildingInfo(reportLevel,targetID));
		contents.put("populationInfo", getReportPopulationInfo(reportLevel,targetCity));
		contents.put("techInfo", getReportTechInfo(reportLevel,targetID));
		contents.put("securityInfo", getReportSecurityInfo(reportLevel,targetCity));
		//获得报告使用的结果信息
		contents.put("resultInfo", MessageFormat.format("此次侦察您共派遣了{0}个侦察兵，损失{1}个。",spyQueue.getNum(),lostNum));
		
		try {
			Map map = mapService.getMapByPos(targetCity.getPosX(), targetCity.getPosY());
			Integer playerID = cityService.getPlayerIDByCityID(spyQueue.getCityID());
			String title = MessageFormat.format("对{0}({1},{2})的侦察报告",map.getName(),targetCity.getPosX(),targetCity.getPosY());
			String reportContent = TemplateService.format("SpyReportForPlayer.ftl", contents);
			//发送报告
			reportService.sendMilitaryReport(playerID,title,reportContent);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
		return (spyQueue.getNum() - lostNum);
	}
	
	//获得报告使用的资源信息
	private String getReportResourceInfo(int reportLevel,City city){
		CityResource cityResource = cityService.getCityResourceByCityID(city.getCityID());
		if(reportLevel > 0){
			return MessageFormat.format("木材{0} 钢铁{1} 石油{2} 食物{3} 金钱{4}",cityResource.getWoodNum(),cityResource.getSteelNum(),cityResource.getOilNum(),cityResource.getFoodNum(),cityResource.getMoneyNum());
		}else{
			return "没有侦察到任何信息";
		}
	}
	
	//获得报告使用的城防信息
	private String getReportDefenseInfo(int reportLevel,Integer cityID,int treasureEffect){
		if(reportLevel < 2){
			return "没有侦察到任何信息";
		}
		
		//对应宝物效果
		int[] effectList = {0,25,-25};
		
		//防空炮数量
		int antigunNum = 0;
		//碉堡数量
		int bunkerNum = 0;
		//围墙数量
		int fenceNum = 0;
		//火炮数量
		int gunNum = 0;
		
		List<CityDefense> list = cityDefenseService.getCityDefenseList(cityID);
		CityDefense cd;
		
		for(int i=0; i<list.size(); i++){
			cd = list.get(i);
			switch(cd.getCityDefenseID()){
			case DefenseConstant.ANTIGUN:
				antigunNum = cd.getNum() * (effectList[treasureEffect] + 100) / 100;
				break;
			case DefenseConstant.BUNKER:
				bunkerNum = cd.getNum() * (effectList[treasureEffect] + 100) / 100;
				break;
			case DefenseConstant.FENCE:
				fenceNum = cd.getNum() * (effectList[treasureEffect] + 100) / 100;
				break;
			case DefenseConstant.GUN:
				gunNum = cd.getNum() * (effectList[treasureEffect] + 100) / 100;
				break;	
			}
		}
		
		if(reportLevel < 6){
			return MessageFormat.format("{0}围墙 {1}碉堡 {2}火炮 {3}防空炮",ArmyUtil.getDefenseForceDescription(fenceNum),ArmyUtil.getDefenseForceDescription(bunkerNum),ArmyUtil.getDefenseForceDescription(gunNum),ArmyUtil.getDefenseForceDescription(antigunNum));
		}else{//6-
			return MessageFormat.format("{0}围墙 {1}碉堡 {2}火炮 {3}防空炮",fenceNum,bunkerNum,gunNum,antigunNum);
		}
	}
	
	//获得报告使用的军队信息
	private String getReportArmyInfo(int reportLevel,Integer cityID,int treasureEffect){
		if(reportLevel < 3){
			return "没有侦察到任何信息";
		}
		
		List<Army> armyList = armyService.getArmyList();
		StringBuffer sb = new StringBuffer();
		long armyNum;
		Army army;
		
		//对应宝物效果
		int[] effectList = {0,25,-25};
		
		for(int i=0; i<armyList.size(); i++){
			army = armyList.get(i);
			armyNum = getTotalArmyNumInCity(cityID,army.getArmyID()) * (effectList[treasureEffect] + 100) / 100;
			
			if(armyNum > 0){
				if(reportLevel < 7){
					sb.append(MessageFormat.format("一个{0}{1} ",army.getName(),ArmyUtil.getArmyForceDescription(armyNum)));
				}else{
					sb.append(MessageFormat.format("{1}{0} ",army.getName(),armyNum));
				}
			}
		}
		
		return sb.toString();
	}
	
	//获得报告使用的建筑信息
	private String getReportBuildingInfo(int reportLevel,Integer cityID){
		if(reportLevel < 4){
			return "没有侦察到任何信息";
		}else{
			List<CityBuilding> list = buildingService.getCityBuildingListByCityID(cityID);
			StringBuffer sb = new StringBuffer();
			CityBuilding cb;
			
			for(int i=0; i<list.size(); i++){
				cb = list.get(i);
				//过滤掉城防建筑
				switch(cb.getBuildingID()){
				case DefenseConstant.FENCE:
				case DefenseConstant.BUNKER:
				case DefenseConstant.GUN:
				case DefenseConstant.ANTIGUN:
					continue;
				}
				
				if(cb.getLevel() > 0){
					sb.append(MessageFormat.format("{0}({1}级) ",cb.getBuilding().getName(),cb.getLevel()));
				}
			}
			
			return sb.toString();
		}
	}
	
	//获得报告使用的人口信息
	private String getReportPopulationInfo(int reportLevel,City city){
		if(reportLevel < 4){
			return "没有侦察到任何信息";
		}else{
			return "当前人口" + city.getPopulationTotal() + " 人口上限" + city.getPopulationMax();
		}
	}
	
	//获得报告使用的科技信息
	private String getReportTechInfo(int reportLevel,Integer cityID){
		if(reportLevel < 5){
			return "没有侦察到任何信息";
		}
		
		//只列出类型为士兵，车辆，飞机的科技信息
		List<CityTechnology> list = technologyService.getCityTechnologyListByType(cityID, TechnologyTypeConstant.SOLDIER);
		list.addAll(technologyService.getCityTechnologyListByType(cityID, TechnologyTypeConstant.VEHICLE));
		list.addAll(technologyService.getCityTechnologyListByType(cityID, TechnologyTypeConstant.PLANE));
		
		StringBuffer sb = new StringBuffer();
		CityTechnology ct;
		
		for(int i=0; i<list.size(); i++){
			ct = list.get(i);
			
			if(ct.getLevel() > 0){
				sb.append(MessageFormat.format("{0}({1}级) ",ct.getTechnology().getName(),ct.getLevel()));
			}
		}
		
		return sb.toString();
	}
	
	//获得报告使用的治安信息
	private String getReportSecurityInfo(int reportLevel,City city){
		if(reportLevel < 5){
			return "没有侦察到任何信息";
		}else{
			return "治安" + city.getSecurity() + " 税率" + city.getTax();
		}
	}
	
	
	//获得城内一种兵的所有数量(包括编制的和未编制的，编制的只算在城市里的军队:状态为正常和留守的)
	private long getTotalArmyNumInCity(Integer cityID,Integer armyID){
		long totalNum = 0;
		
		//计算未编制的士兵信息
		CityArmy ca  = armyService.getCityArmy(cityID, armyID);
		if(ca != null){
			totalNum += ca.getNum();
		}
		
		//计算已编制部分
		List<CityMilitary> list = this.getCityMilitaryList(cityID);
		CityMilitary cm;
		if(list != null && list.size() > 0){
			for(int i=0; i<list.size(); i++){
				cm = list.get(i);
				//只计算在城内的军队:状态为正常和留守的
				if(cm.getState() != CityMilitaryStateConstant.NORMAL || cm.getState() != CityMilitaryStateConstant.STAY)
					continue;
				
				totalNum += parseArmyNum(cm.getArmy1(),armyID);
				totalNum += parseArmyNum(cm.getArmy2(),armyID);
				totalNum += parseArmyNum(cm.getArmy3(),armyID);
				totalNum += parseArmyNum(cm.getArmy4(),armyID);
				totalNum += parseArmyNum(cm.getArmy5(),armyID);
				totalNum += parseArmyNum(cm.getArmy6(),armyID);
				totalNum += parseArmyNum(cm.getArmy7(),armyID);
				totalNum += parseArmyNum(cm.getArmy8(),armyID);
			}
		}
		
		return totalNum;
	}
	
	//从army字符串中提取出给定兵种的数量信息
	private long parseArmyNum(String army,Integer armyID){
		if(army == null || army.trim().length() == 0){
			return 0;
		}
		
		String[] tmp = army.split(":");
		if(tmp.length == 2 && Integer.parseInt(tmp[0]) == armyID){
			return Long.parseLong(tmp[1]);
		}else{
			return 0;
		}
	}
	
	//发送对野怪侦察的报告
	private int sendMonsterReport(SpyQueue spyQueue, Integer targetID){
		
		MapMonster mm = mapMonsterDAO.getMapMonsterByID(targetID);
		
		//要求侦察兵数量
		int requiredNum = SpyConstant.SPY_MONSTER_REQUIRED_NUM[mm.getLevel()-1];
		//失败概率
		int probabilityOfFailure = requiredNum/spyQueue.getNum();
		int random = new Random().nextInt(101);
		if(random<=probabilityOfFailure){
			//侦查失败
			
			//损失数量
			int lostNum = (requiredNum-spyQueue.getNum())/2;
			if(lostNum>spyQueue.getNum()){
				lostNum = spyQueue.getNum();
			}
			if(lostNum<0){
				lostNum = 0;
			}
			
			//发送报告
			Map map = mapService.getMapByID(spyQueue.getMapID());
			Integer playerID = cityService.getPlayerIDByCityID(spyQueue.getCityID());
			String title = MessageFormat.format("对{0}({1},{2})的侦察报告",map.getName(), map.getPosX(), map.getPosY());
			String reportContent = MessageFormat.format("此次侦察您派遣了{0}个侦察兵，损失{1}个，已全军覆没。", spyQueue.getNum(), lostNum);
			reportService.sendMilitaryReport(playerID,title,reportContent);
			
			return spyQueue.getNum()-lostNum;
		}
		
		//报告详细等级
		int reportLevel = 0;
		//获得侦察方的侦察科技的等级
		CityTechnology ct = technologyService.getCityTechnologyByCityIDAndTechnologyID(spyQueue.getCityID(), TechnologyConstant.OTHER_SPY_ADD);
		//侦察方的科技等级
		int techLevel = 0;
		if(ct != null){
			techLevel = ct.getLevel();
		}else{
			
		}
		//侦察科技每5级确定一个报告等级
		if(techLevel==0){
			reportLevel = 0;
		}else{
			reportLevel = techLevel/5+1;
		}
		
		//构造报告内容
		Map map = mapService.getMapByID(spyQueue.getMapID());
		java.util.Map<String,Object> contents = new HashMap<String, Object>();
		contents.put("cityName", "野地("+map.getPosX()+","+map.getPosY()+")");
		contents.put("armyInfo", getReportMonsterArmyInfo(reportLevel,mm));
		
		if(reportLevel < 2){
			contents.put("commanderInfo", "没有侦察到任何信息");
		}else{
			contents.put("commanderInfo", "等级"+mm.getCmderLevel());
		}
		
		try {
			Integer playerID = cityService.getPlayerIDByCityID(spyQueue.getCityID());
			String title = MessageFormat.format("对{0}({1},{2})的侦察报告", map.getName(), map.getPosX(), map.getPosY());
			String reportContent = TemplateService.format("SpyReportForMonster.ftl", contents);
			//发送报告
			reportService.sendMilitaryReport(playerID,title,reportContent);
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		return spyQueue.getNum();
	}
	
	//获得报告使用的军队信息
	private String getReportMonsterArmyInfo(int reportLevel,MapMonster mm){
		if(reportLevel < 1){
			return "没有侦察到任何信息";
		}
		
		StringBuffer sb = new StringBuffer();
		
		java.util.Map<String,Integer> armyMap = new HashMap<String, Integer>();
		setMonsterArmyInfo(armyMap,mm.getArmy1());
		setMonsterArmyInfo(armyMap,mm.getArmy2());
		setMonsterArmyInfo(armyMap,mm.getArmy3());
		setMonsterArmyInfo(armyMap,mm.getArmy4());
		setMonsterArmyInfo(armyMap,mm.getArmy5());
		setMonsterArmyInfo(armyMap,mm.getArmy6());
		setMonsterArmyInfo(armyMap,mm.getArmy7());
		setMonsterArmyInfo(armyMap,mm.getArmy8());
		
		Set<String> keys = armyMap.keySet();
		Iterator<String> iter = keys.iterator();
		String armyName;
		String armyID;
		
		while(iter.hasNext()){
			armyID = iter.next();
			armyName = armyService.getArmyNameByID(new Integer(armyID));
			//根据报告等级不同显示不同信息
			if (reportLevel < 2) {
				sb.append(armyName+" ");
			} else if (reportLevel < 3) {
				sb.append(MessageFormat.format("{0}{1} ",ArmyUtil.getArmyForceDescription(armyMap.get(armyID)),armyName ));
			} else {
				sb.append(MessageFormat.format("{0}{1} ",armyName, armyMap.get(armyID)));
			}
			
		}
		
		return sb.toString();
	}
	
	//将同一兵种的数量累加起来
	private void setMonsterArmyInfo(java.util.Map<String, Integer> armyMap,String army){
		if(army == null || army.trim().length() == 0)
			return;
		
		String[] tmp = army.split(":");
		int armyNum = 0;
		
		if(armyMap.containsKey(tmp[0])){
			armyNum = armyMap.get(tmp[0]);
			armyNum += Integer.parseInt(tmp[1]);
		}else{
			armyNum = Integer.parseInt(tmp[1]);
		}
		
		armyMap.put(tmp[0], armyNum);
	}
	
	public void spy(int fromCityID,int num,int toPosX,int toPosY){
		
		CityArmy cityArmy = armyService.getCityArmy(fromCityID, ArmyConstant.SCOUT);
		
		if(num <= 0)
			throw new GameException("无效操作。");
		
		if(cityArmy == null || cityArmy.getNum() < num)
			throw new GameException("城市侦察兵数量不足。");
		
		Map map = mapService.getMapByPos(toPosX,toPosY);
		
		if(map == null)
			throw new GameException("非法地图坐标。");
		
		//不能对空地进行该操作
		if(map.getCategory() == MapConstant.CATEGORY_BLANK_FIELD){
			throw new GameException("您指定的目标不存在，请刷新地图后重试。");
		}
		
		if (map.getCategory() != MapConstant.CATEGORY_MONSTER && map.getCategory() != MapConstant.CATEGORY_CITY) {
			throw new GameException("该功能暂未开放，请稍后再试！");
		}
		
		//扣除资源
		City city = cityService.getCityByID(fromCityID);
		//侦察所需金钱数量
		long moneyCost = SpyConstant.SPY_MONEY_COST * num;
		//扣除城市金钱
		cityService.minusCityResources(fromCityID, 0, 0, 0, 0, moneyCost);
		
		//减少城内未编制的侦察兵数量
		cityArmy.setNum(cityArmy.getNum() - num);
		armyService.updateCityArmy(cityArmy);
		
		//加入队列
		SpyQueue spyQueue = new SpyQueue();
		spyQueue.setCityID(fromCityID);
		spyQueue.setMapID(map.getMapID());
		spyQueue.setNum(num);
		spyQueue.setState(SpyQueueStateConstant.GOING);
		int speed = armyService.getArmyByID(ArmyConstant.SCOUT).getSpeed();
		Date now = new Date();
		now.setTime(now.getTime()+1000 * CostTimeCalculateUtil.calculateMilitaryCostTime(city.getPosX(), city.getPosY(), toPosX, toPosY, speed));
		spyQueue.setFinishTime(now);
		//创建队列
		spyQueueService.createSpyQueue(spyQueue);
		
		// 记录玩家操作日志
		switch (map.getCategory()) {
			case MapConstant.CATEGORY_MONSTER:
				Integer level = monsterService.getMapMonsterByID(map.getTargetID()).getLevel();
				operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.SPY_MONSTER, level.toString());
				break;
			case MapConstant.CATEGORY_CITY:
				Integer cityID = cityService.getCityByID(map.getTargetID()).getCityID();
				operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.SPY_CITY, cityID.toString());
				break;
			case MapConstant.CATEGORY_STRONG_HOLD:
				Integer strongHoldID = strongholdService.getStrongholdByID(map.getTargetID()).getStrongholdID();
				operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.SPY_STRONG_HOLD, strongHoldID.toString());
				break;
		}
	}
	
	public Integer hasMilitaryInBattleOrGoingToMap(Integer posX,Integer posY){
		Map map = mapService.getMapByPos(posX, posY);
		if(map.getState()==MapConstant.STATE_FIGHTING){
			return 1;
		}
		if(depoyQueueDAO.getDepoyQueueNumByPosXAndPosY(posX, posY)>0){
			return 2;
		}else{
			return 0;
		}
	}
	
	public DepoyQueue attack(int cityMilitaryID,int targetPosX,int targetPosY){
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("军队不存在。");
		
		if(cityMilitary.getState() != CityMilitaryStateConstant.NORMAL)
			throw new GameException("只有正常状态下的部队才能发起攻击。");
		
		Map targetMap = mapService.getMapByPos(targetPosX,targetPosY);
		
		if(targetMap == null)
			throw new GameException("非法目标。");
		
		// 不能对空地进行该操作
		if(targetMap.getCategory() == MapConstant.CATEGORY_BLANK_FIELD){
			throw new GameException("您指定的目标可能已经不存在，请刷新地图后再试。");
		}

		if (targetMap.getCategory() != MapConstant.CATEGORY_MONSTER && targetMap.getCategory() != MapConstant.CATEGORY_CITY) {
			throw new GameException("该功能暂未开放，请稍后再试！");
		}
		
		City attackerCity = cityService.getCityByID(cityMilitary.getCityID());
		
		//判断是否攻击玩家城市或要塞
		if(targetMap.getCategory()==MapConstant.CATEGORY_CITY || targetMap.getCategory()==MapConstant.CATEGORY_STRONG_HOLD){
			
			// 判断攻击地点的总军队数据是否大于限额
			if (!(this.getCityMilitaryEnemyNum(targetPosX, targetPosY) < MilitaryConstant.CITY_UNDER_ATTACK_MAX_MILITARY_NUM)) {
				throw new GameException("该城市正在遭受大量军队进攻，已是四面楚歌之势，城市之外再无立足之地，请耐心等待或延迟攻击。");
			}
			
			//判断是否宣战并是否在战争期
			DeclareWar declareWar = declareWarService.getDeclareWar(cityService.getPlayerIDByCityID(cityMilitary.getCityID()), cityService.getPlayerIDByCityID(targetMap.getTargetID()));
			if(declareWar==null){
				declareWar = declareWarService.getDeclareWar(cityService.getPlayerIDByCityID(targetMap.getTargetID()), cityService.getPlayerIDByCityID(cityMilitary.getCityID()));
			}
			if(declareWar==null){
				throw new GameException("您需要先对该玩家宣战才可以派遣军队进攻。");
			}else if(declareWar.getStartTime().getTime()>=System.currentTimeMillis()){
				throw new GameException("宣战12小时后才可攻击对方城市或要塞。");
			}
			
			//判断己方或对方是否处于免战状态
			if(playerService.getPlayerByID(cityService.getPlayerIDByCityID(cityMilitary.getCityID())).getState()==PlayerStateConstant.FREEWAR){
				throw new GameException("您处于免战状态，无法对其他玩家发出进攻。");
			}
			if(playerService.getPlayerByID(cityService.getPlayerIDByCityID(targetMap.getTargetID())).getState()==PlayerStateConstant.FREEWAR){
				throw new GameException("对方处于免战状态，无法对其发出进攻。");
			}
			
		} else {
			if (!(this.getCityMilitaryEnemyNum(targetPosX, targetPosY) < MilitaryConstant.CITY_UNDER_ATTACK_MAX_MILITARY_NUM)) {
				throw new GameException("您的出征目的地，正有大批军队蜂拥而至，同时进攻军队数量已达上限，请耐心等待或延迟攻击。");
			}
		}
		
		//获得出兵部队的速度
		int speed = this.getMilitarySpeed(cityMilitary);
		//到达目的地所需时间
		long costTime = CostTimeCalculateUtil.calculateMilitaryCostTime(attackerCity.getPosX(), attackerCity.getPosY(), targetPosX, targetPosY, speed);
		//扣除资源
		this.reduceMilitaryCostResource(cityMilitary, costTime);
		//更新军队的状态为出征
		cityMilitaryDAO.updateCityMilitaryState(cityMilitaryID, CityMilitaryStateConstant.MARCH);
		//更新指挥官的状态为出征
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.DEPOY);
		
		DepoyQueue depoyQueue = new DepoyQueue();
		depoyQueue.setCityID(cityMilitary.getCityID());
		depoyQueue.setCityMilitaryID(cityMilitaryID);
		depoyQueue.setMapID(targetMap.getMapID());
		depoyQueue.setType(DepoyTypeConstant.ATTACK);
		Date arriveTime = new Date();
		arriveTime.setTime(System.currentTimeMillis()+1000*costTime);
		depoyQueue.setFinishTime(arriveTime);
		
		Integer dpid = depoyQueueService.createDepoyQueue(depoyQueue);
		depoyQueue.setDepoyQueueID(dpid);
			
		return null;
	}
	
	public void recallMilitary(Integer depoyQueueID){
		
		
		
	}
	
	public int getMilitarySpeed(CityMilitary cityMilitary){
		return this.getMilitarySpeed(cityMilitary.getArmy1(), cityMilitary.getArmy2(), cityMilitary.getArmy3(), cityMilitary.getArmy4(), cityMilitary.getArmy5(), cityMilitary.getArmy6(), cityMilitary.getArmy7(), cityMilitary.getArmy8());
	}
	
	public int getMilitarySpeed(BattleMilitary battleMilitary) {
		return this.getMilitarySpeed(battleMilitary.getArmy1(), battleMilitary.getArmy2(), battleMilitary.getArmy3(), battleMilitary.getArmy4(), battleMilitary.getArmy5(), battleMilitary.getArmy6(), battleMilitary.getArmy7(), battleMilitary.getArmy8());
	}
	
	private int getMilitarySpeed(String army1, String army2, String army3, String army4, String army5, String army6, String army7, String army8) {
		int speed = MilitaryConstant.MILITARY_DEFAULT_SPEED;
		speed = Math.min(speed, getArmySpeed(army1));
		speed = Math.min(speed, getArmySpeed(army2));
		speed = Math.min(speed, getArmySpeed(army3));
		speed = Math.min(speed, getArmySpeed(army4));
		speed = Math.min(speed, getArmySpeed(army5));
		speed = Math.min(speed, getArmySpeed(army6));
		speed = Math.min(speed, getArmySpeed(army7));
		speed = Math.min(speed, getArmySpeed(army8));
		
		return speed;
	}
	
	/**
	 * 根据军队士兵信息获得士兵速度
	 * @param armyStr
	 * @return 士兵速度
	 */
	private Integer getArmySpeed(String armyStr){
		if(armyStr==null || armyStr.trim().length()==0){
			return MilitaryConstant.MILITARY_DEFAULT_SPEED;
		}
		return armyService.getArmySpeed(new Integer(armyStr.split(":")[0]));
	}

	/**
	 * 扣除军队出征消耗资源
	 * @param cityMilitary
	 * @param costTime
	 */
	private void reduceMilitaryCostResource(CityMilitary cityMilitary, Long costTime){
		CityResource cityResource = cityService.getCityResourceByCityID(cityMilitary.getCityID());
		
		long costOil = MilitaryConstant.CAMPAIGN_COST_RESOURCE_MULTIPLE*cityMilitary.getCostOil()*costTime/3600;
		long costFood = MilitaryConstant.CAMPAIGN_COST_RESOURCE_MULTIPLE*cityMilitary.getCostFood()*costTime/3600;
		long costMoney = MilitaryConstant.CAMPAIGN_COST_RESOURCE_MULTIPLE*cityMilitary.getCostMoney()*costTime/3600;
		
		//资源检查
		if(cityResource.getOilNum() < costOil)
			throw new GameException("石油不足，本次出征需要石油"+costOil+"。");
		if(cityResource.getFoodNum() < costFood)
			throw new GameException("食物不足，本次出征需要食物"+costFood+"。");
		if(cityResource.getMoneyNum() < costMoney)
			throw new GameException("金钱不足，本次出征需要金钱"+costMoney+"。");

		cityService.minusCityResources(cityResource.getCityID(), 0, 0, costOil, costFood, costMoney);
	}
	
	public DepoyQueue dispatch(int cityMilitaryID,int posX,int posY,long carryFood,long carryWood,long carryOil,long carrySteel,long carryMoney, boolean isCallback){
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("无效操作。");
		
		Map map = mapService.getMapByPos(posX, posY);
		
		if(map == null)
			throw new GameException("目标不存在。");
		
		if( map.getCategory() != MapConstant.CATEGORY_CITY ) { 
			throw new GameException("派遣目标需为城市。");
		}
		
		// 获得部队的速度
		int speed = this.getMilitarySpeed(cityMilitary);
		
		// 到达目的地所需时间
		long costTime = 0;
		City dispatcherCity = cityService.getCityByID(cityMilitary.getCityID());
		
		if (isCallback) {
			CityMilitarySuccor cityMilitarySuccor = cityMilitarySuccorDAO.getCityMilitarySuccorByCityMilitaryID(cityMilitaryID);
			City succorCity = cityService.getCityByID(cityMilitarySuccor.getTargetCityID());
			costTime = CostTimeCalculateUtil.calculateMilitaryCostTime(succorCity.getPosX(), succorCity.getPosY(), posX, posY, speed);
			
			cityService.minusCityResourcesClear(dispatcherCity.getCityID(), 0, 0, cityMilitary.getCostOil(), cityMilitary.getCostFood(), cityMilitary.getCostMoney());
			
		} else {
			costTime = CostTimeCalculateUtil.calculateMilitaryCostTime(dispatcherCity.getPosX(), dispatcherCity.getPosY(), posX, posY, speed);
			Player dispatchPlayer = playerService.getPlayerByID(cityService.getPlayerIDByCityID(cityMilitary.getCityID()));
			
			if (dispatchPlayer.getGuildID() == null) {
				throw new GameException("派遣失败，您还未加入任何军团。");
			}
			
			Player succorPlayer = playerService.getPlayerByID(cityService.getCityByPosXAndPosY(posX, posY).getPlayerID());
			GuildPlayer succorGuildPlayer = guildService.getGuildPlayerByGuildIDAndPlayerID(succorPlayer.getGuildID(), succorPlayer.getPlayerID());
			
			if (succorGuildPlayer == null || (succorGuildPlayer.getGuildID().intValue() != dispatchPlayer.getGuildID().intValue())) {
				throw new GameException("派遣失败，军队执行派遣操作的目标必须是好友或同一军团成员的城市。");
			}
			
			if (succorGuildPlayer.getAllowGarrison() == GuildConstant.FORBID_SUCCOR) {
				throw new GameException("派遣失败，目标城市，不允许驻扎军队。");
			}
			
			if (!( this.getCityMilitarySuccorNum(cityService.getCityIDByCityPos(posX, posY)) < MilitaryConstant.CITY_MILITARY_SUCCOR_MAX_NUM )) {
				throw new GameException("目标城市最大驻军数已满。");
			}
			
			cityService.minusCityResources(dispatcherCity.getCityID(), 0, 0, cityMilitary.getCostOil(), cityMilitary.getCostFood(), cityMilitary.getCostMoney());
		}
		
		// 创建队列
		DepoyQueue dq = new DepoyQueue();
		dq.setCityMilitaryID(cityMilitaryID);
		dq.setCityID(cityMilitary.getCityID());
		dq.setMapID(map.getMapID());
		dq.setType(isCallback ? DepoyTypeConstant.RETURN : DepoyTypeConstant.DISPATCH);
		Date now = new Date();
		now.setTime(now.getTime() + 1000 * costTime);
		dq.setFinishTime(now);
		JSONObject carry = new JSONObject();
		
		try {
			carry.put("Wood", carryWood);
			carry.put("Steel", carrySteel);
			carry.put("oil", carryOil);
			carry.put("food", carryFood);
			carry.put("Money", carryMoney);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		dq.setRemark(carry.toString());
		
		Integer dpid = depoyQueueService.createDepoyQueue(dq);
		dq.setDepoyQueueID(dpid);
		
		// 更改军队状态
		cityMilitaryDAO.updateCityMilitaryState(cityMilitary.getCityMilitaryID(), isCallback ? CityMilitaryStateConstant.MARCH_RETURN : CityMilitaryStateConstant.MARCH);
		// 更改指挥官状态
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.DEPOY);
			
		return dq;
	}
	
	public void clientMilitaryArrived(Integer depoyQueueID){
		
		try {
			clientMilitaryArrivedLock.lock();
			
			DepoyQueue depoyQueue = depoyQueueService.getDepoyQueueByID(depoyQueueID);
			
			if(depoyQueue == null)
				//已经被处理
				return;
			
			//进程结束时间和当前时间的间隔毫秒数
			long clips = depoyQueue.getFinishTime().getTime()-System.currentTimeMillis();
			
			//如果过了完成时间就对该进程进行处理
			if(clips <= 0){
				this.finishAttackWait(depoyQueue);
			}
			
		} finally {
			clientMilitaryArrivedLock.unlock();
		}
		
	}
	
	public void cancelDefensiveMilitary(int cityMilitaryID) {
		CityMilitary cityMilitary = this.cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("军队不存在。");

		//更新城市留守军队信息
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityMilitary.getCityID());
		params.put("defensiveMilitary", null);
		this.cityService.updateCity(params);
		
		//设置军队的状态为正常
		this.cityMilitaryDAO.updateCityMilitaryState(cityMilitaryID, CityMilitaryStateConstant.NORMAL);
		// 设置军队的指挥官的状态为空间
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.FREE);
	}

	public void setDefensiveMilitary(int cityMilitaryID) {
		CityMilitary cityMilitary = this.cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("军队不存在。");
	
		//如果已经是留守军队就返回
		if(cityMilitary.getState() == CityMilitaryStateConstant.STAY)
			return;
		
		if(cityMilitary.getState() != CityMilitaryStateConstant.NORMAL)
			throw new GameException("只能设置本城空闲的军队为留守军队。");
		
		City city = this.cityService.getCityByID(cityMilitary.getCityID());
		// 获得之前的城守军队,如果设置了城守部队就把其状态改为正常
		Integer defensiveMilitary = city.getDefensiveMilitary();
		
		if(defensiveMilitary != null){
			cityMilitaryDAO.updateCityMilitaryState(defensiveMilitary, CityMilitaryStateConstant.NORMAL);
			cityHeroDAO.updateStateByCityHeroID(city.getOfficer(), CityHeroStateConstant.FREE);
		}
		
		//设置军队的状态为留守
		this.cityMilitaryDAO.updateCityMilitaryState(cityMilitaryID, CityMilitaryStateConstant.STAY);
		// 设置军队的指挥官状态为执政
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.REIGN);
		
		//更新城市留守军队信息
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("cityID", city.getCityID());
		params.put("officer", cityMilitary.getCityHeroID());
		params.put("defensiveMilitary", cityMilitaryID);
		
		this.cityService.updateCity(params);
	}
	
	public Integer getCityMilitaryAttackWithLeadership(Integer leadership, Integer armyAttackNum) {
		if (leadership >= MilitaryConstant.LIMIT_OF_HIGH_LEADERSHIP[0] && leadership <= MilitaryConstant.LIMIT_OF_HIGH_LEADERSHIP[1]) {
			int minPercent = (int)(((leadership / 100.0) * (MilitaryConstant.HEGH_LEADERSHIP_ATTACK_TOUCH_OFF_RATE / 100.0) * 1000 ));
			return RandomService.isInTheLimits(minPercent, 1000) ? (int)(armyAttackNum * (MilitaryConstant.HIGH_LEADERSHIP_ATTACK_PLUS_PERCENT / 100.0)) : 0 ;
			
		} else {
			return 0;
			
		}
	}

	public Integer getCityMilitaryBeAttackWithLeadership(Integer leadership, Integer armyAttackNum) {
		if (leadership >= MilitaryConstant.LIMIT_OF_FEW_LEADERSHIP[0] && leadership <= MilitaryConstant.LIMIT_OF_FEW_LEADERSHIP[1]) {
			int minPercent = (int)(((leadership / 100.0) * (MilitaryConstant.LOW_LEADERSHIP_BEATTACK_TOUCH_OFF_RATE / 100.0) * 1000 ));
			return RandomService.isInTheLimits(minPercent, 1000) ? (int)(armyAttackNum * (MilitaryConstant.LOW_LEADERSHIP_BEATTACK_PLUS_PERCENT / 100.0)) : 0 ;
			
		} else {
			return 0;
			
		}
	}

	public Integer getCityMilitaryTurnTailNumWithLeadership(Integer leadership, Integer armyNum) {
		if (leadership >= MilitaryConstant.LIMIT_OF_FEW_LEADERSHIP[0] && leadership <= MilitaryConstant.LIMIT_OF_FEW_LEADERSHIP[1]) {
			int minPercent = (int)(((leadership / 100.0) * (MilitaryConstant.FEW_LEADERSHIP_MILITARY_FLEE_TOUCH_OFF_RATE / 100.0) * 1000 ));
			return RandomService.isInTheLimits(minPercent, 1000) ? (int)(armyNum * (MilitaryConstant.FEW_LEADERSHIP_MILITARY_FLEE_PERCENT / 100.0)) : 0;
			
		} else {
			return 0;
			
		}
	}

	public boolean existsStayMilitary(Integer cityID){
		return cityMilitaryDAO.existsStayMilitary(cityID);
	}
	
	public void callbackCityMilitarySuccor(Integer cityID, Integer targetCityID, Integer cityMilitaryID) {
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		// 战斗中不能召回
		if (cityMilitary.getState() == CityMilitaryStateConstant.FIGHTING) {
			throw new GameException("您的驻扎军队正在进行战斗，无法进行召回操作。"); 
		}
		
		// 这里callback在这里的意思是 -> 执行召回的 <-
		City callbackCity = cityService.getCityByID(cityID);
		CityResource callbackCityResource = cityService.getCityResourceByCityID(cityID);
		CityResource targetCityResource = cityService.getCityResourceByCityID(targetCityID);
		
		// 召回
		dispatch(cityMilitaryID, callbackCity.getPosX(), callbackCity.getPosY(), 0, 0, 0, 0, 0, true);
		// 加上支援城市的消耗
		cityService.updateCityResourceConsumeByCityID(cityID, callbackCityResource.getOilConsume() + cityMilitary.getCostOil(), callbackCityResource.getFoodConsume() + cityMilitary.getCostFood(), callbackCityResource.getMoneyConsume() + cityMilitary.getCostMoney());
		// 减去被支援城市的消耗
		cityService.updateCityResourceConsumeByCityID(targetCityID, targetCityResource.getOilConsume() - cityMilitary.getCostOil(), targetCityResource.getFoodConsume() - cityMilitary.getCostFood(), targetCityResource.getMoneyConsume() - cityMilitary.getCostMoney());
		// 删除被支援城市对应的支援军队信息
		cityMilitarySuccorDAO.deleteCityMilitarySuccorByID(cityMilitarySuccorDAO.getCityMilitarySuccorIDByCityMilitaryID(cityMilitaryID));
	}
	
	public DepoyQueue cityMilitarySuccorBattleFailReturn(Integer cityMilitaryID, Integer targetPosX, Integer targetPosY) {
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		Map map = mapService.getMapByPos(targetPosX, targetPosY);
		
		DepoyQueue dq = new DepoyQueue();
		dq.setCityMilitaryID(cityMilitaryID);
		dq.setCityID(cityMilitary.getCityID());
		dq.setMapID(map.getMapID());
		dq.setType(DepoyTypeConstant.RETURN);
		dq.setFinishTime(new Date());
		
		Integer dpid = depoyQueueService.createDepoyQueue(dq);
		dq.setDepoyQueueID(dpid);
		
		// 更改军队状态
		cityMilitaryDAO.updateCityMilitaryState(cityMilitary.getCityMilitaryID(), CityMilitaryStateConstant.MARCH_RETURN);
		// 更改指挥官状态
		cityHeroDAO.updateStateByCityHeroID(cityMilitary.getCityHeroID(), CityHeroStateConstant.DEPOY);
		
		return dq;
	}

	public void dispatchCityMilitarySuccor(Integer cityMilitaryID, Integer posX, Integer posY) {
		this.dispatch(cityMilitaryID, posX, posY, 0, 0, 0, 0, 0, false);
	}

	public List<CityMilitarySuccor> getCityMilitarySuccorListByTargetCityID(Integer cityID) {
		return cityMilitarySuccorDAO.getCityMilitarySuccorListByTargetCityID(cityID);
	}
	
	public Integer getCityMilitarySuccorNum(Integer cityID) {
		// 被驻扎军队数目
		Integer beResideMilitary = cityMilitarySuccorDAO.getCityMilitarySuccorNumByTargetCityID(cityID);
		// 队列中正向本城派遣的军队数目
		Integer depoyMilitary = depoyQueueService.getCitySuccorDepoyQueueNum(cityID);
		
		return beResideMilitary + depoyMilitary;
	}

	public void handleCityMilitarySuccorOverTime() {
		try {
			List<CityMilitarySuccor> cityMilitarySuccorList = cityMilitarySuccorDAO.getOverTimeCityMilitarySuccorList();
			if (cityMilitarySuccorList != null && !cityMilitarySuccorList.isEmpty()) {
				for (CityMilitarySuccor cityMilitarySuccor : cityMilitarySuccorList) {
					callbackCityMilitarySuccor(cityMilitarySuccor.getCityID(), cityMilitarySuccor.getTargetCityID(), cityMilitarySuccor.getCityMilitaryID());
					cityMilitarySuccorDAO.deleteCityMilitarySuccorByID(cityMilitarySuccor.getCityMilitarySuccorID());
				}
			}
		
		} catch (Exception e) {
			logger.error("异常：", e);
		}
	}

	public int[] getConsumeOfCityMilitary(String army1, String army2, String army3, String army4, String army5, String army6, String army7, String army8) {
		
		int[] resourceConsume = new int[4];
		Army army = null;
		
		String[] armyInfo = null;
		int num = 0;
		
		// 军队消耗
		int foodConsume = 0;
		int oilConsume = 0;
		int moneyConsume = 0;
		
		if (army1 != null) {
			armyInfo = army1.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army2 != null) {
			armyInfo = army2.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army3 != null) {
			armyInfo = army3.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army4 != null) {
			armyInfo = army4.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army5 != null) {
			armyInfo = army5.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army6 != null) {
			armyInfo = army6.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army7 != null) {
			armyInfo = army7.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		if (army8 != null) {
			armyInfo = army8.split(":");
			
			army = armyService.getArmyByID(Integer.parseInt(armyInfo[0]));
			num = Integer.parseInt(armyInfo[1]);
			
			foodConsume += army.getCostFood() * num;
			oilConsume += army.getCostOil() * num;
			moneyConsume += army.getCostMoney() * num;
		}
		
		resourceConsume[0] = foodConsume;
		resourceConsume[1] = oilConsume;
		resourceConsume[2] = moneyConsume;
		resourceConsume[3] = foodConsume + oilConsume + moneyConsume;
		
		return resourceConsume;
	}

	public void updateCityMilitarySuccorBattleOrder(int[] cityMilitarySuccorIDs, int[] battleOrders) {
		if (cityMilitarySuccorIDs != null && cityMilitarySuccorIDs.length > 0 && battleOrders != null && battleOrders.length > 0 && cityMilitarySuccorIDs.length == battleOrders.length) {
			Arrays.sort(battleOrders);
			for (int i = 1; i <= 4; i++) {
				if (Arrays.binarySearch(battleOrders, i) < 0)
					throw new GameException("缺少第" + i + "援军，请重新调整支援队列。");
			}
			
			for (int i = 0; i < cityMilitarySuccorIDs.length; i++) {
				cityMilitarySuccorDAO.updateBattleOrderByCityMilitarySuccorID(battleOrders[i], cityMilitarySuccorIDs[i]);
			}
		}
	}
	
	public Integer getCityMilitaryEnemyNum(Integer posX, Integer posY) {
		Integer depoyQueueNum = depoyQueueDAO.getDepoyQueueNumByPosXAndPosY(posX, posY);
		Integer battleQueueNum = battleQueueDAO.getBattleQueueNumByPosXAndPosY(posX, posY);
		
		return depoyQueueNum + battleQueueNum;
	}
	
	public void nextAttackerAttack(Integer attackerCityMilitaryID, Integer mapID) {
		this.initBattleInfo(attackerCityMilitaryID, mapID);
	}
	
	public CityMilitarySuccor getCityMilitarySuccorByCityMilitaryID(Integer cityMilitaryID) {
		return cityMilitarySuccorDAO.getCityMilitarySuccorByCityMilitaryID(cityMilitaryID);
	}
	
	public void dismissCityMilitaryForCityHeroRunAway(Integer cityMilitaryID) {
		
		CityMilitary cityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		
		if(cityMilitary == null)
			throw new GameException("指定的军队不存在。");
		
		StringBuffer armyStrBuff = new StringBuffer();

		if (cityMilitary.getArmy1() != null) 
			armyStrBuff.append(cityMilitary.getArmy1()).append(";");
		if (cityMilitary.getArmy2() != null) 
			armyStrBuff.append(cityMilitary.getArmy2()).append(";");
		if (cityMilitary.getArmy3() != null)
			armyStrBuff.append(cityMilitary.getArmy3()).append(";");
		if (cityMilitary.getArmy4() != null)
			armyStrBuff.append(cityMilitary.getArmy4()).append(";");
		if (cityMilitary.getArmy5() != null)
			armyStrBuff.append(cityMilitary.getArmy5()).append(";");
		if (cityMilitary.getArmy6() != null)
			armyStrBuff.append(cityMilitary.getArmy6()).append(";");
		if (cityMilitary.getArmy7() != null)
			armyStrBuff.append(cityMilitary.getArmy7()).append(";");
		if (cityMilitary.getArmy8() != null)
			armyStrBuff.append(cityMilitary.getArmy8()).append(";");
		
		armyService.releaseCityMilitaryArmyPopulation(cityMilitary.getCityID(), armyStrBuff.substring(0, armyStrBuff.length() - 1));
		cityMilitaryDAO.deleteCityMilitaryByID(cityMilitaryID);
		
	}
	

	public IMilitaryDAO getMilitaryDAO() {
		return militaryDAO;
	}

	public void setMilitaryDAO(IMilitaryDAO militaryDAO) {
		this.militaryDAO = militaryDAO;
	}

	public ICityMilitaryDAO getCityMilitaryDAO() {
		return cityMilitaryDAO;
	}

	public void setCityMilitaryDAO(ICityMilitaryDAO cityMilitaryDAO) {
		this.cityMilitaryDAO = cityMilitaryDAO;
	}

	public IBattleDAO getBattleDAO() {
		return battleDAO;
	}

	public IBattleLogDAO getBattleLogDAO() {
		return battleLogDAO;
	}

	public void setBattleLogDAO(IBattleLogDAO battleLogDAO) {
		this.battleLogDAO = battleLogDAO;
	}
	
	public void setBattleDAO(IBattleDAO battleDAO) {
		this.battleDAO = battleDAO;
	}
	
	public IBattleWaitDAO getBattleWaitDAO() {
		return battleWaitDAO;
	}

	public void setBattleWaitDAO(IBattleWaitDAO battleWaitDAO) {
		this.battleWaitDAO = battleWaitDAO;
	}

	public IBattleQueueDAO getBattleQueueDAO() {
		return battleQueueDAO;
	}

	public void setBattleQueueDAO(IBattleQueueDAO battleQueueDAO) {
		this.battleQueueDAO = battleQueueDAO;
	}

	public IMapMonsterDAO getMapMonsterDAO() {
		return mapMonsterDAO;
	}

	public void setMapMonsterDAO(IMapMonsterDAO mapMonsterDAO) {
		this.mapMonsterDAO = mapMonsterDAO;
	}

	public IDepoyQueueDAO getDepoyQueueDAO() {
		return depoyQueueDAO;
	}

	public void setDepoyQueueDAO(IDepoyQueueDAO depoyQueueDAO) {
		this.depoyQueueDAO = depoyQueueDAO;
	}
	
	public ICityHeroDAO getCityHeroDAO() {
		return cityHeroDAO;
	}

	public void setCityHeroDAO(ICityHeroDAO cityHeroDAO) {
		this.cityHeroDAO = cityHeroDAO;
	}
	
	public ICityMilitarySuccorDAO getCityMilitarySuccorDAO() {
		return cityMilitarySuccorDAO;
	}

	public void setCityMilitarySuccorDAO(ICityMilitarySuccorDAO cityMilitarySuccorDAO) {
		this.cityMilitarySuccorDAO = cityMilitarySuccorDAO;
	}

	public IHeroSkillDAO getHeroSkillDAO() {
		return heroSkillDAO;
	}

	public void setHeroSkillDAO(IHeroSkillDAO heroSkillDAO) {
		this.heroSkillDAO = heroSkillDAO;
	}
	
	public IFriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(IFriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	public ISkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(ISkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}
	
	public IProcessQueueDAO getProcessQueueDAO() {
		return processQueueDAO;
	}

	public void setProcessQueueDAO(IProcessQueueDAO processQueueDAO) {
		this.processQueueDAO = processQueueDAO;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

	public IDepoyQueueService getDepoyQueueService() {
		return depoyQueueService;
	}

	public void setDepoyQueueService(IDepoyQueueService depoyQueueService) {
		this.depoyQueueService = depoyQueueService;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}
	
	public IMapService getMapService() {
		return mapService;
	}

	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}
	
	public IDeclareWarService getDeclareWarService() {
		return declareWarService;
	}

	public void setDeclareWarService(IDeclareWarService declareWarService) {
		this.declareWarService = declareWarService;
	}

	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
	public ITechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(ITechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public ISpyQueueService getSpyQueueService() {
		return spyQueueService;
	}

	public void setSpyQueueService(ISpyQueueService spyQueueService) {
		this.spyQueueService = spyQueueService;
	}

	public ICityDefenseService getCityDefenseService() {
		return cityDefenseService;
	}

	public void setCityDefenseService(ICityDefenseService cityDefenseService) {
		this.cityDefenseService = cityDefenseService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public IMarketService getMarketService() {
		return marketService;
	}
	
	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}
	
	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}
	
	public IColonizationService getColonizationService() {
		return colonizationService;
	}

	public void setColonizationService(IColonizationService colonizationService) {
		this.colonizationService = colonizationService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public IMonsterService getMonsterService() {
		return monsterService;
	}

	public void setMonsterService(IMonsterService monsterService) {
		this.monsterService = monsterService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public IStrongholdService getStrongholdService() {
		return strongholdService;
	}

	public void setStrongholdService(IStrongholdService strongholdService) {
		this.strongholdService = strongholdService;
	}

}