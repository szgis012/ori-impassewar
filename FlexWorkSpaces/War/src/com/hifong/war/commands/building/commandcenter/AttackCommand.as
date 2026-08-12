/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.commandcenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CommandCenterDelegate;
	import com.hifong.war.events.building.commandcenter.AttackEvent;
	import com.hifong.war.events.building.commandcenter.GetCityMilitaryListEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理发动攻击事件
     *
     */
	public final class AttackCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:AttackEvent = event as AttackEvent;
			var delegate:CommandCenterDelegate = new CommandCenterDelegate( this );
			delegate.attack(evt.cityMilitaryID,evt.posX,evt.posY);
		}
		
		public function result(data:Object) : void
		{
			//刷新城市军队列表
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityMilitaryListEvent(model.cityInfo.cityID));
			//刷新城市资源
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
			MsgBox.showMessage("出征成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
