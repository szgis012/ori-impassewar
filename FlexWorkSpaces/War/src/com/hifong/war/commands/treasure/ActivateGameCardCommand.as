/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GameCardDelegate;
	import com.hifong.war.events.treasure.ActivateGameCardEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ActivateGameCardCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ActivateGameCardEvent = event as ActivateGameCardEvent;
			var delegate:GameCardDelegate = new GameCardDelegate(this);
			delegate.activateGameCard(evt.playerID,evt.gameCardNO,evt.cardType);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("游戏卡奖励激活成功，奖励已发送至您的道具-宝箱中。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}