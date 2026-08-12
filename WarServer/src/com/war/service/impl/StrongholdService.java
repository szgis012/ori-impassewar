package com.war.service.impl;

import java.util.List;

import com.war.dao.IShbuildingDAO;
import com.war.dao.IStrongholdDAO;
import com.war.dao.IStrongholdShbuildingDAO;
import com.war.domain.Shbuilding;
import com.war.domain.Stronghold;
import com.war.domain.StrongholdShbuilding;
import com.war.service.IStrongholdService;

/**
 * 要塞service接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class StrongholdService implements IStrongholdService {
	private IStrongholdDAO strongholdDAO;
	private IShbuildingDAO shbuildingDAO;
	private IStrongholdShbuildingDAO strongholdShbuildingDAO;
	
	public Integer createStronghold(Stronghold stronghold) {
		return strongholdDAO.createStronghold(stronghold);
	}

	public void deleteStrongholdByID(Integer strongholdID) {
		strongholdDAO.deleteStrongholdByID(strongholdID);
	}

	public Stronghold getStrongholdByID(Integer strongholdID) {
		return strongholdDAO.getStrongholdByID(strongholdID);
	}

	public List<Stronghold> getStrongholdList() {
		return strongholdDAO.getStrongholdList();
	}

	public void updateStronghold(Stronghold stronghold) {
		strongholdDAO.updateStronghold(stronghold);
	}
	
	public List<Shbuilding> getStrongoldAvailableBuildingList(Integer strongholdID){
		//TODO 这里没有对只能建造一项的建筑进行过滤，需要根据后面策划补全
		
		return shbuildingDAO.getShbuildingList();
	}
	
	public List<StrongholdShbuilding> getStrongholdBuildingListByStrongholdID(Integer strongholdID){
		List<StrongholdShbuilding> buildingList = strongholdShbuildingDAO.getStrongholdBuildingListByStrongholdID(strongholdID);
		
		//TODO 这里没有包含依赖约束信息
		for(StrongholdShbuilding building : buildingList){
			building.setBuilding(shbuildingDAO.getShbuildingByID(building.getShbuildingID()));
		}
		
		return buildingList;
	}

	public IStrongholdDAO getStrongholdDAO() {
		return strongholdDAO;
	}

	public void setStrongholdDAO(IStrongholdDAO strongholdDAO) {
		this.strongholdDAO = strongholdDAO;
	}

	public IShbuildingDAO getShbuildingDAO() {
		return shbuildingDAO;
	}

	public void setShbuildingDAO(IShbuildingDAO shbuildingDAO) {
		this.shbuildingDAO = shbuildingDAO;
	}

	public IStrongholdShbuildingDAO getStrongholdShbuildingDAO() {
		return strongholdShbuildingDAO;
	}

	public void setStrongholdShbuildingDAO(
			IStrongholdShbuildingDAO strongholdShbuildingDAO) {
		this.strongholdShbuildingDAO = strongholdShbuildingDAO;
	}

}
