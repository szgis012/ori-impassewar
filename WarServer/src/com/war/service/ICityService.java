package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.City;
import com.war.domain.CityExt;
import com.war.domain.CityInfo;
import com.war.domain.CityResource;

public interface ICityService {

	/**
	 * 初始化城市编号城市名称Map
	 * @return 城市编号城市名称Map(key:城市编号 value:城市名称)
	 */
	public Map<Integer, String> initCityIDCityNameMap();
	
	/**
	 * 初始化城市编号玩家编号Map
	 * @return 城市编号玩家编号Map(key:城市编号 value:玩家编号)
	 */
	public Map<Integer, Integer> initCityIDPlayerIDMap();
	
	/**
	 * 初始化玩家编号城市编号Map
	 * @return 玩家编号城市编号Map(key:玩家编号 value:城市编号)
	 */
	public Map<Integer, Integer> initPlayerIDCityIDMap();
	
	/**
	 * 创建城市
	 * @param playerID
	 * @param name
	 * @param mapArea
	 * @return 城市编号
	 */
	public Integer createCity(Integer playerID, String name, Integer mapArea);
	
	/**
	 * 更新人口上限
	 * @param cityID
	 * @param populationMax
	 */
	public void updateCityPopulationMax(Integer cityID,Long populationMax);
	
	/**
	 * 更新城市资源
	 * @param cityID
	 * @param woodNum
	 * @param steelNum
	 * @param oilNum
	 * @param foodNum
	 * @param money
	 */
	public void updateCityResources(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long money);
	
	/**
	 * 更新城市资源上限
	 * @param cityID
	 * @param woodNumMax
	 * @param steelNumMax
	 * @param oilNumMax
	 * @param foodNumMax
	 */
	public void updateCityResourcesMax(Integer cityID, Long resourceNumMax);
	
	/**
	 * 更新资源产量
	 * @param cityID
	 * @param woodOutput
	 * @param steelOutput
	 * @param oilOutput
	 * @param foodOutput
	 */
	public void updateCityResourcesOutput(Integer cityID, Long woodOutput, Long steelOutput, Long oilOutput, Long foodOutput);
	
	/**
	 * 根据城市编号更新城市资源消耗
	 * @param cityID
	 * @param oilConsume
	 * @param foodConsume
	 * @param moneyConsume
	 */
	public void updateCityResourceConsumeByCityID(Integer cityID, Long oilConsume, Long foodConsume, Long moneyConsume);
	
	/**
	 * 处理批量增加城市治安
	 */
	public void handleBatchAddCitySecurity();
	
	/**
	 * 改变城市状态(0.新手 1.正常 2.免战 3.封停)
	 * @param cityID
	 * @param state
	 */
	public void changeCityState(Integer cityID,Integer state);
	
    /**
     * 添加城市资源
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param moneyNum
     */
    public void addCityResources(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum);
    
    /**
     * 减少城市资源(如果资源不足则抛出异常，终止操作)
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param moneyNum
     */
    public void minusCityResources(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum);

    /**
     * 减少城市资源(如果资源不足则减为0)
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param moneyNum
     */
    public void minusCityResourcesClear(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum);
    
	/**
     * 更新城市
     * @param city
     */
    public void updateCity(City city);
	
	/**
	 * 获得城市资源数量
	 * @param cityID
	 * @return
	 */
	public Map<String,Long> getCityResourcesNum(Integer cityID);
	
	/**
	 * 获得城市资源上限
	 * @param cityID
	 * @return
	 */
	public Long getCityResourcesNumMax(Integer cityID);
	
	/**
	 * 获得城市资源产量
	 * @param cityID
	 * @return
	 */
	public Map<String,Long> getCityResourcesOutput(Integer cityID);
	
	/**
	 * 获得城市资源消耗
	 * @param cityID
	 * @return
	 */
	public Map<String,Long> getCityResourcesConsume(Integer cityID);
	
	/**
	 * 获得城市人口信息
	 * @param cityID
	 * @return
	 */
	public Map<String,Long> getCityPopulation(Integer cityID);

	/**
	 * 获得城市空闲商人数量
	 * @param cityID
	 * @return
	 */
	public Integer getCityBusinessFree(Integer cityID);
	
    /**
	 * 根据城市编号获得城市的税率和治安值
	 * @param cityID
	 * @return
	 */
	public Map<String,Integer> getCityTaxAndSecurity(Integer cityID);
	
