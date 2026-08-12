package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.Army;
import com.war.domain.ArmyDepend;
import com.war.domain.CityArmy;
import com.war.domain.CityWoundedArmy;

/**
 * 军队Service接口
 * 
 * @author TopTong
 * @version 1.0
 */
public interface IArmyService {
	
	/**
	 * 初始化士兵Map
	 * @return 士兵Map(key:士兵编号 value:士兵对象)
	 */
	public Map<Integer, Army> initArmiesMap();
	
	/**
	 * 初始化自由联邦士兵列表
	 * @return
	 */
	public List<Army> initFreeUnionArmyList();
	
	/**
	 * 初始化联合帝国士兵列表
	 * @return
	 */
	public List<Army> initUnionEmpireArmyList();
	
	public Integer createArmy(Army army);

	public void updateArmy(Army army);

	public void deleteArmyByID(Integer armyID);

	/**
	 * 根据编号获得兵种(缓存)
	 * @param armyID
	 * @return
	 */
	public Army getArmyByID(Integer armyID);

	/**
	 * 获得兵种列表
	 * @param type 兵种类型
	 * @return
	 */
	public List<Army> getArmyList(Integer type);
	
	/**
	 * 获得兵种列表
	 * @return
	 */
	public List<Army> getArmyList();
	
	/**
	 * 获得指定兵种编号的兵种名称
	 * @param armyID
	 * @return
	 */
	public String getArmyNameByID(Integer armyID);
	
	/**
	 * 创建城市军队
	 * @param cityArmy
	 * @return
	 */
	public Integer createCityArmy(CityArmy cityArmy);

	/**
	 * 更新城市军队
	 * @param cityArmy
	 */
	public void updateCityArmy(CityArmy cityArmy);

	/**
	 * 根据编号删除城市军队
	 * @param cityArmyID
	 */
	public void deleteCityArmyByID(Integer cityArmyID);
	
	/**
	* 获得城市兵种信息
	* @param cityID
	* @param armyID
	* @return
	*/
   public CityArmy getCityArmy(Integer cityID,Integer armyID);

	/**
	 * 根据编号获得城市军队
	 * @param cityArmyID
	 * @return
	 */
	public CityArmy getCityArmyByID(Integer cityArmyID);

	public List<CityArmy> getCityArmyList();
	
	public List<CityArmy> getCityArmyList(Integer cityID);
	
	public Integer createArmyDepend(ArmyDepend armyDepend);

	public void updateArmyDepend(ArmyDepend armyDepend);

	public void deleteArmyDependByID(Integer armyDependID);

	public ArmyDepend getArmyDependByID(Integer armyDependID);

	public List<ArmyDepend> getArmyDependList();

	public ArmyDepend getArmyDepend(Integer armyID,Integer ordnanceID);
	
	public List<ArmyDepend> getArmyDependList(Integer armyID);	
	
	/**
	 * 获得兵种的速度
	 * @param armyID
	 * @return
	 */
	public Integer getArmySpeed(Integer armyID);
	
	/**
	 * 根据城市编号及士兵编号批量更新城市士兵数量
	 * @param cityID
	 * @param armyIDs
	 * @param nums
	 */
	public void batchUpdateCityArmyNumByCityIDAndArmyIDs(int cityID, int[] armyIDs, int[] nums);
	
	/**
	 * 检查生产兵种所需的资源是否足够
	 * (如果检查失败会抛出相应的异常信息)
	 * @param cityID 城市编号
	 * @param armyID 要生产的兵种
	 * @param num 数量
	 */
	public void checkResources(int cityID,int armyID, int num);
	
	/**
	 * 增加城市部队
	 * (该方法应该在checkResources之后调用，确保资源足够)
	 * @param cityID 城市编号
	 * @param armyID 要生产的兵种
	 * @param num 数量
	 */
	public void addCityArmy(int cityID,int armyID, int num);
	
	/**
	 * 系统奖励城市部队
	 * @param cityID
	 * @param armyID
	 * @param num
	 */
	public void rewardCityArmy(int cityID,int armyID, int num);
	
	/**
	 * 减少城市已武装(或组装)的士兵(车辆，飞机)，并返还相应资源。
	 * @param cityID 城市编号
	 * @param armyID 要生产的兵种
	 * @param num 数量
	 */
	public void reduceCityArmy(int cityID,int armyID, int num);
	
	/**
	 * 减少城市已武装(或组装)的士兵(车辆，飞机)，并返还相应的人口。
	 * @param cityID
	 * @param armyID
	 * @param num
	 */
	public void reduceCityArmyForTask(int cityID, int armyID, int num);
	
	/**
	 * 获得指定阵营的兵种信息列表
	 * @param country 阵营(ContryTypeConstant定义)
	 * @return
	 */
	public List<Army> getArmyListByContry(int country);
	
	
	/**
	 * 创建城市伤兵部队
	 * @param cityWoundedArmy
	 * @return 
	 */
	public Integer createCityWoundedArmy(CityWoundedArmy cityWoundedArmy);
	
	/**
	 * 更新城市伤兵信息
	 * @param cityWoundedArmyID 编号
	 * @param num 治愈数量
	 */
	public void cureCityWoundedArmy(Integer cityWoundedArmyID, Integer num);
	
	/**
	 * 根据编号删除城市伤兵信息
	 * @param cityWoundedArmy
	 * @param num 数量
	 */
	public void dismissCityWoundedArmy(Integer cityWoundedArmyID, Integer num);
	
	/**
	 * 根据城市编号获得城市的伤兵信息列表
	 * @param cityID
	 * @return 伤兵信息列表
	 */
	public List<CityWoundedArmy> getCityWoundedArmyList(Integer cityID);
	
	/**
	 * 根据编号获得伤兵信息
	 * @param cityWoundedArmyID
	 * @return
	 */
	public CityWoundedArmy getCityWoundedArmyByID(Integer cityWoundedArmyID);
	
	/**
	 * 伤兵到期死亡
	 */
	public void handleAutoDismissedCityWoundedArmy();


	/**
	 * 遣散城市士兵
	 * @param cityID
	 * @param armyStr 军队的所有士兵信息，格式： armyID:num;armyID,num;...
	 */
	public void releaseCityMilitaryArmyPopulation(Integer cityID, String armyStr);

	/**
	 * 获得士兵对象的克隆： 浅拷贝缓存中的士兵对象
	 * @param armyID
	 * @return
	 */
	public Army getClonedArmyByID(Integer armyID);

	/**
	 * 获得士兵所需要的人口
	 * @param armyID
	 * @return
	 */
	public Integer getArmyPopulation(Integer armyID);
}
