/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.barracks
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     *  裁减新兵事件
     *
     */
	public final class ReduceSoldierEvent extends CairngormEvent
	{
		/** 裁减新兵的数量 */
		public var reduceNum:int;
		
		public static const REDUCESOLDIER_EVENT:String = "com.hifong.war.events.ReduceSoldierEvent";
		
		public function ReduceSoldierEvent(reduceNum:int) 
		{
			super( REDUCESOLDIER_EVENT );
			
			this.reduceNum = reduceNum;
		}
	}
}
