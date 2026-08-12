/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildEventListEvent;
	import com.hifong.war.events.guild.ShowGuildEventPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;
	
	public final class ShowGuildEventListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildEventListEvent = event as ShowGuildEventListEvent;
			this.nextEvent = new ShowGuildEventPageEvent(evt.guildID);
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildEventList(evt.guildID,evt.start,evt.offset);
			this.executeNextCommand();
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
			}
			ModelLocator.getInstance().guildEventList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}

	}
}