package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildIncExpHistoryDAO;
import com.war.domain.GuildIncExpHistory;

public class GuildIncExpHistoryDAO extends SqlMapClientDaoSupport implements IGuildIncExpHistoryDAO{

	public Integer createGuildIncExpHistory(GuildIncExpHistory guildIncExpHistory) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildIncExpHistory.createGuildIncExpHistory", guildIncExpHistory);
	}

	public void updateGuildIncExpHistory(GuildIncExpHistory guildIncExpHistory) {
		this.getSqlMapClientTemplate().update("GuildIncExpHistory.updateGuildIncExpHistory", guildIncExpHistory);
	}

	public void deleteGuildIncExpHistoryByID(Integer guildIncExpHistoryID) {
		this.getSqlMapClientTemplate().delete("GuildIncExpHistory.deleteGuildIncExpHistoryByID", guildIncExpHistoryID);
	}

	public GuildIncExpHistory getGuildIncExpHistoryByID(Integer guildIncExpHistoryID) {
		return (GuildIncExpHistory)this.getSqlMapClientTemplate().queryForObject("GuildIncExpHistory.getGuildIncExpHistoryByID", guildIncExpHistoryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildIncExpHistory> getGuildIncExpHistoryList() {
		return this.getSqlMapClientTemplate().queryForList("GuildIncExpHistory.getGuildIncExpHistoryList");
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildIncExpHistory> getGuildIncExpHistoryListByType(Integer guildID, Integer type) {

		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("guildID", guildID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("GuildIncExpHistory.getGuildIncExpHistoryListByType", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildIncExpHistory> getGuildIncExpHistoryListByGuildIDAndPlayerID(Integer guildID, Integer guildPlayerID, Integer type) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("guildID", guildID);
		params.put("guildPlayerID", guildPlayerID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("GuildIncExpHistory.getGuildIncExpHistoryListByGuildIDAndPlayerID", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildIncExpHistory> getTotalIncExpOfGuildMemberInGuild(Integer guildID, Integer guildPlayerID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("guildID", guildID);
		params.put("guildPlayerID", guildPlayerID);
		params.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("GuildIncExpHistory.getTotalIncExpOfGuildMemberInGuild", params);
	}
}