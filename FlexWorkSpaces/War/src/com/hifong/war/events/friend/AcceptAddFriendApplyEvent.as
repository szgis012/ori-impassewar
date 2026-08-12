/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.friend
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 接受好友申请
	 * @param playerID 执行审批的玩家编号
	 * @param targetPlayerID 接受审批的玩家编号
	 */
	public final class AcceptAddFriendApplyEvent extends CairngormEvent
	{

		public static const ACCEPTADDFRIENDAPPLY_EVENT:String = "com.hifong.war.events.AcceptAddFriendApplyEvent";
	
		public var playerID:int;
		public var targetPlayerID:int;
		public function AcceptAddFriendApplyEvent(playerID:int,targetPlayerID:int) 
		{
			super( ACCEPTADDFRIENDAPPLY_EVENT );
			this.playerID=playerID;
			this.targetPlayerID=targetPlayerID;
		}
	}
}
