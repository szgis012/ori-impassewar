package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildtechnologyDAO;
import com.war.domain.GuildTechnology;

public class GuildtechnologyDAO extends SqlMapClientDaoSupport implements IGuildtechnologyDAO{

	public Integer createGuildtechnology(GuildTechnology guildtechnology) {
		return (Integer)this.getSqlMapClientTemplate().insert("Guildtechnology.createGuildtechnology", guildtechnology);
	}

	public void updateGuildtechnology(GuildTechnology guildtechnology) {
		this.getSqlMapClientTemplate().update("Guildtechnology.updateGuildtechnology", guildtechnology);
	}

	public void deleteGuildtechnologyByID(Integer guildtechnologyID) {
		this.getSqlMapClientTemplate().delete("Guildtechnology.deleteGuildtechnologyByID", guildtechnologyID);
	}

	public GuildTechnology getGuildtechnologyByID(Integer guildtechnologyID) {
		return (GuildTechnology)this.getSqlMapClientTemplate().queryForObject("Guildtechnology.getGuildtechnologyByID", guildtechnologyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildTechnology> getGuildtechnologyList() {
		return this.getSqlMapClientTemplate().queryForList("Guildtechnology.getGuildtechnologyList");
	}

}