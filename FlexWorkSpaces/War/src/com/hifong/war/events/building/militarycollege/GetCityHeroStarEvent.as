/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 获得城市英雄星级
	 * @param cityHeroID
	 * @return
	 */
	public final class GetCityHeroStarEvent extends CairngormEvent
	{

		public static const GETCITYHEROSTAR_EVENT:String = "com.hifong.war.events.GetCityHeroStarEvent";

		public var cityHeroID:int;
		public function GetCityHeroStarEvent(cityHeroID:int) 
		{
			super( GETCITYHEROSTAR_EVENT );
			this.cityHeroID=cityHeroID;
		}
	}
}
