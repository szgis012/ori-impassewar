package com.war.dao;

import java.util.List;

import com.war.domain.GuildTechnologyGuild;

public interface IGuildTechnologyguildDAO {

	public Integer createGuildTechnologyguild(GuildTechnologyGuild guildTechnologyguild);

	public void updateGuildTechnologyguild(GuildTechnologyGuild guildTechnologyguild);

	public void deleteGuildTechnologyguildByID(Integer guildTechnologyguildID);

	public GuildTechnologyGuild getGuildTechnologyguildByID(Integer guildTechnologyguildID);

	/** 
	 * 
	 * 取得军团-军团科技所有列表
	 * @return list
	 */
	public List<GuildTechnologyGuild> getGuildTechnologyguildList();
	
	/** 
	 * 
	 * 根据军团编号取得军团科技列表
	 * @param guildID	军团编号
	 * @return list
	 */
	public List<GuildTechnologyGuild> getGuildTechnologyguildListByGuildID(Integer guildID);

	/** 
	 * 
	 * 根据军团编号取得军团科技
	 * @param ID		军团科技信息编号
	 * @param guildID	军团编号
	 * @return
	 */
	public GuildTechnologyGuild getGuildTechnologyguildByGuildID(Integer ID, Integer guildID);
	
	/** 
	 * 
	 * 取得军团正在升级中的科技
	 * @param ID		军团科技信息编号
	 * @param guildID	军团编号
	 * @param state		科技当前状态
	 * @return
	 */
	public GuildTechnologyGuild getGuildTechnologyguildWithResearching(Integer ID, Integer guildID, Integer state);
	
	/**
	 * 更新军团科技状态
	 * @param guildTechnologyguildID
	 */
	public void updateGuildTechnologyguildWithState(Integer guildTechnologyguildID);
}