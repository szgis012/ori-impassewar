/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ActivateGameCardEvent extends CairngormEvent
	{

		public static const ACTIVATEGAMECARD_EVENT:String = "com.hifong.war.events.ActivateGameCardEvent";

		public var playerID:int

		public var gameCardNO:String;
		
		public var cardType:int;

		public function ActivateGameCardEvent(playerID:int,gameCardNO:String,cardType:int) 
		{
			super( ACTIVATEGAMECARD_EVENT );
			this.playerID = playerID;
			this.gameCardNO = gameCardNO;
			this.cardType = cardType;
		}
	}
}
