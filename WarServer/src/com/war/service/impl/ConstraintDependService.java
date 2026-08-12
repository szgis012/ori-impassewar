package com.war.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.dao.IBuildingDAO;
import com.war.dao.IConstraintDependDAO;
import com.war.dao.ITechnologyDAO;
import com.war.domain.Building;
import com.war.domain.ConstraintDepend;
import com.war.domain.PreBuilding;
import com.war.domain.Technology;
import com.war.service.IConstraintDependService;

public class ConstraintDependService implements IConstraintDependService {

	private IConstraintDependDAO constraintDependDAO;
	
	private IBuildingDAO buildingDAO;
	
	private ITechnologyDAO technologyDAO;

	private static Logger logger = Logger.getLogger(ConstraintDependService.class);
	
	@SuppressWarnings("unchecked")
	public List<ConstraintDepend> getConstraintDependListByTypeAndTargetID(
			Integer type, Integer targetID) {

		List<ConstraintDepend> constraintDependList = constraintDependDAO
				.getConstraintDependListByTypeAndTargetID(type, targetID);

		Map<Integer, Building> buildingMap = (Map<Integer, Building>)CacheService.getFromCache(CacheConstant.BUILDINGS_MAP);
		
		for (int i = 0; i < constraintDependList.size(); i++) {

			ConstraintDepend constraintDepend = constraintDependList.get(i);

			// 初始化PreBuildingList
			if (constraintDepend.getPreBuildings() != null
					&& !constraintDepend.getPreBuildings().equals("")) {
				List<PreBuilding> preBuildingList = new ArrayList<PreBuilding>();
				String[] preBuildings = constraintDepend.getPreBuildings()
						.split(";");
				for (int j = 0; j < preBuildings.length; j++) {
					PreBuilding preBuilding = new PreBuilding();
					String[] strPreBuilding = preBuildings[j].split(":");
					preBuilding.setBuildingID(new Integer(strPreBuilding[0]));
					preBuilding.setBuildingName(buildingMap.get(preBuilding.getBuildingID()).getName());
					preBuilding.setLevel(new Integer(strPreBuilding[1]));
					preBuildingList.add(preBuilding);
				}
				constraintDepend.setPreBuildingList(preBuildingList);
			}
		}

		return constraintDependList;
	}

	@SuppressWarnings("unchecked")
	public ConstraintDepend getConstraintDependByTypeAndTargetIDAndLevel(
			Integer type, Integer targetID, Integer level) {
		ConstraintDepend constraintDepend = constraintDependDAO.getConstraintDependByTypeAndTargetIDAndLevel(type, targetID, level);

		if(constraintDepend == null){
			return null;
		}
		
		Map<Integer, Building> buildingMap = (Map<Integer, Building>)CacheService.getFromCache(CacheConstant.BUILDINGS_MAP);
		
		// 初始化PreBuildingList
		if (constraintDepend.getPreBuildings() != null
				&& !constraintDepend.getPreBuildings().equals("")) {
			List<PreBuilding> preBuildingList = new ArrayList<PreBuilding>();
			String[] preBuildings = constraintDepend.getPreBuildings().split(
					";");
			for (int j = 0; j < preBuildings.length; j++) {
				PreBuilding preBuilding = new PreBuilding();
				String[] strPreBuilding = preBuildings[j].split(":");
				preBuilding.setBuildingID(new Integer(strPreBuilding[0]));
				preBuilding.setBuildingName(buildingMap.get(preBuilding.getBuildingID()).getName());
				preBuilding.setLevel(new Integer(strPreBuilding[1]));
				preBuildingList.add(preBuilding);
			}
			constraintDepend.setPreBuildingList(preBuildingList);
		}

		return constraintDepend;
	}
	
	
	public IConstraintDependDAO getConstraintDependDAO() {
		return constraintDependDAO;
	}

	public void setConstraintDependDAO(IConstraintDependDAO constraintDependDAO) {
		this.constraintDependDAO = constraintDependDAO;
	}

	public IBuildingDAO getBuildingDAO() {
		return buildingDAO;
	}

	public void setBuildingDAO(IBuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	public ITechnologyDAO getTechnologyDAO() {
		return technologyDAO;
	}

	public void setTechnologyDAO(ITechnologyDAO technologyDAO) {
		this.technologyDAO = technologyDAO;
	}

}
