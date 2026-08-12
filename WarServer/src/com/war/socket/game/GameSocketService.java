package com.war.socket.game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.SpringService;
import com.war.common.SystemConfig;
import com.war.constant.TreasureConstant;
import com.war.domain.ChatHistory;
import com.war.domain.GuildPlayer;
import com.war.exception.GameException;
import com.war.service.IChatService;
import com.war.service.IGuildService;
import com.war.service.IPlayerService;
import com.war.service.ITreasureService;

public class GameSocketService {

	private static Map<Integer,IoSession> gameSessionMap = new HashMap<Integer,IoSession>();
	
	private static IChatService chatService = (IChatService)SpringService.getBean("chatService");
	
	private static ITreasureService treasureService = (ITreasureService)SpringService.getBean("treasureService");
	
	private static IPlayerService playerService = (IPlayerService)SpringService.getBean("playerService");
	
	private static IGuildService guildService = (IGuildService)SpringService.getBean("guildService");

	private static Logger logger = Logger.getLogger(GameSocketService.class);


	public static Integer getSessionNum() {
		return gameSessionMap.size();
	}
	
	public static String getOnlinePlayerArray(){
		StringBuffer onlinePlayerBuffer = new StringBuffer();
		Iterator<Map.Entry<Integer, IoSession>> iterator = gameSessionMap.entrySet().iterator();
		while(iterator.hasNext()){
			onlinePlayerBuffer.append(iterator.next().getKey());
			onlinePlayerBuffer.append(",");
		}
		return onlinePlayerBuffer.toString();
	}
	
	public synchronized static void addSession(Integer playerID,IoSession session) {
		gameSessionMap.put(playerID, session);
	}

	public synchronized static void removeSession(IoSession session) {
		
		Iterator<Map.Entry<Integer, IoSession>> iterator = gameSessionMap.entrySet().iterator();
		Entry<Integer,IoSession> currentEntry = null;
		// 是否存在对应的session
		boolean exsitsSession = false;
		
		while(iterator.hasNext()){
			currentEntry = iterator.next();
			if(currentEntry.getValue()==session){
				exsitsSession = true;
				break;
			}
		}
		
		// 如果Session存在则清除
		if(exsitsSession){
			gameSessionMap.remove(currentEntry.getKey());
			playerService.addPlayerOnlineTime(currentEntry.getKey());
		}
		
	}
	
	public static boolean isSessionExist(Integer playerID){
		if(gameSessionMap.get(playerID) != null)
			return true;
		else
			return false;
	}
	
	public static void sendSystemNotice(String message){
		JSONObject json = new JSONObject();
		try {
			json.put("type", 1);
			json.put("message", message);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		sendToAllClient(json);
	}
	
	public static void sendChatMessage(Object message){
		
		try {
			JSONObject json = new JSONObject(message.toString());
			
			ChatHistory chatHistory = new ChatHistory();
			
			// 判断是否含有被过滤关键字
			String chatMessage = json.getString("chatMessage");
			String regexChatMessage = chatMessage;
			for(int i=0;i<SystemConfig.chatFilterWordArray.length;i++){
				regexChatMessage = regexChatMessage.replaceAll(SystemConfig.chatFilterWordArray[i], "**");
			}
			json.put("chatMessage", regexChatMessage);
			
			if(json.getInt("chatType")==1){
				// 世界聊天
				
				// 扣除喇叭
				try {
					treasureService.decreasePlayerTreasure(json.getInt("playerID"), TreasureConstant.BIG_LOUDSPEAKER, 1);
				} catch (GameException e) {
					// 道具数量不足
					json.put("chatType", 13);
					gameSessionMap.get(json.getInt("playerID")).write(json);
					return;
				}
				sendToAllClient(json);
				
				// 向玩家推送消息发送成功
				JSONObject resultJson = new JSONObject();
				resultJson.put("type", 2);
				resultJson.put("chatType", 14);
				gameSessionMap.get(json.getInt("playerID")).write(resultJson);
			}else if(json.getInt("chatType")==2){
				// 军团聊天
				List<GuildPlayer> guildPlayerList = guildService.getGuildMemberList(json.getInt("guildID"));
				IoSession tempSession = null;
				for(int i=0;i<guildPlayerList.size();i++){
					tempSession = gameSessionMap.get(guildPlayerList.get(i).getPlayerID());
					if(tempSession!=null){
						tempSession.write(json);
					}
				}
			}else if(json.getInt("chatType")==3){
				// 私聊
				Integer receiverID = playerService.getPlayerIDByPlayerName(json.getString("receiverName"));
				if(receiverID==null){
					json.put("chatType", 11);
					gameSessionMap.get(json.getInt("playerID")).write(json);
					return;
				}
				IoSession session = gameSessionMap.get(receiverID);
				if(session==null){
					json.put("chatType", 12);
					gameSessionMap.get(json.getInt("playerID")).write(json);
					return;
				}
				
				json.put("receiverID", receiverID);
				// 向发送方发送发送结果
				gameSessionMap.get(json.getInt("playerID")).write(json);
				// 向接收方发送消息
				session.write(json);
				
				chatHistory.setReceiverName(json.getString("receiverName"));
			}
			
			chatHistory.setPlayerName(json.getString("name"));
			chatHistory.setContent(chatMessage);
			chatHistory.setChatType(json.getInt("chatType"));
			// 保存聊天历史记录
			chatService.addChatHistory(chatHistory);
			
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
	}
	
	/**
	 * 将数据发送至指定客户端
	 * @param playerID
	 * @param message
	 */
	public static void sendDataToClient(Integer playerID,Object message){
		if(gameSessionMap.get(playerID)!=null){
			gameSessionMap.get(playerID).write(message);
		}
	}
	
	/**
	 * 将数据发送至所有客户端
	 * @param message
	 */
	public static void sendToAllClient(Object message){
		
		Iterator<Map.Entry<Integer, IoSession>> iterator = gameSessionMap.entrySet().iterator();
		while(iterator.hasNext()){
			iterator.next().getValue().write(message);
		}
		
	}
	
}
