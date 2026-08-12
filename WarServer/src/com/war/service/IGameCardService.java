package com.war.service;

public interface IGameCardService {

	/**
	 * 激活游戏卡
	 * @param playerID
	 * @param gameCardNO
	 * @param type
	 */
	public void activateGameCard(Integer playerID, String gameCardNO, Integer type);
	
	/**
	 * 生成游戏卡
	 * @param type 类型
	 * @param num 数量
	 */
	public void generateGameCard(Integer type, Integer num);
	
}
