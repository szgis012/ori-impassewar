package com.war.script;

import java.util.Date;
import java.util.Map;

/**
 * 游戏脚本执行的上下文 这里包含了脚本可以使用的属性，方法，这些是脚本和服务组件进行交互的接口
 * 
 * @author ghleed
 * @version 1.0
 */
public interface IGameScriptContext {
	
	/** 得到编码后的字符串，用于解决groovy脚本中返回汉字的问题 */
	public String getEncodingText(String txt);
	
	/**
	 * 操作类型(指示脚本做什么样的操作)
	 * @return 检查任务完成情况返回2,领取任务奖励操作返回1.
	 */
	public int getProcessType();
	
	/**
	 * 得到给脚本传递的特殊参数
	 * @return
	 */
	public Object getParams();
	
	/**
	 * 是否有指定等级的建筑
	 * @param buildingID 建筑编号
	 * @param level 等级
	 * @return 如果有满足条件的建筑返回true，否则返回false
	 */
	public boolean hasBuilding(int buildingID,int level);
	
	/**
	 * 获得大于等于指定等级建筑的数量
	 * @param buildingID 建筑编号
	 * @param level 建筑等级
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int buildingID,int level);
	
	/**
	 * 获得指定等级建筑的所有数量(等级大于0的建筑数量)
	 * @param buildingID 建筑编号
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int buildingID);
	
	/**
	 * 获得建筑的最大等级
	 * @param buildingID 建筑编号
	 * @return 返回该建筑的最大等级，如果没有该建筑或者建筑正在建造中返回0
	 */
	public int getBuildingMaxLevel(int buildingID);
	
	/**
	 * 获得木材工人数量
	 * @return 返回木材厂当前工人数量
	 */
	public int getWoodWorkerNum();
	
	/**
	 * 获得钢铁工人数量
	 * @return 返回钢铁厂当前工人数量
	 */
	public int getSteelWorkerNum();
	
	/**
	 * 获得石油工人数量
	 * @return 返回炼油厂当前工人数量
	 */
	public int getOilWorkerNum();
	
	/**
	 * 获得农场工人数量
	 * @return 返回农场当前工人数量
	 */
	public int getFoodWorkerNum();
	
	/**
	 * 获得玩家招募的市民数量
	 * @return 返回已招募的市民数量,如果玩家没有招募市民返回0
	 */
	public int getEnlistCitizen();
	
	/**
	 * 获得城市人口上限
	 * @return
	 */
	public long getCityMaxPopulation();
	
	/**
	 * 获得城市某兵的数量
	 * @param armyID 兵种编号
	 * @return 返回城市里拥有的该兵数量，如果没有该兵返回0
	 */
	public int getCityArmyNum(int armyID);
	
	/**
	 * 获得城市拥有的指挥官数量
	 * @return 返回指挥官的数量，如果没有任何指挥官返回0
	 */
	public int getCityHeroNum();
	
	/**
	 * 获得城市已编制的军队数量
	 * @return 返回已编制军队数量，如果没有编制任何军队返回0
	 */
	public int getCityMilitaryNum();
	
	/**
	 * 获得城防
	 * @param type 城防类型(DefenseConstant类中定义)
	 * @return 返回相应城防的数量
	 */
	public int getCityDefenseNum(int defenseID);
	
	/**
	 * 获得科技等级
	 * @param technologyID 科技编号
	 * @return 返回科技的等级，如果科技没有升级返回0
	 */
	public int getTechnologyLevel(int technologyID);
	
	/**
	 * 获得城市的军械数量
	 * @param ordnanceID 军械编号
	 * @return 返回军械的数量，如果没有军械返回0
	 */
	public int getCityOrdnanceNum(int ordnanceID);
	
	/**
	 * 获得城市新兵的数量
	 * @return 返回新兵的数量,如果没有新兵返回0
	 */
	public int getRecruitNum();
	
