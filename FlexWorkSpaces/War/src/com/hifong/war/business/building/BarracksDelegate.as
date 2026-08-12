/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 兵营相关操作服务代理
     *
     */
	public final class BarracksDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function BarracksDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("barracksService");
		}
		
		/**
		 * 招募新兵
		 * enlistNum 招募数量
		 */ 
		public function enlistSoldier(cityID:int, enlistNum:int):void{
			var call:Object = service.enlistSoldier(cityID,enlistNum);
			call.addResponder(responder);
		}
		
		/**
		 * 裁减新兵
		 * reduceNum 裁减数量
		 */ 
		public function reduceSoldier(cityID:int, reduceNum:int):void{
			var call:Object = service.reduceSoldier(cityID,reduceNum);
			call.addResponder(responder);
		}
		
		/**
		 * 武装新兵
		 * armyID 士兵类型
		 * num 人数
		 */ 
		public function armSoldier(cityID:int,armyID:int, num:int):void{
			var call:Object = service.armSoldier(cityID,armyID,num);
			call.addResponder(responder);
		}
		
		/**
		 * 解除士兵的武装
		 * 注：解除武装的士兵将成为新兵
		 * armyID 士兵类型
		 * num 人数
		 */ 
		public function disarmSoldier(cityID:int,armyID:int, num:int):void{
			var call:Object = service.disarmSoldier(cityID,armyID,num);
			call.addResponder(responder);
		}
		
	}
}
