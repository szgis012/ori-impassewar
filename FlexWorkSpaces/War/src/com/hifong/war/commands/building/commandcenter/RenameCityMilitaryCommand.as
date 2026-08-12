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
	import com.hifong.war.events.building.commandcenter.RenameCityMilitaryEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RenameCityMilitaryCommand implements ICommand, IResponder
	{
		
		private var cityID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:RenameCityMilitaryEvent = event as RenameCityMilitaryEvent;
			cityID = evt.cityID;
			
			var delegate:CommandCenterDelegate = new CommandCenterDelegate(this);
			delegate.renameCityMilitary(evt.cityMilitaryID,evt.name);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(cityID));
			MsgBox.showMessage("军队改名成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}