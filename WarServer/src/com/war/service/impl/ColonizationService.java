package com.war.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.war.common.DateService;
import com.war.common.TemplateService;
import com.war.constant.ColonizationConstant;
import com.war.dao.IColonizationDAO;
import com.war.dao.IPlayerDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.domain.Colonization;
import com.war.domain.Player;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IColonizationService;
import com.war.service.IReportService;

public class ColonizationService implements IColonizationService {

	private IColonizationDAO colonizationDAO;
	
	private IPlayerDAO playerDAO;
	
	private ICityService cityService;
	
	private IReportService reportService;
	
	private static Logger logger = Logger.getLogger(ColonizationService.class);
	
	
	public void addColonization(Integer cityID, Integer targetCityID) {

		Colonization colonization = new Colonization(); 
		colonization.setCityID(cityID);
		colonization.setTargetCityID(targetCityID);
		
		colonization.setStartTime(DateService.getCurrentUtilDate());
		Date endDate = new Date();
		endDate.setTime(System.currentTimeMillis() + 3 * 3600 * 1000 * 24);
		colonization.setEndTime(endDate);
		colonization.setHaveImposed(0);
		
		colonizationDAO.createColonization(colonization);
		
		Map<String,Object> reportParams = new HashMap<String,Object>();
		reportParams.put("startTime", DateService.parseDateToReportTimeString(colonization.getStartTime()));
		reportParams.put("endTime", DateService.parseDateToReportTimeString(colonization.getEndTime()));
		
		String attackerReport = null,defenderReport = null;
		try {
			attackerReport = TemplateService.format("Colonization_Attacker.ftl", reportParams);
			defenderReport = TemplateService.format("Colonization_Defender.ftl", reportParams);
		} catch (Exception e) {
			logger.error("异常：", e);
		}
		
		//向殖民方发送报告
		int attackerPlayerID = cityService.getPlayerIDByCityID(cityID);
		reportService.sendMilitaryReport(attackerPlayerID, "殖民城市报告", attackerReport);
		//向被殖民方发送报告
		int defenderPlayerID = cityService.getPlayerIDByCityID(targetCityID);
		reportService.sendMilitaryReport(defenderPlayerID, "城市被殖民报告", defenderReport);
	}

	public void cancelColonization(Integer colonizationID) {
		colonizationDAO.deleteColonizationByID(colonizationID);
	}

	public boolean canCityColonize(Integer cityID) {
		
		this.getCityColonizationNum(cityID);
		return true;
	}
	
	public boolean haveColonized(Integer cityID, Integer targetCityID) {
		if(colonizationDAO.getColonizationByCityIDAndTargetCityID(cityID, targetCityID)==null){
			return false;
		}else{
			return true;
		}
	}
	
	public Integer getCityColonizationNum(Integer cityID){
		return colonizationDAO.getColonizationNumByCityID(cityID);
	}
	
	public List<Colonization> getCityColonizationList(Integer cityID) {
		
		List<Colonization> cityColonizationList = colonizationDAO.getColonizationListByCityID(cityID);
		for(int i=0;i<cityColonizationList.size();i++){
			cityColonizationList.get(i).setTargetCityInfo(cityService.getCityInfoByCityID(cityColonizationList.get(i).getTargetCityID()));
		}
		
		return cityColonizationList;
	}

