/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GuildMemberGrantEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 军团授权
	 * 
	 */
	public final class GuildMemberGrantCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GuildMemberGrantEvent = event as GuildMemberGrantEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.guildMemeberGrant(evt.guildPlayer);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("设置成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}