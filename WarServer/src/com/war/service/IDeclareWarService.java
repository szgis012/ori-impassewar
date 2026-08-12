package com.war.service;

import java.util.List;

import com.war.domain.DeclareWar;

/**
 * 宣战Service接口
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IDeclareWarService {
	
	/**
	 * 宣战
	 * @param playerID
	 * @param targetPlayerID
	 * @return
	 */
	public DeclareWar declareWar(Integer playerID,Integer targetPlayerID);
	
	/**
	 * 立即宣战（使用【宣战文书】）
	 * @param playerID
	 * @param targetPlayerID
	 * @return
	 */
	public DeclareWar declareWarImmediately(Integer playerID, Integer targetPlayerID);
	
	/**
	 * 获得宣战信息
	 * @param playerID
	 * @param targetPlayerID
	 * @return
	 */
	public DeclareWar getDeclareWar(Integer playerID,Integer targetPlayerID);
	
	/**
	 * 获得玩家宣战列表
	 * @param playerID
	 * @return
	 */
	public List<DeclareWar> getPlayerDeclareWarList(Integer playerID);
	
	/**
	 * 获得玩家相关的宣战数量总和
	 * （包括自己的宣战记录和其他玩家对其的宣战记录）
	 * @param playerID
	 * @return
	 */
	public Integer getDeclareWarCountByPlayerID(Integer playerID);
	
	/**
	 * 删除已结束宣战列表
	 */
	public void deleteFinishedDeclareWarList();
	
}
