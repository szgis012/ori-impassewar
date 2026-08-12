/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 根据玩家编号获得地图收藏信息列表
	 * @param playerID
	 * @return
	 */
	public final class GetMapFavouritePagingListEvent extends CairngormEvent
	{

		public static const GETMAPFAVOURITEPAGINGLIST_EVENT:String = "com.hifong.war.events.GetMapFavouritePagingListEvent";

		public var playerID:int;
		public var start:int;
		public var offset:int;
		public function GetMapFavouritePagingListEvent(playerID:int, start:int, offset:int) 
		{
			super( GETMAPFAVOURITEPAGINGLIST_EVENT );
			this.playerID=playerID;
			this.start=start;
			this.offset=offset;
		}
	}
}
