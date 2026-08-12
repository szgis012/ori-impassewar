package com.war.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IMilitaryDAO;
import com.war.domain.DepoyQueue;

/**
 * 为MilitaryService服务的DAO
 *
 * @author ghleed
 * @version 1.0
 */
public class MilitaryDAO extends SqlMapClientDaoSupport implements IMilitaryDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMilitaryActionList(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("Military.getMilitaryActionList",cityID);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAttackDetail(Integer depoyQueueID) {
		return (Map<String, Object>) this.getSqlMapClientTemplate().queryForObject("Military.getAttackDetail",depoyQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getMilitaryDefenseList(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("Military.getMilitaryDefenseList",cityID);
	}

}
