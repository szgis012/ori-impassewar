/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.ChangeCityMilitaryOfficerEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.building.commandcenter.GetFreeCityHeroListCommandEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ChangeCityMilitaryOfficerCommand implements ICommand, IResponder
	{

		private var cityID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ChangeCityMilitaryOfficerEvent = event as ChangeCityMilitaryOfficerEvent;
			cityID = evt.cityID;
			
			var delegate:CommandCenterDelegate = new CommandCenterDelegate(this);
			delegate.changeOfficer(evt.cityMilitaryID,evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(cityID));
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetFreeCityHeroListCommandEvent(cityID));
			MsgBox.showMessage("任命指挥官成功");
		}
		
		public function fault(info:Object) : void
		{
		}
		
	}
}