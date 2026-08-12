/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.control
{
	import com.adobe.cairngorm.control.FrontController;
	import com.hifong.war.commands.battle.*;
	import com.hifong.war.commands.building.*;
	import com.hifong.war.commands.building.airport.*;
	import com.hifong.war.commands.building.armory.*;
	import com.hifong.war.commands.building.barracks.*;
	import com.hifong.war.commands.building.citycenter.*;
	import com.hifong.war.commands.building.commandcenter.*;
	import com.hifong.war.commands.building.defense.*;
	import com.hifong.war.commands.building.heavyfactory.*;
	import com.hifong.war.commands.building.lumber.*;
	import com.hifong.war.commands.building.market.*;
	import com.hifong.war.commands.building.militarycollege.*;
	import com.hifong.war.commands.building.mill.*;
	import com.hifong.war.commands.building.oilfield.*;
	import com.hifong.war.commands.building.steel.*;
	import com.hifong.war.commands.building.techcenter.*;
	import com.hifong.war.commands.colonization.*;
	import com.hifong.war.commands.common.*;
	import com.hifong.war.commands.equipment.*;
	import com.hifong.war.commands.friend.AcceptAddFriendApplyCommand;
	import com.hifong.war.commands.friend.ApplyAddFriendCommand;
	import com.hifong.war.commands.friend.DeleteFriendCommand;
	import com.hifong.war.commands.friend.GetFriendListCommand;
	import com.hifong.war.commands.friend.GetFriendNumCommand;
	import com.hifong.war.commands.friend.RefuseAddFriendApplyCommand;
	import com.hifong.war.commands.guild.*;
	import com.hifong.war.commands.message.*;
	import com.hifong.war.commands.military.*;
	import com.hifong.war.commands.player.*;
	import com.hifong.war.commands.rank.*;
	import com.hifong.war.commands.report.*;
	import com.hifong.war.commands.stat.*;
	import com.hifong.war.commands.stronghold.*;
	import com.hifong.war.commands.task.*;
	import com.hifong.war.commands.treasure.*;
	import com.hifong.war.commands.world.*;
	import com.hifong.war.events.battle.*;
	import com.hifong.war.events.building.*;
	import com.hifong.war.events.building.airport.*;
	import com.hifong.war.events.building.armory.*;
	import com.hifong.war.events.building.barracks.*;
	import com.hifong.war.events.building.citycenter.*;
	import com.hifong.war.events.building.commandcenter.*;
	import com.hifong.war.events.building.defense.*;
	import com.hifong.war.events.building.heavyfactory.*;
	import com.hifong.war.events.building.lumber.*;
	import com.hifong.war.events.building.market.*;
	import com.hifong.war.events.building.militarycollege.*;
	import com.hifong.war.events.building.mill.*;
	import com.hifong.war.events.building.oilfield.*;
	import com.hifong.war.events.building.steel.*;
	import com.hifong.war.events.building.techcenter.*;
	import com.hifong.war.events.colonization.*;
	import com.hifong.war.events.common.*;
	import com.hifong.war.events.equipment.*;
	import com.hifong.war.events.friend.AcceptAddFriendApplyEvent;
	import com.hifong.war.events.friend.ApplyAddFriendEvent;
	import com.hifong.war.events.friend.DeleteFriendEvent;
	import com.hifong.war.events.friend.GetFriendListEvent;
	import com.hifong.war.events.friend.GetFriendNumEvent;
	import com.hifong.war.events.friend.RefuseAddFriendApplyEvent;
	import com.hifong.war.events.guild.*;
	import com.hifong.war.events.message.*;
	import com.hifong.war.events.military.*;
	import com.hifong.war.events.player.*;
	import com.hifong.war.events.rank.*;
	import com.hifong.war.events.report.*;
	import com.hifong.war.events.stat.*;
	import com.hifong.war.events.stronghold.*;
	import com.hifong.war.events.task.*;
	import com.hifong.war.events.treasure.*;
	import com.hifong.war.events.world.*;
	
    
    /**
     * 对系统caringorm 事件,命令 进行统一管理
     *
     */
	public final class WarController extends FrontController
	{
		public function WarController()
		{
			this.initialize();
		}
		

		private function initialize() : void
		{
			//使用宝物
		    this.addCommand( UseTreasureEvent.USETREASURE_EVENT, UseTreasureCommand );
		    //获得任务列表信息
		    this.addCommand( GetTaskListEvent.GETTASKLIST_EVENT, GetTaskListCommand );
		    //领取任务奖励
		    this.addCommand( ReceiveRewardEvent.RECEIVEREWARD_EVENT, ReceiveRewardCommand );
		    // 处理获得城市已有的建筑列表事�?
		    this.addCommand( GetCompletedBuildingListEvent.GETCOMPLETEDBUILDINGLIST_EVENT, GetCompletedBuildingListCommand );
		    //处理获取可建造建筑列表事�?
		    this.addCommand( GetBuildableBuildingListEvent.GETBUILDABLEBUILDINGLIST_EVENT, GetBuildableBuildingListCommand );
		    //处理建筑升级事件
		    this.addCommand( UpgradeBuildiingEvent.UPGRADEBUILDIING_EVENT, UpgradeBuildiingCommand );
		    //处理建筑拆除事件
		    this.addCommand( BackoutBuildingEvent.BACKOUTBUILDING_EVENT, BackoutBuildingCommand );
		    //处理建筑建�?事件
		    this.addCommand( BuildBuildingEvent.BUILDBUILDING_EVENT, BuildBuildingCommand );
		    //处理客户端完成建造，升级，拆除的计时时的事件
		    this.addCommand( ClientProcessFinishedEvent.CLIENTPROCESSFINISHED_EVENT, ClientProcessFinishedCommand );
		    //处理刷新CityBuilding信息的事�?
		    this.addCommand( RefreshCityBuildingEvent.REFRESHCITYBUILDING_EVENT, RefreshCityBuildingCommand );
		    // 处理建�?，升级，拆除取消事件
		    this.addCommand( CancelProcessEvent.CANCELPROCESS_EVENT, CancelProcessCommand );
		    //获得消息收件箱列�?
		    this.addCommand( ShowMessageInboxListEvent.GETMESSAGEINBOXLIST_EVENT, ShowMessageInboxListCommand );
		    // 获得消息发件箱列�?
		    this.addCommand( ShowMessageOutboxListEvent.GETMESSAGEOUTBOXLIST_EVENT, ShowMessageOutboxListCommand );
		    //
		    this.addCommand( ShowMessageDetailEvent.SHOWMESSAGEDETAIL_EVENT, ShowMessageDetailCommand );
		    //发�?消息
		    this.addCommand( SendMessageEvent.SENDMESSAGE_EVENT, SendMessageCommand );
		    //删除消息收件箱所选消�?
		    this.addCommand( DeleteInboxSelectedMessagesEvent.DELETEINBOXSELECTEDMESSAGES_EVENT, DeleteInboxSelectedMessagesCommand );
		    //删除消息发件箱所选消�?
		    this.addCommand( DeleteOutboxSelectedMessagesEvent.DELETEOUTBOXSELECTEDMESSAGES_EVENT, DeleteOutboxSelectedMessagesCommand );
//		    this.addCommand( DeleteMessageEvent.DELETEMESSAGE_EVENT, DeleteMessageCommand );
		    //显示收件箱页�?
		    this.addCommand( ShowMessageInboxPageEvent.SHOWMESSAGEINBOXPAGE_EVENT, ShowMessageInboxPageCommand );
		    // 显示发件箱页�?
		    this.addCommand( ShowMessageOutboxPageEvent.SHOWMESSAGEOUTBOXPAGE_EVENT, ShowMessageOutboxPageCommand );
		    //
		    this.addCommand( ShowGuildInfoEvent.SHOWGUILDINFO_EVENT, ShowGuildInfoCommand );
		    //
		    this.addCommand( ShowGuildMemberListEvent.SHOWGUILDMEMBERLIST_EVENT, ShowGuildMemberListCommand );
		    //
		    this.addCommand( ShowGuildMemberPageEvent.SHOWGUILDMEMBERPAGE_EVENT, ShowGuildMemberPageCommand );
		    //
		    this.addCommand( ShowGuildEventListEvent.SHOWGUILDEVENTLIST_EVENT, ShowGuildEventListCommand );
		    //
		    this.addCommand( ShowGuildEventPageEvent.SHOWGUILDEVENTPAGE_EVENT, ShowGuildEventPageCommand );
		    //
		    this.addCommand( ShowGuildAttackListEvent.SHOWGUILDATTACKLIST_EVENT, ShowGuildAttackListCommand );
		    //
		    this.addCommand( ShowGuildAttackPageEvent.SHOWGUILDATTACKPAGE_EVENT, ShowGuildAttackPageCommand );
		    //
		    this.addCommand( InitGuildManageEvent.INITGUILDMANAGE_EVENT, InitGuildManageCommand );
		    //
		    this.addCommand( ShowGuildInfo_ManageEvent.SHOWGUILDINFO_MANAGE_EVENT, ShowGuildInfo_ManageCommand );
		    //
		    this.addCommand( UpdateGuildInfoEvent.UPDATEGUILDINFO_EVENT, UpdateGuildInfoCommand );
		    //
		    this.addCommand( DismissGuildEvent.DISMISSGUILD_EVENT, DismissGuildCommand );
		    
		    this.addCommand( InviteJoinGuildEvent.INVITEJOINGUILD_EVENT, InviteJoinGuildCommand );
		    this.addCommand( ShowGuildPlayerAppInvListEvent.SHOWGUILDPLAYERAPPINVLIST_EVENT, ShowGuildPlayerAppInvListCommand );
		    this.addCommand( CancelInvitePlayerEvent.CANCELINVITEPLAYER_EVENT, CancelInvitePlayerCommand );
		    this.addCommand( AddGuildRelationshipEvent.ADDGUILDRELATIONSHIP_EVENT, AddGuildRelationshipCommand );
		    this.addCommand( ShowGuildMemberGrantListEvent.SHOWGUILDMEMBERGRANTLIST_EVENT, ShowGuildMemberGrantListCommand );
		    this.addCommand( ShowGuildMemberGrantWindowEvent.SHOWGUILDMEMBERGRANTWINDOW_EVENT, ShowGuildMemberGrantWindowCommand );
		    this.addCommand( GuildMemberGrantEvent.GUILDMEMBERGRANT_EVENT, GuildMemberGrantCommand );
		    this.addCommand( ShowPlayerRankListEvent.SHOWPLAYERRANKLIST_STEP1_EVENT, ShowPlayerRankListCommand );
		    this.addCommand( ShowPlayerRankListByPlayerRankEvent.SHOWPLAYERRANKLISTBYPLAYERRANK_EVENT, ShowPlayerRankListByPlayerRankCommand );
		    this.addCommand( ShowPlayerRankListByPlayerNameEvent.SHOWPLAYERRANKLISTBYPLAYERNAME_STEP1_EVENT, ShowPlayerRankListByPlayerNameCommand );
		    this.addCommand( ShowGuildRankListByGuildNameEvent.SHOWGUILDRANKLISTBYGUILDNAME_EVENT, ShowGuildRankListByGuildNameCommand );
		    this.addCommand( ShowGuildRankListByGuildRankEvent.SHOWGUILDRANKLISTBYGUILDRANK_EVENT, ShowGuildRankListByGuildRankCommand );
		    this.addCommand( ShowGuildRankListEvent.SHOWGUILDRANKLIST_EVENT, ShowGuildRankListCommand );
		    this.addCommand( ShowCityConstructionPointRankListEvent.SHOWCITYCONSTRUCTIONPOINTRANKLIST_EVENT, ShowCityConstructionPointRankListCommand );
		    this.addCommand( ShowCityConstructionPointRankListByCityRankEvent.SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYRANK_EVENT, ShowCityConstructionPointRankListByCityRankCommand );
		    this.addCommand( ShowCityConstructionPointRankListByCityNameEvent.SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYNAME_EVENT, ShowCityConstructionPointRankListByCityNameCommand );
		    this.addCommand( ShowCityTechnologyPointRankListEvent.SHOWCITYTECHNOLOGYPOINTRANKLIST_EVENT, ShowCityTechnologyPointRankListCommand );
		    this.addCommand( ShowCityTechnologyPointRankListByCityRankEvent.SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYRANK_EVENT, ShowCityTechnologyPointRankListByCityRankCommand );
		    this.addCommand( ShowCityTechnologyPointRankListByCityNameEvent.SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYNAME_EVENT, ShowCityTechnologyPointRankListByCityNameCommand );
		    this.addCommand( ShowCityPopulationRankListEvent.SHOWCITYPOPULATIONRANKLIST_EVENT, ShowCityPopulationRankListCommand );
		    this.addCommand( ShowCityPopulationRankListByCityRankEvent.SHOWCITYPOPULATIONRANKLISTBYCITYRANK_EVENT, ShowCityPopulationRankListByCityRankCommand );
		    this.addCommand( ShowCityPopulationRankListByCityNameEvent.SHOWCITYPOPULATIONRANKLISTBYCITYNAME_EVENT, ShowCityPopulationRankListByCityNameCommand );
		    this.addCommand( ShowGuildMemberRemoveListEvent.SHOWGUILDMEMBERREMOVELIST_EVENT, ShowGuildMemberRemoveListCommand );
		    this.addCommand( RemoveGuildMemberEvent.REMOVEGUILDMEMBER_EVENT, RemoveGuildMemberCommand );
		    this.addCommand( RemoveGuildMemberByPlayerNameEvent.REMOVEGUILDMEMBERBYPLAYERNAME_EVENT, RemoveGuildMemberByPlayerNameCommand );
		    this.addCommand( ShowGuildRelationshipListEvent.SHOWGUILDRELATIONSHIPLIST_EVENT, ShowGuildRelationshipListCommand );
		    this.addCommand( ModifyLumberWorkerNumEvent.MODIFYLUMBERWORKERNUM_EVENT, ModifyLumberWorkerNumCommand );
		    this.addCommand( ModifySteelWorkerNumEvent.MODIFYSTEELWORKERNUM_EVENT, ModifySteelWorkerNumCommand );
		    this.addCommand( ModifyOilFieldWorkerNumEvent.MODIFYOILFIELDWORKERNUM_EVENT, ModifyOilFieldWorkerNumCommand );
		    this.addCommand( ModifyMillWorkerNumEvent.MODIFYMILLWORKERNUM_EVENT, ModifyMillWorkerNumCommand );
		    this.addCommand( TransportResouceByCityPosEvent.TRANSPORTRESOUCEBYCITYPOS_EVENT, TransportResouceByCityPosCommand );
		    this.addCommand( TransportResouceByCityNameEvent.TRANSPORTRESOUCEBYCITYNAME_EVENT, TransportResouceByCityNameCommand );
		    this.addCommand( TransportResourceEvent.TRANSPORTRESOURCE_EVENT, TransportResourceCommand );
		    this.addCommand( ChangeCityNameEvent.CHANGECITYNAME_EVENT, ChangeCityNameCommand );
		    this.addCommand( TaxAdjustmentEvent.TAXADJUSTMENT_EVENT, TaxAdjustmentCommand );
		    this.addCommand( EnlistCitizenEvent.ENLISTCITIZEN_EVENT, EnlistCitizenCommand );
		    this.addCommand( SafetyPatrolEvent.SAFETYPATROL_EVENT, SafetyPatrolCommand );
		    this.addCommand( HolidayCelebrateEvent.HOLIDAYCELEBRATE_EVENT, HolidayCelebrateCommand );
		    this.addCommand( GuardsParadeEvent.GUARDSPARADE_EVENT, GuardsParadeCommand );
		    this.addCommand( ImposeMaterialEvent.IMPOSEMATERIAL_EVENT, ImposeMaterialCommand );
		    this.addCommand( CancelEnlistCitizenEvent.CANCELENLISTCITIZEN_EVENT, CancelEnlistCitizenCommand );
		    this.addCommand( SellResourceEvent.SELLRESOURCE_EVENT, SellResourceCommand );
		    this.addCommand( ShowResourceSalesListEvent.SHOWRESOURCESALESLIST_EVENT, ShowResourceSalesListCommand );
		    this.addCommand( BuyResourceEvent.BUYRESOURCE_EVENT, BuyResourceCommand );
		    this.addCommand( ShowResourceSalesPageEvent.SHOWRESOURCESALESPAGE_EVENT, ShowResourceSalesPageCommand );
		    this.addCommand( ShowCityResourceSalesListEvent.SHOWCITYRESOURCESALESLIST_EVENT, ShowCityResourceSalesListCommand );
		    this.addCommand( CancelResourceSaleEvent.CANCELRESOURCESALE_EVENT, CancelResourceSaleCommand );
		    this.addCommand( EnlistSoldierEvent.ENLISTSOLDIER_EVENT, EnlistSoldierCommand );
		    this.addCommand( ReduceSoldierEvent.REDUCESOLDIER_EVENT, ReduceSoldierCommand );
		    this.addCommand( ArmSoldierEvent.ARMSOLDIER_EVENT, ArmSoldierCommand );
		    this.addCommand( DisarmSoldierEvent.DISARMSOLDIER_EVENT, DisarmSoldierCommand );
		    this.addCommand( GetArmyListEvent.GETARMYLIST_EVENT, GetArmyListCommand );
		    this.addCommand( GetCityArmyListEvent.GETCITYARMYLIST_EVENT, GetCityArmyListCommand );
		    this.addCommand( GetOrdnanceListEvent.GETORDNANCELIST_EVENT, GetOrdnanceListCommand );
		    this.addCommand( GetCityOrdnanceListEvent.GETCITYORDNANCELIST_EVENT, GetCityOrdnanceListCommand );
		    this.addCommand( GetBuildingListEvent.GETBUILDINGLIST_EVENT, GetBuildingListCommand );
		    this.addCommand( ProduceOrdnanceEvent.PRODUCEORDNANCE_EVENT, ProduceOrdnanceCommand );
		    this.addCommand( BackoutOrdnanceEvent.BACKOUTORDNANCE_EVENT, BackoutOrdnanceCommand );
		    this.addCommand( GetOrdnanceProcessListEvent.GETORDNANCEPROCESSLIST_EVENT, GetOrdnanceProcessListCommand );
		    this.addCommand( FinishProduceOrdnanceEvent.FINISHPRODUCEORDNANCE_EVENT, FinishProduceOrdnanceCommand );
		    this.addCommand( CancelProduceOrdnanceEvent.CANCELPRODUCEORDNANCE_EVENT, CancelProduceOrdnanceCommand );
		    this.addCommand( FinishAllProduceProcessEvent.FINISHALLPRODUCEPROCESS_EVENT, FinishAllProduceProcessCommand );
		    this.addCommand( ResearchTechnologyEvent.RESEARCHTECHNOLOGY_EVENT, ResearchTechnologyCommand );
		    this.addCommand( CancelResearchTechnologyEvent.CANCELRESEARCHTECHNOLOGY_EVENT, CancelResearchTechnologyCommand );
		    this.addCommand( GetCityTechnologyListEvent.GETTECHNOLOGYLIST_EVENT, GetCityTechnologyListCommand );
		    this.addCommand( GetCurrentResearchingTechnologyEvent.GETCURRENTRESEARCHINGTECHNOLOGY_EVENT, GetCurrentResearchingTechnologyCommand );
		    this.addCommand( GetCityCandidacyHeroListEvent.GETCITYCANDIDACYHEROLIST_EVENT, GetCityCandidacyHeroListCommand );
		    this.addCommand( RecruitHeroEvent.RECRUITHERO_EVENT, RecruitHeroCommand );
		    this.addCommand( DismissHeroEvent.DISMISSHERO_EVENT, DismissHeroCommand );
		    this.addCommand( ShowCityHeroInfoEvent.SHOWCITYHEROINFO_EVENT, ShowCityHeroInfoCommand );
		    this.addCommand( GetCityHeroListEvent.GETCITYHEROLIST_EVENT, GetCityHeroListCommand );
		    this.addCommand( HeroRenameEvent.HERORENAME_EVENT, HeroRenameCommand );
		    this.addCommand( SaveHeroPointEvent.ADDHEROPOINT_EVENT, SaveHeroPointCommand );
		    this.addCommand( HeroLevelUpEvent.HEROLEVELUP_EVENT, HeroLevelUpCommand );
		    this.addCommand( GetCityMilitaryListEvent.GETCITYMILITARYLIST_EVENT, GetCityMilitaryListCommand );
		    this.addCommand( CreateCityMilitaryEvent.CREATECITYMILITARY_EVENT, CreateCityMilitaryCommand );
		    this.addCommand( DismissCityMilitaryEvent.DISMISSCITYMILITARY_EVENT, DismissCityMilitaryCommand );
		    this.addCommand( RenameCityMilitaryEvent.RENAMECITYMILITARY_EVENT, RenameCityMilitaryCommand );
		    this.addCommand( ChangeCityMilitaryOfficerEvent.CHANGECITYMILITARYOFFICER_EVENT, ChangeCityMilitaryOfficerCommand );
		    this.addCommand( AssembleVehicleEvent.ASSEMBLEVEHICLE_EVENT, AssembleVehicleCommand );
		    this.addCommand( DisassembleVehicleEvent.DISASSEMBLEVEHICLE_EVENT, DisassembleVehicleCommand );
		    this.addCommand( GetFreeCityHeroListCommandEvent.GETFREECITYHEROLISTCOMMAND_EVENT, GetFreeCityHeroListCommandCommand );
		    this.addCommand( AssemblePlaneEvent.ASSEMBLEPLANE_EVENT, AssemblePlaneCommand );
		    this.addCommand( DisassemblePlaneEvent.DISASSEMBLEPLANE_EVENT, DisassemblePlaneCommand );
		    this.addCommand( LoadMapDataEvent.LOADMAPDATA_EVENT, LoadMapDataCommand );
		    this.addCommand( CreateGuildEvent.CREATEGUILD_EVENT, CreateGuildCommand );
		    this.addCommand( GetPlayerGuildAppInvListEvent.GETGUILDAPPINVLIST_EVENT, GetPlayerGuildAppInvListCommand );
		    this.addCommand( AcceptGuildInvitationEvent.ACCEPTGUILDINVITATION_EVENT, AcceptGuildInvitationCommand );
		    this.addCommand( RefuseGuildInvitationEvent.REFUSEGUILDINVITATION_EVENT, RefuseGuildInvitationCommand );
		    this.addCommand( GetBattleInfoEvent.GETBATTLEINFO_EVENT, GetBattleInfoCommand );
		    this.addCommand( GetServerTimeEvent.GETSERVERTIME_EVENT, GetServerTimeCommand );
		    this.addCommand( BatchModifyCityArmyEvent.BATCHMODIFYCITYARMY_EVENT, BatchModifyCityArmyCommand );
		    this.addCommand( TuneCityMilitaryEvent.TUNEMILITARY_EVENT, TuneCityMilitaryCommand );
		    this.addCommand( SpyEvent.SPY_EVENT, SpyCommand );
		    this.addCommand( AttackEvent.ATTACK_EVENT, AttackCommand );
		    this.addCommand( DispatchEvent.DISPATCH_EVENT, DispatchCommand );
		    this.addCommand( GetPaginateReportListEvent.GETPAGINATEREPORTLIST_EVENT, GetPaginateReportListCommand );
		    this.addCommand( SaveReportEvent.SAVEREPORT_EVENT, SaveReportCommand );
		    this.addCommand( ReadReportEvent.READREPORT_EVENT, ReadReportCommand );
		    this.addCommand( DeleteReportEvent.DELETEREPORT_EVENT, DeleteReportCommand );
		    this.addCommand( GetReportCountEvent.GETREPORTCOUNT_EVENT, GetReportCountCommand );
		    this.addCommand( LoginEvent.LOGIN_EVENT, LoginCommand );
		    this.addCommand( GetDeclareWarListEvent.GETDECLAREWARLIST_EVENT, GetDeclareWarListCommand );
		    this.addCommand( DeclareWarEvent.DECLAREWAR_EVENT, DeclareWarCommand );
		    this.addCommand( GetCityResourcesEvent.GETCITYRESOURCES_EVENT, GetCityResourcesCommand );
		    this.addCommand( GetCityDefenseListEvent.GETCITYDEFENSELIST_EVENT, GetCityDefenseListCommand );
		    this.addCommand( BuildCityDefenseEvent.BUILDCITYDEFENSE_EVENT, BuildCityDefenseCommand );
		    this.addCommand( FinishBuildDefenseEvent.FINISHBUILDDEFENSE_EVENT, FinishBuildDefenseCommand );
		    this.addCommand( CancelBuildDefenseEvent.CANCELBUILDDEFENSE_EVENT, CancelBuildDefenseCommand );
		    this.addCommand( GetDefenseProcessListEvent.GETDEFENSEPROCESSLIST_EVENT, GetDefenseProcessListCommand );
		    this.addCommand( FinishAllBuildDefenseEvent.FINISHALLBUILDDEFENSE_EVENT, FinishAllBuildDefenseCommand );
		    this.addCommand( CreatePlayerEvent.CREATEPLAYER_EVENT, CreatePlayerCommand );
		    this.addCommand( GetCityPopulationEvent.GETCITYPOPULATION_EVENT, GetCityPopulationCommand );
		    this.addCommand( GetCityResourcesOutputEvent.GETCITYRESOURCESOUTPUT_EVENT, GetCityResourcesOutputCommand );
		    this.addCommand( TechnologyResearchFinishedEvent.TECHNOLOGYRESEARCHFINISHED_EVENT, TechnologyResearchFinishedCommand );
		    this.addCommand( ClientFinishEnlistCitizenEvent.CLIENTFINISHENLISTCITIZEN_EVENT, ClientFinishEnlistCitizenCommand );
		    this.addCommand(GetEnlistCitizenProcessEvent.GETENLISTCITIZENPROCESS_EVENT,GetEnlistCitizenProcessCommand);
		    this.addCommand( ExitGuildEvent.EXITGUILD_EVENT, ExitGuildCommand );
		    this.addCommand( GetCityInfoByCityIDEvent.GETCITYINFOBYCITYID_EVENT, GetCityInfoByCityIDCommand );
		    this.addCommand( GetCityResourcesMaxEvent.GETCITYRESOURCESMAX_EVENT, GetCityResourcesMaxCommand );
		    this.addCommand( GetCityTaxAndSecurityEvent.GETCITYTAXANDSECURITY_EVENT, GetCityTaxAndSecurityCommand );
		    this.addCommand( GetDeclareWarEvent.GETDECLAREWAR_EVENT, GetDeclareWarCommand );
		    this.addCommand( GetCityDepoyQueueListEvent.GETCITYDEPOYQUEUELIST_EVENT, GetCityDepoyQueueListCommand );
		    this.addCommand( GetDepoyQueueInfoEvent.GETDEPOYQUEUEINFO_EVENT, GetDepoyQueueInfoCommand );
		    this.addCommand( GetCityBattleListEvent.GETCITYBATTLELIST_EVENT, GetCityBattleListCommand );
		    this.addCommand( GetCityAttackDepoyQueueListEvent.GETCITYATTACKDEPOYQUEUELIST_EVENT, GetCityAttackDepoyQueueListCommand );
		    this.addCommand( GetCityDefenseDepoyQueueListEvent.GETCITYDEFENSEDEPOYQUEUELIST_EVENT, GetCityDefenseDepoyQueueListCommand );
		    this.addCommand( MilitaryAttackArrivedEvent.MILITARYATTACKARRIVED_EVENT, MilitaryAttackArrivedCommand );
		    this.addCommand( MilitaryDefenseArrivedEvent.MILITARYDEFENSEARRIVED_EVENT, MilitaryDefenseArrivedCommand );
		    this.addCommand( MilitaryArrivedEvent.MILITARYARRIVED_EVENT, MilitaryArrivedCommand );
		    this.addCommand( BuyTreasureEvent.BUYTREASURE_EVENT, BuyTreasureCommand );
		    this.addCommand( GetCityResourceConsumeEvent.GETCITYRESOURCECONSUME_EVENT, GetCityResourceConsumeCommand );
		    this.addCommand( GetTreasureQueueListEvent.GETTREASUREQUEUELIST_EVENT, GetTreasureQueueListCommand );
		    this.addCommand( CancelTreasureQueueEvent.CANCELTREASUREQUEUE_EVENT, CancelTreasureQueueCommand );
		    this.addCommand( GetTreasureMapListByTypeEvent.GETTREASUREMAPLISTBYTYPE_EVENT, GetTreasureMapListByTypeCommand );
		    this.addCommand( GetPlayerTreasureMapListEvent.GETPLAYERTREASUREMAPLIST_EVENT, GetPlayerTreasureMapListCommand );
		    this.addCommand( GetTreasureListByCategoryEvent.GETTREASURELISTBYCATEGORY_EVENT, GetTreasureListByCategoryCommand );
		    //根据城市id获取城市信息
		    this.addCommand( GetCityByIDEvent.GETCITYBYID_EVENT, GetCityByIDCommand );
		    //根据城市id获取城市信息及城市资�?
		    this.addCommand(GetCityWithCityResourceByIDEvent.GETCITY_WITH_CITYRESOURCE_BYID_EVENT,GetCityWithCityResourceByIDCommand);
		    
		    this.addCommand( UpdateResourcesWorkerEvent.UPDATERESOURCESWORKER_EVENT, UpdateResourcesWorkerCommand );
		    this.addCommand( SetCityOfficerEvent.SETCITYOFFICER_EVENT, SetCityOfficerCommand );
		    this.addCommand( SetDefensiveMilitaryEvent.SETDEFENSIVEMILITARY_EVENT, SetDefensiveMilitaryCommand );
		    this.addCommand( CancelDefensiveMilitaryEvent.CANCELDEFENSIVEMILITARY_EVENT, CancelDefensiveMilitaryCommand );
		    this.addCommand( CancelCityOfficerEvent.CANCELCITYOFFICER_EVENT, CancelCityOfficerCommand );
		    
		    //this.addCommand( InitPlayerInfoEvent.INITPLAYERINFO_EVENT, InitPlayerInfoCommand );
		    this.addCommand( LoadPlayerGlobalDataEvent.LOADPLAYERGLOBALDATA_EVENT, LoadPlayerGlobalDataCommand );
		    this.addCommand( LoadPlayerCacheDataEvent.LOADPLAYERCACHEDATA_EVENT, LoadPlayerCacheDataCommand );
		    this.addCommand( GetPlayerEquipmentListByCategoryEvent.GETPLAYEREQUIPMENTLISTBYCATEGORY_EVENT, GetPlayerEquipmentListByCategoryCommand );
		    this.addCommand( GetCityHeroEvent.GETCITYHEROEQUIPMENT_EVENT, GetCityHeroCommand );
		    this.addCommand( ChangeCityHeroEquipmentEvent.CHANGECITYHEROEQUIPMENT_EVENT, ChangeCityHeroEquipmentCommand );
		    this.addCommand( OffloadCityHeroEquipmentEvent.OFFLOADCITYHEROEQUIPMENT_EVENT, OffloadCityHeroEquipmentCommand );
		    this.addCommand( StudySkillEvent.STUDYSKILL_EVENT, StudySkillCommand );
		    this.addCommand( ForgetSkillEvent.FORGETSKILL_EVENT, ForgetSkillCommand );
		    this.addCommand( LevelUpSkillEvent.SKILLLEVELUP_EVENT, LevelUpSkillCommand );
		    this.addCommand( GetSkillListEvent.GETSKILLLIST_EVENT, GetSkillListCommand );
		    this.addCommand( EnterGameEvent.ENTERGAME_EVENT, EnterGameCommand );
		    this.addCommand( GetRecommendTreasureListEvent.GETRECOMMENDTREASURELIST_EVENT, GetRecommendTreasureListCommand );
		    this.addCommand( GetMilitaryActionListEvent.GETMILITARYACTIONLIST_EVENT, GetMilitaryActionListCommand );
		    this.addCommand( GetSpyDetailEvent.GETSPYDETAIL_EVENT, GetSpyDetailCommand );
		    this.addCommand( GetAttackDetailEvent.GETATTACKDETAIL_EVENT, GetAttackDetailCommand );
		    this.addCommand( GetCityTradeQueueListEvent.GETCITYTRADEQUEUELIST_EVENT, GetCityTradeQueueListCommand );
		    this.addCommand( GetCityBusinessFreeEvent.GETCITYBUSINESSFREE_EVENT, GetCityBusinessFreeCommand );
		    this.addCommand( GetPlayerNumEvent.GETPLAYERNUM_EVENT, GetPlayerNumCommand );
		    this.addCommand( GetGuildNumEvent.GETGUILDNUM_EVENT, GetGuildNumCommand );
		    this.addCommand( GetCityNumEvent.GETCITYNUM_EVENT, GetCityNumCommand );
		    this.addCommand( ViewTargetPlayerInfoEvent.VIEWTARGETPLAYERINFO_EVENT, ViewTargetPlayerInfoCommand );
		    this.addCommand( GetPlayerGuildIDAndNameEvent.GETPLAYERGUILDIDANDNAME_EVENT, GetPlayerGuildIDAndNameCommand );
		    this.addCommand( GetPlayerInfoEvent.GETPLAYERINFO_EVENT, GetPlayerInfoCommand );
		    this.addCommand( ReadMessageEvent.READMESSAGE_EVENT, ReadMessageCommand );
		    this.addCommand( GetStrongoldAvailableBuildingListEvent.GETSTRONGOLDAVAILABLEBUILDINGLIST_EVENT, GetStrongoldAvailableBuildingListCommand );
		    this.addCommand( GetStrongholdBuildingListByStrongholdIDEvent.GETSTRONGHOLDBUILDINGLISTBYSTRONGHOLDID_EVENT, GetStrongholdBuildingListByStrongholdIDCommand );
		    this.addCommand( RefreshCityCandidacyHeroEvent.REFRESHCITYCANDIDACYHERO_EVENT, RefreshCityCandidacyHeroCommand );
		    this.addCommand( IsPlayerNameExistedEvent.ISPLAYERNAMEEXISTED_EVENT, IsPlayerNameExistedCommand );
		    this.addCommand( IsCityNameExistedEvent.ISCITYNAMEEXISTED_EVENT, IsCityNameExistedCommand );
		    this.addCommand( AccpetPlayerJoinGuildApplicationEvent.ACCPETPLAYERAPPLICATION_EVENT, AccpetPlayerJoinGuildApplicationCommand );
		    this.addCommand( RefusePlayerJoinGuildApplicationEvent.REFUSEGUILDAPPLICATION_EVENT, RefusePlayerJoinGuildApplicationCommand );
		    this.addCommand( ApplyJoinGuildEvent.APPLYJOINGUILD_EVENT, ApplyJoinGuildCommand );
		    this.addCommand( CancelApplyJoinGuildEvent.CANCELAPPLYJOINGUILD_EVENT, CancelApplyJoinGuildCommand );
		    this.addCommand( GetGuildListEvent.GETGUILDLIST_EVENT, GetGuildListCommand );
		    this.addCommand( GetGuildPageEvent.GETGUILDPAGE_EVENT, GetGuildPageCommand );
		    this.addCommand( GetGuildListByGuildNameEvent.GETGUILDLISTBYGUILDNAME_EVENT, GetGuildListByGuildNameCommand );
		    this.addCommand( ActivateGameCardEvent.ACTIVATEGAMECARD_EVENT, ActivateGameCardCommand );
		    this.addCommand( LoadGameInfoEvent.LOADGAMEINFO_EVENT, LoadGameInfoCommand );
		    this.addCommand( HasMilitaryInBattleOrGoingToMapEvent.HASMILITARYGOINGTOMAP_EVENT, HasMilitaryInBattleOrGoingToMapCommand );
		    this.addCommand( GetPlayerTreasureListByCategoryAndTypeEvent.GETTREASURELISTBYTYPE_EVENT, GetPlayerTreasureListByCategoryAndTypeCommand );
		    this.addCommand( UseHeroItemEvent.USERHEROITEM_EVENT, UseHeroItemCommand );
		    this.addCommand( RecallMilitaryEvent.RECALLMILITARY_EVENT, RecallMilitaryCommand );
		    this.addCommand( AccelerateMilitaryRetruningEvent.ACCELERATEMILITARYRETRUNING_EVENT, AccelerateMilitaryRetruningCommand );
		    this.addCommand( GetCityColonzationListEvent.GETCITYCOLONZATIONLIST_EVENT, GetCityColonzationListCommand );
		    this.addCommand( ImposeEvent.IMPOSE_EVENT, ImposeCommand );
		    this.addCommand( AddCityHeroLoyaltyEvent.ADDCITYHEROLOYALTY_EVENT, AddCityHeroLoyaltyCommand );
		    this.addCommand( FinishEnlistCitizenEvent.FINISHENLISTCITIZEN_EVENT, FinishEnlistCitizenCommand );
		    this.addCommand( GetPlayerTreasureListByCategoryEvent.GETPLAYERTREASURELISTBYCATEGORY_EVENT, GetPlayerTreasureListByCategoryCommand );
		    this.addCommand( ResetHeroPointEvent.RESETHEROPOINT_EVENT, ResetHeroPointCommand );
		    this.addCommand( ExchangedCityResourcesEvent.EXCHANGEDCITYRESOURCES_EVENT, ExchangedCityResourcesCommand );
		    this.addCommand( CureCityWoundedArmyEvent.CURECITYWOUNDEDARMY_EVENT, CureCityWoundedArmyCommand );
		    this.addCommand( DismissCityWoundedArmyEvent.DISMISSCITYWOUNDEDARMY_EVENT, DismissCityWoundedArmyCommand );
		    this.addCommand( ReceiveDailyRewardEvent.RECEIVEDAILYREWARD_EVENT, ReceiveDailyRewardCommand );
		    this.addCommand( TrainingCityHeroIncreaseLeadershipEvent.TRAININGCITYHEROINCREASELEADERSHIP_EVENT, TrainingCityHeroIncreaseLeadershipCommand );
		    this.addCommand( GetAllGuildExpenseInfoEvent.GETALLGUILDEXPENSEINFO_EVENT, GetAllGuildExpenseInfoCommand );
		    this.addCommand( GetAllGuildIncomeInfoEvent.GETALLGUILDINCOMEINFO_EVENT, GetAllGuildIncomeInfoCommand );
		    this.addCommand( DonateMoneyEvent.DONATEMONEY_EVENT, DonateMoneyCommand );
		    this.addCommand( GetGuildTechnologyEvent.GETGUILDTECHNOLOGY_EVENT, GetGuildTechnologyCommand );
		    this.addCommand( DonateOriflammeEvent.DONATEORIFLAMME_EVENT, DonateOriflammeCommand );
		    this.addCommand( UpgradeTechnologyEvent.UPGRADETECHNOLOGY_EVENT, UpgradeTechnologyCommand );
		    this.addCommand( UpgradeGuildEvent.UPGRADEGUILD_EVENT, UpgradeGuildCommand );
		    this.addCommand( GetTotalAlmsOfGuildMemberInGuildEvent.GETTOTALALMSOFGUILDMEMBERINGUILD_EVENT, GetTotalAlmsOfGuildMemberInGuildCommand );
		    this.addCommand( StrengthenCityHeroStarEvent.STRENGTHENCITYHEROSTAR_EVENT, StrengthenCityHeroStarCommand );
		    this.addCommand( GetCityHeroExtByCityHeroIDEvent.GETCITYHEROEXTBYCITYHEROID_EVENT, GetCityHeroExtByCityHeroIDCommand );
		    this.addCommand( GetCityHeroStarEvent.GETCITYHEROSTAR_EVENT, GetCityHeroStarCommand );
		    this.addCommand( UpdateCityHeroAndCityHeroExtEvent.UPDATECITYHEROANDCITYHEROEXT_EVENT, UpdateCityHeroAndCityHeroExtCommand );
		    this.addCommand( AddMilitarySoulEvent.ADDMILITARYSOUL_EVENT, AddMilitarySoulCommand );
		    this.addCommand( RemoveGuildRelationshipEvent.REMOVEGUILDRELATIONSHIP_EVENT, RemoveGuildRelationshipCommand );
		    this.addCommand( ReceiveSubsidyEvent.RECEIVESUBSIDY_EVENT, ReceiveSubsidyCommand );
		    this.addCommand( AddMilitarySpiritEvent.ADDMILITARYSPIRIT_EVENT, AddMilitarySpiritCommand );
		    this.addCommand( UpdateReinByCityHeroIDEvent.UPDATEREINBYCITYHEROID_EVENT, UpdateReinByCityHeroIDCommand );
		    this.addCommand( GetMapListByMapPosXYListEvent.GETMAPLISTBYMAPPOSXYLIST_EVENT, GetMapListByMapPosXYListCommand );
		    this.addCommand( ApplyAddFriendEvent.APPLYADDFRIEND_EVENT, ApplyAddFriendCommand );
		    this.addCommand( AcceptAddFriendApplyEvent.ACCEPTADDFRIENDAPPLY_EVENT, AcceptAddFriendApplyCommand );
		    this.addCommand( RefuseAddFriendApplyEvent.REFUSEADDFRIENDAPPLY_EVENT, RefuseAddFriendApplyCommand );
		    this.addCommand( DeleteFriendEvent.DELETEFRIEND_EVENT, DeleteFriendCommand );
		    this.addCommand( GetFriendListEvent.GETFRIENDLIST_EVENT, GetFriendListCommand );
		    this.addCommand( GetFriendNumEvent.GETFRIENDNUM_EVENT, GetFriendNumCommand );
		    this.addCommand( CreateMapFavouriteEvent.CREATEMAPFAVOURITE_EVENT, CreateMapFavouriteCommand );

		    //todo: add commands
		    //获取伤兵
		    this.addCommand(GetWoundArmyEvent.GET_WOUND_ARMY_EVENT,GetWoundArmyCommand);
		}
	}
}
