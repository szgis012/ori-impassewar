package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildPlayerDAO;
import com.war.domain.GuildPlayer;

public class GuildPlayerDAO extends SqlMapClientDaoSupport implements IGuildPlayerDAO{

	public void createGuildPlayer(GuildPlayer guildPlayer) {
		this.getSqlMapClientTemplate().insert("GuildPlayer.createGuildPlayer", guildPlayer);
	}
	
	public void updateGuildPlayer(GuildPlayer guildPlayer) {
		this.getSqlMapClientTemplate().update("GuildPlayer.updateGuildPlayer", guildPlayer);
	}
	
	public void deleteGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("playerID", playerID);
		
		this.getSqlMapClientTemplate().delete("GuildPlayer.deleteGuildPlayerByGuildIDAndPlayerID", map);
	}

	public void deleteGuildPlayersByGuildID(Integer guildID){
		this.getSqlMapClientTemplate().delete("GuildPlayer.deleteGuildPlayersByGuildID",guildID);
	}
	
	public GuildPlayer getGuildPlayerByID(Integer guildPlayerID){
		return (GuildPlayer)this.getSqlMapClientTemplate().queryForObject("GuildPlayer.getGuildPlayerByID",guildPlayerID);
	}
	
	public GuildPlayer getGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("playerID", playerID);
		
		return (GuildPlayer)this.getSqlMapClientTemplate().queryForObject("GuildPlayer.getGuildPlayerByGuildIDAndPlayerID", map);
	}

	public Integer getGuildPlayerAmountByGuildID(Integer guildID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("GuildPlayer.getGuildPlayerAmountByGuildID",guildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildPlayer> getGuildPlayerPagingListByGuildID(Integer guildID, Integer start, Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("GuildPlayer.getGuildPlayerPagingListByGuildID", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildPlayer> getGuildPlayerListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildPlayer.getGuildPlayerListByGuildID", guildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildPlayer> getGuildPlayerList() {
		return this.getSqlMapClientTemplate().queryForList("GuildPlayer.getGuildPlayerList");
	}

	public void updateAllowGarrisonByGuildPlayerID(Integer guildPlayerID, Integer allowGarrison) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildPlayerID", guildPlayerID);
		map.put("allowGarrison", allowGarrison);
		
		this.getSqlMapClientTemplate().update(("GuildPlayer.updateAllowGarrisonByGuildPlayerID"), map);
	}
	
	public void updateAllowGarrisonByPlayerIDAndGuildID(Integer playerID, Integer guildID, Integer allowGarrison) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("playerID", playerID);
		map.put("guildID", guildID);
		map.put("allowGarrison", allowGarrison);
		
		this.getSqlMapClientTemplate().update(("GuildPlayer.updateAllowGarrisonByPlayerIDAndGuildID"), map);
	}
}