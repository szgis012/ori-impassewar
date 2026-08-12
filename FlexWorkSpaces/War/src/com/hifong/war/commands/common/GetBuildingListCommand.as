/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.common.GetBuildingListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.BuildingUtil;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.BuildingVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得所有建筑列表事件
     *
     */
	public final class GetBuildingListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetBuildingListEvent = event as GetBuildingListEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.getBuildingList();
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			model.buildingInfo.buildingList = rs.result as ArrayCollection;
			model.buildingInfo.buildingMap = BuildingUtil.getBuildingListMap(model.buildingInfo.buildingList );
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}
