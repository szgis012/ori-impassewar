/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 根据玩家编号获得其地图收藏条数
	 * @param playerID
	 * @return
	 */
	public final class GetMapFavouriteNumOfPlayerEvent extends CairngormEvent
	{

		public static const GETMAPFAVOURITENUMOFPLAYER_EVENT:String = "com.hifong.war.events.GetMapFavouriteNumOfPlayerEvent";

		public var playerID:int;
		public function GetMapFavouriteNumOfPlayerEvent(playerID:int) 
		{
			super( GETMAPFAVOURITENUMOFPLAYER_EVENT );
			this.playerID=playerID;
		}
	}
}
