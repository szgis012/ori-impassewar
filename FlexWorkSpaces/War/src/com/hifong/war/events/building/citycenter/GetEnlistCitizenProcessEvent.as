/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得征召市民进程的事件
     *
     */
	public final class GetEnlistCitizenProcessEvent extends CairngormEvent
	{
		public static const GETENLISTCITIZENPROCESS_EVENT:String = "com.hifong.war.events.GetEnlistCitizenProcessEvent";
		
		public function GetEnlistCitizenProcessEvent() 
		{
			super( GETENLISTCITIZENPROCESS_EVENT );
		}
	}
}
