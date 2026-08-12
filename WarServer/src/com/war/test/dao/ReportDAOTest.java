package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IReportDAO;
import com.war.domain.Report;

public class ReportDAOTest {

	private static IReportDAO reportDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reportDAO = (IReportDAO)SpringService.getApplicationContext().getBean("reportDAO");
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
		String title = "测试字符串";
		String content = "测试字符串";
		Integer type = 1;
		Integer readFlag = 1;
		Integer saveFlag = 1;

		Report report = new Report();
		
		report.setPlayerID(playerID);
		report.setTitle(title);
		report.setContent(content);
		report.setType(type);
		report.setReadFlag(readFlag);
		report.setSaveFlag(saveFlag);

		//测试创建
		Integer reportID = reportDAO.createReport(report);
		assertNotNull(reportID);

		//测试通过编号获得对象
		Report destReport = reportDAO.getReportByID(reportID);
		assertNotNull(destReport);
		assertEquals(reportID,destReport.getReportID());
		assertEquals(playerID,destReport.getPlayerID());
		assertEquals(title,destReport.getTitle());
		assertEquals(content,destReport.getContent());
		assertEquals(type,destReport.getType());
		assertEquals(readFlag,destReport.getReadFlag());
		assertEquals(saveFlag,destReport.getSaveFlag());
		
		//测试获得列表
		List<Report> reportList = reportDAO.getReportList();
		assertFalse(reportList.isEmpty());
		assertFalse(reportDAO.getPlayerReportList(playerID).isEmpty());
		
		assertTrue(reportDAO.getReportCount(playerID, type)>0);
		
		Integer[] reportIDs ={reportID};
		reportDAO.saveReport(reportIDs);
		reportDAO.readReport(reportIDs);

		//测试更新
		playerID = 10;
		title = "字符串修改";
		content = "字符串修改";
		type = 10;
		readFlag = 10;
		saveFlag = 10;
		destReport.setReportID(reportID);
		destReport.setPlayerID(playerID);
		destReport.setTitle(title);
		destReport.setContent(content);
		destReport.setType(type);
		destReport.setReadFlag(readFlag);
		destReport.setSaveFlag(saveFlag);
		reportDAO.updateReport(destReport);
		Report updatedReport = reportDAO.getReportByID(reportID);
		assertNotNull(updatedReport);
		assertEquals(reportID,updatedReport.getReportID());
		assertEquals(playerID,updatedReport.getPlayerID());
		assertEquals(title,updatedReport.getTitle());
		assertEquals(content,updatedReport.getContent());
		assertEquals(type,updatedReport.getType());
		assertEquals(readFlag,updatedReport.getReadFlag());
		assertEquals(saveFlag,updatedReport.getSaveFlag());

		//测试删除
		reportDAO.deleteReportByID(reportID);
		assertNull(reportDAO.getReportByID(reportID));

	}
	
	@Test
	public void testDeleteReport(){
		reportDAO.deleteReport(14);
	}

}