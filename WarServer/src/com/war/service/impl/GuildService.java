package com.war.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.opensymphony.webwork.dispatcher.json.JSONException;
import com.opensymphony.webwork.dispatcher.json.JSONObject;
import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.constant.BuildingConstant;
import com.war.constant.CacheConstant;
import com.war.constant.GuildConstant;
import com.war.constant.HeroConstant;
import com.war.constant.OperationLogConstant;
import com.war.constant.TreasureConstant;
import com.war.dao.ICityBuildingDAO;
import com.war.dao.ICityHeroDAO;
import com.war.dao.ICityHeroExtDAO;
import com.war.dao.IGuildAttackDAO;
import com.war.dao.IGuildDAO;
import com.war.dao.IGuildEventDAO;
import com.war.dao.IGuildExtDAO;
import com.war.dao.IGuildIncExpHistoryDAO;
import com.war.dao.IGuildPlaAppInvDAO;
import com.war.dao.IGuildPlayerDAO;
import com.war.dao.IGuildRelationshipDAO;
import com.war.dao.IGuildRewardReceiveLogDAO;
import com.war.dao.IGuildTechnologyguildDAO;
import com.war.dao.IGuildtechnologyCostDAO;
import com.war.dao.IGuildtechnologyDAO;
import com.war.dao.IPlayerDAO;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.CityHero;
import com.war.domain.CityResource;
import com.war.domain.Guild;
import com.war.domain.GuildAttack;
import com.war.domain.GuildEvent;
import com.war.domain.GuildExt;
import com.war.domain.GuildIncExpHistory;
import com.war.domain.GuildPlaAppInv;
import com.war.domain.GuildPlayer;
import com.war.domain.GuildRelationship;
import com.war.domain.GuildRewardReceiveLog;
import com.war.domain.GuildTechnology;
import com.war.domain.GuildTechnologyGuild;
import com.war.domain.GuildtechnologyCost;
import com.war.domain.Player;
import com.war.domain.PlayerTreasure;
import com.war.domain.ProcessQueue;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IGuildService;
import com.war.service.IHonorService;
import com.war.service.IOperationLogService;
import com.war.service.IProcessQueueService;
import com.war.service.IReportService;
import com.war.service.ITreasureService;
import com.war.socket.game.GameSocketService;
import com.war.util.GuildComparator;
import com.war.util.ResourceCalculateUtil;

public class GuildService implements IGuildService {

	private IGuildDAO guildDAO;
	
	private IGuildPlayerDAO guildPlayerDAO;
	
	private IGuildRewardReceiveLogDAO guildRewardReceiveLogDAO;
	
	private IGuildPlaAppInvDAO guildPlaAppInvDAO;
	
	private IGuildEventDAO guildEventDAO;
	
	private IGuildAttackDAO guildAttackDAO;
	
	private IGuildRelationshipDAO guildRelationshipDAO;

	private IGuildtechnologyDAO guildtechnologyDAO;
	
	private IGuildTechnologyguildDAO guildTechnologyguildDAO;
	
	private IGuildIncExpHistoryDAO guildIncExpHistoryDAO;
	
	private IGuildtechnologyCostDAO guildtechnologyCostDAO;
	
	private ICityHeroExtDAO cityHeroExtDAO;
	
	private IPlayerDAO playerDAO;
	
	private IGuildExtDAO guildExtDAO;
	
	private ICityHeroDAO cityHeroDAO;
	
	private ICityBuildingDAO cityBuildingDAO;
	
	private ICityService cityService;
	
	private IReportService reportService;
	
	private IProcessQueueService processQueueService;
	
	private IHonorService honorService;
	
	private ITreasureService treasureService;
	
	private IOperationLogService operationLogService;
	
	/**
	 * 该锁主要为了解决服务端，客户端同时进行完成处理而导致的并发问题
	 * ReentrantLock比synchronized效率更高
	 */
	private final Lock lock = new ReentrantLock();
	
	private static Logger logger = Logger.getLogger(GuildService.class);
	
	
	public Map<Integer, String> initGuildIDGuildNameMap() {
		Map<Integer, String> guildIDGuildNameMap = new HashMap<Integer, String>();
		List<Guild> guildList = guildDAO.getGuildList();
		for (int i=0;i<guildList.size();i++) {
			guildIDGuildNameMap.put(guildList.get(i).getGuildID(), guildList.get(i).getName());
		}
		return guildIDGuildNameMap;
	}
	
	@SuppressWarnings("unchecked")
	public Integer createGuild(Guild guild) {

		Player player = playerDAO.getPlayerByID(guild.getChairmanID());
		
		if(player.getGuildID()!=null){
			throw new GameException("您已经加入军团");
		}
		
		if(guildDAO.getGuildByGuildName(guild.getName())!=null){
			throw new GameException("军团 " + guild.getName() + " 已存在");
		}
		
		Integer cityID = cityService.getCityByPlayerID(guild.getChairmanID()).getCityID();
		CityBuilding cityBuilding = cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, BuildingConstant.EMBASSY);
		if ( cityBuilding == null || cityBuilding.getLevel() < 3 ) 
			throw new GameException("大使馆等级不足3级，无法创建军团。");
		
		// 扣减资源
		cityService.minusCityResources(cityID, 0L, 0L, 0L, 0L, 3000L);
		
		guild.setLevel(0);
		guild.setImage("");
		guild.setRenown(0L);
		guild.setMoney(0L);
		guild.setOriflammeLowerNum(0);
		guild.setOriflammeIntermediateNum(0);
		guild.setOriflammeAdvancedNum(0);
		guild.setRank(guildDAO.getGuildNum()+1);
		guild.setPopulation(1);
		guild.setPopulationMax(10 + cityBuilding.getLevel() * 3);
		guild.setIntroduction("");
		guild.setNotice("");
		
		// 创建军团
		Integer guildID = guildDAO.createGuild(guild);
		
		// 创建军团扩展信息
		GuildExt guildExt = new GuildExt();
		guildExt.setGuildID(guildID);
		guildExt.setMilitaryAttackAdd(0);
		guildExt.setMilitarySpeedAdd(0);
		guildExt.setMilitaryAttackMinus(0);
		guildExt.setArmyLifeAdd(0);
		guildExt.setArmyAttackMinus(0);
		guildExt.setArmyRangeMinus(0);
		guildExt.setArmySpeedMinus(0);
		guildExt.setTruckLifeAdd(0);
		guildExt.setTruckAttackMinus(0);
		guildExt.setTruckRangeMinus(0);
		guildExt.setTruckSpeedMinus(0);
		guildExt.setAirplaneLifeAdd(0);
		guildExt.setAirplaneAttackMinus(0);
		guildExt.setAirplaneRangeMinus(0);
		guildExt.setAirplaneSpeedMinus(0);
		
		guildExtDAO.createGuildExt(guildExt);
		
		// 创建军团成员对象
		GuildPlayer guildPlayer = new GuildPlayer();
		guildPlayer.setGuildID(guildID);
		guildPlayer.setPlayerID(guild.getChairmanID());
		guildPlayer.setContribution(0L);
		guildPlayer.setSubscribeMoney(0);
		guildPlayer.setSubscribeOriflammeLowerNum(0);
		guildPlayer.setSubscribeOriflammeIntermediateNum(0);
		guildPlayer.setSubscribeOriflammeAdvancedNum(0);
		guildPlayer.setDutyName("军团长");
		guildPlayer.setPermission("1-1-1-1-1-1");
		guildPlayer.setAllowGarrison(GuildConstant.FORBID_SUCCOR);
		
		guildPlayerDAO.createGuildPlayer(guildPlayer);
		
		//更改军团长军团编号
		player.setGuildID(guildID);
		playerDAO.updatePlayer(player);
		
		//添加军团编号军团名称缓存映射
		((Map<Integer, String>)CacheService.getFromCache(CacheConstant.GUILDID_GUILDNAME_MAP)).put(guildID, guild.getName());
		
