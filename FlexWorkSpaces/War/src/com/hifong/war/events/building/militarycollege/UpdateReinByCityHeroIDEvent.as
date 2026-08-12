/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 根据城市英雄编号更改统御
	 * @param cityHeroID
	 * @param rein
	 */
	public final class UpdateReinByCityHeroIDEvent extends CairngormEvent
	{

		public static const UPDATEREINBYCITYHEROID_EVENT:String = "com.hifong.war.events.UpdateReinByCityHeroIDEvent";

		public var cityHeroID:int;
		public var rein:int;
		public function UpdateReinByCityHeroIDEvent(cityHeroID:int,rein:int) 
		{
			super( UPDATEREINBYCITYHEROID_EVENT );
			this.cityHeroID=cityHeroID;
			this.rein=rein;
		}
	}
}
