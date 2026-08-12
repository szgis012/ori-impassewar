package com.war.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.war.common.DateService;
import com.war.common.TemplateService;
import com.war.constant.PlayerStateConstant;
import com.war.dao.IDeclareWarDAO;
import com.war.domain.DeclareWar;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IDeclareWarService;
import com.war.service.IPlayerService;
import com.war.service.IReportService;
import com.war.service.ITreasureQueueService;

/**
 * 宣战Service实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class DeclareWarService implements IDeclareWarService {
	
	private IDeclareWarDAO declareWarDAO ;
	
	private IPlayerService playerService;
	
	private IReportService reportService;
	
	private ITreasureQueueService treasureQueueService;
	
	private ICityService cityService;
	
	private static Logger logger = Logger.getLogger(DeclareWarService.class);
	
	// 从宣战到开战的间隔时间,12小时
	public static final long WAR_START_INTERVAL_TIME = 12 * 60 * 60 * 1000;
	
	// 从开战到结束的间隔时间,72小时
	public static final long WAR_END_INTERVAL_TIME = 72 * 60 * 60 * 1000;
	
	public DeclareWar declareWar(Integer playerID, Integer targetPlayerID) {
		
		DeclareWar declareWar = declareWarDAO.getDeclareWarByPlayerIDAndTargetPlayerID(playerID, targetPlayerID);
		
		if(declareWar!=null){
			throw new GameException("您已向对方宣战。");
		}
		
		declareWar = declareWarDAO.getDeclareWarByPlayerIDAndTargetPlayerID(targetPlayerID,playerID);
		
		if (declareWar!=null)
			throw new GameException("对方已向您宣战。");
		if (playerService.inProtectPeriod(playerID))
			throw new GameException("您仍处在新手保护期内，无法向对方宣战。");
		if (playerService.inProtectPeriod(targetPlayerID))
			throw new GameException("对方仍处在新手保护期内，无法向对方宣战。");
		if (playerService.getPlayerByID(targetPlayerID).getState() == PlayerStateConstant.FREEWAR)
			throw new GameException("您进攻的城市正受到【中立照会】的保护，无法对其宣战。");
		
		
		declareWar = new DeclareWar();
		declareWar.setPlayerID(playerID);
		declareWar.setTargetPlayerID(targetPlayerID);
		//战争开始时间
		Date startDate = new Date();
		//战争结束时间
		Date endDate = new Date();
		
		startDate.setTime(startDate.getTime() + WAR_START_INTERVAL_TIME);
		endDate.setTime(endDate.getTime() +  WAR_START_INTERVAL_TIME + WAR_END_INTERVAL_TIME);
		
		declareWar.setStartTime(startDate);
		declareWar.setFinishTime(endDate);
		
		declareWar.setDeclareWarID(declareWarDAO.createDeclareWar(declareWar));
		
		// 生成报告
		Map<String, Object> contents = new HashMap<String, Object>();
		String playerName = playerService.getPlayerNameByPlayerID(playerID);
		String targetPlayerName = playerService.getPlayerNameByPlayerID(targetPlayerID);
		contents.put("type", 1);
		contents.put("playerName", targetPlayerName);
		contents.put("startTime", DateService.parseDateToReportTimeString(startDate));
		contents.put("endTime", DateService.parseDateToReportTimeString(endDate));
		contents.put("useTreasure", false);
		
		try {
			// 宣战方的内容
			String rportContent = TemplateService.format("DeclareWar.ftl", contents);
			
			reportService.sendOtherReport(playerID, "您已经向玩家"+targetPlayerName+"宣战", rportContent);
			
			// 宣战目标方的内容
			contents.put("type", 2);
			contents.put("playerName", playerName);
			rportContent = TemplateService.format("DeclareWar.ftl", contents);
			
			reportService.sendOtherReport(targetPlayerID, "玩家"+playerName+"已经向您宣战", rportContent);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
		return declareWar;
	}
	
	public DeclareWar declareWarImmediately(Integer playerID, Integer targetPlayerID) {
		
		DeclareWar declareWar = declareWarDAO.getDeclareWarByPlayerIDAndTargetPlayerID(playerID, targetPlayerID);
		
		if (declareWar != null) {
			
			declareWar.setStartTime(new Date());
			declareWar.setFinishTime(new Date(declareWar.getStartTime().getTime() + WAR_END_INTERVAL_TIME));
			declareWarDAO.updateDeclareWar(declareWar);
		
			return declareWar;
		}
		
		declareWar = declareWarDAO.getDeclareWarByPlayerIDAndTargetPlayerID(targetPlayerID,playerID);
		
		if (declareWar != null) 
			throw new GameException("对方已向您宣战。");
		if (playerService.inProtectPeriod(playerID))
			throw new GameException("您仍处在新手保护期内，无法向对方宣战。");
		if (playerService.inProtectPeriod(targetPlayerID))
			throw new GameException("对方仍处在新手保护期内，无法向对方宣战。");
		if (playerService.getPlayerByID(targetPlayerID).getState() == PlayerStateConstant.FREEWAR)
			throw new GameException("您进攻的城市正受到【中立照会】的保护，无法对其宣战。");
		
		declareWar = new DeclareWar();
		declareWar.setStartTime(new Date());
		declareWar.setFinishTime(new Date(declareWar.getStartTime().getTime() + WAR_END_INTERVAL_TIME));
		
		declareWar.setDeclareWarID(declareWarDAO.createDeclareWar(declareWar));
		
		// 生成报告
		Map<String, Object> contents = new HashMap<String, Object>();
		String playerName = playerService.getPlayerNameByPlayerID(playerID);
		String targetPlayerName = playerService.getPlayerNameByPlayerID(targetPlayerID);
		contents.put("type", 1);
		contents.put("playerName", targetPlayerName);
		contents.put("startTime", DateService.parseDateToReportTimeString(declareWar.getStartTime()));
		contents.put("endTime", DateService.parseDateToReportTimeString(declareWar.getFinishTime()));
		contents.put("useTreasure", true);
		
		try {
			// 宣战方的内容
			String rportContent = TemplateService.format("DeclareWar.ftl", contents);
			
			reportService.sendOtherReport(playerID, "您已经向玩家"+targetPlayerName+"宣战", rportContent);
			
			// 宣战目标方的内容
			contents.put("type", 2);
			contents.put("playerName", playerName);
			rportContent = TemplateService.format("DeclareWar.ftl", contents);
			
			reportService.sendOtherReport(targetPlayerID, "玩家"+playerName+"已经向您宣战", rportContent);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
		return declareWar;
	}
	
	public DeclareWar getDeclareWar(Integer playerID,Integer targetPlayerID){
		return declareWarDAO.getDeclareWarByPlayerIDAndTargetPlayerID(playerID, targetPlayerID);
	}

	public List<DeclareWar> getPlayerDeclareWarList(Integer playerID) {
		return declareWarDAO.getDeclareWarListByPlayerID(playerID);
	}
	
	public Integer getDeclareWarCountByPlayerID(Integer playerID){
		return declareWarDAO.getDeclareWarCountByPlayerID(playerID);
	}
	
	public void deleteFinishedDeclareWarList(){
		declareWarDAO.deleteFinishedDeclareWarList();
	}

	
	public IDeclareWarDAO getDeclareWarDAO() {
		return declareWarDAO;
	}

	public void setDeclareWarDAO(IDeclareWarDAO declareWarDAO) {
		this.declareWarDAO = declareWarDAO;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
}
