/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.TreasureItemVO;

    /**
     * 使用宝物时使用的事件类
     *
     */
	public final class UseTreasureEvent extends CairngormEvent
	{
		/**
		 * 要使用的宝物
		 * 
		 */ 
		public var treausreItem:TreasureItemVO;
		/** 传递为宝物脚本的参数 */
		public var params:Object;
		
		public static const USETREASURE_EVENT:String = "com.hifong.war.events.UseTreasureEvent";
		
		
		public function UseTreasureEvent(treasure:TreasureItemVO,params:Object) 
		{
			super( USETREASURE_EVENT );
			this.treausreItem = treasure;
			this.params = params;
		}
	}
}
