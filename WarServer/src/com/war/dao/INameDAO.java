package com.war.dao;

import java.util.List;

import com.war.domain.Name;

public interface INameDAO {

	/**
	 * 创建姓名库
	 * @param name
	 */
	public void createName(Name name);

	/**
	 * 获得姓数组
	 * @param amount 数量
	 * @return
	 */
	public String[] getFirstNameArray(int amount);
	
	/**
	 * 获得名数组
	 * @param amount 数量
	 * @return
	 */
	public String[] getLastNameArray(int amount);
	
	/**
	 * 获得姓名列表
	 * @return
	 */
	public List<Name> getNameList();

}