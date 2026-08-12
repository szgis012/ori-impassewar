package com.hifong.war.util
{
	import com.hifong.war.constant.CityHeroStateConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.OrdnanceVO;
	
	/**
	 * 提供系统内各类数据的转换
	 * (注：该类转换依赖系统缓存的数据，并不和服务器进行交互。)
	 * 如：从编号到名称
	 */ 
	public class ConvertUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 获得指定编号的军械名称
		 * 
		 */ 
		public static function getOrdnanceNameByID(ordnanceID:int):String{
			var ordnance:OrdnanceVO = model.ordnanceInfo.ordnanceMap[ordnanceID] as OrdnanceVO;
		
			if(ordnance){
				return ordnance.name;
			}else{
				return "";
			}
		}
		
		/**
		 * 获得城市英雄状态名
		 * @param state 在CityHeroStateConstant中定义
		 */ 
		public static function getCityHeroStateName(state:int):String{
			switch(state){
				case CityHeroStateConstant.DEPOY:
					return "出征";
				case CityHeroStateConstant.FREE:
					return "空闲";
				case CityHeroStateConstant.ORGANIZATION:
					return "编制";		
				case CityHeroStateConstant.REIGN:
					return "执政";	
				case CityHeroStateConstant.RESIDE:
					return "驻扎";		
				default:
					return "未知";		
			}
		}
		
		//获得兵种名称
		public static function getArmyName(armyID:int):String{
			return model.armyInfo.armyMap[armyID].name;
		}
		
	}
}