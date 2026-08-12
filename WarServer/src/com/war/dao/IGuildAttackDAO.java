package com.war.dao;

import java.util.List;

import com.war.domain.GuildAttack;

public interface IGuildAttackDAO {

	/**
	 * 创建公会攻击
	 * @param guildAttack
	 * @return
	 */
	public Integer createGuildAttack(GuildAttack guildAttack);

	/**
	 * 删除工会攻击
	 * @param guildAttackID
	 */
	public void deleteGuildAttackByID(Integer guildAttackID);
	
	/**
	 * 根据工会编号删除所有工会攻击
	 * @param guildID
	 */
	public void deleteGuildAttacksByGuildID(Integer guildID);

	/**
	 * 根据工会攻击编号获得工会攻击
	 * @param guildAttackID
	 * @return
	 */
	public GuildAttack getGuildAttackByID(Integer guildAttackID);

	/**
	 * 根据工会编号获得工会攻击数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildAttackAmountByGuildID(Integer guildID);
	
	/**
	 * 根据工会编号获得工会攻击列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildAttack> getGuildAttackPagingListByGuildID(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 根据工会编号获得工会攻击列表
	 * @param guildID
	 * @return
	 */
	public List<GuildAttack> getGuildAttackListByGuildID(Integer guildID);

}