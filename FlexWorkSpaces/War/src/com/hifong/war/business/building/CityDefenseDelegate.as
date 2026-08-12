/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business.building
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 城市防御服务代理
     *
     */
	public final class CityDefenseDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function CityDefenseDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("cityDefenseService");
		}
		
		/**
	     * 获得城市所有防御的信息
	     * @param cityID
	     * @return
	     */
	    public function getCityDefenseList(cityID:int):void{
	    	var call:Object = service.getCityDefenseList(cityID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 建造城市的防御
	     * @param cityID 城市编号
	     * @param type 防御类型
	     * @param num 数量
	     */
	    public function buildCityDefense(cityID:int,type:int, num:int):void{
	    	var call:Object = service.buildCityDefense(cityID,type,num);
			call.addResponder(responder);
	    }
	    
	    /**
		 * 客户端完成生产计算时调用该方法可以及时刷新信息
		 * @param productionProcessID
		 */
		public function clientProcessFinished(productionProcessID:int):void{
			var call:Object = service.clientProcessFinished(productionProcessID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消城防生产
		 * @param productionProcessID
		 */
		public function cancelBuildDefense(productionProcessID:int):void{
			var call:Object = service.cancelBuildDefense(productionProcessID);
			call.addResponder(responder);
		}
		
		/**
		 * 立即完成所有的城防建造进程(需要道具)
		 * @param cityID
		 */
		public function finishAllBuildProcess(cityID:int):void{
			var call:Object = service.finishAllBuildProcess(cityID);
			call.addResponder(responder);
		}
	    
	}
}
