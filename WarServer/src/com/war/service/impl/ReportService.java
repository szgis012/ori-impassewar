package com.war.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.GameConfig;
import com.war.constant.ReportTypeConstant;
import com.war.dao.IReportDAO;
import com.war.domain.Report;
import com.war.service.IReportService;
import com.war.socket.game.GameSocketService;

public class ReportService implements IReportService {
	
	private IReportDAO reportDAO;
	
	private static Logger logger = Logger.getLogger(ReportService.class);
	
	
	public void sendMilitaryReport(Integer playerID,String title,String content){
		
		content = content.replaceAll("\r\n", "");
		content = content.replaceAll("\t", "");
		Report report = new Report();
		
		report.setPlayerID(playerID);
		report.setTitle(title);
		report.setContent(content);
		report.setType(ReportTypeConstant.MILITARY_ACTION);
		report.setReadFlag(0);
		report.setSaveFlag(0);

		reportDAO.createReport(report);
		
		//向客户端push新报告信息
		JSONObject json = new JSONObject();
		try {
			json.put("type", 3);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		GameSocketService.sendDataToClient(report.getPlayerID(), json);
	}
	
	public void sendOtherReport(Integer playerID,String title,String content){
		
		content = content.replaceAll("\r\n", "");
		content = content.replaceAll("\t", "");
		Report report = new Report();
		
		report.setPlayerID(playerID);
		report.setTitle(title);
		report.setContent(content);
		report.setReadFlag(0);
		report.setSaveFlag(0);
		report.setType(ReportTypeConstant.OTHER);
		
		reportDAO.createReport(report);
		
		//向客户端push新报告信息
		JSONObject json = new JSONObject();
		try {
			json.put("type", 3);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		GameSocketService.sendDataToClient(report.getPlayerID(), json);
	}
	
	public void deleteReport(Integer[] reportIDs){
		reportDAO.deleteReport(reportIDs);
	}
	
	public void deleteOverdueReport(){
		reportDAO.deleteReport(GameConfig.REPORT_OVERDUE_DAY);
	}
	
	public void saveReport(Integer[] reportIDs){
		reportDAO.saveReport(reportIDs);
	}
	
	public void readReport(Integer[] reportIDs){
		reportDAO.readReport(reportIDs);
	}
	
	public Integer getPlayerUnReadedReportNum(Integer playerID){
		return (Integer)reportDAO.getReportNumByReadFlagAndPlayerID(0, playerID);
	}
	
	public List<Report> getPlayerReportList(Integer playerID){
		return reportDAO.getPlayerReportList(playerID);
	}
	
	public List<Report> getPaginateReportList(Integer playerID,Integer type,Integer start,Integer offset){
		return reportDAO.getPaginateReportList(playerID, type, start, offset);
	}
	
	public Integer getReportCount(Integer playerID,Integer type){
		return reportDAO.getReportCount(playerID, type);
	}

	
	public IReportDAO getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(IReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

}
