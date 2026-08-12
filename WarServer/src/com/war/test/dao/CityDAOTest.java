package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityDAO;
import com.war.dao.ICityResourceDAO;
import com.war.domain.City;
import com.war.domain.CityResource;

public class CityDAOTest {

	private static ICityDAO cityDAO;
	private static ICityResourceDAO cityResourceDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityDAO = (ICityDAO)SpringService.getApplicationContext().getBean("cityDAO");
		cityResourceDAO = (ICityResourceDAO) SpringService.getApplicationContext().getBean("cityResourceDAO");
		
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

		/*Integer playerID = 1;
		Integer posX = 1;
		Integer posY = 1;
		String name = "测试字符串";
		Integer state = 1;
		Long constructionPoint = 1L;
		Long technologyPoint = 1L;
		Long populationFree = 1L;
		Long populationTotal = 1L;
		Long populationMax = 1L;
		Integer recruitNum = 1;
		Integer tax = 1;
		Integer security = 1;
		Integer officer = 1;
		Integer businessmanFree = 1;
		Long woodNum = 1L;
		Long woodNumMax = 1L;
		Long woodOutput = 1L;
		Integer woodWorkerNum = 1;
		Integer woodBuildingAdd = 1;
		Integer woodTechAdd = 1;
		Integer woodFieldAdd = 1;
		Integer woodOfficerAdd = 1;
		Integer woodTreasureAdd = 1;
		Long steelNum = 1L;
		Long steelNumMax = 1L;
		Long steelOutput = 1L;
		Integer steelWorkerNum = 1;
		Integer steelBuildingAdd = 1;
		Integer steelTechAdd = 1;
		Integer steelFieldAdd = 1;
		Integer steelOfficerAdd = 1;
		Integer steelTreasureAdd = 1;
		Long oilNum = 1L;
		Long oilNumMax = 1L;
		Long oilOutput = 1L;
		Integer oilWorkerNum = 1;
		Integer oilBuildingAdd = 1;
		Integer oilTechAdd = 1;
		Integer oilFieldAdd = 1;
		Integer oilOfficerAdd = 1;
		Integer oilTreasureAdd = 1;
		Long foodNum = 1L;
		Long foodNumMax = 1L;
		Long foodOutput = 1L;
		Integer foodWorkerNum = 1;
		Integer foodBuildingAdd = 1;
		Integer foodTechAdd = 1;
		Integer foodFieldAdd = 1;
		Integer foodOfficerAdd = 1;
		Integer foodTreasureAdd = 1;
		Long foodConsume = 1L;
		Long moneyNum = 1L;
		Long moneyNumMax = 1L;
		Long moneyOutput = 1L;
		Integer moneyTechAdd = 1;
		Integer moneyFieldAdd = 1;
		Integer moneyOfficerAdd = 1;
		Integer moneyTreasureAdd = 1;
		Long moneyConsume = 1L;

		City city = new City();
		CityResource cityResource = new CityResource();
		
		city.setPlayerID(playerID);
		city.setPosX(posX);
		city.setPosY(posY);
		city.setName(name);
		city.setState(state);
		city.setConstructionPoint(constructionPoint);
		city.setTechnologyPoint(technologyPoint);
		city.setPopulationFree(populationFree);
		city.setPopulationTotal(populationTotal);
		city.setPopulationMax(populationMax);
		city.setRecruitNum(recruitNum);
		city.setTax(tax);
		city.setSecurity(security);
		city.setOfficer(officer);
		city.setBusinessmanFree(businessmanFree);
		cityResource.setWoodNum(woodNum);
		cityResource.setWoodNumMax(woodNumMax);
		cityResource.setWoodOutput(woodOutput);
		cityResource.setWoodWorkerNum(woodWorkerNum);
		cityResource.setWoodBuildingAdd(woodBuildingAdd);
		cityResource.setWoodTechAdd(woodTechAdd);
		cityResource.setWoodFieldAdd(woodFieldAdd);
		cityResource.setWoodOfficerAdd(woodOfficerAdd);
		cityResource.setWoodTreasureAdd(woodTreasureAdd);
		cityResource.setSteelNum(steelNum);
		cityResource.setSteelNumMax(steelNumMax);
		cityResource.setSteelOutput(steelOutput);
		cityResource.setSteelWorkerNum(steelWorkerNum);
		cityResource.setSteelBuildingAdd(steelBuildingAdd);
		cityResource.setSteelTechAdd(steelTechAdd);
		cityResource.setSteelFieldAdd(steelFieldAdd);
		cityResource.setSteelOfficerAdd(steelOfficerAdd);
		cityResource.setSteelTreasureAdd(steelTreasureAdd);
		cityResource.setOilNum(oilNum);
		cityResource.setOilNumMax(oilNumMax);
		cityResource.setOilOutput(oilOutput);
		cityResource.setOilWorkerNum(oilWorkerNum);
		cityResource.setOilBuildingAdd(oilBuildingAdd);
		cityResource.setOilTechAdd(oilTechAdd);
		cityResource.setOilFieldAdd(oilFieldAdd);
		cityResource.setOilOfficerAdd(oilOfficerAdd);
		cityResource.setOilTreasureAdd(oilTreasureAdd);
		cityResource.setFoodNum(foodNum);
		cityResource.setFoodNumMax(foodNumMax);
		cityResource.setFoodOutput(foodOutput);
		cityResource.setFoodWorkerNum(foodWorkerNum);
		cityResource.setFoodBuildingAdd(foodBuildingAdd);
		cityResource.setFoodTechAdd(foodTechAdd);
		cityResource.setFoodFieldAdd(foodFieldAdd);
		cityResource.setFoodOfficerAdd(foodOfficerAdd);
		cityResource.setFoodTreasureAdd(foodTreasureAdd);
		cityResource.setFoodConsume(foodConsume);
		cityResource.setMoneyNum(moneyNum);
		cityResource.setMoneyNumMax(moneyNumMax);
		cityResource.setMoneyOutput(moneyOutput);
		cityResource.setMoneyTechAdd(moneyTechAdd);
		cityResource.setMoneyFieldAdd(moneyFieldAdd);
		cityResource.setMoneyOfficerAdd(moneyOfficerAdd);
		cityResource.setMoneyTreasureAdd(moneyTreasureAdd);
		cityResource.setMoneyConsume(moneyConsume);
		
		cityResource.setWoodGuildAdd(1);
		cityResource.setOilGuildAdd(1);
		cityResource.setSteelGuildAdd(1);
		cityResource.setFoodGuildAdd(1);
		cityResource.setMoneyGuildAdd(1);
		
		cityResource.setOilConsume(1L);

		//测试创建
		Integer cityID = cityDAO.createCity(city);
		assertNotNull(cityID);
		
		cityResource.setCityID(cityID);
		cityResourceDAO.createCityResource(cityResource);
		
		

		//测试通过编号获得对象
		City destCity = cityDAO.getCityByID(cityID);
		CityResource destCityResource = cityResourceDAO.getCityResourceByCityID(cityID);
		assertNotNull(destCity);
		assertEquals(cityID,destCity.getCityID());
		assertEquals(playerID,destCity.getPlayerID());
		assertEquals(posX,destCity.getPosX());
		assertEquals(posY,destCity.getPosY());
		assertEquals(name,destCity.getName());
		assertEquals(state,destCity.getState());
		assertEquals(constructionPoint,destCity.getConstructionPoint());
		assertEquals(technologyPoint,destCity.getTechnologyPoint());
		assertEquals(populationTotal,destCity.getPopulationTotal());
		assertEquals(populationFree,destCity.getPopulationFree());
		assertEquals(populationMax,destCity.getPopulationMax());
		assertEquals(recruitNum,destCity.getRecruitNum());
		assertEquals(tax,destCity.getTax());
		assertEquals(security,destCity.getSecurity());
		// assertEquals(officer,destCity.getOfficer());
		assertEquals(businessmanFree,destCity.getBusinessmanFree());
		assertEquals(woodNum,destCityResource.getWoodNum());
		assertEquals(woodNumMax,destCityResource.getWoodNumMax());
		assertEquals(woodOutput,destCityResource.getWoodOutput());
		assertEquals(woodWorkerNum,destCityResource.getWoodWorkerNum());
		assertEquals(woodTechAdd,destCityResource.getWoodTechAdd());
		assertEquals(woodFieldAdd,destCityResource.getWoodFieldAdd());
		assertEquals(woodOfficerAdd,destCityResource.getWoodOfficerAdd());
		assertEquals(woodTreasureAdd,destCityResource.getWoodTreasureAdd());
		assertEquals(steelNum,destCityResource.getSteelNum());
		assertEquals(steelNumMax,destCityResource.getSteelNumMax());
		assertEquals(steelOutput,destCityResource.getSteelOutput());
		assertEquals(steelWorkerNum,destCityResource.getSteelWorkerNum());
		assertEquals(steelTechAdd,destCityResource.getSteelTechAdd());
		assertEquals(steelFieldAdd,destCityResource.getSteelFieldAdd());
		assertEquals(steelOfficerAdd,destCityResource.getSteelOfficerAdd());
		assertEquals(steelTreasureAdd,destCityResource.getSteelTreasureAdd());
		assertEquals(oilNum,destCityResource.getOilNum());
		assertEquals(oilNumMax,destCityResource.getOilNumMax());
		assertEquals(oilOutput,destCityResource.getOilOutput());
		assertEquals(oilWorkerNum,destCityResource.getOilWorkerNum());
		assertEquals(oilTechAdd,destCityResource.getOilTechAdd());
		assertEquals(oilFieldAdd,destCityResource.getOilFieldAdd());
		assertEquals(oilOfficerAdd,destCityResource.getOilOfficerAdd());
		assertEquals(oilTreasureAdd,destCityResource.getOilTreasureAdd());
		assertEquals(foodNum,destCityResource.getFoodNum());
		assertEquals(foodNumMax,destCityResource.getFoodNumMax());
		assertEquals(foodOutput,destCityResource.getFoodOutput());
		assertEquals(foodWorkerNum,destCityResource.getFoodWorkerNum());
		assertEquals(foodTechAdd,destCityResource.getFoodTechAdd());
		assertEquals(foodFieldAdd,destCityResource.getFoodFieldAdd());
		assertEquals(foodOfficerAdd,destCityResource.getFoodOfficerAdd());
		assertEquals(foodTreasureAdd,destCityResource.getFoodTreasureAdd());
		assertEquals(foodConsume,destCityResource.getFoodConsume());
		assertEquals(moneyNum,destCityResource.getMoneyNum());
		assertEquals(moneyNumMax,destCityResource.getMoneyNumMax());
		assertEquals(moneyOutput,destCityResource.getMoneyOutput());
		assertEquals(moneyTechAdd,destCityResource.getMoneyTechAdd());
		assertEquals(moneyFieldAdd,destCityResource.getMoneyFieldAdd());
		assertEquals(moneyOfficerAdd,destCityResource.getMoneyOfficerAdd());
		assertEquals(moneyTreasureAdd,destCityResource.getMoneyTreasureAdd());
		assertEquals(moneyConsume,destCityResource.getMoneyConsume());
		
		//测试获得列表
		List<City> cityList = cityDAO.getCityList();
		assertFalse(cityList.isEmpty());

		//测试更新
		posX = 10;
		posY = 10;
		name = "字符串修改";
		state = 10;
		constructionPoint = 10L;
		technologyPoint = 10L;
		populationTotal = 10L;
		populationFree = 10L;
		populationMax = 10L;
		recruitNum = 10;
		tax = 10;
		security = 10;
		officer = 10;
		businessmanFree = 10;
		woodNum = 10L;
		woodNumMax = 10L;
		woodOutput = 10L;
		woodWorkerNum = 10;
		woodTechAdd = 10;
		woodFieldAdd = 10;
		woodOfficerAdd = 10;
		woodTreasureAdd = 10;
		steelNum = 10L;
		steelNumMax = 10L;
		steelOutput = 10L;
		steelWorkerNum = 10;
		steelTechAdd = 10;
		steelFieldAdd = 10;
		steelOfficerAdd = 10;
		steelTreasureAdd = 10;
		oilNum = 10L;
		oilNumMax = 10L;
		oilOutput = 10L;
		oilWorkerNum = 10;
		oilTechAdd = 10;
		oilFieldAdd = 10;
		oilOfficerAdd = 10;
		oilTreasureAdd = 10;
		foodNum = 10L;
		foodNumMax = 10L;
		foodOutput = 10L;
		foodWorkerNum = 10;
		foodTechAdd = 10;
		foodFieldAdd = 10;
		foodOfficerAdd = 10;
		foodTreasureAdd = 10;
		foodConsume = 10L;
		moneyNum = 10L;
		moneyNumMax = 10L;
		moneyOutput = 10L;
		moneyTechAdd = 10;
		moneyFieldAdd = 10;
		moneyOfficerAdd = 10;
		moneyTreasureAdd = 10;
		moneyConsume = 10L;
		destCity.setCityID(cityID);
		destCity.setPlayerID(playerID);
		destCity.setPosX(posX);
		destCity.setPosY(posY);
		destCity.setName(name);
		destCity.setState(state);
		destCity.setConstructionPoint(constructionPoint);
		destCity.setTechnologyPoint(technologyPoint);
		destCity.setPopulationTotal(populationTotal);
		destCity.setPopulationFree(populationFree);
		destCity.setPopulationMax(populationMax);
		destCity.setRecruitNum(recruitNum);
		destCity.setTax(tax);
		destCity.setSecurity(security);
		destCity.setOfficer(officer);
		destCity.setBusinessmanFree(businessmanFree);
		destCityResource.setWoodNum(woodNum);
		destCityResource.setWoodNumMax(woodNumMax);
		destCityResource.setWoodOutput(woodOutput);
		destCityResource.setWoodWorkerNum(woodWorkerNum);
		destCityResource.setWoodTechAdd(woodTechAdd);
		destCityResource.setWoodFieldAdd(woodFieldAdd);
		destCityResource.setWoodOfficerAdd(woodOfficerAdd);
		destCityResource.setWoodTreasureAdd(woodTreasureAdd);
		destCityResource.setSteelNum(steelNum);
		destCityResource.setSteelNumMax(steelNumMax);
		destCityResource.setSteelOutput(steelOutput);
		destCityResource.setSteelWorkerNum(steelWorkerNum);
		destCityResource.setSteelTechAdd(steelTechAdd);
		destCityResource.setSteelFieldAdd(steelFieldAdd);
		destCityResource.setSteelOfficerAdd(steelOfficerAdd);
		destCityResource.setSteelTreasureAdd(steelTreasureAdd);
		destCityResource.setOilNum(oilNum);
		destCityResource.setOilNumMax(oilNumMax);
		destCityResource.setOilOutput(oilOutput);
		destCityResource.setOilWorkerNum(oilWorkerNum);
		destCityResource.setOilTechAdd(oilTechAdd);
		destCityResource.setOilFieldAdd(oilFieldAdd);
		destCityResource.setOilOfficerAdd(oilOfficerAdd);
		destCityResource.setOilTreasureAdd(oilTreasureAdd);
		destCityResource.setFoodNum(foodNum);
		destCityResource.setFoodNumMax(foodNumMax);
		destCityResource.setFoodOutput(foodOutput);
		destCityResource.setFoodWorkerNum(foodWorkerNum);
		destCityResource.setFoodTechAdd(foodTechAdd);
		destCityResource.setFoodFieldAdd(foodFieldAdd);
		destCityResource.setFoodOfficerAdd(foodOfficerAdd);
		destCityResource.setFoodTreasureAdd(foodTreasureAdd);
		destCityResource.setFoodConsume(foodConsume);
		destCityResource.setMoneyNum(moneyNum);
		destCityResource.setMoneyNumMax(moneyNumMax);
		destCityResource.setMoneyOutput(moneyOutput);
		destCityResource.setMoneyTechAdd(moneyTechAdd);
		destCityResource.setMoneyFieldAdd(moneyFieldAdd);
		destCityResource.setMoneyOfficerAdd(moneyOfficerAdd);
		destCityResource.setMoneyTreasureAdd(moneyTreasureAdd);
		destCityResource.setMoneyConsume(moneyConsume);
		cityDAO.updateCity(destCity);
		cityResourceDAO.updateCityResource(destCityResource);
		City updatedCity = cityDAO.getCityByID(cityID);
		CityResource updateCityResource = cityResourceDAO.getCityResourceByCityID(cityID);
		assertNotNull(updatedCity);
		assertEquals(cityID,updatedCity.getCityID());
		assertEquals(playerID,updatedCity.getPlayerID());
		assertEquals(posX,updatedCity.getPosX());
		assertEquals(posY,updatedCity.getPosY());
		assertEquals(name,updatedCity.getName());
		assertEquals(state,updatedCity.getState());
		assertEquals(constructionPoint,updatedCity.getConstructionPoint());
		assertEquals(technologyPoint,updatedCity.getTechnologyPoint());
		assertEquals(populationTotal,updatedCity.getPopulationTotal());
		assertEquals(populationFree,updatedCity.getPopulationFree());
		assertEquals(populationMax,updatedCity.getPopulationMax());
		assertEquals(recruitNum,updatedCity.getRecruitNum());
		assertEquals(tax,updatedCity.getTax());
		assertEquals(security,updatedCity.getSecurity());
		assertEquals(officer,updatedCity.getOfficer());
		assertEquals(businessmanFree,updatedCity.getBusinessmanFree());
		assertEquals(woodNum,updateCityResource.getWoodNum());
		assertEquals(woodNumMax,updateCityResource.getWoodNumMax());
		assertEquals(woodOutput,updateCityResource.getWoodOutput());
		assertEquals(woodWorkerNum,updateCityResource.getWoodWorkerNum());
		assertEquals(woodTechAdd,updateCityResource.getWoodTechAdd());
		assertEquals(woodFieldAdd,updateCityResource.getWoodFieldAdd());
		assertEquals(woodOfficerAdd,updateCityResource.getWoodOfficerAdd());
		assertEquals(woodTreasureAdd,updateCityResource.getWoodTreasureAdd());
		assertEquals(steelNum,updateCityResource.getSteelNum());
		assertEquals(steelNumMax,updateCityResource.getSteelNumMax());
		assertEquals(steelOutput,updateCityResource.getSteelOutput());
		assertEquals(steelWorkerNum,updateCityResource.getSteelWorkerNum());
		assertEquals(steelTechAdd,updateCityResource.getSteelTechAdd());
		assertEquals(steelFieldAdd,updateCityResource.getSteelFieldAdd());
		assertEquals(steelOfficerAdd,updateCityResource.getSteelOfficerAdd());
		assertEquals(steelTreasureAdd,updateCityResource.getSteelTreasureAdd());
		assertEquals(oilNum,updateCityResource.getOilNum());
		assertEquals(oilNumMax,updateCityResource.getOilNumMax());
		assertEquals(oilOutput,updateCityResource.getOilOutput());
		assertEquals(oilWorkerNum,updateCityResource.getOilWorkerNum());
		assertEquals(oilTechAdd,updateCityResource.getOilTechAdd());
		assertEquals(oilFieldAdd,updateCityResource.getOilFieldAdd());
		assertEquals(oilOfficerAdd,updateCityResource.getOilOfficerAdd());
		assertEquals(oilTreasureAdd,updateCityResource.getOilTreasureAdd());
		assertEquals(foodNum,updateCityResource.getFoodNum());
		assertEquals(foodNumMax,updateCityResource.getFoodNumMax());
		assertEquals(foodOutput,updateCityResource.getFoodOutput());
		assertEquals(foodWorkerNum,updateCityResource.getFoodWorkerNum());
		assertEquals(foodTechAdd,updateCityResource.getFoodTechAdd());
		assertEquals(foodFieldAdd,updateCityResource.getFoodFieldAdd());
		assertEquals(foodOfficerAdd,updateCityResource.getFoodOfficerAdd());
		assertEquals(foodTreasureAdd,updateCityResource.getFoodTreasureAdd());
		assertEquals(foodConsume,updateCityResource.getFoodConsume());
		assertEquals(moneyNum,updateCityResource.getMoneyNum());
		assertEquals(moneyNumMax,updateCityResource.getMoneyNumMax());
		assertEquals(moneyOutput,updateCityResource.getMoneyOutput());
		assertEquals(moneyTechAdd,updateCityResource.getMoneyTechAdd());
		assertEquals(moneyFieldAdd,updateCityResource.getMoneyFieldAdd());
		assertEquals(moneyOfficerAdd,updateCityResource.getMoneyOfficerAdd());
		assertEquals(moneyTreasureAdd,updateCityResource.getMoneyTreasureAdd());
		assertEquals(moneyConsume,updateCityResource.getMoneyConsume());

		//测试删除
		cityResourceDAO.deleteCityResourceByCityID(cityID);
		cityDAO.deleteCityByID(cityID);
		assertNull(cityDAO.getCityByID(cityID));*/

	}
	
	// @Test
	public void testUpdateSecurityOfResourceEffect(){
		cityDAO.updateSecurityOfResourceEffect();
	}
	
	@Test
	public void testGetCityListOfResourceZero(){
		assertTrue(cityResourceDAO.getCityListOfResourceZero().size()>0);
	}

}