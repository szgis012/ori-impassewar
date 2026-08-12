package com.war.dao;

import java.util.List;

import com.war.domain.CityRank;
import com.war.domain.GuildRank;
import com.war.domain.PlayerRank;

public interface IRankDAO {

	/**
	 * 根据玩家编号获得玩家排名
	 * @param playerID
	 * @return
	 */
	public Integer getPlayerRankByPlayerID(Integer playerID);
	
	/**
	 * 根据玩家名称获得玩家排名
	 * @param playerName
	 * @return
	 */
	public Integer getPlayerRankByPlayerName(String playerName);
	
	/**
	 * 获得玩家排名列表(分页)
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<PlayerRank> getPlayerRankPagingList(Integer start,Integer offset);
	
	/**
	 * 获得玩家排名列表
	 * @return
	 */
	public List<PlayerRank> getPlayerRankList();
	
	/**
	 * 更新玩家排名
	 */
	public void refreshPlayerRank();
	

	
	/**
	 * 根据工会编号获得工会排名
	 * @param guildID
	 * @return
	 */
	public Integer getGuildRankByGuildID(Integer guildID);
	
	/**
	 * 根据工会名称获得工会排名
	 * @param guildName
	 * @return
	 */
	public Integer getGuildRankByGuildName(String guildName);
	
	/**
	 * 获得工会排名列表(分页)
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildRank> getGuildRankPagingList(Integer start,Integer offset);
	
	/**
	 * 获得工会排名列表
	 * @return
	 */
	public List<GuildRank> getGuildRankList();
	
	
	
	/**
	 * 获得城市人口排名列表
	 * @return
	 */
	public List<CityRank> getCityPopulationRankList();
	
	
	
	/**
	 * 获得玩家数量
	 * @return
	 */
	public Integer getPlayerNum();
	
	/**
	 * 获得工会数量
	 * @return
	 */
	public Integer getGuildNum();
	
	/**
	 * 获得城市数量
	 * @return
	 */
	public Integer getCityNum();
	
}
