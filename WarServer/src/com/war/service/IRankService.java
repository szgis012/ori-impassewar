package com.war.service;

import java.util.List;

import com.war.domain.CityRank;
import com.war.domain.GuildRank;
import com.war.domain.PlayerRank;

public interface IRankService {

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
	 * 根据排名获得玩家排名列表(分页)
	 * @param rank
	 * @return
	 */
	public List<PlayerRank> getPlayerRankListByRank(Integer rank);
	
	/**
	 * 刷新玩家排名
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
	 * @param rank
	 * @return
	 */
	public List<GuildRank> getGuildRankListByRank(Integer rank);
	
	
	
	/**
	 * 根据城市编号获得城市建筑点数排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityConstructionPointRankByCityID(Integer cityID);
	
	/**
	 * 根据城市名称获得城市建筑点数排名
	 * @param cityName
	 * @return
	 */
	public Integer getCityConstructionPointRankByCityName(String cityName);
	
	/**
	 * 刷新城市建设点数排名
	 */
	public void refreshCityConstructionPointRank();
	
	/**
	 * 根据排名获得城市建筑点数排名列表(分页)
	 * @param rank
	 * @return
	 */
	public List<CityRank> getCityConstructionPointRankListByRank(Integer rank);
	
	
	
	/**
	 * 根据城市编号获得城市科技点数排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityTechnologyPointRankByCityID(Integer cityID);
	
	/**
	 * 根据城市名称获得城市科技点数排名
	 * @param cityName
	 * @return
	 */
	public Integer getCityTechnologyPointRankByCityName(String cityName);
	
	/**
	 * 刷新城市科技点数排名
	 */
	public void refreshCityTechnologyPointRank();
	
	/**
	 * 根据排名获得城市科技点数排名列表(分页)
	 * @param rank
	 * @return
	 */
	public List<CityRank> getCityTechnologyPointRankListByRank(Integer rank);
	
	
	
	/**
	 * 根据城市编号获得城市人口排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityPopulationRankByCityID(Integer cityID);
	
	/**
	 * 根据城市名称获得城市人口排名
	 * @param cityName
	 * @return
	 */
	public Integer getCityPopulationRankByCityName(String cityName);
	
	/**
	 * 刷新城市人口排名
	 */
	public void refreshCityPopulationRank();
	
	/**
	 * 根据排名获得城市人口排名列表(分页)
	 * @param rank
	 * @return
	 */
	public List<CityRank> getCityPopulationRankListByRank(Integer rank);
	
	/**
	 * 刷新工会声望及排名
	 */
	public void refreshGuildRenownAndRank();
	
	
	
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
