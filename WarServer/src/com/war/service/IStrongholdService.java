package com.war.service;

import java.util.List;

import com.war.domain.Shbuilding;
import com.war.domain.Stronghold;
import com.war.domain.StrongholdShbuilding;

/**
 * 要塞service接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IStrongholdService {
	public Integer createStronghold(Stronghold stronghold);

	public void updateStronghold(Stronghold stronghold);

	public void deleteStrongholdByID(Integer strongholdID);

	public Stronghold getStrongholdByID(Integer strongholdID);

	public List<Stronghold> getStrongholdList();
	
	/**
	 * 获得要塞可建筑的建筑列表
	 * @param strongholdID 要塞编号
	 * @return
	 */
	public List<Shbuilding> getStrongoldAvailableBuildingList(Integer strongholdID);
	
	/**
	 * 获得要塞已建的建筑信息 
	 * @param strongholdID 要塞编号
	 * @return
	 */
	public List<StrongholdShbuilding> getStrongholdBuildingListByStrongholdID(Integer strongholdID); 
}
