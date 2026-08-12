package com.war.service;

import java.util.Map;

public interface IHonorService {

	/**
	 * 初始化军衔Map
	 * @return
	 */
	public Map<Integer,String> initHonorMap();
	
	/**
	 * 根据编号获得军衔
	 * @param honorID
	 * @return
	 */
	public String getHonorByID(Integer honorID);
	
}
