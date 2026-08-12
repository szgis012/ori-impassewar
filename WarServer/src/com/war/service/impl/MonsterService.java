package com.war.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.war.common.CacheService;
import com.war.common.LoggerService;
import com.war.common.MonsterConfigService;
import com.war.common.RandomService;
import com.war.constant.CacheConstant;
import com.war.constant.HeroConstant;
import com.war.constant.MapConstant;
import com.war.constant.MonsterConstant;
import com.war.dao.IMapDAO;
import com.war.dao.IMapMonsterDAO;
import com.war.domain.MapMonster;
import com.war.service.IMonsterService;
import com.war.service.INameService;

public class MonsterService implements IMonsterService {
	
	private IMapDAO mapDAO;
	
	private IMapMonsterDAO mapMonsterDAO;
	
	private INameService nameService;
	
	/** 野怪Map */
	private java.util.Map<Integer, Object> monsterMap; 
	
	private static Logger logger = Logger.getLogger(MonsterService.class);
	
	
	public java.util.Map<Integer, Object> initMonsterMap() {
		
		java.util.Map<Integer, Object> monsterMap = new HashMap<Integer, Object>();
		
		for (int i=1;i<=9;i++) {
			java.util.Map<String, Object> currentMonsterMap = new HashMap<String, Object>();
			
			String armyInfo = MonsterConfigService.getInstance().getValue(String.valueOf(i));
			System.out.println("armyInfo:"+armyInfo);
			
			String[] armyInfoArray = armyInfo.split(" ");
			String[] armyArray = armyInfoArray[1].split(";");
			
			List<java.util.Map<String, Integer>> armyList = new ArrayList<java.util.Map<String, Integer>>();
			for (int j=0;j<armyArray.length;j++) {
				java.util.Map<String, Integer> armyMap = new HashMap<String, Integer>();
				armyMap.put("armyNO", Integer.valueOf(armyArray[j].split(":")[0]));
				armyMap.put("minNum", Integer.valueOf(armyArray[j].split(":")[1].split("-")[0]));
				armyMap.put("maxNum", Integer.valueOf(armyArray[j].split(":")[1].split("-")[1]));
				armyList.add(armyMap);
			}
			
			currentMonsterMap.put("armyNum", Integer.valueOf(armyInfoArray[0]));
			currentMonsterMap.put("totalArmyNum", armyList.size());
			currentMonsterMap.put("armyList", armyList);
			
			monsterMap.put(i, currentMonsterMap);
		}

		return monsterMap;
	}
	
