package com.war.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.war.common.CacheService;
import com.war.common.ConfigurationService;
import com.war.common.SpringService;
import com.war.common.SystemConfig;
import com.war.constant.CacheConstant;
import com.war.domain.CityExt;
import com.war.domain.CityHeroExt;
import com.war.domain.GuildExt;
import com.war.quartz.BattleQuartz;
import com.war.quartz.DayQuartz;
import com.war.quartz.MonsterQuartz;
import com.war.quartz.SystemNoticeQuartz;
import com.war.quartz.SystemQuartz;
import com.war.quartz.DepoyQueueQuartz;
import com.war.quartz.HourQuartz;
import com.war.quartz.ProcessQueueQuartz;
import com.war.quartz.ResourceQuartz;
import com.war.quartz.SpyQueueQuartz;
import com.war.quartz.TenMinutesQuartz;
import com.war.quartz.TradeQueueQuartz;
import com.war.quartz.ProductionQueueQuartz;

import com.war.service.IArmyService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IDefenseService;
import com.war.service.IEquipmentService;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.IHonorService;
import com.war.service.IMonsterService;
import com.war.service.IOrdnanceService;
import com.war.service.IPlayerService;
import com.war.service.IRankService;
import com.war.service.ISystemService;
import com.war.service.ITaskService;
import com.war.service.ITechnologyService;
import com.war.service.ITreasureService;
import com.war.socket.FlashSecurityXMLSocketServer;
import com.war.socket.battle.BattleSocketServer;
import com.war.socket.game.GameSocketServer;

