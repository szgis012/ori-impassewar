/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.stat
{
	import com.adobe.cairngorm.control.CairngormEvent;


	/**
	 * 同时更新四种资源工作人数的事件
	 * 
	 */ 
	public final class UpdateResourcesWorkerEvent extends CairngormEvent
	{

		public static const UPDATERESOURCESWORKER_EVENT:String = "com.hifong.war.events.UpdateResourcesWorkerEvent";

		/** 木材厂工作人数*/
		public var woodWorkerNum:int;
		
		/** 炼钢厂工作人数*/
		public var steelWorkerNum:int;
		
		/** 油田工作人数*/
		public var oilWorkerNum:int;
		
		/** 食物工作人数*/
		public var foodWorkerNum:int;
		
		
		public function UpdateResourcesWorkerEvent(woodWorkerNum:int,steelWorkerNum:int,oilWorkerNum:int,foodWorkerNum:int) 
		{
			super( UPDATERESOURCESWORKER_EVENT );
			this.woodWorkerNum = woodWorkerNum;
			this.steelWorkerNum = steelWorkerNum;
			this.oilWorkerNum = oilWorkerNum;
			this.foodWorkerNum = foodWorkerNum;
		}
	}
}
