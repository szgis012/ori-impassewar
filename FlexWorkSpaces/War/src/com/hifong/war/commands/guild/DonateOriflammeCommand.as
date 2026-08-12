/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.DonateOriflammeEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 军团成员捐献军旗到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param oriflammeType		军旗类型
	 * @param oriflammeNum	军旗数量
	 */
	public final class DonateOriflammeCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DonateOriflammeEvent = event as DonateOriflammeEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.donateOriflamme(evt.guildID,evt.playerID,evt.oriflammeType,evt.oriflammeNum);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功捐献军旗到军团。");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}