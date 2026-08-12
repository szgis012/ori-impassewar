package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildDAO;
import com.war.domain.Guild;

public class GuildDAO extends SqlMapClientDaoSupport implements IGuildDAO{

	public Integer createGuild(Guild guild) {
		return (Integer)this.getSqlMapClientTemplate().insert("Guild.createGuild", guild);
	}
	
	public void updateGuildInfo(Guild guild){
		this.getSqlMapClientTemplate().update("Guild.updateGuildInfo",guild);
	}
	
	public void updateGuild(Guild guild) {
		this.getSqlMapClientTemplate().update("Guild.updateGuild", guild);
	}
	
	public void updateGuildRenownAndRankBatch(List<Guild> guildList) throws SQLException{
		for(int i=0;i<guildList.size();i++){
			this.getSqlMapClient().update("Guild.updateGuildRenownAndRankBatch",guildList.get(i));
		}
	}
	
	public void deleteGuildByID(Integer guildID) {
		this.getSqlMapClientTemplate().delete("Guild.deleteGuildByID", guildID);
	}
	
	public Integer getGuildNum(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Guild.getGuildNum");
	}
	
	public Integer getGuildIDByGuildName(String guildName){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Guild.getGuildIDByGuildName",guildName);
	}
	
	public String getGuildNameByID(Integer guildID){
		return (String)this.getSqlMapClientTemplate().queryForObject("Guild.getGuildNameByID",guildID);
	}
	
	public Guild getGuildByGuildName(String guildName){
		return (Guild)this.getSqlMapClientTemplate().queryForObject("Guild.getGuildByGuildName",guildName);
	}
	
	public Guild getGuildByID(Integer guildID) {
		return (Guild)this.getSqlMapClientTemplate().queryForObject("Guild.getGuildByID", guildID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Guild> getGuildPagingList(Integer start,Integer offset){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Guild.getGuildPagingList", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Guild> getGuildList() {
		return this.getSqlMapClientTemplate().queryForList("Guild.getGuildList");
	}

}