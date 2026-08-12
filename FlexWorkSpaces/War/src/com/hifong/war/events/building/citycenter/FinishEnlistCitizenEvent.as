/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class FinishEnlistCitizenEvent extends CairngormEvent
	{

		public static const FINISHENLISTCITIZEN_EVENT:String = "com.hifong.war.events.FinishEnlistCitizenEvent";

		public function FinishEnlistCitizenEvent() 
		{
			super( FINISHENLISTCITIZEN_EVENT );
		}
	}
}
