/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 添加地图收藏信息
	 * @param favourite
	 */
	public final class CreateMapFavouriteEvent extends CairngormEvent
	{

		public static const CREATEMAPFAVOURITE_EVENT:String = "com.hifong.war.events.CreateMapFavouriteEvent";
		
		public var playerID:int;
		public var posX:int;
		public var posY:int;
		public function CreateMapFavouriteEvent(playerID:int,posX:int,posY:int) 
		{
			super( CREATEMAPFAVOURITE_EVENT );
			this.playerID=playerID;
			this.posX=posX;
			this.posY=posY;
		}
	}
}
