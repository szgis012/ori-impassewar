/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	/**
	 * 报告信息
	 */ 
	[RemoteClass(alias="com.war.domain.Report")]
	[Bindable]
	public class ReportVO implements IValueObject {
		/** 报告编号 */
	  	public var reportID:int;
	    /** 玩家编号，标识报告所属的玩家 */
	    public var playerID:int;
	    /** 标题 */
	    public var  title:String;
	    /** 报告内容 */
	    public var  content:String;
	    /** 报告类型 */
	    public var  type:int;
	    /** 已读标志：0 未读 1 已读 */
	    public var  readFlag:int;
	    /** 保存标志：0  未保存 1 已保存 */
	  	public var  saveFlag:int;
	    /** 接收报告的时间 */
	    public var receiveTime:Date;
	    
	    /** 为了在DataGrid中选择chckbox添加的属性*/
	    public var selected:Boolean = false;
	}
}