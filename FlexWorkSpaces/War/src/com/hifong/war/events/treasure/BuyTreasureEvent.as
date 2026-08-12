/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.TreasureItemVO;

	/**
	 * 购买宝物事件
	 * 
	 */ 
	public final class BuyTreasureEvent extends CairngormEvent
	{
		public static const BUYTREASURE_EVENT:String = "com.hifong.war.events.BuyTreasureEvent";
		/** 宝物编号*/
		public var treasureItem:TreasureItemVO;
		/** 购买数量*/
		public var num:int;

		public function BuyTreasureEvent(treasureItem:TreasureItemVO,num:int) 
		{
			super( BUYTREASURE_EVENT );
			this.treasureItem = treasureItem;
			this.num = num;
		}
	}
}
