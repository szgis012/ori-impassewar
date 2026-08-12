package com.war.dao;

import java.util.List;

import com.war.domain.GuildEvent;

public interface IGuildEventDAO {

	/**
	 * 创建军团事件
	 * @param guildEvent
	 * @return
	 */
	public Integer createGuildEvent(GuildEvent guildEvent);

	/**
	 * 删除军团事件
	 * @param guildEventID
	 */
	public void deleteGuildEventByID(Integer guildEventID);

	/**
	 * 根据军团编号删除所有军团事件
	 * @param guildID
	 */
	public void deleteGuildEventsByGuildID(Integer guildID);
	
	/**
	 * 根据军团事件编号获得军团事件
	 * @param guildEventID
	 * @return
	 */
	public GuildEvent getGuildEventByID(Integer guildEventID);

	/**
	 * 根据军团编号获得军团事件数量
	 * @param guildID
	 * @return
	 */
	public Integer getGuildEventAmountByGuildID(Integer guildID);
	
	/**
	 * 根据军团编号获得军团事件列表(分页)
	 * @param guildID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<GuildEvent> getGuildEventPagingListByGuildID(Integer guildID,Integer start,Integer offset);
	
	/**
	 * 根据军团编号获得军团事件列表
	 * @param guildID
	 * @return
	 */
	public List<GuildEvent> getGuildEventListByGuildID(Integer guildID);

}