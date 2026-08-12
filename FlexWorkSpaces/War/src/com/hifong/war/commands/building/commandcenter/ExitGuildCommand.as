/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.ExitGuildEvent;
	import com.hifong.war.events.guild.GetPlayerGuildIDAndNameEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ExitGuildCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ExitGuildEvent = event as ExitGuildEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.exitGuild(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().playerInfo.guildID = 0;
			ModelLocator.getInstance().playerInfo.guildName = "无";
			MsgBox.showMessage("退出军团成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}