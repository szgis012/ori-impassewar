package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildTechnologyguildDAO;
import com.war.domain.GuildTechnologyGuild;

public class GuildTechnologyguildDAO extends SqlMapClientDaoSupport implements IGuildTechnologyguildDAO{

	public Integer createGuildTechnologyguild(GuildTechnologyGuild guildTechnologyguild) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildTechnologyGuild.createGuildTechnologyguild", guildTechnologyguild);
	}

	public void updateGuildTechnologyguild(GuildTechnologyGuild guildTechnologyguild) {
		this.getSqlMapClientTemplate().update("GuildTechnologyGuild.updateGuildTechnologyguild", guildTechnologyguild);
	}
	
	public void updateGuildTechnologyguildWithState(Integer guildTechnologyguildID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("state", 1);
		map.put("guildTechnologyguildID", guildTechnologyguildID);
		this.getSqlMapClientTemplate().update("GuildTechnologyGuild.updateGuildTechnologyguildWithState", map);
	}

	public void deleteGuildTechnologyguildByID(Integer guildTechnologyguildID) {
		this.getSqlMapClientTemplate().delete("GuildTechnologyGuild.deleteGuildTechnologyguildByID", guildTechnologyguildID);
	}

	public GuildTechnologyGuild getGuildTechnologyguildByID(Integer guildTechnologyguildID) {
		return (GuildTechnologyGuild)this.getSqlMapClientTemplate().queryForObject("GuildTechnologyGuild.getGuildTechnologyguildByID", guildTechnologyguildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildTechnologyGuild> getGuildTechnologyguildList() {
		return this.getSqlMapClientTemplate().queryForList("GuildTechnologyGuild.getGuildTechnologyguildList");
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildTechnologyGuild> getGuildTechnologyguildListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildTechnologyGuild.getGuildTechnologyguildListByGuildID", guildID);
	}

	public GuildTechnologyGuild getGuildTechnologyguildByGuildID(Integer ID, Integer guildID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("guildTechnologyID", ID);
		map.put("guildID", guildID);
		return (GuildTechnologyGuild) this.getSqlMapClientTemplate().queryForObject("GuildTechnologyGuild.getGuildTechnologyByIDAndGuildID", map);
	}
	
	public GuildTechnologyGuild getGuildTechnologyguildWithResearching(Integer ID, Integer guildID, Integer state) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("guildTechnologyID", ID);
		map.put("guildID", guildID);
		map.put("state", state);
		return (GuildTechnologyGuild) this.getSqlMapClientTemplate().queryForObject("GuildTechnologyGuild.getGuildTechnologyWithResearching", map);
	}
}