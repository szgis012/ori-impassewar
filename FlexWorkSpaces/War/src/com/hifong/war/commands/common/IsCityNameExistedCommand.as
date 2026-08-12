/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.IsCityNameExistedEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class IsCityNameExistedCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:IsCityNameExistedEvent = event as IsCityNameExistedEvent;
			var delegate:CityDelegate = new CityDelegate(this);
			delegate.isCityNameExisted(evt.cityName);
		}
		
		public function result(data:Object) : void
		{
			if(data.result==true){
				ModelLocator.getInstance().createPlayerWindow.cityNameToolTip.setStyle("color","#FF0000");
				ModelLocator.getInstance().createPlayerWindow.cityNameToolTip.text = "城市名称已存在，请重新输入。";
			}else{
				ModelLocator.getInstance().createPlayerWindow.cityNameToolTip.setStyle("color","#00FF00");
				ModelLocator.getInstance().createPlayerWindow.cityNameToolTip.text = "城市名称可以正常使用!";
			}
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}