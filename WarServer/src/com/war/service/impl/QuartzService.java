package com.war.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.war.constant.BattleConstant;
import com.war.constant.DepoyTypeConstant;
import com.war.constant.ProductionQueueTypeConstant;
import com.war.constant.QueueTypeConstant;
import com.war.constant.TradeConstant;
import com.war.domain.Battle;
import com.war.domain.BattleWait;
import com.war.domain.DepoyQueue;
import com.war.domain.ProcessQueue;
import com.war.domain.ProductionQueue;
import com.war.domain.SpyQueue;
import com.war.domain.TradeQueue;
import com.war.domain.TreasureQueue;
import com.war.service.IArmyService;
import com.war.service.IBattleService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IColonizationService;
import com.war.service.IDataHistoryService;
import com.war.service.IDeclareWarService;
import com.war.service.IDepoyQueueService;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.IMilitaryService;
import com.war.service.IMonsterService;
import com.war.service.IPlayerService;
import com.war.service.IProcessQueueService;
import com.war.service.IProductionQueueService;
import com.war.service.IQuartzService;
import com.war.service.IRankService;
import com.war.service.IReportService;
import com.war.service.ISpyQueueService;
import com.war.service.ISystemService;
import com.war.service.ITaskService;
import com.war.service.ITechnologyService;
import com.war.service.ITradeQueueService;
import com.war.service.ITreasureQueueService;
import com.war.service.building.IArmoryService;
import com.war.service.building.ICityCenterService;
import com.war.service.building.ICityDefenseService;
import com.war.service.building.IMarketService;

public class QuartzService implements IQuartzService {

	private IBuildingService buildingService;
	private ITechnologyService technologyService;
	private IProcessQueueService processQueueService;
	private IProductionQueueService productionQueueService;
	private ICityCenterService cityCenterService;
	private IArmoryService armoryService;
	private IMarketService marketService;
	private ITradeQueueService tradeQueueService;
	private IHeroService heroService;
	private IDepoyQueueService depoyQueueService;
	private IMilitaryService militaryService ;
	private ICityService cityService;
	private IBattleService battleService;
	private ICityDefenseService cityDefenseService;
	private IRankService rankService;
	private ITreasureQueueService treasureQueueService ;
	private IDeclareWarService declareWarService;
	private IMonsterService monsterService;
	private IPlayerService playerService;
	private ISpyQueueService spyQueueService;
	private IDataHistoryService dataHistoryService;
	private IReportService reportService;
	private IColonizationService colonizationService;
	private ITaskService taskService;
	private IGuildService guildService;
	private IArmyService armyService;
	private ISystemService systemService;
	
	private static Logger logger = Logger.getLogger(QuartzService.class);
	
	public void handleTenMinutesEvent(){
		
		// 解除已到达结束时间宣战关系
		this.handleDeleteFinishedDeclareWarList();
		
		// 解除到期宝物效果
		this.handleTreasureQueue();
		
		// 处理城市治安影响
		cityService.handleCitySecurityEffect();
		
		// 伤兵遣散
		armyService.handleAutoDismissedCityWoundedArmy();
		
		// 处理支援军队到期
		militaryService.handleCityMilitarySuccorOverTime();
		
		// 解除到期殖民
		colonizationService.handleFinishedColonization();
	}
	
	private void handleDeleteFinishedDeclareWarList(){
		declareWarService.deleteFinishedDeclareWarList();
	}
	
	private void handleTreasureQueue(){
		List<TreasureQueue> list =  treasureQueueService.getFinishedTreasureQueueList();
		
		for(int i=0; i<list.size(); i++){
			handleTreasure(list.get(i));
		}
	}
	
	public void handleHourEvent(){
		
		//清空城市候选英雄
		heroService.cleanCityCandidacyList();
		
		//刷新排名
		rankService.refreshPlayerRank();
		rankService.refreshGuildRenownAndRank();
		rankService.refreshCityConstructionPointRank();
		rankService.refreshCityTechnologyPointRank();
		rankService.refreshCityPopulationRank();
		
		//刷新新手保护
		playerService.refreshFreshmanProtect();
		
		//增加城市执政官经验
		heroService.addCityOfficerExp();
		
		//资源耗尽对治安的影响
		//cityService.handleCityResourceEffect();
		
		//增加城市治安
		cityService.handleBatchAddCitySecurity();
		
		//增加英雄体力
		heroService.handleBatchAddCityHeroStamina();
		
		//处理英雄忠诚过低逃跑
		heroService.handleCityHeroRunAway();
	}
	
