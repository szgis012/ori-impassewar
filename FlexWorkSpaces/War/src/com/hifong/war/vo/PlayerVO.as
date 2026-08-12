/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	import mx.collections.ArrayCollection;
	
	
	[RemoteClass(alias="com.war.domain.Player")]
	[Bindable]
	public class PlayerVO implements IValueObject {

	    /** 玩家编号 */
	    public var playerID:Number;
	    /** 用户编号 */
	    public var userID:Number;
	    /** 玩家名称 */
	    public var name:String;
	    /** 军衔编号 */
	    public var honorID:Number;
	    /** 军衔名称 */
	    public var honorName:String;
	    /** 工会编号 */
	    public var guildID:Number;
	    /** 国家:ContryTypeConstant中定义 */
	    public var country:Number;
	    /** 声望 */
	    public var renown:Number;
	    /** 进攻点数 */
	    public var attackPoint:Number;
	    /** 防御点数 */
	    public var defensePoint:Number;
	    /** 排名 */
	    public var rank:Number;
	    /** 玩家拥有的金钱数量*/
	    public var money:Number;
	    /** 玩家头像*/
	    public var headImg:String;
	    /** 创建时间 */
	    public var createTime:Date;
	    /** 城市信息 */
	    public var city:CityVO;
	    /** 工会名称 */
	    public var guildName:String;
	    /** 最后登陆时间*/
	    public var lastLoginTime:Date;
	    /** 登录次数*/
	    public var loginNum:int;
	    /** 在线时间(单位:分钟) */
    	public var onlineTime:int;
		/**玩家状态信息,在PlayerStateConstant中定义*/
		public var state:int;
		
		/** 是否领取每日奖励 */
		public var haveReceiveDailyreward:int;
		/** 礼金数量 */
		public var giftCertificate:int;
		//new add 模拟测试数据
		//指挥官数量
		public var officerNum:int;
		//城市坐标
		public var cityPosition:String;
		//sh城市人口
		public var cityPopulation:int;
		//城市建筑
		public var cityBuilding:ArrayCollection;//=new ArrayCollection([{city:"xxx",position:"10:20",type:"未知"},{ city:"AAA",position:"50:20",type:"未知"},{city:"bbb",position:"10:80",type:"未知" }]);
		
		//to do
		//要塞数量
		public var strongholdNum:int;//=0;
		
	}
}