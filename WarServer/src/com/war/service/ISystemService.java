package com.war.service;

import java.util.List;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.domain.SystemNotice;

/**
 * 系统服务接口
 * @author TopTong
 *
 */
public interface ISystemService {

	/**
	 * 初始化系统公告列表
	 * @return
	 */
	public List<SystemNotice> initSystemNoticeList();
	
	/**
	 * 添加系统公告
	 * @param content
	 */
	public void addSystemNotice(String content);
	
	/**
	 * 删除系统公告
	 * @param systemNoticeID
	 */
	public void deleteSystemNotice(Integer systemNoticeID);
	
	/**
	 * 发送系统公告
	 */
	public void sendSystemNotice();
	
}
