package com.war.webservice;

import javax.jws.WebService;

@WebService
public interface IWarWS {

	/**
	 * 获得在线玩家列表(格式为 玩家编号,玩家编号,玩家编号,……)
	 * @param verifyString
	 * @return 在线玩家编号字符串
	 */
	public String getOnlinePlayerArray(String verifyString);
	
	/**
	 * 添加系统公告
	 * @param verifyString
	 * @param content
	 */
	public void addSystemNotice(String verifyString, String content);
	
	/**
	 * 删除系统公告
	 * @param verifyString
	 * @param systemNoticeID
	 */
	public void deleteSystemNotice(String verifyString, Integer systemNoticeID);
	
	/**
	 * 发送系统公告
	 * @param verifyString
	 * @param content
	 */
	public void sendSystemNotice(String verifyString, String content);
	
	/**
	 * 初始化游戏地图
	 * @param verifyString
	 */
	public void initGameMap(String verifyString);
	
	/**
	 * 初始化地图野怪
	 * @param verifyString
	 */
	public void initMapMonster(String verifyString);

}
