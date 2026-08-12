/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.ShowMessageInboxPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	/**
	 * 显示收件箱页数
	 */
	public final class ShowMessageInboxPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowMessageInboxPageEvent = event as ShowMessageInboxPageEvent;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.getMessageInboxAmount(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var messageAmount:int = data.result as int;
			var pageSize:int = ModelLocator.getInstance().messagePageSize;
			var pageNum:int;
	
			if(messageAmount%pageSize!=0){
				pageNum = messageAmount/pageSize + 1;
			}else{
				pageNum = messageAmount/pageSize;
			}
	
			ModelLocator.getInstance().messageInboxPage = pageNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}