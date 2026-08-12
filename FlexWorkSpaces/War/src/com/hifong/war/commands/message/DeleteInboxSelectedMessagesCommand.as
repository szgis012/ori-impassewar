/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.DeleteInboxSelectedMessagesEvent;
	import com.hifong.war.events.message.ShowMessageInboxListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	/**
	 * 删除消息收件箱所选消息
	 */
	public final class DeleteInboxSelectedMessagesCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DeleteInboxSelectedMessagesEvent = event as DeleteInboxSelectedMessagesEvent;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.deleteMessagesInbox(evt.messageInboxIDs);
		}
		
		public function result(data:Object) : void
		{
			//更新收件箱列表
			var model:ModelLocator = ModelLocator.getInstance();
			var start:int = (model.currentInboxPageNum-1)*model.messagePageSize;
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowMessageInboxListEvent(model.playerInfo.playerID,start,model.messagePageSize));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}