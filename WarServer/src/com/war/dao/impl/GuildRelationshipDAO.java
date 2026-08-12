package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildRelationshipDAO;
import com.war.domain.GuildRelationship;

public class GuildRelationshipDAO extends SqlMapClientDaoSupport implements IGuildRelationshipDAO{

	public Integer createGuildRelationship(GuildRelationship guildRelationship) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildRelationship.createGuildRelationship", guildRelationship);
	}
	
	public void deleteGuildRelationshipByID(Integer guildRelationshipID) {
		this.getSqlMapClientTemplate().delete("GuildRelationship.deleteGuildRelationshipByID", guildRelationshipID);
	}
	
	public void deleteGuildRelationshipsByGuildID(Integer guildID){
		this.getSqlMapClientTemplate().delete("GuildRelationship.deleteGuildRelationshipsByGuildID",guildID);
	}
	
	public void deleteGuildRelationshipsByTargetGuildID(Integer targetGuildID){
		this.getSqlMapClientTemplate().delete("GuildRelationship.deleteGuildRelationshipsByTargetGuildID",targetGuildID);
	}
	
	public void deleteGuildRelationshipByGuildIDAndTargetGuildID(Integer guildID, Integer targetGuildID){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("targetGuildID", targetGuildID);
		
		this.getSqlMapClientTemplate().delete("GuildRelationship.deleteGuildRelationshipsByGuildIDAndTargetGuildID",map);
	}
	
	public GuildRelationship getGuildRelationshipByGuildIDAndTargetGuildID(
			Integer guildID, Integer targetGuildID) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("targetGuildID", targetGuildID);
		
		return (GuildRelationship)this.getSqlMapClientTemplate().queryForObject("GuildRelationship.getGuildRelationshipByGuildIDAndTargetGuildID",map);
	}
	
	public GuildRelationship getGuildRelationshipByID(Integer guildRelationshipID) {
		return (GuildRelationship)this.getSqlMapClientTemplate().queryForObject("GuildRelationship.getGuildRelationshipByID", guildRelationshipID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildRelationship> getGuildRelationshipListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildRelationship.getGuildRelationshipListByGuildID",guildID);
	}

	public Integer getGuildRelationshipCountByGuildID(Integer guildID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("GuildRelationship.getGuildRelationshipCountByGuildID",guildID);
	}
	
}