/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.TreasureQueueVO;

	/**
	 * 取消宝物的效果
	 * 
	 */ 
	public final class CancelTreasureQueueEvent extends CairngormEvent
	{

		public static const CANCELTREASUREQUEUE_EVENT:String = "com.hifong.war.events.CancelTreasureQueueEvent";
		
		/** 要取消的宝物效果编号*/
		public var treasureQueue:TreasureQueueVO;
		
		
		public function CancelTreasureQueueEvent(treasureQueue:TreasureQueueVO) 
		{
			super( CANCELTREASUREQUEUE_EVENT );
			this.treasureQueue = treasureQueue;
		}
	}
}
