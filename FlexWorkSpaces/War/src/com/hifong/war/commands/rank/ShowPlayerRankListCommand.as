/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowPlayerRankListByPlayerRankEvent;
	import com.hifong.war.events.rank.ShowPlayerRankListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowPlayerRankListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowPlayerRankListEvent = event as ShowPlayerRankListEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getPlayerRankByPlayerID(evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().currentPlayerRank = data.result;
			this.nextEvent = new ShowPlayerRankListByPlayerRankEvent(data.result as int);
			this.executeNextCommand();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}