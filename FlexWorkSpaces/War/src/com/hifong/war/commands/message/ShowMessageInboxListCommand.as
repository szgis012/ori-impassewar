/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.ShowMessageInboxListEvent;
	import com.hifong.war.events.message.ShowMessageInboxPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	/**
	 * 获得消息收件箱列表
	 */	
	public final class ShowMessageInboxListCommand extends SequenceCommand implements ICommand, IResponder
	{
		
		public override function execute(event:CairngormEvent) : void
		{ 
			var evt:ShowMessageInboxListEvent = event as ShowMessageInboxListEvent;
			this.nextEvent = new ShowMessageInboxPageEvent(evt.playerID);
			
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.getMessageInboxList(evt.playerID,evt.start,evt.offset);
			this.executeNextCommand();
		}
		
		public function result(data:Object) : void
		{
				ModelLocator.getInstance().messageInboxList=data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}