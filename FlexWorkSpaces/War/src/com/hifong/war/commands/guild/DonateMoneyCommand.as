/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.guild.DonateMoneyEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	/**
	 * 军团成员捐献物资到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param money		金币数量
	 */
	public final class DonateMoneyCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:DonateMoneyEvent = event as DonateMoneyEvent;
			var delegate:GuildDelegate=new  GuildDelegate(this);
			delegate.donateMoney(evt.guildID,evt.playerID,evt.money);
		}
		
		public function result(data:Object) : void
		{
			MsgBox.showMessage("成功捐献金钱到军团。");
			CairngormEventDispatcher.getInstance().dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}