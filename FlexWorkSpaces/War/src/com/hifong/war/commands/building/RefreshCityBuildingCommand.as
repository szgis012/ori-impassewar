/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityBuildingVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     *  处理刷新CityBuilding信息的事件
     *
     */
	public final class RefreshCityBuildingCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		//要刷新的cityBuildingID
		private var cityBuildingID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:RefreshCityBuildingEvent = event as RefreshCityBuildingEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			this.cityBuildingID = evt.cityBuildingID;
			delegate.getCityBuildingByID(this.cityBuildingID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var cityBuilding:CityBuildingVO = rs.result as CityBuildingVO;
			//查找buildingList的citybuildingVO
			var cb:CityBuildingVO;
			//CityBuildingVO在buildingList中的索引
			var ind:int ;
			
			//查找要更新的CityBuildingVO
			for(var i:int=0; i<model.cityBuildingList.length; i++){
				cb = model.cityBuildingList.getItemAt(i) as CityBuildingVO;
				//如果cityBuildingID相同
				if(cb.cityBuildingID == this.cityBuildingID){
					ind = i;
					break;
				}
				
				cb = null;
			}
			
			//添加或者更新
			if(cityBuilding){
				//如果已存在就更新，否则就添加
				if(cb){
					cb.level = cityBuilding.level;
					cb.processQueue = cityBuilding.processQueue;
					cb.state = cityBuilding.state;
					cb.building = cityBuilding.building;
				}
				else{
					model.cityBuildingList.addItem(cityBuilding);
				}
			}else{
				//删除建筑
				if(cb){
					model.cityBuildingList.removeItemAt(ind);
				}
			}
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
