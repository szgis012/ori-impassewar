/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroListEvent;
	import com.hifong.war.events.building.militarycollege.SetCityOfficerEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理设置城市执政官事件
     *
     */
	public final class SetCityOfficerCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		//城市指挥官编号
		private var cityHeroID:int;
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:SetCityOfficerEvent = event as SetCityOfficerEvent;
			this.cityHeroID = evt.cityHeroID;
			var delegate:HeroDelegate = new HeroDelegate( this );
			delegate.setCityOfficer(cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.officer = cityHeroID;
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroListEvent(model.cityInfo.cityID));
			MsgBox.showMessage("任命成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
