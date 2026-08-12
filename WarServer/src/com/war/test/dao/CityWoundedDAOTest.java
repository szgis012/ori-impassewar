package com.war.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityWoundedArmyDAO;
import com.war.domain.CityWoundedArmy;

public class CityWoundedDAOTest {

	private static ICityWoundedArmyDAO cityWoundedArmyDAO;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityWoundedArmyDAO = (ICityWoundedArmyDAO) SpringService.getApplicationContext().getBean("cityWoundedArmyDAO");
	}

	@Test
	public void testCreateCityWoundedArmy() {
	}

	@Test
	public void testDeleteCityWoundedArmyByID() {
	}

	@Test
	public void testGetCityWoundedArmyListByCityID() {
	}

	@Test
	public void testUpdateCityWoundedArmy() {
	}

	@Test
	public void testGetCityWoundedArmyByID() {
	}

	@Test
	public void testGetCityWoundedArmyList() {
	}
	
	@Test
	public void testCURD() {

		Integer cityWoundedarmyID = 1;
		Integer cityID = 1;
		Integer armyID = 1;
		Integer num = 1;
		Date deathTime = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

		CityWoundedArmy CityWoundedArmy = new CityWoundedArmy();
		
		CityWoundedArmy.setCityWoundedArmyID(cityWoundedarmyID);
		CityWoundedArmy.setCityID(cityID);
		CityWoundedArmy.setArmyID(armyID);
		CityWoundedArmy.setNum(num);
		CityWoundedArmy.setDeathTime(deathTime);

		//测试创建
		Integer returnCityWoundedArmyID = cityWoundedArmyDAO.createCityWoundedArmy(CityWoundedArmy);
		assertNotNull(returnCityWoundedArmyID);

		//测试通过编号获得对象
		CityWoundedArmy destCityWoundedarmy = cityWoundedArmyDAO.getCityWoundedArmyByID(returnCityWoundedArmyID);
		assertNotNull(destCityWoundedarmy);
		assertEquals(returnCityWoundedArmyID,destCityWoundedarmy.getCityWoundedArmyID());
		assertEquals(cityID,destCityWoundedarmy.getCityID());
		assertEquals(armyID,destCityWoundedarmy.getArmyID());
		assertEquals(num,destCityWoundedarmy.getNum());
		System.out.println(deathTime);
		System.out.println(destCityWoundedarmy.getDeathTime());
		//assertEquals(deathTime,destCityWoundedarmy.getDeathTime());
		
		// 通过城市编号获得城市伤兵列表
		List<CityWoundedArmy> cityWoundedArmyList = cityWoundedArmyDAO.getCityWoundedArmyListByCityID(destCityWoundedarmy.getCityID());
		assertNotNull(cityWoundedArmyList.isEmpty());

		//测试获得列表
		List<CityWoundedArmy> cityWoundedarmyList = cityWoundedArmyDAO.getCityWoundedArmyListByCityID(destCityWoundedarmy.getCityID());
		assertFalse(cityWoundedarmyList.isEmpty());

		//测试更新
		cityID = 10;
		armyID = 10;
		num = 10;
		//destCityWoundedarmy.setCityWoundedArmyID(returnCityWoundedArmyID);
		destCityWoundedarmy.setCityID(cityID);
		destCityWoundedarmy.setArmyID(armyID);
		destCityWoundedarmy.setNum(num);
		destCityWoundedarmy.setDeathTime(deathTime);
		cityWoundedArmyDAO.updateCityWoundedArmy(destCityWoundedarmy);
		CityWoundedArmy updatedCityWoundedarmy = cityWoundedArmyDAO.getCityWoundedArmyByID(returnCityWoundedArmyID);
		assertNotNull(updatedCityWoundedarmy);
		assertEquals(returnCityWoundedArmyID,updatedCityWoundedarmy.getCityWoundedArmyID());
		assertEquals(cityID,updatedCityWoundedarmy.getCityID());
		assertEquals(armyID,updatedCityWoundedarmy.getArmyID());
		assertEquals(num,updatedCityWoundedarmy.getNum());
		// assertEquals(deathTime,updatedCityWoundedarmy.getDeathTime());

		//测试删除
		cityWoundedArmyDAO.deleteCityWoundedArmyByID(returnCityWoundedArmyID);
		assertNull(cityWoundedArmyDAO.getCityWoundedArmyByID(returnCityWoundedArmyID));

	}

}
