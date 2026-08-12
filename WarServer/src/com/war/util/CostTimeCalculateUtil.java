package com.war.util;

/**
 * 花费时间计算
 * 
 * @author TopTong
 * @version 1.0
 */

public class CostTimeCalculateUtil {

	public static long calculateBusinessmanCostTime(int cityPosX,int cityPosY,int targetCityPosX,int targetCityPosY){
		long costTime = (long)Math.sqrt(Math.pow(cityPosX-targetCityPosX, 2)+Math.pow(cityPosY-targetCityPosY, 2)) * 60;
		return costTime;
	}
	
	public static long calculateMilitaryCostTime(int posX,int posY,int targetPosX,int targetPosY,int speed){
		long costTime = (long)Math.ceil(Math.sqrt(Math.pow(posX-targetPosX, 2)+Math.pow(posY-targetPosY, 2)) * 150 / speed);
		return costTime;
	}
	
}
