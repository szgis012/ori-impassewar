/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.GuildPlayer")]
	[Bindable]
	public class GuildPlayerVO implements IValueObject {
		
		/** 工会编号 */
	    public var guildID:Number;
	    /** 玩家编号 */
	    public var playerID:Number;
	    /** 贡献度 */
	    public var contribution:Number;
	    /** 职务名称 */
	    public var dutyName:String;
	    /** 成员权限 */
	    public var permission:String;
	    /** 创建时间 */
	    public var createTime:Date;
	    /** 玩家信息 */
    	public var player:PlayerVO;
		
		/** 军团玩家编号 **/
		public var guildPlayerID:int;
		
		/** 元帅军旗数量 */
		public var oriflammeAdvancedNum:int;
		/** 士官军旗数量 */
		public var oriflammeLowerNum:int;
		/** 校官军旗数量 */
		public var  oriflammeIntermediateNum:int;
		 /** 最后领取补贴时间 */
		public var lastReceiveTime:Date;
		//to do  
		public var guildIncExpHistory:Object;
	}
}