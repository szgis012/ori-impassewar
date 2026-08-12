package com.war.dao;

import java.util.List;

import com.war.domain.Friend;

public interface IFriendDAO {

	/**
	 * 创建好友
	 * @param friend
	 * @return
	 */
	public Integer createFriend(Friend friend);

	/**
	 * 更新好友
	 * @param friend
	 */
	public void updateFriend(Friend friend);

	/**
	 * 根据玩家编号和目标玩家编号更新好友状态
	 * @param playerID
	 * @param targetPlayerID
	 */
	public void updateStateByPlayerIDAndTargetPlayerID(Integer state, Integer playerID, Integer targetPlayerID);
	
	/**
	 * 根据玩家编号和目标玩家编号删除好友
	 * @param playerID
	 * @param targetPlayerID
	 */
	public void deleteFriendByPlayerIDAndTargetPlayerID(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 根据编号删除好友
	 * @param friendID
	 */
	public void deleteFriendByID(Integer friendID);

	/**
	 * 根据编号获得好友
	 * @param friendID
	 * @return
	 */
	public Friend getFriendByID(Integer friendID);

	/**
	 * 获得好友列表
	 * @return
	 */
	public List<Friend> getFriendList();


	/**
	 * 根据玩家编号获得好友列表
	 * @param playerID
	 * @return
	 */
	public List<Friend> getFriendListByPlayerID(Integer playerID);
	
	/**
	 * 根据玩家编号和目标玩家编号获得好友列表
	 * @param playerID
	 * @param targetPlayerID
	 * @return
	 */
	public List<Friend> getFriendListByPlayerIDAndTargetPlayerID(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 根据玩家编号获得其好友数目
	 * @param playerID
	 * @return
	 */
	public Integer getFriendNumByPlayerID(Integer playerID);
}
