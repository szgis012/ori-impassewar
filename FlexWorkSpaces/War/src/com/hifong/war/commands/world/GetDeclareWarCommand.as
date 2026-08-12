/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.DeclareWarDelegate;
	import com.hifong.war.events.world.GetDeclareWarEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetDeclareWarCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetDeclareWarEvent = event as GetDeclareWarEvent;
			var delegate:DeclareWarDelegate = new DeclareWarDelegate(this);
			delegate.declareWar(evt.playerID,evt.targetPlayerID);
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