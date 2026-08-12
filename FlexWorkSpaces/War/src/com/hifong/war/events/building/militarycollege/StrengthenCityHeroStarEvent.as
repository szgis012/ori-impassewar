/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.view.building.militarycollege.StrengthenCityHeroStarWindow;
	/**
	 * 强化城市英雄星级
	 * @param playerID
	 * @param cityHeroID
	 * @param upgradeLuckTreasureID: (强运符)TreasureConstant中661~662, 0 代表不用道具
	 * @param stimulateBloodTreasureID: (血激符)TreasureConstant中663~664, 0 代表不用道具
	 */
	public final class StrengthenCityHeroStarEvent extends CairngormEvent
	{

		public static const STRENGTHENCITYHEROSTAR_EVENT:String = "com.hifong.war.events.StrengthenCityHeroStarEvent";
		
		public var playerID:int;
		public var cityHeroID:int;
		public var upgradeLuckTreasureID:int;
		public var stimulateBloodTreasureID:int;
		
		public var window:StrengthenCityHeroStarWindow;
		public function StrengthenCityHeroStarEvent(playerID:int,cityHeroID:int,upgradeLuckTreasureID:int,stimulateBloodTreasureID:int,window:StrengthenCityHeroStarWindow) 
		{
			super( STRENGTHENCITYHEROSTAR_EVENT );
			this.playerID=playerID;
			this.cityHeroID=cityHeroID;
			this.upgradeLuckTreasureID=upgradeLuckTreasureID;
			this.stimulateBloodTreasureID=stimulateBloodTreasureID;
			
			this.window=window;
		}
	}
}
