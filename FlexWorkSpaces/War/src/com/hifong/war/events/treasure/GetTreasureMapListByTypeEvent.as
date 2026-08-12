/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 得到指定类型的宝物列表(包含玩家拥有该宝物数量的信息)事件
	 * 
	 */ 
	public final class GetTreasureMapListByTypeEvent extends CairngormEvent
	{

		public static const GETTREASUREMAPLISTBYTYPE_EVENT:String = "com.hifong.war.events.GetTreasureMapListByTypeEvent";

		/** 宝物分类，TreasureCategoryConstant中定义 */
		public var category:int;
		/** 宝物类型，TreasureTypeConstant中定义 */
		public var treasureType:int;
		
		public function GetTreasureMapListByTypeEvent(category:int,treasureType:int) 
		{
			super( GETTREASUREMAPLISTBYTYPE_EVENT );
			this.category = category;
			this.treasureType = treasureType;
		}
	}
}
