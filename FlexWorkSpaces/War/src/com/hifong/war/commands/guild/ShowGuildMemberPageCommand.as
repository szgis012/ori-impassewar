/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildMemberPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowGuildMemberPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildMemberPageEvent = event as ShowGuildMemberPageEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildMemberAmount(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var memberAmount:int = data.result as int;
			var pageSize:int = ModelLocator.getInstance().guildPageSize;
			var pageNum:int;
	
			if(memberAmount%pageSize!=0){
				pageNum = memberAmount/pageSize + 1;
			}else{
				pageNum = memberAmount/pageSize;
			}
	
			ModelLocator.getInstance().guildMemberPage = pageNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}