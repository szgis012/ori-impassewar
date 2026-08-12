/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 提升军魂
	 * @param cityHeroID
	 */
	public final class AddMilitarySpiritEvent extends CairngormEvent
	{

		public static const ADDMILITARYSPIRIT_EVENT:String = "com.hifong.war.events.AddMilitarySpiritEvent";

		public var cityHeroID:int;
		public function AddMilitarySpiritEvent(cityHeroID:int) 
		{
			super( ADDMILITARYSPIRIT_EVENT );
			this.cityHeroID=cityHeroID;
		}
	}
}
