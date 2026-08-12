/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
	
	
    /**
     * 军队服务代理
     *
     */
	public final class ArmyDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		/**
		 * 
		 * @param responder
		 */
		public function ArmyDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("armyService");
		}
		
		
		/**
		 * 得到所有兵种信息
		 * country 阵营
		 */
		public function getArmyList(contry:int):void{
			var call:Object = service.getArmyListByContry(contry);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市拥有的军队列表
		 */ 
		public function getCityArmyList(cityID:int):void{
			var call:Object = service.getCityArmyList(cityID);
			call.addResponder(responder);
		}
		/**
		 * 获取城市伤兵列表 
		 */
		 public function getWoundArmyList(cityID:int):void{ 
		 	var call:AsyncToken=service.getCityWoundedArmyList(cityID);
		 	call.addResponder(responder);
		 }
		 /**
		  * 治愈伤兵
		  * @param cityWoundedArmyID
		  * @param num
		  */
		 public function cureCityWoundedArmy(cityWoundedArmyID:int,num:int):void{
		 	var call:AsyncToken=service.cureCityWoundedArmy(cityWoundedArmyID,num);
		 	call.addResponder(responder);
		 }
		 public function dismissCityWoundedArmy(cityWoundedArmyID:int,num:int):void{
		 	var call:AsyncToken=service.dismissCityWoundedArmy(cityWoundedArmyID,num);
		 	call.addResponder(responder);
		 }
	}
}
