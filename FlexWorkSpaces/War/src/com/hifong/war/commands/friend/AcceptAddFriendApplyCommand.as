/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.AcceptAddFriendApplyEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 接受好友申请
	 * @param playerID 执行审批的玩家编号
	 * @param targetPlayerID 接受审批的玩家编号
	 */
	public final class AcceptAddFriendApplyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:AcceptAddFriendApplyEvent = event as AcceptAddFriendApplyEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.acceptAddFriendApply(evt.playerID,evt.targetPlayerID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功接受好友申请"); 
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}