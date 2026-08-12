package com.war.util;

import java.util.HashMap;
import java.util.Map;

import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.domain.ConstraintDepend;

/**
 * 资源操作工具类
 *
 * @author ghleed
 * @version 1.0
 */
public class ConstraintDependUtil {
	/**
	 * 检查城市有没有约束给定的资源(包括空闲人口，食物，石油，木材，钢材，金钱)
	 * @param city
	 * @param constraintDepend
	 * @return 
	 */
	public static boolean hasEnoughResources(City city, CityResource cityResource, ConstraintDepend constraintDepend){
		if(city.getPopulationFree() < constraintDepend.getCostPopulation() ){
			return false;
		}
		
		if(cityResource.getFoodNum() < constraintDepend.getCostFood()){
			return false;
		}
		
		if(cityResource.getOilNum() < constraintDepend.getCostOil()){
			return false;
		}
		
		if(cityResource.getWoodNum() < constraintDepend.getCostWood()){
			return false;
		}
		
		if(cityResource.getSteelNum() < constraintDepend.getCostSteel()){
			return false;
		}
		
		if(cityResource.getMoneyNum() < constraintDepend.getCostMoney()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 	获得资源减少的更新参数(用于建筑或生产等操作)
	 *  构造CityService.updateCity(Map<String, Object> params)方法使用的参数
	 *  (此方法未做资源检查，所以请在资源检查后调用该方法。)
	 * @param cityResource
	 * @param constraintDepend
	 * @return
	 */
	public static Map<String,Object> getDecreaseResourceParams(CityResource cityResource,ConstraintDepend constraintDepend){
		return getDecreaseResourceParams(cityResource,constraintDepend,1);
	}
	
	/**
	 *  获得资源减少的更新参数(用于建筑或生产等操作)
	 *  构造CityService.updateCity(Map<String, Object> params)方法使用的参数
	 *  (此方法未做资源检查，所以请在资源检查后调用该方法。)
	 * @param city
	 * @param constraintDepend
	 * @param num 建造(或生产)数量
	 * @return
	 */
	public static Map<String,Object> getDecreaseResourceParams(CityResource cityResource,ConstraintDepend constraintDepend,int num){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityID", cityResource.getCityID());
		params.put("woodNum", Math.max(cityResource.getWoodNum() - constraintDepend.getCostWood() * num ,0));
		params.put("steelNum",Math.max(cityResource.getSteelNum() - constraintDepend.getCostSteel() * num ,0));
		params.put("oilNum", Math.max(cityResource.getOilNum() - constraintDepend.getCostOil() * num ,0));
		params.put("foodNum", Math.max(cityResource.getFoodNum() - constraintDepend.getCostFood() * num ,0));
		params.put("moneyNum", Math.max(cityResource.getMoneyNum() - constraintDepend.getCostMoney() * num ,0));
		
		return params;
	}
	
	/**
	 *  获得资源加半的更新参数(用于建筑或生产过程中取消等操作)
	 *  构造CityService.updateCity(Map<String, Object> params)方法使用的参数
	 *  (此方法未做资源检查，所以请在资源检查后调用该方法。)
	 * @param cityResource
	 * @param constraintDepend
	 * @return
	 */
	public static Map<String,Object> getIncreaseHalfResourceParams(CityResource cityResource,ConstraintDepend constraintDepend){
		return getIncreaseHalfResourceParams(cityResource,constraintDepend,1);
	}
	
	/**
	 *  获得资源加半的更新参数(用于建筑或生产过程中取消等操作)
	 *  构造CityService.updateCity(Map<String, Object> params)方法使用的参数
	 *  (此方法未做资源检查，所以请在资源检查后调用该方法。)
	 * @param city
	 * @param constraintDepend
	 * @param num 建造(或生产)数量
	 * @return
	 */
	public static Map<String,Object> getIncreaseHalfResourceParams(CityResource cityResource,ConstraintDepend constraintDepend,int num){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityID", cityResource.getCityID());
		params.put("woodNum", Math.min(cityResource.getWoodNum() + constraintDepend.getCostWood() * num / 2 ,cityResource.getResourceNumMax()));
		params.put("steelNum",Math.min(cityResource.getSteelNum() + constraintDepend.getCostSteel() * num / 2 ,cityResource.getResourceNumMax()));
		params.put("oilNum", Math.min(cityResource.getOilNum() + constraintDepend.getCostOil() * num / 2 ,cityResource.getResourceNumMax()));
		params.put("foodNum", Math.min(cityResource.getFoodNum() + constraintDepend.getCostFood() * num / 2 ,cityResource.getResourceNumMax()));
		params.put("moneyNum", cityResource.getMoneyNum() + constraintDepend.getCostMoney() * num / 2 );
		
		return params;
	}
	 
}
