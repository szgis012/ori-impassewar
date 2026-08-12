package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IDataHistoryDAO;
import com.war.domain.DataHistory;

public class DataHistoryDAOTest {

	private static IDataHistoryDAO dataHistoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataHistoryDAO = (IDataHistoryDAO)SpringService.getApplicationContext().getBean("dataHistoryDAO");
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

		Long dataHistoryID = 1L;
		Integer onlinePlayerNum = 1;

		DataHistory dataHistory = new DataHistory();
		
		dataHistory.setDataHistoryID(dataHistoryID);
		dataHistory.setOnlinePlayerNum(onlinePlayerNum);

		//测试创建
		dataHistoryDAO.createDataHistory(dataHistory);

		//测试通过编号获得对象
		DataHistory destDataHistory = dataHistoryDAO.getDataHistoryByID(dataHistoryID);
		assertNotNull(destDataHistory);
		assertEquals(dataHistoryID,destDataHistory.getDataHistoryID());
		assertEquals(onlinePlayerNum,destDataHistory.getOnlinePlayerNum());
		
		//测试获得列表
		List<DataHistory> dataHistoryList = dataHistoryDAO.getDataHistoryList();
		assertFalse(dataHistoryList.isEmpty());

		//测试更新
		onlinePlayerNum = 10;
		destDataHistory.setDataHistoryID(dataHistoryID);
		destDataHistory.setOnlinePlayerNum(onlinePlayerNum);
		dataHistoryDAO.updateDataHistory(destDataHistory);
		DataHistory updatedDataHistory = dataHistoryDAO.getDataHistoryByID(dataHistoryID);
		assertNotNull(updatedDataHistory);
		assertEquals(dataHistoryID,updatedDataHistory.getDataHistoryID());
		assertEquals(onlinePlayerNum,updatedDataHistory.getOnlinePlayerNum());

		//测试删除
		dataHistoryDAO.deleteDataHistoryByID(dataHistoryID);
		assertNull(dataHistoryDAO.getDataHistoryByID(dataHistoryID));

	}

}