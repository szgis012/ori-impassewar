/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TreasureDelegate;
	import com.hifong.war.constant.TreasureCategoryConstant;
	import com.hifong.war.constant.TreasureTypeConstant;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.events.building.armory.FinishProduceOrdnanceEvent;
	import com.hifong.war.events.building.market.GetCityTradeQueueListEvent;
	import com.hifong.war.events.building.militarycollege.AddMilitarySoulEvent;
	import com.hifong.war.events.building.militarycollege.GetCityCandidacyHeroListEvent;
	import com.hifong.war.events.building.techcenter.GetCurrentResearchingTechnologyEvent;
	import com.hifong.war.events.common.GetCityInfoByCityIDEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesMaxEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.common.GetCityTaxAndSecurityEvent;
	import com.hifong.war.events.treasure.GetTreasureQueueListEvent;
	import com.hifong.war.events.treasure.UseTreasureEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	import com.hifong.war.view.building.militarycollege.StrengthenCityHeroStarWindow;
	import com.hifong.war.view.treasure.OpenedTreasureWindow;
	import com.hifong.war.vo.ProductionQueueVO;
	import com.hifong.war.vo.TreasureItemVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	
    /**
     * 使用宝物
     *
     */
	public final class UseTreasureCommand implements ICommand, IResponder
	{
		//保存对象便于更新
		private var treasureItem:TreasureItemVO;
		//宝物参数
		private var params:Object;
		
		//ModelLocator
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();


		public function execute(event:CairngormEvent) : void
		{
			var evt:UseTreasureEvent = event as UseTreasureEvent;
			this.treasureItem = evt.treausreItem;
			this.params = evt.params;
			var delegate:TreasureDelegate = new TreasureDelegate( this );
			
			delegate.useTreasure(model.playerInfo.playerID,evt.treausreItem.treasureID,evt.params);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			this.treasureItem.num -= 1;//减少宝物数量
			
			//如果宝物数量用完，就从宝物列表删除掉该项
			if(this.treasureItem.num <= 0){
				model.treasureList.removeItemAt(model.treasureList.getItemIndex(this.treasureItem));
			}
			
			switch(treasureItem.category){
				//加速类
				case TreasureCategoryConstant.ACCELERATE:
					afterAccelerateTreasureUsed();
					break;
				//普通宝物	
				case TreasureCategoryConstant.NORMAL:
					afterNormalTreasureUsed();
					break;
				//生产类宝物
				case TreasureCategoryConstant.RESOURCE_PRODUCTION:
					afterProductionTreasureUsed();
					break;
				//宝箱类	
				case TreasureCategoryConstant.TREASURE_BOX:
					//陈旧储物箱不做特殊处理
					if(treasureItem.treasureID == 23){
						break;
					}else{
						return afterTreasureBoxUsed(rs.result);
					}
				//指挥官类
				case TreasureCategoryConstant.MILITARY:
				//其他类别
				case TreasureCategoryConstant.COMMANDER:
					dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
					break;
				case TreasureCategoryConstant.TASK:
					break;
				
			}
			
			
			var str:String = rs.result as String;
			
			if(str && str.length>0){
				MsgBox.showMessage(str);
			}else{
				MsgBox.showMessage("使用道具成功。");
			}
		}
		
		//使用宝箱后的操作
		private function afterTreasureBoxUsed(result:Object):void{
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			
			var win:OpenedTreasureWindow = new OpenedTreasureWindow();
			win.treasureList = result as String;
			WindowUtil.showModelWindow(win);
		}
		
		//使用生产类宝物后的操作
		private function afterProductionTreasureUsed():void{
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
		}
		
		//使用加速类宝物后的操作
		private function afterAccelerateTreasureUsed():void{
			switch(treasureItem.type){
				//建筑加速
				case TreasureTypeConstant.ACCELERATE_BUILD:
					dispatcher.dispatchEvent(new RefreshCityBuildingEvent(params["cityBuildingID"]));
					break;
				//科技研究加速
				case TreasureTypeConstant.ACCELERATE_TECHNOLOGY:
					dispatcher.dispatchEvent(new GetCurrentResearchingTechnologyEvent(model.cityInfo.cityID));
					break;
				//军械制造加速
				case TreasureTypeConstant.ACCELERATE_ORDNANCE:
					model.ordnanceInfo.ordnanceProcessList.getItemAt(0).finishTime = model.serverTime;
					dispatcher.dispatchEvent(new FinishProduceOrdnanceEvent(model.ordnanceInfo.ordnanceProcessList.getItemAt(0) as ProductionQueueVO));
					break;
				//加速交易过程
				case TreasureTypeConstant.ACCELERATE_TRADE:
					dispatcher.dispatchEvent(new GetCityTradeQueueListEvent(model.cityInfo.cityID))
					break;	
			}
		}
		
		//使用普通类别宝物后的操作
		private function afterNormalTreasureUsed():void{
			switch(treasureItem.type){
				//移动城市
				case TreasureTypeConstant.MOVE_CITY:
					dispatcher.dispatchEvent(new GetCityInfoByCityIDEvent());
					break;
				//如果是镇压暴乱
				case TreasureTypeConstant.SUPPRESS_RIOT:
					dispatcher.dispatchEvent(new GetCityTaxAndSecurityEvent());	
					dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
					break;
				//仓库容量加成
				case TreasureTypeConstant.STORAGE_ADD:
					dispatcher.dispatchEvent(new GetCityResourcesMaxEvent());
					dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
					break;
				//免战
				case TreasureTypeConstant.AVOID_WAR:	
					dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
					break;
				//增加人口上限		
				case TreasureTypeConstant.POPULATION_MAX_ADD:
					dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
					dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
					break;
				//征召命令
				case TreasureTypeConstant.REFRESH_COMMANDER_LIST:
					dispatcher.dispatchEvent(new GetCityCandidacyHeroListEvent(model.cityInfo.cityID));
					break;
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
