/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
	
	
    /**
     * 地图服务代理
     *
     */
	public final class MapDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function MapDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("mapService");
		}
		
		
		/**
	     * 获得地图信息列表
	     * 
	     * @param startX 起始X坐标
	     * @param startY 起始Y坐标
	     * @param endX 结束X坐标
	     * @param endY 结束Y坐标
	     * @return ArrayCollection<MapVO>
	     */
	    public function loadMapData(startX:int,startY:int,endX:int,endY:int):void{
	    	var call:Object = service.getMapList(startX,startY,endX,endY);
			call.addResponder(responder);
	    }
	    /**
	    * @param mapXY 地图序列数组
	    * 根据请求的坐标点序列请求地图数组信息
	    */
	    public function getMapListByMapPosXYList(mapXY:ArrayCollection):void{
	    	var call:AsyncToken=service.getMapListByMapPosXYList(mapXY);
	    	call.addResponder(responder);
	    }
	/**
	 * 添加地图收藏信息
	 * @param favourite
	 */
	public function createMapFavourite(playerID:int, posX:int, posY:int):void{
		var call:AsyncToken=service.createMapFavourite(playerID,posX,posY);
	   	call.addResponder(responder);  
	}
	
	/**
	 * 根据编号删除地图收藏信息
	 * @param mapFavouriteID
	 */
	public function deleteMapFavourite(mapFavouriteID:int):void{
		var call:AsyncToken=service.deleteMapFavourite(mapFavouriteID);
	   	call.addResponder(responder);  
	}
	
	/**
	 * 根据玩家编号获得地图收藏信息列表
	 * @param playerID
	 * @return
	 */
	public function getMapFavouritePagingList(playerID:int, start:int, offset:int):void{
		var call:AsyncToken=service.getMapFavouritePagingList(playerID,start,offset);
	   	call.addResponder(responder);  
	}
	
	/**
	 * 根据玩家编号获得其地图收藏条数
	 * @param playerID
	 * @return
	 */
	public function getMapFavouriteNumOfPlayer(playerID:int):void{
		var call:AsyncToken=service.getMapFavouriteNumOfPlayer(playerID);
	   	call.addResponder(responder);  
	}	    
	}
}
