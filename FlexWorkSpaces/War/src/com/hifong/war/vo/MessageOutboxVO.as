/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.MessageOutbox")]
	[Bindable]
	public class MessageOutboxVO implements IValueObject {
	/** 消息发件箱编号 */
	public var  messageOutboxID:int;
	/** 发送玩家编号 */
	public var senderID:int;
	/** 接收玩家名称 */
	public var receiverName:String;
	/** 标题 */
	public var title:String;
	/** 内容 */
	public var content:String;
	/** 发送时间 */
	public var sendTime:Date;
	
	public var selected:Boolean=false;
	}
}