	public java.util.Map<Integer,java.util.Map<String,List<java.util.Map<String, Integer>>>> initMonsterDropMap() {

		java.util.Map<Integer,java.util.Map<String,List<java.util.Map<String, Integer>>>> monsterDropMap = new HashMap<Integer,java.util.Map<String,List<java.util.Map<String, Integer>>>>();
		
		try {
			for (int i = 1; i <= 9; i++) {
				FileReader fileReader = new FileReader(new File(new URI(Thread.currentThread().getContextClassLoader().getResource("") + "config/monsterdrop/" + i + ".cfg")));
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				java.util.Map<String,List<java.util.Map<String, Integer>>> subMonsterDropMap = new HashMap<String,List<java.util.Map<String, Integer>>>();
				
				String data = null;
				List<java.util.Map<String, Integer>> treasureList = new ArrayList<java.util.Map<String, Integer>>();
				List<java.util.Map<String, Integer>> equipmentList = new ArrayList<java.util.Map<String, Integer>>();
				List<java.util.Map<String,Integer>> taskItemList = new ArrayList<java.util.Map<String, Integer>>();
				List<java.util.Map<String,Integer>> resourceList = new ArrayList<java.util.Map<String, Integer>>();
				
				while ((data = bufferedReader.readLine()) != null) {

					// 如果以#开始，则为注释，继续下一行解析
					if(data.startsWith("#")){
						continue;
					}
					
					// 宝物掉落(概率)
					if (data.equals("[Treasure_Percent]")) {
						while ((data = bufferedReader.readLine()) != null && data.length() != 0) {
							java.util.Map<String, Integer> params = new HashMap<String, Integer>();
							params.put("ID", new Integer(data.split(" ")[0]));
							params.put("minPercent", new Integer(data
									.split(" ")[1].split("/")[0]));
							params.put("maxPercent", new Integer(data
									.split(" ")[1].split("/")[1]));
							treasureList.add(params);
						}
						subMonsterDropMap.put("treasurePercentList", treasureList);
					}
					
					// 宝物掉落(数量)
					if (data.equals("[Treasure_Num]")) {
						while ((data = bufferedReader.readLine()) != null && data.length() != 0) {
							java.util.Map<String, Integer> params = new HashMap<String, Integer>();
							params.put("ID", new Integer(data.split(" ")[0]));
							params.put("min", new Integer(data
									.split(" ")[1].split("-")[0]));
							params.put("max", new Integer(data
									.split(" ")[1].split("-")[1]));
							taskItemList.add(params);
						}
						subMonsterDropMap.put("treasureNumList", taskItemList);
					}
					
					// 装备掉落
					if (data.equals("[Equipment]")) {
						while ((data = bufferedReader.readLine()) != null && data.length() != 0) {
							java.util.Map<String, Integer> params = new HashMap<String, Integer>();
							params.put("ID", new Integer(data.split(" ")[0]));
							params.put("minPercent", new Integer(data
									.split(" ")[1].split("/")[0]));
							params.put("maxPercent", new Integer(data
									.split(" ")[1].split("/")[1]));
							equipmentList.add(params);
						}
						subMonsterDropMap.put("equipmentList", equipmentList);
					}
					
					// 资源掉落
					if (data.equals("[Resource]")) {
						while ((data = bufferedReader.readLine()) != null && data.length() != 0) {
							java.util.Map<String, Integer> params = new HashMap<String, Integer>();
							params.put("min", new Integer(data.split("-")[0]));
							params.put("max", new Integer(data.split("-")[1]));
							resourceList.add(params);
						}
						subMonsterDropMap.put("resourceList", resourceList);
					}

				}
				
				bufferedReader.close();
				
				monsterDropMap.put(i, subMonsterDropMap);

			}
		} catch (URISyntaxException e) {
			logger.error("异常：", e);
		} catch (FileNotFoundException e) {
			logger.error("异常：", e);
		} catch (IOException e) {
			logger.error("异常：", e);
		}

		return monsterDropMap;
	}
	
