/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.defense
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.building.CityDefenseDelegate;
	import com.hifong.war.events.building.defense.GetCityDefenseListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.CityDefenseUtil;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市所有防御的信息事件
     *
     */
	public final class GetCityDefenseListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityDefenseListEvent = event as GetCityDefenseListEvent;
			var delegate:CityDefenseDelegate = new CityDefenseDelegate( this );
			delegate.getCityDefenseList(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var arr:ArrayCollection = rs.result as ArrayCollection;
			
			model.cityDefenseInfo.cityDefenseMap = CityDefenseUtil.getCityDefenseMap(arr);
			model.cityDefenseInfo.cityDefenseList = arr;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
