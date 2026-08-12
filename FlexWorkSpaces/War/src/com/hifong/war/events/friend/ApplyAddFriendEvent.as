/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 申请添加好友
	 * @param playerID 递交申请的玩家编号
	 * @param targetPlayerID 接受申请的玩家编号
	 */
	public final class ApplyAddFriendEvent extends CairngormEvent
	{

		public static const APPLYADDFRIEND_EVENT:String = "com.hifong.war.events.ApplyAddFriendEvent";
		public var playerID:int;
		public var targetPlayerName:String;
		public function ApplyAddFriendEvent(playerID:int,targetPlayerName:String) 
		{
			super( APPLYADDFRIEND_EVENT );
			this.playerID=playerID;
			this.targetPlayerName=targetPlayerName;
		}
	}
}
