/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
    /**
     * 玩家服务代理
     *
     */
	public final class PlayerDelegate
	{
	
	
		public function PlayerDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("playerService");
		}
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
		 /**
	 * 接受好友申请
	 * @param playerID 执行审批的玩家编号
	 * @param targetPlayerID 接受审批的玩家编号
	 */
	public function acceptAddFriendApply(playerID:int, targetPlayerID:int):void{
		 	var call:AsyncToken=service.acceptAddFriendApply(playerID,targetPlayerID);
		 	call.addResponder(responder);
	}
		 /*=========================好友====================*/
		 /**
		 * 申请添加好友
		 * @param playerID 递交申请的玩家编号
		 * @param targetPlayerName 接受申请的玩家名称
		 */
		 public function applyAddFriend(playerID:int,targetPlayerName:String):void{
		 	var call:AsyncToken=service.applyAddFriend(playerID,targetPlayerName);
		 	call.addResponder(responder);
		 }

	    /**
	     * 创建角色
		 * userID 用户编号
		 * playerName 玩家姓名
		 * cityName 城市名称
		 * contry 阵营
		 * playerImg 玩家头像
		 * mapArea 地图区域
	     */ 
	    public function createPlayer(userID:int,playerName:String,cityName:String,contry:int,playerImg:String,mapArea:int):void{
	    	var call:Object = service.createPlayer(userID,playerName,cityName,contry,playerImg,mapArea);
			call.addResponder(responder);
	    }
	
	/**
	 * 删除好友
	 * @param friendID
	 */
	public function deleteFriend(playerID:int, targetPlayerID:int):void{
		 	var call:AsyncToken=service.deleteFriend(playerID,targetPlayerID);
		 	call.addResponder(responder);
	}
	/**
	 * 获得好友列表
	 * @param playerID
	 */
	public function getFriendList(playerID:int):void{
		var call:AsyncToken=service.getFriendList(playerID);
		call.addResponder(responder);
	}
	
	/**
	 * 获得好友数目
	 * @param playerID
	 * @return
	 */
	public function getFriendNum(playerID:int):void{
		var call:AsyncToken=service.getFriendNum(playerID);
		call.addResponder(responder);
	}
		
		/** 
		 * 根据用户编号获得玩家
	    * @param userID 用户编号
	    * @return
	    */
	    public function getPlayerByUserID(userID:int):void{
	    	var call:Object = service.getPlayerByUserID(userID);
			call.addResponder(responder);
	    }
	    
	    /** 
		 * 根据玩家编号获得玩家
	    * @param playerID 玩家编号
	    * @return
	    */
	    public function getPlayerInfo(playerID:int):void{
	    	var call:Object = service.getPlayerInfo(playerID);
			call.addResponder(responder);
	    }
	    
	    /**
	     * 根据玩家编号获得玩家信息
	     */
	    public function getPlayerInfoByID(playerID:int):void{
	    	var call:Object = service.getPlayerInfo(playerID);
			call.addResponder(responder);
	    }

		/**
		 * 判断玩家名是否存在
		 */
		public function isPlayerNameExisted(playerName:String):void{
			var call:Object = service.isPlayerNameExisted(playerName);
			call.addResponder(responder);
		}
		
		/**
		 * 加载游戏信息(包含玩家，城市等信息，客户端每分钟调用刷新客户端数据)
		 */
		public function loadGameInfo(playerID:int, cityID:int):void{
			var call:Object = service.loadGameInfo(playerID, cityID);
			call.addResponder(responder);
		}
	    
	    /**
	     * 加载用户基础数据(登陆时使用)
	     * @param userID 用户编号
	     * @return
	     */
	    public function loadPlayerGlobalData(userID:int):void{
	    	var call:Object = service.loadPlayerGlobalData(userID);
			call.addResponder(responder);
	    }
		/**
		 * 获取玩家每日奖励信息
		 */
		 public function receiveDailyReward(playerID:int):void{
		 	var call:AsyncToken=service.receiveDailyReward(playerID);
		 	call.addResponder(responder);
		 }
	
	/**
	 * 拒绝好友申请
	 * @param playerID 执行拒绝的玩家编号
	 * @param targetPlayerID 被拒绝的玩家编号
	 */
		public function refuseAddFriendApply(playerID:int, targetPlayerID:int):void{
		 	var call:AsyncToken=service.refuseAddFriendApply(playerID,targetPlayerID);
		 	call.addResponder(responder);
		}
	}
}