public class InitSystemListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(InitSystemListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		//初始化SpringService.servletContext
		SpringService.setServletContext(servletContext);
		
		// 初始化缓存   
		this.initCache();
		
		// 初始化系统参数
		this.initSysConfig();
		
		// 开启进程队列任务
		ProcessQueueQuartz.run();
		
		// 开启交易队列任务
		TradeQueueQuartz.run();
		
		// 开启生产队列任务
		ProductionQueueQuartz.run();
		
		// 开启出征队列任务
		DepoyQueueQuartz.run();
		
		// 开启侦察队列任务
		SpyQueueQuartz.run();
		
		// 开启战斗任务
		BattleQuartz.run();
		
		// 开启资源计算任务
		ResourceQuartz.run();
		
		// 开启每10分钟事件任务
		TenMinutesQuartz.run();
		
		// 开启每小时事件任务
		HourQuartz.run();
		
		// 开启每天事件任务
		DayQuartz.run();
		
		// 开始野怪任务
		MonsterQuartz.run();
		
		// 开启数据历史任务
		SystemQuartz.run();
		
		// 开启系统公告任务
		SystemNoticeQuartz.run();
		
		// 开启Socket任务
		// SocketQuartz.run();
		
		//开启Flash安全策略认证服务
		new Thread(new FlashSecurityXMLSocketServer()).start();
		//聊天Socket
		new Thread(new GameSocketServer()).start();
		//战斗Socket
		new Thread(new BattleSocketServer()).start();
	} 
	
	public void initCache(){
		try{
			
			// 军衔缓存
			IHonorService honorService = (IHonorService)SpringService.getBean("honorService");
			CacheService.putToCache(CacheConstant.HONOR_MAP, honorService.initHonorMap());
			
			// 建筑缓存
			IBuildingService buildingService = (IBuildingService)SpringService.getBean("buildingService");
			CacheService.putToCache(CacheConstant.BUILDINGS_MAP, buildingService.initBuildingsMap());
			CacheService.putToCache(CacheConstant.BUILDING_LIST, buildingService.initBuildingList());
			CacheService.putToCache(CacheConstant.BUILDINGS_WITH_CONSTRANT_MAP, buildingService.initBuildingsMapWithConstraintDepend());
			
			// 科技缓存
			ITechnologyService technologyService = (ITechnologyService)SpringService.getBean("technologyService");
			CacheService.putToCache(CacheConstant.TECHNOLOGIES_MAP, technologyService.initTechnologiesMap());
			CacheService.putToCache(CacheConstant.TECHNOLOGIES_WITH_CONSTRANT_MAP, technologyService.initTechnologiesMapWithConstraintDepend());
			CacheService.putToCache(CacheConstant.TECHNOLOGIES_LIST_BY_TYPE, technologyService.initTechnologiesListByType());

			// 城防缓存
			IDefenseService defenseService = (IDefenseService)SpringService.getBean("defenseService");
			CacheService.putToCache(CacheConstant.DEFENSE_LIST , defenseService.initDefenseList());
			CacheService.putToCache(CacheConstant.DEFENSE_MAP , defenseService.initDefenseMap());
			
			// 士兵缓存
			IArmyService armyService = (IArmyService)SpringService.getBean("armyService");
			CacheService.putToCache(CacheConstant.ARMIES_MAP, armyService.initArmiesMap());
			CacheService.putToCache(CacheConstant.FREE_UNION_ARMY_LIST, armyService.initFreeUnionArmyList());
			CacheService.putToCache(CacheConstant.UNION_EMPIRE_ARMY_LIST, armyService.initUnionEmpireArmyList());
			
			// 装备缓存
			IEquipmentService equipmentService = (IEquipmentService)SpringService.getBean("equipmentService");
			CacheService.putToCache(CacheConstant.EQUIPMENTS_MAP, equipmentService.initEquipmentsMap());
			
			// 宝物缓存
			ITreasureService treasureService = (ITreasureService)SpringService.getBean("treasureService");
			CacheService.putToCache(CacheConstant.TREASURES_MAP, treasureService.initTreasuresMap());
			CacheService.putToCache(CacheConstant.TREASURE_LIST_BY_CATEOGRY_MAP, treasureService.initTreasureListByCategoryMap());
			CacheService.putToCache(CacheConstant.TREASURE_LIST_BY_TYPE_MAP, treasureService.initTreasureListByTypeMap());
			CacheService.putToCache(CacheConstant.RECOMMEND_TREASURE_LIST, treasureService.initRecommendTreasureList());
			
			// 军械缓存
			IOrdnanceService ordnanceService = (IOrdnanceService)SpringService.getBean("ordnanceService");
			CacheService.putToCache(CacheConstant.ORDNANCES_MAP, ordnanceService.initOrdnancesMap());
			CacheService.putToCache(CacheConstant.FREE_UNION_ORDNANCE_LIST, ordnanceService.initFreeUnionOrdnanceList());
			CacheService.putToCache(CacheConstant.UNION_EMPIRE_ORDNANCES_LIST, ordnanceService.initUnionEmpireOrdnanceList());
			
			// 英雄技能缓存
			IHeroService heroService = (IHeroService)SpringService.getBean("heroService");
			CacheService.putToCache(CacheConstant.SKILLS_MAP, heroService.initSkillsMap());
			
			// 野怪缓存 & 怪物掉落缓存
			IMonsterService monsterService = (IMonsterService)SpringService.getBean("monsterService");
			CacheService.putToCache(CacheConstant.MONSTER_MAP, monsterService.initMonsterMap());
			CacheService.putToCache(CacheConstant.MONSTER_DROP, monsterService.initMonsterDropMap());
			
			// 任务缓存
			ITaskService taskService = (ITaskService)SpringService.getBean("taskService");
			CacheService.putToCache(CacheConstant.TASKS_MAP, taskService.initTasksMap());
			
			// 系统公告缓存
			ISystemService systemService = (ISystemService)SpringService.getBean("systemService");
			CacheService.putToCache(CacheConstant.SYSTEM_NOTICE_LIST, systemService.initSystemNoticeList());
			
			// 玩家编号-玩家名称对应缓存
			IPlayerService playerService = (IPlayerService)SpringService.getBean("playerService");
			CacheService.putToCache(CacheConstant.PLAYERID_PLAYERNAME_MAP, playerService.initPlayerIDPlayerNameMap());
			
			// 城市编号-城市名称对应缓存
			ICityService cityService = (ICityService)SpringService.getBean("cityService");
			CacheService.putToCache(CacheConstant.CITYID_CITYNAME_MAP, cityService.initCityIDCityNameMap());
			CacheService.putToCache(CacheConstant.CITYID_PLAYERID_MAP, cityService.initCityIDPlayerIDMap());
			CacheService.putToCache(CacheConstant.PLAYERID_CITYID_MAP, cityService.initPlayerIDCityIDMap());
			
			// 军团编号-军团名称对应缓存
			IGuildService guildService = (IGuildService)SpringService.getBean("guildService");
			CacheService.putToCache(CacheConstant.GUILDID_GUILDNAME_MAP, guildService.initGuildIDGuildNameMap());
			
			
			// 排名缓存
			IRankService rankService = (IRankService)SpringService.getBean("rankService");
			rankService.refreshPlayerRank();
			rankService.refreshGuildRenownAndRank();
			rankService.refreshCityPopulationRank();
			
		}catch(Exception e){
			logger.error("异常：", e);
		}
	}
	
	private void initSysConfig(){
		
		// 初始化系统设置
		String registerFilterWordString = ConfigurationService.getProperty("RegisterFilterWord");
		String chatFilterWordString = ConfigurationService.getProperty("ChatFilterWord");
		SystemConfig.registerFilterWordArray = registerFilterWordString.split(",");
		SystemConfig.chatFilterWordArray = chatFilterWordString.split(",");
		
		
		// 初始化军团扩展信息
		SystemConfig.defaultGuildExt = new GuildExt();
		SystemConfig.defaultGuildExt.setGuildID(0);
		SystemConfig.defaultGuildExt.setMilitaryAttackAdd(0);
		SystemConfig.defaultGuildExt.setMilitarySpeedAdd(0);
		SystemConfig.defaultGuildExt.setMilitaryAttackMinus(0);
		SystemConfig.defaultGuildExt.setArmyLifeAdd(0);
		SystemConfig.defaultGuildExt.setArmyAttackMinus(0);
		SystemConfig.defaultGuildExt.setArmyRangeMinus(0);
		SystemConfig.defaultGuildExt.setArmySpeedMinus(0);
		SystemConfig.defaultGuildExt.setTruckLifeAdd(0);
		SystemConfig.defaultGuildExt.setTruckAttackMinus(0);
		SystemConfig.defaultGuildExt.setTruckRangeMinus(0);
		SystemConfig.defaultGuildExt.setTruckSpeedMinus(0);
		SystemConfig.defaultGuildExt.setAirplaneLifeAdd(0);
		SystemConfig.defaultGuildExt.setAirplaneAttackMinus(0);
		SystemConfig.defaultGuildExt.setAirplaneRangeMinus(0);
		SystemConfig.defaultGuildExt.setAirplaneSpeedMinus(0);
		
		// 初始化城市扩展信息
		SystemConfig.defaultCityExt = new CityExt();
		SystemConfig.defaultCityExt.setCityID(0);
		SystemConfig.defaultCityExt.setTechArmyAttack(0);
		SystemConfig.defaultCityExt.setTechArmyDefense(0);
		SystemConfig.defaultCityExt.setTechArmySpeed(0);
		SystemConfig.defaultCityExt.setTechArmyRange(0);
		SystemConfig.defaultCityExt.setTechTruckAttack(0);
		SystemConfig.defaultCityExt.setTechTruckDefense(0);
		SystemConfig.defaultCityExt.setTechTruckSpeed(0);
		SystemConfig.defaultCityExt.setTechTruckRange(0);
		SystemConfig.defaultCityExt.setTechAirplaneAttack(0);
		SystemConfig.defaultCityExt.setTechAirplaneDefense(0);
		SystemConfig.defaultCityExt.setTechAirplaneSpeed(0);
		SystemConfig.defaultCityExt.setTechAirplaneRange(0);
		SystemConfig.defaultCityExt.setTechCarryAdd(0);
		SystemConfig.defaultCityExt.setTechDefenseAttackAdd(0);
		SystemConfig.defaultCityExt.setTechWoundedArmyRate(0);
		SystemConfig.defaultCityExt.setTechProtectResourcePercent(0);
		SystemConfig.defaultCityExt.setLastManageTime(new Date());
		
		// 初始化城市英雄扩展信息
		SystemConfig.defaultCityHeroExt = new CityHeroExt();
		SystemConfig.defaultCityHeroExt.setCityHeroID(0);
		SystemConfig.defaultCityHeroExt.setCommandEquipmentAdd(0);
		SystemConfig.defaultCityHeroExt.setCommandTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setDefenseEquipmentAdd(0);
		SystemConfig.defaultCityHeroExt.setDefenseTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setMindEquipmentAdd(0);
		SystemConfig.defaultCityHeroExt.setMindTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setExecutivepowerEquipmentAdd(0);
		SystemConfig.defaultCityHeroExt.setExecutivepowerTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setReinGuildAdd(0);
		SystemConfig.defaultCityHeroExt.setReinTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setExpGuildAdd(0);
		SystemConfig.defaultCityHeroExt.setExpTreasureAdd(0);
		SystemConfig.defaultCityHeroExt.setMilitaryAttackAdd(0);
		SystemConfig.defaultCityHeroExt.setMilitaryDefenseAdd(0);
		SystemConfig.defaultCityHeroExt.setMilitaryLifeAdd(0);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}
	
}
