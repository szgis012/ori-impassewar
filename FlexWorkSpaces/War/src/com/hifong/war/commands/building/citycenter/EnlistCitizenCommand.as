/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.EnlistCitizenEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理征召市民的事件
     *
     */
	public final class EnlistCitizenCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		/** 征召市民的数量 */
		public var enlistNum:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:EnlistCitizenEvent = event as EnlistCitizenEvent;
			this.enlistNum = evt.enlistNumber;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			delegate.enlistCitizen(model.cityInfo.cityID,evt.enlistNumber);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;

			//更新征召市民的进程
			model.enlistCitizenProcess =  rs.result as ProductionQueueVO;
			
			//更新城市资源信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
