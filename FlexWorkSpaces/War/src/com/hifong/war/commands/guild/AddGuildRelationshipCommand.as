/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.AddGuildRelationshipEvent;
	import com.hifong.war.events.guild.ShowGuildRelationshipListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class AddGuildRelationshipCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:AddGuildRelationshipEvent = event as AddGuildRelationshipEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.addGuildRelationship(evt.guildID,evt.targetGuildName,evt.targetType);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("添加工会关系成功"); 
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowGuildRelationshipListEvent(ModelLocator.getInstance().guildVO.guildID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}