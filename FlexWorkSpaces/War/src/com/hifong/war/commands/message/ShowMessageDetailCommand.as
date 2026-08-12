/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.ShowMessageDetailEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.view.message.MessageInBoxDetailWindow;
	import com.hifong.war.view.message.MessageWindow;
	import com.hifong.war.vo.MessageInboxVO;
	
	import flash.display.DisplayObject;
	
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	
	public final class ShowMessageDetailCommand implements ICommand, IResponder
	{

		private var parentDisplayObject:DisplayObject;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowMessageDetailEvent = event as ShowMessageDetailEvent;
			this.parentDisplayObject = evt.parentDisplayObject;
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.getMessageDetail(evt.messageID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().messageDetailVO = data.result as MessageInboxVO;
			var messageDetailWindow:MessageInBoxDetailWindow = new MessageInBoxDetailWindow();
			messageDetailWindow.parentWindow = parentDisplayObject as MessageWindow;
			PopUpManager.addPopUp(messageDetailWindow,parentDisplayObject,false);
			PopUpManager.centerPopUp(messageDetailWindow);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}