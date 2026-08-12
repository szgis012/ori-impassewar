package com.war.dao;

import java.util.Date;
import java.util.List;

import com.war.domain.Player;

public interface IPlayerDAO {

	/**
	 * 创建玩家
	 * @param player
	 * @return
	 */
	public Integer createPlayer(Player player);

	/**
	 * 增加玩家声望
	 * @param playerID
	 * @param reown
	 */
	public void addPlayerRenown(Integer playerID,Long renown);
	
	/**
	 * 增加玩家在线时间
	 * @param playerID
	 * @param onlineTime
	 */
	public void addPlayerOnlineTime(Integer playerID,Integer onlineTime);
	
	/**
	 * 更新玩家
	 * @param player
	 */
	public void updatePlayer(Player player);
	
	/**
	 * 更新玩家的金币数量
	 * @param playerID 玩家编号
	 * @param money 金币数量
	 */
	public void updateMoney(Integer playerID,Integer money);
	
	/**
	 * 根据编号更新玩家军衔编号
	 * @param playerID
	 * @param honorID
	 */
	public void updateHonorIDByID(Integer playerID, Integer honorID);
	
	/**
	 * 更新声望值
	 * @param renown 声望值
	 */
	public void updateRenown(Integer playerID,Long renown);
	
	/**
	 * 根据玩家编号删除玩家
	 * @param playerID
	 */
	public void deletePlayerByID(Integer playerID);

	/**
	 * 根据玩家编号获得军衔编号
	 * @param playerID
	 * @return
	 */
	public Integer getHonorIDByPlayerID(Integer playerID);
	
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
	 * 根据玩家编号获得最后登录时间
	 * @param playerID
	 * @return
	 */
	public Date getLastLoginTimeByPlayerID(Integer playerID);
	
	/**
	 * 根据玩家编号获得玩家
	 * @param playerID
	 * @return
	 */
	public Player getPlayerByID(Integer playerID);

	/**
	 * 获得玩家列表
	 * @return
	 */
	public List<Player> getPlayerList();
	
	/**
	*  根据用户名获得玩家
	* @param userName
	* @return
	*/
	public Player getPlayerByUserName(String userName);
	
	/**
	 * 获得当前系统所有玩家的数量
	 * @return
	 */
	public Integer getPlayerCount();
	
	/**
	 * 更新用户最后登陆相关的信息
	 * @param playerID
	 */
	public void updateLastLoginInfo(Integer playerID);
	
	/**
	 * 更新用户每日奖励状态为没有领取奖励
	 */
	public void updateHaveReceiveDailyRewardToNotReceive();
	
	/**
	 * 根据玩家编号更新每日奖励是否已领取
	 * @param palyerID
	 */
	public void updateHaveReceiveDailyReward(Integer playerID, Integer haveReceiveDailyreward);
	
	/**
	 * 更新玩家礼金信息
	 * @param playerID
	 * @param giftCertificate
	 */
	public void updateGiftCertificate(Integer playerID, Integer giftCertificate);
	
	/**
	 * 更新玩家状态信息
	 * @param playerID 
	 * @param state 状态在PlayerStateConstant中定义
	 */
	public void updatePlayerState(Integer playerID, Integer state);
   
	/**
	 * 根据国家获得玩家数量
	 * @param country
	 * @return
	 */
	public Integer getPlayerNumByCountry(Integer country);
	
	/**
	 * 获得所有即将脱离新手保护的玩家列表
	 * @param day 新手保护期天数
	 * @return
	 */
	public List<Player> getFinshedFreshmanProtectList(Integer day);
   
	/**
	 * 根据玩家编号获得声望
	 * @param playerID
	 * @return
	 */
	public Integer getRenownByPlayerID(Integer playerID);
   
	/**
	 * 获得玩家创建账号的日期
	 * @param playerID
	 * @return
	 */
	public Date getPlayerCreateTime(Integer playerID);
   
}