/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 排程服务代理
     *
     */
	public final class TechnologyDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function TechnologyDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("technologyService");
		}

		/**
		 * 获得城市科技列表
		 */
		public function getCityTechnologyListByType(cityID:int, type:int):void{
			
			
			var call:Object = service.getCityTechnologyListByType(cityID,type);
			call.addResponder(responder);
		}
		
		/**
		 * 研究科技
		 */
		public function researchTechnology(cityID:int,technologyID:int):void{
			var call:Object = service.researchTechnology(cityID,technologyID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消研究科技
		 */
		public function cancelResearchTechnology(cityID:int):void{
			var call:Object = service.cancelResearchTechnology(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得当前研究科技
		 */
		public function getCurrentResearchingTechnology(cityID:int):void{
			var call:Object = service.getResearchingTechnology(cityID);
			call.addResponder(responder);
		}
		
		/**
		 * 客户端科技研究完成
		 */
		public function clientProcessFinished(cityTechnologyID:int):void{
			var call:Object = service.clientProcessFinished(cityTechnologyID);
			call.addResponder(responder);
		}

	}
}