	public void handleSixHoursEvent(){
		
	}
	
	public void handleDayEvent(){
		//删除系统中所有过期的报告
		reportService.deleteOverdueReport();
		//更新系统的每日任务信息
		taskService.refreshDailyTask();
		//重置殖民是否已经征收标志
		colonizationService.resetColonizationHaveImpose();
		// 更新用户每日奖励状态为没有领取奖励
		playerService.changePlayersHaveReceiveDailyRewardToNotReceive();
	}
	
	public void handleProcessQueue() {
		
		List<ProcessQueue> finishProcessQueueList = processQueueService.getFinishedProcessQueueList();
		
		for(int i=0;i<finishProcessQueueList.size();i++){
			handleProcess(finishProcessQueueList.get(i));
		}
		
	}
	
	/**
	 * 对单个进程队列进行处理
	 * @param processQueue
	 */
	private void handleProcess(ProcessQueue processQueue) {
		
		int type = processQueue.getType();
		
		switch(type){
			case QueueTypeConstant.QUEUE_BUILD_UPGRADE:
			case QueueTypeConstant.QUEUE_BACKOUT_BUILDING:
			//建造，升级，拆除
				buildingService.buildingFinished(processQueue);
				break;
			//升级科技
			case QueueTypeConstant.QUEUE_TECH_UPGRADE:
				technologyService.researchFinished(processQueue);
				break;
			//军团科技升级
			case QueueTypeConstant.QUEUE_GUILD_TECH_UPGRADE:
				guildService.finishResearchGuildTechnology(processQueue);
				break;
			// 指挥官训练
			case QueueTypeConstant.QUEUE_TRAIN_HREO:
				heroService.cityHeroTrainingFinished(processQueue);
				break;
		}
	}
	

	public void handleTradeQueue() {
		
		List<TradeQueue> tradeQueueList = tradeQueueService.getArrivedTradeQueueList();
		
		for(int i=0;i<tradeQueueList.size();i++){
			handleTrade(tradeQueueList.get(i));
		}
		
	}
	
	/**
	 * 对单个交易队列进行处理
	 * @param tradeQueue
	 */
	private void handleTrade(TradeQueue tradeQueue){
		
		int type = tradeQueue.getType();
		
		switch(type){
			case TradeConstant.RESOURCE_TRANSPORTATION:
				//资源运输
				marketService.finishResourceTransportation(tradeQueue);
				break;
				
			case TradeConstant.RESOURCE_TRANSPORTATION_RETURN:
				//资源运输商人返回
				marketService.resourceTransportationReturn(tradeQueue);
				break;
				
			case TradeConstant.TARDE_RETURN:
				//资源交易商人返回
				marketService.finishResourceTrade(tradeQueue);
				break;

			default:
				break;
		}
		
	}
	
	public void handleProductionQueue() {
		List<ProductionQueue> productionQueueList = productionQueueService.getFinishedProductionQueueList();
		
		for(int i=0; i<productionQueueList.size(); i++){
			handleProduction(productionQueueList.get(i));
		}
	}
	
	/**
	 * 处理单个生产，招募进程
	 * @param productionQueue
	 */
	private void handleProduction(ProductionQueue productionQueue) {
		int type = productionQueue.getType();
		
		switch(type){
			//征召市民
			case ProductionQueueTypeConstant.PROCESS_ENLIST_CITIZEN:
				cityCenterService.finishEnlistCitizen(productionQueue);
				break;
			//生产军械	
			case ProductionQueueTypeConstant.PROCESS_PRODUCE_ORDNANCE:
				armoryService.finishProduceOrdnance(productionQueue);
				break;	
			//建造城防	
			case ProductionQueueTypeConstant.PROCESS_BUILD_DEFENSE:
				cityDefenseService.finishBuildDefense(productionQueue);
				break;		
		}
		
	}
	
	public void handleDepoyQueue(){
		List<DepoyQueue> list = depoyQueueService.getFinishDepoyQueueList();
		
		for(int i=0; i<list.size(); i++){
			handleDepoy(list.get(i));
		}
	}
	
	/**
	 * 处理单个出征进程
	 * @param dq
	 */
	private void handleDepoy(DepoyQueue dq){
		int type = dq.getType();
		
		switch(type){
			//侦察
			case DepoyTypeConstant.SPY:
				break;
			//攻击	
			case DepoyTypeConstant.ATTACK:
				militaryService.finishAttackWait(dq);
				break;
			//派遣	
			case DepoyTypeConstant.DISPATCH:
				militaryService.finishDispatchWait(dq);
				break;
			//返回
			case DepoyTypeConstant.RETURN:
				militaryService.finishReturnWait(dq);
				break;
		}
	}
	
