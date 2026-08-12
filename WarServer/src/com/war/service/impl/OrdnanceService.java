package com.war.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.constant.ConstraintDependTypeConstant;
import com.war.constant.GameConstant;
import com.war.constant.OrdnanceConstant;
import com.war.dao.ICityOrdnanceDAO;
import com.war.dao.IOrdnanceDAO;
import com.war.domain.CityOrdnance;
import com.war.domain.ConstraintDepend;
import com.war.domain.Ordnance;
import com.war.service.IConstraintDependService;
import com.war.service.IOrdnanceService;

/**
 * 军械service实现
 *
 * @author ghleed
 */
public class OrdnanceService implements IOrdnanceService {
	
	private IOrdnanceDAO ordnanceDAO ;
	
	private ICityOrdnanceDAO cityOrdnanceDAO;
	
	private IConstraintDependService constraintDependService;
	
	
	public Map<Integer, Ordnance> initOrdnancesMap() {
		Map<Integer, Ordnance> ordnancesMap = new HashMap<Integer, Ordnance>();
		List<Ordnance> ordnanceList = this.getOrdnanceList();
		for (int i=0;i<ordnanceList.size();i++) {
			ordnancesMap.put(ordnanceList.get(i).getOrdnanceID(), ordnanceList.get(i));
		}
		return ordnancesMap;
	}
	
	public List<Ordnance> initFreeUnionOrdnanceList() {
		List<Ordnance> ordnanceList = this.getOrdnanceList();
		List<Ordnance> returnList = new ArrayList<Ordnance>();
		// 排除军械编号
		int[] excludeOrdnanceIDs = new int[]{OrdnanceConstant.TIAO_ZHAN_ZHE_TANKE_CHE_SHEN_LHDG, OrdnanceConstant.TU_JI_BU_QIANG_LHDG};
		
		Ordnance tempOrdnance = null;
		
		for (int i=0;i<ordnanceList.size();i++) {
			tempOrdnance = ordnanceList.get(i);
			
			if (tempOrdnance.getOrdnanceID() == excludeOrdnanceIDs[0] || tempOrdnance.getOrdnanceID() == excludeOrdnanceIDs[1]) {
				continue;
			}
			
			returnList.add(tempOrdnance);
		}
		
		return returnList;
	}
	
	public List<Ordnance> initUnionEmpireOrdnanceList() {
		List<Ordnance> ordnanceList = this.getOrdnanceList();
		List<Ordnance> returnList = new ArrayList<Ordnance>();
		// 排除军械编号
		int[] excludeOrdnanceIDs = new int[]{OrdnanceConstant.LIE_HU_TANKE_CHE_SHEN_ZYLB,OrdnanceConstant.TU_JI_BU_QIANG_ZYLB};
		
		Ordnance tempOrdnance = null;
		for (int i=0;i<ordnanceList.size();i++) {
			tempOrdnance = ordnanceList.get(i);
			
			if (tempOrdnance.getOrdnanceID() == excludeOrdnanceIDs[0] || tempOrdnance.getOrdnanceID() == excludeOrdnanceIDs[1]) {
				continue;
			}
				
			returnList.add(tempOrdnance);
		}
		
		return returnList;
	}
	
	public Integer createCityOrdnance(CityOrdnance cityOrdnance) {
		return cityOrdnanceDAO.createCityOrdnance(cityOrdnance);
	}

	public Integer createOrdnance(Ordnance ordnance) {
		return ordnanceDAO.createOrdnance(ordnance);
	}

	public void deleteCityOrdnanceByID(Integer cityOrdnanceID) {
		cityOrdnanceDAO.deleteCityOrdnanceByID(cityOrdnanceID);
	}

	public void deleteOrdnanceByID(Integer ordnanceID) {
		ordnanceDAO.deleteOrdnanceByID(ordnanceID);
	}

	public CityOrdnance getCityOrdnance(Integer cityID, Integer ordnanceID) {
		return cityOrdnanceDAO.getCityOrdnance(cityID, ordnanceID);
	}

	public CityOrdnance getCityOrdnanceByID(Integer cityOrdnanceID) {
		return cityOrdnanceDAO.getCityOrdnanceByID(cityOrdnanceID);
	}

	public List<CityOrdnance> getCityOrdnanceList() {
		return cityOrdnanceDAO.getCityOrdnanceList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordnance> getOrdnanceListByCountry(int country){
		if (country==GameConstant.COUNTRY_FREE_UNION) {
			return (List<Ordnance>)CacheService.getFromCache(CacheConstant.FREE_UNION_ORDNANCE_LIST);
		} else if (country==GameConstant.COUNTRY_UNION_EMPIRE) {
			return (List<Ordnance>)CacheService.getFromCache(CacheConstant.UNION_EMPIRE_ORDNANCES_LIST);
		}
		return null;
	}

	public List<CityOrdnance> getCityOrdnanceList(Integer cityID) {
		return cityOrdnanceDAO.getCityOrdnanceList(cityID);
	}

	@SuppressWarnings("unchecked")
	public Ordnance getOrdnanceByID(Integer ordnanceID) {
		return ((Map<Integer, Ordnance>)CacheService.getFromCache(CacheConstant.ORDNANCES_MAP)).get(ordnanceID);
	}

	public List<Ordnance> getOrdnanceList() {
		List<Ordnance> list = ordnanceDAO.getOrdnanceList();
		List<ConstraintDepend> cdList;
		ConstraintDepend cd ;
		
		for(Ordnance ordnance : list){
			cdList = constraintDependService.getConstraintDependListByTypeAndTargetID(ConstraintDependTypeConstant.ORDNANCE, ordnance.getOrdnanceID());
			cd = cdList.get(0);
			ordnance.setConstraintDependID((cd.getConstraintDependID()));
			ordnance.setConstraintDepend(cd);
		}
		
		return list;
	}

	public void updateCityOrdnance(CityOrdnance cityOrdnance) {
		cityOrdnanceDAO.updateCityOrdnance(cityOrdnance);
	}

	public void updateOrdnance(Ordnance ordnance) {
		ordnanceDAO.updateOrdnance(ordnance);
	}

	public IOrdnanceDAO getOrdnanceDAO() {
		return ordnanceDAO;
	}

	public void setOrdnanceDAO(IOrdnanceDAO ordnanceDAO) {
		this.ordnanceDAO = ordnanceDAO;
	}

	public ICityOrdnanceDAO getCityOrdnanceDAO() {
		return cityOrdnanceDAO;
	}

	public void setCityOrdnanceDAO(ICityOrdnanceDAO cityOrdnanceDAO) {
		this.cityOrdnanceDAO = cityOrdnanceDAO;
	}

	public IConstraintDependService getConstraintDependService() {
		return constraintDependService;
	}

	public void setConstraintDependService(
			IConstraintDependService constraintDependService) {
		this.constraintDependService = constraintDependService;
	}

}
