/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.UpgradeGuildEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class UpgradeGuildCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:UpgradeGuildEvent = event as UpgradeGuildEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.upgradeGuild(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("军团升级成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}