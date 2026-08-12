/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.friend
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.friend.GetFriendNumEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 获得好友数目
	 * @param playerID
	 * @return
	 */
	public final class GetFriendNumCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetFriendNumEvent = event as GetFriendNumEvent;
			var delegate:PlayerDelegate=new PlayerDelegate(this);
			delegate.getFriendNum(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var num:int=data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}