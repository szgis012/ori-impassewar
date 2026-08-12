/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.colonization
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ColonizationDelegate;
	import com.hifong.war.events.colonization.ImposeEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ImposeCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ImposeEvent = event as ImposeEvent;
			var delegate:ColonizationDelegate = new ColonizationDelegate(this);
			delegate.impose(evt.colonizationID,evt.colonizeType);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("征收成功，详细信息请查看殖民征收报告。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}