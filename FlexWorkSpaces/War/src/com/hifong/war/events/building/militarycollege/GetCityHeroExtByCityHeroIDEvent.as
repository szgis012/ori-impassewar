/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 获得城市英雄扩展信息
	 * @param cityID
	 */
	public final class GetCityHeroExtByCityHeroIDEvent extends CairngormEvent
	{
  
		public static const GETCITYHEROEXTBYCITYHEROID_EVENT:String = "com.hifong.war.events.GetCityHeroExtByCityHeroIDEvent";
		public var cityID:int;
		public function GetCityHeroExtByCityHeroIDEvent(cityID:int) 
		{
			super( GETCITYHEROEXTBYCITYHEROID_EVENT );
			this.cityID=cityID;
		}
	}
}
