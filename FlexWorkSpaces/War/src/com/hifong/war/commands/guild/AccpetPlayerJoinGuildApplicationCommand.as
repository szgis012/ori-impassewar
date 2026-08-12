/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.AccpetPlayerJoinGuildApplicationEvent;
	import com.hifong.war.events.guild.ShowGuildPlayerAppInvListEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class AccpetPlayerJoinGuildApplicationCommand implements ICommand, IResponder
	{

		private var guildID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:AccpetPlayerJoinGuildApplicationEvent = event as AccpetPlayerJoinGuildApplicationEvent;
			guildID = evt.guildID;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.accpetPlayerJoinGuildApplication(evt.playerID,evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			//刷新工会玩家申请邀请列表
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowGuildPlayerAppInvListEvent(guildID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}