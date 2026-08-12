package com.hifong.war.util
{
	import com.hifong.war.constant.GlobalConstant;
	import com.hifong.war.model.ModelLocator;
	
	/**
	 * 图片路径工具类
	 * 
	 */ 
	public class ImagePathUtil
	{
		private static var model:ModelLocator = ModelLocator.getInstance();
		
		/**
		 * 得到建造兵种实际的图片路径
		 * 
		 */ 
		public static function getBuildArmyPath(image:String):String{
			if(image){
				//分离文件名和后缀
				var arr:Array = image.split(".");
				
				if(arr.length == 2){
					return GlobalConstant.BUILD_ARMY_DIR_PREFIX + arr[0] + "_" + model.playerInfo.country + "." + arr[1];
				}
				
			}
			
			return null;
		}
		
		/**
		 * 得到宝物实际的图片路径
		 * 
		 */ 
		public static function getTreasurePath(image:String):String{
			if(image){
				return GlobalConstant.TREASURE_DIR_PREFIX + image;
			}
			
			return null;
		}
		
		/**
		 * 得到城市建筑的实际路径
		 * 
		 */ 
//		public static function getBuildingPath(image:String):String{
//			if(image){
//				return GlobalConstant.BUILDING_DIR_PREFIX + model.playerInfo.country + "/" + image;
//			}
//			
//			return null;
//		}

		public static function getStrongholdBuildingPath(image:String):String{
			if(image){
					return GlobalConstant.STRONGHOLD_DIR_PREFIX + model.playerInfo.country + "/" + image;
			}
			
			return null;
		}


	}
}