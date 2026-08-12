package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildtechnologyCostDAO;
import com.war.domain.GuildtechnologyCost;

public class GuildtechnologyCostDAO extends SqlMapClientDaoSupport implements IGuildtechnologyCostDAO{

	public Integer createGuildtechnologyCost(GuildtechnologyCost guildtechnologyCost) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildtechnologyCost.createGuildtechnologyCost", guildtechnologyCost);
	}

	public void updateGuildtechnologyCost(GuildtechnologyCost guildtechnologyCost) {
		this.getSqlMapClientTemplate().update("GuildtechnologyCost.updateGuildtechnologyCost", guildtechnologyCost);
	}

	public void deleteGuildtechnologyCostByID(Integer guildtechnologyCostID) {
		this.getSqlMapClientTemplate().delete("GuildtechnologyCost.deleteGuildtechnologyCostByID", guildtechnologyCostID);
	}

	public GuildtechnologyCost getGuildtechnologyCostByID(Integer guildtechnologyCostID) {
		return (GuildtechnologyCost)this.getSqlMapClientTemplate().queryForObject("GuildtechnologyCost.getGuildtechnologyCostByID", guildtechnologyCostID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildtechnologyCost> getGuildtechnologyCostList() {
		return this.getSqlMapClientTemplate().queryForList("GuildtechnologyCost.getGuildtechnologyCostList");
	}

	@SuppressWarnings("unchecked")
	public GuildtechnologyCost getGuildtechnologyCostListByTechnologyIDAndLevel(Integer guildTechnologyID, Integer level){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("guildtechnologyID", guildTechnologyID);
		map.put("level", level);
		return (GuildtechnologyCost) this.getSqlMapClientTemplate().queryForObject("GuildtechnologyCost.getGuildtechnologyCostListByTechnologyIDAndLevel", map);
	}
}