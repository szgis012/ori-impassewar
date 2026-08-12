package com.war.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildExtDAO;
import com.war.domain.GuildExt;
import com.war.exception.GameException;

public class GuildExtDAO extends SqlMapClientDaoSupport implements IGuildExtDAO {

	public Integer createGuildExt(GuildExt guildExt) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildExt.createGuildExt", guildExt);
	}

	public void updateGuildExt(GuildExt guildExt) {
		this.getSqlMapClientTemplate().update("GuildExt.updateGuildExt", guildExt);
	}

	public void updateGuildExtParams(Map<String, Object> params) {
		if(params == null || !params.containsKey("guildID") || params.size()<2)
			throw new GameException("参数有误");
		
		this.getSqlMapClientTemplate().update("GuildExt.updateGuildExtParams", params);
	}
	
	public void deleteGuildExtByID(Integer guildExtID) {
		this.getSqlMapClientTemplate().delete("GuildExt.deleteGuildExtByID", guildExtID);
	}

	public GuildExt getGuildExtByID(Integer guildExtID) {
		return (GuildExt)this.getSqlMapClientTemplate().queryForObject("GuildExt.getGuildExtByID", guildExtID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildExt> getGuildExtList() {
		return this.getSqlMapClientTemplate().queryForList("GuildExt.getGuildExtList");
	}

}
