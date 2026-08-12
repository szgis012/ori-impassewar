package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildRelationshipDAO;
import com.war.domain.GuildRelationship;

public class GuildRelationshipDAOTest {

	private static IGuildRelationshipDAO guildRelationshipDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildRelationshipDAO = (IGuildRelationshipDAO)SpringService.getApplicationContext().getBean("guildRelationshipDAO");
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

		Integer guildID = 1;
		Integer targetGuildID = 1;
		Integer type = 1;

		GuildRelationship guildRelation = new GuildRelationship();
		
		guildRelation.setGuildID(guildID);
		guildRelation.setTargetGuildID(targetGuildID);
		guildRelation.setType(type);

		//测试创建
		Integer guildRelationID = guildRelationshipDAO.createGuildRelationship(guildRelation);
		assertNotNull(guildRelationID);

		/*//测试通过编号获得对象
		GuildRelation destGuildRelation = guildRelationshipDAO.getGuildRelationByID(guildRelationID);
		assertNotNull(destGuildRelation);
		assertEquals(guildRelationID,destGuildRelation.getGuildRelationID());
		assertEquals(guildID,destGuildRelation.getGuildID());
		assertEquals(targetGuildID,destGuildRelation.getTargetGuildID());
		assertEquals(type,destGuildRelation.getType());
		
		//测试获得列表
		List<GuildRelation> guildRelationList = guildRelationshipDAO.getGuildRelationList();
		assertFalse(guildRelationList.isEmpty());

		//测试更新
		guildRelationID = 10;
		guildID = 10;
		targetGuildID = 10;
		type = 10;
		destGuildRelation.setGuildRelationID(guildRelationID);
		destGuildRelation.setGuildID(guildID);
		destGuildRelation.setTargetGuildID(targetGuildID);
		destGuildRelation.setType(type);
		guildRelationshipDAO.updateGuildRelation(destGuildRelation);
		GuildRelation updatedGuildRelation = guildRelationshipDAO.getGuildRelationByID(guildRelationID);
		assertNotNull(updatedGuildRelation);
		assertEquals(guildRelationID,updatedGuildRelation.getGuildRelationID());
		assertEquals(guildID,updatedGuildRelation.getGuildID());
		assertEquals(targetGuildID,updatedGuildRelation.getTargetGuildID());
		assertEquals(type,updatedGuildRelation.getType());

		//测试删除
		guildRelationshipDAO.deleteGuildRelationByID(guildRelationID);
		assertNull(guildRelationshipDAO.getGuildRelationByID(guildRelationID));*/

	}

}