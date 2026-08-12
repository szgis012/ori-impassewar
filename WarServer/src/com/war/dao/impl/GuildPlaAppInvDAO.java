package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildPlaAppInvDAO;
import com.war.domain.GuildPlaAppInv;

public class GuildPlaAppInvDAO extends SqlMapClientDaoSupport implements IGuildPlaAppInvDAO{

	public void createGuildPlaAppInv(GuildPlaAppInv guildPlaAppInv) {
		this.getSqlMapClientTemplate().insert("GuildPlaAppInv.createGuildPlaAppInv", guildPlaAppInv);
	}
	
	public void deleteGuildPlaAppInv(GuildPlaAppInv guildPlaAppInv) {
		this.getSqlMapClientTemplate().delete("GuildPlaAppInv.deleteGuildPlaAppInv", guildPlaAppInv);
	}
	
	public void deleteGuildPlaAppInvsByGuildID(Integer guildID){
		this.getSqlMapClientTemplate().delete("GuildPlaAppInv.deleteGuildPlaAppInvsByGuildID",guildID);
	}
	
	public void deleteGuildPlaAppInvsByPlayerID(Integer playerID) {
		this.getSqlMapClientTemplate().delete("GuildPlaAppInv.deleteGuildPlaAppInvsByPlayerID",playerID);
	}
	
	public GuildPlaAppInv getGuildPlaAppInvByGuildIDAndPlayerID(Integer guildID,Integer playerID){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("playerID", playerID);
		
		return (GuildPlaAppInv)this.getSqlMapClientTemplate().queryForObject("GuildPlaAppInv.getGuildPlaAppInvByGuildIDAndPlayerID",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildPlaAppInv> getGuildPlaAppInvListByPlayerID(Integer playerID){
		return this.getSqlMapClientTemplate().queryForList("GuildPlaAppInv.getGuildPlaAppInvListByPlayerID",playerID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildPlaAppInv> getGuildPlaAppInvListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildPlaAppInv.getGuildPlaAppInvList",guildID);
	}

}