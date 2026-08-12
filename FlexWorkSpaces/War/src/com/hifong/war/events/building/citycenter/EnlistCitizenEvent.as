/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 征召市民事件
     *
     */
	public final class EnlistCitizenEvent extends CairngormEvent
	{
		/** 征召市民的数量 */
		public var enlistNumber:int;
		
		public static const ENLISTCITIZEN_EVENT:String = "com.hifong.war.events.EnlistCitizenEvent";
		
		public function EnlistCitizenEvent(enlistNumber:int) 
		{
			super( ENLISTCITIZEN_EVENT );
			
			this.enlistNumber = enlistNumber;
		}
	}
}
