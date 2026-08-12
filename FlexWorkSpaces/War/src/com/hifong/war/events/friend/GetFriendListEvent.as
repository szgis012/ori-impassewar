/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 获得好友列表
	 * @param playerID
	 */
	public final class GetFriendListEvent extends CairngormEvent
	{

		public static const GETFRIENDLIST_EVENT:String = "com.hifong.war.events.GetFriendListEvent";
		public var playerID:int;
		public function GetFriendListEvent(playerID:int) 
		{
			super( GETFRIENDLIST_EVENT );
			this.playerID=playerID;
		}
	}
}
