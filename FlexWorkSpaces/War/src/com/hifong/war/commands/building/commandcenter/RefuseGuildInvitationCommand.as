/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.GetPlayerGuildAppInvListEvent;
	import com.hifong.war.events.building.commandcenter.RefuseGuildInvitationEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RefuseGuildInvitationCommand implements ICommand, IResponder
	{

		private var playerID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:RefuseGuildInvitationEvent = event as RefuseGuildInvitationEvent;
			playerID = evt.playerID;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.refuseGuildInvitation(evt.playerID,evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerGuildAppInvListEvent(playerID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}