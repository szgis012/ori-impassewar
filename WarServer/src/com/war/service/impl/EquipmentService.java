package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.dao.IEquipmentDAO;
import com.war.dao.IPlayerEquipmentDAO;
import com.war.domain.Equipment;
import com.war.domain.PlayerEquipment;
import com.war.service.IEquipmentService;

public class EquipmentService implements IEquipmentService {

	private IEquipmentDAO equipmentDAO;
	
	private IPlayerEquipmentDAO playerEquipmentDAO; 
	
	public Map<Integer,Equipment> initEquipmentsMap(){
		
		Map<Integer,Equipment> equipmentMap = new HashMap<Integer,Equipment>();
		
		List<Equipment> equipmentList = equipmentDAO.getEquipmentList();
		for(int i=0;i<equipmentList.size();i++){
			equipmentMap.put(equipmentList.get(i).getEquipmentID(), equipmentList.get(i));
		}
		
		return equipmentMap;
	}
	
	public Integer addPlayerEquipment(PlayerEquipment playerEquipment){
		return playerEquipmentDAO.createPlayerEquipment(playerEquipment);
	}

	public void updatePlayerEquipment(PlayerEquipment playerEquipment){
		playerEquipmentDAO.updatePlayerEquipment(playerEquipment);
	}

	public void deletePlayerEquipment(Integer playerEquipmentID){
		playerEquipmentDAO.deletePlayerEquipmentByID(playerEquipmentID);
	}

	@SuppressWarnings("unchecked")
	public Equipment getEquipmentByID(Integer equipmentID){
		return ((Map<Integer,Equipment>)CacheService.getFromCache(CacheConstant.EQUIPMENTS_MAP)).get(equipmentID);
	}
	
	public PlayerEquipment getPlayerEquipment(Integer playerEquipmentID){
		PlayerEquipment playerEquipment = playerEquipmentDAO.getPlayerEquipmentByID(playerEquipmentID);
		playerEquipment.setEquipment(this.getEquipmentByID(playerEquipment.getEquipmentID()));
		return playerEquipment;
	}

	public List<PlayerEquipment> getPlayerEquipmentListByCategory(Integer playerID,Integer category){
		List<PlayerEquipment> playerEquipmentList = playerEquipmentDAO.getPlayerEquipmentListByPlayerIDAndCategory(playerID, category);
		
		//设置装备对象
		for(int i=0;i<playerEquipmentList.size();i++){
			
			playerEquipmentList.get(i).setEquipment(equipmentDAO.getEquipmentByID(playerEquipmentList.get(i).getEquipmentID()));
		}
		
		return playerEquipmentList;
	}
	
	public List<PlayerEquipment> getPlayerEquipmentList(){
		return playerEquipmentDAO.getPlayerEquipmentList();
	}
	

	public IEquipmentDAO getEquipmentDAO() {
		return equipmentDAO;
	}

	public void setEquipmentDAO(IEquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}
	
	public IPlayerEquipmentDAO getPlayerEquipmentDAO() {
		return playerEquipmentDAO;
	}

	public void setPlayerEquipmentDAO(IPlayerEquipmentDAO playerEquipmentDAO) {
		this.playerEquipmentDAO = playerEquipmentDAO;
	}

}
