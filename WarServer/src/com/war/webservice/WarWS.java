package com.war.webservice;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.war.common.ConfigurationService;
import com.war.common.MD5Service;
import com.war.constant.WebServiceAuthenticationConstant;
import com.war.service.IMapService;
import com.war.service.IMonsterService;
import com.war.service.ISystemService;
import com.war.socket.game.GameSocketService;

/**
 * 游戏WebService(后台调用)
 * @author TopTong
 *
 */
@WebService(endpointInterface="com.war.webservice.IWarWS")
public class WarWS implements IWarWS {

	private IMapService mapService;
	
	private IMonsterService monsterService;
	
	private ISystemService systemService;
	
	private static Logger logger = Logger.getLogger(WarWS.class);
	
	
	public String getOnlinePlayerArray(String verifyString){
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		return GameSocketService.getOnlinePlayerArray();
	}
	
	public void addSystemNotice(String verifyString, String content) {
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		systemService.addSystemNotice(content);
	}
	
	public void deleteSystemNotice(String verifyString, Integer systemNoticeID) {
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		systemService.deleteSystemNotice(systemNoticeID);
	}
	
	public void sendSystemNotice(String verifyString, String content){
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		GameSocketService.sendSystemNotice(content);
	}
	
	public void initGameMap(String verifyString){
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		mapService.generateGameMap();
	}
	
	public void initMapMonster(String verifyString){
		if (!this.checkVerifyString(verifyString)) {
			throw new RuntimeException("非法密匙。");
		}
		monsterService.generateMapMonsterList();
	}
	
	private boolean checkVerifyString(String verifyString) {
		
		String resultString = null;
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(ConfigurationService.getProperty("WarWebServiceKey"));
		stringBuffer.append(WebServiceAuthenticationConstant.WS_WAR_KEY);
		try {
			resultString = MD5Service.encryptString(stringBuffer.toString());
		} catch (Exception e) {
			logger.error("异常：", e);
			return false;
		}
		if (resultString.equals(verifyString)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public IMapService getMapService() {
		return mapService;
	}

	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}

	public IMonsterService getMonsterService() {
		return monsterService;
	}

	public void setMonsterService(IMonsterService monsterService) {
		this.monsterService = monsterService;
	}

	public ISystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(ISystemService systemService) {
		this.systemService = systemService;
	}

}
