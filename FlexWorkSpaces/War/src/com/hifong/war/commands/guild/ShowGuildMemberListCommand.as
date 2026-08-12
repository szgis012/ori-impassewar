
/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildMemberListEvent;
	import com.hifong.war.events.guild.ShowGuildMemberPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.view.guild.GuildConfig;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public final class ShowGuildMemberListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildMemberListEvent = event as ShowGuildMemberListEvent;
			this.nextEvent = new ShowGuildMemberPageEvent(evt.guildID);
			
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildMemberList(evt.guildID,evt.start,evt.offset);
			this.executeNextCommand(); 
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
				//new add
				//记录自身军团信息
				if(int(array[i].player.playerID)==ModelLocator.getInstance().playerInfo.playerID){
					GuildConfig.GuildName=array[i].dutyName;
					GuildConfig.playerOriflammeArray=[array[i].oriflammeLowerNum,array[i].oriflammeIntermediateNum,array[i].oriflammeAdvancedNum];
				}
			}
			ModelLocator.getInstance().guildMemberList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}