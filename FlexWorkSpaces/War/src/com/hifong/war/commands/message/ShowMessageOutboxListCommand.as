/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.message
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.MessageDelegate;
	import com.hifong.war.events.message.ShowMessageOutboxListEvent;
	import com.hifong.war.events.message.ShowMessageOutboxPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	
	/**
	 * 获得消息发件箱列表
	 */
	public final class ShowMessageOutboxListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowMessageOutboxListEvent = event as ShowMessageOutboxListEvent;
			this.nextEvent = new ShowMessageOutboxPageEvent(evt.playerID);
			
			var delegate:MessageDelegate = new MessageDelegate(this);
			delegate.getMessageOutboxList(evt.playerID,evt.start,evt.offset);
			this.executeNextCommand();
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().messageOutboxList = data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}