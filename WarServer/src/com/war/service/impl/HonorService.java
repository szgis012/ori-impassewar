package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.dao.IHonorDAO;
import com.war.domain.Honor;
import com.war.service.IHonorService;

public class HonorService implements IHonorService {

	private IHonorDAO honorDAO;
	
	public Map<Integer, String> initHonorMap() {
		
		Map<Integer,String> honorMap = new HashMap<Integer,String>();
		
		List<Honor> honorList = honorDAO.getHonorList();
		for(int i=0;i<honorList.size();i++){
			honorMap.put(honorList.get(i).getHonorID(), honorList.get(i).getName());
		}
		
		return honorMap;
	}
	
	@SuppressWarnings("unchecked")
	public String getHonorByID(Integer honorID) {
		return ((Map<Integer,String>)CacheService.getFromCache(CacheConstant.HONOR_MAP)).get(honorID);
	}

	
	public IHonorDAO getHonorDAO() {
		return honorDAO;
	}

	public void setHonorDAO(IHonorDAO honorDAO) {
		this.honorDAO = honorDAO;
	}
	
}
