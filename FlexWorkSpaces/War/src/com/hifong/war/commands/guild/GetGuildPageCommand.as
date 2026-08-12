/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetGuildPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetGuildPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetGuildPageEvent = event as GetGuildPageEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildNum();
		}
		
		public function result(data:Object) : void
		{
			var guildNum:int = data.result as int;
			var pageSize:int = ModelLocator.getInstance().guildPageSize;
			var pageNum:int;
	
			if(guildNum%pageSize!=0){
				pageNum = guildNum/pageSize + 1;
			}else{
				pageNum = guildNum/pageSize;
			}
			
			ModelLocator.getInstance().guildPage = Math.max(pageNum, 1);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}