	public void impose(Integer colonizationID, Integer type) {
		
		Colonization colonization = colonizationDAO.getColonizationByID(colonizationID);
		
		if (colonization==null) {
			throw new GameException("殖民信息不存在。");
		}
		
		if (colonization.getHaveImposed()==1) {
			throw new GameException("您今日已经征收过该殖民城市。");
		}
		
		Integer playerID = cityService.getPlayerIDByCityID(colonization.getCityID());
		Integer targetPlayerID = cityService.getPlayerIDByCityID(colonization.getTargetCityID());
		
		switch(type){
			case 1:
				Player player = playerDAO.getPlayerByID(playerID);
				Player targetPlayer = playerDAO.getPlayerByID(targetPlayerID);
				City city = cityService.getCityByID(colonization.getCityID());
				City targetCity = cityService.getCityByID(colonization.getTargetCityID());
				CityResource targetCityResource = cityService.getCityResourceByCityID(colonization.getTargetCityID());
				Calendar calendar = new GregorianCalendar();
				
				long woodNum = (long)(targetCityResource.getWoodNum()*ColonizationConstant.IMPOSE_PROPORTIONMENT);
				long steelNum = (long)(targetCityResource.getSteelNum()*ColonizationConstant.IMPOSE_PROPORTIONMENT);
				long oilNum = (long)(targetCityResource.getOilNum()*ColonizationConstant.IMPOSE_PROPORTIONMENT);
				long foodNum = (long)(targetCityResource.getFoodNum()*ColonizationConstant.IMPOSE_PROPORTIONMENT);
				long moneyNum = (long)(targetCityResource.getMoneyNum()*ColonizationConstant.IMPOSE_PROPORTIONMENT);
				
				//扣除被殖民城市资源
				cityService.minusCityResources(colonization.getTargetCityID(), woodNum, steelNum, oilNum, foodNum, moneyNum);
				
				//增加殖民城市资源
				cityService.addCityResources(colonization.getCityID(), woodNum, steelNum, oilNum, foodNum, moneyNum);
				
				String reportAttacker = null;
				String reportDefender = null;
				
				Map<String, Object> reportParams = new HashMap<String, Object>();
				
				reportParams.put("woodNum", woodNum);
				reportParams.put("steelNum", steelNum);
				reportParams.put("oilNum", oilNum);
				reportParams.put("foodNum", foodNum);
				reportParams.put("moneyNum", moneyNum);
				
				reportParams.put("month", calendar.get(Calendar.MONTH));
				reportParams.put("day", calendar.get(Calendar.DAY_OF_MONTH));
				reportParams.put("hour", calendar.get(Calendar.HOUR_OF_DAY));
				reportParams.put("minute", calendar.get(Calendar.MINUTE));
				
				
				try {
					// 殖民方报告
					reportParams.put("type", 1);
					reportParams.put("playerName", targetPlayer.getName());
					reportParams.put("cityName", targetCity.getName());
					reportParams.put("posX", targetCity.getPosX());
					reportParams.put("posY", targetCity.getPosY());
					reportAttacker = TemplateService.format("Colonization_Impose.ftl", reportParams);
					
					// 被殖民方报告
					reportParams.put("type", 2);
					reportParams.put("playerName", player.getName());
					reportParams.put("cityName", city.getName());
					reportParams.put("posX", city.getPosX());
					reportParams.put("posY", city.getPosY());
					reportDefender = TemplateService.format("Colonization_Impose.ftl", reportParams);
				} catch (Exception e) {
					logger.error("异常：", e);
				}
				
				reportService.sendOtherReport(playerID, "殖民征收报告", reportAttacker);
				reportService.sendOtherReport(targetCity.getPlayerID(), "殖民征收报告", reportDefender);
				break;
			case 2:
				
				break;
		}
		
		//更新是否已征收状态为已征收
		colonizationDAO.updateHaveImposedByColonizationID(colonizationID, 1);
	}
	
	public void resetColonizationHaveImpose() {
		colonizationDAO.updateHaveImposed(0);
	}
	
	public void handleFinishedColonization() {
		List<Colonization> colonizationList = colonizationDAO.getFinishedColonizationList();
		for (int i=0;i<colonizationList.size();i++) {
			// TODO 发送殖民结束报告
			colonizationDAO.deleteColonizationByID(colonizationList.get(i).getColonizationID());
		}
	}
	
	
	public IColonizationDAO getColonizationDAO() {
		return colonizationDAO;
	}

	public void setColonizationDAO(IColonizationDAO colonizationDAO) {
		this.colonizationDAO = colonizationDAO;
	}
	
	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	
}
