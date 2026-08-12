/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildMemberGrantWindowEvent;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.util.WindowUtil;
	import com.hifong.war.view.guild.GuildGrantWindow;
	import com.hifong.war.vo.GuildPlayerVO;
	
	import flash.display.DisplayObject;
	
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;

	public final class ShowGuildMemberGrantWindowCommand implements ICommand, IResponder
	{

		private var parentDisplayObject:DisplayObject;

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildMemberGrantWindowEvent = event as ShowGuildMemberGrantWindowEvent;
			parentDisplayObject = evt.parentDisplayObject;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildPlayerByGuildIDAndPlayerID(evt.guildID,evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var win:GuildGrantWindow= new GuildGrantWindow();
			win.guildPlayer=data.result as GuildPlayerVO;
			WindowUtil.showModelWindow(win);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}