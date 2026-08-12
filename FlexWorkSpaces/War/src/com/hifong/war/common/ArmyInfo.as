package com.hifong.war.common
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.events.common.GetArmyListEvent;
	import com.hifong.war.events.common.GetCityArmyListEvent;
	
	import mx.collections.ArrayCollection;
	
	/**
	 * 军队信息
	 * 
	 */ 
	[Bindable]
	public class ArmyInfo
	{
		/**所有兵种信息,列表中的元素为ArmyVO*/ 
		public var armyList:ArrayCollection;
		/** *所有兵种信息Map,key=兵种编号,value=ArmyVO*/
		public var armyMap:Object;
		
		/** 士兵*/
		public var soldierList:ArrayCollection;
		/** 车辆*/
		public var vehicleList:ArrayCollection ;
		/** 飞机*/
		public var planeList:ArrayCollection;
		
		/** 城市拥有的军队列表,列表中的元素为CityArmyVO */
		public var cityArmyList:ArrayCollection;
		/** 未编制的城内军队,key=armyID,value=CityArmyVO*/
		public var nosetArmyMap:Object ;
		
		public function ArmyInfo()
		{
//			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
//			dispatcher.dispatchEvent(new GetArmyListEvent());
//			dispatcher.dispatchEvent(new GetCityArmyListEvent());
		}

	}
}