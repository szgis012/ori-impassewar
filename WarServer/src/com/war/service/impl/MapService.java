package com.war.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.RandomService;
import com.war.constant.CacheConstant;
import com.war.constant.MapConstant;
import com.war.constant.MilitaryConstant;
import com.war.dao.ICityDAO;
import com.war.dao.IMapDAO;
import com.war.dao.IMapFavouriteDAO;
import com.war.dao.IMapMonsterDAO;
import com.war.dao.IPlayerDAO;
import com.war.domain.Map;
import com.war.domain.MapFavourite;
import com.war.domain.MapMonster;
import com.war.exception.GameException;
import com.war.service.IMapService;
import com.war.service.IPlayerService;

/**
 * 地图Service实现
 *
 * @author TopTong
 */
public class MapService implements IMapService {
	
	private IMapDAO mapDAO;
	
	private IMapFavouriteDAO mapFavouriteDAO;
	
	private IMapMonsterDAO mapMonsterDAO;
	
	private IPlayerDAO playerDAO;
	
	private ICityDAO cityDAO;
	
	private IPlayerService playerService;
	
	
	public Integer createMap(Map map){
		return mapDAO.createMap(map);
	}
	
	public void updateMapCategoryByID(Integer mapID, Integer category){
		mapDAO.updateMapCategoryByID(mapID, category);
	}

	public void updateMap(Map map) {
		mapDAO.updateMap(map);
	}
	
	public Integer getMapNumByCategoryAndArea(Integer category, Integer area) {
		return mapDAO.getMapNumByCategoryAndArea(category, area);
	}
	
	/**
	 * 获得地图名称
	 * @param map
	 * @return 地图名称
	 */
	@SuppressWarnings("unchecked")
	private String getMapName(Map map){
		String mapName = null;
		switch(map.getCategory()){
			case MapConstant.CATEGORY_CITY:
				mapName = this.getCityNameByCityID(map.getTargetID());
				break;
			case MapConstant.CATEGORY_STRONG_HOLD:
				mapName = "要塞";
				break;
			case MapConstant.CATEGORY_BLANK_FIELD:
				mapName = "空地";
				break;
			case MapConstant.CATEGORY_MONSTER:
				MapMonster mapMonster = mapMonsterDAO.getMapMonsterByID(map.getTargetID());
				if (mapMonster==null) {
					mapName = "野怪";
				} else {
					mapName = MilitaryConstant.MILITARY_NAME[mapMonster.getLevel()] + "级部队(等级" + mapMonster.getLevel() + ")";
				}
				break;
			default:
				mapName = "未知";
				break;
		}
		return mapName;
	}
	
	public Map getMapByID(Integer mapID) {
		Map map = mapDAO.getMapByID(mapID);
		map.setName(this.getMapName(map));
		return map;
	}
	
	public List<Map> getMapList(Integer startX, Integer startY, Integer endX, Integer endY){
		List<Map> mapList =  mapDAO.getMapListByStartPosXYAndEndPosXY(startX, startY, endX, endY);
		
		// 设置地图信息
		for(Map map:mapList){
			// 城市
			if (map.getCategory() == MapConstant.CATEGORY_CITY) {
				map.setPlayer(playerService.getPlayerByID(this.getPlayerIDByCityID(map.getTargetID())));
				// 设置城市名称
				map.setName(this.getCityNameByCityID(map.getTargetID()));
			} else if (map.getCategory()==MapConstant.CATEGORY_STRONG_HOLD) {
				// TODO 要塞
			} else if (map.getCategory()==MapConstant.CATEGORY_MONSTER) {
				// 野怪
				map.setMapMonster(mapMonsterDAO.getMapMonsterByID(map.getTargetID()));
			} else {
				// 空地&野地
			}
		}
		
		return mapList;
	}
	
