/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 侦察事件
     */
	public final class SpyEvent extends CairngormEvent
	{
		/** 派遣的侦察兵数量*/
		public var num:int;
		/** 目标点X坐标值*/
		public var toPosX:int;
		/** 目标点Y坐标值*/
		public var toPosY:int;
		
		public static const SPY_EVENT:String = "com.hifong.war.events.SpyEvent";
		
		public function SpyEvent(num:int,toPosX:int,toPosY:int) 
		{
			super( SPY_EVENT );
			this.num = num;
			this.toPosX = toPosX;
			this.toPosY = toPosY;			
		}
	}
}
