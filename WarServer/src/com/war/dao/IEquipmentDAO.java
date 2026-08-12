package com.war.dao;

import java.util.List;

import com.war.domain.Equipment;

public interface IEquipmentDAO {

	/**
	 * 创建装备
	 * @param equipment
	 * @return
	 */
	public Integer createEquipment(Equipment equipment);

	/**
	 * 更新装备
	 * @param equipment
	 */
	public void updateEquipment(Equipment equipment);

	/**
	 * 根据编号删除装备
	 * @param equipmentID
	 */
	public void deleteEquipmentByID(Integer equipmentID);

	/**
	 * 根据编号获得装备
	 * @param equipmentID
	 * @return
	 */
	public Equipment getEquipmentByID(Integer equipmentID);

	/**
	 * 获得装备列表
	 * @return
	 */
	public List<Equipment> getEquipmentList();

}