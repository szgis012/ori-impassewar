/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.constant.BuildingConstant;
	import com.hifong.war.events.building.ClientProcessFinishedEvent;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.events.common.GetCityBusinessFreeEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesMaxEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityBuildingVO;
	
	import mx.rpc.IResponder;
	
    /**
     *处理客户端完成建造，升级，拆除的计时时的事件
     *
     */
	public final class ClientProcessFinishedCommand extends SequenceCommand implements  IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityBuilding:CityBuildingVO;
		
		public override function execute(event:CairngormEvent) : void
		{
			var evt:ClientProcessFinishedEvent = event as ClientProcessFinishedEvent;
			this.cityBuilding = evt.cityBuilding;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.clientProcessFinished(evt.cityBuilding.processQueue.processQueueID);
			this.nextEvent = new RefreshCityBuildingEvent(evt.cityBuilding.cityBuildingID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//对不同建筑进行不同的升级后处理
			switch(cityBuilding.buildingID){
				//住宅
				case BuildingConstant.HOURSE:
					dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
				break;
				//仓库
				case BuildingConstant.STORAGE:
					dispatcher.dispatchEvent(new GetCityResourcesMaxEvent());
					break;
				//农场 伐木场 炼钢厂 油井
				case BuildingConstant.FARM:
				case BuildingConstant.LUMBER_MILL:
				case BuildingConstant.STEEL_PLANT:
				case BuildingConstant.OIL_WELL:
					dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
					break;
				//市场	
				case BuildingConstant.MARKET:
					dispatcher.dispatchEvent(new GetCityBusinessFreeEvent());
					break;	
			}
			
			//更新城市资源信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			
			//刷新城市建筑信息
			this.executeNextCommand();
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
