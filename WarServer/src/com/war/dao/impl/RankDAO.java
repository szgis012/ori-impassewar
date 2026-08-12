package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IRankDAO;
import com.war.domain.CityRank;
import com.war.domain.GuildRank;
import com.war.domain.PlayerRank;

public class RankDAO extends SqlMapClientDaoSupport implements IRankDAO {

	public Integer getPlayerRankByPlayerID(Integer playerID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getPlayerRankByPlayerID",playerID);
	}

	public Integer getPlayerRankByPlayerName(String playerName) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getPlayerRankByPlayerName",playerName);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerRank> getPlayerRankPagingList(Integer start, Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Rank.getPlayerRankPagingList",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerRank> getPlayerRankList(){
		return this.getSqlMapClientTemplate().queryForList("Rank.getPlayerRankList");
	}
	
	public void refreshPlayerRank() {
		this.getSqlMapClientTemplate().update("Rank.refreshPlayerRank");
	}
	
	
	
	public Integer getGuildRankByGuildID(Integer guildID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getGuildRankByGuildID",guildID);
	}

	public Integer getGuildRankByGuildName(String guildName) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getGuildRankByGuildName",guildName);
	}

	@SuppressWarnings("unchecked")
	public List<GuildRank> getGuildRankPagingList(Integer start, Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Rank.getGuildRankPagingList",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildRank> getGuildRankList() {
		return this.getSqlMapClientTemplate().queryForList("Rank.getGuildRankList");
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CityRank> getCityPopulationRankList() {
		return this.getSqlMapClientTemplate().queryForList("Rank.getCityPopulationRankList");
	}
	
	
	
	public Integer getPlayerNum(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getPlayerNum");
	}
	
	public Integer getGuildNum(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getGuildNum");
	}
	
	public Integer getCityNum(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Rank.getCityNum");
	}

}
