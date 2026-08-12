/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.hifong.war.vo.MessageOutboxVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 消息服务代理
     *
     */
	public final class MessageDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function MessageDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("messageService");
		}
		
		
		/**
		 * 阅读消息
		 */
		public function readMessage(messageID:int):void{
			var call:Object = service.readMessage(messageID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得消息发件箱列表
		 */
		public function getMessageInboxList(playerID:int,start:int,offset:int):void{
			var call:Object = service.getInboxMessagePagingList(playerID,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得消息发件箱数量
		 */
		public function getMessageInboxAmount(playerID:int):void{
			var call:Object = service.getInboxMessageAmount(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得消息收件箱列表
		 */
		public function getMessageOutboxList(playerID:int,start:int,offset:int):void{
			var call:Object = service.getOutboxMessagePagingList(playerID,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得消息收件箱数量
		 */
		public function getMessageOutboxAmount(playerID:int):void{
			var call:Object = service.getOutboxMessageAmount(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得消息详细信息
		 */
		public function getMessageDetail(messageID:int):void{
			var call:Object = service.getMessageByID(messageID);
			call.addResponder(responder);
		}
		 
		/**
		 * 根据收件箱消息编号批量删除消息
		 */
		public function deleteMessagesInbox(messageInboxIDs:Array):void{
			var call:Object = service.deleteMessagesInbox(messageInboxIDs);
			call.addResponder(responder);
		}
		/**
		 * 根据发件箱消息编号批量删除消息
		 */
		public function deleteMessagesOutbox(messageOutboxIDs:Array):void{
			var call:Object = service.deleteMessagesOutbox(messageOutboxIDs);
			call.addResponder(responder);
		}
		
		
		 
		/**
		 * 发送消息
		 */
		public function sendMessage(msg:MessageOutboxVO):void{
			var call:Object = service.sendMessage(msg);
			call.addResponder(responder);
		}
	}
}
