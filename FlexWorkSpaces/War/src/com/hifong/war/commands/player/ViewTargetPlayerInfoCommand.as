/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.player
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.player.ViewTargetPlayerInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.PlayerVO;
	
	import mx.rpc.IResponder;

	public final class ViewTargetPlayerInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ViewTargetPlayerInfoEvent = event as ViewTargetPlayerInfoEvent;
			var delegate:PlayerDelegate = new PlayerDelegate(this);
			delegate.getPlayerInfoByID(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().targetPlayerInfo = data.result as PlayerVO;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}