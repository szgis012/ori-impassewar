package com.war.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.GameConfig;
import com.war.common.SystemConfig;
import com.war.common.TemplateService;
import com.war.constant.ArmyConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CountryConstant;
import com.war.constant.DailyRewardConstant;
import com.war.constant.FriendStateConstant;
import com.war.constant.HonorConstant;
import com.war.constant.MapConstant;
import com.war.constant.PlayerStateConstant;
import com.war.dao.ICityArmyDAO;
import com.war.dao.ICityBuildingDAO;
import com.war.dao.ICityDefenseDAO;
import com.war.dao.IDefenseDAO;
import com.war.dao.IFriendDAO;
import com.war.dao.IMapDAO;
import com.war.dao.IPlayerDAO;
import com.war.dao.IPlayerTaskDAO;
import com.war.dao.IPlayerTreasureDAO;
import com.war.dao.IReferenceAccountDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.domain.Friend;
import com.war.domain.Player;
import com.war.domain.PlayerTask;
import com.war.exception.GameException;
import com.war.service.IArmyService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IDefenseService;
import com.war.service.IGuildService;
import com.war.service.IHonorService;
import com.war.service.IMessageService;
import com.war.service.IOrdnanceService;
import com.war.service.IPlayerService;
import com.war.service.IReportService;
import com.war.service.ITaskService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityDefenseService;
import com.war.socket.game.GameSocketService;

/**
 * 玩家Service
 * @author TopTong
 *
 */
public class PlayerService implements IPlayerService {

	private IPlayerDAO playerDAO;
	
	private IFriendDAO friendDAO;
	
	private ICityBuildingDAO cityBuildingDAO;
	
	private ICityDefenseDAO cityDefenseDAO;
	
	private ICityArmyDAO cityArmyDAO;
	
	private IDefenseDAO defenseDAO;
	
	private IPlayerTaskDAO playerTaskDAO;
	
	private IMapDAO mapDAO;
	
	private IGuildService guildService;
	
	private IPlayerTreasureDAO playerTreasureDAO;
	
	private IReferenceAccountDAO referenceAccountDAO;
	
	private IHonorService honorService;

	private ICityService cityService;
	
	private IBuildingService buildingService;
	
	private ICityDefenseService cityDefenseService;
	
	private IMessageService messageService;
	
	private IReportService reportService;
	
	private ITaskService taskService;
	
	private IArmyService armyService;
	
	private IOrdnanceService ordnanceService;
	
	private IDefenseService defenseService;
	
	private ITreasureService treasureService;
	
	private DataSourceTransactionManager transactionManager;
	
	private static Logger logger = Logger.getLogger(PlayerService.class);
	
	private Lock createPlayerLock = new ReentrantLock();
	
	private Lock receiveDailyRewardLock = new ReentrantLock();
	
	
	public List<Player> getPlayerList() {
		return playerDAO.getPlayerList();
	}
	
	public Map<Integer, String> initPlayerIDPlayerNameMap() {
		Map<Integer, String> playerIDPlayerNameMap = new HashMap<Integer, String>();
		List<Player> playerList = playerDAO.getPlayerList();
		for (int i=0;i<playerList.size();i++) {
			playerIDPlayerNameMap.put(playerList.get(i).getPlayerID(), playerList.get(i).getName());
		}
		return playerIDPlayerNameMap;
	}
	
	public void addPlayerRenown(Integer playerID,Long renown){
		playerDAO.addPlayerRenown(playerID, renown);
	}
	
	public void addPlayerOnlineTime(Integer playerID){
		Date lastLoginTime = playerDAO.getLastLoginTimeByPlayerID(playerID);
		Date now = DateService.getCurrentUtilDate();
		int onlineTime = (int)(now.getTime()-lastLoginTime.getTime())/1000/60;
		playerDAO.addPlayerOnlineTime(playerID, onlineTime);
	}
	
	public void updatePlayer(Player player){
		playerDAO.updatePlayer(player);
	}
	
	public void updateMoney(Integer playerID,Integer money){
		playerDAO.updateMoney(playerID, money);
	}
	
	public void updateHonor(Integer playerID, Integer honorID){
		playerDAO.updateHonorIDByID(playerID, honorID);
	}
	
