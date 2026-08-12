/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.common.GetPlayerInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.PlayerVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 获得玩家的信息
     *
     */
	public final class GetPlayerInfoCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPlayerInfoEvent = event as GetPlayerInfoEvent;
			var delegate:PlayerDelegate = new PlayerDelegate( this );
			delegate.getPlayerInfo(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			model.playerInfo = data.result as PlayerVO;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
