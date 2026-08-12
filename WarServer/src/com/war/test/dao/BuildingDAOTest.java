package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IBuildingDAO;
import com.war.domain.Building;

public class BuildingDAOTest {

	private static IBuildingDAO buildingDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		buildingDAO = (IBuildingDAO)SpringService.getApplicationContext().getBean("buildingDAO");
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
		String description = "测试字符串";
		Integer isOnlyone = 1;

		Building building = new Building();
		
		building.setName(name);
		building.setImage(image);
		building.setDescription(description);
		building.setIsOnlyone(isOnlyone);

		//测试创建
		Integer buildingID = buildingDAO.createBuilding(building);
		assertNotNull(buildingID);

		assertNotNull(buildingDAO.getBuildingName(buildingID));
		
		//测试通过编号获得对象
		Building destBuilding = buildingDAO.getBuildingByID(buildingID);
		assertNotNull(destBuilding);
		assertEquals(buildingID,destBuilding.getBuildingID());
		assertEquals(name,destBuilding.getName());
		assertEquals(image,destBuilding.getImage());
		assertEquals(description,destBuilding.getDescription());
		assertEquals(isOnlyone,destBuilding.getIsOnlyone());
		
		//测试获得列表
		List<Building> buildingList = buildingDAO.getBuildingList();
		assertFalse(buildingList.isEmpty());

		//测试更新
		buildingID = 10;
		name = "字符串修改";
		image = "字符串修改";
		description = "字符串修改";
		isOnlyone = 10;
		destBuilding.setBuildingID(buildingID);
		destBuilding.setName(name);
		destBuilding.setImage(image);
		destBuilding.setDescription(description);
		destBuilding.setIsOnlyone(isOnlyone);
		buildingDAO.updateBuilding(destBuilding);
		Building updatedBuilding = buildingDAO.getBuildingByID(buildingID);
		assertNotNull(updatedBuilding);
		assertEquals(buildingID,updatedBuilding.getBuildingID());
		assertEquals(name,updatedBuilding.getName());
		assertEquals(image,updatedBuilding.getImage());
		assertEquals(description,updatedBuilding.getDescription());
		assertEquals(isOnlyone,updatedBuilding.getIsOnlyone());

		//测试删除
		buildingDAO.deleteBuildingByID(buildingID);
		assertNull(buildingDAO.getBuildingByID(buildingID));

	}

}