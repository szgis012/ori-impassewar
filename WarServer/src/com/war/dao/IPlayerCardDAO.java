package com.war.dao;

import com.war.domain.PlayerCard;

public interface IPlayerCardDAO {

	/**
	 * 创建玩家游戏卡使用
	 * @param playerCard
	 */
	public void createPlayerCard(PlayerCard playerCard);

	/**
	 * 根据玩家编号及类型获得玩家游戏卡使用
	 * @param playerID
	 * @param type
	 * @return
	 */
	public PlayerCard getPlayerCardByPlayerIDAndType(Integer playerID,Integer type);

}