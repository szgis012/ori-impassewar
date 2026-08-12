package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.City;
import com.war.domain.CityResource;

public interface ICityResourceDAO {
	
	/**
	 * 创建城市资源信息
	 * @param cityResource
	 */
	public void createCityResource(CityResource cityResource);
	
	/**
	 * 更新城市资源信息
	 * @param cityResource
	 */
	public void updateCityResource(CityResource cityResource);
	
	/**
	 * 根据城市编号删除城市资源信息
	 * @param cityID
	 */
	public void deleteCityResourceByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得城市资源信息
	 * @param cityID
	 * @return
	 */
	public CityResource getCityResourceByCityID(Integer cityID);
	
	/**
	 * 获得城市资源信息列表
	 * @return
	 */
	public List<CityResource> getCityResourceList();
	
	/**
	 * 更新城市资源信息
	 * @param params
	 */
	public void updateCityResource(java.util.Map<String, Object> params);
	
    
    /**
     * 根据城市编号更新城市资源数量
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param money
     */
    public void updateResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long money);
    
    /**
     * 根据城市编号更新城市资源数量上限
     * @param cityID
     * @param woodNumMax
     * @param steelNumMax
     * @param oilNumMax
     * @param foodNumMax
     */
    public void updateResourcesMaxByCityID(Integer cityID, Long resourceNumMax);
    
    /**
     * 根据城市编号更新城市资源产量
     * @param cityID
     * @param woodOutput
     * @param steelOutput
     * @param oilOutput
     * @param foodOutput
     */
    public void updateResourcesOutputByCityID(Integer cityID, Long woodOutput, Long steelOutput, Long oilOutput, Long foodOutput);
    
    /**
     * 根据城市编号更新城市资源消耗
     * @param cityID
     * @param oilConsume
     * @param foodConsume
     * @param moneyConsume
     */
    public void updateResourceConsumeByCityID(Integer cityID, Long oilConsume, Long foodConsume, Long moneyConsume);
    
    /**
     * 根据城市编号获得城市资源
     * @param cityID
     * @return
     */
    public Map<String,Long> getResourcesNumByCityID(Integer cityID);
    
    /**
     * 根据城市编号获得城市资源上限
     * @param cityID
     * @return
     */
    public Long getResourcesNumMaxByCityID(Integer cityID);
    
    /**
     * 根据城市编号获得城市资源产量
     * @param cityID
     * @return
     */
    public Map<String,Long> getResourcesOutputByCityID(Integer cityID);
    
    /**
     * 根据城市编号获得城市资源消耗
     * @param cityID
     * @return
     */
    public Map<String,Long> getResourcesConsumeByCityID(Integer cityID);
    
    /**
     * 根据城市编号添加资源
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param money
     */
    public void addCityResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long moneyNum);
    
    /**
     * 根据城市编号减少资源
     * @param cityID
     * @param woodNum
     * @param steelNum
     * @param oilNum
     * @param foodNum
     * @param money
     */
    public void minusCityResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long moneyNum);
    
    /**
     * 计算城市资源
     */
    public void computeCityResource();
    
    /**
     * 获得食物，石油，金钱任意一个资源值为0的所有城市列表
     * @return
     */
    public List<City> getCityListOfResourceZero();
}
