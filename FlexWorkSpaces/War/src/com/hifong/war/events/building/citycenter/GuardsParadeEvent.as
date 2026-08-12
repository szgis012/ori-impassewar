/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 进行阅兵仪式事件
     *
     */
	public final class GuardsParadeEvent extends CairngormEvent
	{
		public static const GUARDSPARADE_EVENT:String = "com.hifong.war.events.GuardsParadeEvent";
		
		public function GuardsParadeEvent() 
		{
			super( GUARDSPARADE_EVENT );
		}
	}
}
