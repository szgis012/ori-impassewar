/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.armory
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.ArmoryDelegate;
	import com.hifong.war.events.building.armory.BackoutOrdnanceEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理拆卸军械事件
     *
     */
	public final class BackoutOrdnanceCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
	    
		public function execute(event:CairngormEvent) : void
		{
			var evt:BackoutOrdnanceEvent = event as BackoutOrdnanceEvent;
			var delegate:ArmoryDelegate = new ArmoryDelegate( this );
			delegate.backoutOrdnance(evt.cityOrdnanceID,evt.num);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源和城市军械信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
			
			MsgBox.showMessage("拆卸成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
