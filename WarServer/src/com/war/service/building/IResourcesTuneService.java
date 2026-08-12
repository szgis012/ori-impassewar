package com.war.service.building;

/**
 * 调整资源生产service
 * 资源包括：食物，石油，钢材，木材
 *
 * @author ghleed
 * @version 1.0
 */
public interface IResourcesTuneService {
	
	/**
	 * 修改木材工人人数
	 * @param cityID 城市编号
	 * @param workerNum 工作人数
	 */
	public void modifyWoodWorkerNum(Integer cityID,Integer workerNum);
	
	/**
	 * 修改钢铁工人人数
	 * @param cityID 城市编号
	 * @param workerNum 工作人数
	 */
	public void modifySteelWorkerNum(Integer cityID,Integer workerNum);

	/**
	 * 修改石油工作人数
	 * @param cityID 城市编号
	 * @param workerNum 工作人数
	 */
	public void modifyOilWorkerNum(Integer cityID,Integer workerNum);
	
	/**
	 * 修改食物工人人数
	 * @param cityID 城市编号
	 * @param workerNum 工作人数
	 */
	public void modifyFoodWorkerNum(Integer cityID,Integer workerNum);
	
	/**
	 * 同时更新四种资源工作人数
	 * @param cityID 城市编号
	 * @param woodWorkerNum  木材工人人数
	 * @param steelWorkerNum 钢铁工人人数
	 * @param oilWorkerNum 石油工作人数
	 * @param foodWorkerNum 食物工人人数
	 */
	public void modifyResourcesWorkerNum(Integer cityID,Integer woodWorkerNum,Integer steelWorkerNum, Integer oilWorkerNum,Integer foodWorkerNum);
	
}
