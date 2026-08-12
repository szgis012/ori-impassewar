/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TimeDelegate;
	import com.hifong.war.events.common.GetServerTimeEvent;
	import com.hifong.war.model.ModelLocator;
	
	import mx.rpc.IResponder;

	public final class GetServerTimeCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetServerTimeEvent = event as GetServerTimeEvent;
			var delegate:TimeDelegate = new TimeDelegate(this);
			delegate.getServerTime();
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().serverTime = data.result;
		}
		
		public function fault(info:Object) : void
		{
			trace();
		}
		
	}
}