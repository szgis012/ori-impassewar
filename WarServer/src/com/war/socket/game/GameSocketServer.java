package com.war.socket.game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Socket服务(服务器端) 用于服务器与客户端数据交换(基于Apache mina)
 * 
 * @author TopTong
 * @version 1.0
 */
public class GameSocketServer implements Runnable {

	private static final int PORT = 19393;

	private static Logger logger = Logger.getLogger(GameSocketServer.class);
	
	public void run() {
		
		//
		final IoAcceptor acceptor = new NioSocketAcceptor();

		// 设定一个事件处理器   
		acceptor.setHandler(new GameSessionHandle());

		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		try {
			// 绑定一个监听端口   
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("Game Socket Server Started...");
		} catch (IOException e) {
			logger.error("异常：", e);
		}
	}
	
}