	/**
	 * 获得玩家指定宝物的数量
	 * @param treasureID 宝物编号
	 */
	public int getPlayerTreasureNum(int treasureID);
	
	/**
	 * 获得玩家的声望值
	 * @return
	 */
	public long getRenown();
	
	/**
	 * 获得玩家军衔编号
	 * @return
	 */
	public Integer getHonorID();
	
	/**
	 * 获得城市的金钱数量
	 * @return
	 */
	public long getMoneyNum();
	
	/**
	 * 如果城市存在执政官返回true，否则返回false
	 * @return
	 */
	public boolean existsCityOfficer();
	
	/**
	 * 如果城市留守部队返回true，否则返回false
	 * @return
	 */
	public boolean existsStayMilitary();
	
	/**
	 * 是否已经创建或者加入了工会
	 * @return
	 */
	public boolean hasCreateOrJoinGuild();
	
	/**
	 * 是否已经攻击了中立军队
	 * @return
	 */
	public boolean hasAttackMonster();
	
	/**
	 * 是否已使用了宝物
	 * @param treasureID
	 * @return
	 */
	public boolean hasUsedTreasure(int treasureID);
	
	/**
	 * 是否已在世界频道进行聊天
	 * @param playerName
	 * @return
	 */
	public boolean hasChatedInWorldScope();
	
	/**
	 * 是否已在军团频道进行聊天
	 * @return
	 */
	public boolean hasChatedInGuildScope();
	
	/**
	 * 奖励宝物
	 * @param treasureID 宝物编号
	 * @param num 奖励的宝物数量
	 */
	public void rewardTreasure(int treasureID,int num);
	
	/**
	 * 扣除宝物
	 * @param treasureID
	 * @param num
	 */
	public void minusTreasure(int treasureID, int num);
	
	/**
	 * 奖励军械
	 * @param ordanceID 军械编号
	 * @param num 奖励的数量
	 */
	public void rewardOrdnance(int ordanceID,int num);
	
	/**
	 * 奖励市民
	 * @param num 奖励的数量
	 */
	public void rewardCitizen(int num);
	
	/**
	 * 奖励资源
	 * @param woodNum 奖励的木材数量
	 * @param steelNum 奖励的钢铁数量
	 * @param oilNum 奖励的石油数量
	 * @param foodNum 奖励的食物数量
	 * @param moneyNum 奖励的金钱数量
	 */
	public void rewardResource(int woodNum,int steelNum,int oilNum,int foodNum,int moneyNum);
	
	/**
	 * 捐献资源
	 * @param woodNum 捐献的木材数量
	 * @param steelNum 捐献的钢铁数量
	 * @param oilNum 捐献的石油数量
	 * @param foodNum 捐献的食物数量
	 * @param moneyNum 捐献的金钱数量
	 */
	public void minusResource(int woodNum, int steelNum, int oilNum, int foodNum, int moneyNum);
	
	/**
	 * 奖励城防
	 * @param type 城防类型(DefenseConstant类中定义)
	 * @param num 奖励的城防数量
	 */
	public void rewardCityDefense(int type,int num);
	
	/**
	 * 奖励装备
	 * @param equipmentID 装备编号
	 * @param num 奖励的装备数量
	 */
	public void rewardEquipment(int equipmentID,int num );
	
	/**
	 * 奖励声望
	 * @param renown 奖励的声望值
	 */
	public void rewardRenown(long renown);
	
	/**
	 * 减少声望
	 * @param renown
	 */
	public void reduceRenown(long renown);
	
	/**
	 * 减少道具
	 * @param treasureID
	 * @param num
	 */
	public void reduceTreasure(Integer treasureID, Integer num);
	
	/**
	 * 更新玩家的军衔,同时更新与军衔相关的任务信息
	 * @param honorID
	 */
	public void updateHonor(Integer honorID);
	
