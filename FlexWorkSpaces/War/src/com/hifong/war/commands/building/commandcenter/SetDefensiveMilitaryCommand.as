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
	import com.hifong.war.events.building.commandcenter.SetDefensiveMilitaryEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 设置城守军队
     *
     */
	public final class SetDefensiveMilitaryCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityMilitaryID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:SetDefensiveMilitaryEvent = event as SetDefensiveMilitaryEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate( this );
			this.cityMilitaryID = evt.cityMilitaryID;
			delegate.setDefensiveMilitary(cityMilitaryID);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.defensiveMilitary = this.cityMilitaryID;
			//刷新军队信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