		return guildID;
	}
	
	public void updateGuildInfo(Guild guild) {
		guildDAO.updateGuildInfo(guild);
	}
	
	public void updateGuild(Guild guild) {
		guildDAO.updateGuild(guild);
	}
	
	public Integer getGuildIDByGuildName(String guildName){
		return guildDAO.getGuildIDByGuildName(guildName);
	}
	
	public Integer getGuildRankByGuildName(String guildName) {
		Integer guildID = guildDAO.getGuildIDByGuildName(guildName);
		if (guildID != null)
			return guildDAO.getGuildByID(guildID).getRank();
		else
			throw new GameException(guildName + "军团不存在。");
	}
	
	@SuppressWarnings("unchecked")
	public String getGuildNameByID(Integer guildID){
		if(guildID != null){
			return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.GUILDID_GUILDNAME_MAP)).get(guildID);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void dismissGuild(Integer playerID) {
		
		Player curPlayer = playerDAO.getPlayerByID(playerID);
		if (curPlayer.getGuildID() == null)
			throw new GameException("您未加入任何军团。");
		
		GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(curPlayer.getGuildID(), curPlayer.getPlayerID());
		if (!guildPlayer.getPermission().equals("1-1-1-1-1-1"))
			throw new GameException("只有军团长才能够解散军团。");
		
		Guild guild = guildDAO.getGuildByID(curPlayer.getGuildID());
		
		//更新玩家军团状态
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerListByGuildID(guild.getGuildID());
		for(int i=0;i<guildPlayerList.size();i++){
			Player player = playerDAO.getPlayerByID(guildPlayerList.get(i).getPlayerID());
			player.setGuildID(null);
			playerDAO.updatePlayer(player);
			
			// 去除军团对玩家的影响
			this.cityHeroGuildAdd(cityService.getCityIDByPlayerID(player.getPlayerID()), guild.getLevel(), false);
			
			//向玩家发送报告
			reportService.sendOtherReport(player.getPlayerID(), "军团解散报告", "您所在军团" + guild.getName() + "已经正式解散，您已经脱离军团。");
		}
		
		// 删除军团双向关系
		guildRelationshipDAO.deleteGuildRelationshipsByGuildID(guild.getGuildID());
		guildRelationshipDAO.deleteGuildRelationshipsByTargetGuildID(guild.getGuildID());
		// TODO 删除别的军团对应关系
		// 删除军团成员
		guildPlayerDAO.deleteGuildPlayersByGuildID(guild.getGuildID());
		// 删除军团事件
		guildEventDAO.deleteGuildEventsByGuildID(guild.getGuildID());
		// 删除军团攻击
		guildAttackDAO.deleteGuildAttacksByGuildID(guild.getGuildID());
		// 删除军团成员申请邀请
		guildPlaAppInvDAO.deleteGuildPlaAppInvsByGuildID(guild.getGuildID());
		// 删除军团扩展信息
		guildExtDAO.deleteGuildExtByID(guild.getGuildID());
		// 删除军团
		guildDAO.deleteGuildByID(guild.getGuildID());
		
		// 记录玩家操作日志
		operationLogService.createOperationLog(playerID, OperationLogConstant.DISMISS_GUILD, guild.getName());
		
		// 移除军团编号军团名称缓存映射
		((Map<Integer, String>)CacheService.getFromCache(CacheConstant.GUILDID_GUILDNAME_MAP)).remove(guild.getGuildID());
	}
	
	public void refreshGuildRenownAndRank(){
		
		List<Guild> guildList = this.getGuildList();
		
		for(int i=0;i<guildList.size();i++){
			List<GuildPlayer> guildPlayerList = this.getGuildMemberList(guildList.get(i).getGuildID());
			long renown = 0;
			for(int j=0;j<guildPlayerList.size();j++){
				renown += guildPlayerList.get(j).getPlayer().getRenown();
			}
			guildList.get(i).setRenown(renown);
		}
		
		// 根据声望排序
		GuildComparator guildComparator = new GuildComparator();
		Collections.sort(guildList, guildComparator);
		int guildNum = guildList.size();
		for(int i=0;i<guildList.size();i++){
			guildList.get(i).setRank(guildNum-i);
		}
		
		// 更新军团声望及排名
		try {
			guildDAO.updateGuildRenownAndRankBatch(guildList);
		} catch (SQLException e) {
			logger.error("异常：", e);
		}
	}
	
	public Guild getGuildByID(Integer guildID) {
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		List<GuildRelationship> guildRelationshipList = guildRelationshipDAO.getGuildRelationshipListByGuildID(guildID);

		//初始化目标军团信息
		for(int i=0;i<guildRelationshipList.size();i++){
			guildRelationshipList.get(i).setTargetGuild(guildDAO.getGuildByID(guildRelationshipList.get(i).getTargetGuildID()));
		}
		
		//初始化军团关系列表
		guild.setFriendlyGuildList(new ArrayList<GuildRelationship>());
		guild.setNeutralGuildList(new ArrayList<GuildRelationship>());
		guild.setHostileGuildList(new ArrayList<GuildRelationship>());
		for(int i=0;i<guildRelationshipList.size();i++){
			GuildRelationship guildRelationship = guildRelationshipList.get(i);
			if(guildRelationship.getType()==GuildConstant.GUILD_RELATIONSHIP_TYPE_FRIENDLY){
				//友好
				guild.getFriendlyGuildList().add(guildRelationship);
			}else if(guildRelationship.getType()==GuildConstant.GUILD_RELATIONSHIP_TYPE_HOSTILITY){
				//敌对
				guild.getHostileGuildList().add(guildRelationship);
			}
		}
		
		//设置军团长信息
		guild.setChairman(playerDAO.getPlayerByID(guild.getChairmanID()));
		
		return guild;
	}
	
	public Integer getGuildNum(){
		return guildDAO.getGuildNum();
	}
	
	public List<Guild> getGuildPagingList(Integer start,Integer offset){
		
		List<Guild> guildList = guildDAO.getGuildPagingList(start, offset);
		for(int i=0;i<guildList.size();i++){
			guildList.get(i).setChairman(playerDAO.getPlayerByID(guildList.get(i).getChairmanID()));
		}
		
		return guildList;
	}
	
	public List<Guild> getGuildList() {
		return guildDAO.getGuildList();
	}

	public void applyJoinGuild(Integer guildID,Integer playerID) {

		Player player = playerDAO.getPlayerByID(playerID);
		
		if(player.getGuildID()!=null){
			throw new GameException("您已经加入军团。");
		}
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		if(guild==null){
			throw new GameException("军团不存在。");
		}
		
		if(guild.getPopulation().intValue()>=guild.getPopulationMax().intValue()){
			throw new GameException("军团成员人数已达到上限。");
		}
		
		// 已申请或邀请加入
		if(guildPlaAppInvDAO.getGuildPlaAppInvByGuildIDAndPlayerID(guildID, playerID) != null){
			throw new GameException("您已申请加入军团或军团已邀请您加入。");
		}
		
		GuildPlaAppInv guildPlaAppInv = new GuildPlaAppInv();
		guildPlaAppInv.setGuildID(guildID);
		guildPlaAppInv.setPlayerID(playerID);
		guildPlaAppInv.setType(1);
		
		guildPlaAppInvDAO.createGuildPlaAppInv(guildPlaAppInv);
		
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + player.getName() +  " 申请加入军团。");
		guildEventDAO.createGuildEvent(guildEvent);
	}
	
	public void cancelApplyJoinGuild(Integer guildID,Integer playerID){
		
		GuildPlaAppInv guildPlaAppInv = new GuildPlaAppInv();
		guildPlaAppInv.setGuildID(guildID);
		guildPlaAppInv.setPlayerID(playerID);
		
		guildPlaAppInvDAO.deleteGuildPlaAppInv(guildPlaAppInv);
		
		String playerName = this.getPlayerNameByPlayerID(playerID);
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + playerName +  " 取消了加入军团申请。");
		guildEventDAO.createGuildEvent(guildEvent);
	}
	
	public void accpetPlayerJoinGuildApplication(Integer playerID,Integer guildID){
		
		Player player = playerDAO.getPlayerByID(playerID);
		
		if(player.getGuildID() != null){
			throw new GameException("玩家 " + player.getName() + " 已经加入军团");
		}
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		if(guild.getPopulation().intValue()>=guild.getPopulationMax().intValue()){
			throw new GameException("军团成员人数已达到上限");
		}
		
		// 删除军团玩家申请邀请
		guildPlaAppInvDAO.deleteGuildPlaAppInvsByPlayerID(playerID);
		
		// 更新军团成员数量
		guild.setPopulation(guild.getPopulation()+1);
		
		// 更新军团声望
		guild.setRenown(guild.getRenown() + player.getRenown());
		guildDAO.updateGuild(guild);
		
		// 创建军团成员
		GuildPlayer guildPlayer = new GuildPlayer();
		guildPlayer.setGuildID(guildID);
		guildPlayer.setPlayerID(playerID);
		guildPlayer.setContribution(0L);
		guildPlayer.setSubscribeMoney(0);
		guildPlayer.setSubscribeOriflammeLowerNum(0);
		guildPlayer.setSubscribeOriflammeIntermediateNum(0);
		guildPlayer.setSubscribeOriflammeAdvancedNum(0);
		guildPlayer.setDutyName("成员");
		guildPlayer.setPermission("0-0-0-0-0-0");
		guildPlayer.setAllowGarrison(GuildConstant.FORBID_SUCCOR);
		guildPlayerDAO.createGuildPlayer(guildPlayer);
		
		// 添加军团对指挥官的增益影响
		this.cityHeroGuildAdd(cityService.getCityIDByPlayerID(player.getPlayerID()), guild.getLevel(), true); 
		
		// 创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + player.getName() +  " 加入军团。");
		guildEventDAO.createGuildEvent(guildEvent);
		
		// 更新成员军团科技及相关产出
		this.addTechToNewMember(guildPlayer);
		
		// 更新玩家军团编号
		player.setGuildID(guildID);
		playerDAO.updatePlayer(player);
		
		reportService.sendOtherReport(playerID, "加入军团报告", "您已经加入 " + guild.getName() + " 军团，您现在的身份是 " + guild.getName() + " 军团成员。");
		
		// 向客户端发送数据
		JSONObject json = new JSONObject();
		try {
			json.put("type", 50);
			json.put("guildID", guild.getGuildID());
			json.put("guildName", guild.getName());
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		GameSocketService.sendDataToClient(playerID, json);
	}
	
	public void refusePlayerJoinGuildApplication(Integer playerID,Integer guildID){
		
		GuildPlaAppInv guildPlaAppInv = guildPlaAppInvDAO.getGuildPlaAppInvByGuildIDAndPlayerID(guildID, playerID);
		
		guildPlaAppInvDAO.deleteGuildPlaAppInv(guildPlaAppInv);
		
		//创建军团事件
		String playerName = this.getPlayerNameByPlayerID(playerID);
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + playerName +  " 加入军团的申请被驳回。");
		guildEventDAO.createGuildEvent(guildEvent);

		String guildName = this.getGuildNameByID(guildID);
		reportService.sendOtherReport(playerID, "加入军团申请被驳回报告", "您所发出加入 " + guildName + " 军团的申请被驳回，您可以申请加入其他军团或者再次申请加入该军团。");
	}
	
	public void inviteJoinGuild(Integer guildID,String playerName) {
		
		Integer playerID = playerDAO.getPlayerIDByPlayerName(playerName);
		
		// 玩家不存在
		if (playerID == null) {
			throw new GameException("玩家 " + playerName + " 不存在");
		}
		
		// 玩家已加入军团
		if(guildPlayerDAO.getGuildPlayerByID(playerID) != null){
			throw new GameException("玩家 " + playerName + " 已经加入军团");
		}
		
		// 已邀请
		if(guildPlaAppInvDAO.getGuildPlaAppInvByGuildIDAndPlayerID(guildID, playerID) != null){
			throw new GameException("玩家已申请加入军团或军团已邀请玩家加入");
		}
		
		String guildName = this.getGuildNameByID(guildID);
		
		GuildPlaAppInv guildPlaAppInv = new GuildPlaAppInv();
		guildPlaAppInv.setGuildID(guildID);
		guildPlaAppInv.setPlayerID(playerID);
		guildPlaAppInv.setType(2);
		
		guildPlaAppInvDAO.createGuildPlaAppInv(guildPlaAppInv);
		
		// 创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("军团邀请 " + playerName +  " 玩家加入军团。");
		guildEventDAO.createGuildEvent(guildEvent);
		
		// 向玩家发送报告
		reportService.sendOtherReport(playerID, "军团加入邀请报告", "军团 " + guildName + " 邀请您加入，请您在大使馆中进行操作。");
	}
	
	public void cancelInvitePlayer(Integer guildID,Integer playerID){
		
		GuildPlaAppInv guildPlaAppInv = new GuildPlaAppInv();
		guildPlaAppInv.setGuildID(guildID);
		guildPlaAppInv.setPlayerID(playerID);
		
		guildPlaAppInvDAO.deleteGuildPlaAppInv(guildPlaAppInv);
		
		String playerName = this.getPlayerNameByPlayerID(playerID);
		String guildName = this.getGuildNameByID(guildID);
		
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("军团取消对玩家 " + playerName +  " 的加入邀请。");
		guildEventDAO.createGuildEvent(guildEvent);
		
		//向玩家发送报告
		reportService.sendOtherReport(playerID, "军团加入邀请取消报告", "军团 " + guildName + " 取消了对您的加入邀请。");
	}
	
	public void acceptGuildInvitation(Integer playerID,Integer guildID){
		
		Player player = playerDAO.getPlayerByID(playerID);
		
		if(player.getGuildID()!=null){
			throw new GameException("您已经加入军团");
		}
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		if(guild.getPopulation().intValue() >= guild.getPopulationMax().intValue()){
			throw new GameException("军团成员人数已达到上限");
		}
		
		GuildPlaAppInv guildPlaAppInv = guildPlaAppInvDAO.getGuildPlaAppInvByGuildIDAndPlayerID(guildID, playerID);
		
		//删除军团玩家申请邀请
		guildPlaAppInvDAO.deleteGuildPlaAppInv(guildPlaAppInv);
		
		//更新军团成员数量
		guild.setPopulation(guild.getPopulation()+1);
		guildDAO.updateGuild(guild);
		
		//创建军团成员
		GuildPlayer guildPlayer = new GuildPlayer();
		guildPlayer.setGuildID(guildID);
		guildPlayer.setPlayerID(playerID);
		guildPlayer.setContribution(0L);
		guildPlayer.setSubscribeMoney(0);
		guildPlayer.setSubscribeOriflammeLowerNum(0);
		guildPlayer.setSubscribeOriflammeIntermediateNum(0);
		guildPlayer.setSubscribeOriflammeAdvancedNum(0);
		guildPlayer.setDutyName("成员");
		guildPlayer.setPermission("0-0-0-0-0-0");
		guildPlayer.setAllowGarrison(GuildConstant.FORBID_SUCCOR);
		guildPlayerDAO.createGuildPlayer(guildPlayer);
		
		// 更新军团对玩家麾下所有指挥官的增益影响
		this.cityHeroGuildAdd(cityService.getCityIDByPlayerID(player.getPlayerID()), guild.getLevel(), true); 
		
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + player.getName() +  " 加入军团。");
		guildEventDAO.createGuildEvent(guildEvent);
		
		//更新玩家军团编号
		player.setGuildID(guildID);
		playerDAO.updatePlayer(player);
		
		reportService.sendOtherReport(playerID, "加入军团报告", "您已经加入 " + guild.getName() + " 军团，您现在的身份是" + guild.getName() + "军团成员。");
		
		// 向客户端发送数据
		JSONObject json = new JSONObject();
		try {
			json.put("type", 50);
			json.put("guildID", guild.getGuildID());
			json.put("guildName", guild.getName());
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		GameSocketService.sendDataToClient(playerID, json);
	}
	
	public void refuseGuildInvitation(Integer playerID,Integer guildID){
		
		String playerName = this.getPlayerNameByPlayerID(playerID);
		
		GuildPlaAppInv guildPlaAppInv = guildPlaAppInvDAO.getGuildPlaAppInvByGuildIDAndPlayerID(guildID, playerID);
		
		guildPlaAppInvDAO.deleteGuildPlaAppInv(guildPlaAppInv);
		
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("玩家 " + playerName +  " 拒绝了军团的加入邀请。");
		guildEventDAO.createGuildEvent(guildEvent);
	}
	
	public void exitGuild(Integer playerID){
		
		Player player = playerDAO.getPlayerByID(playerID);
		
		if(player.getGuildID()==null){
			throw new GameException("您未加入任何军团");
		}
		
		if(guildDAO.getGuildByID(player.getGuildID()).getChairmanID().intValue() == playerID.intValue()){
			throw new GameException("军团长无法退出，如果您希望解散请在军团管理面板中操作。");
		}
		
		Guild guild = guildDAO.getGuildByID(player.getGuildID());
		
		guildPlayerDAO.deleteGuildPlayerByGuildIDAndPlayerID(player.getGuildID(), playerID);
		
		// 创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(player.getGuildID());
		guildEvent.setGuildEventID(player.getGuildID());
		guildEvent.setDescription("玩家 " + player.getName() + " 退出了军团");
		guildEventDAO.createGuildEvent(guildEvent);
		
		player.setGuildID(null);
		playerDAO.updatePlayer(player);
		
		// 更新军团成员数量
		guild.setPopulation(guild.getPopulation() - 1);
		
		// 更新军团声望值
		guild.setRenown(guild.getRenown() - player.getRenown());
		guildDAO.updateGuild(guild);
		this.restoreMemberTechWhenQuitGuild(playerID);
		
		// 减去军团对玩家的增益
		cityHeroGuildAdd(cityService.getCityIDByPlayerID(player.getPlayerID()), guild.getLevel(),  false); 
	}
	
	/**
	 * 军团对玩家的影响
	 * @param cityID
	 * @param guildLevel
	 * @param isAdd 如果为true得到军团加成, 否则false失去军团加成
	 */
	private void cityHeroGuildAdd(Integer cityID, Integer guildLevel, boolean isAdd) {
		
		int expGuildAdd = GuildConstant.GUILD_LEVEL_ADD_HERO_EXP_PERCENT[guildLevel];
		int addReinPersent = GuildConstant.GUILD_LEVEL_ADD_HERO_REIN_PERCENT[guildLevel];
		Map<String, Integer> params = new HashMap<String, Integer>();
		
		int basicRein = 0;
		int reinGuildAdd = 0;
		
		List<CityHero> cityHeroList = cityHeroDAO.getCityHeroListByCityID(cityID);
		
		for (CityHero cityHero : cityHeroList) {
			params.clear();
			
			switch (cityHero.getQuality()) {
				case HeroConstant.QUALITY_NORMAL:
					basicRein = 200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[0];
				case HeroConstant.QUALITY_SINGULARITY:
					basicRein = 200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[1];		
				case HeroConstant.QUALITY_EPIC:
					basicRein = 200 + (cityHero.getLevel() - 1) * HeroConstant.REIN_MUTIPLE[2];
			}
			
			// 更新指挥官信息
			reinGuildAdd = basicRein * addReinPersent / 100;
			if (isAdd) {
				cityHero.setRein(cityHero.getRein() + reinGuildAdd);
				
				params.put("reinGuildAdd", reinGuildAdd);
				params.put("expGuildAdd", expGuildAdd);
				
			} else {
				cityHero.setRein(cityHero.getRein() - reinGuildAdd);
				
				params.put("reinGuildAdd", 0);
				params.put("expGuildAdd", 0);
			}
			cityHeroDAO.updateCityHero(cityHero);
			
			// 更新指挥官扩展信息
			cityHeroExtDAO.updateGuildAddByCityIDWithParams(params, cityID);
		}
	}
	
	public List<GuildPlaAppInv> getPlayerAppInvList(Integer playerID){
		
		List<GuildPlaAppInv> guildPlaAppInvList = guildPlaAppInvDAO.getGuildPlaAppInvListByPlayerID(playerID);
		
		for(int i=0;i<guildPlaAppInvList.size();i++){
			guildPlaAppInvList.get(i).setGuild(guildDAO.getGuildByID(guildPlaAppInvList.get(i).getGuildID()));
		}
		
		return guildPlaAppInvList;
	}
		
	public List<GuildPlaAppInv> getGuildPlaAppInvList(Integer guildID){
		
		List<GuildPlaAppInv> guildPlaAppInvList = guildPlaAppInvDAO.getGuildPlaAppInvListByGuildID(guildID);
		
		for(int i=0;i<guildPlaAppInvList.size();i++){
			guildPlaAppInvList.get(i).setPlayer(playerDAO.getPlayerByID(guildPlaAppInvList.get(i).getPlayerID()));
		}
		
		return guildPlaAppInvList;
	}
	
	public Integer getGuildMemberAmount(Integer guildID) {
		return guildPlayerDAO.getGuildPlayerAmountByGuildID(guildID);
	}

	public List<GuildPlayer> getGuildMemeberPagingList(Integer guildID, Integer start, Integer offset) {
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerPagingListByGuildID(guildID,start,offset);
		
		for(int i=0;i<guildPlayerList.size();i++){
			guildPlayerList.get(i).setPlayer(playerDAO.getPlayerByID(guildPlayerList.get(i).getPlayerID()));
			guildPlayerList.get(i).getPlayer().setHonorName(honorService.getHonorByID(guildPlayerList.get(i).getPlayer().getHonorID()));
		}
		return guildPlayerList;
	}
	
	public List<GuildPlayer> getGuildMemberList(Integer guildID){
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerListByGuildID(guildID);
		
		for(int i=0;i<guildPlayerList.size();i++){
			guildPlayerList.get(i).setPlayer(playerDAO.getPlayerByID(guildPlayerList.get(i).getPlayerID()));
		}
		return guildPlayerList;
	}

	public Integer getGuildEventAmount(Integer guildID) {
		return guildEventDAO.getGuildEventAmountByGuildID(guildID);
	}
	
	public List<GuildEvent> getGuildEventPagingList(Integer guildID, Integer start,
			Integer offset) {
		return guildEventDAO.getGuildEventPagingListByGuildID(guildID, start, offset);
	}
	
	public Integer getGuildAttackAmount(Integer guildID) {
		return guildAttackDAO.getGuildAttackAmountByGuildID(guildID);
	}
	
	public List<GuildAttack> getGuildAttackPagingList(Integer guildID, Integer start,
			Integer offset) {
		
		List<GuildAttack> guildAttackList = guildAttackDAO.getGuildAttackPagingListByGuildID(guildID, start, offset);
		
		for(int i=0;i<guildAttackList.size();i++){
			guildAttackList.get(i).setTargetGuild(guildDAO.getGuildByID(guildAttackList.get(i).getTargetGuildID()));
		}
		
		return guildAttackList;
	}
	
	public void addGuildPlayer(GuildPlayer guildPlayer) {
		guildPlayerDAO.createGuildPlayer(guildPlayer);
		//更新成员军团科技及相关产出
		this.addTechToNewMember(guildPlayer);
	}

	public void deleteGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID){
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		if(playerID.intValue()==guild.getChairmanID().intValue()){
			throw new GameException("军团长无法被移除。");
		}
		
		//更新玩家军团信息
		Player player = playerDAO.getPlayerByID(playerID);
		player.setGuildID(null);
		playerDAO.updatePlayer(player);
		
		guildPlayerDAO.deleteGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		
		//更新军团成员数量
		guild.setPopulation(guild.getPopulation() - 1);
		guildDAO.updateGuild(guild);
		this.restoreMemberTechWhenQuitGuild(playerID);
		
		//向玩家发送报告
		reportService.sendOtherReport(playerID, "请退军团报告", "您已经被请出" + guild.getName() + "军团，现在已经脱离军团。");
	}
	
	public void deleteGuildPlayerByGuildIDAndPlayerName(Integer guildID,String playerName){
		Integer playerID = playerDAO.getPlayerIDByPlayerName(playerName);
		
		if(playerID==null){
			throw new GameException("玩家 " + playerName + " 不存在");
		}
		
		if(guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID)==null){
			throw new GameException("玩家 " + playerName + " 不在军团中");
		}
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		//更新玩家军团信息
		Player player = playerDAO.getPlayerByID(playerID);
		player.setGuildID(null);
		playerDAO.updatePlayer(player);
		
		guildPlayerDAO.deleteGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		
		//更新军团成员数量
		guild.setPopulation(guild.getPopulation() - 1);
		//更新军团声望值
		guild.setRenown(guild.getRenown() - player.getRenown());
		guildDAO.updateGuild(guild);
		this.restoreMemberTechWhenQuitGuild(playerID);
		
		//向玩家发送报告
		reportService.sendOtherReport(playerID, "请退军团报告", "您已经被请出" + guild.getName() + "军团，现在已经脱离军团。");
	}
	
	public void updateGuildPlayer(GuildPlayer guildPlayer) {
		guildPlayerDAO.updateGuildPlayer(guildPlayer);
	}

	public void guildMemeberGrant(GuildPlayer guildPlayer){
		
		GuildPlayer currentGuildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildPlayer.getGuildID(), guildPlayer.getPlayerID());
		
		if (!currentGuildPlayer.getPermission().equals(guildPlayer.getPermission())) {
			Guild guild = guildDAO.getGuildByID(currentGuildPlayer.getGuildID());
			// 成员权限发生变动
			if (currentGuildPlayer.getPermission().equals("0-0-0-0-0-0") && !guildPlayer.getPermission().equals("0-0-0-0-0-0")) {
				// 新授权
				if (guild.getOfficialNum()>=GuildConstant.GUILD_OFFICER_NUM_EVERY_GRADE[guild.getLevel()]) {
					throw new GameException("军团官员数量已达到上限，无法再添加官员。");
				}
				guild.setOfficialNum(guild.getOfficialNum()+1);
				guildDAO.updateGuild(guild);
			} else if (!currentGuildPlayer.getPermission().equals("0-0-0-0-0-0") && guildPlayer.getPermission().equals("0-0-0-0-0-0")) {
				// 取消授权
				guild.setOfficialNum(guild.getOfficialNum()-1);
				guildDAO.updateGuild(guild);
			} else {
				// 授权变动
			}
			
			currentGuildPlayer.setDutyName(guildPlayer.getDutyName());
			currentGuildPlayer.setPermission(guildPlayer.getPermission());
			guildPlayerDAO.updateGuildPlayer(currentGuildPlayer);
		}
		
		//创建军团事件
		String playerName = this.getPlayerNameByPlayerID(guildPlayer.getPlayerID());
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildPlayer.getGuildID());
		guildEvent.setDescription("军团成员 " + playerName + " 的权限已变更，现在职位为 " + guildPlayer.getDutyName() + "。");
		guildEvent.setCreateTime(new Date());
		guildEventDAO.createGuildEvent(guildEvent);
		
		//向玩家发送报告
		reportService.sendOtherReport(guildPlayer.getPlayerID(), "军团权限变更报告", "您的军团权限已变更，现在职位为 " + guildPlayer.getDutyName() + " 。");
	}

	public GuildPlayer getGuildPlayerByGuildIDAndPlayerID(Integer guildID,Integer playerID) {
		
		GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		guildPlayer.setPlayer(playerDAO.getPlayerByID(guildPlayer.getPlayerID()));
		
		return guildPlayer;
	}

	public void addGuildRelationship(Integer guildID,String targetGuildName,Integer type){
		
		Guild guild = guildDAO.getGuildByID(guildID);
		if(guild.getName().equals(targetGuildName)){
			throw new GameException("无法与自身军团建立任何关系");
		}
		
		Guild targetGuild = guildDAO.getGuildByGuildName(targetGuildName);
		
		//军团不存在
		if(targetGuild==null)
			throw new GameException("军团 " + targetGuildName + " 不存在");
		
		//限制每级所能添加的盟友个数
		Integer relationCount = guildRelationshipDAO.getGuildRelationshipCountByGuildID(guildID);
		if (relationCount >= GuildConstant.GUILD_FRIENDGUILD_NUM_EVERY_GRADE[guild.getLevel()]) {
			throw new GameException("您所在军团不能再添加盟友了。");
		}
		GuildRelationship existGuildRelationship = guildRelationshipDAO.getGuildRelationshipByGuildIDAndTargetGuildID(guildID, targetGuild.getGuildID());
		if(existGuildRelationship != null){
			String existTypeStr = "";
			if (existGuildRelationship.getType() == GuildConstant.GUILD_RELATIONSHIP_TYPE_FRIENDLY) {
				existTypeStr = "友好";
			} else if (existGuildRelationship.getType() == GuildConstant.GUILD_RELATIONSHIP_TYPE_HOSTILITY) {
				existTypeStr = "敌对";
			}
			throw new GameException("您所在军团已与 " + targetGuildName + " 已存在" + existTypeStr + "关系");
		}
		
		GuildRelationship guildRelationship = new GuildRelationship();
		guildRelationship.setGuildID(guildID);
		guildRelationship.setTargetGuildID(targetGuild.getGuildID());
		guildRelationship.setType(type);
		guildRelationshipDAO.createGuildRelationship(guildRelationship);
		
		String typeStr = "";
		switch(type){
			case 1:
				typeStr = "友好";
				break;
			case 2:
				typeStr = "敌对";
				break;
		}
		
		//创建军团事件
		GuildEvent guildEvent = new GuildEvent();
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription("军团成功与" + targetGuildName + "建立" + typeStr + "关系");
		guildEventDAO.createGuildEvent(guildEvent);
	}

	 public void removeGuildRelationship(Integer guildID,String targetGuildName) {
			
			Guild guild = guildDAO.getGuildByID(guildID);
			if(guild.getName().equals(targetGuildName)){
				throw new GameException("无法与该军团的关系。");
			}
			
			Guild targetGuild = guildDAO.getGuildByGuildName(targetGuildName);
			
			//军团不存在
			if(targetGuild==null){
				throw new GameException("军团 " + targetGuildName + " 不存在");
			}
			//查看与该军团的关系
			GuildRelationship existGuildRelationship = guildRelationshipDAO.getGuildRelationshipByGuildIDAndTargetGuildID(guildID, targetGuild.getGuildID());
			//删除与该军团的关系
			guildRelationshipDAO.deleteGuildRelationshipByGuildIDAndTargetGuildID(guildID, targetGuild.getGuildID());
			String typeStr = "";
			switch(existGuildRelationship.getType()){
				case 1:
					typeStr = "友好";
					break;
				case 2:
					typeStr = "敌对";
					break;
			}
			//创建军团事件
			GuildEvent guildEvent = new GuildEvent();
			guildEvent.setGuildID(guildID);
			guildEvent.setDescription("军团成功与" + targetGuildName + "解除" + typeStr + "关系");
			guildEventDAO.createGuildEvent(guildEvent);
			GuildEvent targetGuildEvent = new GuildEvent();
			targetGuildEvent.setGuildID(targetGuild.getGuildID());
			targetGuildEvent.setDescription("军团成功与" + targetGuildName + "脱离" + typeStr + "关系");
			guildEventDAO.createGuildEvent(targetGuildEvent);
	 }
	
	public List<GuildRelationship> getGuildRelationshipListByGuildID(Integer guildID) {
		List<GuildRelationship> guildRelationshipList = guildRelationshipDAO.getGuildRelationshipListByGuildID(guildID);
		
		for(int i=0;i<guildRelationshipList.size();i++){
			guildRelationshipList.get(i).setTargetGuild(guildDAO.getGuildByID(guildRelationshipList.get(i).getTargetGuildID()));
		}
		
		return guildRelationshipList;
	}
	
	public boolean hasCreateOrJoinGuild(Integer playerID){
		if(playerDAO.getPlayerByID(playerID).getGuildID() != null){
			return true;
		} else {
			return false;
		}
	}
	
	public List<GuildPlayer> getGuildPlayerListByGuildID(Integer guildID) {
		
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerListByGuildID(guildID);
		if (guildPlayerList != null && !guildPlayerList.isEmpty()) {
			for (GuildPlayer guildPlayer : guildPlayerList) {
				guildPlayer.setPlayer(playerDAO.getPlayerByID(guildPlayer.getPlayerID()));
			}
		}
		
		return guildPlayerList;
	}
	
	public Map<String,Object> getPlayerGuildIDAndName(Integer playerID){
		
		Map<String,Object> result = new HashMap<String,Object>();
		Integer guildID = playerDAO.getPlayerByID(playerID).getGuildID();
		
		if(guildID==null){
			result.put("guildID", 0);
			result.put("guildName", "无");
			return null;
		}
		
		Guild guild = guildDAO.getGuildByID(guildID);
		
		result.put("guildID", guild.getGuildID());
		result.put("guildName", guild.getName());
		
		return result;
	}
	
	public void addGuildAttack(Integer guildID, Integer targetGuildID, String description, Integer type){
		GuildAttack guildAttack = new GuildAttack();
		guildAttack.setGuildID(guildID);
		guildAttack.setTargetGuildID(targetGuildID);
		guildAttack.setDescription(description);
		guildAttack.setType(type);
		guildAttackDAO.createGuildAttack(guildAttack);
	}

	public void resignOfficialPosition(Integer guildID, Integer playerID, Integer type) {
		GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		guildPlayer.setPermission("0-0-0-0-0-0");
		guildPlayerDAO.updateGuildPlayer(guildPlayer);
	}

	public void donateMoney(Integer guildID, Integer playerID, Integer money) {
		
		cityService.minusCityResources(cityService.getCityIDByPlayerID(playerID), 0L, 0L, 0L, 0L, money);
		
		Guild guild = guildDAO.getGuildByID(guildID);
		guild.setMoney(guild.getMoney() + money);
		guildDAO.updateGuild(guild);
		
		// 增加玩家贡献度
		GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		guildPlayer.setSubscribeMoney(guildPlayer.getSubscribeMoney() + money);
		guildPlayer.setContribution(guildPlayer.getContribution() + money * GuildConstant.MONEY_CONTRIBUTION_EXCHANGE_HONOR_NUM);
		this.updateGuildPlayer(guildPlayer);
		
		// 添加捐献日志
		GuildIncExpHistory guildIncExpHistory = new GuildIncExpHistory();
		guildIncExpHistory.setGuildID(guildID);
		guildIncExpHistory.setPlayerID(guildPlayer.getGuildPlayerID());
		guildIncExpHistory.setMoney(new Long(money));
		guildIncExpHistory.setOriflammeLowerNum(0);
		guildIncExpHistory.setOriflammeIntermediateNum(0);
		guildIncExpHistory.setOriflammeAdvancedNum(0);
		guildIncExpHistory.setType(GuildConstant.OPRATION_CONTRIBUTION);
		guildIncExpHistory.setCreateTime(new Date());
		guildIncExpHistoryDAO.createGuildIncExpHistory(guildIncExpHistory);
	}
	
	public void donateOriflamme(Integer guildID, Integer playerID, Integer oriflammeType, Integer oriflammeNum){
		
		if (oriflammeNum <= 0) {
			return;
		}
		
		GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID);

		GuildIncExpHistory guildIncExpHistory = new GuildIncExpHistory();
		guildIncExpHistory.setOriflammeLowerNum(0);
		guildIncExpHistory.setOriflammeIntermediateNum(0);
		guildIncExpHistory.setOriflammeAdvancedNum(0);
		
		Guild guild = guildDAO.getGuildByID(guildID);
		int contribution = 0;
		
		PlayerTreasure playerTreasure = null;
		switch (oriflammeType) {
			case GuildConstant.FLAG_OF_PETTY_OFFICER:
			// 士官军旗数
				playerTreasure = treasureService.getPlayerTreasureByID(playerID, TreasureConstant.ORIFLAMME_LOWER);
				if (playerTreasure == null || playerTreasure.getNum() < oriflammeNum) {
					throw new GameException("您拥有的士官军旗数不足。");
				}
				guildPlayer.setSubscribeOriflammeLowerNum(guildPlayer.getSubscribeOriflammeLowerNum() + oriflammeNum);
				guild.setOriflammeLowerNum(guild.getOriflammeLowerNum() + oriflammeNum);
				contribution = GuildConstant.ORIFLAMME_LOWER_CONTRIBUTION_EXCHANGE_HONOR_NUM * oriflammeNum;
				guildIncExpHistory.setOriflammeLowerNum(oriflammeNum);
				
				treasureService.decreasePlayerTreasure(playerID, TreasureConstant.ORIFLAMME_LOWER, oriflammeNum);
				break;
				
			case GuildConstant.FLAG_OF_FIELD_OFFICER:
			// 少将军旗
				playerTreasure = treasureService.getPlayerTreasureByID(playerID, TreasureConstant.ORIFLAMME_INTERMEDIATE_NUM);
				if (playerTreasure == null || playerTreasure.getNum() < oriflammeNum) {
					throw new GameException("您拥有的校官军旗数不足。");
				}
				
				guildPlayer.setSubscribeOriflammeIntermediateNum(guildPlayer.getSubscribeOriflammeIntermediateNum() + oriflammeNum);
				guild.setOriflammeIntermediateNum(guild.getOriflammeIntermediateNum() + oriflammeNum);
				contribution = GuildConstant.ORIFLAMME_INTERMEDIATE_CONTRIBUTION_EXCHANGE_HONOR_NUM * oriflammeNum;
				guildIncExpHistory.setOriflammeIntermediateNum(oriflammeNum);
				
				treasureService.decreasePlayerTreasure(playerID, TreasureConstant.ORIFLAMME_INTERMEDIATE_NUM, oriflammeNum);
				break;
				
			case GuildConstant.FLAG_OF_MARSHAL:
			// 元帅军旗
				playerTreasure = treasureService.getPlayerTreasureByID(playerID, TreasureConstant.ORIFLAMME_ADVANCED_NUM);
				if (playerTreasure == null || playerTreasure.getNum() < oriflammeNum) {
					throw new GameException("您拥有的元帅军旗数不足。");
				}
				
				guildPlayer.setSubscribeOriflammeAdvancedNum(guildPlayer.getSubscribeOriflammeAdvancedNum() + oriflammeNum);
				guild.setOriflammeAdvancedNum(guild.getOriflammeAdvancedNum() + oriflammeNum);
				contribution = GuildConstant.ORIFLAMME_ADVANCED_CONTRIBUTION_EXCHANGE_HONOR_NUM * oriflammeNum;
				guildIncExpHistory.setOriflammeAdvancedNum(oriflammeNum);
				
				treasureService.decreasePlayerTreasure(playerID, TreasureConstant.ORIFLAMME_ADVANCED_NUM, oriflammeNum);
				break;
	
			default:
				break;
		}
		
		guildDAO.updateGuild(guild);
		
		// 更新军团玩家信息
		guildPlayer.setContribution(guildPlayer.getContribution() + contribution);
		guildPlayerDAO.updateGuildPlayer(guildPlayer);
		
		// 生成捐献日志
		guildIncExpHistory.setGuildID(guildID);
		guildIncExpHistory.setMoney(0L);
		guildIncExpHistory.setType(GuildConstant.OPRATION_CONTRIBUTION);
		guildIncExpHistory.setPlayerID(guildPlayer.getGuildPlayerID());
		guildIncExpHistory.setCreateTime(DateService.getCurrentUtilDate());
		guildIncExpHistoryDAO.createGuildIncExpHistory(guildIncExpHistory);
		
	}
	
	public void upgradeGuild(Integer guildID) {
		Guild guild = guildDAO.getGuildByID(guildID);
		
		// level 为军团升级前的等级，能为0,1,2,3,4
		Integer level = guild.getLevel();
		
		if (guild.getRenown() < GuildConstant.GUILD_RENOWN[level]) {
			throw new GameException("军团声望不足。");
		} else if (guild.getMoney() < GuildConstant.GUILD_TOTAL_MONEY[level]) {
			throw new GameException("军团金币不足。");
		} else if (guild.getPopulation() < GuildConstant.GUILD_MAN_COUNT[level]) {
			throw new GameException("军团成员不足。");
		} else if (guild.getOriflammeLowerNum() < GuildConstant.GUILD_PERTYOFFICER_ENSIGN_COUNT[level]) {
			throw new GameException("军团士官旗帜不足。");
		} else if (guild.getOriflammeIntermediateNum() < GuildConstant.GUILD_MAJOR_ENSIGN_COUNT[level]) {
			throw new GameException("军团少将旗帜不足。");
		} else if (guild.getOriflammeAdvancedNum() < GuildConstant.GUILD_MARSHAL_ENSIGN_COUNT[level]) {
			throw new GameException("军团元帅旗帜不足。");
		} else {
			
			// 如果条件都满足的话，则执行升级操作
			guild.setMoney(guild.getMoney() - GuildConstant.GUILD_TOTAL_MONEY[level]);
			guild.setOriflammeLowerNum(guild.getOriflammeLowerNum() - GuildConstant.GUILD_PERTYOFFICER_ENSIGN_COUNT[level]);
			guild.setOriflammeIntermediateNum(guild.getOriflammeIntermediateNum() - GuildConstant.GUILD_MAJOR_ENSIGN_COUNT[level]);
			guild.setOriflammeAdvancedNum(guild.getOriflammeAdvancedNum() - GuildConstant.GUILD_MARSHAL_ENSIGN_COUNT[level]);
			guild.setLevel(level+1);
			
			//设置允许任命的最大官员数
			guild.setOfficialNum(GuildConstant.GUILD_OFFICER_NUM_EVERY_GRADE[level]);
			guildDAO.updateGuild(guild);
			
			// 更新军团对玩家指挥官的影响
			List<GuildPlayer> playerList = this.getGuildPlayerListByGuildID(guildID);
			for (GuildPlayer guildPlayer : playerList) {
				this.cityHeroGuildAdd(cityService.getCityByPlayerID(guildPlayer.getPlayer().getPlayerID()).getCityID(), guild.getLevel(), true); 
			}
			
			//生成军团资源支出日志
			GuildIncExpHistory guildIncExpHistory = new GuildIncExpHistory();
			guildIncExpHistory.setGuildID(guildID);
			guildIncExpHistory.setPlayerID(0);
			guildIncExpHistory.setMoney(GuildConstant.GUILD_TOTAL_MONEY[level]);
			guildIncExpHistory.setOriflammeLowerNum(GuildConstant.GUILD_PERTYOFFICER_ENSIGN_COUNT[level]);
			guildIncExpHistory.setOriflammeIntermediateNum(GuildConstant.GUILD_MAJOR_ENSIGN_COUNT[level]);
			guildIncExpHistory.setOriflammeAdvancedNum(GuildConstant.GUILD_MARSHAL_ENSIGN_COUNT[level]);
			guildIncExpHistory.setType(GuildConstant.OPRATION_CONSUME);
			guildIncExpHistory.setCreateTime(new Date());
			guildIncExpHistoryDAO.createGuildIncExpHistory(guildIncExpHistory);
		} 
	}
		
	public List<GuildTechnology> getGuildTechnology(Integer guildID) {
		List<GuildTechnologyGuild> guildTechnologyGuildList = guildTechnologyguildDAO.getGuildTechnologyguildListByGuildID(guildID);
		Guild guild = guildDAO.getGuildByID(guildID);
		List<GuildTechnology> list = guildtechnologyDAO.getGuildtechnologyList();
		List<GuildTechnology> rlist = new ArrayList<GuildTechnology>();
		for (Iterator<GuildTechnology> it = list.iterator(); it.hasNext();) {
			GuildTechnology guildTechnology = it.next();
			for (Iterator<GuildTechnologyGuild> iterator = guildTechnologyGuildList.iterator(); iterator.hasNext();) {
				GuildTechnologyGuild guildTechnologyGuild = iterator.next();
				if (guildTechnologyGuild.getGuildtechnologyID().equals(guildTechnology.getGuildtechnologyID())) {
					guildTechnology.setGuildTechnologyGuild(iterator.next());
					GuildtechnologyCost guildtechnologyCost = guildtechnologyCostDAO.getGuildtechnologyCostListByTechnologyIDAndLevel(guildTechnology.getGuildtechnologyID(), guild.getLevel());
					guildTechnology.setGuildtechnologyCost(guildtechnologyCost);
					break;
				}
			}
			
			//如果没有相应的科技，则将升级到军团科技为1级的军团需求列出
			if (guildTechnology.getGuildTechnologyGuild() == null) {
				guildTechnology.setGuildTechnologyGuild(null);
				GuildtechnologyCost guildtechnologyCost = guildtechnologyCostDAO.getGuildtechnologyCostListByTechnologyIDAndLevel(guildTechnology.getGuildtechnologyID(), 1);
				guildTechnology.setGuildtechnologyCost(guildtechnologyCost);
			}
			rlist.add(guildTechnology);
		}
		return rlist;
	}
	
	public void upgradeTechnology(Integer guildID, Integer technologyID) {
		GuildTechnology guildTechnology = guildtechnologyDAO.getGuildtechnologyByID(technologyID); 
		if (guildTechnology != null) {
			@SuppressWarnings("unused")
			GuildTechnologyGuild guildTechnologyGuild = guildTechnologyguildDAO.getGuildTechnologyguildByGuildID(guildID, technologyID);
		}
	}
	
	public void receiveSubsidy(Integer guildID, Integer playerID) {
		
		GuildRewardReceiveLog guildRewardReceiveLog = guildRewardReceiveLogDAO.getGuildRewardReceiveLogByID(playerID);
		
		Calendar currentCalendar = Calendar.getInstance();
		
		Calendar receiveCalendar = Calendar.getInstance();
		
		if (guildRewardReceiveLog != null) {
			receiveCalendar.setTime(guildRewardReceiveLog.getReceiveTime());
		}
		
		if (guildRewardReceiveLog == null)  {
			guildRewardReceiveLog = new GuildRewardReceiveLog();
			guildRewardReceiveLog.setPlayerID(playerID);
			guildRewardReceiveLog.setReceiveTime(DateService.getCurrentUtilDate());
			
			guildRewardReceiveLogDAO.createGuildRewardReceiveLog(guildRewardReceiveLog);


		} else if ((receiveCalendar.get(Calendar.WEEK_OF_YEAR) != currentCalendar.get(Calendar.WEEK_OF_YEAR)) || (receiveCalendar.get(Calendar.YEAR) != currentCalendar.get(Calendar.YEAR))) { 
			guildRewardReceiveLog.setReceiveTime(DateService.getCurrentUtilDate());
			guildRewardReceiveLogDAO.updateGuildRewardReceiveLog(guildRewardReceiveLog);
			
		} else {
			throw new GameException("这周您已领取过补贴。");
		}
		
		// 领取军队补贴
		Integer cityID = cityService.getCityIDByPlayerID(playerID);
		Integer guildLevel = this.getGuildByID(guildID).getLevel();
		cityService.addCityResources(cityID, GuildConstant.GUILD_SUBSIDY[guildLevel][0], GuildConstant.GUILD_SUBSIDY[guildLevel][1], GuildConstant.GUILD_SUBSIDY[guildLevel][2], GuildConstant.GUILD_SUBSIDY[guildLevel][3], GuildConstant.GUILD_SUBSIDY[guildLevel][4]);
		
	}
	
	public boolean hasReceivedSubsidy(Integer playerID) {
		return guildRewardReceiveLogDAO.getGuildRewardReceiveLogByID(playerID) == null ? false : true ;
	}

	public void shareProfit(Integer guildID) {
		
	}
	
	public List<GuildIncExpHistory> getAllGuildExpenseInfo(Integer guildID) {
		List<GuildIncExpHistory> list = guildIncExpHistoryDAO.getGuildIncExpHistoryListByType(guildID, 1);
		return list;
	}

	public List<GuildIncExpHistory> getAllGuildIncomeInfo(Integer guildID) {
		List<GuildIncExpHistory> list = guildIncExpHistoryDAO.getGuildIncExpHistoryListByType(guildID, 2);
		return list;
	}
	
	public List<GuildPlayer> getTotalAlmsOfGuildMemberInGuild(Integer guildID, Integer guildPlayerID, Integer type) {
		
		// 取得军团各成员捐献和的记录列表
		List<GuildIncExpHistory> guildIncExpHistoryList = guildIncExpHistoryDAO.getTotalIncExpOfGuildMemberInGuild(guildID, guildPlayerID.equals(0) ? null : guildPlayerID, type.equals(0) ? null : type);
		if (guildIncExpHistoryList == null) {
			throw new GameException("还没有历史记录。");
		}
		
		// 放入map中，以便更具guildPlayerID取出记录
		Map<Integer, GuildIncExpHistory> map = new HashMap<Integer, GuildIncExpHistory>();
		for (Iterator<GuildIncExpHistory> it=guildIncExpHistoryList.iterator(); it.hasNext();) {
			GuildIncExpHistory history = it.next();
			map.put(history.getPlayerID(),history);
		}
		
		// 如果guildPlayerId不为0，则说明是要取出某个玩家捐献总和的记录
		List<GuildPlayer> list = new ArrayList<GuildPlayer>();
		if (guildPlayerID != null && !guildPlayerID.equals(0)) {
			GuildPlayer guildPlayer = guildPlayerDAO.getGuildPlayerByID(guildPlayerID);
			guildPlayer.setGuildIncExpHistory(map.get(guildPlayer.getGuildPlayerID()));
			guildPlayer.setPlayer(playerDAO.getPlayerByID(guildPlayer.getPlayerID()));
			list.add(guildPlayer);
			
		// 如果guildPlayerId为null，则说明是要取出玩家捐献总和的所有记录
		} else {
			List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerListByGuildID(guildID);
			if (guildPlayerList == null) {
				throw new GameException("军团信息出错。");
			}
			
			List<GuildPlayer> tempGuildPlayerList = new ArrayList<GuildPlayer>();
			for (Iterator<GuildPlayer> it = guildPlayerList.iterator(); it.hasNext();) {
				GuildPlayer guildPlayer = it.next();
				if (map.get(guildPlayer.getGuildPlayerID()) == null) {
					tempGuildPlayerList.add(guildPlayer);
					continue;
				} else {
					guildPlayer.setGuildIncExpHistory(map.get(guildPlayer.getGuildPlayerID()));
					guildPlayer.setPlayer(playerDAO.getPlayerByID(guildPlayer.getPlayerID()));
				}
			}
			
			if (tempGuildPlayerList.size() > 0) {
				guildPlayerList.removeAll(tempGuildPlayerList);
			}
			
			list = guildPlayerList;
		}
		
		return list;
	}
	
	public List<GuildIncExpHistory> getAlmsHistoryOfGuildMember(Integer guildID, Integer guildPlayerID) {
		return guildIncExpHistoryDAO.getGuildIncExpHistoryListByGuildIDAndPlayerID(guildID, guildPlayerID, GuildConstant.OPRATION_CONTRIBUTION);
	}
	
	public void researchGuildTechnologyByTechnologyID(Integer cityID, Integer technologyID, Integer guildID){
		
		// 升级科技目标级别
		Integer level = null;
		GuildTechnologyGuild guildTechnologyGuild = guildTechnologyguildDAO.getGuildTechnologyguildByGuildID(technologyID, guildID);
		if (guildTechnologyGuild == null) {
			level = 1;
		} else {
			level = guildTechnologyGuild.getLevel() + 1;
		}
		
		// 判断军团资源是否够升级科技
		Guild guild = guildDAO.getGuildByID(guildID);
		GuildtechnologyCost cost = guildtechnologyCostDAO.getGuildtechnologyCostListByTechnologyIDAndLevel(technologyID, level);
		if (guild.getLevel() < cost.getRequiredGuildLevel()) {
			throw new GameException("军团等级不足。");
		}
		if (guild.getMoney() - cost.getMoney() < 0) {
			throw new GameException("金钱不足。");
		}
		if (guild.getOriflammeLowerNum() - cost.getOriflammeLowerNum() < 0) {
			throw new GameException("少校旗帜不足。");
		}
		if (guild.getOriflammeIntermediateNum() - cost.getOriflammeIntermediateNum() < 0) {
			throw new GameException("少校旗帜不足。");
		}
		if (guild.getOriflammeAdvancedNum() - cost.getOriflammeAdvancedNum() < 0) {
			throw new GameException("元帅旗帜不足。");
		}
		
		GuildTechnology guildTechnology = guildtechnologyDAO.getGuildtechnologyByID(technologyID);
		Integer guildTechnologyID = null;
		
		// 升级军团科技，如果军团科技等级为0，则创建一条军团科技记录到军团科技表中；否则修改科技等级加1，并设置状态为2(正在升级中)
		if (guildTechnologyGuild == null) {
			guildTechnologyGuild = new GuildTechnologyGuild();
			guildTechnologyGuild.setGuildID(guildID);
			guildTechnologyGuild.setGuildtechnologyID(guildTechnology.getGuildtechnologyID());
			guildTechnologyGuild.setLevel(0);
			guildTechnologyGuild.setState(GuildConstant.GUILD_TECH_CURRENT_STATE_UPGRADING);
			guildTechnologyID = guildTechnologyguildDAO.createGuildTechnologyguild(guildTechnologyGuild);
			
		} else {
			guildTechnologyGuild.setState(GuildConstant.GUILD_TECH_CURRENT_STATE_UPGRADING);
			guildTechnologyguildDAO.updateGuildTechnologyguild(guildTechnologyGuild);
			guildTechnologyID = guildTechnologyGuild.getGuildTechnologyguildID();
		}
		
		if(level == null){
			throw new GameException("升级科技出现异常。");
		}
		
		// 升级科技消耗军团资源
		guild.setMoney(guild.getMoney() - cost.getMoney());
		guild.setOriflammeAdvancedNum(guild.getOriflammeAdvancedNum() - cost.getOriflammeAdvancedNum());
		guild.setOriflammeIntermediateNum(guild.getOriflammeIntermediateNum() - cost.getOriflammeIntermediateNum());
		guild.setOriflammeLowerNum(guild.getOriflammeLowerNum() - cost.getOriflammeLowerNum());
		guildDAO.updateGuild(guild);
		
		// 生成军团资源支出日志
		GuildIncExpHistory history = new GuildIncExpHistory();
		history.setGuildID(guildID);
		history.setPlayerID(0);
		history.setMoney(cost.getMoney());
		history.setOriflammeAdvancedNum(cost.getOriflammeAdvancedNum());
		history.setOriflammeIntermediateNum(cost.getOriflammeIntermediateNum());
		history.setOriflammeLowerNum(cost.getOriflammeLowerNum());
		history.setType(GuildConstant.OPRATION_CONSUME);
		history.setCreateTime(new Date());
		guildIncExpHistoryDAO.createGuildIncExpHistory(history);
		
		// 添加进程队列
		List<ProcessQueue> plist = processQueueService.getCityIDProcessQueueList(cityID);
		Date finishTime ;
		
		// 如果有队列，计算结束时间就依最后的记录作为参考
		if(plist.size() >0 ){
			ProcessQueue p = plist.get(plist.size()-1);
			finishTime = p.getFinishTime();
		}else{
			finishTime = new Date();
		}
		
		// 计算结束时间
		finishTime.setTime(finishTime.getTime() + cost.getTime() * 60 * 60 * 1000);
		ProcessQueue processQueue = new ProcessQueue();
		processQueue.setCityID(cityID);
		processQueue.setTargetID(guildTechnologyID);
		processQueue.setStartTime(DateService.getCurrentUtilDate());
		processQueue.setFinishTime(finishTime);
		processQueue.setType(GuildConstant.RESEARCH_GUILD_TECH_QUEUE_TYPE);
		processQueueService.addProcessQueue(processQueue);
	}

	public void cancelResearchGuildTechnology(Integer cityID, Integer ID, Integer guildID) {
		
		GuildTechnologyGuild guildTechnology = guildTechnologyguildDAO.getGuildTechnologyguildWithResearching(ID, guildID, 2);
		
		if(guildTechnology==null){
			throw new GameException("当前没有正在研究的科技。");
		}
		
		if(guildTechnology.getLevel()==0){
			//如果是0级则删除当前军团科技
			guildTechnologyguildDAO.deleteGuildTechnologyguildByID(guildTechnology.getGuildTechnologyguildID());
		}else{
			//不为0级则更新状态为正常
			//更新军团科技状态为正常
			guildTechnologyguildDAO.updateGuildTechnologyguildWithState(guildTechnology.getGuildTechnologyguildID());
		}
		
		//获得进程队列并删除
		ProcessQueue processQueue = processQueueService.getProcessQueue(cityID, guildTechnology.getGuildTechnologyguildID(), GuildConstant.RESEARCH_GUILD_TECH_QUEUE_TYPE);
		processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
	}
	
	public void finishResearchGuildTechnology(ProcessQueue processQueue){

		lock.lock();
		try{
			if(processQueueService.getProcessQueueByID(processQueue.getProcessQueueID()) == null){
				return;
			}
			//删除进程队列
			processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
		}finally{
			lock.unlock();
		}
		
		GuildTechnologyGuild guildTechnology = guildTechnologyguildDAO.getGuildTechnologyguildByID(processQueue.getTargetID());
		guildTechnology.setLevel(guildTechnology.getLevel()+1);
		//设置城市科技为正常
		guildTechnology.setState(GuildConstant.GUILD_TECH_CURRENT_STATE_NORMAL);
		//更新科技
		guildTechnologyguildDAO.updateGuildTechnologyguild(guildTechnology);
		
		int level = guildTechnology.getLevel();
		
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerListByGuildID(guildTechnology.getGuildID());
		
		//取得军团科技类型编号:军团科技十位和百位
		int guildTechTypeID = guildTechnology.getGuildtechnologyID()/10;
		
		if (guildTechTypeID >= 11 && guildTechTypeID <= 15) {	// 更新军团科技对每个玩家城市资源的影响
			for (int i=0;i<guildPlayerList.size();i++) {
				
				City city = cityService.getCityByPlayerID(guildPlayerList.get(i).getPlayerID());
				
				CityResource cityResource = cityService.getCityResourceByCityID(city.getCityID());
				
				Map<String,Object> cityResourceParams = new HashMap<String,Object>();
				cityResourceParams.put("cityID", city.getCityID());
				switch(guildTechTypeID){ 
					case GuildConstant.WOOD_GUILD_TECH_ADD:
						// 增加军团加成及木材产量
						int woodGuildAdd = GuildConstant.WOOD_GUILD_TECH_ADD_MULTIPLE[level];
						cityResourceParams.put("woodOutput", ResourceCalculateUtil.calculateWoodOutput(city.getTax(), cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), woodGuildAdd, cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd()));
						cityResourceParams.put("woodGuildAdd", woodGuildAdd);
						break;
					case GuildConstant.STEEL_GUILD_TECH_ADD:
						// 增加军团加成及钢铁产量
						int steelGuildAdd = GuildConstant.STEEL_GUILD_TECH_ADD_MULTIPLE[level];
						cityResourceParams.put("steelOutput", ResourceCalculateUtil.calculateSteelOutput(city.getTax(), cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), steelGuildAdd, cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd()));
						cityResourceParams.put("steelGuildAdd", steelGuildAdd);
						break;
					case GuildConstant.OIL_GUILD_TECH_ADD:
						// 增加军团加成及石油产量
						int oilGuildAdd = GuildConstant.OIL_GUILD_TECH_ADD_MULTIPLE[level];
						cityResourceParams.put("oilOutput", ResourceCalculateUtil.calculateOilOutput(city.getTax(), cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), oilGuildAdd, cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd()));
						cityResourceParams.put("oilGuildAdd", oilGuildAdd);
						break;
					case GuildConstant.FOOD_GUILD_TECH_ADD:
						// 增加军团加成及食物产量
						int foodGuildAdd = GuildConstant.WOOD_GUILD_TECH_ADD_MULTIPLE[level];
						cityResourceParams.put("foodOutput", ResourceCalculateUtil.calculateFoodOutput(city.getTax(), cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), foodGuildAdd, cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd()));
						cityResourceParams.put("foodGuildAdd", foodGuildAdd);
						break;
					case GuildConstant.MONEY_GUILD_TECH_ADD:
						// 增加军团加成及金钱产量
						int moneyGuildAdd = GuildConstant.MONEY_GUILD_TECH_ADD_MULTIPLE[level];
						cityResourceParams.put("moneyOutput", ResourceCalculateUtil.calculateMoneyOutput(cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd()));
						cityResourceParams.put("moneyGuildAdd", moneyGuildAdd);
						break;
				}
				cityService.updateCityResource(cityResourceParams);
			}
		} else { 	// 更新军团科技对军队的影响
			Map<String,Object> guildExtParams = new HashMap<String,Object>();
			guildExtParams.put("guildID", processQueue.getTargetID());
			switch(guildTechTypeID) {
				case GuildConstant.ARMY_ATTACK_TECH_MINUS:
					guildExtParams.put("armyAttackMinus", GuildConstant.ARMY_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_RANGE_TECH_MINUS:
					guildExtParams.put("armyRangeMinus", GuildConstant.ARMY_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_SPEED_TECH_MINUS:
					guildExtParams.put("armySpeedMinus", GuildConstant.ARMY_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_LIFE_ADD:
					guildExtParams.put("armyLifeAdd", GuildConstant.ARMY_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.TRUCK_ATTACK_TECH_MINUS:
					guildExtParams.put("truckAttackMinus", GuildConstant.TRUCK_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_RANGE_TECH_MINUS:
					guildExtParams.put("truckRangeMinus", GuildConstant.TRUCK_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_SPEED_TECH_MINUS:
					guildExtParams.put("truckSpeedMinus", GuildConstant.TRUCK_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_LIFE_ADD:
					guildExtParams.put("truckLifeAdd", GuildConstant.TRUCK_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.AIRPLANE_ATTACK_TECH_MINUS:
					guildExtParams.put("airplaneAttackMinus", GuildConstant.AIRPLANE_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_RANGE_TECH_MINUS:
					guildExtParams.put("airplaneRangeMinus", GuildConstant.AIRPLANE_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_SPEED_TECH_MINUS:
					guildExtParams.put("airplaneSpeedMinus", GuildConstant.AIRPLANE_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_LIFE_ADD:
					guildExtParams.put("airplaneLifeAdd", GuildConstant.AIRPLANE_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_ATTACK_TECH_ADD:
					guildExtParams.put("militaryAttackAdd", GuildConstant.MILITARY_ATTACK_TECH_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_ATTACK_TECH_MINUS:
					guildExtParams.put("militaryAttackAdd", GuildConstant.MILITARY_ATTACK_TECH_MINUS_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_SPEED_TECH_ADD:
					guildExtParams.put("militarySpeedAdd", GuildConstant.MILITARY_SPEED_TECH_ADD_MULTIPLE[level]);
					break;
			}
			
			guildExtDAO.updateGuildExtParams(guildExtParams);
		}
		
	}
	
	public void modifyGuildNoticeAndIntro(Integer guildID, String introduction, String notice){
		Guild guild = guildDAO.getGuildByID(guildID);
		if (guild != null) {
			guild.setNotice(notice);
			guild.setIntroduction(introduction);
			guildDAO.updateGuild(guild);
		} else {
			throw new GameException("军团不存在。");
		}
	}

	/**
	 * 退出或被踢出军团时，恢复军团科技为初始值；并计算产出
	 * @param playerID
	 */
	private void restoreMemberTechWhenQuitGuild(Integer playerID){
		
		Map<String,Object> cityResourceParams = new HashMap<String,Object>();
		City city = cityService.getCityByPlayerID(playerID);
		CityResource cityResource = cityService.getCityResourceByCityID(city.getCityID());
		cityResourceParams.put("cityID", city.getCityID());
	
		// 重置军团加成及木材产量
		int woodGuildAdd = 0;
		cityResourceParams.put("woodOutput", ResourceCalculateUtil.calculateWoodOutput(city.getTax(), cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(),  cityResource.getWoodOfficerAdd(), woodGuildAdd, cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd()));
		cityResourceParams.put("woodGuildAdd", woodGuildAdd);
		
		// 重置军团加成及钢铁产量
		int steelGuildAdd = 0;
		cityResourceParams.put("steelOutput", ResourceCalculateUtil.calculateSteelOutput(city.getTax(), cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), steelGuildAdd, cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd()));
		cityResourceParams.put("steelGuildAdd", steelGuildAdd);
			
		// 重置军团加成及石油产量
		int oilGuildAdd = 0;
		cityResourceParams.put("oilOutput", ResourceCalculateUtil.calculateOilOutput(city.getTax(), cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), oilGuildAdd, cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd()));
		cityResourceParams.put("oilGuildAdd", oilGuildAdd);
		
		// 重置军团加成及食物产量
		int foodGuildAdd = 0;
		cityResourceParams.put("foodOutput", ResourceCalculateUtil.calculateFoodOutput(city.getTax(), cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), foodGuildAdd, cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd()));
		cityResourceParams.put("foodGuildAdd", foodGuildAdd);
		
		// 重置军团加成及金钱产量
		int moneyGuildAdd = 0;
		cityResourceParams.put("moneyOutput", ResourceCalculateUtil.calculateMoneyOutput(cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), moneyGuildAdd, cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd()));
		cityResourceParams.put("moneyGuildAdd", moneyGuildAdd);
		
		cityService.updateCityResource(cityResourceParams);
	}
	
	/**
	 * 添加军团科技到新成员中
	 * @param guildPlayer
	 */
	private void addTechToNewMember(GuildPlayer guildPlayer){
		List<GuildTechnologyGuild> guildTechnoloyGuildList = guildTechnologyguildDAO.getGuildTechnologyguildListByGuildID(guildPlayer.getGuildID());
		
		if (guildTechnoloyGuildList == null || guildTechnoloyGuildList.isEmpty()) {
			return;
		}
		
		Map<String,Object> cityResourceParams = new HashMap<String,Object>();
		Map<String,Object> guildExtParams = new HashMap<String,Object>();
		
		City city = cityService.getCityByPlayerID(guildPlayer.getPlayerID());
		CityResource cityResource = cityService.getCityResourceByCityID(city.getCityID());
		
		cityResourceParams.put("cityID", city.getCityID());
		guildExtParams.put("guildID", guildPlayer.getGuildID());
		
		int level;
		int guildTechTypeID;
		GuildTechnologyGuild guildTechnology = null;
		for (int i=0;i<guildTechnoloyGuildList.size();i++) {
			//取得军团科技类型编号:军团科技十位和百位
			guildTechnology = guildTechnoloyGuildList.get(i);
			level = guildTechnology.getLevel();
			guildTechTypeID = guildTechnology.getGuildtechnologyID()/10;
			switch(guildTechTypeID){
				case GuildConstant.WOOD_GUILD_TECH_ADD:
					//增加木材产量
					int woodGuildAdd = GuildConstant.WOOD_GUILD_TECH_ADD_MULTIPLE[level];
					cityResourceParams.put("woodOutput", ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), woodGuildAdd, cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd()));
					cityResourceParams.put("woodGuildAdd", woodGuildAdd);
					break;
				case GuildConstant.STEEL_GUILD_TECH_ADD:
					//增加钢铁产量
					int steelGuildAdd = GuildConstant.STEEL_GUILD_TECH_ADD_MULTIPLE[level];
					cityResourceParams.put("steelOutput", ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), steelGuildAdd, cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd()));
					cityResourceParams.put("steelGuildAdd", steelGuildAdd);
					break;
				case GuildConstant.OIL_GUILD_TECH_ADD:
					//增加石油产量
					int oilGuildAdd = GuildConstant.OIL_GUILD_TECH_ADD_MULTIPLE[level];
					cityResourceParams.put("oilOutput", ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), oilGuildAdd, cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd()));
					cityResourceParams.put("oilGuildAdd", oilGuildAdd);
					break;
				case GuildConstant.FOOD_GUILD_TECH_ADD:
					//增加食物产量
					int foodGuildAdd = GuildConstant.FOOD_GUILD_TECH_ADD_MULTIPLE[level];
					cityResourceParams.put("foodOutput", ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), foodGuildAdd, cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd()));
					cityResourceParams.put("foodGuildAdd", foodGuildAdd);
					break;
				case GuildConstant.MONEY_GUILD_TECH_ADD:
					//增加金钱产量
					int moneyGuildAdd = GuildConstant.MONEY_GUILD_TECH_ADD_MULTIPLE[level];
					cityResourceParams.put("moneyOutput", ResourceCalculateUtil.calculateMoneyOutput(cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), moneyGuildAdd, cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd()));
					cityResourceParams.put("moneyGuildAdd", moneyGuildAdd);
					break;
				case GuildConstant.ARMY_ATTACK_TECH_MINUS:
					guildExtParams.put("armyAttackMinus", GuildConstant.ARMY_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_RANGE_TECH_MINUS:
					guildExtParams.put("armyRangeMinus", GuildConstant.ARMY_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_SPEED_TECH_MINUS:
					guildExtParams.put("armySpeedMinus", GuildConstant.ARMY_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.ARMY_LIFE_ADD:
					guildExtParams.put("armyLifeAdd", GuildConstant.ARMY_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.TRUCK_ATTACK_TECH_MINUS:
					guildExtParams.put("truckAttackMinus", GuildConstant.TRUCK_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_RANGE_TECH_MINUS:
					guildExtParams.put("truckRangeMinus", GuildConstant.TRUCK_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_SPEED_TECH_MINUS:
					guildExtParams.put("truckSpeedMinus", GuildConstant.TRUCK_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.TRUCK_LIFE_ADD:
					guildExtParams.put("truckLifeAdd", GuildConstant.TRUCK_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.AIRPLANE_ATTACK_TECH_MINUS:
					guildExtParams.put("airplaneAttackMinus", GuildConstant.AIRPLANE_ATTACK_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_RANGE_TECH_MINUS:
					guildExtParams.put("airplaneRangeMinus", GuildConstant.AIRPLANE_RANGE_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_SPEED_TECH_MINUS:
					guildExtParams.put("airplaneSpeedMinus", GuildConstant.AIRPLANE_SPEED_TECH_MINUS_POINT[level]);
					break;
				case GuildConstant.AIRPLANE_LIFE_ADD:
					guildExtParams.put("airplaneLifeAdd", GuildConstant.AIRPLANE_LIFE_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_ATTACK_TECH_ADD:
					guildExtParams.put("militaryAttackAdd", GuildConstant.MILITARY_ATTACK_TECH_ADD_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_ATTACK_TECH_MINUS:
					guildExtParams.put("militaryAttackAdd", GuildConstant.MILITARY_ATTACK_TECH_MINUS_MULTIPLE[level]);
					break;
				case GuildConstant.MILITARY_SPEED_TECH_ADD:
					guildExtParams.put("militarySpeedAdd", GuildConstant.MILITARY_SPEED_TECH_ADD_MULTIPLE[level]);
					break;
			}
			
		}
		
		if (cityResourceParams.size() > 1) {
			cityService.updateCityResource(cityResourceParams);
		}
		
		if (guildExtParams.size() > 1) {
			guildExtDAO.updateGuildExtParams(guildExtParams);
		}
	}

	public GuildExt getGuildExt(Integer guildID) {
		return guildExtDAO.getGuildExtByID(guildID);
	}
	
	public long getGuildPlayerContribution(Integer guildID, Integer playerID) {
		
		if (guildID == null) {
			return 0L;
		}
		
		GuildPlayer guildPlayer =  guildPlayerDAO.getGuildPlayerByGuildIDAndPlayerID(guildID, playerID);
		return guildPlayer == null ? 0L : guildPlayer.getContribution(); 
	}

	@SuppressWarnings("unchecked")
	private String getPlayerNameByPlayerID(Integer playerID) {
		return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERNAME_MAP)).get(playerID);
	}
	
	public void changeGarrisonState(Integer playerID, Integer guildID, Integer state) {
		guildPlayerDAO.updateAllowGarrisonByPlayerIDAndGuildID(playerID, guildID, state);
	}
	

	public IGuildDAO getGuildDAO() {
		return guildDAO;
	}
	
	public void setGuildDAO(IGuildDAO guildDAO) {
		this.guildDAO = guildDAO;
	}
	
	public IGuildPlayerDAO getGuildPlayerDAO() {
		return guildPlayerDAO;
	}
	
	public void setGuildPlayerDAO(IGuildPlayerDAO guildPlayerDAO) {
		this.guildPlayerDAO = guildPlayerDAO;
	}
	
	public IGuildRewardReceiveLogDAO getGuildRewardReceiveLogDAO() {
		return guildRewardReceiveLogDAO;
	}

	public void setGuildRewardReceiveLogDAO(
			IGuildRewardReceiveLogDAO guildRewardReceiveLogDAO) {
		this.guildRewardReceiveLogDAO = guildRewardReceiveLogDAO;
	}

	public IGuildPlaAppInvDAO getGuildPlaAppInvDAO() {
		return guildPlaAppInvDAO;
	}
	
	public void setGuildPlaAppInvDAO(IGuildPlaAppInvDAO guildPlaAppInvDAO) {
		this.guildPlaAppInvDAO = guildPlaAppInvDAO;
	}
	
	public IGuildEventDAO getGuildEventDAO() {
		return guildEventDAO;
	}

	public void setGuildEventDAO(IGuildEventDAO guildEventDAO) {
		this.guildEventDAO = guildEventDAO;
	}
	
	public IGuildAttackDAO getGuildAttackDAO() {
		return guildAttackDAO;
	}
	
	public void setGuildAttackDAO(IGuildAttackDAO guildAttackDAO) {
		this.guildAttackDAO = guildAttackDAO;
	}
	
	public IGuildRelationshipDAO getGuildRelationshipDAO() {
		return guildRelationshipDAO;
	}

	public void setGuildRelationshipDAO(IGuildRelationshipDAO guildRelationshipDAO) {
		this.guildRelationshipDAO = guildRelationshipDAO;
	}

	public IGuildtechnologyDAO getGuildtechnologyDAO() {
		return guildtechnologyDAO;
	}
	
	public void setGuildtechnologyDAO(IGuildtechnologyDAO guildtechnologyDAO) {
		this.guildtechnologyDAO = guildtechnologyDAO;
	}

	public IGuildTechnologyguildDAO getGuildTechnologyguildDAO() {
		return guildTechnologyguildDAO;
	}

	public void setGuildTechnologyguildDAO(
			IGuildTechnologyguildDAO guildTechnologyguildDAO) {
		this.guildTechnologyguildDAO = guildTechnologyguildDAO;
	}

	public IGuildIncExpHistoryDAO getGuildIncExpHistoryDAO() {
		return guildIncExpHistoryDAO;
	}
	
	public void setGuildIncExpHistoryDAO(
			IGuildIncExpHistoryDAO guildIncExpHistoryDAO) {
		this.guildIncExpHistoryDAO = guildIncExpHistoryDAO;
	}
	
	public IGuildtechnologyCostDAO getGuildtechnologyCostDAO() {
		return guildtechnologyCostDAO;
	}

	public void setGuildtechnologyCostDAO(
			IGuildtechnologyCostDAO guildtechnologyCostDAO) {
		this.guildtechnologyCostDAO = guildtechnologyCostDAO;
	}
	
	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public ICityHeroExtDAO getCityHeroExtDAO() {
		return cityHeroExtDAO;
	}

	public void setCityHeroExtDAO(ICityHeroExtDAO cityHeroExtDAO) {
		this.cityHeroExtDAO = cityHeroExtDAO;
	}
	
	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	public IGuildExtDAO getGuildExtDAO() {
		return guildExtDAO;
	}

	public void setGuildExtDAO(IGuildExtDAO guildExtDAO) {
		this.guildExtDAO = guildExtDAO;
	}
	
	public ICityHeroDAO getCityHeroDAO() {
		return cityHeroDAO;
	}

	public void setCityHeroDAO(ICityHeroDAO cityHeroDAO) {
		this.cityHeroDAO = cityHeroDAO;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public IHonorService getHonorService() {
		return honorService;
	}

	public void setHonorService(IHonorService honorService) {
		this.honorService = honorService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
}