	/**
	 * 减少用户某个宝物的数量
	 * 处理包括两种情况：
	 * 1.如果用户还没有该宝物，或者减少的num比宝物的之前的数量要大则抛出运行时异常
	 * 2.否则就在之前的数量-num
	 * @param treasureID 宝物编号
	 * @param num 数量
	 */
	public void decreasePlayerTreasure(Integer treasureID,int num);
	
	/**
	 * 获得城市正在售卖的资源交易数目
	 * @return
	 */
	public int getCityResourceSalesNum();
	
	/**
	 * 获得正在购买的资源交易数
	 * @return
	 */
	public int getImportResourceTradeNum();
	
	/**
	 * 获得身上有装备的指挥官数目
	 * @return
	 */
	public int getEquipedCityHeroNum();
	
	/**
	 * 获得玩家地图收藏数目
	 * @return
	 */
	public int getMapFavouriteNum();
	
	/**
	 * 获得玩家参与进攻的战斗日志数目
	 * @return
	 */
	public int getPlayerAttackBattleLogNum();
	
	/**
	 * 获得玩家好友数目
	 * @return
	 */
	public int getFriendNum();
	
	/**
	 * 玩家是否已加入军团
	 * @return
	 */
	public boolean hasJoinedGuild();
	
	/**
	 * 建筑是否完成升级
	 * @param buildingID
	 * @param level	要达到的等级
	 * @return
	 */
	public boolean buildingUpgradeHasFinished(Integer buildingID, Integer level) ;
	
	/**
	 * 获得城市正在建造的建筑数量
	 * @return
	 */
	public int getCityBuildBuildingNum() ;
	
	/**
	 * 获得城市税收额
	 * @return
	 */
	public int getCityTex();
	
	/**
	 * 用户是否执行过这个动作
	 * @return
	 */
	public boolean hasPerformTheOperation(String operation);
	
	/**
	 * 获得玩家的军团贡献值
	 * @return
	 */
	public long getGuildPlayerContribution();
	
	/**
	 * 是否领取过军团补贴
	 * @return
	 */
	public boolean hasReceivedSubsidy();
	
	/**
	 * 城市中所有科技是否都达到了指定等级
	 * @param level
	 * @return
	 */
	public boolean hasAllTechnologReachedTheLevel(Integer level);
	
	/**
	 * 奖励城市军队
	 * @param armyID
	 * @param num
	 */
	public void rewardCityArmy(Integer armyID, Integer num);
	
	/**
	 * 是否完成指定目标的侦查任务 
	 * @param operation
	 * @param level
	 * @param date 界定时间: 获得的记录都在此时间之后
	 * @return
	 */
	public boolean hasCompleteSpyForSpecifyLevel(String operation, Integer level, Date date);
	
	/**
	 * 是否完成指定目标的掠夺任务 
	 * @param level
	 * @param date
	 * @return 界定时间：获得的记录都在此时间之后
	 */
	public boolean hasCompleteAttackForSpecifyLevel(Integer level, Date date);
	
	/**
	 * 交换宝物
	 * @param originalTreasureID 被用来交换的宝物编号
	 * @param fromNum 被用来交换的宝物的数量
	 * @param targetTreasureNum 所要交换的宝物编号
	 * @param toNum 所要交换的宝物的数量
	 */
	public void exchangeTreasure(Integer originalTreasureID, Integer fromNum, Integer targetTreasureID, Integer toNum);
	
	/**
	 * 交换部队
	 * @param originalAmryID 被用来交换的部队编号
	 * @param fromNum 被用来交换的部队的数量
	 * @param targetTreasureID 所要交换的部队编号
	 * @param toNum 所要交换的部队的数量
	 */
	public void exchangeArmy(Integer originalAmryID, Integer fromNum, Integer targetTreasureID, Integer toNum);
	
	/**
	 * 获得城市资源数目（木，铁，油，食，钱）
	 * 其中的key 为: woodNum, steelNum, oilNum, foodNum, moneyNum
	 * @return
	 */
	public Map<String, Long> getCityResource();
	
