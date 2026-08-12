/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
    /**
     * 军事服务代理类
     */
	public final class MilitaryDelegate
	{ //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
		

		public function MilitaryDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("militaryService");
		}
		
		/**
	     * 获得城市军事行动信息列表
	     * @param cityID
	     * @return
	     */
	    public function getMilitaryActionList(cityID:int):void{
	    	var call:Object = service.getMilitaryActionList(cityID);
			call.addResponder(responder);
	    }
	    
	     /**
	     * 获得指定编号的侦察队列详细
	     * @param spyQueueID
	     * @return
	     */
	    public function getSpyDetail(spyQueueID:int):void{
	    	var call:Object = service.getSpyDetail(spyQueueID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 获得指定编号的出征队列详细信息
	     * @param depoyQueueID
	     * @return
	     */
	    public function getAttackDetail(depoyQueueID:int):void{
	    	var call:Object = service.getAttackDetail(depoyQueueID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 获得城市敌情警报信息列表
	     */
	    public function getMilitaryDefenseList(cityID:int):void{
	    	var call:Object = service.getMilitaryDefenseList(cityID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 根据指定坐标获得是否正在战斗或已经有军队前往
	     */
	    public function hasMilitaryInBattleOrGoingToMap(posX:int,posY:int):void{
	    	var call:Object = service.hasMilitaryInBattleOrGoingToMap(posX,posY);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 加速军队返回
	     */
	    public function accelerateMilitaryRetruning(depoyQueue:int):void{
	    	var call:Object = service.accelerateMilitaryRetruning(depoyQueue);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 召回军队
	     */
	    public function recallMilitary(depoyQueue:int):void{
	    	var call:Object = service.recallMilitary(depoyQueue);
			call.addResponder(responder);
	    }
	    
	}
}
