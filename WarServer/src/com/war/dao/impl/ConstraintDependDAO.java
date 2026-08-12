package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IConstraintDependDAO;
import com.war.domain.ConstraintDepend;

public class ConstraintDependDAO extends SqlMapClientDaoSupport implements IConstraintDependDAO{

	public Integer createConstraintDepend(ConstraintDepend constraintDepend) {
		return (Integer)this.getSqlMapClientTemplate().insert("ConstraintDepend.createConstraintDepend", constraintDepend);
	}
	
	public void updateConstraintDepend(ConstraintDepend constraintDepend) {
		this.getSqlMapClientTemplate().update("ConstraintDepend.updateConstraintDepend", constraintDepend);
	}
	
	public void deleteConstraintDependByID(Integer constraintDependID) {
		this.getSqlMapClientTemplate().delete("ConstraintDepend.deleteConstraintDependByID", constraintDependID);
	}
	
	public ConstraintDepend getConstraintDependByID(Integer constraintDependID) {
		return (ConstraintDepend)this.getSqlMapClientTemplate().queryForObject("ConstraintDepend.getConstraintDependByID", constraintDependID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstraintDepend> getConstraintDependListByTypeAndTargetID(Integer type,Integer targetID){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("type", type);
		map.put("targetID", targetID);
		
		return this.getSqlMapClientTemplate().queryForList("ConstraintDepend.getConstraintDependByTypeAndTargetID",map);
	}
	
	public ConstraintDepend getConstraintDependByTypeAndTargetIDAndLevel(Integer type,Integer targetID,Integer level){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("type", type);
		map.put("targetID", targetID);
		map.put("level", level);
		
		return (ConstraintDepend)this.getSqlMapClientTemplate().queryForObject("ConstraintDepend.getConstraintDependByTypeAndTargetIDAndLevel", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstraintDepend> getConstraintDependList() {
		return this.getSqlMapClientTemplate().queryForList("ConstraintDepend.getConstraintDependList");
	}

}