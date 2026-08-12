package com.war.webservice;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.war.common.ConfigurationService;
import com.war.common.MD5Service;
import com.war.constant.WebServiceAuthenticationConstant;
import com.war.dao.IPayHistoryDAO;
import com.war.domain.PayHistory;
import com.war.domain.Player;
import com.war.service.IPlayerService;

/**
 * 充值WebService
 * @author TopTong
 *
 */
@WebService(endpointInterface="com.war.webservice.IPayWS")
public class PayWS implements IPayWS {

	private IPayHistoryDAO payHistoryDAO;
	
	private IPlayerService playerService;
	
	private DataSourceTransactionManager transactionManager;
	
	private static Logger logger = Logger.getLogger(PayWS.class);
	
	
	public Boolean isPlayerExisted(String userName) {
		if (playerService.getPlayerByUserName(userName)!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized Integer addPlayerMoney(String userName, Integer money, String verifyString) {
		
		if (!checkVerifyString(userName, money, verifyString)) {
			return 0;
		}
		
		Player player = playerService.getPlayerByUserName(userName);
		if (player==null) {
			logger.info("充值失败：玩家信息不存在。");
			return 0;
		}
		
		//DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(td);
		
		try {
			// 创建充值日志
			PayHistory payHistory = new PayHistory();
			payHistory.setPlayerID(player.getPlayerID());
			payHistory.setAmount(money);
			payHistoryDAO.createPayHistory(payHistory);
			
			player.setMoney(player.getMoney() + money*10);
			playerService.updateMoney(player.getPlayerID(), player.getMoney());
			
			//transactionManager.commit(status);
			logger.info("充值成功：用户名：" + userName + "。金额：" + money + "元。");
			return 1;
		} catch (Exception e) {
			//transactionManager.rollback(status);
			logger.info("充值失败：", e);
			return 0;
		}
		
	}

	private boolean checkVerifyString(String userName, Integer money, String verifyString) {
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(userName);
		stringBuffer.append(money);
		stringBuffer.append(ConfigurationService.getProperty("PayWebServiceKey"));
		stringBuffer.append(WebServiceAuthenticationConstant.WS_PAY_KEY);

		String resultString = null;
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
	
	
	public IPayHistoryDAO getPayHistoryDAO() {
		return payHistoryDAO;
	}

	public void setPayHistoryDAO(IPayHistoryDAO payHistoryDAO) {
		this.payHistoryDAO = payHistoryDAO;
	}
	
	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
