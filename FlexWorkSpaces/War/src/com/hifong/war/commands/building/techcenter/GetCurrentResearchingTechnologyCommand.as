/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.techcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TechnologyDelegate;
	import com.hifong.war.events.building.techcenter.GetCurrentResearchingTechnologyEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProcessQueueVO;
	
	import mx.rpc.IResponder;

	public final class GetCurrentResearchingTechnologyCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCurrentResearchingTechnologyEvent = event as GetCurrentResearchingTechnologyEvent;
			var delegate:TechnologyDelegate = new TechnologyDelegate(this);
			delegate.getCurrentResearchingTechnology(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			if(data.result==null){
				ModelLocator.getInstance().currentResearchingTechnology = "当前没有正在研究的科技。";
				model.haveTechnologyResearching = false;
			}else{
				model.techProcess =  data.result.processQueue as ProcessQueueVO;
				model.currentResearchingTechnology = "正在研究：" + data.result.researchingTechnology.technology.name + "（等级" + data.result.researchingTechnology.level + "→" + (int(data.result.researchingTechnology.level)+1) + "）";
				model.haveTechnologyResearching = true;
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}