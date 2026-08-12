/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.IResponder;
	
	
	
    /**
     * 宝物服务代理
     *
     */
	public final class TreasureDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function TreasureDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("treasureService");
		}
		
		 /**
	      * 得到指定类型的宝物列表(包含玩家拥有该宝物数量的信息)
	      * (使用宝物时调用该方法返回所有可以使用的宝物列表)
	      * @param playerID 玩家编号
	      * @param category 宝物分类，TreasureCategoryConstant中定义
	      * @param type 宝物类型，TreasureTypeConstant中定义
	      * @return List<Map<String,Object>>
	      */
	    public function getTreasureMapListByType(playerID:int,category:int,type:int):void{
	    	var call:Object = service.getTreasureMapListByType(playerID,category,type);
			call.addResponder(responder);
	    }
	    
	     /**
	      * 得到玩家所有属于给定分类的宝物信息(包含宝物的信息)
	      * (我的宝物界面显示时调用该接口)
	      * @param playerID 玩家编号
	      * @param category 宝物分类，TreasureCategoryConstant中定义
	      * @return List<Map<String,Object>>
	      */
	    public function getPlayerTreasureMapList(playerID:int,category:int):void{
	    	var call:Object = service.getPlayerTreasureMapList(playerID,category);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 根据类型获得玩家宝物列表
	     */
	    public function getPlayerTreasureListByCategory(playerID:int,category:int):void{
	    	var call:Object = service.getPlayerTreasureList(playerID,category);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 根据类型及类别获得玩家宝物列表
	     */
	    public function getPlayerTreasureList(playerID:int,category:int,type:int):void{
	    	var call:Object = service.getPlayerTreasureList(playerID,category,type);
			call.addResponder(responder);
	    }
		
		/**
	     * 获得推荐宝物列表
	     * @return
	     */
	   public function getRecommendTreasureList():void{
	   		var call:Object = service.getRecommendTreasureList();
			call.addResponder(responder);
	   }
	   
		/**
	     * 获得指定分类下的所有宝物列表
	     * (宝物商城使用)
	     * @param category 宝物分类，TreasureCategoryConstant中定义
	     * @return List<Treasure>
	     */
	    public function getTreasureListByCategory(category:int):void{
	    	var call:Object = service.getTreasureListByCategory(category);
			call.addResponder(responder);
	    }
	    
		//使用宝物
		public function useTreasure(playerID:int, treasureID:int,params:Object):void{
			var call:Object = service.useTreasure(playerID,treasureID,params);
			call.addResponder(responder);
		}
		
	    /**
	     * 购买宝物
	     * @param playerID 玩家编号
	     * @param treasureID 宝物编号
	     * @param num 宝物数量
	     */
	   public function  buyTreasure(playerID:int,treasureID:int,num:int):void{
	   		var call:Object = service.buyTreasure(playerID,treasureID,num);
			call.addResponder(responder);
	   }
	}
}
