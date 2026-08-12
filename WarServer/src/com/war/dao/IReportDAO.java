package com.war.dao;


import java.util.List;

import com.war.domain.Report;

/**
 * 报告信息dao
 *
 * @author ghleed
 * @version 1.0
 */
public interface IReportDAO {

	public Integer createReport(Report report);

	public void updateReport(Report report);

	public void deleteReportByID(Integer reportID);

	public Report getReportByID(Integer reportID);

	public List<Report> getReportList();
	
	/**
	 * 删除多个报告信息
	 * @param reportIDs
	 */
	public void deleteReport(Integer[] reportIDs);
	
	/**
	 * 保存多个报告信息
	 * @param reportIDs
	 */
	public void saveReport(Integer[] reportIDs);
	
	/**
	 * 设置多个报告为已读状态
	 * @param reportIDs
	 */
	public void readReport(Integer[] reportIDs);
	
	/**
	 * 根据已读标示及玩家编号获得报告数量
	 * @param readFlag
	 * @param playerID
	 * @return
	 */
	public Integer getReportNumByReadFlagAndPlayerID(Integer readFlag,Integer playerID);
	
	/**
	 * 得到玩家所有的报告信息
	 * @param playerID 玩家编号
	 * @return
	 */
	public List<Report> getPlayerReportList(Integer playerID);
	
	
	/**
	 * 得到玩家某类报告信息(带分页)
	 * @param playerID 玩家编号
	 * @param type 报告类型ReportTypeConstant定义
	 * @param start 记录的开始位置,以0开始的索引
	 * @param offset 获取的记录条数
	 * @return
	 */
	public List<Report> getPaginateReportList(Integer playerID,Integer type,Integer start,Integer offset);	
	
	/**
	 * 获得某个玩家某种报告的总共数量
	 * @param playerID 玩家编号
	 * @param type 报告类型ReportTypeConstant定义
	 * @return
	 */
	public Integer getReportCount(Integer playerID,Integer type);
	
	/**
	 * 删除指定天数前的报告
	 * (已保存的报告不在删除范围)
	 * @param day
	 */
	public void deleteReport(Integer day);

}