package com.hifong.war.util
{
	/**
	 * 依赖
	 */ 
	public class DependUtil
	{
		/**
		 * 获得依赖的前提建筑描述
		 * 多个建筑以空格分隔
		 */ 
		public static function getPreBuildingName(constraintDepend:Object):String{
			if(constraintDepend && constraintDepend.preBuildingList){
				var prebuilding:String = "";
				var depend:Object;
				
				for(var i:int=0;i<constraintDepend.preBuildingList.length;i++){
					depend = constraintDepend.preBuildingList.getItemAt(i);
					prebuilding += depend.level + "级" + depend.buildingName +" ";
				}
				
				return prebuilding;
			}else{
				return "无";
			}
		}
	}
}