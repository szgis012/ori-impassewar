/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildEventPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowGuildEventPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildEventPageEvent = event as ShowGuildEventPageEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildEventAmount(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var eventAmount:int = data.result as int;
			var pageSize:int = ModelLocator.getInstance().guildPageSize;
			var pageNum:int;
	
			if(eventAmount%pageSize!=0){
				pageNum = eventAmount/pageSize + 1;
			}else{
				pageNum = eventAmount/pageSize;
			}
	
			ModelLocator.getInstance().guildEventPage = pageNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}