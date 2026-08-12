/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.DismissGuildEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class DismissGuildCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DismissGuildEvent = event as DismissGuildEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.dismissGuild(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.guildVO = null;
			model.playerInfo.guildID = 0;
			model.playerInfo.guildName = "无";
			MsgBox.showMessage("军团解散成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}