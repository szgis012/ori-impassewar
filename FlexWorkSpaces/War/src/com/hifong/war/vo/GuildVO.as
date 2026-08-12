/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	import mx.collections.ArrayCollection;
	
	[RemoteClass(alias="com.war.domain.Guild")]
	[Bindable]
	public class GuildVO implements IValueObject {
		
		/** 工会编号 */
	    public var guildID:Number;
	    /** 工会名称 */
	    public var name:String;
	    /** 工会图片 */
	    public var image:String;
	    /** 创始人编号 */
	    public var chairmanID:Number;
	    /** 声望 */
	    public var renown:Number;
	    /** 排名 */
	    public var rank:Number;
	    /** 人数 */
	    public var population:Number;
	    /** 人数上限 */
	    public var populationMax:Number;
	    /** 工会介绍 */
	    public var introduction:String;
	    /** 工会公告 */
	    public var notice:String;
	    /** 创建时间 */
	    public var createTime:Date;
	    /** 创始人信息 */
	    public var chairman:PlayerVO;
	    /** 友好工会列表 */
	    public var friendlyGuildList:ArrayCollection;
	    /** 敌对工会列表 */
	    public var hostileGuildList:ArrayCollection;
	    /** 工会介绍描述(介绍拼接HTML完成的字符串) */
		public var introductionDescription:String;
		/** 工会公告描述(公告拼接HTML完成的字符串) */
		public var noticeDescription:String;
		/** 工会关系描述(关系拼接HTML完成的字符串) */
		public var relationshipDescription:String;
		
		/** 员官数量 */
		public var officialNum:int;
	
		/**军团等级 */
		public var level:int;	
		/** 军团财富*/
		public var money:int;
		/** 军团贡献*/
		public var contribution:int;
		
		/** 士官军旗数量 */
		public  var  oriflammeLowerNum:int;
		/** 校官军旗数量 */
		public  var oriflammeIntermediateNum:int;
		/** 元帅军旗数量 */
		public  var oriflammeAdvancedNum:int;	
		//to do
	    /** 中立工会列表 */
	    public var neutralGuildList:ArrayCollection;
	}
}