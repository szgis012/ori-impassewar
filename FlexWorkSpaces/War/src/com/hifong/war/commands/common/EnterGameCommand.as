/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.PlayerDelegate;
	import com.hifong.war.events.common.EnterGameEvent;
	import com.hifong.war.events.common.LoadPlayerGlobalDataEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	
    /**
     * 处理用户通过登陆验证进入游戏的事件
     *
     */
	public final class EnterGameCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/** 登陆用户的编号*/
		private var userID:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:EnterGameEvent = event as EnterGameEvent;
			var delegate:PlayerDelegate = new PlayerDelegate( this );
			this.userID = evt.userID;
			delegate.getPlayerByUserID(evt.userID);
		}
		
		public function result(data:Object) : void
		{
			var player:Object = data.result;
			
			//如果用户还未创建角色
			if(player == null){
				WindowUtil.closeWindow(model.loginWindow);
				showCreatePlayerWindow();
			}else{
				//初始化游戏运行环境
				CairngormEventDispatcher.getInstance().dispatchEvent(new LoadPlayerGlobalDataEvent(userID))
			}
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
		//显示创建角色窗口
		private function showCreatePlayerWindow():void{
			var win:CreatePlayerWindow = new CreatePlayerWindow();
			win.userID = userID;
			model.createPlayerWindow = win;
			PopUpManager.addPopUp(win,model.app);
		}
	}
}
