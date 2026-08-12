/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.armory
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.ArmoryDelegate;
	import com.hifong.war.events.building.armory.ProduceOrdnanceEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityOrdnanceVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理军械制造事件
     *
     */
	public final class ProduceOrdnanceCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		/** 军械编号 */
	    private var ordnanceID:int;
	    
		public function execute(event:CairngormEvent) : void
		{
			var evt:ProduceOrdnanceEvent = event as ProduceOrdnanceEvent;
			var delegate:ArmoryDelegate = new ArmoryDelegate( this );
			this.ordnanceID = evt.ordnanceID;
			delegate.produceOrdnance(model.cityInfo.cityID,evt.ordnanceID,evt.num);
		}
		
		public function result(data:Object) : void
		{
			var dispacher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			var rs:ResultEvent = data as ResultEvent;
			var pp:ProductionQueueVO = rs.result as ProductionQueueVO;
//			model.ordnanceInfo.ordnanceProcessList.addItem(pp);
			var co:CityOrdnanceVO = model.ordnanceInfo.cityOrdnanceMap[ordnanceID] as CityOrdnanceVO;
			
			//如果map中不存在就新创建一个
			if(!co){
				co = new CityOrdnanceVO();
				co.cityID = pp.cityID;
				co.cityOrdnanceID = pp.targetID;
				co.num = 0;
				co.ordnanceID = ordnanceID;
				
				model.ordnanceInfo.cityOrdnanceMap[ordnanceID] = co;
				//同时添加
				model.ordnanceInfo.cityOrdnanceMap2[co.cityOrdnanceID] = co;
			}
			
			//重新加载进程列表
			dispacher.dispatchEvent(new GetOrdnanceProcessListEvent());
			//更新资源信息
//			dispacher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
