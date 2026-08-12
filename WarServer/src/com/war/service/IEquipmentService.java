package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.Equipment;
import com.war.domain.PlayerEquipment;

public interface IEquipmentService {

	/**
	 * 初始化装备Map
	 * @return
	 */
	public Map<Integer,Equipment> initEquipmentsMap();
	
	/**
	 * 添加玩家装备
	 * @param playerEquipment
	 * @return
	 */
	public Integer addPlayerEquipment(PlayerEquipment playerEquipment);

	/**
	 * 更新玩家装备
	 * @param playerEquipment
	 */
	public void updatePlayerEquipment(PlayerEquipment playerEquipment);

	/**
	 * 根据编号删除玩家装备
	 * @param playerEquipmentID
	 */
	public void deletePlayerEquipment(Integer playerEquipmentID);

	/**
	 * 根据编号获得装备(缓存)
	 * @param equipmentID
	 * @return
	 */
	public Equipment getEquipmentByID(Integer equipmentID);
	
	/**
	 * 根据编号获得玩家装备
	 * @param playerEquipmentID
	 * @return
	 */
	public PlayerEquipment getPlayerEquipment(Integer playerEquipmentID);

	/**
	 * 根据种类获得玩家装备列表
	 * @param playerID
	 * @param category
	 * @return
	 */
	public List<PlayerEquipment> getPlayerEquipmentListByCategory(Integer playerID,Integer category);
	
	/**
	 * 获得玩家装备列表
	 * @return
	 */
	public List<PlayerEquipment> getPlayerEquipmentList();
	
}
