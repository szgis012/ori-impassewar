package com.war.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPlayerCardDAO;
import com.war.domain.PlayerCard;

public class PlayerCardDAO extends SqlMapClientDaoSupport implements IPlayerCardDAO{

	public void createPlayerCard(PlayerCard playerCard) {
		this.getSqlMapClientTemplate().insert("PlayerCard.createPlayerCard", playerCard);
	}
	
	public PlayerCard getPlayerCardByPlayerIDAndType(Integer playerID,Integer type) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("playerID", playerID);
		params.put("type", type);
		
		return (PlayerCard)this.getSqlMapClientTemplate().queryForObject("PlayerCard.getPlayerCardByPlayerIDAndType", params);
	}
	
}