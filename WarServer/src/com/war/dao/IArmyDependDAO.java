package com.war.dao;


import java.util.List;

import com.war.domain.ArmyDepend;

/**
 * 兵种依赖dao
 *
 * @author ghleed
 * @version 1.0
 */
public interface IArmyDependDAO {

	public Integer createArmyDepend(ArmyDepend armyDepend);

	public void updateArmyDepend(ArmyDepend armyDepend);

	public void deleteArmyDependByID(Integer armyDependID);

	public ArmyDepend getArmyDependByID(Integer armyDependID);

	public List<ArmyDepend> getArmyDependList();

	public ArmyDepend getArmyDepend(Integer armyID,Integer ordnanceID);
	
	public List<ArmyDepend> getArmyDependList(Integer armyID);	
}