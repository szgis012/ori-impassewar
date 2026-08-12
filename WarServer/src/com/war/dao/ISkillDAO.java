package com.war.dao;

import java.util.List;

import com.war.domain.Skill;

/**
 * 技能DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */
public interface ISkillDAO {

	/**
	 * 创建技能
	 * @param skill
	 * @return
	 */
	public Integer createSkill(Skill skill);

	/**
	 * 更新技能
	 * @param skill
	 */
	public void updateSkill(Skill skill);

	/**
	 * 根据技能编号及等级删除技能
	 * @param skillID
	 * @param level
	 */
	public void deleteSkillByIDAndLevel(Integer skillID,Integer level);

	/**
	 * 根据技能编号及等级获得技能
	 * @param skillID
	 * @param level
	 * @return
	 */
	public Skill getSkillByIDAndLevel(Integer skillID,Integer level);

	/**
	 * 获得等级为1的技能列表
	 * @return
	 */
	public List<Skill> getLevel1SkillList();

	/**
	 * 根据技能编号获得技能列表
	 * @param skillID
	 * @return 技能列表
	 */
	public List<Skill> getSkillListBySkillID(Integer skillID);
	
	/**
	 * 获得技能列表
	 * @return
	 */
	public List<Skill> getSkillList();
	
	/**
	 * 获得技能编号列表
	 * @return 技能编号列表
	 */
	public List<Integer> getSkillIDList();
	
}