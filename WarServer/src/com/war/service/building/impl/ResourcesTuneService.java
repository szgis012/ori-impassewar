package com.war.service.building.impl;

import java.util.HashMap;

import com.war.constant.BuildingConstant;
import com.war.dao.ICityBuildingDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.building.IResourcesTuneService;
import com.war.util.ResourceCalculateUtil;

/**
 * 调整资源生产Service实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class ResourcesTuneService implements IResourcesTuneService {
	
	private ICityService cityService;
	
	private ICityBuildingDAO cityBuildingDAO;
	
	public void modifyWoodWorkerNum(Integer cityID, Integer workerNum) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("操作失败。");
		
		if(city.getPopulationFree()+cityResource.getWoodWorkerNum()<workerNum){
			throw new GameException("城市空闲人口不足。");
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		
		//重新计算没有工作的人数
		long populationFree = city.getPopulationFree() - (workerNum - cityResource.getWoodWorkerNum());
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("woodWorkerNum", workerNum);
		
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(), workerNum, cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
		cityResourceParams.put("woodOutput", woodOutput);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+workerNum+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree , 
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}

	public void modifySteelWorkerNum(Integer cityID, Integer workerNum) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("操作失败。");
		
		if(city.getPopulationFree()+cityResource.getSteelWorkerNum()<workerNum){
			throw new GameException("城市空闲人口不足。");
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		//重新计算没有工作的人数
		long populationFree = city.getPopulationFree() - (workerNum - cityResource.getSteelWorkerNum());
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("steelWorkerNum", workerNum);
		
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),workerNum, cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
		cityResourceParams.put("steelOutput", steelOutput);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+workerNum,populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);

	}

	public void modifyOilWorkerNum(Integer cityID, Integer workerNum) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("操作失败。");
		
		if(city.getPopulationFree()+cityResource.getOilWorkerNum()<workerNum){
			throw new GameException("城市空闲人口不足。");
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		//重新计算没有工作的人数
		long populationFree = city.getPopulationFree() - (workerNum - cityResource.getOilWorkerNum());
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("oilWorkerNum", workerNum);
		
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(), workerNum, cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), 
				cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
		cityResourceParams.put("oilOutput", oilOutput);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+workerNum+cityResource.getSteelWorkerNum(),populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);

	}
	
	public void modifyFoodWorkerNum(Integer cityID, Integer workerNum) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("操作失败。");
		
		if(city.getPopulationFree()+cityResource.getFoodWorkerNum()<workerNum){
			throw new GameException("城市空闲人口不足。");
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		
		//重新计算没有工作的人数
		long populationFree = city.getPopulationFree() - (workerNum - cityResource.getFoodWorkerNum());
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("foodWorkerNum", workerNum);
		
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),workerNum, cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(),  cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
		cityResourceParams.put("foodOutput", foodOutput);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(workerNum+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree , 
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);

	}
	
	public void modifyResourcesWorkerNum(Integer cityID, Integer woodWorkerNum,
			Integer steelWorkerNum, Integer oilWorkerNum, Integer foodWorkerNum) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("操作失败。");
		
		if(woodWorkerNum < 0 || steelWorkerNum < 0 || oilWorkerNum < 0 || foodWorkerNum < 0)
			throw new GameException("资源生产的工作人数不能为负数。");
		
		//计算调整后的空闲人口
		long populationFree = (city.getPopulationFree() + cityResource.getWoodWorkerNum() + cityResource.getSteelWorkerNum() + cityResource.getOilWorkerNum() + cityResource.getFoodWorkerNum())
			- (woodWorkerNum + steelWorkerNum + oilWorkerNum + foodWorkerNum);
		
		if(populationFree < 0)
			throw new GameException("资源生产的总工作人数超过了可用的空闲人口数量。");
		
		if(woodWorkerNum>0 && cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, BuildingConstant.LUMBER_MILL)==null){
			throw new GameException("城市还未修建伐木场，无法派遣市民进行资源生产。");
		}
		if(steelWorkerNum>0 && cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, BuildingConstant.STEEL_PLANT)==null){
			throw new GameException("城市还未修建炼钢厂，无法派遣市民进行资源生产。");
		}
		if(oilWorkerNum>0 && cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, BuildingConstant.OIL_WELL)==null){
			throw new GameException("城市还未修建油井，无法派遣市民进行资源生产。");
		}
		if(foodWorkerNum>0 && cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, BuildingConstant.FARM)==null){
			throw new GameException("城市还未修建农场，无法派遣市民进行资源生产。");
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("woodWorkerNum", woodWorkerNum);
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(), woodWorkerNum, cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
		cityResourceParams.put("woodOutput", woodOutput);
		cityResourceParams.put("steelWorkerNum", steelWorkerNum);
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),steelWorkerNum, cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
		cityResourceParams.put("steelOutput", steelOutput);
		cityResourceParams.put("oilWorkerNum", oilWorkerNum);
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(), oilWorkerNum, cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
		cityResourceParams.put("oilOutput", oilOutput);
		cityResourceParams.put("foodWorkerNum", foodWorkerNum);
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),foodWorkerNum, cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
		cityResourceParams.put("foodOutput", foodOutput);

		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(foodWorkerNum+woodWorkerNum+steelWorkerNum+oilWorkerNum,populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}	


	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}

}
