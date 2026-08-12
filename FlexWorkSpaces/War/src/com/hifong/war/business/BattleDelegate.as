/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 战斗服务代理
     *
     */
	public final class BattleDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
		
		//远程军队对象
		private var militaryService:Object;
	
	
		public function BattleDelegate(responder:IResponder)
		{
			this.responder = responder;
			
			this.militaryService = ServiceLocator.getInstance().getRemoteObject("battleService");
		}

		/**
		 * 获得军队信息
		 */
		public function getCityMilitaryByID(cityMilitaryID:int):void{
			var call:Object = militaryService.getCityMilitaryByID(cityMilitaryID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得战斗信息
		 */
		public function getBattleInfo(battleID:int):void{
			var call:Object = militaryService.getBattleInfo(battleID);
			call.addResponder(responder);
		}

		/**
		 * 获得城市攻击战斗列表
		 */
		public function getCityAttackBattleList(cityID:int):void{
			var call:Object = militaryService.getCityAttackBattleList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市防守战斗列表
		 */
		public function getCityDefenseBattleList(cityID:int):void{
			var call:Object = militaryService.getCityDefenseBattleList(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市战斗列表
		 */
		public function getCityBattleList(cityID:int):void{
			var call:Object = militaryService.getCityBattleList(cityID);
			call.addResponder(responder);
		}
		
	}
}
