/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.common.ClientProcess;
	import com.hifong.war.events.building.citycenter.CancelEnlistCitizenEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import flash.events.TimerEvent;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理取消征召市民事件
     *
     */
	public final class CancelEnlistCitizenCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelEnlistCitizenEvent = event as CancelEnlistCitizenEvent;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			delegate.cancelEnlistCitizen(evt.productionProcessID);
		}
		
		public function result(data:Object) : void
		{
			//取消招募市民的进程
			model.enlistCitizenProcess = null;
			//更新城市资源信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			
			MsgBox.showMessage("成功取消征召！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
