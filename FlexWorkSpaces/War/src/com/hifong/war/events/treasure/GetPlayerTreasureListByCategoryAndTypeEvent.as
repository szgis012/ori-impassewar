/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerTreasureListByCategoryAndTypeEvent extends CairngormEvent
	{

		public static const GETTREASURELISTBYTYPE_EVENT:String = "com.hifong.war.events.GetTreasureListByTypeEvent";

		public var playerID:int;
		
		public var category:int;
		
		public var aType:int;

		public function GetPlayerTreasureListByCategoryAndTypeEvent(playerID:int,category:int,aType:int) 
		{
			super( GETTREASURELISTBYTYPE_EVENT );
			this.playerID = playerID;
			this.category = category;
			this.aType = aType;
		}
	}
}
