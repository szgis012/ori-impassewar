package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IResTradeDAO;
import com.war.domain.ResTrade;

public class ResTradeDAO extends SqlMapClientDaoSupport implements IResTradeDAO{

	public Integer createResTrade(ResTrade resTrade) {
		return (Integer)this.getSqlMapClientTemplate().insert("ResTrade.createResTrade", resTrade);
	}
	
	public void updateResTradeState(Integer resTradeID,Integer state){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("resTradeID", resTradeID);
		map.put("state", state);
		
		this.getSqlMapClientTemplate().update("ResTrade.updateResTradeState",map);
	}
	
	public void updateResTrade(ResTrade resTrade) {
		this.getSqlMapClientTemplate().update("ResTrade.updateResTrade", resTrade);
	}
	
	public void deleteResTradeByID(Integer resTradeID) {
		this.getSqlMapClientTemplate().delete("ResTrade.deleteResTradeByID", resTradeID);
	}
	
	public ResTrade getResTradeByID(Integer resTradeID) {
		return (ResTrade)this.getSqlMapClientTemplate().queryForObject("ResTrade.getResTradeByID", resTradeID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ResTrade> getResTradePagingListByCityPosOrderByDistance(Integer cityID,Integer cityPosX,Integer cityPosY,Integer resourceType,Integer start,Integer offset){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("cityID", cityID);
		map.put("cityPosX", cityPosX);
		map.put("cityPosY", cityPosY);
		if(resourceType==0){
			map.put("resourceType", null);
		}else{
			map.put("resourceType", resourceType);
		}
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("ResTrade.getResTradePagingListOrderByDistance",map);
	}
	
	public Integer getResourceSalesAmount(Integer cityID,Integer resourceType){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("cityID", cityID);
		resourceType = resourceType==0?null:resourceType;
		map.put("resourceType", resourceType);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("ResTrade.getResourceSalesAmount",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<ResTrade> getResourceSalesListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("ResTrade.getResourceSalesListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ResTrade> getResTradeList() {
		return this.getSqlMapClientTemplate().queryForList("ResTrade.getResTradeList");
	}

}