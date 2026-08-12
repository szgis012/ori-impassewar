package com.war.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.constant.CacheConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.dao.IPlayerDAO;
import com.war.dao.IPlayerTreasureDAO;
import com.war.dao.ITreasureDAO;
import com.war.dao.ITreasureHistoryDAO;
import com.war.domain.Player;
import com.war.domain.PlayerTreasure;
import com.war.domain.Treasure;
import com.war.domain.TreasureHistory;
import com.war.exception.GameException;
import com.war.script.IGameScriptContextFactory;
import com.war.script.IGameScriptEngine;
import com.war.service.ITreasureService;

/**
 * 道具Service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class TreasureService implements ITreasureService {
	
	private ITreasureDAO treasureDAO ;
	
	private ITreasureHistoryDAO treasureHistoryDAO;
	
	private IPlayerTreasureDAO playerTreasureDAO;
	
	private IPlayerDAO playerDAO;
	
	private IGameScriptEngine gameScriptEngine;
	
	private IGameScriptContextFactory gameScriptContextFactory;
	
	private DataSourceTransactionManager transactionManager;
	
	private static Logger logger = Logger.getLogger(TreasureService.class);
	
	private static final String PRE_SCRIPT_PATH = "/script/treasure/";
	
	private final Lock useTreasureLock = new ReentrantLock();
	
	private final Lock buyTreasureLock = new ReentrantLock();
	
	
	public Map<Integer, Treasure> initTreasuresMap() {
		Map<Integer, Treasure> treasuresMap = new HashMap<Integer, Treasure>();
		List<Treasure> treasureList = treasureDAO.getTreasureList();
		for (int i=0;i<treasureList.size();i++) {
			treasuresMap.put(treasureList.get(i).getTreasureID(), treasureList.get(i));
		}
		return treasuresMap;
	}
	
	public Map<Integer, List<Treasure>> initTreasureListByCategoryMap() {
		Map<Integer, List<Treasure>> treasureListByCategoryMap = new HashMap<Integer, List<Treasure>>();
		for (int i=0;i<TreasureCategoryConstant.TREASURE_CATELOGY_ARRAY.length;i++) {
			treasureListByCategoryMap.put(TreasureCategoryConstant.TREASURE_CATELOGY_ARRAY[i], treasureDAO.getTreasureListByCategory(TreasureCategoryConstant.TREASURE_CATELOGY_ARRAY[i]));
		}
		return treasureListByCategoryMap;
	}
	
	public Map<Integer, List<Treasure>> initTreasureListByTypeMap() {
		Map<Integer, List<Treasure>> treasureListByTypeMap = new HashMap<Integer, List<Treasure>>();
		List<Integer> treasureTypeList = treasureDAO.getTreasureTypeList();
		for (int i=0;i<treasureTypeList.size();i++) {
			treasureListByTypeMap.put(treasureTypeList.get(i), treasureDAO.getTreasureListByType(treasureTypeList.get(i)));
		}
		return treasureListByTypeMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> initRecommendTreasureList() {
		return (List<Treasure>)CacheService.getFromCache(CacheConstant.RECOMMEND_TREASURE_LIST);
	}
	
	public Object useTreasure(int playerID, int treasureID,Object params) {
		
		PlayerTreasure playerTreasure = playerTreasureDAO.getPlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
		if (playerTreasure==null) {
			throw new GameException("道具数量不足。");
		}
		
		Treasure treasure = this.getTreasureByID(treasureID);
		
		//DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		//TransactionStatus status = null;
		
		// 执行脚本
		try {
			useTreasureLock.lock();
			
			//status = transactionManager.getTransaction(td);
			
			// 执行脚本
			gameScriptEngine.setContext(gameScriptContextFactory.getContext(playerID,0,params));
			Object result = gameScriptEngine.executeScript(PRE_SCRIPT_PATH + treasure.getCodeSrc());
			
			// 扣除玩家道具
			if (playerTreasure.getNum() > 1) {
				playerTreasure.setNum(playerTreasure.getNum() - 1);
				playerTreasureDAO.updatePlayerTreasure(playerTreasure);
			} else {
				playerTreasureDAO.deletePlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
			}
			
			// 创建宝物使用历史记录
			TreasureHistory treasureHistory = new TreasureHistory();
			treasureHistory.setPlayerID(playerID);
			treasureHistory.setTreasureID(treasureID);
			treasureHistory.setNum(1);
			treasureHistory.setType(2);
			treasureHistory.setCreateTime(DateService.getCurrentUtilDate());
			treasureHistoryDAO.createTreasureHistory(treasureHistory);
			
			//transactionManager.commit(status);
			
			return result;
		} catch (RuntimeException re) {
			//transactionManager.rollback(status);
			throw new GameException(re.getMessage().substring(re.getMessage().indexOf(" ")+1, re.getMessage().length()));
		} catch (Exception e) {
			//transactionManager.rollback(status);
			logger.error("异常：", e);
		} finally {
			useTreasureLock.unlock();
		}
		
		//transactionManager.rollback(status);
		throw new GameException("使用道具失败。");
	}

	public Integer createTreasure(Treasure treasure) {
		return treasureDAO.createTreasure(treasure);
	}

	public void decreasePlayerTreasure(Integer playerID, Integer treasureID, int num) {
		PlayerTreasure playerTreasure = playerTreasureDAO.getPlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
		if(playerTreasure==null || playerTreasure.getNum()<num)
			throw new GameException("道具数量不足。");
		
		//如果还有道具就更新，否则删除记录
		if(playerTreasure.getNum()-num>0){
			playerTreasure.setNum(playerTreasure.getNum()-num);
			playerTreasureDAO.updatePlayerTreasure(playerTreasure);
		}else{
			playerTreasureDAO.deletePlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
		}
		
	}

	public void deletePlayerTreasure(Integer playerID) {
		playerTreasureDAO.deletePlayerTreasure(playerID);
	}

	public void deleteTreasureByID(Integer treasureID) {
		treasureDAO.deleteTreasureByID(treasureID);
	}

	public PlayerTreasure getPlayerTreasureByID(Integer playerID, Integer treasureID) {
		return playerTreasureDAO.getPlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
	}

	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID) {
		List<PlayerTreasure> playerTreasureList = playerTreasureDAO.getPlayerTreasureList(playerID);
		
		for (PlayerTreasure pt:playerTreasureList) {
			pt.setTreasure(this.getTreasureByID(pt.getTreasureID()));
		}
		
		return playerTreasureList;
	}

	@SuppressWarnings("unchecked")
	public Treasure getTreasureByID(Integer treasureID) {
		return ((Map<Integer, Treasure>)CacheService.getFromCache(CacheConstant.TREASURES_MAP)).get(treasureID);
	}
	
	public List<Treasure> getTreasureListByIDList(List<Integer> treasureIDList) {
		List<Treasure> treasureList = new ArrayList<Treasure>(treasureIDList.size());
		for (int i=0;i<treasureIDList.size();i++) {
			treasureList.add(this.getTreasureByID(treasureIDList.get(i)));
		}
		return treasureList;
	}

	public List<Treasure> getTreasureList() {
		return treasureDAO.getTreasureList();
	}
	
	public List<Treasure> getTreasureListByState(Integer state){
		return treasureDAO.getTreasureListByState(state);
	}

	public List<Treasure> getRecommendTreasureList(){
		return treasureDAO.getRecommendTreasureList();
	}
	
	public void increasePlayerTreasure(Integer playerID, Integer treasureID,int num) {
		PlayerTreasure playerTreasure = playerTreasureDAO.getPlayerTreasureByPlayerIDAndTreasureID(playerID, treasureID);
		if (playerTreasure == null) {
			// 没有相应记录则创建玩家宝物
			playerTreasure = new PlayerTreasure();
			playerTreasure.setPlayerID(playerID);
			playerTreasure.setTreasureID(treasureID);
			playerTreasure.setNum(num);
			playerTreasureDAO.createPlayerTreasure(playerTreasure);
			
		} else {
			// 更新玩家宝物数量
			playerTreasure.setNum(playerTreasure.getNum() + num);
			playerTreasureDAO.updatePlayerTreasure(playerTreasure);
		}
	}
	
	public void buyTreasure(Integer playerID, Integer treasureID, Integer num, Integer currencyType){
		
		Player player = playerDAO.getPlayerByID(playerID);
		
		if (player==null) {
			throw new GameException("玩家不存在。");
		}
		
		Treasure treasure = this.getTreasureByID(treasureID);
		
		if (treasure==null) {
			throw new GameException("道具不存在。");
		}
		
		if (num<=0) {
			throw new GameException("道具数量有误。");
		}
		
		//DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		//TransactionStatus status = null;
		
		try {
			buyTreasureLock.lock();
			
			//status = transactionManager.getTransaction(td);
			
			if (currencyType==1) {
				if(player.getMoney()<(treasure.getCost()*num)){
					throw new GameException("金币数量不足。");
				}
				this.increasePlayerTreasure(playerID, treasureID, num);
				playerDAO.updateMoney(playerID, player.getMoney() - treasure.getCost()*num);
			} else if (currencyType==2) {
				if (treasure.getCanGiftCertificateBuy()==0) {
					throw new GameException("该道具无法使用礼金购买。");
				}
				if(player.getGiftCertificate()<(treasure.getCost()*num)){
					throw new GameException("礼金数量不足。");
				}
				this.increasePlayerTreasure(playerID, treasureID, num);
				playerDAO.updateGiftCertificate(playerID, player.getGiftCertificate() - treasure.getCost()*num);
			} else {
				throw new GameException("购买道具失败。");
			}
			
			//创建宝物购买历史记录
			TreasureHistory treasureHistory = new TreasureHistory();
			treasureHistory.setPlayerID(playerID);
			treasureHistory.setTreasureID(treasureID);
			treasureHistory.setNum(num);
			treasureHistory.setType(1);
			treasureHistory.setCreateTime(DateService.getCurrentUtilDate());
			treasureHistoryDAO.createTreasureHistory(treasureHistory);
			
			//transactionManager.commit(status);
		} catch (Exception e) {
			//transactionManager.rollback(status);
			throw new GameException(e.getMessage());
		} finally {
			buyTreasureLock.unlock();
		}
	}

	public void updateTreasure(Treasure treasure) {
		treasureDAO.updateTreasure(treasure);
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureListByCategory(Integer category) {
		return ((Map<Integer, List<Treasure>>)CacheService.getFromCache(CacheConstant.TREASURE_LIST_BY_CATEOGRY_MAP)).get(category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureListByType(Integer type) {
		return ((Map<Integer, List<Treasure>>)CacheService.getFromCache(CacheConstant.TREASURE_LIST_BY_TYPE_MAP)).get(type);
	}
	
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID,Integer category) {
		
		List<PlayerTreasure> playerTreasureList = playerTreasureDAO.getPlayerTreasureListByCategory(playerID, category);
		
		for(int i=0;i<playerTreasureList.size();i++){
			playerTreasureList.get(i).setTreasure(this.getTreasureByID(playerTreasureList.get(i).getTreasureID()));
		}
		
		return playerTreasureList;
	}

	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID,Integer category, Integer type) {
		
		List<PlayerTreasure> playerTreasureList = playerTreasureDAO.getPlayerTreasureListByType(playerID, category, type);
		
		for(int i=0;i<playerTreasureList.size();i++){
			playerTreasureList.get(i).setTreasure(this.getTreasureByID(playerTreasureList.get(i).getTreasureID()));
		}
		
		return playerTreasureList;
	}

	public List<Map<String, Object>> getPlayerTreasureMapList(Integer playerID, Integer category) {
		return playerTreasureDAO.getPlayerTreasureMapList(playerID, category);
	}

	public List<Map<String, Object>> getTreasureMapListByType(Integer playerID, Integer category, Integer type) {
		return playerTreasureDAO.getTreasureMapListByType(playerID, category, type);
	}
	
	public List<TreasureHistory> getTreasureHistoryList(Integer playerID, Integer treasureID, Integer type) {
		return treasureHistoryDAO.getTreasureHistoryListByPlayerIDAndTreasureIDAndType(playerID, treasureID, type);
	}
	
	public Integer getPlayerDailyTreasureHistoryNum(Integer playerID, Integer treasureID, Integer type) { 
		return treasureHistoryDAO.getDailyTreasureHistoryNumByPlayerIDAndTreasureIDAndType(playerID, treasureID, type);
	}
	
	
	public ITreasureDAO getTreasureDAO() {
		return treasureDAO;
	}

	public void setTreasureDAO(ITreasureDAO treasureDAO) {
		this.treasureDAO = treasureDAO;
	}
	
	public ITreasureHistoryDAO getTreasureHistoryDAO() {
		return treasureHistoryDAO;
	}

	public void setTreasureHistoryDAO(ITreasureHistoryDAO treasureHistoryDAO) {
		this.treasureHistoryDAO = treasureHistoryDAO;
	}
	
	public IPlayerTreasureDAO getPlayerTreasureDAO() {
		return playerTreasureDAO;
	}

	public void setPlayerTreasureDAO(IPlayerTreasureDAO playerTreasureDAO) {
		this.playerTreasureDAO = playerTreasureDAO;
	}
	
	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public IGameScriptEngine getGameScriptEngine() {
		return gameScriptEngine;
	}

	public void setGameScriptEngine(IGameScriptEngine gameScriptEngine) {
		this.gameScriptEngine = gameScriptEngine;
	}

	public IGameScriptContextFactory getGameScriptContextFactory() {
		return gameScriptContextFactory;
	}

	public void setGameScriptContextFactory(
			IGameScriptContextFactory gameScriptContextFactory) {
		this.gameScriptContextFactory = gameScriptContextFactory;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
