/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.CityHeroExtVO;
	import com.hifong.war.vo.CityHeroVO;
	/**
	 * 更新城市英雄以及其扩展信息
	 * @param cityHero
	 * @param cityHeroExt
	 */
	public final class UpdateCityHeroAndCityHeroExtEvent extends CairngormEvent
	{

		public static const UPDATECITYHEROANDCITYHEROEXT_EVENT:String = "com.hifong.war.events.UpdateCityHeroAndCityHeroExtEvent";

		public var cityHero:CityHeroVO;
		public var cityHeroExt:CityHeroExtVO;
		public function UpdateCityHeroAndCityHeroExtEvent(cityHero:CityHeroVO,cityHeroExt:CityHeroExtVO) 
		{
			super( UPDATECITYHEROANDCITYHEROEXT_EVENT );
			this.cityHero=cityHero;
			this.cityHeroExt=cityHeroExt;
		}
	}
}
