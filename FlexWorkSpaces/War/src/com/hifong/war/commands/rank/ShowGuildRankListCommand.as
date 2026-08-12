/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.rank
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.RankDelegate;
	import com.hifong.war.events.rank.ShowGuildRankListByGuildRankEvent;
	import com.hifong.war.events.rank.ShowGuildRankListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowGuildRankListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildRankListEvent = event as ShowGuildRankListEvent;
			var delegate:RankDelegate = new RankDelegate(this);
			delegate.getGuildRankByGuildID(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			ModelLocator.getInstance().currentGuildRank = data.result;
			this.nextEvent = new ShowGuildRankListByGuildRankEvent(data.result as int);
			this.executeNextCommand();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}