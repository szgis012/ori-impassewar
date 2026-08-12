/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.ShowGuildInfoEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.GuildVO;
	
	import mx.rpc.IResponder;
	
	public final class ShowGuildInfoCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowGuildInfoEvent = event as ShowGuildInfoEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildInfo(evt.guildID);
		}
		
		public function result(data:Object) : void
		{
			var guild:GuildVO = data.result as GuildVO;
			
			//初始化联盟介绍
			var introduction:String = "";
			introduction += "<p align='center'><font color='#826B33'><b>联盟介绍</b></font></p>";
			introduction += "<p align='center'>───────────</p>";
			introduction += guild.introduction;
			guild.introductionDescription = introduction;
			
			//初始化联盟公告
			var notice:String = "";
			notice += "<p align='center'><font color='#826B33'><b>联盟公告</b></font></p>";
			notice += "<p align='center'>──────────────────────────</p>";
			notice += guild.notice;
			guild.noticeDescription = notice;
			
			//初始化联盟关系
			var relationship:String = "";
			relationship += "<p align='center'><font color='#826B33'><b>联盟外交</b></font></p>";
			relationship += "<p align='center'>───────────</p>";
			
			var i:int;
			
			relationship += "<p><font color='#5fce5f'><b>友好</b></font></p>";
			for(i=0;i<guild.friendlyGuildList.length;i++){
				relationship += "<p>   " + guild.friendlyGuildList[i].targetGuild.name + "</p>";
			}
			relationship += "<br>";

			/* relationship += "<p><font color='#bfce5f'><b>中立</b></font></p>";
			for(i=0;i<guild.neutralGuildList.length;i++){
				relationship += "<p>   " + guild.neutralGuildList[i].targetGuild.name + "</p>";
			} */
			relationship += "<br>";
	
			relationship += "<p><font color='#F07171'><b>敌对</b></font></p>";
			for(i=0;i<guild.hostileGuildList.length;i++){
				relationship += "<p>   " + guild.hostileGuildList[i].targetGuild.name + "</p>";
			}
			relationship += "<br>";
			guild.relationshipDescription = relationship;
			
			ModelLocator.getInstance().guildVO = guild;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}