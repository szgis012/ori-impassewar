/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.ApplyAddFriendEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 申请添加好友
	 * @param playerID 递交申请的玩家编号
	 * @param targetPlayerID 接受申请的玩家编号
	 */	
	public final class ApplyAddFriendCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ApplyAddFriendEvent = event as ApplyAddFriendEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.applyAddFriend(evt.playerID,evt.targetPlayerName);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功申请添加好友");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}