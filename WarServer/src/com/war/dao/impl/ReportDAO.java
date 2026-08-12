package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IReportDAO;
import com.war.domain.Report;

/**
 * 报告DAO实现
 * @author TopTong
 *
 */
public class ReportDAO extends SqlMapClientDaoSupport implements IReportDAO{

	public Integer createReport(Report report) {
		return (Integer)this.getSqlMapClientTemplate().insert("Report.createReport", report);
	}
	
	public void updateReport(Report report) {
		this.getSqlMapClientTemplate().update("Report.updateReport", report);
	}
	
	public void deleteReportByID(Integer reportID) {
		this.getSqlMapClientTemplate().delete("Report.deleteReportByID", reportID);
	}
	
	public Integer getReportNumByReadFlagAndPlayerID(Integer readFlag,Integer playerID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("readFlag", readFlag);
		params.put("playerID", playerID);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Report.getReportNumByReadFlagAndPlayerID",params);
	}
	
	public Report getReportByID(Integer reportID) {
		return (Report)this.getSqlMapClientTemplate().queryForObject("Report.getReportByID", reportID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Report> getReportList() {
		return this.getSqlMapClientTemplate().queryForList("Report.getReportList");
	}

	/**
	 * 删除多个报告信息
	 * @param reportIDs
	 */
	public void deleteReport(Integer[] reportIDs){
		if(reportIDs == null || reportIDs.length == 0)
			return;
		
		//批量删除报告
		for(int i=0; i<reportIDs.length; i++){
			 this.getSqlMapClientTemplate().delete("Report.deleteReportByID", reportIDs[i]);
		}
	}
	
	
	/**
	 * 设置多个报告为已读状态
	 * @param reportIDs
	 */
	public void readReport(Integer[] reportIDs){
		if(reportIDs == null || reportIDs.length == 0)
			return;
		
			//批量删除报告
			for(int i=0; i<reportIDs.length; i++){
				 this.getSqlMapClientTemplate().update("Report.readReport", reportIDs[i]);
			}
	}
	
	
	/**
	 * 保存多个报告信息
	 * @param reportIDs
	 */
	public void saveReport(Integer[] reportIDs){
		if(reportIDs == null || reportIDs.length == 0)
			return;
		
		//批量删除报告
		for(int i=0; i<reportIDs.length; i++){
			 this.getSqlMapClientTemplate().update("Report.saveReport", reportIDs[i]);
		}
	}
	
	/**
	 * 得到玩家所有的报告信息
	 * @param playerID 玩家编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Report> getPlayerReportList(Integer playerID){
		return this.getSqlMapClientTemplate().queryForList("Report.getPlayerReportList",playerID);
	}
	
	/**
	 * 得到玩家某类报告信息(带分页)
	 * @param playerID 玩家编号
	 * @param type 报告类型ReportTypeConstant定义
	 * @param start 记录的开始位置,以0开始的索引
	 * @param offset 获取的记录条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Report> getPaginateReportList(Integer playerID,Integer type,Integer start,Integer offset){
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("type", type);
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Report.getPaginateReportList",params);
	}
	
	/**
	 * 获得某个玩家某种报告的总共数量
	 * @param playerID 玩家编号
	 * @param type 报告类型ReportTypeConstant定义
	 * @return
	 */
	public Integer getReportCount(Integer playerID,Integer type){
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("type", type);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Report.getReportCount",params);
	}
	
	public void deleteReport(Integer day){
		this.getSqlMapClientTemplate().delete("Report.deleteReport",day);
	}
}