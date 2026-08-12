/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 加载地图数据事件
     *
     */
	public final class LoadMapDataEvent extends CairngormEvent
	{
		/** 地图范围*/
		public var startX:int;
		public var startY:int;
		public var endX:int;
		public var endY:int;
		
		public static const LOADMAPDATA_EVENT:String = "com.hifong.war.events.LoadMapDataEvent";
		
		public function LoadMapDataEvent(startX:int,startY:int,endX:int,endY:int) 
		{
			super( LOADMAPDATA_EVENT );
			
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}
	}
}
