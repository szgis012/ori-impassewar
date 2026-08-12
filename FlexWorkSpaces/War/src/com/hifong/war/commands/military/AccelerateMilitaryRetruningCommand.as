/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.military
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MilitaryDelegate;
	import com.hifong.war.events.military.AccelerateMilitaryRetruningEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class AccelerateMilitaryRetruningCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:AccelerateMilitaryRetruningEvent = event as AccelerateMilitaryRetruningEvent;
			var delegate:MilitaryDelegate = new MilitaryDelegate(this);
			delegate.accelerateMilitaryRetruning(evt.depoyQueueID);
		}
		
		public function result(data:Object) : void
		{
			trace();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}