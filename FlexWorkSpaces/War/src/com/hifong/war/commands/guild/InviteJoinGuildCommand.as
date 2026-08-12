/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.InviteJoinGuildEvent;
	import com.hifong.war.events.guild.ShowGuildPlayerAppInvListEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class InviteJoinGuildCommand implements ICommand, IResponder
	{

		private var guildID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:InviteJoinGuildEvent = event as InviteJoinGuildEvent;
			guildID = evt.guildID;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.inviteJoinGuild(evt.guildID,evt.playerName);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("邀请成功");
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowGuildPlayerAppInvListEvent(guildID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}