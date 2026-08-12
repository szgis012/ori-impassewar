/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.HolidayCelebrateEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.common.GetCityTaxAndSecurityEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理进行节日庆典事件
     *
     */
	public final class HolidayCelebrateCommand implements ICommand, IResponder
	{
		public var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:HolidayCelebrateEvent = event as HolidayCelebrateEvent;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			delegate.doHolidayCelebrate(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新城市资源信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			//更新税率和治安信息
			dispatcher.dispatchEvent(new GetCityTaxAndSecurityEvent());
			
			MsgBox.showMessage("成功进行节日庆典！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
