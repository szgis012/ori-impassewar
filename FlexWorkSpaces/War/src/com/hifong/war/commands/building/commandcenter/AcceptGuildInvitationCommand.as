/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.AcceptGuildInvitationEvent;
	import com.hifong.war.events.building.commandcenter.GetPlayerGuildAppInvListEvent;
	import com.hifong.war.events.guild.GetPlayerGuildIDAndNameEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class AcceptGuildInvitationCommand implements ICommand, IResponder
	{
		
		private var playerID:int;
		private var guildID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:AcceptGuildInvitationEvent = event as AcceptGuildInvitationEvent;
			playerID = evt.playerID;
			guildID = evt.guildID;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.acceptGuildInvitation(evt.playerID,evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerGuildAppInvListEvent(playerID));
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerGuildIDAndNameEvent(playerID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}