/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.military
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MilitaryDelegate;
	import com.hifong.war.events.military.GetAttackDetailEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	
    /**
     * 获得出兵队列的详细信息
     */
	public final class GetAttackDetailCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetAttackDetailEvent = event as GetAttackDetailEvent;
			var delegate:MilitaryDelegate = new MilitaryDelegate( this );
			delegate.getAttackDetail(evt.depoyQueueID);
		}
		
		public function result(data:Object) : void
		{
			model.militaryActionDetail = data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
