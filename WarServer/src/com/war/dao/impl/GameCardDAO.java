package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGameCardDAO;
import com.war.domain.GameCard;

public class GameCardDAO extends SqlMapClientDaoSupport implements IGameCardDAO{

	public void createGameCard(GameCard gameCard){
		this.getSqlMapClientTemplate().insert("GameCard.createGameCard", gameCard);
	}
	
	public void createGameCardBatch(GameCard[] gameCardArray) throws SQLException {
		for(int i=0;i<gameCardArray.length;i++){
			this.getSqlMapClient().insert("GameCard.createGameCard",gameCardArray[i]);
		}
	}

	public void updateStateByGameCardNOAndType(String gameCardNO, Integer type, Integer state){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("gameCardNO", gameCardNO);
		params.put("type", type);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("GameCard.updateStateByGameCardNOAndType", params);
	}

	public Integer getMaxGameCardIDByType(Integer type){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("GameCard.getMaxGameCardIDByType",type);
	}
	
	public GameCard getGameCardByGameCardNOAndType(String gameCardNO, Integer type){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("gameCardNO", gameCardNO);
		params.put("type", type);
		
		return (GameCard)this.getSqlMapClientTemplate().queryForObject("GameCard.getGameCardByGameCardNOAndType", params);
	}
	
}