package com.war.dao;


import java.util.List;

import com.war.domain.Stronghold;

/**
 * 要塞DAO接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IStrongholdDAO {

	public Integer createStronghold(Stronghold stronghold);

	public void updateStronghold(Stronghold stronghold);

	public void deleteStrongholdByID(Integer strongholdID);

	public Stronghold getStrongholdByID(Integer strongholdID);

	public List<Stronghold> getStrongholdList();

}
