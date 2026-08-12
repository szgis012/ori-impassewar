package com.war.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.war.domain.Building;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.ConstraintDepend;
import com.war.domain.ProcessQueue;

public interface IBuildingService {

	/**
	 * 初始化建筑列表
	 * @return
	 */
	public List<Building> initBuildingList();
	
	/**
	 * 初始化建筑列表Map(key:buildingID建筑编号,value:building建筑对象)
	 * @return
	 */
	public Map<Integer,Building> initBuildingsMap();
	
	/**
	 * 初始化建筑Map附带约束依赖对象
	 * @return
	 */
	public Map<Integer,Map<Integer,Building>> initBuildingsMapWithConstraintDepend();

	/**
	 * 根据建筑编号及等级获得建筑及建造/升级条件
	 * @param buildingID
	 * @param level
	 * @return
	 */
	public Building getBuildingByBuildingIDAndLevel(Integer buildingID,Integer level);
	
	/**
	 * 该方法给客户端使用建造或者升级建筑
	 * @param cityID 城市编号 
	 * @param buildingID 建筑编号
	 * @param position 位置
	 * @return
	 */
	public Integer buildBuilding(Integer cityID,Integer buildingID,Integer position);
	
	/**
	 * 建筑建造完成(Quartz使用)
	 * @param processQueue
	 */
	public void buildingFinished(ProcessQueue processQueue);
	
	/**
	 * 根据城市编号获得有效的建筑列表
	 * @param cityID
	 * @return
	 */
	public List<Building> getCityAvailableBuildingList(Integer cityID);
	
	/**
	 * 根据编号获得0级城市信息
	 * @param buildingID
	 * @return
	 */
	public Building getLevel0BuildingByID(Integer buildingID);
	
	/**
	 * 获得建筑列表
	 * @return
	 */
	public List<Building> getBuildingList();
	
	/**
	 * 获得指定编号的城市建筑信息
	 * @param cityBuildingID
	 * @return
	 */
	public CityBuilding getCityBuildingByID(Integer cityBuildingID);
	
	/**
	 * 获得城市已有的建筑列表
	 * @param cityID
	 * @return
	 */
	public List<CityBuilding> getCityBuildingListByCityID(Integer cityID);
	
	/**
	 * 取消建造，升级，拆除操作
	 * @param processQueueID
	 */
	public void cancelProcess(Integer processQueueID);
	
	/**
	 * 拆除建筑
	 * @param cityBuildingID
	 */
	public void backoutBuilding(Integer cityBuildingID);
	
	
	/**
	 * 客户端建造，升级，拆除完成时调用该方法可以及时刷新信息
	 * @param processQueueID
	 */
	public void clientProcessFinished(Integer processQueueID);
	
	/**
	 * 根据编号获得建筑
	 * @param buildingID
	 * @return
	 */
	public Building getBuildingByID(Integer buildingID);
	
	/**
	 * 获得城市建筑
	 * @param cityID
	 * @param buildingID
	 * @return
	 */
	public CityBuilding getCityBuilding(Integer cityID,Integer buildingID);
	
	/**
	 * 检查城市是否满足前提科技和前提建筑的约束条件
	 * 如果没有满足任何一个条件会抛出相应信息的GameException
	 * @param cityID
	 * @param constraintDepend
	 */
	public void checkPreBuildingAndTech(Integer cityID,ConstraintDepend constraintDepend);
	
	/**
	 * 检查城市是否有足够的资源建造(或生产)给定的对象
	 * 如果没有满足任何一个条件会抛出相应信息的GameException
	 * @param city
	 * @param constraintDepend
	 */
	public void checkResources(City city,ConstraintDepend constraintDepend);
	
	/**
	 * 检查城市是否有足够的资源建造(或生产)给定数量的对象
	 * 如果没有满足任何一个条件会抛出相应信息的GameException
	 * @param city
	 * @param constraintDepend
	 * @param num 建造(或生产)的数量
	 */
	public void checkResources(City city,ConstraintDepend constraintDepend,int num);
	
	/**
	 * 检查城市是否满足所有约束条件(包括前提建筑，前提科技和资源的检查)
	 * 如果没有满足任何一个条件会抛出相应信息的GameException
	 * （此方法应在进行数据操作前使用）。
	 * @param city
	 * @param constraintDepend
	 */
	public void checkAllConstraintDepend(City city,ConstraintDepend constraintDepend);
	
	/**
	 * 检查城市是否满足所有约束条件(包括前提建筑，前提科技和资源的检查)
	 * 如果没有满足任何一个条件会抛出相应信息的GameException
	 * （此方法应在进行数据操作前使用）。
	 * @param city
	 * @param constraintDepend
	 * @param num 建造(或生产)的数量
	 */
	public void checkAllConstraintDepend(City city,ConstraintDepend constraintDepend,int num);
	
}
