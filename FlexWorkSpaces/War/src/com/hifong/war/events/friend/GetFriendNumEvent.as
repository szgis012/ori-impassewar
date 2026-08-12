/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 获得好友数目
	 * @param playerID
	 * @return
	 */
	public final class GetFriendNumEvent extends CairngormEvent
	{

		public static const GETFRIENDNUM_EVENT:String = "com.hifong.war.events.GetFriendNumEvent";
		
		public var playerID:int;
		public function GetFriendNumEvent(playerID:int) 
		{
			super( GETFRIENDNUM_EVENT );
			this.playerID=playerID;
		}
	}
}
