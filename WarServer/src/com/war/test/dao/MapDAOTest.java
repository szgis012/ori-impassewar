package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMapDAO;
import com.war.domain.Map;

public class MapDAOTest {

	private static IMapDAO mapDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapDAO = (IMapDAO)SpringService.getApplicationContext().getBean("mapDAO");
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
	
	//@Test
	public void testCURD() {

		Integer mapID = 1;
		Integer posX = 1;
		Integer posY = 1;
		Integer type = 1;
		Integer state = 1;
		Integer targetID = 1;
		Integer category = 1;

		Map map = new Map();
		
		map.setMapID(mapID);
		map.setPosX(posX);
		map.setPosY(posY);
		map.setType(type);
		map.setState(state);
		map.setTargetID(targetID);
		map.setCategory(category);

		//测试创建
		mapDAO.createMap(map);

		//测试通过编号获得对象
		Map destMap = mapDAO.getMapByID(mapID);
		assertNotNull(destMap);
		assertEquals(mapID,destMap.getMapID());
		assertEquals(posX,destMap.getPosX());
		assertEquals(posY,destMap.getPosY());
		assertEquals(type,destMap.getType());
		assertEquals(state,destMap.getState());
		assertEquals(targetID,destMap.getTargetID());
		assertEquals(category,destMap.getCategory());
		
		//测试获得列表
		List<Map> mapList = mapDAO.getMapList();
		assertFalse(mapList.isEmpty());

		//测试更新
		mapID = 10;
		posX = 10;
		posY = 10;
		type = 10;
		state = 10;
		targetID = 10;
		category = 10;
		destMap.setMapID(mapID);
		destMap.setPosX(posX);
		destMap.setPosY(posY);
		destMap.setType(type);
		destMap.setState(state);
		destMap.setTargetID(targetID);
		destMap.setCategory(category);
		mapDAO.updateMap(destMap);
		Map updatedMap = mapDAO.getMapByID(mapID);
		assertNotNull(updatedMap);
		assertEquals(mapID,updatedMap.getMapID());
		assertEquals(posX,updatedMap.getPosX());
		assertEquals(posY,updatedMap.getPosY());
		assertEquals(type,updatedMap.getType());
		assertEquals(state,updatedMap.getState());
		assertEquals(targetID,updatedMap.getTargetID());
		assertEquals(category,updatedMap.getCategory());

		//测试删除
		mapDAO.deleteMapByID(mapID);
		assertNull(mapDAO.getMapByID(mapID));
	}
	
	//初始化地图数据 
	@Test
	public void initMapData() {

		Integer type = 1;
		Integer state = 1;

		Map map = new Map();
	  
		int ind = 0;
		//500
		for(int j=0; j<50; j++){
			
			for(int i=0; i<50; i++){
				ind++;
				map.setMapID(ind);
				map.setPosX(i);
				map.setPosY(j);
				type = (int)(Math.random() * 23 % 23)+1;//1-23
				map.setType(type);
				state = (int)(Math.random() * 2 % 2)+1;//1-2
				map.setState(state);
				
				if(type == 1 || type == 2){
					//map.setOwner(1);
				}else{
					//map.setOwner(0);
				}
				
				mapDAO.createMap(map);
			}
		}

	}
	
	//@Test
	public void testGetMapList(){
		assertEquals(25,mapDAO.getMapListByStartPosXYAndEndPosXY(0, 0, 5, 5).size());
		assertEquals(16,mapDAO.getMapListByStartPosXYAndEndPosXY(1, 1, 5, 5).size());
	}
   
	@Test
	public void testGetMapRange(){
		List<Map> mlist = mapDAO.getMapList(5, 10);
		assertTrue(mlist.size()>0);
	}

}