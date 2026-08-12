/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import mx.collections.ArrayCollection;

	public final class GetMapListByMapPosXYListEvent extends CairngormEvent
	{

		public static const GETMAPLISTBYMAPPOSXYLIST_EVENT:String = "com.hifong.war.events.GetMapListByMapPosXYListEvent";

		public var mapXY:ArrayCollection;
		public function GetMapListByMapPosXYListEvent(mapXY:ArrayCollection) 
		{
			super( GETMAPLISTBYMAPPOSXYLIST_EVENT );
			this.mapXY=mapXY;
		}
	}
}
