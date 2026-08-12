/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.OrdnanceDelegate;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityOrdnanceVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     *  处理获得城市军械信息列表事件
     *
     */
	public final class GetCityOrdnanceListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityOrdnanceListEvent = event as GetCityOrdnanceListEvent;
			var delegate:OrdnanceDelegate = new OrdnanceDelegate( this );
			delegate.getCityOrdnanceList(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.ordnanceInfo.cityOrdnanceList = rs.result as ArrayCollection;
			
			initCityOrdnanceMap();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
		// 方便通过军械编号找到对应的城市现有的军械信息
		private function initCityOrdnanceMap():void{
			var map:Object = {};
			var map2:Object = {};
			
			var list:ArrayCollection = model.ordnanceInfo.cityOrdnanceList;
			
			if(list && list.length>0){
				var cityOrdnance:CityOrdnanceVO ;
				
				for(var i:int=0; i<list.length; i++){
					cityOrdnance = list.getItemAt(i) as CityOrdnanceVO;
					map[cityOrdnance.ordnanceID] = cityOrdnance;
					map2[cityOrdnance.cityOrdnanceID] = cityOrdnance;
				}
			}
			
			model.ordnanceInfo.cityOrdnanceMap = map;
			model.ordnanceInfo.cityOrdnanceMap2 = map2;
		}
	}
}
