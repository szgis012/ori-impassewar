package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.war.dao.IMessageDAO;
import com.war.domain.Message;

/**
 * 消息DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */

public class MessageDAO extends SqlMapClientDaoSupport implements IMessageDAO{

	public void createMessageBatch(final Message[] messageArray) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				for (int i=0;i<messageArray.length;i++) {
					executor.insert("Message.createMessage", messageArray[i]);
				}
				return null;
			}
		});
	}
	
	public Integer createMessage(Message message) {
		return (Integer)this.getSqlMapClientTemplate().insert("Message.createMessage", message);
	}
	
	public void updateMessageReadFlagByID(Integer messageID,Integer readFlag){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("messageID", messageID);
		params.put("readFlag", readFlag);
		
		this.getSqlMapClientTemplate().update("Message.updateMessageReadFlagByID",params);
		
	}
	
	public void updateMessageDeleteFlagByID(Integer messageID,Integer deleteFlag){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("messageID", messageID);
		params.put("deleteFlag", deleteFlag);
		
		this.getSqlMapClientTemplate().update("Message.updateMessageDeleteFlagByID",params);
	}
	
	public void deleteMessageByID(Integer messageID) {
		this.getSqlMapClientTemplate().delete("Message.deleteMessageByID", messageID);
	}
	
	public void deleteMessages(Integer[] messageIDs) throws SQLException{
		for(int i=0;i<messageIDs.length;i++){
			this.getSqlMapClientTemplate().delete("Message.deleteMessageByID", messageIDs[i]);
		}
		
	}
	
	public Integer getMessageNumByReadFlagAndReceiverID(Integer readFlag,Integer receiverID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("readFlag", readFlag);
		params.put("receiverID", receiverID);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Message.getMessageNumByReadFlagAndReceiverID",params);
	}
	
	public Integer getMessageReadFlagByID(Integer messageID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Message.getMessageReadFlagByID", messageID);
	}
	
	public Integer getMessageDeleteFlagByID(Integer messageID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Message.getMessageDeleteFlagByID", messageID);
	}
	
	public Message getMessageByID(Integer messageID) {
		return (Message)this.getSqlMapClientTemplate().queryForObject("Message.getMessageByID", messageID);
	}
	
	public Integer getMessageAmountBySenderID(Integer senderID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Message.getMessageAmountBySenderID", senderID);
	}
	
	public Integer getMessageAmountByReceiverID(Integer receiverID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Message.getMessageAmountByReceiverID",receiverID);
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessagePagingListBySenderID(Integer senderID,
			Integer start, Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("senderID", senderID);
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Message.getMessagePagingListBySenderID",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessageListBySenderID(Integer senderID) {
		return this.getSqlMapClientTemplate().queryForList(
				"Message.getMessageListBySenderID", senderID);
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessagePagingListByReceiverID(Integer receiverID,
			Integer start, Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("receiverID", receiverID);
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("Message.getMessagePagingListByReceiverID",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessageListByReceiverID(Integer receiverID) {
		return this.getSqlMapClientTemplate().queryForList(
				"Message.getMessageListByReceiverID", receiverID);
	}

	public void sendGuildMessage(Integer guildID, Integer playerID, String title, String content) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guildID", guildID);
		params.put("playerID", playerID);
		params.put("title", title);
		params.put("content", content);
		this.getSqlMapClientTemplate().insert("Message.sendGuildMessage", params);
	}
}