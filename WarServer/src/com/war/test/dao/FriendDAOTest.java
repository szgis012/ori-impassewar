package com.war.test.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IFriendDAO;
import com.war.domain.Friend;

public class FriendDAOTest {

	private static IFriendDAO friendDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		friendDAO = (IFriendDAO)SpringService.getApplicationContext().getBean("friendDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCURD() {

		Integer friendID = 1;
		Integer playerID = 1;
		Integer targetPlayerID = 1;
		Integer state = 1;
		Date createTime = new Date();

		Friend friend = new Friend();
		
		friend.setFriendID(friendID);
		friend.setPlayerID(playerID);
		friend.setTargetPlayerID(targetPlayerID);
		friend.setState(state);
		friend.setCreateTime(createTime);

		//测试创建
		friendID = friendDAO.createFriend(friend);
		assertNotNull(friendID);

		//测试通过编号获得对象
		Friend destFriend = friendDAO.getFriendByID(friendID);
		assertNotNull(destFriend);
		assertEquals(friendID,destFriend.getFriendID());
		assertEquals(playerID,destFriend.getPlayerID());
		assertEquals(targetPlayerID,destFriend.getTargetPlayerID());
		assertEquals(state,destFriend.getState());
//		assertEquals(createTime,destFriend.getCreateTime());

		//测试获得列表
		List<Friend> friendList = friendDAO.getFriendList();
		assertFalse(friendList.isEmpty());

		//测试更新
		playerID = 10;
		targetPlayerID = 10;
		state = 10;
		destFriend.setFriendID(friendID);
		destFriend.setPlayerID(playerID);
		destFriend.setTargetPlayerID(targetPlayerID);
		destFriend.setState(state);
		destFriend.setCreateTime(createTime);
		friendDAO.updateFriend(destFriend);
		Friend updatedFriend = friendDAO.getFriendByID(friendID);
		assertNotNull(updatedFriend);
		assertEquals(friendID,updatedFriend.getFriendID());
		assertEquals(playerID,updatedFriend.getPlayerID());
		assertEquals(targetPlayerID,updatedFriend.getTargetPlayerID());
		assertEquals(state,updatedFriend.getState());
//		assertEquals(createTime,updatedFriend.getCreateTime());

		//测试删除
		friendDAO.deleteFriendByID(friendID);
		assertNull(friendDAO.getFriendByID(friendID));

	}

}