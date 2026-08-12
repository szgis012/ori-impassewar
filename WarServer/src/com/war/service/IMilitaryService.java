package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.BattleMilitary;
import com.war.domain.CityArmy;
import com.war.domain.CityMilitary;
import com.war.domain.CityMilitarySuccor;
import com.war.domain.DepoyQueue;
import com.war.domain.SpyQueue;

public interface IMilitaryService {

	/**
	 * 创建军队
	 * @param cityID
	 * @param name
	 * @param cityHeroID
	 * @return
	 */
	public Integer createCityMilitary(Integer cityID,String name,Integer cityHeroID);

    /**
     * 军队更名
     * @param cityMilitaryID
     * @param name
     */
    public void renameCityMilitary(Integer cityMilitaryID,String name);
    
    /**
     * 更新城市军队士兵
     * @param battleMilitary
     */
    public void updateCityMilitaryArmy(BattleMilitary battleMilitary);
    
    /**
     * 根据城市军队编号更新其资源消耗值
     * @param cityMilitaryID
     * @param costOil
     * @param costFood
     * @param costMoney
     */
    public void updateCityMilitaryConsume(Integer cityMilitaryID, Integer costOil, Integer costFood, Integer costMoney);
    
    /**
     * 更新地图野怪士兵
     * @param battleMilitary
     */
    public void updateMapMonsterArmy(BattleMilitary battleMilitary);
    
    /**
     * 更新军队
     * @param cityMilitary
     */
    public void updateCityMilitary(CityMilitary cityMilitary);
    
    /**
     * 删除地图野怪
     * @param mapMonsterID
     */
    public void deleteMapMonster(Integer mapMonsterID);
    
    /**
     * 更改指挥官
     * @param cityMilitaryID
     * @param cityHeroID
     */
    public void changeOfficer(Integer cityMilitaryID,Integer cityHeroID);

    //public void adjustMilitaryArmy(Integer cityMilitaryID);
    
    /**
     * 解散城市军队
     * @param cityMilitaryID
     */
    public void dismissCityMilitary(Integer cityMilitaryID);

    /**
     * 根据地图野怪编号获得地图野怪-战斗军队
     * @param mapMonsterID
     * @return
     */
    public BattleMilitary getMapMonsterBattleMilitary(Integer mapMonsterID);
    
    /**
     * 根据城市军队编号获得城市军队-战斗军队
     * @param cityMilitaryID
     * @return
     */
    public BattleMilitary getCityMilitaryBattleMilitary(Integer cityMilitaryID);
    
    /**
     * 根据指挥官编号获得所属城市军队编号
     * @param cityHeroID
     * @return
     */
    public Integer getCityMilitaryIDByCityHeroID(Integer cityHeroID);
    
    /**
     * 根据城市军队编号获得城市军队
     * @param cityMilitaryID
     * @return
     */
    public CityMilitary getCityMilitaryByID(Integer cityMilitaryID);

    /**
     * 根据城市编号获得城市军队列表
     * @return
     */
    public List<CityMilitary> getCityMilitaryList(Integer cityID);

    /**
     * 根据城市编号获得城市兵力列表
     * @param cityID
     * @return
     */
    public List<CityArmy> getCityArmyList(Integer cityID);
    
    /**
     * 获得城市军事行动信息列表
     * @param cityID
     * @return
     */
    public List<Map<String, Object>> getMilitaryActionList(Integer cityID);
    
    /**
     * 获得指定编号的侦察队列详细信息
     * @param spyQueueID
     * @return
     */
    public SpyQueue getSpyDetail(Integer spyQueueID);
    
    /**
     * 获得指定编号的出征队列详细信息
     * @param depoyQueueID
     * @return
     */
    public Map<String, Object> getAttackDetail(Integer depoyQueueID);
    
    /**
     * 加速军队返回
     * @param depoyQueueID
     */
    public void accelerateMilitaryRetruning(Integer depoyQueueID);
    
