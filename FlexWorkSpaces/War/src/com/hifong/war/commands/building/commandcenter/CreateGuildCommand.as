/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.building.commandcenter.CreateGuildEvent;
	import com.hifong.war.events.guild.GetPlayerGuildIDAndNameEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class CreateGuildCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:CreateGuildEvent = event as CreateGuildEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.createGuild(evt.guild);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetPlayerGuildIDAndNameEvent(ModelLocator.getInstance().playerInfo.playerID));
			MsgBox.showMessage("创建军团成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}