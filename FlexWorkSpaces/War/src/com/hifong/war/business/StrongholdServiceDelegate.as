/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 要塞service代理
     *
     */
	public final class StrongholdServiceDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function StrongholdServiceDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("strongholdService");
		}
		
		/**
	     * 获得要塞可建筑的建筑列表
	     * @param strongholdID 要塞编号
	     * @return List<Shbuilding>
	     */
	    public function getStrongoldAvailableBuildingList(strongholdID:int):void{
	    	var call:Object = service.getStrongoldAvailableBuildingList(strongholdID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 获得要塞已建的建筑信息 
	     * @param strongholdID 要塞编号
	     * @return List<StrongholdShbuilding> 
	     */
	    public function getStrongholdBuildingListByStrongholdID(strongholdID:int):void{
	    		var call:Object = service.getStrongholdBuildingListByStrongholdID(strongholdID);
			call.addResponder(responder);
	    } 
	}
}
