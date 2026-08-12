/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.UserDelegate;
	import com.hifong.war.constant.UserStateConstant;
	import com.hifong.war.events.common.LoadPlayerGlobalDataEvent;
	import com.hifong.war.events.common.LoginEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理登陆系统事件
     *
     */
	public final class LoginCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:LoginEvent = event as LoginEvent;
			var delegate:UserDelegate = new UserDelegate( this );
			
			delegate.login(evt.name);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			//用户信息
			var user:Object = rs.result;
			
			switch(user.state){
				case UserStateConstant.ABNORMAL:
					MsgBox.showMessage("账号处在异常状态，不能进行游戏！");
					break;
				case UserStateConstant.NORMAL:
					//初始化游戏运行环境
					CairngormEventDispatcher.getInstance().dispatchEvent(new LoadPlayerGlobalDataEvent(user.userID))
					break;
				case UserStateConstant.NOT_ACTIVE:
					WindowUtil.closeWindow(model.loginWindow);
					showCreatePlayerWindow(user.userID);
					break;		
			}
			
		}
		
		//显示创建角色窗口
		private function showCreatePlayerWindow(user:Object):void{
			var win:CreatePlayerWindow = new CreatePlayerWindow();
			win.userID = user.userID;
			model.createPlayerWindow = win;
			PopUpManager.addPopUp(win,model.app);
		}
		
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
