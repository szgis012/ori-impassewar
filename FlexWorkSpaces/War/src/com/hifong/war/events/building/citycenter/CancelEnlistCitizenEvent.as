/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 取消征召市民事件
     *
     */
	public final class CancelEnlistCitizenEvent extends CairngormEvent
	{
		/** 进程编号*/
		public var productionProcessID:int;
		
		
		public static const CANCELENLISTCITIZEN_EVENT:String = "com.hifong.war.events.CancelEnlistCitizenEvent";
		
		public function CancelEnlistCitizenEvent(productionProcessID:int) 
		{
			super( CANCELENLISTCITIZEN_EVENT );
			
			this.productionProcessID = productionProcessID;
		}
	}
}
