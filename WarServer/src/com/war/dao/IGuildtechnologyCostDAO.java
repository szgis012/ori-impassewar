package com.war.dao;

import java.util.List;

import com.war.domain.GuildtechnologyCost;

public interface IGuildtechnologyCostDAO {

	public Integer createGuildtechnologyCost(GuildtechnologyCost guildtechnologyCost);

	public void updateGuildtechnologyCost(GuildtechnologyCost guildtechnologyCost);

	public void deleteGuildtechnologyCostByID(Integer guildtechnologyCostID);

	public GuildtechnologyCost getGuildtechnologyCostByID(Integer guildtechnologyCostID);

	public List<GuildtechnologyCost> getGuildtechnologyCostList();

	public GuildtechnologyCost getGuildtechnologyCostListByTechnologyIDAndLevel(Integer guildTechnologyID, Integer requiredGuildLevel);
}