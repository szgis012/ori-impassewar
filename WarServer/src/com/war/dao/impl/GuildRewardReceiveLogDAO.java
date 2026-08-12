package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGuildRewardReceiveLogDAO;
import com.war.domain.GuildRewardReceiveLog;

public class GuildRewardReceiveLogDAO extends SqlMapClientDaoSupport implements IGuildRewardReceiveLogDAO {

	public Integer createGuildRewardReceiveLog(GuildRewardReceiveLog guildRewardReceiveLog) {
		return (Integer)this.getSqlMapClientTemplate().insert("GuildRewardReceiveLog.createGuildRewardReceiveLog", guildRewardReceiveLog);
	}

	public void updateGuildRewardReceiveLog(GuildRewardReceiveLog guildRewardReceiveLog) {
		this.getSqlMapClientTemplate().update("GuildRewardReceiveLog.updateGuildRewardReceiveLog", guildRewardReceiveLog);
	}

	public void deleteGuildRewardReceiveLogByID(Integer playerID) {
		this.getSqlMapClientTemplate().delete("GuildRewardReceiveLog.deleteGuildRewardReceiveLogByID", playerID);
	}

	public GuildRewardReceiveLog getGuildRewardReceiveLogByID(Integer playerID) {
		return (GuildRewardReceiveLog)this.getSqlMapClientTemplate().queryForObject("GuildRewardReceiveLog.getGuildRewardReceiveLogByID", playerID);
	}
	
	@SuppressWarnings("unchecked")
	public List<GuildRewardReceiveLog> getGuildRewardReceiveLogList() {
		return this.getSqlMapClientTemplate().queryForList("GuildRewardReceiveLog.getGuildRewardReceiveLogList");
	}
	
}
