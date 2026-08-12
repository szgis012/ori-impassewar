/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.common.IsPlayerNameExistedEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class IsPlayerNameExistedCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:IsPlayerNameExistedEvent = event as IsPlayerNameExistedEvent;
			var delegate:PlayerDelegate = new PlayerDelegate(this);
			delegate.isPlayerNameExisted(evt.playerName);
		}
		
		public function result(data:Object) : void
		{
			if(data.result==true){
				ModelLocator.getInstance().createPlayerWindow.playerNameToolTip.setStyle("color","#FF0000");
				ModelLocator.getInstance().createPlayerWindow.playerNameToolTip.text = "市长名称已存在，请重新输入。";
			}else{
				ModelLocator.getInstance().createPlayerWindow.playerNameToolTip.setStyle("color","#00FF00");
				ModelLocator.getInstance().createPlayerWindow.playerNameToolTip.text = "市长名称可以正常使用!";
			} 
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}