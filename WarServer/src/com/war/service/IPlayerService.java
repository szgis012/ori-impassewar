package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.Friend;
import com.war.domain.Player;

public interface IPlayerService {

	/**
	 * 获得所有的玩家信息
	 * @return
	 */
	public List<Player> getPlayerList();
	
	/**
	 * 初始化玩家编号玩家名称Map
	 * @return 玩家编号玩家名称Map(key:玩家编号 value:玩家名称)
	 */
	public Map<Integer, String> initPlayerIDPlayerNameMap();
	
	/**
	 * 更新玩家
	 * @param player
	 */
	public void updatePlayer(Player player);
	
	/**
	 * 增加玩家声望
	 * @param playerID
	 * @param renown
	 */
	public void addPlayerRenown(Integer playerID,Long renown);
	
	/**
	 * 增加玩家在线时间
	 * @param playerID
	 */
	public void addPlayerOnlineTime(Integer playerID);
	
	/**
	 * 更新玩家的金币数量
	 * @param playerID 玩家编号
	 * @param money 金币数量
	 */
	public void updateMoney(Integer playerID,Integer money);
	
	/**
	 * 更新声望值
	 * @param renown 声望值
	 */
	public void updateRenown(Integer playerID,Long renown);
	
	/**
	 * 根据编号获得玩家信息
	 * @param playerID
	 * @return
	 */
	public Player getPlayerInfo(Integer playerID);
	
	/**
	 * 更新玩家军衔编号
	 * @param playerID
	 * @param honorID
	 */
	public void updateHonor(Integer playerID, Integer honorID);
	
	/**
	 * 根据玩家编号获得玩家
	 * @param playerID
	 * @return
	 */
	public Player getPlayerByID(Integer playerID);
	
	/**
	 * 根据玩家编号获得玩家名称
	 * @param playerName
	 * @return
	 */
	public Integer getPlayerIDByPlayerName(String playerName);
	
	/**
	 * 根据玩家名称获得玩家编号
	 * @param playerID
	 * @return
	 */
	public String getPlayerNameByPlayerID(Integer playerID);
	
	/**
	* 根据用户名获得玩家
	* @param userID
	* @return
	*/
	public Player getPlayerByUserName(String userName);
	
	/**
	 * 获得游戏世界信息
	 * @return
	 */
	public Map<String, Object> getGameWorldInfo();
	
	/**
	 * 创建玩家
	 * @param userName 用户名
	 * @param playerName 玩家名称
	 * @param cityName 城市名称
	 * @param contry 阵型
	 * @param playerImg 玩家头像
	 * @param mapArea 地图区域
	 */
	public void createPlayer(String userName, String playerName, String cityName, Integer contry, String playerImg, Integer mapArea);
	
	/**
	 * 加载用户基础数据(登录时使用)
	 * @param userName 用户名
	 * @return
	 */
	public Map<String, Object> loadPlayerGlobalData(String userName);
	
	/**
	 * 加载游戏信息(包含玩家，城市等信息，客户端每分钟调用刷新客户端数据)
	 * @param playerID
	 * @param cityID
	 * @return
	 */
	public Map<String, Object> loadGameInfo(Integer playerID, Integer cityID);
	
	/**
	 * 更新用户最后登陆相关的信息
	 * @param playerID
	 */
	public void updateLastLoginInfo(Integer playerID);
	
	/**
	 * 根据玩家编号获得军衔编号
	 * @param playerID
	 * @return
	 */
	public Integer getHonorIDByPlayerID(Integer playerID);
	
	/**
	 * 更新新手保护信息
	 */
	public void refreshFreshmanProtect();
	
	/**
	 * 判断玩家名是否存在
	 * @param playerName
	 * @return
	 */
	public boolean isPlayerNameExisted(String playerName);
	
	/**
	 * 判断玩家是否在新手保护期,如果在返回true，否则返回false
	 */
	public boolean inProtectPeriod(Integer palyerID);
	
	/**
	 * 领取每日奖励
	 */
	public void receiveDailyReward(Integer playerID);
	
	/**
	 * 改变玩家每日奖励状态： 已领取 --> 未领取
	 */
	public void changePlayersHaveReceiveDailyRewardToNotReceive();
	
	/**
	 * 更新玩家每日奖励领取状态信息
	 * @param playerID
	 * @param receiveState 0：未领取；1：已领取
	 */
	public void updateHaveReceiveDailyReward(Integer playerID, Integer receiveState);
	
	/**
	 * 申请添加好友
	 * @param playerID 递交申请的玩家编号
	 * @param targetPlayerID 接受申请的玩家编号
	 */
	public void applyAddFriend(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 申请添加好友
	 * @param playerID
	 * @param targetPlayerName
	 */
	public void applyAddFriend(Integer playerID, String targetPlayerName);
	
	/**
	 * 接受好友申请
	 * @param playerID 执行审批的玩家编号
	 * @param targetPlayerID 接受审批的玩家编号
	 */
	public void acceptAddFriendApply(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 拒绝好友申请
	 * @param playerID 执行拒绝的玩家编号
	 * @param targetPlayerID 被拒绝的玩家编号
	 */
	public void refuseAddFriendApply(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 删除好友
	 * @param playerID
	 * @param targetPlayerID
	 */
	public void deleteFriend(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 获得好友列表
	 * @param playerID
	 */
	public List<Friend> getFriendList(Integer playerID);
	
	/**
	 * 获得好友数量
	 * @param playerID
	 * @return
	 */
	public Integer getFriendNum(Integer playerID);

	/**
	 * 更新用户礼金数量
	 * @param playerID
	 * @param giftCertificate
	 */
	void updateGiftCertificate(Integer playerID, Integer giftCertificate);
	
}
