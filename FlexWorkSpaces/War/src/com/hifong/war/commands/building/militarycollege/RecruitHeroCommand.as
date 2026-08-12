/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.militarycollege
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.HeroDelegate;
	import com.hifong.war.events.building.militarycollege.RecruitHeroEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class RecruitHeroCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:RecruitHeroEvent = event as RecruitHeroEvent;
			var delegate:HeroDelegate = new HeroDelegate(this);
			delegate.recruitHero(evt.cityCandidacyHeroID);
		}
		
		public function result(data:Object) : void
		{
			//刷新城市资源
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
			
			MsgBox.showMessage("招募指挥官成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage(info.fault.rootCause.message);
		}
		
	}
}