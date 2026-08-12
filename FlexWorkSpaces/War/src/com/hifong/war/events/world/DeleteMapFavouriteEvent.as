/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 根据编号删除地图收藏信息
	 * @param mapFavouriteID
	 */
	public final class DeleteMapFavouriteEvent extends CairngormEvent
	{

		public static const DELETEMAPFAVOURITE_EVENT:String = "com.hifong.war.events.DeleteMapFavouriteEvent";
		public var mapFavouriteID:int;
		public function DeleteMapFavouriteEvent(mapFavouriteID:int) 
		{
			super( DELETEMAPFAVOURITE_EVENT );
			this.mapFavouriteID=mapFavouriteID;
		}
	}
}