	@SuppressWarnings("unchecked")
	public void generateMapMonsterList() {
		
		// 初始化MonsterMap
		monsterMap = (java.util.Map<Integer, Object>)CacheService.getFromCache(CacheConstant.MONSTER_MAP);
		
		int mapNum;
		int monsterNum;
		int availableMonsterNum;
		int absentMonsterNum;
		List<Integer> mapIDList;
		String[] commanderNameArray;
		MapMonster mapMonster;
		
		StringBuffer stringBuffer = new StringBuffer();
		
		// 左上区域
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.AREA_1_1);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-左上区域 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.AREA_1_1, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.AREA_1_1, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
		// 左下区域
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.AREA_1_2);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-左下区域 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.AREA_1_2, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.AREA_1_2, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
		// 右上区域
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.AREA_2_1);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-右上区域 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.AREA_2_1, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.AREA_2_1, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
		// 右下区域
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.AREA_2_2);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-右下区域 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.AREA_2_2, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.AREA_2_2, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
		// 上方岛屿
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.ISLAND_1);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-上方岛屿 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.ISLAND_1, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.ISLAND_1, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
		// 下方岛屿
		mapNum = mapDAO.getBlankFieldAndMonsterFieldMapNumByArea(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.CATEGORY_MONSTER, MapConstant.ISLAND_3);
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append("野怪刷新-下方岛屿 ");
		for (int i=MonsterConstant.MIN_MONSTER_LEVEL;i<=MonsterConstant.MAX_MONSTER_LEVEL;i++) {
			monsterNum = mapDAO.getMapNumByAreaAndCategoryAndMapMonsterLevel(MapConstant.ISLAND_3, MapConstant.CATEGORY_MONSTER, i);
			availableMonsterNum = mapNum*MonsterConstant.MONSTER_MAP_PERCENT_ARRAY[i]/100;
			stringBuffer.append("等级:");
			stringBuffer.append(i);
			stringBuffer.append(" 已有数量:");
			stringBuffer.append(monsterNum);
			stringBuffer.append(" 应有数量:");
			stringBuffer.append(availableMonsterNum);
			if (monsterNum<availableMonsterNum) {
				absentMonsterNum = availableMonsterNum-monsterNum;
				stringBuffer.append(" 刷新数量:");
				stringBuffer.append(absentMonsterNum);
				stringBuffer.append(" | ");
				mapIDList = mapDAO.getRandomMapIDListByCategoryAndAreaAndNum(MapConstant.CATEGORY_BLANK_FIELD, MapConstant.ISLAND_3, absentMonsterNum);
				commanderNameArray = nameService.generateNameArray(absentMonsterNum);
				for (int j=0;j<absentMonsterNum;j++) {
					mapMonster = this.generateMapMonster(i, commanderNameArray[j]);
					mapMonsterDAO.createMapMonster(mapMonster);
					mapDAO.updateTargetIDAndCategoryByID(mapIDList.get(j), mapMonster.getMapMonsterID(), MapConstant.CATEGORY_MONSTER);
				}
			}
		}
		LoggerService.log(stringBuffer.toString());
		
	}
	
	/**
	 * 生成指定等级野怪
	 * @param level
	 * @param commanderName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private MapMonster generateMapMonster(Integer level, String commanderName) {

		Random random = new Random();

		MapMonster mapMonster = new MapMonster();

		// 野怪等级
		mapMonster.setLevel(level);

		// 指挥官等级( (等级-1)*10+(1~9) )
		int commandLevel = (level - 1) * 10 + 1 + random.nextInt(9);

		mapMonster.setCmderName(commanderName);
		mapMonster.setCmderHead(String.valueOf(random.nextInt(20) + 1));
		mapMonster.setCmderLevel(commandLevel);
		
		// 随机指挥官点数总和(范围 1*指挥官等级 ~ 3*指挥官等级)
		int totalPoint = commandLevel* (random.nextInt(HeroConstant.HERO_POINT_MULTIPLE_MAX/2) + 1)+HeroConstant.HERO_BASE_POINT;
		
		mapMonster.setCmderCommand(random.nextInt(totalPoint)+1);
		mapMonster.setCmderDefense(totalPoint-mapMonster.getCmderCommand());

		java.util.Map<String, Object> currentMonsterMap = (java.util.Map<String, Object>)monsterMap.get(level);
		List<java.util.Map<String, Integer>> armyList = (List<java.util.Map<String, Integer>>)currentMonsterMap.get("armyList");
		int[] armyIndexArray = RandomService.generateRandomNumberArray(Integer.parseInt(currentMonsterMap.get("totalArmyNum").toString())-1, Integer.parseInt(currentMonsterMap.get("armyNum").toString()));
		
		StringBuilder armyStringBuilder = new StringBuilder();
		java.util.Map<String, Integer> armyMap;
		int num;
		for (int i=0;i<armyIndexArray.length;i++) {
			armyMap = armyList.get(armyIndexArray[i]);
			num = armyMap.get("minNum") + random.nextInt(armyMap.get("maxNum")-armyMap.get("minNum")+1);
			armyStringBuilder.delete(0, armyStringBuilder.length());
			armyStringBuilder.append(armyMap.get("armyNO"));
			armyStringBuilder.append(":");
			armyStringBuilder.append(num);
			switch (i) {
				case 0:
					mapMonster.setArmy4(armyStringBuilder.toString());
					break;
				case 1:
					mapMonster.setArmy5(armyStringBuilder.toString());
					break;
				case 2:
					mapMonster.setArmy3(armyStringBuilder.toString());
					break;
				case 3:
					mapMonster.setArmy6(armyStringBuilder.toString());
					break;
				case 4:
					mapMonster.setArmy2(armyStringBuilder.toString());
					break;
				case 5:
					mapMonster.setArmy7(armyStringBuilder.toString());
					break;
				case 6:
					mapMonster.setArmy1(armyStringBuilder.toString());
					break;
				case 7:
					mapMonster.setArmy8(armyStringBuilder.toString());
					break;
				default:
					break;
			}
		}

		return mapMonster;
	}

	public MapMonster getMapMonsterByID(Integer mapMonsterID){
		return mapMonsterDAO.getMapMonsterByID(mapMonsterID);
	}
	
	
	public IMapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(IMapDAO mapDAO) {
		this.mapDAO = mapDAO;
	}

	public IMapMonsterDAO getMapMonsterDAO() {
		return mapMonsterDAO;
	}

	public void setMapMonsterDAO(IMapMonsterDAO mapMonsterDAO) {
		this.mapMonsterDAO = mapMonsterDAO;
	}

	public INameService getNameService() {
		return nameService;
	}

	public void setNameService(INameService nameService) {
		this.nameService = nameService;
	}

}
