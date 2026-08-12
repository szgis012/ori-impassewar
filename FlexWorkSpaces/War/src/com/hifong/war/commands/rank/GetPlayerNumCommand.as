/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.GetPlayerNumEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetPlayerNumCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetPlayerNumEvent = event as GetPlayerNumEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getPlayerNum();
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().rankPlayerPageNum = Math.ceil(data.result/ModelLocator.getInstance().rankPageSize);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}