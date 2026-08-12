/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.ShowMessageOutboxPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	/**
	 * 显示发件箱页数
	 */
	public final class ShowMessageOutboxPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowMessageOutboxPageEvent = event as ShowMessageOutboxPageEvent;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.getMessageOutboxAmount(evt.playerID);
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
	
			ModelLocator.getInstance().messageOutboxPage = pageNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}