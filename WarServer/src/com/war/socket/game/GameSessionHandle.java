package com.war.socket.game;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

public class GameSessionHandle extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.close();
		GameSocketService.removeSession(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		
		if(message.toString().startsWith("init")){
			//初始化GameSocket  
			GameSocketService.addSession(new Integer(message.toString().split(":")[1]),session);
		}else{
			//发送聊天消息
			
			JSONObject json = new JSONObject(message.toString());
			System.out.println(json.toString());
			
			int type = json.getInt("type");
			
			switch(type){
				case 1:
					break;
				case 2:
					//发送聊天消息
					GameSocketService.sendChatMessage(message);
					break;
				case 11:
					break;
				case 99:
					break;
				default:
					break;
			}
		}
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close();
		GameSocketService.removeSession(session);
	}
	
}
