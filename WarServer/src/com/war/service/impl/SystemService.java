package com.war.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.dao.ISystemNoticeDAO;
import com.war.domain.SystemNotice;
import com.war.service.ISystemService;
import com.war.socket.game.GameSocketService;

public class SystemService implements ISystemService {

	private ISystemNoticeDAO systemNoticeDAO;

	private static int CURRENT_NOTICE_INDEX;
	
	private static Logger logger = Logger.getLogger(SystemService.class);
	
	
	public List<SystemNotice> initSystemNoticeList() {
		return systemNoticeDAO.getSystemNoticeList();
	}
	
	@SuppressWarnings("unchecked")
	public void addSystemNotice(String content) {
		
		SystemNotice systemNotice = new SystemNotice();
		systemNotice.setContent(content);
		systemNoticeDAO.createSystemNotice(systemNotice);

		// 更新缓存数据
		CacheService.putToCache(CacheConstant.SYSTEM_NOTICE_LIST, systemNoticeDAO.getSystemNoticeList());
	}
	
	@SuppressWarnings("unchecked")
	public void deleteSystemNotice(Integer systemNoticeID) {
		
		systemNoticeDAO.deleteSystemNoticeByID(systemNoticeID);
		
		// 更新缓存数据
		CacheService.putToCache(CacheConstant.SYSTEM_NOTICE_LIST, systemNoticeDAO.getSystemNoticeList());
	}
	
	@SuppressWarnings("unchecked")
	public void sendSystemNotice() {
		
		List<SystemNotice> systemNoticeList = (List<SystemNotice>)CacheService.getFromCache(CacheConstant.SYSTEM_NOTICE_LIST);
		
		if (systemNoticeList.size()==0) {
			return;
		}
		
		if (CURRENT_NOTICE_INDEX>=systemNoticeList.size()) {
			CURRENT_NOTICE_INDEX = 0;
		}
		
		String content = systemNoticeList.get(CURRENT_NOTICE_INDEX).getContent();
		
		JSONObject json = new JSONObject();
		try {
			json.put("type", 1);
			json.put("message", content);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		GameSocketService.sendToAllClient(json);
		
		CURRENT_NOTICE_INDEX++;
	}


	public ISystemNoticeDAO getSystemNoticeDAO() {
		return systemNoticeDAO;
	}

	public void setSystemNoticeDAO(ISystemNoticeDAO systemNoticeDAO) {
		this.systemNoticeDAO = systemNoticeDAO;
	}

}
