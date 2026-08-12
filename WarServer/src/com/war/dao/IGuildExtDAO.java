package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.GuildExt;

public interface IGuildExtDAO {
	
	/**
	 * 创建军团扩展信息
	 * @param guildExt
	 * @return
	 */
	public Integer createGuildExt(GuildExt guildExt);

	/**
	 * 更新军团扩展信息
	 * @param guildExt
	 */
	public void updateGuildExt(GuildExt guildExt);

	/**
	 * 更新军团扩展信息
	 * @param params
	 */
	public void updateGuildExtParams(Map<String, Object> params);
	
	/**
	 * 删除军团扩展信息
	 * @param guildID
	 */
	public void deleteGuildExtByID(Integer guildID);

	/**
	 * 根据编号获得军团扩展信息
	 * @param guildID
	 * @return
	 */
	public GuildExt getGuildExtByID(Integer guildID);

	/**
	 * 获得军团扩展信息列表
	 * @return
	 */
	public List<GuildExt> getGuildExtList();

}
