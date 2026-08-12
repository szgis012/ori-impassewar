package com.war.dao;

import java.util.List;

import com.war.domain.GuildPlayer;

public interface IGuildPlayerDAO {

	/**
	 * 创建军团成员
	 * @param guildPlayer
	 */
	public void createGuildPlayer(GuildPlayer guildPlayer);

	/**
	 * 更新军团成员
	 * @param guildPlayer
	 */
	public void updateGuildPlayer(GuildPlayer guildPlayer);

	/**
	 * 根据军团编号及玩家编号删除军团成员
	 * @param guildPlayer
	 */
	public void deleteGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID);
	
	/**
	 * 根据军团编号删除军团所有玩家
	 * @param guildID
	 */
	public void deleteGuildPlayersByGuildID(Integer guildID);

	/**
	 * 根据玩家编号获得军团玩家
	 * @param playerID
	 * @return
	 */
	public GuildPlayer getGuildPlayerByID(Integer guildPlayerID);
	
	/**
	 * 根据军团编号及玩家编号获得军团玩家
	 * @param guildID
	 * @param playerID
	 * @return
	 */
	public GuildPlayer getGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID);
	
	/**
	 * 根据军团编号获得军团成员数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildPlayerAmountByGuildID(Integer guildID);
	
	/**
	 * 根据军团编号获得军团成员列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildPlayer> getGuildPlayerPagingListByGuildID(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 根据军团编号获得军团成员列表
	 * @param guildID
	 * @return
	 */
	public List<GuildPlayer> getGuildPlayerListByGuildID(Integer guildID);

	/**
	 * 获得军团玩家列表
	 * @return
	 */
	public List<GuildPlayer> getGuildPlayerList();

	/**
	 * 根据编号更新是否允许驻军状态
	 * @param guildPlayerID
	 * @param allowGarrison
	 */
	public void updateAllowGarrisonByGuildPlayerID(Integer guildPlayerID, Integer allowGarrison);

	/**
	 * 根据军团编号和玩家编号更新是否允许驻军状态
	 * @param playerID
	 * @param guildID
	 * @param allowGarrison
	 */
	public void updateAllowGarrisonByPlayerIDAndGuildID(Integer playerID, Integer guildID, Integer allowGarrison);

}