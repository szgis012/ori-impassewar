package com.war.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPlayerDAO;
import com.war.domain.Player;

public class PlayerDAO extends SqlMapClientDaoSupport implements IPlayerDAO {

	public Integer createPlayer(Player player) {
		return (Integer) this.getSqlMapClientTemplate().insert(
				"Player.createPlayer", player);
	}

	public void addPlayerRenown(Integer playerID, Long renown) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("renown", renown);

		this.getSqlMapClientTemplate().update("Player.addPlayerRenown", params);
	}

	public void addPlayerOnlineTime(Integer playerID, Integer onlineTime) {

		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("onlineTime", onlineTime);

		this.getSqlMapClientTemplate().update("Player.addPlayerOnlineTime",
				params);
	}

	public void updatePlayer(Player player) {
		this.getSqlMapClientTemplate().update("Player.updatePlayer", player);
	}

	public void updateMoney(Integer playerID, Integer money) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("money", money);

		this.getSqlMapClientTemplate().update("Player.updateMoney", params);
	}
	
	public void updateHonorIDByID(Integer playerID, Integer honorID){
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("honorID", honorID);

		this.getSqlMapClientTemplate().update("Player.updateHonorIDByID", params);
	}

	public void updateRenown(Integer playerID,Long renown){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("renown", renown);

		this.getSqlMapClientTemplate().update("Player.updateRenown", params);
	}
	
	public void deletePlayerByID(Integer playerID) {
		this.getSqlMapClientTemplate().delete("Player.deletePlayerByID", playerID);
	}

	public Integer getHonorIDByPlayerID(Integer playerID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.getHonorIDByPlayerID", playerID);
	}
	
	public Integer getPlayerIDByPlayerName(String playerName) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Player.getPlayerIDByPlayerName", playerName);
	}

	public String getPlayerNameByPlayerID(Integer playerID) {
		return (String) this.getSqlMapClientTemplate().queryForObject("Player.getPlayerNameByPlayerID", playerID);
	}

	public Date getLastLoginTimeByPlayerID(Integer playerID){
		return (Date)this.getSqlMapClientTemplate().queryForObject("Player.getLastLoginTimeByPlayerID",playerID);
	}
	
	public Player getPlayerByID(Integer playerID) {
		return (Player) this.getSqlMapClientTemplate().queryForObject("Player.getPlayerByID", playerID);
	}

	@SuppressWarnings("unchecked")
	public List<Player> getPlayerList() {
		return this.getSqlMapClientTemplate().queryForList("Player.getPlayerList");
	}

	public Player getPlayerByUserName(String userName) {
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.getPlayerByUserName", userName);
	}

	public Integer getPlayerCount() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Player.getPlayerCount");
	}

	public void updateLastLoginInfo(Integer playerID) {
		this.getSqlMapClientTemplate().update("Player.updateLastLoginInfo", playerID);
	}
	
	public void updateHaveReceiveDailyRewardToNotReceive() {
		this.getSqlMapClientTemplate().update("Player.updateHaveReceiveDailyRewardToNotReceive");
		
	}
	
	public void updateHaveReceiveDailyReward(Integer playerID, Integer haveReceiveDailyreward) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("haveReceiveDailyreward", haveReceiveDailyreward);
		
		this.getSqlMapClientTemplate().update("Player.updateHaveReceiveDailyReward", params);
	}
	
	public void updateGiftCertificate(Integer playerID, Integer giftCertificate) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("giftCertificate", giftCertificate);
		
		this.getSqlMapClientTemplate().update("Player.updateGiftCertificate", params);
	}
	
	public void updatePlayerState(Integer playerID, Integer state) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("state", state);

		this.getSqlMapClientTemplate().update("Player.updatePlayerState", params);
	}

	public Integer getPlayerNumByCountry(Integer country) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.getPlayerNumByCountry", country);
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getFinshedFreshmanProtectList(Integer day) {
		return this.getSqlMapClientTemplate().queryForList(
				"Player.getFinshedFreshmanProtectList", day);
	}
	
	public Integer getRenownByPlayerID(Integer playerID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.getRenownByPlayerID",playerID);
	}
	
	public Date getPlayerCreateTime(Integer playerID){
		return (Date)this.getSqlMapClientTemplate().queryForObject("Player.getPlayerCreateTime",playerID);
	}
	
}