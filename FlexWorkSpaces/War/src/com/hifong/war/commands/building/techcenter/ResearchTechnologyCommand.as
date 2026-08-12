/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.techcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TechnologyDelegate;
	import com.hifong.war.events.building.techcenter.GetCurrentResearchingTechnologyEvent;
	import com.hifong.war.events.building.techcenter.ResearchTechnologyEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ResearchTechnologyCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var cityID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:ResearchTechnologyEvent = event as ResearchTechnologyEvent;
			cityID = evt.cityID;
			
			var delegate:TechnologyDelegate = new TechnologyDelegate(this);
			delegate.researchTechnology(evt.cityID,evt.technologyID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//刷新当前研究科技
			dispatcher.dispatchEvent(new GetCurrentResearchingTechnologyEvent(cityID));
			//更新城市资源
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}