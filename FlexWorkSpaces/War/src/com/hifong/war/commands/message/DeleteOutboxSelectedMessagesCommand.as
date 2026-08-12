/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.DeleteOutboxSelectedMessagesEvent;
	import com.hifong.war.events.message.ShowMessageOutboxListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
	 * 删除消息发件箱所选消息
	 */
	public final class DeleteOutboxSelectedMessagesCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DeleteOutboxSelectedMessagesEvent = event as DeleteOutboxSelectedMessagesEvent;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.deleteMessagesOutbox(evt.messageOutboxIDs);
		}
		
		public function result(data:Object) : void
		{
			//更新发件箱列表
			var model:ModelLocator = ModelLocator.getInstance();
			var start:int = (model.currentOutboxPageNum-1)*model.messagePageSize;
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowMessageOutboxListEvent(model.playerInfo.playerID,start,model.messagePageSize));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}