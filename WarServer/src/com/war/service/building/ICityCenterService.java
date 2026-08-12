package com.war.service.building;

import com.war.domain.ProductionQueue;

/**
 * 城镇中心service
 *
 * @author ghleed
 * @version 1.0
 */
public interface ICityCenterService {
	/**
	 * 更改城市名称
	 */
	public void changeCityName(Integer cityID,String newCityName);
	
	/**
	 * 征召市民
	 */
	public ProductionQueue enlistCitizen(Integer cityID,Integer enlistNumber);
	
	/**
	 * 进行阅兵仪式
	 */ 
	public void doGuardsParade(Integer cityID);
	
	/**
	 * 进行节日庆典
	 */ 
	public void doHolidayCelebrate(Integer cityID);
	
	/**
	 * 进行安全巡查
	 */ 
	public void doSafetyPatrol(Integer cityID);
	
	/**
	 * 征收物资
	 */ 
	public void imposeMaterial(Integer cityID);
	
	/**
	 * 调整税率
	 */ 
	public void adjustTax(Integer cityID,Integer newValue);
	
	/**
	 * 取消征召市民
	 */
	public void cancelEnlistCitizen(Integer productionProcessID);
	
	/**
	 * 完成征召市民的进程
	 */
	public void finishEnlistCitizen(ProductionQueue produnctionProcess);
	
	/**
	 * 客户端完成征召市民计算时调用该方法可以及时刷新信息
	 * @param productionProcessID
	 */
	public void clientEnlistCitizenFinished(Integer productionProcessID);
	
	/**
	 * 获得招募市民的进程
	 * @param cityID 
	 * @return
	 */
	public ProductionQueue getEnlistCitizenProcess(Integer cityID);
}
