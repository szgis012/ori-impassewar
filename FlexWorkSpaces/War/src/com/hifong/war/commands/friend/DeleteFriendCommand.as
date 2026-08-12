/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.DeleteFriendEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 删除好友
	 * @param friendID
	 */
	public final class DeleteFriendCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DeleteFriendEvent = event as DeleteFriendEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this); 
			delegate.deleteFriend(evt.playerID,evt.targetPlayerID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功删除好友");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}