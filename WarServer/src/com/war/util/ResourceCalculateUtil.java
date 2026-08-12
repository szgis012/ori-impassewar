package com.war.util;

import com.war.common.GameConfig;
import com.war.constant.CityConstant;

public class ResourceCalculateUtil {

	// 计算木材产出
	public static long calculateWoodOutput(Integer tax, Integer workerNum, Integer buildingAdd, Integer techAdd, Integer officerAdd, Integer guildAdd, Integer fieldAdd, Integer treasureAdd) {
		return (GameConfig.WOOD_OUTPUT_PER_WORKER
				* workerNum
				* (100 + buildingAdd + techAdd + officerAdd + guildAdd + fieldAdd + treasureAdd)
				/ 100) * (100 - tax) / 100 + CityConstant.BASE_WOOD_OUTPUT;
	}

	// 计算钢铁产出
	public static long calculateSteelOutput(Integer tax,Integer workerNum, Integer buildingAdd, Integer techAdd, Integer officerAdd, Integer guildAdd, Integer fieldAdd, Integer treasureAdd) {
		return (GameConfig.STEEL_OUTPUT_PER_WORKER
				* workerNum
				* (100 + buildingAdd + techAdd + officerAdd + guildAdd + fieldAdd + treasureAdd)
				/ 100) * (100 - tax) / 100 + CityConstant.BASE_STEEL_OUTPUT;
	}

	// 计算石油产出
	public static long calculateOilOutput(Integer tax,Integer workerNum, Integer buildingAdd, Integer techAdd, Integer officerAdd, Integer guildAdd, Integer fieldAdd, Integer treasureAdd) {
		return (GameConfig.OIL_OUTPUT_PER_WORKER
				* workerNum
				* (100 + buildingAdd + techAdd + officerAdd + guildAdd + fieldAdd + treasureAdd)
				/ 100) * (100 - tax) / 100 + CityConstant.BASE_OIL_OUTPUT;
	}

	// 计算食物产出
	public static long calculateFoodOutput(Integer tax,Integer workerNum, Integer buildingAdd, Integer techAdd, Integer officerAdd, Integer guildAdd, Integer fieldAdd, Integer treasureAdd) {
		return (GameConfig.FOOD_OUTPUT_PER_WORKER
				* workerNum
				* (100 + buildingAdd + techAdd + officerAdd + guildAdd + fieldAdd + treasureAdd)
				/ 100) * (100 - tax) / 100 + CityConstant.BASE_FOOD_OUTPUT;
	}
	
	/**
	 * 计算金钱产出
	 * @param workerNum 工作人口(四种资源工作人数之和)
	 * @param freeManNum 闲人数量
	 * @param tax 税收
	 * @param techAdd 科技加成
	 * @param officerAdd 指挥官加成
	 * @param guildAdd 军团加成
	 * @param fieldAdd 野地加成
	 * @param treasureAdd 宝物加成
	 * @return
	 */
	public static long calculateMoneyOutput(Integer workerNum, Long freeManNum, Integer tax, Integer techAdd, Integer officerAdd, Integer guildAdd, Integer fieldAdd, Integer treasureAdd) {
		return (long)((GameConfig.MONEY_OUTPUT_PER_WORKER * workerNum + GameConfig.MONEY_OUTPUT_PER_FREEMAN * freeManNum) * tax)
		* (100 + techAdd + officerAdd + guildAdd + fieldAdd + treasureAdd) / 100 + CityConstant.BASE_MONEY_OUTPUT;
	}
	
	/**
	 * 计算仓库的容量
	 * @param level 仓库等级
	 * @param addRates addRates 加成比例数量（加成数值可以按任意顺序填入）
	 * @return 加成之后的仓库容量
	 */
	public static long calculateStorageCapacity(int level, int[] addRates ){
		
		long baseCapacity =  level * level * 8500;
		
		if (addRates == null || addRates.length == 0) {
			return baseCapacity;
		}
		
		int sumRate = 100;
		for (int addRate : addRates) {
			sumRate += addRate;
		}
		
		return baseCapacity * sumRate / 100;
	}

}
