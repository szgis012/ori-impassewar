/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 征收物资事件
     *
     */
	public final class ImposeMaterialEvent extends CairngormEvent
	{
		public static const IMPOSEMATERIAL_EVENT:String = "com.hifong.war.events.ImposeMaterialEvent";
		
		public function ImposeMaterialEvent() 
		{
			super( IMPOSEMATERIAL_EVENT );
		}
	}
}
