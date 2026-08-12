package com.war.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.constant.PagingConstant;
import com.war.dao.ICityDAO;
import com.war.dao.ICityRankDAO;
import com.war.dao.IRankDAO;
import com.war.domain.CityRank;
import com.war.domain.GuildRank;
import com.war.domain.PlayerRank;
import com.war.exception.GameException;
import com.war.service.IGuildService;
import com.war.service.IRankService;

/**
 * 排名Service实现
 * @author TopTong
 *
 */
public class RankService implements IRankService {
	
	private IRankDAO rankDAO;
	
	private ICityRankDAO cityRankDAO;
	
	private ICityDAO cityDAO;
	
	private IGuildService guildService;
	
	
	public Integer getPlayerRankByPlayerID(Integer playerID){
		return rankDAO.getPlayerRankByPlayerID(playerID);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getPlayerRankByPlayerName(String playerName){
		
		List<PlayerRank> playerRankList = (List<PlayerRank>)CacheService.getFromCache(CacheConstant.PLAYER_RANK_LIST);
		int playerRank = 0;
		for (int i=0;i<playerRankList.size();i++) {
			if (playerRankList.get(i).getName().equals(playerName)) {
				playerRank = i;
				break;
			}
		}
		if (playerRank==0) {
			throw new GameException("玩家 " + playerName + " 排名信息不存在。");
		}
		
		return playerRank;
	}

	@SuppressWarnings("unchecked")
	public List<PlayerRank> getPlayerRankListByRank(Integer rank){
		
		int start = rank/PagingConstant.RANK_PAGE_SIZE;
		
		if(rank!=0 && rank%PagingConstant.RANK_PAGE_SIZE==0){
			start--;
		}
		
		List<PlayerRank> playerRankList = new ArrayList<PlayerRank>(PagingConstant.RANK_PAGE_SIZE);
		List<PlayerRank> allPlayerRankList = (List<PlayerRank>)CacheService.getFromCache(CacheConstant.PLAYER_RANK_LIST);
		for (int i=start*PagingConstant.RANK_PAGE_SIZE;i<start*PagingConstant.RANK_PAGE_SIZE+PagingConstant.RANK_PAGE_SIZE;i++) {
			if (i>=allPlayerRankList.size()) {
				break;
			}
			playerRankList.add(allPlayerRankList.get(i));
		}
		
		return playerRankList;
	}
	
	@SuppressWarnings("unchecked")
	public void refreshPlayerRank() {
		// 刷新玩家排名
		rankDAO.refreshPlayerRank();
		// 缓存玩家排名信息
		List<PlayerRank> playerRankList = rankDAO.getPlayerRankList();
		
		// 添加/覆盖缓存数据
		CacheService.putToCache(CacheConstant.PLAYER_RANK_NUM, playerRankList.size());
		CacheService.putToCache(CacheConstant.PLAYER_RANK_LIST, playerRankList);
	}
	

	
	public Integer getGuildRankByGuildID(Integer guildID){
		return rankDAO.getGuildRankByGuildID(guildID);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getGuildRankByGuildName(String guildName){
		
		List<GuildRank> guildRankList = (List<GuildRank>)CacheService.getFromCache(CacheConstant.GUILD_RANK_LIST);
		int guildRank = 0;
		
		for (int i=0;i<guildRankList.size();i++) {
			if (guildRankList.get(i).getName().equals(guildName)) {
				guildRank = i;
				break;
			}
		}
		if(guildRank==0){
			throw new GameException("军团 " + guildName + " 排名信息不存在。");
		}
		
		return guildRank;
	}

	@SuppressWarnings("unchecked")
	public List<GuildRank> getGuildRankListByRank(Integer rank){
		
		int start = rank/PagingConstant.RANK_PAGE_SIZE;
		
		if(rank!=0 && rank%PagingConstant.RANK_PAGE_SIZE==0){
			start--;
		}
		
		List<GuildRank> guildRankList = new ArrayList<GuildRank>(PagingConstant.RANK_PAGE_SIZE);
		List<GuildRank> allGuildRankList = (List<GuildRank>)CacheService.getFromCache(CacheConstant.GUILD_RANK_LIST);
		for (int i=start*PagingConstant.RANK_PAGE_SIZE;i<start*PagingConstant.RANK_PAGE_SIZE+PagingConstant.RANK_PAGE_SIZE;i++) {
			if (i>=allGuildRankList.size()) {
				break;
			}
			guildRankList.add(allGuildRankList.get(i));
		}
		
		return guildRankList;
	}
	
	public void refreshGuildRenownAndRank(){
		// 刷新军团排名
		guildService.refreshGuildRenownAndRank();
		// 缓存军团排名信息
		List<GuildRank> guildRankList = rankDAO.getGuildRankList();
		
		// 添加/覆盖缓存数据
		CacheService.putToCache(CacheConstant.GUILD_RANK_NUM, guildRankList.size());
		CacheService.putToCache(CacheConstant.GUILD_RANK_LIST, guildRankList);
	}
	
	
	
	public Integer getCityConstructionPointRankByCityID(Integer cityID) {
		return cityRankDAO.getCityConstructionPointRankByCityID(cityID);
	}
	
	public Integer getCityConstructionPointRankByCityName(String cityName){
		
		Integer cityID = cityDAO.getCityIDByCityName(cityName);
		
		if(cityID==null){
			throw new GameException("城市 " + cityName + " 不存在。");
		}
		
		return cityRankDAO.getCityConstructionPointRankByCityID(cityID);
	}

	public void refreshCityConstructionPointRank() {
		cityRankDAO.refreshCityConstructionPointRank();
	}
	
	public List<CityRank> getCityConstructionPointRankListByRank(Integer rank) {
		
		int start = rank/PagingConstant.RANK_PAGE_SIZE;
		
		if(rank!=0 && rank%PagingConstant.RANK_PAGE_SIZE==0){
			start--;
		}
		
		List<CityRank> cityRankList = cityRankDAO.getCityConstructionPointRankPagingList(start*PagingConstant.RANK_PAGE_SIZE, PagingConstant.RANK_PAGE_SIZE);
		
		List<CityRank> initiailzedCityRankList = new ArrayList<CityRank>(PagingConstant.RANK_PAGE_SIZE);
		
		for(int i=0;i<cityRankList.size();i++){
			CityRank cityRank = cityRankDAO.getCityRankByCityID(cityRankList.get(i).getCityID());
			cityRank.setRank(cityRankList.get(i).getRank());
			
			initiailzedCityRankList.add(cityRank);
		}
		
		return initiailzedCityRankList;
	}
	
	
	
	public Integer getCityTechnologyPointRankByCityID(Integer cityID) {
		return cityRankDAO.getCityTechnologyPointRankByCityID(cityID);
	}
	
	public Integer getCityTechnologyPointRankByCityName(String cityName){
		
		Integer cityID = cityDAO.getCityIDByCityName(cityName);
		
		if(cityID==null){
			throw new GameException("城市 " + cityName + " 不存在。");
		}
		
		return cityRankDAO.getCityTechnologyPointRankByCityID(cityID);
	}

	public void refreshCityTechnologyPointRank() {
		cityRankDAO.refreshCityTechnologyPointRank();
	}
	
	public List<CityRank> getCityTechnologyPointRankListByRank(Integer rank) {
		
		int start = rank/PagingConstant.RANK_PAGE_SIZE;
		
		if(rank!=0 && rank%PagingConstant.RANK_PAGE_SIZE==0){
			start--;
		}
		
		List<CityRank> cityRankList = cityRankDAO.getCityTechnologyPointRankPagingList(start*PagingConstant.RANK_PAGE_SIZE, PagingConstant.RANK_PAGE_SIZE);
		
		List<CityRank> initiailzedCityRankList = new ArrayList<CityRank>(PagingConstant.RANK_PAGE_SIZE);
		
		for(int i=0;i<cityRankList.size();i++){
			CityRank cityRank = cityRankDAO.getCityRankByCityID(cityRankList.get(i).getCityID());
			cityRank.setRank(cityRankList.get(i).getRank());
			
			initiailzedCityRankList.add(cityRank);
		}
		
		return initiailzedCityRankList;
	}
	
	
	
	public Integer getCityPopulationRankByCityID(Integer cityID) {
		return cityRankDAO.getCityPopulationRankByCityID(cityID);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getCityPopulationRankByCityName(String cityName){
		
		List<CityRank> cityRankList = (List<CityRank>)CacheService.getFromCache(CacheConstant.CITY_POPULATION_RANK_LIST);
		int cityRank = 0;
		
		for (int i=0;i<cityRankList.size();i++) {
			if (cityRankList.get(i).getName().equals(cityName)) {
				cityRank = i;
				break;
			}
		}
		if(cityRank==0){
			throw new GameException("城市 " + cityName + " 人口排名信息不存在。");
		}
		
		return cityRank;
	}

	@SuppressWarnings("unchecked")
	public List<CityRank> getCityPopulationRankListByRank(Integer rank) {
		
		int start = rank/PagingConstant.RANK_PAGE_SIZE;
		
		if(rank!=0 && rank%PagingConstant.RANK_PAGE_SIZE==0){
			start--;
		}
		
		List<CityRank> cityRankList = new ArrayList<CityRank>(PagingConstant.RANK_PAGE_SIZE);
		List<CityRank> allCityRankList = (List<CityRank>)CacheService.getFromCache(CacheConstant.CITY_POPULATION_RANK_LIST);
		for (int i=start*PagingConstant.RANK_PAGE_SIZE;i<start*PagingConstant.RANK_PAGE_SIZE+PagingConstant.RANK_PAGE_SIZE;i++) {
			if (i>=allCityRankList.size()) {
				break;
			}
			cityRankList.add(allCityRankList.get(i));
		}
		
		return cityRankList;
	}

	public void refreshCityPopulationRank() {
		// 刷新城市人口排名
		cityRankDAO.refreshCityPopulationRank();
		// 缓存城市人口信息
		List<CityRank> cityRankList = rankDAO.getCityPopulationRankList();
		
		// 设置排名信息
		int rank = 1;
		for (CityRank cityRank:cityRankList) {
			cityRank.setRank(rank++);
		}
		
		// 添加/覆盖缓存数据
		CacheService.putToCache(CacheConstant.CITY_RANK_NUM, cityRankList.size());
		CacheService.putToCache(CacheConstant.CITY_POPULATION_RANK_LIST, cityRankList);
	}
	
	
	
	public Integer getPlayerNum(){
		return (Integer)CacheService.getFromCache(CacheConstant.PLAYER_RANK_NUM);
	}
	
	public Integer getGuildNum(){
		return (Integer)CacheService.getFromCache(CacheConstant.GUILD_RANK_NUM);
	}
	
	public Integer getCityNum(){
		return (Integer)CacheService.getFromCache(CacheConstant.CITY_RANK_NUM);
	}
	
	
	public IRankDAO getRankDAO() {
		return rankDAO;
	}

	public void setRankDAO(IRankDAO rankDAO) {
		this.rankDAO = rankDAO;
	}

	public ICityRankDAO getCityRankDAO() {
		return cityRankDAO;
	}

	public void setCityRankDAO(ICityRankDAO cityRankDAO) {
		this.cityRankDAO = cityRankDAO;
	}
	
	public ICityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(ICityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}

}
