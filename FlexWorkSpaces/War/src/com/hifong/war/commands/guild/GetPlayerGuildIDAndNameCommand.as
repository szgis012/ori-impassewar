/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetPlayerGuildIDAndNameEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetPlayerGuildIDAndNameCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPlayerGuildIDAndNameEvent = event as GetPlayerGuildIDAndNameEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getPlayerGuildIDAndName(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.playerInfo.guildID = data.result.guildID;
			model.playerInfo.guildName = data.result.guildName;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}