    /**
     * 编制军队
     * @param cityMilitaryID
     * @param militaryArmyStr
     */
    public void tuneCityMilitary(Integer cityMilitaryID, String militaryArmyStr);
    
	/**
	 * 处理完成侦察等待的进程
	 * @param spyQueue
	 */
	public void finishSpyWait(SpyQueue spyQueue);
	
	/**
	 * 处理完成攻击等待的进程
	 * @param depoyQueue
	 */
	public void finishAttackWait(DepoyQueue depoyQueue);
	
	/**
	 * 处理完成派遣等待的进程
	 * @param depoyQueue
	 */
	public void finishDispatchWait(DepoyQueue depoyQueue);
	
	/**
	 * 处理完成返回等待的进程
	 * @param depoyQueue
	 */
	public void finishReturnWait(DepoyQueue depoyQueue);
	
	/**
	 * 侦察地图上的某个点的信息
	 * @param fromCityID 侦察方城市编号
	 * @param num 侦察兵的数量
	 * @param toPosX 目标点X坐标值
	 * @param  toPosY 目标点Y坐标值
	 * @return
	 */
	public void spy(int fromCityID,int num,int toPosX,int toPosY);
	
	/**
	 * 根据指定坐标获得是否正在战斗或已经有军队前往
	 * @param posX
	 * @param posY
	 * @return 0.正常 1.正在战斗 2.已经有军队前往
	 */
	public Integer hasMilitaryInBattleOrGoingToMap(Integer posX,Integer posY);
	
	/**
	 * 攻击地图上的某个点
	 * @param cityMilitaryID 已编制的城市军队编号
	 * @param posX 地图X坐标
	 * @param posY 地图Y坐标
	 * @param policy 策略
	 * @return
	 */
	public DepoyQueue attack(int cityMilitaryID,int posX,int posY);
	
	/**
	 * 召回军队
	 * @param depoyQueueID
	 */
	public void recallMilitary(Integer depoyQueueID);
	
	/**
	 * 向地图上的某个点派遣军队
	 * @param cityMilitaryID 已编制的城市军队编号
	 * @param posX 地图X坐标
	 * @param posY 地图Y坐标
	 * @param carryFood 携带食物的数量
	 * @param carryWood 携带木材的数量
	 * @param carryOil 携带石油的数量
	 * @param carrySteel 携带钢铁的数量
	 * @param carryMoney 携带金钱的数量
	 * @param isCallback 是否正在进行召回操作
	 * @return
	 */
	public DepoyQueue dispatch(int cityMilitaryID,int posX,int posY,long carryFood,long carryWood,long carryOil,long carrySteel,long carryMoney, boolean isCallback);
	
	/**
	 * 客户端军队到达
	 * @param depoyQueueID
	 */
	public void clientMilitaryArrived(Integer depoyQueueID);
	
	/**
	 * 设置留守军队
	 * @param cityMilitaryID
	 */
	public void setDefensiveMilitary(int cityMilitaryID);
	
	/**
	 * 取消留守部队
	 * @param cityMilitaryID
	 */
	public void cancelDefensiveMilitary(int cityMilitaryID);
	
	/**
	 * 如果城市留守部队返回true，否则返回false
	 * @param cityID
	 * @return
	 */
	public boolean existsStayMilitary(Integer cityID);
	
	/**
	 * 城市军队攻击加强
	 * @param leadership 士气值
	 * @param armyAttackNum 士兵攻击点数
	 * @return 附加伤害,没有效果时返回0
	 */
	public Integer getCityMilitaryAttackWithLeadership(Integer leadership, Integer armyAttackNum);
	
	/**
	 * 城市军队受到的伤害加成
	 * @param leadership
	 * @param armyAttackNum
	 * @return 受到的伤害加成,没有时返回0
	 */
	public Integer getCityMilitaryBeAttackWithLeadership(Integer leadership, Integer armyAttackNum);
	
