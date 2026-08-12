/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.DismissCityMilitaryEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.building.commandcenter.GetFreeCityHeroListCommandEvent;
	import com.hifong.war.events.common.GetCityArmyListEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class DismissCityMilitaryCommand implements ICommand, IResponder
	{

		private var cityID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:DismissCityMilitaryEvent = event as DismissCityMilitaryEvent;
			cityID = evt.cityID;
			
			var delegate:CommandCenterDelegate = new CommandCenterDelegate(this);
			delegate.dismissCityMilitary(evt.cityMilitaryID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			dispatcher.dispatchEvent(new GetCityMilitaryListEvent(cityID));
			dispatcher.dispatchEvent(new GetFreeCityHeroListCommandEvent(cityID));
			dispatcher.dispatchEvent(new GetCityArmyListEvent());
			
			MsgBox.showMessage("解散军队成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}