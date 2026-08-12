/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.BuildBuildingEvent;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理建筑建造事件
     *
     */
	public final class BuildBuildingCommand extends SequenceCommand implements  IResponder
	{
		public override function execute(event:CairngormEvent) : void
		{
			var evt:BuildBuildingEvent = event as BuildBuildingEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.buildBuilding(evt.cityID,evt.buildingID,evt.position);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			
			if(result.result){
				this.nextEvent = new RefreshCityBuildingEvent(int(result.result));
				//更新城市资源信息
				CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
				//更新城市人口信息
				CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityPopulationEvent(ModelLocator.getInstance().cityInfo.cityID));
				this.executeNextCommand();
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
