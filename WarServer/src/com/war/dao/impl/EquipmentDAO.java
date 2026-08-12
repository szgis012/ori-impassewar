package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IEquipmentDAO;
import com.war.domain.Equipment;

public class EquipmentDAO extends SqlMapClientDaoSupport implements IEquipmentDAO{

	public Integer createEquipment(Equipment equipment) {
		return (Integer)this.getSqlMapClientTemplate().insert("Equipment.createEquipment", equipment);
	}
	
	public void updateEquipment(Equipment equipment) {
		this.getSqlMapClientTemplate().update("Equipment.updateEquipment", equipment);
	}
	
	public void deleteEquipmentByID(Integer equipmentID) {
		this.getSqlMapClientTemplate().delete("Equipment.deleteEquipmentByID", equipmentID);
	}
	
	public Equipment getEquipmentByID(Integer equipmentID) {
		return (Equipment)this.getSqlMapClientTemplate().queryForObject("Equipment.getEquipmentByID", equipmentID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipment> getEquipmentList() {
		return this.getSqlMapClientTemplate().queryForList("Equipment.getEquipmentList");
	}

}