	/**
	 * 奖励礼金
	 * @param num
	 */
	public void rewardGiftCertificate(Integer num);
	
	/**
	 * 获得已编制的城市军队数量
	 * @return
	 */
	public Integer getHasArmyCityMilitaryNum();
	
	/************* 以下接口为宝物使用 ******************/
	
	
	/**
	 * 设置宝物给食物的生产加成
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setFoodTreasureAdd(int num);
	
	/**
	 * 设置宝物给木材的生产加成
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setWoodTreasureAdd(int num);
	
	/**
	 * 设置宝物给钢铁的生产加成
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setSteelTreasureAdd(int num);
	
	/**
	 * 设置宝物给石油的生产加成
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setOilTreasureAdd(int num);
	
	/**
	 * 设置宝物给食物的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setFoodTreasureAdd(int num,int days);
	
	/**
	 * 设置宝物给金钱的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setMoneyTreasureAdd(int num);
	
	/**
	 * 设置宝物给木材的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setWoodTreasureAdd(int num,int days);
	
	/**
	 * 设置宝物给钢铁的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setSteelTreasureAdd(int num,int days);
	
	/**
	 * 设置宝物给石油的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setOilTreasureAdd(int num,int days);
	
	/**
	 * 设置宝物给金钱的生产加成,持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setMoneyTreasureAdd(int num, int days);
	
	/**
	 * 增加仓储容量的上限，持续时间为days天
	 * @param num 加成的百分比(如：加成10%，num为10)
	 */
	public void setStorageTreasureAdd(int num,int days);
	
	/**
	 * 减少建筑建造，升级，拆除或者科技研究花费的时间
	 * @param queueID 进程编号
	 * @param reduceTime 减少时间，以秒为单位
	 */
	public void reduceBuildCostTime(int queueID, int reduceTime);
	
	/**
	 * 减少科技升级花费时间
	 * @param queueID 进程编号
	 * @param reduceTime 减少时间，以秒为单位
	 */
	public void reduceTechResearchCostTime(int queueID, int reduceTime);
	
	/**
	 * 减少军械制造花费时间
	 * @param queueID 进程编号
	 * @param reduceTime 减少时间，以秒为单位
	 */
	public void reduceOrdnanceProductCostTime(int queueID, int reduceSecond);
	
	/**
	 * 减少交易花费的时间
	 * @param queueID 进程编号
	 */
	public void reduceTradeCostTime(int queueID);
	
	/**
	 * 减少城市的金钱
	 * @param money 减少的数量
	 */
	public void reduceMoneyNum(long money);
	
	//TODO 缩减交易时间的接口
	
	/**
	 * 平息暴乱
	 */
	public void suppressRiot();
	
	//TODO 刷新一次指挥官征召列表
	
	/**
	 * 设定免战时间，单位小时
	 * @param hours 免战时间
	 */
	public void avoidWar(int hours);
	
	/**
	 * 增加10%人口上限
	 * @param days 持续的天数
	 */
	public void addCityPopulationMax(int days);
	
	/**
	 * 增加所有军队攻击力
	 * @param num 加成的百分比(如：加成10%，num为10)
	 * @param days 持续的天数
	 */
	public void setMilitaryAttackAdd(int num,int days);
	
	/**
	 * 增加所有军队防御力
	 * @param num 加成的百分比(如：加成10%，num为10)
	 * @param days 持续的天数
	 */
	public void setMilitaryDefenseAdd(int num,int days);
	
	/**
	 * 战略欺骗
	 * @param days 持续的天数
	 */
	public void strategyCheat(int days);
	
	/**
	 * 战略伪装
	 * @param days 持续的天数
	 */
	public void strategyCamouflage(int days);
	
	/**
	 * 指挥官经验加成
	 * @param num 加成的百分比(如：加成10%，num为10)
	 * @param days 持续的天数
	 */
	public void setCommanderExpAdd(int cityHeroID, int num, int days);
	
