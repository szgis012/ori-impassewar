package com.war.dao;

import java.util.List;

import com.war.domain.StrongholdShbuilding;

/**
 * 要塞建筑关系DAO接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IStrongholdShbuildingDAO {
	  public Integer createStrongholdShbuilding(StrongholdShbuilding shShbuilding);

		public void updateStrongholdShbuilding(StrongholdShbuilding shShbuilding);

		public void deleteStrongholdShbuildingByID(Integer shShbuildingID);

		public StrongholdShbuilding getStrongholdShbuildingByID(Integer shShbuildingID);

		public List<StrongholdShbuilding> getStrongholdShbuildingList();
		
		/**
		 * 获得要塞已建的建筑信息 
		 * @param strongholdID 要塞编号
		 * @return
		 */
		public List<StrongholdShbuilding> getStrongholdBuildingListByStrongholdID(Integer strongholdID); 

}
