/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetGuildListByGuildNameEvent;
	import com.hifong.war.events.guild.GetGuildListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetGuildListByGuildNameCommand implements ICommand, IResponder
	{

		private var guildName:String;

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetGuildListByGuildNameEvent = event as GetGuildListByGuildNameEvent;
			guildName = evt.guildName;
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildIDByGuildName(guildName);
		}
		
		public function result(data:Object) : void
		{
			var guildID:int = data.result as int;
			if(guildID==0){
				MsgBox.showMessage("军团 " + guildName + " 不存在。");
				return;
			}
			
			var pageSize:int = ModelLocator.getInstance().guildPageSize;
			var start:int;
			if(guildID%pageSize==0){
				start = guildID/pageSize-1;
			}else{
				start = guildID/pageSize;
			}
			start = start*pageSize;
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetGuildListEvent(start,start+pageSize));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}