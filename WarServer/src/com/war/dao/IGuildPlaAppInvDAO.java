package com.war.dao;

import java.util.List;

import com.war.domain.GuildPlaAppInv;

public interface IGuildPlaAppInvDAO {

	/**
	 * 创建军团玩家申请邀请
	 * @param guildPlaAppInv
	 */
	public void createGuildPlaAppInv(GuildPlaAppInv guildPlaAppInv);

	/**
	 * 删除军团玩家申请邀请
	 * @param guildPlaAppInv
	 */
	public void deleteGuildPlaAppInv(GuildPlaAppInv guildPlaAppInv);
	
	/**
	 * 根据军团编号删除所有军团玩家申请邀请
	 * @param guildID
	 */
	public void deleteGuildPlaAppInvsByGuildID(Integer guildID);

	/**
	 * 根据玩家编号删除所有军团玩家申请邀请
	 * @param playerID
	 */
	public void deleteGuildPlaAppInvsByPlayerID(Integer playerID);
	
	/**
	 * 根据军团编号及玩家编号获得军团玩家邀请申请
	 * @param guildID
	 * @param playerID
	 * @return
	 */
	public GuildPlaAppInv getGuildPlaAppInvByGuildIDAndPlayerID(Integer guildID,Integer playerID);
	
	/**
	 * 根据玩家编号获得军团玩家邀请申请列表
	 * @param playerID
	 * @return
	 */
	public List<GuildPlaAppInv> getGuildPlaAppInvListByPlayerID(Integer playerID);
	
	/**
	 * 根据军团编号获得军团玩家邀请申请列表
	 * @param guildID
	 * @return
	 */
	public List<GuildPlaAppInv> getGuildPlaAppInvListByGuildID(Integer guildID);


}