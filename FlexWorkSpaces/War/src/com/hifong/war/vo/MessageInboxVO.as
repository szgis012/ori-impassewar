/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.MessageInbox")]
	[Bindable]
	public class MessageInboxVO implements IValueObject {
	
	/** 消息收件箱编号 */
	public var messageInboxID:int;
	/** 发送玩家名称 */
	public var senderName:String;
	/** 接收玩家编号 */
	public var receiverID:int;
	/** 标题 */
	public var title:String;
	/** 内容 */
	public var content:String;
	/** 已读标示(0.未读 1.已读) */
	public var readFlag:int;
	/** 接收时间 */
	public var receiveTime:Date;
		
	public var selected:Boolean=false;
	}
}