package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IFriendDAO;
import com.war.domain.Friend;

public class FriendDAO extends SqlMapClientDaoSupport implements IFriendDAO {

	public Integer createFriend(Friend friend) {
		return (Integer)this.getSqlMapClientTemplate().insert("Friend.createFriend", friend);
	}

	public void updateFriend(Friend friend) {
		this.getSqlMapClientTemplate().update("Friend.updateFriend", friend);
	}

	public void updateStateByPlayerIDAndTargetPlayerID(Integer state, Integer playerID, Integer targetPlayerID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", state);
		params.put("playerID", playerID);
		params.put("targetPlayerID", targetPlayerID);
		
		this.getSqlMapClientTemplate().update("Friend.updateStateByPlayerIDAndTargetPlayerID", params);
		
	}

	public void deleteFriendByPlayerIDAndTargetPlayerID(Integer playerID, Integer targetPlayerID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("targetPlayerID", targetPlayerID);
		
		this.getSqlMapClientTemplate().delete("Friend.deleteFriendByPlayerIDAndTargetPlayerID", params);
	}

	public void deleteFriendByID(Integer friendID) {
		this.getSqlMapClientTemplate().delete("Friend.deleteFriendByID", friendID);
	}

	public Friend getFriendByID(Integer friendID) {
		return (Friend)this.getSqlMapClientTemplate().queryForObject("Friend.getFriendByID", friendID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendList() {
		return this.getSqlMapClientTemplate().queryForList("Friend.getFriendList");
	}

	@SuppressWarnings("unchecked")
	public List<Friend> getFriendListByPlayerID(Integer playerID) {
		return this.getSqlMapClientTemplate().queryForList("Friend.getFriendListByPlayerID", playerID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendListByPlayerIDAndTargetPlayerID(Integer playerID, Integer targetPlayerID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("targetPlayerID", targetPlayerID);
		
		return this.getSqlMapClientTemplate().queryForList("Friend.getFriendListByPlayerIDAndTargetPlayerID", params);
	}

	public Integer getFriendNumByPlayerID(Integer playerID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Friend.getFriendNumByPlayerID", playerID);
	}
}
