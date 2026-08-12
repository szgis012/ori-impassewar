package com.war.test.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMapFavouriteDAO;
import com.war.domain.MapFavourite;

public class MapFavouriteDAOTest {

	private static IMapFavouriteDAO mapFavouriteDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapFavouriteDAO = (IMapFavouriteDAO)SpringService.getApplicationContext().getBean("mapFavouriteDAO");
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

		Integer mapFavouriteID = 1;
		Integer playerID = 1;
		Integer mapID = 1;
		Date createTime = new Date();

		MapFavourite mapFavourite = new MapFavourite();
		
		mapFavourite.setMapFavouriteID(mapFavouriteID);
		mapFavourite.setPlayerID(playerID);
		mapFavourite.setMapID(mapID);
		mapFavourite.setCreateTime(createTime);

		//测试创建
		mapFavouriteID = mapFavouriteDAO.createMapFavourite(mapFavourite);
		assertNotNull(mapFavouriteID);

		//测试通过编号获得对象
		MapFavourite destMapFavourite = mapFavouriteDAO.getMapFavouriteByID(mapFavouriteID);
		assertNotNull(destMapFavourite);
		assertEquals(mapFavouriteID,destMapFavourite.getMapFavouriteID());
		assertEquals(playerID,destMapFavourite.getPlayerID());
		assertEquals(mapID,destMapFavourite.getMapID());
//		assertEquals(createTime,destMapFavourite.getCreateTime());

		//测试获得列表
		List<MapFavourite> mapFavouriteList = mapFavouriteDAO.getMapFavouriteList();
		assertFalse(mapFavouriteList.isEmpty());

		//测试更新
		playerID = 10;
		mapID = 10;
		destMapFavourite.setMapFavouriteID(mapFavouriteID);
		destMapFavourite.setPlayerID(playerID);
		destMapFavourite.setMapID(mapID);
		destMapFavourite.setCreateTime(createTime);
		mapFavouriteDAO.updateMapFavourite(destMapFavourite);
		MapFavourite updatedMapFavourite = mapFavouriteDAO.getMapFavouriteByID(mapFavouriteID);
		assertNotNull(updatedMapFavourite);
		assertEquals(mapFavouriteID,updatedMapFavourite.getMapFavouriteID());
		assertEquals(playerID,updatedMapFavourite.getPlayerID());
		assertEquals(mapID,updatedMapFavourite.getMapID());
//		assertEquals(createTime,updatedMapFavourite.getCreateTime());

		//测试删除
		mapFavouriteDAO.deleteMapFavouriteByID(mapFavouriteID);
		assertNull(mapFavouriteDAO.getMapFavouriteByID(mapFavouriteID));

	}

}