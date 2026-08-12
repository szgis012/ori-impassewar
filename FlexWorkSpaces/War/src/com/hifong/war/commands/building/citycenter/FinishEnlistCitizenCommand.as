/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.constant.TreasureConstant;
	import com.hifong.war.events.building.citycenter.ClientFinishEnlistCitizenEvent;
	import com.hifong.war.events.building.citycenter.FinishEnlistCitizenEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class FinishEnlistCitizenCommand implements ICommand, IResponder
	{

		private var model:ModelLocator = ModelLocator.getInstance();

		public function execute(event:CairngormEvent) : void
		{
			var evt:FinishEnlistCitizenEvent = event as FinishEnlistCitizenEvent;
			
			var params:Object = {queueID:model.enlistCitizenProcess.productionQueueID};
			
			var delegate:TreasureDelegate = new TreasureDelegate(this);
			delegate.useTreasure(model.playerInfo.playerID, TreasureConstant.CITIZEN_EMLIST_COMMAND, params);
		}
		
		public function result(data:Object) : void
		{
			model.enlistCitizenProcess.finishTime = model.serverTime;
			CairngormEventDispatcher.getInstance().dispatchEvent(new ClientFinishEnlistCitizenEvent(model.enlistCitizenProcess));
			MsgBox.showMessage("市民征召已完成。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}