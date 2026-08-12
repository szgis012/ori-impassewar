package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IEquipmentDAO;
import com.war.domain.Equipment;

public class EquipmentDAOTest {

	private static IEquipmentDAO equipmentDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		equipmentDAO = (IEquipmentDAO)SpringService.getApplicationContext().getBean("equipmentDAO");
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
		Integer command = 1;
		Integer defense = 1;
		Integer mind = 1;
		Integer executivepower = 1;
		Integer category = 1;

		Equipment equipment = new Equipment();
		
		equipment.setName(name);
		equipment.setImage(image);
		equipment.setCommand(command);
		equipment.setDefense(defense);
		equipment.setMind(mind);
		equipment.setExecutivepower(executivepower);
		equipment.setCategory(category);

		//测试创建
		Integer equipmentID = equipmentDAO.createEquipment(equipment);
		assertNotNull(equipmentID);

		//测试通过编号获得对象
		Equipment destEquipment = equipmentDAO.getEquipmentByID(equipmentID);
		assertNotNull(destEquipment);
		assertEquals(equipmentID,destEquipment.getEquipmentID());
		assertEquals(name,destEquipment.getName());
		assertEquals(image,destEquipment.getImage());
		assertEquals(command,destEquipment.getCommand());
		assertEquals(defense,destEquipment.getDefense());
		assertEquals(mind,destEquipment.getMind());
		assertEquals(executivepower,destEquipment.getExecutivepower());
		assertEquals(category,destEquipment.getCategory());
		
		//测试获得列表
		List<Equipment> equipmentList = equipmentDAO.getEquipmentList();
		assertFalse(equipmentList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		command = 10;
		defense = 10;
		mind = 10;
		executivepower = 10;
		category = 10;
		destEquipment.setEquipmentID(equipmentID);
		destEquipment.setName(name);
		destEquipment.setImage(image);
		destEquipment.setCommand(command);
		destEquipment.setDefense(defense);
		destEquipment.setMind(mind);
		destEquipment.setExecutivepower(executivepower);
		destEquipment.setCategory(category);
		equipmentDAO.updateEquipment(destEquipment);
		Equipment updatedEquipment = equipmentDAO.getEquipmentByID(equipmentID);
		assertNotNull(updatedEquipment);
		assertEquals(equipmentID,updatedEquipment.getEquipmentID());
		assertEquals(name,updatedEquipment.getName());
		assertEquals(image,updatedEquipment.getImage());
		assertEquals(command,updatedEquipment.getCommand());
		assertEquals(defense,updatedEquipment.getDefense());
		assertEquals(mind,updatedEquipment.getMind());
		assertEquals(executivepower,updatedEquipment.getExecutivepower());
		assertEquals(category,updatedEquipment.getCategory());

		//测试删除
		equipmentDAO.deleteEquipmentByID(equipmentID);
		assertNull(equipmentDAO.getEquipmentByID(equipmentID));

	}

}