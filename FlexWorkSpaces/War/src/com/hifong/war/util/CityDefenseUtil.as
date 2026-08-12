package com.hifong.war.util
{
	import com.hifong.war.constant.CityDefenseTypeConstant;
	import com.hifong.war.constant.DefenseConstant;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.vo.CityDefenseVO;
	import com.hifong.war.vo.DefenseVO;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 城防使用的工具类
	 */ 
	public class CityDefenseUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		
		/**
		 * 得到城防类型对应的建筑信息
		 * type:城防类型(CityDefenseTypeConstant中定义)
		 */ 
		public static function getDefenseBuilding(type:int):DefenseVO{
			switch(type){
				case CityDefenseTypeConstant.ANTIGUN:
					return model.buildingInfo.defenseMap[DefenseConstant.ANTIGUN];
				case CityDefenseTypeConstant.GUN:
					return model.buildingInfo.defenseMap[DefenseConstant.GUN];
				case CityDefenseTypeConstant.BUNKER:
					return model.buildingInfo.defenseMap[DefenseConstant.BUNKER];
				case CityDefenseTypeConstant.FENCE:
					return model.buildingInfo.defenseMap[DefenseConstant.FENCE];
			}
			
			return null;
		}
		
		/**
		 * 获得防御类型对应的建筑编号
		 */ 
		public static function toBuildingID(defenseType:int):int{
			switch(defenseType){
				case CityDefenseTypeConstant.ANTIGUN:
					return DefenseConstant.ANTIGUN;
				case CityDefenseTypeConstant.BUNKER:
					return DefenseConstant.BUNKER;
				case CityDefenseTypeConstant.FENCE:
					return DefenseConstant.FENCE;
				case CityDefenseTypeConstant.GUN:
					return DefenseConstant.GUN;			
			}
			
			return -1;
		}
		
		/**
		 * 获得防御编号对应的防御类型
		 */ 
		public static function toDefenseType(buildingID:int):int{
			switch(buildingID){
				case DefenseConstant.ANTIGUN:
					return CityDefenseTypeConstant.ANTIGUN;
				case DefenseConstant.BUNKER:
					return CityDefenseTypeConstant.BUNKER;
				case DefenseConstant.FENCE:
					return CityDefenseTypeConstant.FENCE;
				case DefenseConstant.GUN:
					return CityDefenseTypeConstant.GUN;			
			}
			
			return -1;
		}
		
		/**
		 * 判断建筑是否为城防建筑
		 * buildingID为建筑编号
		 */ 
		public static function isCityDefense(buildingID:int):Boolean{
			switch(buildingID){
				case DefenseConstant.ANTIGUN:
				case DefenseConstant.BUNKER:
				case DefenseConstant.FENCE:
				case DefenseConstant.GUN:
					return true;
				default:
					return false;	
			}
		}
		
		/**
		 * 获得指定防御的数量
		 * 
		 */ 
		public static function getDefenseCount(buildingID:int):int{
			var count:int = 0;
			
			var cd:CityDefenseVO = model.cityDefenseInfo.cityDefenseMap[toDefenseType(buildingID)] as CityDefenseVO;
			
			if(cd){
				count = cd.num;
			}
			
			return count;
		}
		
		/**
		 * 将城防列表转换成map形式，其中key为防御类型，value为该类型的城防信息
		 * 注意：该方法会改写cityDefenseList
		 */ 
		public  static function getCityDefenseMap(cityDefenseList:ArrayCollection):Object{
				var cd:CityDefenseVO;
				
				var cdmap:Object = {};
				//放入map
				for each (cd in cityDefenseList){
					cdmap[cd.defenseID] = cd;
				}
				
				//为了程序处理的方便把未建造的防御也加入列表
				for(var i:int=1;i<=4;i++){
					cd = cdmap[i] as CityDefenseVO;
					if(!cd){
						cd = new CityDefenseVO();
						cd.cityID = model.cityInfo.cityID;
						cd.defenseID = i;
						cd.num = 0;
						//列表和map中各放一份
						cityDefenseList.addItem(cd);
						cdmap[i] = cd;
					}
				}
				
				return cdmap;
		}
	}
	
}