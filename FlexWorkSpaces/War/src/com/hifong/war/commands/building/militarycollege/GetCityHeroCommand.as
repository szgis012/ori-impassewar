/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetCityHeroCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityHeroEvent = event as GetCityHeroEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.getCityHero(evt.cityHeroID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().currentCityHero = data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}