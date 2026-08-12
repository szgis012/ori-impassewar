/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ReceiveSubsidyEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 领取军团补贴
	 * @param guildID
	 * @param playerID
	 */
	public final class ReceiveSubsidyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ReceiveSubsidyEvent = event as ReceiveSubsidyEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.receiveSubsidy(evt.guildID,evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功领取军团补贴");	
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}