	/**
	 * 迁移城市位置(随机位置)
	 * @param cityID 城市编号
	 */
	public void moveCity(Integer mapArea);
	
	/**
	 * 迁移城市位置(指定坐标)
	 * @param posX
	 * @param posY
	 */
	public void moveCityToTargetPosition(int posX, int posY);
	
	/**
	 * 增加城市英雄体力
	 * @param cityHeroID
	 * @param addStamina
	 */
	public void addCityHeroStamina(int cityHeroID, int addStamina);
	
	/**
	 * 增加城市英雄最大技能数量
	 * @param cityHeroID
	 * @param num
	 */
	public void addCityHeroMaxSkillNum(int cityHeroID,int num);
	
	/**
	 * 刷新城市候选英雄列表
	 */
	public void refreshCityCandidacyHeroList();
	
	/**
	 * 完成生产/征召
	 * @param queueID
	 */
	public void finishProduction(int queueID);
	
	/**
	 * 获得建造队列列表
	 */
	public int getProductionQueueNum();

	/**
	 * 指挥官统御加成
	 * @param cityHeroID
	 * @param addReinRate
	 * @param day
	 */
	public void setCityHeroReinAdd(int cityHeroID, int addReinRate, int day);

	/**
	 * 增加指挥官领导力
	 * @param cityHeroID
	 * @param addLeadershipPoint
	 */
	public void addCityHeroLeadership(int cityHeroID, int addLeadershipPoint);

	/**
	 * 增加指挥官经验值
	 * @param cityHeroID
	 * @param addExpPoint
	 */
	public void addCityHeroExp(int cityHeroID, Long addExpPoint);

	/**
	 * 指挥官完美升级
	 * @param cityHeroID
	 */
	public void cityHeroPerfectLevelUp(int cityHeroID);

	/**
	 * 国家宝藏摇奖机
	 * @return
	 */
	public String nationalTreasureErnie();

	/**
	 * 获得战场功勋箱宝物
	 * @param treasures
	 * @return
	 */
	public String[] getBattleHonorBoxTreasure(String[] treasures);

	/**
	 * 获得高级名誉箱宝物
	 * @return
	 */
	public String getAdvanceHonorBoxTreasure();

	/**
	 * 使用再生药水
	 * @param cityHeroID
	 */
	public void useRebornMedicament(Integer cityHeroID);

	/**
	 * 使用高级再生药水
	 * @param cityHeroID
	 */
	public void useAdvanceRebornMedicament(Integer cityHeroID);

	/** 使用战魂铜章 */
	public void useFightSoulCopperMedal(Integer cityHeroID);

	/** 使用战魂银章 */
	public void useFightSoulSilverMedal(Integer cityHeroID);

	/** 使用战魂金章 */
	public void useFightSoulGoldMedal(Integer cityHeroID);

	/** 使用宣战文书 */
	public void useDeclareWarAnnouncement(String playerName);

	/**
	 * 指挥官指挥属性加成
	 * @param cityHeroID
	 * @param addCommandPoints
	 * @param days
	 */
	public void setCityHeroCommandAdd(int cityHeroID, int addCommandPoints, int days);

	/**
	 * 指挥官防御属性加成
	 * @param cityHeroID
	 * @param addDefensePoints
	 * @param days
	 */
	public void setCityHeroDefenseAdd(int cityHeroID, int addDefensePoints, int days);

	/**
	 * 指挥官思维属性加成
	 * @param cityHeroID
	 * @param addDefensePoints
	 * @param days
	 */
	public void setCityHeroMindAdd(int cityHeroID, int addMindPoints, int days);

	/**
	 * 指挥官行政属性加成
	 * @param cityHeroID
	 * @param addDefensePoints
	 * @param days
	 */
	public void setCityHeroExecutivepowerAdd(int cityHeroID, int addExecutivepowerPoints, int days);

}
