/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.GetCompletedBuildingListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市已有的建筑列表事件
     *
     */
	public final class GetCompletedBuildingListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator =  ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCompletedBuildingListEvent = event as GetCompletedBuildingListEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.getCityBuildingListByCityID(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var list:ArrayCollection = rs.result as ArrayCollection;
			
			for(var i:int=0; i<list.length; i++){
				model.cityBuildingList.addItem(list.getItemAt(i));
			}
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
