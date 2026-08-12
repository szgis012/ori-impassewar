/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.ProductionQueueDelegate;
	import com.hifong.war.constant.ProductionProcessTypeConstant;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
    /**
     *  处理获得城防建造进程列表事件
     *
     */
	public final class  GetDefenseProcessListCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetDefenseProcessListEvent = event as GetDefenseProcessListEvent;
			var delegate:ProductionQueueDelegate = new ProductionQueueDelegate( this );
			delegate.getProductionProcessList(model.cityInfo.cityID,ProductionProcessTypeConstant.PROCESS_BUILD_DEFENSE);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.cityDefenseInfo.defenseProcessList = rs.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
