package com.war.test.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;

import com.war.common.SpringService;
import com.war.domain.CityWoundedArmy;
import com.war.service.IArmyService;
import com.war.service.impl.ArmyService;

public class ArmyServiceTest {
	
	private static IArmyService armyService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		armyService = (IArmyService) SpringService.getApplicationContext().getBean("armyService");
	}
	
	@Test
	public void testCreateCityWoundedArmy(){
		
	}
	
	@Test
	public void testDismissCityWoundedArmy(){
		
	}
	
	@Test
	public void testCureCityWoundedArmy(){
		
	}
	
	@Test
	public void testCityWoundedArmy(){
		// 添加伤兵
		CityWoundedArmy cityWoundedArmy = new CityWoundedArmy();
		cityWoundedArmy.setArmyID(1);
		cityWoundedArmy.setCityID(8);
		cityWoundedArmy.setNum(10);
		Integer cityWoundedArmyID =  armyService.createCityWoundedArmy(cityWoundedArmy);
		assertNotNull(cityWoundedArmyID);
		
		// 治愈伤兵
		armyService.cureCityWoundedArmy(cityWoundedArmyID, 5);
		
		// 遣散伤兵
		armyService.dismissCityWoundedArmy(cityWoundedArmyID, 2);
	}
	
	@Test
	public void testCityWoundedArmy02(){
		// 添加伤兵
		CityWoundedArmy cityWoundedArmy = new CityWoundedArmy();
		cityWoundedArmy.setArmyID(8);
		cityWoundedArmy.setCityID(8);
		cityWoundedArmy.setNum(10);
		Integer cityWoundedArmyID =  armyService.createCityWoundedArmy(cityWoundedArmy);
		assertNotNull(cityWoundedArmyID);
		
		// 治愈伤兵
		// armyService.cureCityWoundedArmy(cityWoundedArmyID, 10);
		// assertNull(armyService.getCityWoundedArmyByID(cityWoundedArmyID));
		
		// 遣散伤兵
		armyService.dismissCityWoundedArmy(cityWoundedArmyID, 10);
	}
}
