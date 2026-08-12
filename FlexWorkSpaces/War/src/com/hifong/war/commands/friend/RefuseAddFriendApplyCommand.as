/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package   com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.RefuseAddFriendApplyEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 拒绝好友申请
	 * @param playerID 执行拒绝的玩家编号
	 * @param targetPlayerID 被拒绝的玩家编号
	 */
	public final class RefuseAddFriendApplyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:RefuseAddFriendApplyEvent = event as RefuseAddFriendApplyEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.refuseAddFriendApply(evt.playerID,evt.targetPlayerID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功拒绝好友申请"); 
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}