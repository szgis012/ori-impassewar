package com.war.dao;

import java.util.List;

import com.war.domain.GuildRewardReceiveLog;

public interface IGuildRewardReceiveLogDAO {
	
	/**
	 * 创建军团奖励领取日志信息
	 * @param guildRewardReceiveLog
	 * @return
	 */
	public Integer createGuildRewardReceiveLog(GuildRewardReceiveLog guildRewardReceiveLog);

	/**
	 * 更新军团奖励领取日志信息
	 * @param guildRewardReceiveLog
	 */
	public void updateGuildRewardReceiveLog(GuildRewardReceiveLog guildRewardReceiveLog);

	/**
	 * 根据编号删除军团奖励领取日志信息
	 * @param playerID
	 */
	public void deleteGuildRewardReceiveLogByID(Integer playerID);
	
	/**
	 * 根据编号获得军团奖励领取日志信息
	 * @param playerID
	 * @return
	 */
	public GuildRewardReceiveLog getGuildRewardReceiveLogByID(Integer playerID);

	/**
	 * 获得军团奖励领取日志信息列表
	 * @return
	 */
	public List<GuildRewardReceiveLog> getGuildRewardReceiveLogList();

}
