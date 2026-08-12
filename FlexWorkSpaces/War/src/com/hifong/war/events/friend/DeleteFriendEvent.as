/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 删除好友
	 * @param friendID
	 */
	public final class DeleteFriendEvent extends CairngormEvent
	{

		public static const DELETEFRIEND_EVENT:String = "com.hifong.war.events.DeleteFriendEvent";

		public var playerID:int;
		public var targetPlayerID:int;
		public function DeleteFriendEvent(playerID:int,targetPlayerID:int) 
		{
			super( DELETEFRIEND_EVENT );
			this.playerID=playerID;
			this.targetPlayerID=targetPlayerID;
		}
	}
}
