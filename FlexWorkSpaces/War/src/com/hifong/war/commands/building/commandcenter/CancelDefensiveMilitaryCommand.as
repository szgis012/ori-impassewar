/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.CancelDefensiveMilitaryEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 取消城守军队
     *
     */
	public final class CancelDefensiveMilitaryCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelDefensiveMilitaryEvent = event as CancelDefensiveMilitaryEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate( this );
			delegate.cancelDefensiveMilitary(evt.cityMilitaryID);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.defensiveMilitary = -1;
			//刷新军队信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
