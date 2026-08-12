/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.CancelCityOfficerEvent;
	import com.hifong.war.events.building.militarycollege.GetCityHeroListEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 取消城市的执政官设置
     *
     */
	public final class CancelCityOfficerCommand implements ICommand, IResponder
	{
		
		private var model:ModelLocator = ModelLocator.getInstance();
		
		//城市指挥官编号
		private var cityHeroID:int;
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelCityOfficerEvent = event as CancelCityOfficerEvent;
			var delegate:HeroDelegate = new HeroDelegate( this );
			this.cityHeroID = evt.cityHeroID;
			delegate.cancelCityOfficer(cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.officer = -1;
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			dispatcher.dispatchEvent(new GetCityHeroListEvent(model.cityInfo.cityID));
			MsgBox.showMessage("城市已经没有执政官");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
