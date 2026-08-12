/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.RemoveGuildMemberEvent;
	import com.hifong.war.events.guild.ShowGuildMemberRemoveListEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RemoveGuildMemberCommand implements ICommand, IResponder
	{

		private var guildID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:RemoveGuildMemberEvent = event as RemoveGuildMemberEvent;
			guildID = evt.guildID;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.removeGuildPlayer(evt.guildID,evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowGuildMemberRemoveListEvent(guildID));
			MsgBox.showMessage("移除玩家成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}