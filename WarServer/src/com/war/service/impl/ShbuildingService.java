package com.war.service.impl;

import java.util.List;

import com.war.dao.IShbuildingDAO;
import com.war.domain.Shbuilding;
import com.war.service.IShbuildingService;

/**
 * 要塞建筑service接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class ShbuildingService implements IShbuildingService {
	private IShbuildingDAO shbuildingDAO;
	
	public Integer createShbuilding(Shbuilding shbuilding) {
		return shbuildingDAO.createShbuilding(shbuilding);
	}

	public void deleteShbuildingByID(Integer shbuildingID) {
		shbuildingDAO.deleteShbuildingByID(shbuildingID);
	}

	public Shbuilding getShbuildingByID(Integer shbuildingID) {
		return shbuildingDAO.getShbuildingByID(shbuildingID);
	}

	public List<Shbuilding> getShbuildingList() {
		return shbuildingDAO.getShbuildingList();
	}

	public void updateShbuilding(Shbuilding shbuilding) {
		shbuildingDAO.updateShbuilding(shbuilding);
	}

	public IShbuildingDAO getShbuildingDAO() {
		return shbuildingDAO;
	}

	public void setShbuildingDAO(IShbuildingDAO shbuildingDAO) {
		this.shbuildingDAO = shbuildingDAO;
	}

}
