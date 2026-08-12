package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ISkillDAO;
import com.war.domain.Skill;

public class SkillDAO extends SqlMapClientDaoSupport implements ISkillDAO{

	public Integer createSkill(Skill skill) {
		return (Integer)this.getSqlMapClientTemplate().insert("Skill.createSkill", skill);
	}
	
	public void updateSkill(Skill skill) {
		this.getSqlMapClientTemplate().update("Skill.updateSkill", skill);
	}
	
	public void deleteSkillByIDAndLevel(Integer skillID,Integer level) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("skillID", skillID);
		params.put("level", level);
		
		this.getSqlMapClientTemplate().delete("Skill.deleteSkillByIDAndLevel", params);
	}
	
	public Skill getSkillByIDAndLevel(Integer skillID,Integer level) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("skillID", skillID);
		params.put("level", level);
		
		return (Skill)this.getSqlMapClientTemplate().queryForObject("Skill.getSkillByIDAndLevel", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Skill> getLevel1SkillList(){
		return this.getSqlMapClientTemplate().queryForList("Skill.getLevel1SkillList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Skill> getSkillListBySkillID(Integer skillID) {
		return this.getSqlMapClientTemplate().queryForList("Skill.getSkillListBySkillID", skillID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Skill> getSkillList() {
		return this.getSqlMapClientTemplate().queryForList("Skill.getSkillList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getSkillIDList() {
		return this.getSqlMapClientTemplate().queryForList("Skill.getSkillIDList");
	}

}