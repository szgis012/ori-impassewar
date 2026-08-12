/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.GetFriendListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	/**
	 * 获得好友列表
	 * @param playerID
	 */
	public final class GetFriendListCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetFriendListEvent = event as GetFriendListEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.getFriendList(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().friendList=data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}