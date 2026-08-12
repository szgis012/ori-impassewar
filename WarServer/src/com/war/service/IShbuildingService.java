package com.war.service;

import java.util.List;

import com.war.domain.Shbuilding;

/**
 * 要塞建筑service接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IShbuildingService {
	public Integer createShbuilding(Shbuilding shbuilding);

	public void updateShbuilding(Shbuilding shbuilding);

	public void deleteShbuildingByID(Integer shbuildingID);

	public Shbuilding getShbuildingByID(Integer shbuildingID);

	public List<Shbuilding> getShbuildingList();
}
