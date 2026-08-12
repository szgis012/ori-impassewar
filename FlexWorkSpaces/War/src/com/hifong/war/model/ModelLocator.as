/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.model
{
	import com.adobe.cairngorm.CairngormError;
	import com.adobe.cairngorm.CairngormMessageCodes;
	import com.adobe.cairngorm.model.IModelLocator;
	import com.hifong.war.common.ArmyInfo;
	import com.hifong.war.common.BuildingInfo;
	import com.hifong.war.common.CityDefenseInfo;
	import com.hifong.war.common.GlobalTimer;
	import com.hifong.war.common.OrdnanceInfo;
	import com.hifong.war.common.ReportInfo;
	import com.hifong.war.common.WorldInfo;
	import com.hifong.war.view.assets.ICountryRelatedAssets;
	import com.hifong.war.view.battle.BattleWindow;
	import com.hifong.war.view.building.BuildingGrid;
	import com.hifong.war.view.treasure.UseTreasureWindow;
	import com.hifong.war.vo.CityHeroVO;
	import com.hifong.war.vo.CityVO;
	import com.hifong.war.vo.GuildVO;
	import com.hifong.war.vo.MessageInboxVO;
	import com.hifong.war.vo.MessageOutboxVO;
	import com.hifong.war.vo.PlayerVO;
	import com.hifong.war.vo.ProcessQueueVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	
    [Bindable]
	public final class ModelLocator implements IModelLocator
	{

		public var SERVER_IP:String;
		public var SERVER_ENDPOINT:String;

		public var GAME_SOCKET_PORT:Number = 19393;
		public var BATTLE_SOCKET_PORT:Number = 29292;
		
		public var LOGIN_WEBSERVICE_WSDL:String;
		
		//全局计时器
		public var globalTimer:GlobalTimer;
		//服务器端时间
		public var serverTime:Date
		
		
		/**对主窗口的引用*/
		public var app:War;
		/** 登陆窗口的引用*/
		public var loginWindow:LoginWindow;
		/** 玩家首次登陆游戏时出现的创建角色窗口引用*/
		public var createPlayerWindow:CreatePlayerWindow;
		/** 战斗窗口引用 */
		public var battleWindow:BattleWindow;
		/** 世界面板的引用*/
		public var worldPanel:WorldPanel;
		/** 当前遮罩Canvas */
		public var maskCanvas:Canvas;
		
		/**阵营相关的图形资源*/
		public var countryRelatedAssets:ICountryRelatedAssets;
		
		/** 玩家信息 */
		public var playerInfo:PlayerVO;
		/** 城市信息 */
		public var cityInfo:CityVO;
		/** 军队信息  */
		public var armyInfo:ArmyInfo;
		/** 目标玩家信息(查看玩家信息) */
		public var targetPlayerInfo:PlayerVO;
		/** 是否有新报告 */
		public var haveNewReport:Boolean;
		/** 是否有新消息 */
		public var haveNewMessage:Boolean;
		/** 是否有新战斗信息 */
		public var haveNewBattle:Boolean;
		/** 是否有已完成任务 */
		public var haveCompletedTask:Boolean;
		
		/** 殖民列表 */
		public var colonizationList:ArrayCollection;
		
		/** 要塞列表 */
		public var strongholdList:ArrayCollection;
		
		/** 工会分页大小 */
		public const guildPageSize:int = 10;
		/** 消息分页大小 */
		public const messagePageSize:int = 10;
		/** 排名分页大小 */
		public const rankPageSize:int = 10;
		/** 市场分页大小 */
		public const marketPageSize:int = 10;
		
		
		
		/** 商人速度(单位:秒) */
		public const businessmanSpeed:int = 60;
		
		/** 当前建筑 */
		public var currentBuindingGrid:BuildingGrid;
		
		
		/** 工会信息 */
		public var guildVO:GuildVO;
		/** 工会成员列表 */
		public var guildMemberList:ArrayCollection;
		//new add
		/** 工会成员捐献情况 */
		public var guildMenberContributeList:ArrayCollection;
		/** 工会可研究科技列表*/
		public var guildTechnologyList:ArrayCollection;
		//new add end
		/** 工会成员页数 */
		public var guildMemberPage:int;
		/** 工会事件列表 */
		public var guildEventList:ArrayCollection;
		/** 工会事件页数 */
		public var guildEventPage:int;
		/** 工会攻击列表 */
		public var guildAttackList:ArrayCollection;
		/** 工会攻击页数 */
		public var guildAttackPage:int;
		/** 工会成员申请邀请列表 */
		public var guildPlayerAppInvList:ArrayCollection;
		/** 工会成员移除列表 */
		public var guildMemberRemoveList:ArrayCollection;
		/** 工会关系列表 */
		public var guildRelationshipList:ArrayCollection;
		/** 工会成员授权列表 */
		public var guildMemberGrantList:ArrayCollection;
		/** 工会成员授权页数 */
		public var guildMemberGrantPage:int;
		/** 管理公会信息权限 */
		public var manageGuildInfoPermission:Boolean = false;
		/** 管理工会成员审核邀请权限 */
		public var manageGuildMemberAppInvPermission:Boolean = false;
		/** 管理工会成员移除权限 */
		public var manageGuildMemberRemovePermission:Boolean = false;
		/** 管理工会外交权限 */
		public var manageGuildRelationshipPermission:Boolean = false;
		/** 管理军团消息*/
		public var manageGuildMessagePermission:Boolean=false;
		/** 官员辞职*/
		public var manageOfficerResignPermission:Boolean=false;
		/** 关系工会官员权限(创建人，团长) */
		public var manageGuildOfficerPermission:Boolean = false;
		/** 工会列表页数 */
		public var guildPage:int;
		/** 工会列表 */
		public var guildList:ArrayCollection;

		/** 玩家工会申请邀请列表 */
		public var playerGuildAppInvList:ArrayCollection;
		
		/** 收件箱列表 */
		public var messageInboxList:ArrayCollection;
		/** 收件箱页数 */
		public var messageInboxPage:int;
		/** 发件箱列表 */
		public var messageOutboxList:ArrayCollection;
		/** 发件箱页数 */
		public var messageOutboxPage:int;
		/** 收件详细信息 */
		public var messageDetailVO:MessageInboxVO;
		/** 当前收件箱页数 */
		public var currentInboxPageNum:int;
		/** 当前发件箱页数 */
		public var currentOutboxPageNum:int;
		/** 要发送的信息 */
		public var sendMessageVO:MessageOutboxVO;
		
		/** 玩家排名列表 */		
		public var playerRankList:ArrayCollection;
		/** 当前玩家排名 */
		public var currentPlayerRank:int;
		/** 工会排名列表 */		
		public var guildRankList:ArrayCollection;
		/** 当前工会排名 */
		public var currentGuildRank:int;
		/** 城市建筑点数排名列表 */		
		public var cityConstructionPointRankList:ArrayCollection;
		/** 当前城市建筑点数排名 */
		public var currentCityConstructionPointRank:int;
		/** 城市科技点数排名列表 */		
		public var cityTechnologyPointRankList:ArrayCollection;
		/** 当前城市科技点数排名 */
		public var currentCityTechnologyPointRank:int;
		/** 城市人口排名列表 */		
		public var cityPopulationRankList:ArrayCollection;
		/** 当前城市人口排名 */
		public var currentCityPopulationRank:int;
		/** 排名玩家数量 */
		public var rankPlayerPageNum:int;
		/** 排名工会数量 */
		public var rankGuildPageNum:int;
		/** 排名城市数量 */
		public var rankCityPageNum:int;
		
		/** 资源类型 */
		public var resourceType:Array = new Array({label:"木材",data:"1"},{label:"钢铁",data:"2"},{label:"石油",data:"3"},{label:"食物",data:"4"});
		/** 过滤资源类型 */
		public var filterResourceType:Array = new Array({label:"全部",data:"0"},{label:"木材",data:"1"},{label:"钢铁",data:"2"},{label:"石油",data:"3"},{label:"食物",data:"4"});
		/** 资源销售列表 */
		public var resourceSalesList:ArrayCollection;
		/** 资源销售列表页数 */
		public var resourceSalesPage:int;
		/** 城市资源销售(挂单)列表 */
		public var cityResourceSalesList:ArrayCollection;
		/** 城市交易中资源交易列表 */
		public var cityTradeQueueList:ArrayCollection;
		
		/**宝物列表(其中为TreasureItemVO)*/
		public var treasureList:ArrayCollection;
		/** 商场宝物 列表（其中为TreasureItemVO)*/
		public var treasureShopList:ArrayCollection;
		/** 当前正在使用的宝物窗口引用 */
		public var currentUseTreasure:UseTreasureWindow;
	
		/**玩家拥有的任务列表*/
		public var taskList:ArrayCollection;
		
		/**城市已有的建筑列表,其中元素为CityBuilding*/
		public var cityBuildingList:ArrayCollection;
		
		/**可建造建筑列表,其中元素为Building*/
		public var buildableBuildingList:ArrayCollection;
		
		/** 最后一次城镇管理的时间,10分钟的间隔*/
		public var lastCityManageTime:Date;
		
		/** 征召市民的进程(ProductionProcessVO)*/
		public var enlistCitizenProcess:ProductionQueueVO;
		
		/** 军械相关信息 */
		public var ordnanceInfo:OrdnanceInfo;
		
		/** 建筑相关信息 */
		public var buildingInfo:BuildingInfo;

		/** 是否有科技正在研究 */
		public var haveTechnologyResearching:Boolean;
		/** 当前研究科技描述 */
		public var currentResearchingTechnology:String;
		/** 当前科技研究进程 */
		public var techProcess:ProcessQueueVO;
		/** 当前科技研究客户端进程 */
//		public var techClientProcess:ClientProcess;
		/** 科技列表 */
		public var technologyList:ArrayCollection;
		
		
		/** 城市候选英雄列表 */
		public var cityCandidacyHeroList:ArrayCollection;
		/** 城市英雄列表 */
		public var cityHeroList:ArrayCollection;
		/** 城市英雄Map,key=英雄编号，value=CityHero */
		public var cityHeroMap:Object;
		/** 空闲城市英雄列表 */
		public var freeCityHeroList:ArrayCollection;
		/** 当前英雄信息 */
		public var currentCityHero:CityHeroVO;
		
		
		/** 城市军队列表 */
		public var cityMilitaryList:ArrayCollection;
		
		public var worldInfo:WorldInfo;
		
		/** 城市战斗列表 */
		public var cityBattleList:ArrayCollection;
		/** 军事行动列表 */
		public var cityMilitaryActionList:ArrayCollection;
		/** 敌情警报列表 */
		public var cityMilitaryDefenseList:ArrayCollection;
		/**军事行动详细信息*/
		public var militaryActionDetail:Object;
		
		/** 战斗信息 */
		public var battleInfo:Object;
		/** 报告信息*/
		public var reportInfo:ReportInfo;
		
		/** 对外宣战信息，列表中的元素为DeclareWarVO*/
		public var declareWarList:ArrayCollection;
		
		/**城防信息*/
		public var cityDefenseInfo:CityDefenseInfo;
		
		/** 宝物效果队列*/
		public var treasureQueueList:ArrayCollection;
		
		/** 装备列表(英雄) */
		public var equipmentList_Hero:ArrayCollection;
		/** 道具列表(英雄) */
		public var itemList_hero:ArrayCollection;
		
		/** 技能列表 */
		public var skillList:ArrayCollection;
		
		
		/***************** Stronghold start here ********************/
		
		/** 要塞可建造建筑列表，其中元素为ShbuildingVO*/
		public var strongholdBuildableBuildingList:ArrayCollection = new ArrayCollection();
		
		/** 要塞已有的建筑列表，其中元素为StrongholdShbuildingVO*/
		public var strongholdBuildingList:ArrayCollection = new ArrayCollection();
		
		
		private static var instance:ModelLocator;

			/***************** 伤兵 start here ********************/
		public var woundArmyList:ArrayCollection;
		
		/**地图收藏*/
		public var mapCollectionList:ArrayCollection;
		/** 地图收藏条数*/
		public var mapCollectionNum:int;
		/** 地图收藏每页条数 */
		public const mapCollectionPageSize:int=8;
		/** 好友 */
		public var friendList:ArrayCollection;
		
		public function ModelLocator(access:Private)
		{
			if ( access == null )
			{
			    throw new CairngormError( CairngormMessageCodes.SINGLETON_EXCEPTION, "ModelLocator" );
			}
			instance = this;
			
			sendMessageVO = new MessageOutboxVO();
			
			cityBuildingList = new ArrayCollection();
			
			armyInfo = new ArmyInfo();
			
			ordnanceInfo = new OrdnanceInfo();
			
			buildingInfo = new BuildingInfo();
			
			worldInfo = new WorldInfo();
			
			reportInfo = new ReportInfo();
			
			cityDefenseInfo = new CityDefenseInfo();
			
		}
		
		public static function getInstance() : ModelLocator
		{
			if ( instance == null )
			{
				instance = new ModelLocator( new Private() );
			}
			return instance;
		}
	}
}

class Private {}
