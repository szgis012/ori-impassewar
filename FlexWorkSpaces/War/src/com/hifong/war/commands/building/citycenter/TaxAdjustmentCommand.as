/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.TaxAdjustmentEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.common.GetCityTaxAndSecurityEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理调整税收事件
     *
     */
	public final class TaxAdjustmentCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var newValue:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:TaxAdjustmentEvent = event as TaxAdjustmentEvent;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			this.newValue = evt.newValue;
			delegate.adjustTax(model.cityInfo.cityID,this.newValue);
		}
		
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源产量信息
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			//更新税率和治安信息
			dispatcher.dispatchEvent(new GetCityTaxAndSecurityEvent());
			
			MsgBox.showMessage("成功调整税率！");
		}

		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
			
		}
	}
}
