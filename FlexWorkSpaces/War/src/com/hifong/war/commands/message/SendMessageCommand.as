/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.SendMessageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.MessageOutboxVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * 发送消息
	 */
	public final class SendMessageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:SendMessageEvent = event as SendMessageEvent;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.sendMessage(evt.message);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			var model:ModelLocator = ModelLocator.getInstance();
			var msg:MessageOutboxVO = model.sendMessageVO;
			msg.receiverName = "";
			msg.title = "";
			msg.content = "";
			
			MsgBox.showMessage("发送成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}
