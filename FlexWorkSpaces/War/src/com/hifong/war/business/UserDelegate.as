/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 军队服务代理
     *
     */
	public final class UserDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function UserDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("playerService");
		}
		
		
		/**
	     * 登陆系统
	     * @param name 用户名
	     * @param password 密码
	     * @return 
	     */
	    public function login(name:String):void{
	    	var call:Object = service.getPlayerByUserName(name);
			call.addResponder(responder);
	    }
		
	}
}
