package com.war.dao;


import java.util.List;

import com.war.domain.Map;

public interface IMapDAO {

	/**
	 * 创建地图
	 * @param map
	 * @return
	 */
	public Integer createMap(Map map);

	/**
	 * 根据编号更新地图类别
	 * @param mapID
	 * @param category
	 */
	public void updateMapCategoryByID(Integer mapID,Integer category);
	
	/**
	 * 根据地图编号更新地图目标编号及类别
	 * @param mapID
	 * @param targetID
	 * @param category
	 */
	public void updateTargetIDAndCategoryByID(Integer mapID, Integer targetID, Integer category);
	
	/**
	 * 批量更新地图目标编号及类别
	 * @param mapArray
	 */
	public void updateMapTargetIDAndCategoryBatch(Map[] mapArray);
	
	/**
	 * 更新地图
	 * @param map
	 */
	public void updateMap(Map map);

	/**
	 * 根据编号删除地图
	 * @param mapID
	 */
	public void deleteMapByID(Integer mapID);

	/**
	 * 根据类别及区域获得地图数量
	 * @param category
	 * @param area
	 * @return
	 */
	public Integer getMapNumByCategoryAndArea(Integer category, Integer area);
	
	/**
	 * 根据X坐标及Y坐标获得地图
	 * @param posX 地图X坐标
	 * @param posY 地图Y坐标
	 * @return
	 */
	public Map getMapByPosXAndPoxY(int posX,int posY);
	
	/**
	 * 根据区域及类别及野怪等级获得地图数量
	 * @param area
	 * @param category
	 * @param mapMonsterLevel
	 * @return
	 */
	public Integer getMapNumByAreaAndCategoryAndMapMonsterLevel(Integer area, Integer category, Integer mapMonsterLevel);
	
	/**
	 * 获得无出征队列目标为当前地图并且不在战斗中的地图列表
	 * @return
	 */
	public List<Map> getNoDepoyQueueAndNotInBattleMapList();
	
	/**
	 * 根据目标编号及类别获得地图
	 * @param targerID
	 * @param category
	 * @return
	 */
	public Map getMapByTargetIDAndCategory(Integer targerID,Integer category);
	
	/**
	 * 根据编号获得地图
	 * @param mapID
	 * @return
	 */
	public Map getMapByID(Integer mapID);

	/**
	 * 获得地图列表
	 * @return
	 */
	public List<Map> getMapList();

	/**
	 * 根据区域获得野地及野怪地图数量
	 * @param blankFieldCategory
	 * @param monsterCategory
	 * @param area
	 * @return
	 */
	public Integer getBlankFieldAndMonsterFieldMapNumByArea(Integer blankFieldCategory, Integer monsterCategory, Integer area);
	
	/**
	 * 根据分类获得地图数量
	 * @param category
	 * @return
	 */
	public Integer getMapAmountByCategory(Integer category);
	
	/**
	 * 获得野地编号列表
	 * @return
	 */
	public List<Integer> getBlankFieldIDList();
	
	/**
	 * 根据区域及类型获得随机地图
	 * @param area
	 * @param category
	 * @return
	 */
	public Map getRandomMapByAreaAndCategory(Integer area, Integer category);
	
	/**
	 * 根据开始XY坐标及结束XY坐标及类别获得随机地图
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param category
	 * @return 随机地图信息
	 */
	public Map getRandomMapByStartPosXYAndEndPosXYAndCategory(Integer startX, Integer startY, Integer endX, Integer endY, Integer category);
	
	/**
	 * 根据开始XY坐标及结束XY坐标地图列表
	 * 
	 * @param startX 起始X坐标
	 * @param startY 起始Y坐标
	 * @param endX 结束X坐标
	 * @param endY 结束Y坐标
	 * @return 地图列表
	 */
	public List<Map> getMapListByStartPosXYAndEndPosXY(Integer startX, Integer startY, Integer endX, Integer endY);
	
	/**
	 * 根据SQL字符串获得地图列表
	 * @param sql
	 * @return
	 */
	public List<Map> getMapListBySQL(String sql);
	
	/**
	 * 根据类别及区域及数量获得随机地图编号列表
	 * @param category
	 * @param area
	 * @return
	 */
	public List<Integer> getRandomMapIDListByCategoryAndAreaAndNum(Integer category, Integer area, Integer num);
	
	/**
	 * 获得野地列表
	 * @return
	 */
	public List<Map> getBlankFieldList();
	
	/**
	 * 获得半径为radius和radius+range范围内的地图列表
	 * @param radius 离地图原点的距离
	 * @param range 圆环的宽度
	 * @return
	 */
	public List<Map> getMapList(int radius,int range);

}
