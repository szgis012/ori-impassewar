/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.CityBuildingVO;

    /**
     * 客户端完成建造，升级，拆除的计时时的事件
     *
     */
	public final class ClientProcessFinishedEvent extends CairngormEvent
	{
		/** 完成进程的城市建筑 */
		public var cityBuilding:CityBuildingVO;
		
		public static const CLIENTPROCESSFINISHED_EVENT:String = "com.hifong.war.events.ClientProcessFinishedEvent";
		
		public function ClientProcessFinishedEvent(cb:CityBuildingVO) 
		{
			super( CLIENTPROCESSFINISHED_EVENT );
			this.cityBuilding = cb;
		}
	}
}
