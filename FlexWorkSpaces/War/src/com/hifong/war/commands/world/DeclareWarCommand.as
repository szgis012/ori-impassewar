/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.DeclareWarDelegate;
	import com.hifong.war.events.world.DeclareWarEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     *  处理宣战事件
     *
     */
	public final class DeclareWarCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:DeclareWarEvent = event as DeclareWarEvent;
			var delegate:DeclareWarDelegate = new DeclareWarDelegate(this);
			delegate.declareWar(evt.playerID,evt.targetPlayerID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("宣战成功，战斗将于12小时后开启。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
