/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得指定分类下的所有宝物列表事件
	 * 
	 */ 
	public final class GetTreasureListByCategoryEvent extends CairngormEvent
	{

		public static const GETTREASURELISTBYCATEGORY_EVENT:String = "com.hifong.war.events.GetTreasureListByCategoryEvent";

		/** 宝物分类，TreasureCategoryConstant中定义 */
		public var category:int;
		
		
		public function GetTreasureListByCategoryEvent(category:int) 
		{
			super( GETTREASURELISTBYCATEGORY_EVENT );
			this.category = category;
		}
	}
}
