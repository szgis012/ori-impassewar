package com.war.socket.battle;

import java.io.IOException;
import java.net.InetSocketAddress;

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
public class BattleSocketServer implements Runnable {

	private static final int PORT = 29292;

	private static Logger logger = Logger.getLogger(BattleSocketServer.class);
	
	public void run() {
		final IoAcceptor acceptor = new NioSocketAcceptor();

		acceptor.setHandler(new BattleSessionHandle());

		 /*
         * 这里注意点:
         * 1:TextLineCodecFactory设置这个过滤器一行一行(/r/n)的发送/读取数据
         */
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory()));

		try {
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("Battle Socket Server Started...");
		} catch (IOException e) {
			logger.error("异常：", e);
		}
	}

}
