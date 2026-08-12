/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.guild
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.GuildDelegate;
	import com.hifong.war.events.guild.InitGuildManageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.GuildPlayerVO;
	
	import mx.rpc.IResponder;

	public final class InitGuildManageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:InitGuildManageEvent = event as InitGuildManageEvent;
			var delegate:GuildDelegate = new GuildDelegate(this);
			delegate.getGuildPlayerByGuildIDAndPlayerID(evt.guildID,evt.playerID);
		}
		
		public function result(data:Object) : void
		{
			var guildPlayer:GuildPlayerVO = data.result as GuildPlayerVO;
			
			var model:ModelLocator = ModelLocator.getInstance();

			if(guildPlayer.permission.charAt(0)=='1'){
				//审核邀请成员
				model.manageGuildMemberAppInvPermission = true;
			}
			if(guildPlayer.permission.charAt(2)=='1'){
				//删除成员
				model.manageGuildMemberRemovePermission = true;
			}
			if(guildPlayer.permission.charAt(4)=='1'){
				//修改联盟信息
				model.manageGuildInfoPermission = true;
			}
			if(guildPlayer.permission.charAt(6)=='1'){
				//修改联盟关系
				model.manageGuildRelationshipPermission = true;
			}
			if(guildPlayer.permission.charAt(8)=="1"){
				//消息群发
				model.manageGuildMessagePermission=true;
			}
			if(guildPlayer.permission.charAt(10)=="1"){
				//官员辞职
				model.manageOfficerResignPermission=true;
			}
	
			if(model.guildVO.chairmanID==guildPlayer.playerID){
				//管理联盟官员(也是创建人）
				model.manageGuildOfficerPermission = true;
			}
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}