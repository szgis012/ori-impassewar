/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 批量更改城市未编制军队信息
     *
     */
	public final class BatchModifyCityArmyEvent extends CairngormEvent
	{
		/**
		 * 需要调整的未编制城市军队信息，格式如：
		 * cityArmyID1:num1,cityArmyID2:num2 ...
		 */ 
		public var cityArmyInfo:String;
		
		
		public static const BATCHMODIFYCITYARMY_EVENT:String = "com.hifong.war.events.BatchModifyCityArmyEvent";
		
		public function BatchModifyCityArmyEvent(cityArmyInfo:String) 
		{
			super( BATCHMODIFYCITYARMY_EVENT );
			
			this.cityArmyInfo = cityArmyInfo;
		}
	}
}