	/**
	 * 根据城市编号获得玩家编号
	 * @param cityID
	 * @return
	 */
	public Integer getPlayerIDByCityID(Integer cityID);
	
	/**
	 * 根据玩家编号获得城市编号
	 * @param playerID  
	 * @return
	 */
	public Integer getCityIDByPlayerID(Integer playerID);
	
	/**
	 * 根据城市名称获得城市编号
	 * @param cityName
	 * @return
	 */
	public Integer getCityIDByCityName(String cityName);
	
	/**
	 * 根据城市编号获得城市名称
	 * @param cityID
	 * @return
	 */
	public String getCityNameByCityID(Integer cityID);
	
	/**
	 * 根据玩家编号获得城市
	 * @param playerID
	 * @return
	 */
	public City getCityByPlayerID(Integer playerID);
	
	/**
	 * 玩家编号获得城市信息包含城市资源
	 * @param playerID
	 * @return
	 */
	public City getCityWithCityResourceByPlayerID(Integer playerID);
	
	/**
	 * 根据城市坐标获得城市编号
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Integer getCityIDByCityPos(Integer posX,Integer posY);
	
	/**
	 * 根据城市编号获得城市坐标
	 * @param cityID
	 * @return
	 */
	public Map<String,Integer> getCityPosByCityID(Integer cityID);
	
    /**
     * 根据城市编号获得城市
     * @param cityID
     * @return
     */
    public City getCityByID(Integer cityID);
    
    /**
     * 根据城市编号获得城市信息
     * @param cityID
     * @return
     */
    public CityInfo getCityInfoByCityID(Integer cityID);

    /**
     * 获得城市列表
     * @return
     */
    public List<City> getCityList();
	
    /**
	 * 计算并更新玩家城市资源信息
	 */
	public void computeCityResource();
	
	/**
	 * 处理城市治安影响
	 */
	public void handleCitySecurityEffect();
	
	/**
	 * 处理城市资源影响
	 */
	public void handleCityResourceEffect();
	
	/**
     * 更新城市的部分字段信息
     * 其中必须指定要更新的cityID和至少一个其他要更新的字段信息
     * @param params key为要更新的字段 value为更新的值
     */
    public void updateCity(java.util.Map<String, Object> params);
    
    /**
     * 迁移城市位置(指定区域随机位置)
     * @param cityID
     * @param mapArea
     */
    public void moveCity(Integer cityID, Integer mapArea);
    
    /**
     * 迁移城市位置至指定坐标
     * @param cityID
     * @param posX
     * @param posY
     */
    public void moveCityToTargetPosition(Integer cityID, Integer posX, Integer posY);
    
    /**
     * 交换(重置)城市资源
     * @param cityID
     * @param exchangedWoodNum
     * @param exchangedSteelNum
     * @param exchangedOilNum
     * @param exchangedFoodNum
     */
    public void exchangeCityResources(Integer cityID, Long exchangedWoodNum, Long exchangedSteelNum, Long exchangedOilNum, Long exchangedFoodNum);
    
    /**
     * 更新城市扩展信息
     * 其中必须指定要更新的cityID和至少一个其他要更新的字段信息
     * @param params key为要更新的字段 value为更新的值
     * @param params
     */
    public void updateCityExt(java.util.Map<String,Object> params);
    
    /**
     * 获得城市扩展信息
     * @param cityID
     * @return
     */
    public CityExt getCityExt(Integer cityID);
    
    /**
     * 判断城市名是否存在
     * @param cityName
     * @return
     */
    public boolean isCityNameExisted(String cityName);
    
	/**
	 * 更新城市资源信息
	 * @param cityResource
	 */
	public void updateCityResource(CityResource cityResource);
	
	/**
	 * 根据城市编号获得城市资源信息
	 * @param cityID
	 * @return
	 */
	public CityResource getCityResourceByCityID(Integer cityID);
	
	/**
	 * 更新城市资源信息
	 * @param params
	 */
    public void updateCityResource(java.util.Map<String, Object> params);

    /**
     * 获得城市资源信息列表
     * @return
     */
	public List<CityResource> getCityResourceList();
    
	/**
	 * 根据编号获得城市信息包含城市资源
	 * @param cityID 编号
	 * @return
	 */
	public City getCityWithCityResourceByID(Integer cityID);

	/**
	 * 根据X坐标及Y坐标获得城市
	 * @param posX
	 * @param posY
	 * @return
	 */
	public City getCityByPosXAndPosY(Integer posX, Integer posY);
	
}