	public void updateRenown(Integer playerID,Long renown){
		playerDAO.updateRenown(playerID, renown);
	}
	 
	public void updateLastLoginInfo(Integer playerID){
		playerDAO.updateLastLoginInfo(playerID);
	}
	
	public Integer getHonorIDByPlayerID(Integer playerID) {
		return playerDAO.getHonorIDByPlayerID(playerID);
	}
	
	public Player getPlayerInfo(Integer playerID){
		Player player = playerDAO.getPlayerByID(playerID);
		player.setGuildName(guildService.getGuildNameByID(player.getGuildID()));
		if (player.getGuildName() == null) {
			player.setGuildName("无");
		}
		player.setHonorName(honorService.getHonorByID(player.getHonorID()));
		return player;
	}
	
	public Player getPlayerByID(Integer playerID) {

		Player player = playerDAO.getPlayerByID(playerID);

		if (player != null) {
			player.setCity(cityService.getCityByPlayerID(player.getPlayerID()));
			player.setGuildName(guildService.getGuildNameByID(player.getGuildID()));
			if (player.getGuildName() == null) {
				player.setGuildName("无");
			}
			player.setHonorName(honorService.getHonorByID(player.getHonorID()));
		}
		
		return player;
	}
	
	public Integer getPlayerIDByPlayerName(String playerName) {
		return playerDAO.getPlayerIDByPlayerName(playerName);
	}

