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
	import com.hifong.war.events.building.techcenter.TechnologyResearchFinishedEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class TechnologyResearchFinishedCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:TechnologyResearchFinishedEvent = event as TechnologyResearchFinishedEvent;
			var delegate:TechnologyDelegate = new TechnologyDelegate(this);
			delegate.clientProcessFinished(evt.cityTechnologyID);
		}
		
		public function result(data:Object) : void
		{
			model.techProcess = null;
			//更新城市当前研究科技
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCurrentResearchingTechnologyEvent(ModelLocator.getInstance().cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}