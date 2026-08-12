package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IPayHistoryDAO;
import com.war.domain.PayHistory;

public class PayHistoryDAOTest {

	private static IPayHistoryDAO payHistoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		payHistoryDAO = (IPayHistoryDAO)SpringService.getApplicationContext().getBean("payHistoryDAO");
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

		Integer playerID = 1;
		Integer amount = 1;

		PayHistory payHistory = new PayHistory();
		
		payHistory.setPlayerID(playerID);
		payHistory.setAmount(amount);

		//测试创建
		Integer payHistoryID = payHistoryDAO.createPayHistory(payHistory);
		assertNotNull(payHistoryID);

		//测试通过编号获得对象
		PayHistory destPayHistory = payHistoryDAO.getPayHistoryByID(payHistoryID);
		assertNotNull(destPayHistory);
		assertEquals(payHistoryID,destPayHistory.getPayHistoryID());
		assertEquals(playerID,destPayHistory.getPlayerID());
		assertEquals(amount,destPayHistory.getAmount());
		
		//测试获得列表
		List<PayHistory> payHistoryList = payHistoryDAO.getPayHistoryList();
		assertFalse(payHistoryList.isEmpty());

		//测试更新
		playerID = 10;
		amount = 10;
		destPayHistory.setPayHistoryID(payHistoryID);
		destPayHistory.setPlayerID(playerID);
		destPayHistory.setAmount(amount);
		payHistoryDAO.updatePayHistory(destPayHistory);
		PayHistory updatedPayHistory = payHistoryDAO.getPayHistoryByID(payHistoryID);
		assertNotNull(updatedPayHistory);
		assertEquals(payHistoryID,updatedPayHistory.getPayHistoryID());
		assertEquals(playerID,updatedPayHistory.getPlayerID());
		assertEquals(amount,updatedPayHistory.getAmount());

		//测试删除
		payHistoryDAO.deletePayHistoryByID(payHistoryID);
		assertNull(payHistoryDAO.getPayHistoryByID(payHistoryID));

	}

}