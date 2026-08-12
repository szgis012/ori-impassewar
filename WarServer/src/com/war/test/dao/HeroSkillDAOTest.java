package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IHeroSkillDAO;
import com.war.domain.HeroSkill;

public class HeroSkillDAOTest {

	private static IHeroSkillDAO heroSkillDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		heroSkillDAO = (IHeroSkillDAO)SpringService.getApplicationContext().getBean("heroSkillDAO");
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

		Integer cityHeroID = 1;
		Integer skillID = 1;
		Integer level = 1;
		Integer proficiency = 1;

		HeroSkill heroSkill = new HeroSkill();
		
		heroSkill.setCityHeroID(cityHeroID);
		heroSkill.setSkillID(skillID);
		heroSkill.setLevel(level);
		heroSkill.setProficiency(proficiency);

		//测试创建
		Integer heroSkillID = heroSkillDAO.createHeroSkill(heroSkill);
		assertNotNull(heroSkillID);

		//测试通过编号获得对象
		HeroSkill destHeroSkill = heroSkillDAO.getHeroSkillByID(heroSkillID);
		assertNotNull(destHeroSkill);
		assertEquals(heroSkillID,destHeroSkill.getHeroSkillID());
		assertEquals(cityHeroID,destHeroSkill.getCityHeroID());
		assertEquals(skillID,destHeroSkill.getSkillID());
		assertEquals(level,destHeroSkill.getLevel());
		assertEquals(proficiency,destHeroSkill.getProficiency());
		
		//测试获得列表
		List<HeroSkill> heroSkillList = heroSkillDAO.getHeroSkillList();
		assertFalse(heroSkillList.isEmpty());

		//测试更新
		cityHeroID = 10;
		skillID = 10;
		level = 10;
		proficiency = 10;
		destHeroSkill.setHeroSkillID(heroSkillID);
		destHeroSkill.setCityHeroID(cityHeroID);
		destHeroSkill.setSkillID(skillID);
		destHeroSkill.setLevel(level);
		destHeroSkill.setProficiency(proficiency);
		heroSkillDAO.updateHeroSkill(destHeroSkill);
		HeroSkill updatedHeroSkill = heroSkillDAO.getHeroSkillByID(heroSkillID);
		assertNotNull(updatedHeroSkill);
		assertEquals(heroSkillID,updatedHeroSkill.getHeroSkillID());
		assertEquals(cityHeroID,updatedHeroSkill.getCityHeroID());
		assertEquals(skillID,updatedHeroSkill.getSkillID());
		assertEquals(level,updatedHeroSkill.getLevel());
		assertEquals(proficiency,updatedHeroSkill.getProficiency());

		//测试删除
		heroSkillDAO.deleteHeroSkillByID(heroSkillID);
		assertNull(heroSkillDAO.getHeroSkillByID(heroSkillID));

	}

}