	@SuppressWarnings("unchecked")
	public String getPlayerNameByPlayerID(Integer playerID) {
		return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERNAME_MAP)).get(playerID);
	}

	public Player getPlayerByUserName(String userName) {

		Player player = playerDAO.getPlayerByUserName(userName);

		if (player != null) {
			player.setCity(cityService.getCityWithCityResourceByPlayerID(player.getPlayerID()));
			player.setGuildName(guildService.getGuildNameByID(player.getGuildID()));
			if (player.getGuildName() == null) {
				player.setGuildName("无");
			}
			player.setHonorName(honorService.getHonorByID(player.getHonorID()));
		}

		return player;
	}
	
	public Map<String, Object> getGameWorldInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int freeUnionPlayerNum = playerDAO.getPlayerNumByCountry(CountryConstant.FREE_UNION);
		int unionEmpirePlayerNum = playerDAO.getPlayerNumByCountry(CountryConstant.UNION_EMPIRE);
		
		resultMap.put("recommendCountry", freeUnionPlayerNum<=unionEmpirePlayerNum?1:2);
		
		resultMap.put("area1_1", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.AREA_1_1));
		resultMap.put("area1_2", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.AREA_1_2));
		resultMap.put("area2_1", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.AREA_2_1));
		resultMap.put("area2_2", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.AREA_2_2));
		resultMap.put("island_1", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.ISLAND_1));
		resultMap.put("island_3", mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, MapConstant.ISLAND_3));
		
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	public void createPlayer(String userName, String playerName, String cityName, Integer contry, String playerImg, Integer mapArea) {

		if (playerName.trim().length()==0 || cityName.trim().length()==0) {
			throw new GameException("市长名称及城市名称不能为空。");
		}
		
		int i;
		boolean isPlayerNameAvailable = true;
		for (i=0;i<SystemConfig.registerFilterWordArray.length;i++) {
			if (playerName.indexOf(SystemConfig.registerFilterWordArray[i])!=-1) {
				isPlayerNameAvailable = false;
				break;
			}
		}
		if (!isPlayerNameAvailable) {
			throw new GameException("市长名称包含非法字符，请重新输入。");
		}
		
		boolean isCityNameAvailable = true;
		for (i=0;i<SystemConfig.registerFilterWordArray.length;i++) {
			if (cityName.indexOf(SystemConfig.registerFilterWordArray[i])!=-1) {
				isCityNameAvailable = false;
				break;
			}
		}
		if (!isCityNameAvailable) {
			throw new GameException("城市名称包含非法字符，请重新输入。");
		}

		//DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		//TransactionStatus status = null;
		
		try {
			createPlayerLock.lock();
			
			// 判断地图人数
			if (mapDAO.getMapNumByCategoryAndArea(MapConstant.CATEGORY_CITY, mapArea)>=MapConstant.MAP_AREA_MAX_CITY_NUM) {
				throw new GameException("该区域玩家数量已达到上限，请选择其他区域进入。");
			}
			
			Player player;
			Integer playerID;
			Integer cityID;
			
			//status = transactionManager.getTransaction(td);
			
			player = playerDAO.getPlayerByUserName(userName);
			if (player!=null) {
				throw new GameException("您的账号已经创建过角色，无法再次创建。");
			}
			
			player = new Player();
			playerID = playerDAO.getPlayerIDByPlayerName(playerName);
			if (playerID != null) {
				throw new GameException("市长名称 " + playerName + " 已存在。");
			}

			cityID = cityService.getCityIDByCityName(cityName);
			if (cityID != null) {
				throw new GameException("城市名称 " + cityName + " 已存在。");
			}

			player.setUserName(userName);
			player.setName(playerName);
			player.setHonorID(1);
			player.setCountry(contry);
			player.setHeadImg(playerImg);
			player.setAttackPoint(0);
			player.setDefensePoint(0);
			player.setRank(playerDAO.getPlayerCount() + 1);
			player.setRenown(0L);
			player.setMoney(0);
			player.setGiftCertificate(0);
			player.setHaveReceiveDailyreward(0);
			player.setState(PlayerStateConstant.FRESHMAN);
			player.setOnlineTime(0);
			player.setLoginNum(1);
			
			playerID = playerDAO.createPlayer(player);

			player.setPlayerID(playerID);
			
			// 创建城市
			cityService.createCity(playerID, cityName, mapArea);
			
			// 初始化用户任务信息
			this.initPlayerTask(player.getPlayerID());
			
			// 向玩家发送消息
			String welcomeContent = TemplateService.format("NewPlayer_Welcome.ftl", null);
			String giftContent = TemplateService.format("NewPlayer_Gift.ftl", null);
			
			messageService.sendMessage(0, playerName, "欢迎您来到《绝地战争》的世界", welcomeContent);
			messageService.sendMessage(0, playerName, "请领取您的新手礼包", giftContent);
			
			//transactionManager.commit(status);
			// 添加玩家编号玩家名称Map缓存
			((Map<Integer, String>)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERNAME_MAP)).put(playerID, playerName);
			
		} catch (Exception e) {
			//transactionManager.rollback(status);
			throw new GameException(e.getMessage());
		} finally {
			createPlayerLock.unlock();
		}
	}
	
	/**
	 * 初始化用户任务信息
	 * @param playerID
	 */
	private void initPlayerTask(int playerID){
		// 初始任务编号
		int[] initTasks = {1001,2080,3001,3002,3003,3004,3005,3007,4001,4002,4003,4004,4005,4006,4007,4008,4009,4010,4011,4012,4013,4014,4015,4016,4017};
		
		PlayerTask playerTask = new PlayerTask();
		playerTask.setPlayerID(playerID);
		playerTask.setState(0);
		playerTask.setFlag(0);
		
		for (int taskID:initTasks) {
			playerTask.setTaskID(taskID);
			playerTask.setTaskType(taskService.getTaskByID(taskID).getType());
			playerTaskDAO.createPlayerTask(playerTask);
		}
	}
	
	public java.util.Map<String, Object> loadPlayerGlobalData(String userName){
		
		Player player = this.getPlayerByUserName(userName);
		
		if (player==null) {
			return null;
		}
		
		// 判断用户是否登录，如果已经登录则将原用户Kick
		if (GameSocketService.isSessionExist(player.getPlayerID())) {
			try {
				JSONObject json = new JSONObject();
				json.put("type", 11);
				GameSocketService.sendDataToClient(player.getPlayerID(), json);
				this.addPlayerOnlineTime(player.getPlayerID());
			} catch (JSONException e) {
				logger.error("异常：", e);
			}
		}
		
		// 更新用户的最后登录信息
		this.updateLastLoginInfo(player.getPlayerID());
		
		// 加载游戏数据
		java.util.Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("player", player);
		
		resultMap.put("buildingList", buildingService.getBuildingList());
		resultMap.put("cityBuildingList", buildingService.getCityBuildingListByCityID(player.getCity().getCityID()));
		
		resultMap.put("defenseList", defenseService.getDefenseList());
		resultMap.put("cityDefenseList", cityDefenseService.getCityDefenseList(player.getCity().getCityID()));
		
		resultMap.put("ordnanceList", ordnanceService.getOrdnanceListByCountry(player.getCountry()));
		resultMap.put("cityOrdnanceList", ordnanceService.getCityOrdnanceList(player.getCity().getCityID()));
		
		resultMap.put("cityArmyList", armyService.getCityArmyList(player.getCity().getCityID()));
		resultMap.put("armyList", armyService.getArmyListByContry(player.getCountry()));
		
		resultMap.put("serverTime", DateService.getCurrentUtilDate());
		
		resultMap.put("haveNewReport", reportService.getPlayerUnReadedReportNum(player.getPlayerID())>0);
		resultMap.put("haveNewMessage", messageService.getPlayerUnReadedMessageNum(player.getPlayerID())>0);
		
		resultMap.put("playerTreasureList", treasureService.getPlayerTreasureList(player.getPlayerID()));
		
		return resultMap;
	}
	
	public java.util.Map<String, Object> loadGameInfo(Integer playerID, Integer cityID){
		
		java.util.Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("serverTime", DateService.getCurrentUtilDate());
		resultMap.put("cityPopulation", cityService.getCityPopulation(cityID));
		resultMap.put("playerRenown", playerDAO.getRenownByPlayerID(playerID));
		
		return resultMap;
	}

	public void refreshFreshmanProtect(){
		 List<Player> freshmanList = playerDAO.getFinshedFreshmanProtectList(GameConfig.FRESHMAN_PROTECT_DAY);
		 
		 for(int i=0; i<freshmanList.size(); i++){
			 //发送结束新手保护的消息
			 sendReport(freshmanList.get(i));
			 //将玩家状态改为正常
			 playerDAO.updatePlayerState(freshmanList.get(i).getPlayerID(), PlayerStateConstant.NORMAL);
		 }
	 }
	 
	/**
	 * 发送结束新手保护的消息
	 * @param player
	 */
	private void sendReport(Player player){
		java.util.Map<String, Object> contents = new HashMap<String, Object>();
		contents.put("playerName", player.getName());
		try {
			String reportContent = TemplateService.format("FinishFreshManProtect.ftl", contents);

			reportService.sendOtherReport(player.getPlayerID(),"脱离新手保护报告",reportContent);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
	}
	
	public boolean isPlayerNameExisted(String playerName){
		
		Integer playerID = playerDAO.getPlayerIDByPlayerName(playerName);
		
		if(playerID==null)
			return false;
		else
			return true;
	}
	
	public boolean inProtectPeriod(Integer playerID){
		int state = playerDAO.getPlayerByID(playerID).getState();
		
		if(state != 0)
			return false;
		else
			return true;
	}
	
	public void receiveDailyReward(Integer playerID) {
		
		try {
			receiveDailyRewardLock.lock();
			
			Player player = playerDAO.getPlayerByID(playerID);
			
			if (player.getHaveReceiveDailyreward() == 1) {
				throw new GameException("对不起，您已经领取过每日奖励。");
			}
			
			CityResource cityResource = cityService.getCityResourceByCityID(cityService.getCityIDByPlayerID(playerID));
			
			// 领取每日奖励
			int honor = player.getHonorID();
			if (honor >= HonorConstant.PRIVATE_SOLDIER && honor <= HonorConstant.BASIC_PRIVATE) {
				receiveDailyRewardUpdateInfo(cityResource, player, 0);
			} else if (honor >= HonorConstant.CORPORAL && honor <= HonorConstant.MASTER_SERGEANT) {
				receiveDailyRewardUpdateInfo(cityResource, player, 1);
			} else if (honor >= HonorConstant.SECOND_LIEUTENANT && honor <= HonorConstant.CAPTAIN) {
				receiveDailyRewardUpdateInfo(cityResource, player, 2);
			} else if (honor >= HonorConstant.MAJOR && honor <= HonorConstant.COLONEL) {
				receiveDailyRewardUpdateInfo(cityResource, player, 3);
			} else if (honor >= HonorConstant.BRIGADIER_GENERAL && honor <= HonorConstant.ADMIRAL) {
				receiveDailyRewardUpdateInfo(cityResource, player, 4);
			} else if (honor == HonorConstant.MARSHAL) {
				receiveDailyRewardUpdateInfo(cityResource, player, 5);
			} else if (honor == HonorConstant.SUPER_MARSHAL) {
				receiveDailyRewardUpdateInfo(cityResource, player, 6);
			}
			
			// 更新为已领取奖励
			playerDAO.updateHaveReceiveDailyReward(playerID, 1);
			
		} finally {
			receiveDailyRewardLock.unlock();
		}
	}
	
	/**
	 * 领取每日奖励
	 * @param cityResource
	 * @param player
	 * @param index 常量(DailyRewardConstant.DAILY_REWARD)数组索引
	 */
	private void receiveDailyRewardUpdateInfo(CityResource cityResource, Player player, Integer index){
		
		Long getWoodNum = Math.min(cityResource.getWoodNum() + DailyRewardConstant.DAILY_REWARD[index][0], cityResource.getResourceNumMax());
		Long getSteelNum = Math.min(cityResource.getSteelNum() + DailyRewardConstant.DAILY_REWARD[index][0], cityResource.getResourceNumMax());
		Long getOilNum = Math.min(cityResource.getOilNum() + DailyRewardConstant.DAILY_REWARD[index][0], cityResource.getResourceNumMax());
		Long getFoodNum = Math.min(cityResource.getFoodNum() + DailyRewardConstant.DAILY_REWARD[index][0], cityResource.getResourceNumMax());
		Long getMoneyNum = cityResource.getMoneyNum() + DailyRewardConstant.DAILY_REWARD[index][0];
		
		cityService.updateCityResources(cityResource.getCityID(), getWoodNum, getSteelNum, getOilNum, getFoodNum, getMoneyNum);
		
		armyService.rewardCityArmy(cityResource.getCityID(), ArmyConstant.ARMORED_TRUCK, DailyRewardConstant.DAILY_REWARD[index][1]);

		playerDAO.updateGiftCertificate(player.getPlayerID(), player.getGiftCertificate() + DailyRewardConstant.DAILY_REWARD[index][2]);
	}

	public void changePlayersHaveReceiveDailyRewardToNotReceive() {
		playerDAO.updateHaveReceiveDailyRewardToNotReceive();
	}
	
	public void updateHaveReceiveDailyReward(Integer playerID, Integer receiveState) {
		playerDAO.updateHaveReceiveDailyReward(playerID, receiveState);
	}

	public void applyAddFriend(Integer playerID, Integer targetPlayerID) {
		
		if (playerID.intValue() == targetPlayerID.intValue()) {
			throw new GameException("不能添加自己为好友。");
		}
		
		if (playerDAO.getPlayerByID(targetPlayerID) == null) {
			throw new GameException("该玩家不存在。");
		}
		
		if (friendDAO.getFriendListByPlayerIDAndTargetPlayerID(playerID, targetPlayerID).size() > 0) {
			throw new GameException("该玩家已经存在您的好友列表中。");
		}
		
		Friend friend = new Friend();
		Friend targetFriend = new Friend();
		
		friend.setPlayerID(playerID);
		friend.setTargetPlayerID(targetPlayerID);
		friend.setState(FriendStateConstant.APPLING);
		friend.setCreateTime(new Date());
		friendDAO.createFriend(friend);
		
		targetFriend.setPlayerID(targetPlayerID);
		targetFriend.setTargetPlayerID(playerID);
		targetFriend.setState(FriendStateConstant.AUDITING);
		targetFriend.setCreateTime(new Date());
		friendDAO.createFriend(targetFriend);
		
		// 发送消息
		reportService.sendOtherReport(targetPlayerID, "好友申请报告", "玩家“" + playerDAO.getPlayerNameByPlayerID(playerID) + "”想加你为好友，您可以在好友界面查看或取消。");
	}
	
	public void applyAddFriend(Integer playerID, String targetPlayerName) {
		
		Integer targetPlayerID = playerDAO.getPlayerIDByPlayerName(targetPlayerName);
		if (targetPlayerID == null) {
			throw new GameException("该玩家不存在。");
		}
		
		this.applyAddFriend(playerID, targetPlayerID);
	}
	
	public void acceptAddFriendApply(Integer playerID, Integer targetPlayerID) {
		friendDAO.updateStateByPlayerIDAndTargetPlayerID(FriendStateConstant.NORMAL, playerID, targetPlayerID);
		// 发送消息
		reportService.sendOtherReport(targetPlayerID, "好友申请结果报告", "玩家“" + this.getPlayerNameByPlayerID(playerID) +  "”接受了你的好友申请。");
	}
	
	public void refuseAddFriendApply(Integer playerID, Integer targetPlayerID) {
		friendDAO.deleteFriendByPlayerIDAndTargetPlayerID(playerID, targetPlayerID);
		// 发送消息
		reportService.sendOtherReport(targetPlayerID, "好友申请结果报告", "玩家“" + this.getPlayerNameByPlayerID(playerID) +  "”拒绝了你的好友申请。");
	}

	public void deleteFriend(Integer playerID, Integer targetPlayerID) {
		friendDAO.deleteFriendByPlayerIDAndTargetPlayerID(playerID, targetPlayerID);
		// 发送消息
		reportService.sendOtherReport(targetPlayerID, "好友取消报告", "玩家“" + this.getPlayerNameByPlayerID(playerID) + "”解除了与您之间的好友关系。");
	}

	public List<Friend> getFriendList(Integer playerID) {
		List<Friend> friendList =  friendDAO.getFriendListByPlayerID(playerID);
		City city;
		Player targetPlayer;
		
		for (Friend friend : friendList) {
			targetPlayer = playerDAO.getPlayerByID(friend.getTargetPlayerID());
			city = cityService.getCityByPlayerID(targetPlayer.getPlayerID());
			
			friend.setTargetPlayer(targetPlayer);
			friend.setPosX(city.getPosX());
			friend.setPosY(city.getPosY());
		}
		
		return friendList;
	}
	
	public Integer getFriendNum(Integer playerID) {
		return friendDAO.getFriendNumByPlayerID(playerID);
	}
	
	public void updateGiftCertificate(Integer playerID, Integer giftCertificate) {
		playerDAO.updateGiftCertificate(playerID, giftCertificate);
	}
	

	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public IFriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(IFriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}
	
	public ICityDefenseDAO getCityDefenseDAO() {
		return cityDefenseDAO;
	}

	public void setCityDefenseDAO(ICityDefenseDAO cityDefenseDAO) {
		this.cityDefenseDAO = cityDefenseDAO;
	}

	public ICityArmyDAO getCityArmyDAO() {
		return cityArmyDAO;
	}

	public void setCityArmyDAO(ICityArmyDAO cityArmyDAO) {
		this.cityArmyDAO = cityArmyDAO;
	}

	public IDefenseDAO getDefenseDAO() {
		return defenseDAO;
	}

	public void setDefenseDAO(IDefenseDAO defenseDAO) {
		this.defenseDAO = defenseDAO;
	}

	public IPlayerTreasureDAO getPlayerTreasureDAO() {
		return playerTreasureDAO;
	}

	public void setPlayerTreasureDAO(IPlayerTreasureDAO playerTreasureDAO) {
		this.playerTreasureDAO = playerTreasureDAO;
	}
	
	public IPlayerTaskDAO getPlayerTaskDAO() {
		return playerTaskDAO;
	}

	public void setPlayerTaskDAO(IPlayerTaskDAO playerTaskDAO) {
		this.playerTaskDAO = playerTaskDAO;
	}

	public IMapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(IMapDAO mapDAO) {
		this.mapDAO = mapDAO;
	}
	
	public IHonorService getHonorService() {
		return honorService;
	}

	public void setHonorService(IHonorService honorService) {
		this.honorService = honorService;
	}
	
	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public ICityDefenseService getCityDefenseService() {
		return cityDefenseService;
	}

	public void setCityDefenseService(ICityDefenseService cityDefenseService) {
		this.cityDefenseService = cityDefenseService;
	}

	public IMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

	public IOrdnanceService getOrdnanceService() {
		return ordnanceService;
	}

	public void setOrdnanceService(IOrdnanceService ordnanceService) {
		this.ordnanceService = ordnanceService;
	}

	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}

	public IDefenseService getDefenseService() {
		return defenseService;
	}

	public void setDefenseService(IDefenseService defenseService) {
		this.defenseService = defenseService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public IReferenceAccountDAO getReferenceAccountDAO() {
		return referenceAccountDAO;
	}

	public void setReferenceAccountDAO(IReferenceAccountDAO referenceAccountDAO) {
		this.referenceAccountDAO = referenceAccountDAO;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