	public List<Map> getMapListByMapPosXYList(List<Map> mapPosXYList) {
		// 构建SQL参数
		StringBuffer stringBuffer = new StringBuffer();
		for (int i=0;i<mapPosXYList.size();i++) {
			stringBuffer.append("SELECT MAP_ID AS mapID, POSX AS posX, POSY AS posY, AREA AS area, TYPE AS type, STATE AS state, TARGET_ID AS targetID, CATEGORY AS category FROM T_MAP WHERE POSX=");
			stringBuffer.append(mapPosXYList.get(i).getPosX());
			stringBuffer.append(" AND POSY=");
			stringBuffer.append(mapPosXYList.get(i).getPosY());
			if (i!=mapPosXYList.size()-1) {
				stringBuffer.append(" UNION ALL ");
			}
		}
		
		List<Map> mapList = mapDAO.getMapListBySQL(stringBuffer.toString());
		// 设置地图信息
		for(Map map:mapList){
			// 城市
			if (map.getCategory()==MapConstant.CATEGORY_CITY) {
				map.setPlayer(playerService.getPlayerByID(this.getPlayerIDByCityID(map.getTargetID())));
				// 设置城市名称
				map.setName(this.getCityNameByCityID(map.getTargetID()));
			} else if (map.getCategory()==MapConstant.CATEGORY_STRONG_HOLD) {
				// TODO 要塞
			} else if (map.getCategory()==MapConstant.CATEGORY_MONSTER) {
				// 野怪
				map.setMapMonster(mapMonsterDAO.getMapMonsterByID(map.getTargetID()));
			} else {
				// 空地&野地
			}
		}
		
		return mapList;
	}
	
	public Map getMapByTargetIDAndCategory(Integer targerID, Integer category) {
		return mapDAO.getMapByTargetIDAndCategory(targerID, category);
	}
	
	public Map getMapByPos(Integer posX, Integer posY){
		Map map = mapDAO.getMapByPosXAndPoxY(posX, posY);
		
		if (map == null)
			throw new GameException("地图目标不存在。");
			
		map.setName(this.getMapName(map));
		return map;
	}
	
	public Map getAreaBlankMap(Integer mapArea) {
		return mapDAO.getRandomMapByAreaAndCategory(mapArea, MapConstant.CATEGORY_BLANK_FIELD);
	}
	
