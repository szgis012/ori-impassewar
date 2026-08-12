/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetGuildTechnologyEvent;
	import com.hifong.war.events.guild.UpgradeTechnologyEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 升级军团科技
	 * @param guildID
	 * @param technologyID
	 */
	public final class UpgradeTechnologyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:UpgradeTechnologyEvent = event as UpgradeTechnologyEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.upgradeTechnology(evt.guildID,evt.technologyID);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("军团科技升级成功");
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetGuildTechnologyEvent(ModelLocator.getInstance().guildVO.guildID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}