	/**
	 * 处理侦察完成的事件
	 */
	public void handleSpyQueue(){
		List<SpyQueue> list = spyQueueService.getFinishSpyQueueList();
		
		for(int i=0; i<list.size(); i++){
			militaryService.finishSpyWait(list.get(i));
		}
	}
	
	public void handleBattleInfo() {
		
		// 处理战斗信息
		List<Battle> battleList = battleService.getRoundFinishedBattleList(BattleConstant.ROUND_TIME);
		for(int i=0;i<battleList.size();i++) {
			battleService.roundFinished(battleList.get(i).getBattleID(), battleList.get(i).getRound()%2 == 0 ? 2 : 1);
		}
		
		// 处理战斗等待
		List<BattleWait> battleWaitList = battleService.getIntervalFinishedBattleWaitList();
		if (battleWaitList != null && !battleWaitList.isEmpty()) {
			for (int i=0; i<battleWaitList.size(); i++) {
				battleService.finishBattleIntervalWait(battleWaitList.get(i).getBattleWaitID());
			}
		}
	}

	public void handleRefreshMapMonster(){
		monsterService.generateMapMonsterList();
	}
	
	public void handleComputeCityResource(){
		cityService.computeCityResource();
	}
	
	private void handleTreasure(TreasureQueue tq){
		treasureQueueService.handleTreasureQueue(tq);
	}

	public void handleSystemEvent() {
		//保存数据历史
		dataHistoryService.saveDataHistory();
	}
	
	public void handleSystemNoticeEvent() {
		systemService.sendSystemNotice();
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
	
	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public IMarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}

	public ITradeQueueService getTradeQueueService() {
		return tradeQueueService;
	}

	public void setTradeQueueService(ITradeQueueService tradeQueueService) {
		this.tradeQueueService = tradeQueueService;
	}

	public IHeroService getHeroService() {
		return heroService;
	}

	public void setHeroService(IHeroService heroService) {
		this.heroService = heroService;
	}

	public IProductionQueueService getProductionQueueService() {
		return productionQueueService;
	}

	public void setProductionQueueService(
			IProductionQueueService productionQueueService) {
		this.productionQueueService = productionQueueService;
	}

	public ICityCenterService getCityCenterService() {
		return cityCenterService;
	}

	public void setCityCenterService(ICityCenterService cityCenterService) {
		this.cityCenterService = cityCenterService;
	}

	public IArmoryService getArmoryService() {
		return armoryService;
	}

	public void setArmoryService(IArmoryService armoryService) {
		this.armoryService = armoryService;
	}

	public IDepoyQueueService getDepoyQueueService() {
		return depoyQueueService;
	}

	public void setDepoyQueueService(IDepoyQueueService depoyQueueService) {
		this.depoyQueueService = depoyQueueService;
	}

	public IMilitaryService getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(IMilitaryService militaryService) {
		this.militaryService = militaryService;
	}

	public IBattleService getBattleService() {
		return battleService;
	}

	public void setBattleService(IBattleService battleService) {
		this.battleService = battleService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public ICityDefenseService getCityDefenseService() {
		return cityDefenseService;
	}

	public void setCityDefenseService(ICityDefenseService cityDefenseService) {
		this.cityDefenseService = cityDefenseService;
	}

	public IRankService getRankService() {
		return rankService;
	}

	public void setRankService(IRankService rankService) {
		this.rankService = rankService;
	}

	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}

	public IDeclareWarService getDeclareWarService() {
		return declareWarService;
	}

	public void setDeclareWarService(IDeclareWarService declareWarService) {
		this.declareWarService = declareWarService;
	}

	public IMonsterService getMonsterService() {
		return monsterService;
	}

	public void setMonsterService(IMonsterService monsterService) {
		this.monsterService = monsterService;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public ISpyQueueService getSpyQueueService() {
		return spyQueueService;
	}

	public void setSpyQueueService(ISpyQueueService spyQueueService) {
		this.spyQueueService = spyQueueService;
	}

	public IDataHistoryService getDataHistoryService() {
		return dataHistoryService;
	}

	public void setDataHistoryService(IDataHistoryService dataHistoryService) {
		this.dataHistoryService = dataHistoryService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	
	public IColonizationService getColonizationService() {
		return colonizationService;
	}

	public void setColonizationService(IColonizationService colonizationService) {
		this.colonizationService = colonizationService;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

}
