package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityBuildingDAO;
import com.war.domain.CityBuilding;

public class CityBuildingDAOTest {

	private static ICityBuildingDAO cityBuildingDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityBuildingDAO = (ICityBuildingDAO)SpringService.getApplicationContext().getBean("cityBuildingDAO");
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

		Integer cityID = 1;
		Integer buildingID = 1;
		Integer position = 1;
		Integer level = 1;
		Integer state = 1;

		CityBuilding cityBuilding = new CityBuilding();
		
		cityBuilding.setCityID(cityID);
		cityBuilding.setBuildingID(buildingID);
		cityBuilding.setPosition(position);
		cityBuilding.setLevel(level);
		cityBuilding.setState(state);

		//测试创建
		Integer cityBuildingID = cityBuildingDAO.createCityBuilding(cityBuilding);
		assertNotNull(cityBuildingID);

		//测试通过编号获得对象
		CityBuilding destCityBuilding = cityBuildingDAO.getCityBuildingByID(cityBuildingID);
		assertNotNull(destCityBuilding);
		assertEquals(cityBuildingID,destCityBuilding.getCityBuildingID());
		assertEquals(cityID,destCityBuilding.getCityID());
		assertEquals(buildingID,destCityBuilding.getBuildingID());
		assertEquals(position,destCityBuilding.getPosition());
		assertEquals(level,destCityBuilding.getLevel());
		assertEquals(state,destCityBuilding.getState());
		
		//测试获得列表
		List<CityBuilding> cityBuildingList = cityBuildingDAO.getCityBuildingListByCityID(1);
		assertFalse(cityBuildingList.isEmpty());

		//测试更新
		cityID = 10;
		buildingID = 10;
		position = 10;
		level = 10;
		state = 10;
		destCityBuilding.setCityBuildingID(cityBuildingID);
		destCityBuilding.setCityID(cityID);
		destCityBuilding.setBuildingID(buildingID);
		destCityBuilding.setPosition(position);
		destCityBuilding.setLevel(level);
		destCityBuilding.setState(state);
		cityBuildingDAO.updateCityBuilding(destCityBuilding);
		CityBuilding updatedCityBuilding = cityBuildingDAO.getCityBuildingByID(cityBuildingID);
		assertNotNull(updatedCityBuilding);
		assertEquals(cityBuildingID,updatedCityBuilding.getCityBuildingID());
		assertEquals(cityID,updatedCityBuilding.getCityID());
		assertEquals(buildingID,updatedCityBuilding.getBuildingID());
		assertEquals(position,updatedCityBuilding.getPosition());
		assertEquals(level,updatedCityBuilding.getLevel());
		assertEquals(state,updatedCityBuilding.getState());

		//测试删除
		cityBuildingDAO.deleteCityBuildingByID(cityBuildingID);
		assertNull(cityBuildingDAO.getCityBuildingByID(cityBuildingID));

	}

}