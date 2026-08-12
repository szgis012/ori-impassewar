package com.war.service;

public interface IQuartzService {

	/**
	 * 处理每10分钟事件
	 */
	public void handleTenMinutesEvent();
	
	/**
	 * 处理每小时事件
	 */
	public void handleHourEvent();
	
	/**
	 * 处理每6小时事件
	 */
	public void handleSixHoursEvent();
	
	/**
	 * 处理每天事件
	 */
	public void handleDayEvent();
	
	/**
	 * 处理对进程队列
	 */
	public void handleProcessQueue();
	
	/**
	 * 处理交易队列
	 */
	public void handleTradeQueue();

	/**
	 * 处理生产，招募队列
	 */
	public void handleProductionQueue();
	
	/**
	 * 处理出征队列
	 */
	public void handleDepoyQueue();
	
	/**
	 * 处理侦察队列
	 */
	public void handleSpyQueue();

	/**
	 * 处理战斗信息
	 */
	public void handleBattleInfo();

	/**
	 * 处理计算城市资源
	 */
	public void handleComputeCityResource();
	
	/**
	 * 处理刷新地图野怪
	 */
	public void handleRefreshMapMonster();
	
	/**
	 * 处理系统事件
	 */
	public void handleSystemEvent();
	
	/**
	 * 处理系统公告事件
	 */
	public void handleSystemNoticeEvent();
	
}
