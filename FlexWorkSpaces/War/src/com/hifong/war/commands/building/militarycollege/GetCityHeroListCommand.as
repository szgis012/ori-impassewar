/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityHeroVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public final class GetCityHeroListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityHeroListEvent = event as GetCityHeroListEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.getCityHeroList(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			model.cityHeroList = data.result as ArrayCollection;
			var cityHeroMap:Object = {};
			var cityHero:CityHeroVO ;
			
			for each( cityHero in model.cityHeroList){
				cityHeroMap[cityHero.cityHeroID] = cityHero;
			}
			
			model.cityHeroMap = cityHeroMap;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(fault as FaultEvent);	
		}
		
	}
}