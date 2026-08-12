/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.ShowCityHeroInfoEvent;
	
	import mx.rpc.IResponder;

	public final class ShowCityHeroInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowCityHeroInfoEvent = event as ShowCityHeroInfoEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
		}
		
		public function result(data:Object) : void
		{
			
		}
		
		public function fault(info:Object) : void
		{
		
		}
		
	}
}