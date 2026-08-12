package com.war.dao;

import java.sql.SQLException;
import java.util.List;

import com.war.domain.Guild;

public interface IGuildDAO {

	/**
	 * 创建军团
	 * @param guild
	 * @return
	 */
	public Integer createGuild(Guild guild);

	/**
	 * 更新军团信息(名称、介绍、公告)
	 * @param guild
	 */
	public void updateGuildInfo(Guild guild);
	
	/**
	 * 更新军团
	 * @param guild
	 */
	public void updateGuild(Guild guild);
	
	/**
	 * 批量更新军团声望及排名
	 * @param guildList
	 * @throws SQLException
	 */
	public void updateGuildRenownAndRankBatch(List<Guild> guildList) throws SQLException;

	/**
	 * 根据编号删除军团
	 * @param guildID
	 */
	public void deleteGuildByID(Integer guildID);

	/**
	 * 获得军团数量
	 * @return
	 */
	public Integer getGuildNum();
	
	/**
	 * 根据军团名称获得军团编号
	 * @param guildName
	 * @return
	 */
	public Integer getGuildIDByGuildName(String guildName);
	
	/**
	 * 根据编号获得军团名称
	 * @param guildID
	 * @return
	 */
	public String getGuildNameByID(Integer guildID);
	
	/**
	 * 根据军团名称获得军团信息
	 * @param guildName
	 * @return
	 */
	public Guild getGuildByGuildName(String guildName);
	
	/**
	 * 根据编号获得军团
	 * @param guildID
	 * @return
	 */
	public Guild getGuildByID(Integer guildID);

	/**
	 * 根据开始记录及偏移获得军团分页列表
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<Guild> getGuildPagingList(Integer start,Integer offset);
	
	/**
	 * 获得军团列表
	 * @return
	 */
	public List<Guild> getGuildList();

}