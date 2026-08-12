/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.military
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MilitaryDelegate;
	import com.hifong.war.events.military.GetSpyDetailEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	
    /**
     * 获得侦察行动详情
     *
     */
	public final class GetSpyDetailCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetSpyDetailEvent = event as GetSpyDetailEvent;
			var delegate:MilitaryDelegate = new MilitaryDelegate( this );
			delegate.getSpyDetail(evt.spyQueueID);
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
