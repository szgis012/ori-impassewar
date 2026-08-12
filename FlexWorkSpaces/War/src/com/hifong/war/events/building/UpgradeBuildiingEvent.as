/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.CityBuildingVO;

    /**
     * 建筑升级事件
     *
     */
	public final class UpgradeBuildiingEvent extends CairngormEvent{
		//要升级的建筑
		public var cityBuilding:CityBuildingVO;
		
		public static const UPGRADEBUILDIING_EVENT:String = "com.hifong.war.events.UpgradeBuildiingEvent";
		
		public function UpgradeBuildiingEvent(cb:CityBuildingVO) 
		{
			super( UPGRADEBUILDIING_EVENT );
			
			this.cityBuilding = cb;
		}
	}
}
