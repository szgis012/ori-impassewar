package com.war.script.impl;

import com.war.common.SpringService;
import com.war.constant.OperationLogConstant;
import com.war.script.IGameScriptContext;
import com.war.script.IGameScriptContextFactory;
import com.war.service.IArmyService;
import com.war.service.IBattleService;
import com.war.service.IChatService;
import com.war.service.ICityService;
import com.war.service.IDeclareWarService;
import com.war.service.IEquipmentService;
import com.war.service.IGameScriptService;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.IMapService;
import com.war.service.IMilitaryService;
import com.war.service.IOperationLogService;
import com.war.service.IOrdnanceService;
import com.war.service.IPlayerService;
import com.war.service.IProcessQueueService;
import com.war.service.IProductionQueueService;
import com.war.service.IReportService;
import com.war.service.ITaskService;
import com.war.service.ITechnologyService;
import com.war.service.ITradeQueueService;
import com.war.service.ITreasureQueueService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityDefenseService;
import com.war.service.building.IMarketService;

/**
 * IGameScriptContext工厂类
 *
 * @author ghleee
 * @version 1.0
 */
public class GameScriptContextFactory implements IGameScriptContextFactory{
	
	IPlayerService playerService;
	GroovyGameScriptContext gameScriptContext;
	
	public IGameScriptContext getContext(Integer playerID,int processType,Object params){
		
		//为了避免循环引用，在这里初始化上下文使用的service
		if(gameScriptContext == null){
			gameScriptContext = new GroovyGameScriptContext();
			gameScriptContext.setCityDefenseService((ICityDefenseService) SpringService.getBean("cityDefenseService"));
			gameScriptContext.setCityService((ICityService) SpringService.getBean("cityService"));
			gameScriptContext.setGameScriptService((IGameScriptService) SpringService.getBean("gameScriptService"));
			gameScriptContext.setOrdnanceService((IOrdnanceService) SpringService.getBean("ordnanceService"));
			gameScriptContext.setTreasureService((ITreasureService) SpringService.getBean("treasureService"));
			gameScriptContext.setProcessQueueService((IProcessQueueService) SpringService.getBean("processQueueService"));
			gameScriptContext.setProductionQueueService((IProductionQueueService) SpringService.getBean("productionQueueService"));
			gameScriptContext.setTreasureQueueService((ITreasureQueueService) SpringService.getBean("treasureQueueService"));
			gameScriptContext.setTradeQueueService( (ITradeQueueService) SpringService.getBean("tradeQueueService"));
			gameScriptContext.setHeroService( (IHeroService) SpringService.getBean("heroService"));
			gameScriptContext.setMilitaryService( (IMilitaryService) SpringService.getBean("militaryService"));
			gameScriptContext.setGuildService( (IGuildService) SpringService.getBean("guildService"));
			gameScriptContext.setReportService( (IReportService) SpringService.getBean("reportService"));
			gameScriptContext.setDeclareWarService( (IDeclareWarService) SpringService.getBean("declareWarService"));
			gameScriptContext.setTaskService( (ITaskService) SpringService.getBean("taskService"));
			gameScriptContext.setEquipmentService( (IEquipmentService) SpringService.getBean("equipmentService"));
			gameScriptContext.setChatService( (IChatService)SpringService.getBean("chatService"));
			gameScriptContext.setMarketService((IMarketService) SpringService.getBean("marketService"));
			gameScriptContext.setMapService( (IMapService) SpringService.getBean("mapService"));
			gameScriptContext.setBattleService( (IBattleService) SpringService.getBean("battleService"));
			gameScriptContext.setOperationLogService( (IOperationLogService) SpringService.getBean("operationLogService"));
			gameScriptContext.setTechnologyService( (ITechnologyService) SpringService.getBean("technologyService"));
			gameScriptContext.setArmyService( (IArmyService) SpringService.getBean("armyService"));
			
			playerService = (IPlayerService) SpringService.getBean("playerService");
			gameScriptContext.setPlayerService( playerService );
			
		}
		
		//初始化脚本执行上下文
		gameScriptContext.initGroovyGameScriptContext(playerService.getPlayerByID(playerID), processType,params);
		
		return gameScriptContext;
	}

}
