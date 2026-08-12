package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IDeclareWarDAO;
import com.war.domain.DeclareWar;

/**
 * 宣战DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */

public class DeclareWarDAO extends SqlMapClientDaoSupport implements IDeclareWarDAO{

	public Integer createDeclareWar(DeclareWar declareWar) {
		return (Integer)this.getSqlMapClientTemplate().insert("DeclareWar.createDeclareWar", declareWar);
	}
	
	public void updateDeclareWar(DeclareWar declareWar) {
		this.getSqlMapClientTemplate().update("DeclareWar.updateDeclareWar", declareWar);
	}
	
	public void deleteFinishedDeclareWarList(){
		this.getSqlMapClientTemplate().delete("DeclareWar.deleteFinishedDeclareWarList");
	}
	
	public void deleteDeclareWarByID(Integer declareWarID) {
		this.getSqlMapClientTemplate().delete("DeclareWar.deleteDeclareWarByID", declareWarID);
	}
	
	public DeclareWar getDeclareWarByID(Integer declareWarID) {
		return (DeclareWar)this.getSqlMapClientTemplate().queryForObject("DeclareWar.getDeclareWarByID", declareWarID);
	}

	public DeclareWar getDeclareWarByPlayerIDAndTargetPlayerID(
			Integer playerID, Integer targetPlayerID) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("playerID", playerID);
		params.put("targetPlayerID", targetPlayerID);
		
		return (DeclareWar)this.getSqlMapClientTemplate().queryForObject("DeclareWar.getDeclareWarByPlayerIDAndTargetPlayerID",params);
	}

	@SuppressWarnings("unchecked")
	public List<DeclareWar> getDeclareWarListByPlayerID(Integer playerID) {
		return this.getSqlMapClientTemplate().queryForList("DeclareWar.getDeclareWarListByPlayerID",playerID);
	}

	public Integer  getDeclareWarCountByPlayerID(Integer playerID){
		 return (Integer)this.getSqlMapClientTemplate().queryForObject("DeclareWar.getDeclareWarCountByPlayerID", playerID);
	}
}