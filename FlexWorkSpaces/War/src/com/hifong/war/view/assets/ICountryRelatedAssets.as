package com.hifong.war.view.assets
{
	/**
	 * 阵营相关的图形资源接口
	 * 
	 */ 
	public interface ICountryRelatedAssets
	{
		/**
		 * 得到指定编号建筑的图片类
		 */ 
		function getBuildingImageClass(buildingID:int):Class;
		
		/**
		 * 得到指定编号建筑的图片资源
		 */ 
		function getBuildingImageSource(buildingID:int):Object;
	}
}