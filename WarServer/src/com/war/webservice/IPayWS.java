package com.war.webservice;

import javax.jws.WebService;

@WebService
public interface IPayWS {

	/**
	 * 玩家是否存在
	 * @param userName 用户名
	 * @return true:存在 false:不存在
	 */
	public Boolean isPlayerExisted(String userName);
	
	/**
	 * 增加玩家金币
	 * @param userName 用户名
	 * @param money 金币数量
	 * @param verifyString 验证字符串
	 * @return 充值成功返回1 充值失败返回(密匙验证失败)0
	 */
	public Integer addPlayerMoney(String userName, Integer money, String verifyString);
	
}
