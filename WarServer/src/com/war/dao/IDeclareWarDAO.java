package com.war.dao;


import java.util.List;

import com.war.domain.DeclareWar;

/**
 * 宣战DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IDeclareWarDAO {

	/**
	 * 创建宣战信息
	 * @param declareWar
	 * @return
	 */
	public Integer createDeclareWar(DeclareWar declareWar);

	/**
	 * 更新宣战信息
	 * @param declareWar
	 */
	public void updateDeclareWar(DeclareWar declareWar);

	/**
	 * 删除已结束宣战列表
	 * @return
	 */
	public void deleteFinishedDeclareWarList();
	
	/**
	 * 根据编号删除宣战信息
	 * @param declareWarID
	 */
	public void deleteDeclareWarByID(Integer declareWarID);

	/**
	 * 根据玩家编号及目标玩家编号获得宣战信息
	 * @param playerID
	 * @param targetPlayerID
	 * @return
	 */
	public DeclareWar getDeclareWarByPlayerIDAndTargetPlayerID(Integer playerID,Integer targetPlayerID); 
	
	/**
	 * 根据玩家编号获得宣战信息列表
	 * @param playerID
	 * @return
	 */
	public List<DeclareWar> getDeclareWarListByPlayerID(Integer playerID);
	
	/**
	 * 获得玩家相关的宣战数量总和
	 * （包括自己的宣战记录和其他玩家对其的宣战记录）
	 * @param playerID
	 * @return
	 */
	public Integer getDeclareWarCountByPlayerID(Integer playerID);
	
}
