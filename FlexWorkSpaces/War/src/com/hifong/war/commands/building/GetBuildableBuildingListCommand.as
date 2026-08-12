/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.GetBuildableBuildingListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.view.building.Building;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获取可建造建筑列表事件
     *
     */
	public final class GetBuildableBuildingListCommand implements ICommand, IResponder
	{
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetBuildableBuildingListEvent = event as GetBuildableBuildingListEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.getCityAvailableBuildingList(evt.cityID);
		}

		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			var list:ArrayCollection = result.result as ArrayCollection
			var buildableList:ArrayCollection = new ArrayCollection();
			
			if(list && list.length>0){
				
				for(var i:int=0; i<list.length; i++){
					buildableList.addItem(new Building(list.getItemAt(i)));
				}
			}
			
			ModelLocator.getInstance().buildableBuildingList = buildableList;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
