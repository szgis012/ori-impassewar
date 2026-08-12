/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.GetCityHeroEvent;
	import com.hifong.war.events.building.militarycollege.ResetHeroPointEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ResetHeroPointCommand implements ICommand, IResponder
	{

		public var cityHeroID:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ResetHeroPointEvent = event as ResetHeroPointEvent;
			cityHeroID = evt.cityHeroID;
			
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.resetHeroPoint(evt.playerID, evt.cityHeroID, evt.command, evt.defense, evt.mind, evt.executivepower);
		}
		
		public function result(data:Object) : void
		{
			//刷新当前指挥官信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityHeroEvent(cityHeroID));
			MsgBox.showMessage("指挥官重置点数成功。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}