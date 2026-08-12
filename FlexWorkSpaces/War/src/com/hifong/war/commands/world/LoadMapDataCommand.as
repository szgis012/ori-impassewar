/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.world
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MapDelegate;
	import com.hifong.war.events.world.LoadMapDataEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.LoadingUtil;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WorldUtil;
	import com.hifong.war.vo.MapVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理加载地图数据事件
     *
     */
	public final class LoadMapDataCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:LoadMapDataEvent = event as LoadMapDataEvent;
			var delegate:MapDelegate = new MapDelegate( this );
			delegate.loadMapData(evt.startX,evt.startY,evt.endX,evt.endY);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var gridmap:Object = {};
			var list:ArrayCollection = rs.result as ArrayCollection;
			var map:MapVO;
			for(var i:int=0; i<list.length; i++){
				map = list.getItemAt(i) as MapVO;
				gridmap[WorldUtil.getIndexFromXY(map.posX,map.posY)] = map;
			}
			
			model.worldInfo.gridInfoMap = gridmap;
			
			LoadingUtil.hideLoadingScreen();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
