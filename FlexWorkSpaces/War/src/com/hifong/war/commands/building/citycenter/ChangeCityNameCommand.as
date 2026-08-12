/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.ChangeCityNameEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理城市改名事件
     *
     */
	public final class ChangeCityNameCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var newCityName:String;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:ChangeCityNameEvent = event as ChangeCityNameEvent;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			this.newCityName = evt.newCityName;
			
			delegate.changeCityName(model.cityInfo.cityID,this.newCityName);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.name = this.newCityName;	
			MsgBox.showMessage("成功修改城市名称！");		
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
