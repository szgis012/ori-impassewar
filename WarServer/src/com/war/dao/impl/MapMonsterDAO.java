package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IMapMonsterDAO;
import com.war.domain.BattleMilitary;
import com.war.domain.MapMonster;

public class MapMonsterDAO extends SqlMapClientDaoSupport implements IMapMonsterDAO{

	public Integer createMapMonster(MapMonster mapMonster) {
		return (Integer)this.getSqlMapClientTemplate().insert("MapMonster.createMapMonster", mapMonster);
	}
	
	public Integer[] createMapMonsterBatch(MapMonster[] mapMonsterArray){
		Integer[] resultArray = new Integer[mapMonsterArray.length];
		for(int i=0;i<mapMonsterArray.length;i++){
			resultArray[i] = (Integer)this.getSqlMapClientTemplate().insert("MapMonster.createMapMonster",mapMonsterArray[i]);
		}
		return resultArray;
	}
	
	public void updateMapMonsterArmyByBattleMilitary(BattleMilitary battleMilitary){
		this.getSqlMapClientTemplate().update("MapMonster.updateMapMonsterArmyByBattleMilitary",battleMilitary);
	}
	
	public void updateMapMonster(MapMonster mapMonster) {
		this.getSqlMapClientTemplate().update("MapMonster.updateMapMonster", mapMonster);
	}
	
	public void deleteMapMonsterByID(Integer mapMonsterID) {
		this.getSqlMapClientTemplate().delete("MapMonster.deleteMapMonsterByID", mapMonsterID);
	}
	
	public void deleteMapMonsterNotInTargetMapMonsterID(String targetMapMonsterIDStr){
		this.getSqlMapClientTemplate().delete("MapMonster.deleteMapMonsterNotInTargetMapMonsterID", targetMapMonsterIDStr);
	}
	
	public void deleteNoDepoyQueueAndNotInBattleMapMonster(){
		this.getSqlMapClientTemplate().delete("MapMonster.deleteNoDepoyQueueAndNotInBattleMapMonster");
	}
	
	public BattleMilitary getMapMonsterAsBattleMilitaryByID(Integer mapMonsterID){
		return (BattleMilitary)this.getSqlMapClientTemplate().queryForObject("MapMonster.getMapMonsterAsBattleMilitaryByID",mapMonsterID);
	}
	
	public MapMonster getMapMonsterByID(Integer mapMonsterID) {
		return (MapMonster)this.getSqlMapClientTemplate().queryForObject("MapMonster.getMapMonsterByID", mapMonsterID);
	}
	
	@SuppressWarnings("unchecked")
	public List<MapMonster> getMapMonsterList() {
		return this.getSqlMapClientTemplate().queryForList("MapMonster.getMapMonsterList");
	}

}