	public void generateGameMap() {
		
		Random random = new Random();
		
		// 初始化野地地图索引
		
		int area_1_wildlandIndexArray[][] = new int[MapConstant.AREA_1_END_POSY+1][];
		int area_2_wildlandIndexArray[][] = new int[MapConstant.AREA_2_END_POSY+1][];
		
		for (int i=0;i<=MapConstant.AREA_1_END_POSY;i++) {
			area_1_wildlandIndexArray[i] = RandomService.generateRandomNumberArray(MapConstant.AREA_1_START_POSX+1, MapConstant.AREA_1_END_POSX-1, MapConstant.ROW_WILDLAND_NUM);
			area_2_wildlandIndexArray[i] = RandomService.generateRandomNumberArray(MapConstant.AREA_2_START_POSX+1, MapConstant.AREA_2_END_POSX-1, MapConstant.ROW_WILDLAND_NUM);
		}
		
		// 当前地图编号
		int currentMapID = 1;
		// 当前列索引
		int currentColumnIndex = 0;
		// 当前野地索引
		int currentArea1WildlandIndex = 0;
		int currentArea2WildlandIndex = 0;
		// 当前地图类型索引
		int currentWildlandTypeIndex = 0;
		int currentArea1WildlandTypeIndex = 0;
		int currentArea2WildlandTypeIndex = 0;
		// 是否野地
		boolean isWildland;
		
		List<List<Map>> hMapList = new ArrayList<List<Map>>();
		// 行(横向)
		for (int i=1;i<=MapConstant.MAP_WIDTH;i++) {
			
			List<Map> vMapList = new ArrayList<Map>();
			
			currentColumnIndex = 0;
			currentArea1WildlandIndex = 0;
			currentArea2WildlandIndex = 0;
			
			// 列(纵向)
			for (int j=1;j<=MapConstant.MAP_HEIGHT;j++) {
				Map map = new Map();
				map.setPosX(j);
				map.setPosY(i);
				
				if (j>=MapConstant.V_SEA1_START && j<=MapConstant.V_SEA1_END) {
					// 第一列海洋
					if (j==MapConstant.H_SEA1_END && (i==MapConstant.V_SEA1_START || i==MapConstant.V_SEA1_END || i==MapConstant.V_SEA2_START || i==MapConstant.V_SEA2_END)) {
						// 拐角判断
						if (i==MapConstant.V_SEA1_START) {
							// 上岛屿左下角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_BOTTOM_LEFT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA1_END) {
							// 中岛屿左上角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_TOP_LEFT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA2_START) {
							// 中岛屿左下角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_BOTTOM_LEFT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA2_END) {
							// 下岛屿左上角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_TOP_LEFT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						}
					} else if (j==MapConstant.V_SEA1_START) {
						// 左海岸(左陆地右海洋)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_LAND_LEFT_SEA_RIGHT_ARRAY[random.nextInt(MapConstant.TYPE_LAND_LEFT_SEA_RIGHT_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else if (j==MapConstant.V_SEA1_END && (i<=MapConstant.H_SEA1_START || (i>=MapConstant.H_SEA1_END && i<=MapConstant.H_SEA2_START) || i>=MapConstant.H_SEA2_END)) {
						// 右海岸(左海洋右陆地)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_SEA_LEFT_LAND_RIGHT_ARRAY[random.nextInt(MapConstant.TYPE_SEA_LEFT_LAND_RIGHT_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else {
						// 海洋
						map.setArea(MapConstant.SEA);
						map.setType(MapConstant.TYPE_SEA_ARRAY[random.nextInt(MapConstant.TYPE_SEA_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_SEA);
					}
				} else if (j>=MapConstant.V_SEA2_START && j<=MapConstant.V_SEA2_END) {
					// 第二列海洋
					if (j==MapConstant.H_SEA2_START && (i==MapConstant.V_SEA1_START || i==MapConstant.V_SEA1_END || i==MapConstant.V_SEA2_START || i==MapConstant.V_SEA2_END)) {
						// 拐角判断
						if (i==MapConstant.V_SEA1_START) {
							// 上岛屿右下角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_BOTTOM_RIGHT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA1_END) {
							// 中岛屿右上角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_TOP_RIGHT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA2_START){
							// 中岛屿右下角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_BOTTOM_RIGHT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						} else if (i==MapConstant.V_SEA2_END) {
							// 下岛屿右上角
							map.setArea(MapConstant.COAST);
							map.setType(MapConstant.TYPE_ISLAND_TOP_RIGHT_CORNER);
							map.setCategory(MapConstant.CATEGORY_COAST);
						}
					} else if (j==MapConstant.V_SEA2_END) {
						// 右海岸(左海洋右陆地)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_SEA_LEFT_LAND_RIGHT_ARRAY[random.nextInt(MapConstant.TYPE_SEA_LEFT_LAND_RIGHT_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else if (j==MapConstant.V_SEA2_START && (i<=MapConstant.H_SEA1_START || (i>=MapConstant.H_SEA1_END && i<=MapConstant.H_SEA2_START) || i>=MapConstant.H_SEA2_END)) {
						// 左海岸(左陆地右海洋)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_LAND_LEFT_SEA_RIGHT_ARRAY[random.nextInt(MapConstant.TYPE_LAND_LEFT_SEA_RIGHT_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					}  else {
						// 海洋
						map.setArea(MapConstant.SEA);
						map.setType(MapConstant.TYPE_SEA_ARRAY[random.nextInt(MapConstant.TYPE_SEA_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_SEA);
					}
				} else if (i>=MapConstant.H_SEA1_START && i<=MapConstant.H_SEA1_END && j>MapConstant.H_SEA1_START && j<MapConstant.H_SEA2_END) {
					// 第一行海洋
					if (i==MapConstant.H_SEA1_START) {
						// 上海岸(上陆地下海洋)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_LAND_TOP_SEA_BOTTOM_ARRAY[random.nextInt(MapConstant.TYPE_LAND_TOP_SEA_BOTTOM_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else if (i==MapConstant.H_SEA1_END) {
						// 下海岸(上海洋下陆地)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_SEA_TOP_LAND_BOTTOM_ARRAY[random.nextInt(MapConstant.TYPE_SEA_TOP_LAND_BOTTOM_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else {
						// 海洋
						map.setArea(MapConstant.SEA);
						map.setType(MapConstant.TYPE_SEA_ARRAY[random.nextInt(MapConstant.TYPE_SEA_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_SEA);
					}
				} else if (i>=MapConstant.H_SEA2_START && i<=MapConstant.H_SEA2_END && j>MapConstant.H_SEA1_START && j<MapConstant.H_SEA2_END) {
					// 第二行海洋
					if (i==MapConstant.H_SEA2_START) {
						// 上海岸(上陆地下海洋)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_LAND_TOP_SEA_BOTTOM_ARRAY[random.nextInt(MapConstant.TYPE_LAND_TOP_SEA_BOTTOM_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else if (i==MapConstant.H_SEA2_END) {
						// 下海岸(上海洋下陆地)
						map.setArea(MapConstant.COAST);
						map.setType(MapConstant.TYPE_SEA_TOP_LAND_BOTTOM_ARRAY[random.nextInt(MapConstant.TYPE_SEA_TOP_LAND_BOTTOM_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_COAST);
					} else {
						// 海洋
						map.setArea(MapConstant.SEA);
						map.setType(MapConstant.TYPE_SEA_ARRAY[random.nextInt(MapConstant.TYPE_SEA_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_SEA);
					}
				} else {
					// 陆地
					isWildland = false;
					if (j>=MapConstant.AREA_1_START_POSX && j<=MapConstant.AREA_1_END_POSX) {
						// 左区域
						
						if (i<=MapConstant.AREA_1_1_END_POSY) {
							// 左上区域
							map.setArea(MapConstant.AREA_1_1);
						} else if (i>=MapConstant.AREA_1_2_START_POSY) {
							// 左下区域
							map.setArea(MapConstant.AREA_1_2);
						}
						
						if (currentArea1WildlandIndex<MapConstant.ROW_WILDLAND_NUM && currentColumnIndex==area_1_wildlandIndexArray[i][currentArea1WildlandIndex]) {
							
							if (currentArea1WildlandTypeIndex>3) {
								currentArea1WildlandTypeIndex = 0;
							}
							
							currentWildlandTypeIndex = currentArea1WildlandTypeIndex;
							
							isWildland = true;
							currentArea1WildlandTypeIndex++;
							currentArea1WildlandIndex++;
						}
					} else if (j>=MapConstant.AREA_2_START_POSX && j<=MapConstant.AREA_2_END_POSX) {
						// 右区域
						
						if (i<=MapConstant.AREA_2_1_END_POSY) {
							// 右上区域
							map.setArea(MapConstant.AREA_2_1);
						} else if (i>=MapConstant.AREA_2_2_START_POSY) {
							// 右下区域
							map.setArea(MapConstant.AREA_2_2);
						}
						
						if (currentArea2WildlandIndex<MapConstant.ROW_WILDLAND_NUM && currentColumnIndex==area_2_wildlandIndexArray[i][currentArea2WildlandIndex]) {
							
							if (currentArea2WildlandTypeIndex>3) {
								currentArea2WildlandTypeIndex = 0;
							}
							
							currentWildlandTypeIndex = currentArea2WildlandTypeIndex;
							
							isWildland = true;
							currentArea2WildlandTypeIndex++;
							currentArea2WildlandIndex++;
						}
					} else {
						// 岛屿
						if (i<=MapConstant.ISLAND_1_END_POSY) {
							// 上岛屿
							map.setArea(MapConstant.ISLAND_1);
						} else if (i>=MapConstant.ISLAND_2_START_POSY && i<=MapConstant.ISLAND_2_END_POSY) {
							// 中岛屿
							map.setArea(MapConstant.ISLAND_2);
						} else if (i>=MapConstant.ISLAND_3_START_POSY) {
							// 下岛屿
							map.setArea(MapConstant.ISLAND_3);
						}
					}
					
					if (isWildland) {
						switch (currentWildlandTypeIndex) {
							case 0:
								// 林场
								map.setType(MapConstant.TYPE_TIMBER_LAND_ARRAY[random.nextInt(MapConstant.TYPE_TIMBER_LAND_ARRAY.length)]);
								map.setCategory(MapConstant.CATEGORY_TIMBER_LAND);
								break;
							case 1:
								// 铁矿
								map.setType(MapConstant.TYPE_IRON_MINE_ARRAY[random.nextInt(MapConstant.TYPE_IRON_MINE_ARRAY.length)]);
								map.setCategory(MapConstant.CATEGORY_IRON_MINE);
								break;
							case 2:
								// 油井
								map.setType(MapConstant.TYPE_OIL_WELL_ARRAY[random.nextInt(MapConstant.TYPE_OIL_WELL_ARRAY.length)]);
								map.setCategory(MapConstant.CATEGORY_OIL_WELL);
								break;
							case 3:
								// 麦田
								map.setType(MapConstant.TYPE_WHEAT_FIELD_ARRAY[random.nextInt(MapConstant.TYPE_WHEAT_FIELD_ARRAY.length)]);
								map.setCategory(MapConstant.CATEGORY_WHEAT_FIELD);
								break;
						}
					} else {
						map.setType(MapConstant.TYPE_BLANK_FIELD_ARRAY[random.nextInt(MapConstant.TYPE_BLANK_FIELD_ARRAY.length)]);
						map.setCategory(MapConstant.CATEGORY_BLANK_FIELD);
					}
					
				}

				map.setMapID(currentMapID);
				map.setTargetID(null);
				map.setState(MapConstant.STATE_NORMAL);
				
				// 创建地图
				this.createMap(map);
				
				vMapList.add(map);
				
				currentMapID++;
				currentColumnIndex++;
			}
			hMapList.add(vMapList);
			
		}

		Map tempMap = null;
		
		// 左上据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_1_POSX, MapConstant.LODGMENT_1_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_1_POSX+1, MapConstant.LODGMENT_1_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_1_POSX, MapConstant.LODGMENT_1_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_1_POSX+1, MapConstant.LODGMENT_1_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 左下据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_2_POSX, MapConstant.LODGMENT_1_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_2_POSX+1, MapConstant.LODGMENT_1_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_2_POSX, MapConstant.LODGMENT_1_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_1_2_POSX+1, MapConstant.LODGMENT_1_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_1_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 左上据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_1_POSX, MapConstant.LODGMENT_2_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_1_POSX+1, MapConstant.LODGMENT_2_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_1_POSX, MapConstant.LODGMENT_2_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_1_POSX+1, MapConstant.LODGMENT_2_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 左上据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_2_POSX, MapConstant.LODGMENT_2_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_2_POSX+1, MapConstant.LODGMENT_2_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_2_POSX, MapConstant.LODGMENT_2_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_2_2_POSX+1, MapConstant.LODGMENT_2_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_2_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 上岛屿据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_1_POSX, MapConstant.LODGMENT_ISLAND_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_1_POSX+1, MapConstant.LODGMENT_ISLAND_1_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_1_POSX, MapConstant.LODGMENT_ISLAND_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_1_POSX+1, MapConstant.LODGMENT_ISLAND_1_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_1);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 下岛屿据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_3_POSX, MapConstant.LODGMENT_ISLAND_3_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_3);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_3_POSX+1, MapConstant.LODGMENT_ISLAND_3_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_3);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_3_POSX, MapConstant.LODGMENT_ISLAND_3_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_3);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_3_POSX+1, MapConstant.LODGMENT_ISLAND_3_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_3);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		
		// 中岛屿据点
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX-1, MapConstant.LODGMENT_ISLAND_2_POSY-1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX, MapConstant.LODGMENT_ISLAND_2_POSY-1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX+1, MapConstant.LODGMENT_ISLAND_2_POSY-1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX-1, MapConstant.LODGMENT_ISLAND_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX, MapConstant.LODGMENT_ISLAND_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX+1, MapConstant.LODGMENT_ISLAND_2_POSY);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX-1, MapConstant.LODGMENT_ISLAND_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX, MapConstant.LODGMENT_ISLAND_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		tempMap = this.getMapByPos(MapConstant.LODGMENT_ISLAND_2_POSX+1, MapConstant.LODGMENT_ISLAND_2_POSY+1);
		tempMap.setType(MapConstant.TYPE_LODGMENT_ISLAND_2);
		tempMap.setCategory(MapConstant.CATEGORY_LODGMENT);
		mapDAO.updateMap(tempMap);
		
		// 特训基地(正常分布)
		for (int i=0;i<MapConstant.SPECIAL_TRAINING_BASE_POSX_ARRAY.length;i++) {
			tempMap = this.getMapByPos(MapConstant.SPECIAL_TRAINING_BASE_POSX_ARRAY[i], MapConstant.SPECIAL_TRAINING_BASE_POSY_ARRAY[i]);
			tempMap.setType(MapConstant.TYPE_SPECIAL_TRAINING_BASE);
			tempMap.setCategory(MapConstant.CATEGORY_SPECIAL_TRAINING_BASE);
			mapDAO.updateMap(tempMap);
		}
		
		// 特训基地(据点周围)
		for (int i=0;i<MapConstant.SPECIAL_TRAINING_BASE_POSX_ARRAY_LODGMENT.length;i++) {
			tempMap = this.getMapByPos(MapConstant.SPECIAL_TRAINING_BASE_POSX_ARRAY_LODGMENT[i], MapConstant.SPECIAL_TRAINING_BASE_POSY_ARRAY_LODGMENT[i]);
			tempMap.setType(MapConstant.TYPE_SPECIAL_TRAINING_BASE);
			tempMap.setCategory(MapConstant.CATEGORY_SPECIAL_TRAINING_BASE);
			mapDAO.updateMap(tempMap);
		}
		
	}

	public void createMapFavourite(Integer playerID, Integer posX, Integer posY) {
		
		// 地图不存在
		Map map = mapDAO.getMapByPosXAndPoxY(posX, posY);
		if (map == null) {
			throw new GameException("您所收藏的地图坐标不存在。");
		}
		
		// 已添加地图收藏
		MapFavourite mapFavourite = mapFavouriteDAO.getMapFavouriteByPosXAndPosYAndPlayerID(playerID, posX, posY);
		if (mapFavourite != null) {
			throw new GameException("您已经收藏过该坐标，无法再次收藏。");
		}
		
		mapFavourite = new MapFavourite(); 
		mapFavourite.setPlayerID(playerID);
		mapFavourite.setMapID(mapDAO.getMapByPosXAndPoxY(posX, posY).getMapID());
		mapFavourite.setCreateTime(DateService.getCurrentUtilDate());
		
		mapFavouriteDAO.createMapFavourite(mapFavourite);
	}

	public void deleteMapFavourite(Integer mapFavouriteID) {
		mapFavouriteDAO.deleteMapFavouriteByID(mapFavouriteID);
	}

	public List<MapFavourite> getMapFavouritePagingList(Integer playerID, Integer start, Integer offset) {
		List<MapFavourite> mapFavouriteList =  mapFavouriteDAO.getMapPagingFavouriteListByPlayerID(playerID, start, offset);
		for (MapFavourite mapFavourite : mapFavouriteList) {
			mapFavourite.setMap(this.getMapByID(mapFavourite.getMapID()));
		}
		
		return mapFavouriteList;
	}
	
	public Integer getMapFavouriteNumOfPlayer(Integer playerID) {
		return mapFavouriteDAO.getMapFavouriteNumByPlayerID(playerID);
	}

	/**
	 * 根据城市编号获得城市名称(缓存)
	 * @param cityID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getCityNameByCityID(Integer cityID) {
		return ((java.util.Map<Integer, String>)CacheService.getFromCache(CacheConstant.CITYID_CITYNAME_MAP)).get(cityID);
	}
	
	/**
	 * 根据玩家编号获得城市编号(缓存)
	 * @param cityID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Integer getCityIDByPlayerID(Integer playerID){
		return ((java.util.Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.PLAYERID_CITYID_MAP)).get(playerID);
	}
	
	@SuppressWarnings("unchecked")
	private Integer getPlayerIDByCityID(Integer cityID) {
		return ((java.util.Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.CITYID_PLAYERID_MAP)).get(cityID);
	}
	
	public IMapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(IMapDAO mapDAO) {
		this.mapDAO = mapDAO;
	}

	public IMapFavouriteDAO getMapFavouriteDAO() {
		return mapFavouriteDAO;
	}

	public void setMapFavouriteDAO(IMapFavouriteDAO mapFavouriteDAO) {
		this.mapFavouriteDAO = mapFavouriteDAO;
	}

	public IMapMonsterDAO getMapMonsterDAO() {
		return mapMonsterDAO;
	}

	public void setMapMonsterDAO(IMapMonsterDAO mapMonsterDAO) {
		this.mapMonsterDAO = mapMonsterDAO;
	}

	public ICityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(ICityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

}
