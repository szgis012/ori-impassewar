package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildEventDAO;
import com.war.domain.GuildEvent;

public class GuildEventDAO extends SqlMapClientDaoSupport implements IGuildEventDAO{

	public Integer createGuildEvent(GuildEvent guildEvent) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildEvent.createGuildEvent", guildEvent);
	}
	
	public void deleteGuildEventByID(Integer guildEventID) {
		this.getSqlMapClientTemplate().delete("GuildEvent.deleteGuildEventByID", guildEventID);
	}
	
	public void deleteGuildEventsByGuildID(Integer guildID){
		this.getSqlMapClientTemplate().delete("GuildEvent.deleteGuildEventsByGuildID",guildID);
	}
	
	public GuildEvent getGuildEventByID(Integer guildEventID) {
		return (GuildEvent)this.getSqlMapClientTemplate().queryForObject("GuildEvent.getGuildEventByID", guildEventID);
	}
	
	public Integer getGuildEventAmountByGuildID(Integer guildID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("GuildEvent.getGuildEventAmountByGuildID",guildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildEvent> getGuildEventPagingListByGuildID(Integer guildID,Integer start,Integer offset) {
	   
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("guildID", guildID);
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("GuildEvent.getGuildEventPagingListByGuildID",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildEvent> getGuildEventListByGuildID(Integer guildID) {
		return this.getSqlMapClientTemplate().queryForList("GuildEvent.getGuildEventListByGuildID",guildID);
	}

}