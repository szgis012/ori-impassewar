package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildAttackDAO;
import com.war.domain.GuildAttack;

public class GuildAttackDAO extends SqlMapClientDaoSupport implements IGuildAttackDAO{

	public Integer createGuildAttack(GuildAttack guildAttack) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildAttack.createGuildAttack", guildAttack);
	}
	
	public void deleteGuildAttackByID(Integer guildAttackID) {
		this.getSqlMapClientTemplate().delete("GuildAttack.deleteGuildAttackByID", guildAttackID);
	}
	
	public void deleteGuildAttacksByGuildID(Integer guildID){
		this.getSqlMapClientTemplate().delete("GuildAttack.deleteGuildAttacksByGuildID",guildID);
	}
	
	public GuildAttack getGuildAttackByID(Integer guildAttackID) {
		return (GuildAttack)this.getSqlMapClientTemplate().queryForObject("GuildAttack.getGuildAttackByID", guildAttackID);
	}
	
	public Integer getGuildAttackAmountByGuildID(Integer guildID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("GuildAttack.getGuildAttackAmountByGuildID",guildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildAttack> getGuildAttackPagingListByGuildID(Integer guildID,Integer start,Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("GuildAttack.getGuildAttackPagingListByGuildID",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildAttack> getGuildAttackListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildAttack.getGuildAttackListByGuildID",guildID);
	}

}