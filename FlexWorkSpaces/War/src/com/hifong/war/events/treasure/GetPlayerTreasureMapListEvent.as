/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 得到玩家所有属于给定分类的宝物信息(包含宝物的信息)事件 
	 * 
	 */ 
	public final class GetPlayerTreasureMapListEvent extends CairngormEvent
	{

		public static const GETPLAYERTREASUREMAPLIST_EVENT:String = "com.hifong.war.events.GetPlayerTreasureMapListEvent";

		/** 宝物分类，TreasureCategoryConstant中定义 */
		public var category:int;
		
			
		public function GetPlayerTreasureMapListEvent(category:int) 
		{
			super( GETPLAYERTREASUREMAPLIST_EVENT );
			this.category = category;
		}
	}
}
