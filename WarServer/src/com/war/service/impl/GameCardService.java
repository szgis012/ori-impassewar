package com.war.service.impl;

import java.sql.SQLException;

import com.war.common.RandomStringService;
import com.war.constant.GameCardTypeConstant;
import com.war.constant.TreasureConstant;
import com.war.dao.IGameCardDAO;
import com.war.dao.IPlayerCardDAO;
import com.war.domain.GameCard;
import com.war.domain.PlayerCard;
import com.war.exception.GameException;
import com.war.service.IGameCardService;
import com.war.service.ITreasureService;

public class GameCardService implements IGameCardService {

	private IGameCardDAO gameCardDAO;
	
	private IPlayerCardDAO playerCardDAO;
	
	private ITreasureService treasureService;
	
	public void activateGameCard(Integer playerID, String gameCardNO, Integer type) {
		
		GameCard gameCard = gameCardDAO.getGameCardByGameCardNOAndType(gameCardNO, type);
		
		if(gameCard==null){
			throw new GameException("卡号不存在。");
		}
		
		if(gameCard.getState()==2){
			throw new GameException("该卡号已经激活。");
		}
		
		//判断是否已经领取
		PlayerCard playerCard = playerCardDAO.getPlayerCardByPlayerIDAndType(playerID, type);
		if(playerCard!=null){
			throw new GameException("您已经领取过当前类型奖励。");
		}
		
		playerCard = new PlayerCard();
		playerCard.setPlayerID(playerID);
		playerCard.setType(type);
		playerCardDAO.createPlayerCard(playerCard);
		
		switch(type){
			case GameCardTypeConstant.NEW_PLAYER_CARD:
				treasureService.increasePlayerTreasure(playerID, TreasureConstant.NEW_PLAYER_CARD, 1);
				break;
			case GameCardTypeConstant.PLATINUM_CARD:
				treasureService.increasePlayerTreasure(playerID, TreasureConstant.PLATINUM_CARD, 1);
				break;
			case GameCardTypeConstant.TG_CARD:
				
				break;
			default:
				
		}
		
		//更新游戏卡状态为已激活
		gameCardDAO.updateStateByGameCardNOAndType(gameCardNO, type, 2);
	}
	
	public void generateGameCard(Integer type, Integer num){
		
		int gameCardNOLength = 16;
		
		GameCard[] gameCardArray = new GameCard[num];
		
		Integer maxGameCardID = gameCardDAO.getMaxGameCardIDByType(type);
		if(maxGameCardID==null){
			maxGameCardID = 10000*type;
		}
		maxGameCardID++;
		
		for(int i=0;i<num;i++){
			
			GameCard gameCard = new GameCard();
			gameCard.setGameCardNO(maxGameCardID+RandomStringService.getRandomString(gameCardNOLength-5));
			gameCard.setType(type);
			gameCard.setState(1);
			gameCardArray[i] = gameCard;
			
			maxGameCardID++;
		}
		
		try {
			gameCardDAO.createGameCardBatch(gameCardArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public IGameCardDAO getGameCardDAO() {
		return gameCardDAO;
	}

	public void setGameCardDAO(IGameCardDAO gameCardDAO) {
		this.gameCardDAO = gameCardDAO;
	}

	public IPlayerCardDAO getPlayerCardDAO() {
		return playerCardDAO;
	}

	public void setPlayerCardDAO(IPlayerCardDAO playerCardDAO) {
		this.playerCardDAO = playerCardDAO;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

}
