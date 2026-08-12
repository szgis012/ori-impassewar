/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.GuardsParadeEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.common.GetCityTaxAndSecurityEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理阅兵仪式事件
     *
     */
	public final class GuardsParadeCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GuardsParadeEvent = event as GuardsParadeEvent;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			delegate.doGuardsParade(model.cityInfo.cityID);
			
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新城市资源信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			//更新税率和治安信息
			dispatcher.dispatchEvent(new GetCityTaxAndSecurityEvent());
			
			MsgBox.showMessage("成功进行阅兵仪式！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
