/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.building.commandcenter.TuneCityMilitaryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     *  处理编制一只军队事件
     *
     */
	public final class TuneCityMilitaryCommand2 implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:TuneCityMilitaryEvent = event as TuneCityMilitaryEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate( this );
			
			delegate.tuneCityMilitary(evt.cityMilitaryID,evt.armyInfo);
		}
		
		public function result(data:Object) : void
		{
			//从新获取军队信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
			MsgBox.showMessage("军队编制成功。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
