package com.war.dao;

import java.util.List;

import com.war.domain.ResTrade;

public interface IResTradeDAO {

	/**
	 * 创建资源交易
	 * @param resTrade
	 * @return
	 */
	public Integer createResTrade(ResTrade resTrade);

	/**
	 * 根据资源交易编号更新资源交易状态
	 * @param resTradeID
	 * @param state
	 */
	public void updateResTradeState(Integer resTradeID,Integer state);
	
	/**
	 * 更新资源交易
	 * @param resTrade
	 */
	public void updateResTrade(ResTrade resTrade);

	/**
	 * 根据资源交易编号删除资源交易
	 * @param resTradeID
	 */
	public void deleteResTradeByID(Integer resTradeID);

	/**
	 * 根据资源交易编号获得资源交易
	 * @param resTradeID
	 * @return
	 */
	public ResTrade getResTradeByID(Integer resTradeID);

	/**
	 * 根据城市坐标获得资源销售列表(根据城市距离排序)
	 * @param cityID
	 * @param cityPosX
	 * @param cityPosY
	 * @param resourceType
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<ResTrade> getResTradePagingListByCityPosOrderByDistance(Integer cityID,Integer cityPosX,Integer cityPosY,Integer resourceType,Integer start,Integer offset);
	
	/**
	 * 根据资源类型获得资源销售(挂单)数量
	 * @param cityID
	 * @param resourceType
	 * @return
	 */
	public Integer getResourceSalesAmount(Integer cityID,Integer resourceType);
	
	/**
	 * 根据城市编号获得资源销售(挂单)列表
	 * @param cityID
	 * @return
	 */
	public List<ResTrade> getResourceSalesListByCityID(Integer cityID);
	
	/**
	 * 获得资源交易列表
	 * @return
	 */
	public List<ResTrade> getResTradeList();

}