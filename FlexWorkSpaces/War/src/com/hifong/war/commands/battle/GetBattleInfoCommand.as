/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.battle
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BattleDelegate;
	import com.hifong.war.events.battle.GetBattleInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetBattleInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetBattleInfoEvent = event as GetBattleInfoEvent;
			var delegate:BattleDelegate = new BattleDelegate(this);
			delegate.getBattleInfo(evt.battleID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().battleInfo = data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage(info.fault);
		}
		
	}
}