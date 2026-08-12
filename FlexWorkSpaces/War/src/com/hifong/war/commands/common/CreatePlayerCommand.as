/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.common.CreatePlayerEvent;
	import com.hifong.war.events.common.LoadPlayerGlobalDataEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	
	import mx.rpc.IResponder;
	
    /**
     *  处理玩家首次进入游戏创建角色的事件
     *
     */
	public final class CreatePlayerCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		//玩家编号
		private var userID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CreatePlayerEvent = event as CreatePlayerEvent;
			this.userID = evt.userID;
			var delegate:PlayerDelegate = new PlayerDelegate( this );
			delegate.createPlayer(evt.userID, evt.playerName, evt.cityName, evt.contry, evt.playerImg, evt.mapArea);
		}
		
		public function result(data:Object) : void
		{
			//如果创建成功就加载玩家的信息
			CairngormEventDispatcher.getInstance().dispatchEvent(new LoadPlayerGlobalDataEvent(userID));
			//关闭创建角色窗口
			WindowUtil.closeWindow(model.createPlayerWindow);
		}
		
		public function fault(info:Object) : void
		{
			model.createPlayerWindow.btnCreatePlayer.enabled = true;
			MsgBox.showDefaultError(info);
		}
	}
}
