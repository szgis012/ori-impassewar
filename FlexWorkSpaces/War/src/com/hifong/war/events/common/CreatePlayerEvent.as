/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 玩家首次进入游戏创建角色的事件
     *
     */
	public final class CreatePlayerEvent extends CairngormEvent
	{
		public static const CREATEPLAYER_EVENT:String = "com.hifong.war.events.CreatePlayerEvent";
		
		/** 用户编号 */
		public var userID:int;
		/** 玩家姓名 */
		public var playerName:String;
		/** 城市名称 */
		public var cityName:String;
		/** 阵营 */
		public var contry:int;
		/** 玩家头像 */
		public var playerImg:String;
		/** 地图区域 */
		public var mapArea:int;
		
		public function CreatePlayerEvent(userID:int, playerName:String, cityName:String, contry:int, playerImg:String, mapArea:int) 
		{
			super( CREATEPLAYER_EVENT );
			this.userID = userID;
			this.playerName = playerName;
			this.cityName = cityName;
			this.contry =  contry;
			this.playerImg = playerImg;
			this.mapArea = mapArea;
		}
	}
}
