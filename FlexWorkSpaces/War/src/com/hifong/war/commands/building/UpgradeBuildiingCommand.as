/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.events.building.UpgradeBuildiingEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityBuildingVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理建筑升级事件
     *
     */
	public final class UpgradeBuildiingCommand extends SequenceCommand implements ICommand, IResponder
	{
		/** 更新后刷新建筑信息 */
		public override function execute(event:CairngormEvent) : void
		{
			var evt:UpgradeBuildiingEvent = event as UpgradeBuildiingEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			var cb:CityBuildingVO = evt.cityBuilding;
			delegate.buildBuilding(cb.cityID,cb.buildingID,cb.position);
			this.nextEvent = new RefreshCityBuildingEvent(cb.cityBuildingID);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			//更新城市资源信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
			//更新城市人口信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityPopulationEvent(ModelLocator.getInstance().cityInfo.cityID));
			this.executeNextCommand();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
