/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.DispatchEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理派遣事件
     *
     */
	public final class DispatchCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:DispatchEvent = event as DispatchEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate( this );
			delegate.dispatch(evt.cityMilitaryID,evt.posX,evt.posY,evt.carryFood,evt.carryWood,evt.carryOil,evt.carrySteel,evt.carryMoney);
		}
		
		public function result(data:Object) : void
		{
			//刷新城市军队列表
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
			MsgBox.showMessage("出征成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);  
		}
	}
}
