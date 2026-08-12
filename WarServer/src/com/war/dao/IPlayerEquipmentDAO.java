package com.war.dao;

import java.util.List;

import com.war.domain.PlayerEquipment;

public interface IPlayerEquipmentDAO {

	/**
	 * 创建玩家装备
	 * @param playerEquipment
	 * @return
	 */
	public Integer createPlayerEquipment(PlayerEquipment playerEquipment);

	/**
	 * 更新玩家装备
	 * @param playerEquipment
	 */
	public void updatePlayerEquipment(PlayerEquipment playerEquipment);

	/**
	 * 根据编号删除玩家装备
	 * @param playerEquipmentID
	 */
	public void deletePlayerEquipmentByID(Integer playerEquipmentID);

	/**
	 * 根据编号获得玩家装备
	 * @param playerEquipmentID
	 * @return
	 */
	public PlayerEquipment getPlayerEquipmentByID(Integer playerEquipmentID);

	/**
	 * 根据玩家编号及种类获得玩家装备列表
	 * @param playerID
	 * @param category
	 * @return
	 */
	public List<PlayerEquipment> getPlayerEquipmentListByPlayerIDAndCategory(Integer playerID,Integer category);
	
	/**
	 * 获得玩家装备列表
	 * @return
	 */
	public List<PlayerEquipment> getPlayerEquipmentList();

}