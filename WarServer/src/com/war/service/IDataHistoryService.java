package com.war.service;

import java.util.Calendar;

public interface IDataHistoryService {

	/**
	 * 根据时间生成数据历史编号
	 * @param calendar
	 * @return
	 */
	public Long generateID(Calendar calendar);
	
	/**
	 * 保存数据历史
	 */
	public void saveDataHistory();
	
}
