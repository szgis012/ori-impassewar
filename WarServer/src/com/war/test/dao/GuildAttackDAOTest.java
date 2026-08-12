package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildAttackDAO;
import com.war.domain.GuildAttack;

public class GuildAttackDAOTest {

	private static IGuildAttackDAO guildAttackDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildAttackDAO = (IGuildAttackDAO)SpringService.getApplicationContext().getBean("guildAttackDAO");
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
		Integer type = 1;
		String description = "测试字符串";

		GuildAttack guildAttack = new GuildAttack();
		
		guildAttack.setGuildID(guildID);
		guildAttack.setType(type);
		guildAttack.setDescription(description);

		//测试创建
		Integer guildAttackID = guildAttackDAO.createGuildAttack(guildAttack);
		assertNotNull(guildAttackID);

		/*//测试通过编号获得对象
		GuildAttack destGuildAttack = guildAttackDAO.getGuildAttackByID(guildAttackID);
		assertNotNull(destGuildAttack);
		assertEquals(guildAttackID,destGuildAttack.getGuildAttackID());
		assertEquals(guildID,destGuildAttack.getGuildID());
		assertEquals(type,destGuildAttack.getType());
		assertEquals(description,destGuildAttack.getDescription());
		assertEquals(targetGuildName,destGuildAttack.getTargetGuildName());
		assertEquals(createTime,destGuildAttack.getCreateTime());
		
		//测试获得列表
		List<GuildAttack> guildAttackList = guildAttackDAO.getGuildAttackList();
		assertFalse(guildAttackList.isEmpty());

		//测试更新
		guildAttackID = 10;
		guildID = 10;
		type = 10;
		description = "字符串修改";
		targetGuildName = "字符串修改";
		destGuildAttack.setGuildAttackID(guildAttackID);
		destGuildAttack.setGuildID(guildID);
		destGuildAttack.setType(type);
		destGuildAttack.setDescription(description);
		destGuildAttack.setTargetGuildName(targetGuildName);
		destGuildAttack.setCreateTime(createTime);
		guildAttackDAO.updateGuildAttack(destGuildAttack);
		GuildAttack updatedGuildAttack = guildAttackDAO.getGuildAttackByID(guildAttackID);
		assertNotNull(updatedGuildAttack);
		assertEquals(guildAttackID,updatedGuildAttack.getGuildAttackID());
		assertEquals(guildID,updatedGuildAttack.getGuildID());
		assertEquals(type,updatedGuildAttack.getType());
		assertEquals(description,updatedGuildAttack.getDescription());
		assertEquals(targetGuildName,updatedGuildAttack.getTargetGuildName());
		assertEquals(createTime,updatedGuildAttack.getCreateTime());

		//测试删除
		guildAttackDAO.deleteGuildAttackByID(guildAttackID);
		assertNull(guildAttackDAO.getGuildAttackByID(guildAttackID));*/

	}

}