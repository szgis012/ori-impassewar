/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.military
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.DepoyQueueDelegate;
	import com.hifong.war.events.military.GetDepoyQueueInfoEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetDepoyQueueInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetDepoyQueueInfoEvent = event as GetDepoyQueueInfoEvent;
			var delegate:DepoyQueueDelegate = new DepoyQueueDelegate(this);
			delegate.getDepoyQueueByID(evt.depoyQueueID);
		}
		
		public function result(data:Object) : void
		{
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}