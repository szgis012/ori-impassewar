/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.GetAllGuildExpenseInfoEvent;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
		/**
		 * 获取工会所有支出明细列表
		 */
	public final class GetAllGuildExpenseInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetAllGuildExpenseInfoEvent = event as GetAllGuildExpenseInfoEvent;
			var delegate:GuildDelegate=new GuildDelegate(this);
			delegate.getAllGuildExpenseInfo(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var ac:ArrayCollection=data.result as ArrayCollection;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}