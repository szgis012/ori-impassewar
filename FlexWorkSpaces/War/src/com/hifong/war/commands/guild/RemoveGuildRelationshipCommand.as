/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.RemoveGuildRelationshipEvent;
	import com.hifong.war.events.guild.ShowGuildRelationshipListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 移除军团关系
	 * @parm guildID
	 * @parm targetGuildName
	 */
	public final class RemoveGuildRelationshipCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:RemoveGuildRelationshipEvent = event as RemoveGuildRelationshipEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.removeGuildRelationship(evt.guildID,evt.targetGuildName);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功移除军团关系");
			CairngormEventDispatcher.getInstance().dispatchEvent(new ShowGuildRelationshipListEvent(ModelLocator.getInstance().guildVO.guildID));
		}
		 
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}