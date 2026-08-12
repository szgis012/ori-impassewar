package com.war.util;

/**
 * 军队工具类
 *
 * @author ghleed
 * @version 1.0
 */
public class ArmyUtil {
	
	/**
	 * 获得数量对应的兵力描述信息
	 * @param armyNum 兵的数量
	 * @return
	 */
	public static String getArmyForceDescription(long armyNum){
		if(armyNum <= 10){
			return "排(1-10)";
		}else if(armyNum <= 50){
			return "连(10-50)";
		}else if(armyNum <= 100){
			return "营(50-100)";
		}else if(armyNum <= 300){
			return "团(100-300)";
		}else if(armyNum <= 1000){
			return "旅(300-1000)";
		}else if(armyNum <= 3000){
			return "师(1000-3000)";
		}else if(armyNum <= 10000){
			return "军(3000-10000)";
		}else if(armyNum <= 30000){
			return "集团军(10000-30000)";
		}else {
			return "集团军群(30000以上)";
		}
	}
	
	/**
	 * 获得数量对应的城防数量描述信息
	 * @param defenseNum 城防的数量
	 * @return
	 */
	public static String getDefenseForceDescription(long defenseNum){
		if(defenseNum <= 10){
			return "极少(1-10)";
		}else if(defenseNum <= 50){
			return "少量(10-50)";
		}else if(defenseNum <= 100){
			return "较少(50-100)";
		}else if(defenseNum <= 300){
			return "中等(100-300)";
		}else if(defenseNum <= 1000){
			return "较多(300-1000)";
		}else if(defenseNum <= 3000){
			return "大量(1000-3000)";
		}else if(defenseNum <= 10000){
			return "极多(3000-10000)";
		}else if(defenseNum <= 30000){
			return "海量(10000-30000)";
		}else {
			return "无数(30000以上)";
		}
	}
}
