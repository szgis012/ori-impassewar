package com.hifong.war.common
{
	import mx.collections.ArrayCollection;
	
	/**
	 * 建筑相关信息 
	 * 
	 */ 
	public class BuildingInfo
	{
		/**
		 * 所有建筑列表,其中元素为BuildingVO
		 */  
		public var buildingList:ArrayCollection;
		/** 城防建筑 列表  其中元素 为 DefenseVO */
		public var defenseList:ArrayCollection;
		/**
		 * 所有建筑的map,方便通过建筑编号获得对应的建筑信息 
		 * key为建筑编号
		 * value为BuildingVO
		 */ 
		public var buildingMap:Object;
		/** 装载城防的对象 */
		public var defenseMap:Object;
		
		public function BuildingInfo()
		{
//			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
//			
//			//获得所有建筑信息列表
//			dispatcher.dispatchEvent(new GetBuildingListEvent());	
		}

	}
}