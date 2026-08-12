package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ISkillDAO;
import com.war.domain.Skill;

public class SkillDAOTest {

	private static ISkillDAO skillDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		skillDAO = (ISkillDAO)SpringService.getApplicationContext().getBean("skillDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCURD() {

		Integer skillID = 1;
		Integer level = 1;
		Integer life = 1;
		Integer attack = 1;
		Integer defense = 1;
		Integer speed = 1;
		Integer range = 1;
		Integer costStamina = 1;
		Integer studyMoney = 1;
		Integer studyProficiency = 1;

		Skill skill = new Skill();
		
		skill.setSkillID(skillID);
		skill.setLevel(level);
		skill.setLife(life);
		skill.setAttack(attack);
		skill.setDefense(defense);
		skill.setSpeed(speed);
		skill.setRange(range);
		skill.setCostStamina(costStamina);
		skill.setStudyMoney(studyMoney);
		skill.setStudyProficiency(studyProficiency);

		//测试创建
		skillDAO.createSkill(skill);

		//测试通过编号获得对象
		Skill destSkill = skillDAO.getSkillByIDAndLevel(skillID,level);
		assertNotNull(destSkill);
		assertEquals(skillID,destSkill.getSkillID());
		assertEquals(level,destSkill.getLevel());
		assertEquals(life,destSkill.getLife());
		assertEquals(attack,destSkill.getAttack());
		assertEquals(defense,destSkill.getDefense());
		assertEquals(speed,destSkill.getSpeed());
		assertEquals(range,destSkill.getRange());
		assertEquals(costStamina,destSkill.getCostStamina());
		assertEquals(studyMoney,destSkill.getStudyMoney());
		assertEquals(studyProficiency,destSkill.getStudyProficiency());
		
		//测试获得列表
		List<Skill> skillList = skillDAO.getSkillList();
		assertFalse(skillList.isEmpty());

		//测试更新
		life = 10;
		attack = 10;
		defense = 10;
		speed = 10;
		range = 10;
		costStamina = 10;
		studyMoney = 10;
		studyProficiency = 10;
		destSkill.setSkillID(skillID);
		destSkill.setLevel(level);
		destSkill.setLife(life);
		destSkill.setAttack(attack);
		destSkill.setDefense(defense);
		destSkill.setSpeed(speed);
		destSkill.setRange(range);
		destSkill.setCostStamina(costStamina);
		destSkill.setStudyMoney(studyMoney);
		destSkill.setStudyProficiency(studyProficiency);
		skillDAO.updateSkill(destSkill);
		Skill updatedSkill = skillDAO.getSkillByIDAndLevel(skillID,level);
		assertNotNull(updatedSkill);
		assertEquals(skillID,updatedSkill.getSkillID());
		assertEquals(level,updatedSkill.getLevel());
		assertEquals(life,updatedSkill.getLife());
		assertEquals(attack,updatedSkill.getAttack());
		assertEquals(defense,updatedSkill.getDefense());
		assertEquals(speed,updatedSkill.getSpeed());
		assertEquals(range,updatedSkill.getRange());
		assertEquals(costStamina,updatedSkill.getCostStamina());
		assertEquals(studyMoney,updatedSkill.getStudyMoney());
		assertEquals(studyProficiency,updatedSkill.getStudyProficiency());

		//测试删除
		skillDAO.deleteSkillByIDAndLevel(skillID,level);
		assertNull(skillDAO.getSkillByIDAndLevel(skillID,level));

	}
	
	//@Test
	public void testGetSkillIDList() {
		skillDAO.getSkillIDList();
	}

}