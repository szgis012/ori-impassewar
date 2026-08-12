package com.war.dao;

import java.util.List;

import com.war.domain.GuildRelationship;

public interface IGuildRelationshipDAO {

	/**
	 * 创建军团关系
	 * @param guildRelationship
	 * @return
	 */
	public Integer createGuildRelationship(GuildRelationship guildRelationship);

	/**
	 * 删除军团关系
	 * @param guildRelationshipID
	 */
	public void deleteGuildRelationshipByID(Integer guildRelationshipID);
	
	/**
	 * 根据军团编号删除所有军团关系
	 * @param guildID
	 */
	public void deleteGuildRelationshipsByGuildID(Integer guildID);
	
	/**
	 * 根据目标军团编号删除所有军团关系
	 * @param targetGuildID
	 */
	public void deleteGuildRelationshipsByTargetGuildID(Integer targetGuildID);
	
	/**
	 * 根据军团编号及目标军团编号删除军团关系
	 * @param guildID
	 * @param targetGuildID
	 * @return
	 */
	public void deleteGuildRelationshipByGuildIDAndTargetGuildID(Integer guildID, Integer targetGuildID);

	/**
	 * 根据军团编号及目标军团编号获得军团关系
	 * @param guildID
	 * @param targetGuildID
	 * @return
	 */
	public GuildRelationship getGuildRelationshipByGuildIDAndTargetGuildID(Integer guildID,Integer targetGuildID);
	
	/**
	 * 根据军团关系编号获得军团关系
	 * @param guildRelationshipID
	 * @return
	 */
	public GuildRelationship getGuildRelationshipByID(Integer guildRelationshipID);

	/**
	 * 根据军团编号获得军团关系列表
	 * @param guildID
	 * @return
	 */
	public List<GuildRelationship> getGuildRelationshipListByGuildID(Integer guildID);

	/**
	 * 取得军团已有友邻关系的个数
	 * @param guildID
	 * @return Integer
	 */
	public Integer getGuildRelationshipCountByGuildID(Integer guildID);
}