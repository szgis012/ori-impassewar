/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.equipment
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerEquipmentListByCategoryEvent extends CairngormEvent
	{

		public var playerID:int;
		
		public var category:int;

		public static const GETPLAYEREQUIPMENTLISTBYCATEGORY_EVENT:String = "com.hifong.war.events.GetPlayerEquipmentListByCategoryEvent";

		public function GetPlayerEquipmentListByCategoryEvent(playerID:int,category:int) 
		{
			super( GETPLAYEREQUIPMENTLISTBYCATEGORY_EVENT );
			this.playerID = playerID;
			this.category = category;
		}
	}
}
