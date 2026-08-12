/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.GetPlayerGuildAppInvListEvent;
	import com.hifong.war.events.guild.CancelApplyJoinGuildEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class CancelApplyJoinGuildCommand implements ICommand, IResponder
	{

		private var playerID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelApplyJoinGuildEvent = event as CancelApplyJoinGuildEvent;
			playerID = evt.playerID;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.cancelApplyJoinGuild(evt.guildID,evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			//刷新玩家工会申请邀请列表
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerGuildAppInvListEvent(playerID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}