/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerTreasureListByCategoryEvent extends CairngormEvent
	{

		public static const GETPLAYERTREASURELISTBYCATEGORY_EVENT:String = "com.hifong.war.events.GetPlayerTreasureListByCategoryEvent";

		public var playerID:int;
		
		public var category:int;

		public function GetPlayerTreasureListByCategoryEvent(playerID:int, category:int) 
		{
			super( GETPLAYERTREASURELISTBYCATEGORY_EVENT );
			this.playerID = playerID;
			this.category = category;
		}
	}
}
