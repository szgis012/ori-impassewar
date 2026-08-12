package com.war.dao;

import java.util.List;

import com.war.domain.SystemNotice;

/**
 * 系统公告DAO接口
 * @author TopTong
 *
 */
public interface ISystemNoticeDAO {

	/**
	 * 创建系统公告
	 * @param systemNotice
	 * @return
	 */
	public Integer createSystemNotice(SystemNotice systemNotice);

	/**
	 * 更新系统公告
	 * @param systemNotice
	 */
	public void updateSystemNotice(SystemNotice systemNotice);

	/**
	 * 根据编号删除系统公告
	 * @param systemNoticeID
	 */
	public void deleteSystemNoticeByID(Integer systemNoticeID);

	/**
	 * 根据编号获得系统公告
	 * @param systemNoticeID
	 * @return
	 */
	public SystemNotice getSystemNoticeByID(Integer systemNoticeID);

	/**
	 * 获得系统公告列表
	 * @return
	 */
	public List<SystemNotice> getSystemNoticeList();

}