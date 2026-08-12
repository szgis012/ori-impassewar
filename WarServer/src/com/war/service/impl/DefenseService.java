package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.constant.ConstraintDependTypeConstant;
import com.war.dao.IDefenseDAO;
import com.war.domain.Defense;
import com.war.service.IConstraintDependService;
import com.war.service.IDefenseService;

public class DefenseService implements IDefenseService {

	private IDefenseDAO defenseDAO;
	
	private IConstraintDependService constraintDependService;
	
	
	public List<Defense> initDefenseList() {
		List<Defense> defenseList = defenseDAO.getDefenseList();
		
		for (Defense defense : defenseList) {
			defense.setConstraintDepend(constraintDependService.getConstraintDependByTypeAndTargetIDAndLevel(ConstraintDependTypeConstant.DEFENSE, defense.getDefenseID(), 1));
		}
		
		return defenseList;
	}
	
	public Map<Integer, Defense> initDefenseMap() {
		Map<Integer, Defense> defenseMap = new HashMap<Integer, Defense>();
		
		List<Defense> defenseList = this.getDefenseList();
		for (int i=0;i<defenseList.size();i++) {
			defenseMap.put(defenseList.get(i).getDefenseID(), defenseList.get(i));
		}
		
		return defenseMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<Defense> getDefenseList() {
		return (List<Defense>)CacheService.getFromCache(CacheConstant.DEFENSE_LIST);
	}
	
	public Integer createDefense(Defense defense) {
		return defenseDAO.createDefense(defense);
	}

	public void deleteDefenseByID(Integer defenseID) {
		defenseDAO.deleteDefenseByID(defenseID);
	}

	@SuppressWarnings("unchecked")
	public Defense getDefenseByID(Integer defenseID) {
		return (Defense)((Map<Integer, Defense>)CacheService.getFromCache(CacheConstant.DEFENSE_MAP)).get(defenseID);
	}
	
	@SuppressWarnings("unchecked")
	public Defense getClonedDefenseByID(Integer defenseID) {
		Defense defenseObj = (Defense)((Map<Integer, Defense>)CacheService.getFromCache(CacheConstant.DEFENSE_MAP)).get(defenseID);
		Defense clonedDefense = new Defense();
		
		clonedDefense.setDefenseID(defenseID);
		clonedDefense.setName(defenseObj.getName());
		clonedDefense.setImage(defenseObj.getImage());
		clonedDefense.setDescription(defenseObj.getDescription());
		clonedDefense.setLife(defenseObj.getLife());
		clonedDefense.setAttack(defenseObj.getAttack());
		clonedDefense.setDefense(defenseObj.getDefense());
		clonedDefense.setRange(defenseObj.getRange());
		
		return clonedDefense;
	}


	public void updateDefense(Defense defense) {
		defenseDAO.updateDefense(defense);
	}

	public IDefenseDAO getDefenseDAO() {
		return defenseDAO;
	}

	public void setDefenseDAO(IDefenseDAO defenseDAO) {
		this.defenseDAO = defenseDAO;
	}

	public IConstraintDependService getConstraintDependService() {
		return constraintDependService;
	}

	public void setConstraintDependService(
			IConstraintDependService constraintDependService) {
		this.constraintDependService = constraintDependService;
	}

}
