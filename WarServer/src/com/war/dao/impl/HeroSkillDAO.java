package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IHeroSkillDAO;
import com.war.domain.HeroSkill;

public class HeroSkillDAO extends SqlMapClientDaoSupport implements IHeroSkillDAO{

	public Integer createHeroSkill(HeroSkill heroSkill) {
		return (Integer)this.getSqlMapClientTemplate().insert("HeroSkill.createHeroSkill", heroSkill);
	}
	
	public void updateHeroSkillProficiency(Integer heroSkillID,Integer proficiency){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("heroSkillID", heroSkillID);
		params.put("proficiency", proficiency);
		
		this.getSqlMapClientTemplate().update("HeroSkill.updateHeroSkillProficiency",params);
	}
	
	public void updateHeroSkill(HeroSkill heroSkill) {
		this.getSqlMapClientTemplate().update("HeroSkill.updateHeroSkill", heroSkill);
	}
	
	public void deleteHeroSkillByID(Integer heroSkillID) {
		this.getSqlMapClientTemplate().delete("HeroSkill.deleteHeroSkillByID", heroSkillID);
	}
	
	public void deleteHeroSkillListByCityHeroID(Integer cityHeroID){
		this.getSqlMapClientTemplate().delete("HeroSkill.deleteHeroSkillListByCityHeroID", cityHeroID);
	}
	
	public HeroSkill getHeroSkillByID(Integer heroSkillID) {
		return (HeroSkill)this.getSqlMapClientTemplate().queryForObject("HeroSkill.getHeroSkillByID", heroSkillID);
	}
	
	public HeroSkill getHeroSkillByCityHeroIDAndSkillID(Integer cityHeroID, Integer skillID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("skillID", skillID);
		
		return (HeroSkill)this.getSqlMapClientTemplate().queryForObject("HeroSkill.getHeroSkillByCityHeroIDAndSkillID", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<HeroSkill> getHeroSkillListByCityHeroID(Integer cityHeroID){
		return this.getSqlMapClientTemplate().queryForList("HeroSkill.getHeroSkillListByCityHeroID",cityHeroID);
	}
	
	@SuppressWarnings("unchecked")
	public List<HeroSkill> getHeroSkillList() {
		return this.getSqlMapClientTemplate().queryForList("HeroSkill.getHeroSkillList");
	}

}