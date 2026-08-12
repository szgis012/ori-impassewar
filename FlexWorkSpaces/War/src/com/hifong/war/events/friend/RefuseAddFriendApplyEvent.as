/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RefuseAddFriendApplyEvent extends CairngormEvent
	{
	/**
	 * 拒绝好友申请
	 * @param playerID 执行拒绝的玩家编号
	 * @param targetPlayerID 被拒绝的玩家编号
	 */		
		public static const REFUSEADDFRIENDAPPLY_EVENT:String = "com.hifong.war.events.RefuseAddFriendApplyEvent";
		
		public var playerID:int;
		public var targetPlayerID:int;
		public function RefuseAddFriendApplyEvent(playerID:int,targetPlayerID:int) 
		{
			super( REFUSEADDFRIENDAPPLY_EVENT );
			this.playerID=playerID;
			this.targetPlayerID=targetPlayerID;
		}
	}
}
