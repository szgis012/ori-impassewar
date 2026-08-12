/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	/**
     * 宣战服务代理
     *
     */
	public final class DeclareWarDelegate
	{
		 //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function DeclareWarDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("declareWarService");
		}
		
		/**
		 * 宣战
		 */
		public function declareWar(playerID:int,targetPlayerID:int):void{
			var call:Object = service.declareWar(playerID,targetPlayerID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得宣战信息
		 */
		public function getDeclareWar(playerID:int,targetPlayerID:int):void{
			var call:Object = service.declareWar(playerID,targetPlayerID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得玩家宣战列表
		 */
		public function getPlayerDeclareWarList(playerID:int):void{
			var call:Object = service.getCityDeclareWarList(playerID);
			call.addResponder(responder);
		}
		
	}
}