	/**
	 * 军队毫无士气时将会逃跑的士兵数
	 * @param leadership
	 * @param armyNum
	 * @return 逃跑的士兵数, 没有逃跑时返回0
	 */
	public Integer getCityMilitaryTurnTailNumWithLeadership(Integer leadership, Integer armyNum);
	
	/**
	 * 根据被支援城市编号获得其军队支援信息列表
	 * @param cityID
	 * @return
	 */
	public List<CityMilitarySuccor> getCityMilitarySuccorListByTargetCityID(Integer cityID);
	
	/**
	 * 召回支援其他城市的军队
	 * @param cityID 需要召回军队的城市的编号
	 * @param targetCityID 军队目前驻扎的城市的编号
	 * @param militaryID
	 */
	public void callbackCityMilitarySuccor(Integer cityID, Integer targetCityID, Integer cityMilitaryID);
	
	/**
	 * 支援军队战败瞬间回城
	 * @param cityMilitaryID
	 * @param targetPosX
	 * @param targetPosY
	 */
	public DepoyQueue cityMilitarySuccorBattleFailReturn(Integer cityMilitaryID, Integer targetPosX, Integer targetPosY);
	
	/**
	 * 向其他城市派遣(支援)军队
	 * @param cityID
	 * @param militaryID
	 */
	public void dispatchCityMilitarySuccor(Integer cityMilitaryID, Integer posX, Integer posY);
	
	/**
	 * 处理支援军队到期
	 */
	public void handleCityMilitarySuccorOverTime();
	
	/**
	 * 获得城市中已驻扎的军队数量
	 * @param cityID 被支援的城市编号
	 * @return
	 */
	public Integer getCityMilitarySuccorNum(Integer cityID);
	
	/**
	 * 更新支援军队的出征顺序
	 * @param cityMilitarySuccorIDs
	 * @param battleOrders(0: 待命，1：第一援军 2：第二援军　。。。)
	 */
	public void updateCityMilitarySuccorBattleOrder(int[] cityMilitarySuccorIDs, int[] battleOrders);
	
	/**
	 * 获得军队资源消耗 
	 * @param cityMilitary
	 * @return ( 下标 --> 对应数据 ) 0 --> 食物消耗; 1 --> 石油消耗; 2 --> 金钱消耗; 3 --> 总消耗
	 */
	public int[] getConsumeOfCityMilitary(String army1, String army2, String army3, String army4, String army5, String army6, String army7, String army8);
	
	/**
	 * 获得敌方军队总数目
	 * @param cityID 受到攻击的城市编号
	 * @return
	 */
	public Integer getCityMilitaryEnemyNum(Integer posX, Integer posY);
	
	/**
	 * 下一个进攻者开始攻击
	 * @param attackerMilitaryID
	 * @param mapID
	 */
	public void nextAttackerAttack(Integer attackerCityMilitaryID, Integer mapID);

	/**
	 * 获得城市支援军队信息
	 * @param cityMilitaryID
	 * @return
	 */
	public CityMilitarySuccor getCityMilitarySuccorByCityMilitaryID(Integer cityMilitaryID);
	
	/**
	 * 由于指挥官逃跑而遣散城市军队
	 * @param cityMilitaryID
	 */
	public void dismissCityMilitaryForCityHeroRunAway(Integer cityMilitaryID);
	
	/**
	 * 获得军队速度(军队最慢士兵速度)
	 * @param cityMilitary
	 * @return
	 */
	public int getMilitarySpeed(CityMilitary cityMilitary);

	/**
	 * 获得军队速度(军队最慢士兵速度)
	 * @param battleMilitary
	 * @return
	 */
	public int getMilitarySpeed(BattleMilitary battleMilitary);

	/**
	 * 敌情预警
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getMilitaryDefenseList(Integer cityID);
}
