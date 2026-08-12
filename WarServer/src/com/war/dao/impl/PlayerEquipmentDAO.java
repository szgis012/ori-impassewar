package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPlayerEquipmentDAO;
import com.war.domain.PlayerEquipment;

public class PlayerEquipmentDAO extends SqlMapClientDaoSupport implements IPlayerEquipmentDAO{

	public Integer createPlayerEquipment(PlayerEquipment playerEquipment) {
		return (Integer)this.getSqlMapClientTemplate().insert("PlayerEquipment.createPlayerEquipment", playerEquipment);
	}
	
	public void updatePlayerEquipment(PlayerEquipment playerEquipment) {
		this.getSqlMapClientTemplate().update("PlayerEquipment.updatePlayerEquipment", playerEquipment);
	}
	
	public void deletePlayerEquipmentByID(Integer playerEquipmentID) {
		this.getSqlMapClientTemplate().delete("PlayerEquipment.deletePlayerEquipmentByID", playerEquipmentID);
	}
	
	public PlayerEquipment getPlayerEquipmentByID(Integer playerEquipmentID) {
		return (PlayerEquipment)this.getSqlMapClientTemplate().queryForObject("PlayerEquipment.getPlayerEquipmentByID", playerEquipmentID);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerEquipment> getPlayerEquipmentListByPlayerIDAndCategory(Integer playerID,Integer category){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("playerID", playerID);
		params.put("category", category);
		
		return this.getSqlMapClientTemplate().queryForList("PlayerEquipment.getPlayerEquipmentListByPlayerIDAndCategory",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerEquipment> getPlayerEquipmentList() {
		return this.getSqlMapClientTemplate().queryForList("PlayerEquipment.getPlayerEquipmentList");
	}

}