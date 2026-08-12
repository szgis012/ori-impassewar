/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowPlayerRankListByPlayerRankEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public final class ShowPlayerRankListByPlayerRankCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowPlayerRankListByPlayerRankEvent = event as ShowPlayerRankListByPlayerRankEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getPlayerRankListByRank(evt.rank);
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
			}
			ModelLocator.getInstance().playerRankList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}