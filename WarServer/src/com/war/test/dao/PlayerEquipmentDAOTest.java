package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IPlayerEquipmentDAO;
import com.war.domain.PlayerEquipment;

public class PlayerEquipmentDAOTest {

	private static IPlayerEquipmentDAO playerEquipmentDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerEquipmentDAO = (IPlayerEquipmentDAO)SpringService.getApplicationContext().getBean("playerEquipmentDAO");
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

		Integer playerID = 1;
		Integer equipmentID = 1;

		PlayerEquipment playerEquipment = new PlayerEquipment();
		
		playerEquipment.setPlayerID(playerID);
		playerEquipment.setEquipmentID(equipmentID);

		//测试创建
		Integer playerEquipmentID = playerEquipmentDAO.createPlayerEquipment(playerEquipment);
		assertNotNull(playerEquipmentID);

		//测试通过编号获得对象
		PlayerEquipment destPlayerEquipment = playerEquipmentDAO.getPlayerEquipmentByID(playerEquipmentID);
		assertNotNull(destPlayerEquipment);
		assertEquals(playerEquipmentID,destPlayerEquipment.getPlayerEquipmentID());
		assertEquals(playerID,destPlayerEquipment.getPlayerID());
		assertEquals(equipmentID,destPlayerEquipment.getEquipmentID());
		
		//测试获得列表
		List<PlayerEquipment> playerEquipmentList = playerEquipmentDAO.getPlayerEquipmentList();
		assertFalse(playerEquipmentList.isEmpty());

		//测试更新
		playerID = 10;
		equipmentID = 10;
		destPlayerEquipment.setPlayerEquipmentID(playerEquipmentID);
		destPlayerEquipment.setPlayerID(playerID);
		destPlayerEquipment.setEquipmentID(equipmentID);
		playerEquipmentDAO.updatePlayerEquipment(destPlayerEquipment);
		PlayerEquipment updatedPlayerEquipment = playerEquipmentDAO.getPlayerEquipmentByID(playerEquipmentID);
		assertNotNull(updatedPlayerEquipment);
		assertEquals(playerEquipmentID,updatedPlayerEquipment.getPlayerEquipmentID());
		assertEquals(playerID,updatedPlayerEquipment.getPlayerID());
		assertEquals(equipmentID,updatedPlayerEquipment.getEquipmentID());

		//测试删除
		playerEquipmentDAO.deletePlayerEquipmentByID(playerEquipmentID);
		assertNull(playerEquipmentDAO.getPlayerEquipmentByID(playerEquipmentID));

	}

}