/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.colonization
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ImposeEvent extends CairngormEvent
	{

		public static const IMPOSE_EVENT:String = "com.hifong.war.events.ImposeEvent";
		
		public var colonizationID:int;
		
		public var colonizeType:int;

		public function ImposeEvent(colonizationID:int,colonizeType:int) 
		{
			super( IMPOSE_EVENT );
			this.colonizationID = colonizationID;
			this.colonizeType = colonizeType;
		}
	}
}
