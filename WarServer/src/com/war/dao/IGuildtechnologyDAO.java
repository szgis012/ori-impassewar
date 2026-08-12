package com.war.dao;

import java.util.List;

import com.war.domain.GuildTechnology;

public interface IGuildtechnologyDAO {

	public Integer createGuildtechnology(GuildTechnology guildtechnology);

	public void updateGuildtechnology(GuildTechnology guildtechnology);

	public void deleteGuildtechnologyByID(Integer guildtechnologyID);

	public GuildTechnology getGuildtechnologyByID(Integer guildtechnologyID);

	public List<GuildTechnology> getGuildtechnologyList();
}