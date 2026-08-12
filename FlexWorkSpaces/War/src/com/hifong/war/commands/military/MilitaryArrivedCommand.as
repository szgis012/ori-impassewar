/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.military
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.DepoyQueueDelegate;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.military.MilitaryArrivedEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class MilitaryArrivedCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:MilitaryArrivedEvent = event as MilitaryArrivedEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate(this);
			delegate.clientMilitaryArrived(evt.depoyQueueID);
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