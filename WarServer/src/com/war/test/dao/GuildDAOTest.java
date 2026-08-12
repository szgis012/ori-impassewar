package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildDAO;
import com.war.domain.Guild;

public class GuildDAOTest {

	private static IGuildDAO guildDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildDAO = (IGuildDAO)SpringService.getApplicationContext().getBean("guildDAO");
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

		String name = "测试字符串";
		String image = "测试字符串";
		Integer chairmanID = 1;
		Long renown = 1L;
		Integer population = 1;
		Integer populationMax = 1;
		String introduction = "测试字符串";
		String notice = "测试字符串";
		Integer level = 0;
		Long money = 0L;
		Integer rank = 15;
		
		Guild guild = new Guild();
		
		guild.setName(name);
		guild.setImage(image);
		guild.setChairmanID(chairmanID);
		guild.setRenown(renown);
		guild.setPopulation(population);
		guild.setPopulationMax(populationMax);
		guild.setIntroduction(introduction);
		guild.setNotice(notice);
		guild.setLevel(level);
		guild.setMoney(money);
		guild.setRank(rank);

		//测试创建
		Integer guildID = guildDAO.createGuild(guild);
		assertNotNull(guildID);

		//测试通过编号获得对象
		Guild destGuild = guildDAO.getGuildByID(guildID);
		assertNotNull(destGuild);
		assertEquals(guildID,destGuild.getGuildID());
		assertEquals(name,destGuild.getName());
		assertEquals(image,destGuild.getImage());
		assertEquals(chairmanID,destGuild.getChairmanID());
		assertEquals(renown,destGuild.getRenown());
		assertEquals(population,destGuild.getPopulation());
		assertEquals(populationMax,destGuild.getPopulationMax());
		assertEquals(introduction,destGuild.getIntroduction());
		assertEquals(notice,destGuild.getNotice());
		assertEquals(level,destGuild.getLevel());
		assertEquals(money,destGuild.getMoney());
		assertEquals(rank,destGuild.getRank());
		
		//测试获得列表
		List<Guild> guildList = guildDAO.getGuildList();
		assertFalse(guildList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		chairmanID = 10;
		renown = 10L;
		population = 10;
		populationMax = 10;
		introduction = "字符串修改";
		notice = "字符串修改";
		destGuild.setGuildID(guildID);
		destGuild.setName(name);
		destGuild.setImage(image);
		destGuild.setChairmanID(chairmanID);
		destGuild.setRenown(renown);
		destGuild.setPopulation(population);
		destGuild.setPopulationMax(populationMax);
		destGuild.setIntroduction(introduction);
		destGuild.setNotice(notice);
		guildDAO.updateGuild(destGuild);
		Guild updatedGuild = guildDAO.getGuildByID(guildID);
		assertNotNull(updatedGuild);
		assertEquals(guildID,updatedGuild.getGuildID());
		assertEquals(name,updatedGuild.getName());
		assertEquals(image,updatedGuild.getImage());
		assertEquals(chairmanID,updatedGuild.getChairmanID());
		assertEquals(renown,updatedGuild.getRenown());
		assertEquals(population,updatedGuild.getPopulation());
		assertEquals(populationMax,updatedGuild.getPopulationMax());
		assertEquals(introduction,updatedGuild.getIntroduction());
		assertEquals(notice,updatedGuild.getNotice());

		//测试删除
		guildDAO.deleteGuildByID(guildID);
		assertNull(guildDAO.getGuildByID(guildID));

	}

}