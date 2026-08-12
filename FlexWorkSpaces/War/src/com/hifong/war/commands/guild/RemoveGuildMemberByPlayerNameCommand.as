/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.RemoveGuildMemberByPlayerNameEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RemoveGuildMemberByPlayerNameCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:RemoveGuildMemberByPlayerNameEvent = event as RemoveGuildMemberByPlayerNameEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.removeGuildPlayerByPlayerName(evt.guildID,evt.playerName);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("移除玩家成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}