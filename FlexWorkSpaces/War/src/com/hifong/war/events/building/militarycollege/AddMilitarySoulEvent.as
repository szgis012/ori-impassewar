/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 增加军魄点数
	 * @param playerID
	 * @param cityHeroID
	 * @param treasureID TreasureConstant中671~673
	 */
	public final class AddMilitarySoulEvent extends CairngormEvent
	{

		public static const ADDMILITARYSOUL_EVENT:String = "com.hifong.war.events.AddMilitarySoulEvent";
		
		public var playerID:int;
		public var cityHeroID:int;
		public var treasureID:int;
		public function AddMilitarySoulEvent(playerID:int,cityHeroID:int,treasureID:int) 
		{
			super( ADDMILITARYSOUL_EVENT );
			this.playerID=playerID;
			this.cityHeroID=cityHeroID;
			this.treasureID=treasureID;
		}
	}
}
