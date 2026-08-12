package com.war.dao;

import java.util.List;

import com.war.domain.GuildIncExpHistory;

public interface IGuildIncExpHistoryDAO {

	public Integer createGuildIncExpHistory(GuildIncExpHistory guildIncExpHistory);

	public void updateGuildIncExpHistory(GuildIncExpHistory guildIncExpHistory);

	public void deleteGuildIncExpHistoryByID(Integer guildIncExpHistoryID);

	public GuildIncExpHistory getGuildIncExpHistoryByID(Integer guildIncExpHistoryID);

	public List<GuildIncExpHistory> getGuildIncExpHistoryList();

	/**
	 * 
	 * 取得收入/支出列表
	 * @param type
	 * @return list
	 */
	public List<GuildIncExpHistory> getGuildIncExpHistoryListByType(Integer guildID, Integer type);
	
	/**
	 * 
	 * 取得军团成员个人捐献历史列表
	 * @param type
	 * @param guildPlayerID
	 * @return list
	 */
	public List<GuildIncExpHistory> getGuildIncExpHistoryListByGuildIDAndPlayerID(Integer guildID, Integer guildPlayerID, Integer type);

	/**
	 * 取得历史收入支出总数
	 * @param guildID
	 * @param guildPlayerID
	 * @param type
	 */
	public List<GuildIncExpHistory> getTotalIncExpOfGuildMemberInGuild(Integer guildID, Integer guildPlayerID, Integer type);
}