package com.war.dao;

import java.sql.SQLException;

import com.war.domain.GameCard;

public interface IGameCardDAO {

	/**
	 * 创建游戏卡号
	 * @param gameCard
	 */
	public void createGameCard(GameCard gameCard);

	/**
	 * 批量创建游戏卡号
	 * @param gameCardArray
	 * @throws SQLException
	 */
	public void createGameCardBatch(GameCard[] gameCardArray) throws SQLException;
	
	/**
	 * 根据游戏卡号及类型更新状态
	 * @param gameCardNO
	 * @param type
	 * @param state
	 */
	public void updateStateByGameCardNOAndType(String gameCardNO, Integer type, Integer state);

	/**
	 * 根据类型获得最大卡号编号
	 * @param type
	 * @return
	 */
	public Integer getMaxGameCardIDByType(Integer type);
	
	/**
	 * 根据游戏卡号及类型获得游戏卡号
	 * @param gameCardNO
	 * @param type
	 * @return
	 */
	public GameCard getGameCardByGameCardNOAndType(String gameCardNO, Integer type);

}