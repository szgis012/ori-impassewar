/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.TreasureQueueDelegate;
	import com.hifong.war.events.treasure.GetTreasureQueueListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 获得城市的所有宝物效果进程列表
     *
     */
	public final class GetTreasureQueueListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetTreasureQueueListEvent = event as GetTreasureQueueListEvent;
			var delegate:TreasureQueueDelegate = new TreasureQueueDelegate( this );
			delegate.getTreasureQueueListByCityID(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.treasureQueueList = rs.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
