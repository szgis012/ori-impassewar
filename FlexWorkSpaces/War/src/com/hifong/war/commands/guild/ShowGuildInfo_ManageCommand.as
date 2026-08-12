/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.events.guild.ShowGuildInfo_ManageEvent;
	import com.hifong.war.model.ModelLocator;

	import mx.rpc.IResponder;

	public final class ShowGuildInfo_ManageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildInfo_ManageEvent = event as ShowGuildInfo_ManageEvent;
		}
		
		public function result(data:Object) : void
		{
			
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}