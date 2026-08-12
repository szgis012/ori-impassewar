/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 排名服务代理
     *
     */
	public final class RankDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function RankDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("rankService");
		}
		
		/**
		 * 根据玩家编号获得玩家排名
		 */
		public function getPlayerRankByPlayerID(playerID:int):void{
			var call:Object = service.getPlayerRankByPlayerID(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据玩家名称获得玩家排名
		 */
		public function getPlayerRankByPlayerName(playerName:String):void{
			var call:Object = service.getPlayerRankByPlayerName(playerName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据玩家排名获得玩家排名列表
		 */
		public function getPlayerRankListByRank(rank:int):void{
			var call:Object = service.getPlayerRankListByRank(rank);
			call.addResponder(responder);
		}
		
		
		
		/**
		 * 根据工会编号获得工会排名
		 */
		public function getGuildRankByGuildID(guildID:int):void{
			var call:Object = service.getGuildRankByGuildID(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会名称获得工会排名
		 */
		public function getGuildRankByGuildName(guildName:String):void{
			var call:Object = service.getGuildRankByGuildName(guildName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会排名获得工会排名列表
		 */
		public function getGuildRankListByRank(rank:int):void{
			var call:Object = service.getGuildRankListByRank(rank);
			call.addResponder(responder);
		}
		
		
		
		/**
		 * 根据城市编号获得城市建设点数排名
		 */
		public function getCityConstructionPointRankByCityID(cityID:int):void{
			var call:Object = service.getCityConstructionPointRankByCityID(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市名称获得城市建设点数排名
		 */
		public function getCityConstructionPointRankByCityName(cityName:String):void{
			var call:Object = service.getCityConstructionPointRankByCityName(cityName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市建设点数排名获得城市建设点数排名列表
		 */
		public function getCityConstructionPointRankListByRank(rank:int):void{
			var call:Object = service.getCityConstructionPointRankListByRank(rank);
			call.addResponder(responder);
		}
		
		
		
		/**
		 * 根据城市编号获得城市科技点数排名
		 */
		public function getCityTechnologyPointRankByCityID(cityID:int):void{
			var call:Object = service.getCityTechnologyPointRankByCityID(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市名称获得城市科技点数排名
		 */
		public function getCityTechnologyPointRankByCityName(cityName:String):void{
			var call:Object = service.getCityTechnologyPointRankByCityName(cityName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市科技点数排名获得城市科技点数排名列表
		 */
		public function getCityTechnologyPointRankListByRank(rank:int):void{
			var call:Object = service.getCityTechnologyPointRankListByRank(rank);
			call.addResponder(responder);
		}
		
		
		
		/**
		 * 根据城市编号获得城市人口排名
		 */
		public function getCityPopulationRankByCityID(cityID:int):void{
			var call:Object = service.getCityTechnologyPointRankByCityID(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市名称获得城市人口排名
		 */
		public function getCityPopulationRankByCityName(cityName:String):void{
			var call:Object = service.getCityPopulationRankByCityName(cityName);
			call.addResponder(responder);
		}
		
		/**
		 * 根据城市人口排名获得城市人口排名列表
		 */
		public function getCityPopulationRankListByRank(rank:int):void{
			var call:Object = service.getCityPopulationRankListByRank(rank);
			call.addResponder(responder);
		}
		
		/**
		 * 获得玩家数量
		 */
		public function getPlayerNum():void{
			var call:Object = service.getPlayerNum();
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会数量
		 */
		public function getGuildNum():void{
			var call:Object = service.getGuildNum();
			call.addResponder(responder);
		}
		
		/**
		 * 获得城市数量
		 */
		public function getCityNum():void{
			var call:Object = service.getCityNum();
			call.addResponder(responder);
		}
		
	}
}
