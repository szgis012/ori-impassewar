/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityInfoByCityIDEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityInfoVO;
	import com.hifong.war.vo.CityVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市部分信息事件
     *
     */
	public final class GetCityInfoByCityIDCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityInfoByCityIDEvent = event as GetCityInfoByCityIDEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityInfoByCityID(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			var ci:CityInfoVO = rs.result as CityInfoVO;
			//如果坐标值不同，就重新加载地图信息
			if(model.cityInfo.posX != ci.posX || model.cityInfo.posY != ci.posY){
				model.worldInfo.loadMapData(ci.posX,ci.posY);
			}
			
			model.cityInfo.posX = ci.posX;
			model.cityInfo.posY = ci.posY;
			model.cityInfo.name = ci.name;
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
