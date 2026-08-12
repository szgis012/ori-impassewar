package com.war.dao;


import java.util.List;

import com.war.domain.Ordnance;


/**
 * 军械dao
 *
 * @author ghleed
 * @version 1.0
 */
public interface IOrdnanceDAO {

	public Integer createOrdnance(Ordnance ordnance);

	public void updateOrdnance(Ordnance ordnance);

	public void deleteOrdnanceByID(Integer ordnanceID);

	public Ordnance getOrdnanceByID(Integer ordnanceID);

	public List<Ordnance> getOrdnanceList();

}