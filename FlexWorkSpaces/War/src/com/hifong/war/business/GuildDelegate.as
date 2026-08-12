/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.hifong.war.vo.GuildPlayerVO;
	import com.hifong.war.vo.GuildVO;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	
	
	
    /**
     * 工会服务代理
     *
     */
	public final class GuildDelegate
	{
	    //远程调用返回时的处理对象
		private var responder:IResponder;
		
		//远程对象
		private var service:Object;
	
	
		public function GuildDelegate(responder:IResponder)
		{
			this.responder = responder;
			this.service = ServiceLocator.getInstance().getRemoteObject("guildService");
		}

		/**
		 * 创建工会
		 */
		public function createGuild(guild:GuildVO):void{
			var call:Object = service.createGuild(guild);
			call.addResponder(responder);
		}

		/**
		 * 获得工会信息
		 */
		public function getGuildInfo(guildID:int):void{
			var call:Object = service.getGuildByID(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会编号获得工会成员列表
		 */
		public function getGuildMemberListByGuildID(guildID:int):void{
			var call:Object = service.getGuildMemberList(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会成员列表
		 */
		public function getGuildMemberList(guildID:int,start:int,offset:int):void{
			var call:Object = service.getGuildMemeberPagingList(guildID,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会成员数量
		 */
		public function getGuildMemberAmount(guildID:int):void{
			var call:Object = service.getGuildMemberAmount(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会事件列表
		 */
		public function getGuildEventList(guildID:int,start:int,offset:int):void{
			var call:Object = service.getGuildEventPagingList(guildID,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会事件数量
		 */
		public function getGuildEventAmount(guildID:int):void{
			var call:Object = service.getGuildEventAmount(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会攻击列表
		 */
		public function getGuildAttackList(guildID:int,start:int,offset:int):void{
			var call:Object = service.getGuildAttackPagingList(guildID,start,offset);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会攻击数量
		 */
		public function getGuildAttackPage(guildID:int):void{
			var call:Object = service.getGuildAttackAmount(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会编号及玩家编号获得工会玩家信息
		 */
		public function getGuildPlayerByGuildIDAndPlayerID(guildID:int,playerID:int):void{
			var call:Object = service.getGuildPlayerByGuildIDAndPlayerID(guildID,playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 申请加入工会
		 */
		public function applyJoinGuild(guildID:int,playerID:int):void{
			var call:Object = service.applyJoinGuild(guildID,playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 取消申请加入工会
		 */
		public function cancelApplyJoinGuild(guildID:int,playerID:int):void{
			var call:Object = service.cancelApplyJoinGuild(guildID,playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 同意加入工会申请
		 */
		public function accpetPlayerJoinGuildApplication(playerID:int,guildID:int):void{
			var call:Object = service.accpetPlayerJoinGuildApplication(playerID,guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 拒绝加入工会申请
		 */
		public function refusePlayerJoinGuildApplication(playerID:int,guildID:int):void{
			var call:Object = service.refusePlayerJoinGuildApplication(playerID,guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 邀请加入工会
		 */
		public function inviteJoinGuild(guildID:int,playerName:String):void{
			var call:Object = service.inviteJoinGuild(guildID,playerName);
			call.addResponder(responder);
		}
		
		/**
		 * 取消邀请玩家
		 */
		public function cancelInvitePlayer(guildID:int,playerID:int):void{
			var call:Object = service.cancelInvitePlayer(guildID,playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 同意工会邀请
		 */
		public function acceptGuildInvitation(playerID:int,guildID:int):void{
			var call:Object = service.acceptGuildInvitation(playerID,guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 拒绝工会邀请
		 */
		public function refuseGuildInvitation(playerID:int,guildID:int):void{
			var call:Object = service.refuseGuildInvitation(playerID,guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 退出工会
		 */
		public function exitGuild(playerID:int):void{
			var call:Object = service.exitGuild(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 解散工会
		 */
		public function dismissGuild(guildID:int):void{
			var call:Object = service.dismissGuild(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 更新工会信息
		 */
		public function updateGuildInfo(guild:GuildVO):void{
			var call:Object = service.updateGuildInfo(guild);
			call.addResponder(responder);
		}
		
		/**
		 * 移除工会成员
		 */
		public function removeGuildPlayer(guildID:int,playerID:int):void{
			var call:Object = service.deleteGuildPlayerByGuildIDAndPlayerID(guildID,playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据玩家姓名移除工会成员
		 */
		public function removeGuildPlayerByPlayerName(guildID:int,playerName:String):void{
			var call:Object = service.deleteGuildPlayerByGuildIDAndPlayerName(guildID,playerName);
			call.addResponder(responder);
		}
		
		/**
		 * 获得玩家工会申请邀请列表
		 */
		public function getPlayerAppInvList(playerID:int):void{
			var call:Object = service.getPlayerAppInvList(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会申请邀请列表
		 */
		public function getGuildPlaAppInvList(guildID:int):void{
			var call:Object = service.getGuildPlaAppInvList(guildID);
			call.addResponder(responder);
		}
		
		/**
		 * 添加工会关系
		 */
		public function addGuildRelationship(guildID:int,targetGuildName:String,type:int):void{
			var call:Object = service.addGuildRelationship(guildID,targetGuildName,type);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会编号获得工会关系列表
		 */
		public function getGuildRelationshipListByGuildID(guildID:int):void{
			var call:Object = service.getGuildRelationshipListByGuildID(guildID);
			call.addResponder(responder);
		}

		/**
		 * 工会成员授权
		 */
		public function guildMemeberGrant(guildPlayer:GuildPlayerVO):void{
			var call:Object = service.guildMemeberGrant(guildPlayer);
			call.addResponder(responder);
		}
		
		/**
		 * 获得玩家工会编号及名称
		 */
		public function getPlayerGuildIDAndName(playerID:int):void{
			var call:Object = service.getPlayerGuildIDAndName(playerID);
			call.addResponder(responder);
		}
		
		/**
		 * 根据工会名称获得工会编号
		 */
		public function getGuildIDByGuildName(guildName:String):void{
			var call:Object = service.getGuildIDByGuildName(guildName);
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会数量
		 */
		public function getGuildNum():void{
			var call:Object = service.getGuildNum();
			call.addResponder(responder);
		}
		
		/**
		 * 获得工会分页列表
		 */
		public function getGuildPagingList(start:int,offset:int):void{
			var call:Object = service.getGuildPagingList(start,offset);
			call.addResponder(responder);
		}
		/**
		 * 获取工会所有支出明细列表
		 */
		 public function getAllGuildExpenseInfo(guildID:int):void{
		 	var call:AsyncToken=service.getAllGuildExpenseInfo(guildID);
		 	call.addResponder(responder);
		 }
		/**
		 * 获取工会所有收入明细列表
		 */
		 public function getAllGuildIncomeInfo(guildID:int):void{
		 	var call:AsyncToken=service.getAllGuildIncomeInfo(guildID);
		 	call.addResponder(responder);
		 }
		/**
		 * 军团成员捐献物资到军团
		 * @param guildID 	军团ID
		 * @param playerID	玩家ID
		 * @param money		金币数量
		 */
		 public function donateMoney(guildID:int,playerID:int,money:Number):void{
		 	var call:AsyncToken=service.donateMoney(guildID,playerID,money);
		 	call.addResponder(responder);
		 }
	/**
	 * 军团成员捐献军旗到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param oriflammeType		军旗类型
	 * @param oriflammeNum	军旗数量
	 */
	 public function donateOriflamme(guildID:int, playerID:int, oriflammeType:String, oriflammeNum:int):void{
	 	var call:AsyncToken=service.donateOriflamme(guildID,playerID,oriflammeType,oriflammeNum);
	 	call.addResponder(responder);
	 }
		/**
		 * 取得军团可研究科技列表
		 * @param guildID
	 	* @return list
		 */
		 public function getGuildTechnology(guildID:int):void{
	 		var call:AsyncToken=service.getGuildTechnology(guildID);
		 	call.addResponder(responder);
		 }
	/**
	 * 升级军团科技
	 * @param guildID
	 * @param technologyID
	 */	 
	 public function upgradeTechnology(guildID:int,technologyID:int):void{
	 	var call:AsyncToken=service.upgradeTechnology(guildID,technologyID);
	 	call.addResponder(responder);
	 }
	 /**
	 * 升级军团
	 * @param guildID 	军团ID
	 */
	 public function upgradeGuild(guildID:int):void{
	 	var call:AsyncToken=service.upgradeGuild(guildID);
	 	call.addResponder(responder); 
	 }
	 /**
	 * 取得军团收入总和或支出总和或玩家捐献总和
	 * 1：若playerID为null表示取得军团收入总和或支出总和（type不为null）
	 * 2：若playerID不为Null表示取得军团玩家捐献总和（type应为null）
	 * @param guildID
	 * @param guildPlayerID
	 * @param type
	 */
	 public function getTotalAlmsOfGuildMemberInGuild(guildID:int,guildPlayerID:int,type:int):void{ 
	 	var call:AsyncToken=service.getTotalAlmsOfGuildMemberInGuild(guildID,guildPlayerID,type); 
	 	call.addResponder(responder);
	 }
	 /**
	 * 移除军团关系
	 * @parm guildID
	 * @parm targetGuildName
	 */
	 public function removeGuildRelationship(guildID:int,targetGuildName:String):void{
	 	var call:AsyncToken=service.removeGuildRelationship(guildID,targetGuildName);
	 	call.addResponder(responder);
	 }
	 /**
	 * 领取军团补贴
	 * @param guildID
	 * @param playerID
	 */
	 public function receiveSubsidy(guildID:int,playerID:int):void{
	 	var call:AsyncToken=service.receiveSubsidy(guildID,playerID);
	 	call.addResponder(responder);
	 }
	}
}
