package com.war.socket;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Socket服务(服务器端) 用于服务器与客户端数据交换(基于Apache mina)
 * 
 * @author TopTong
 * @version 1.0
 */
public class FlashSecurityXMLSocketServer implements Runnable {

	private static final int PORT = 843;

	private static Logger logger = Logger.getLogger(FlashSecurityXMLSocketServer.class);
	
	public void run() {
		final IoAcceptor acceptor = new NioSocketAcceptor();

		acceptor.setHandler(new FlashSecurityXMLSessionHandle());

		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CodecFactory()));

		try {
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("FlashSecurityXML Socket Server Started...");
		} catch (IOException e) {
			logger.error("异常：", e);
		}
	}

	public static void main(String[] args) {
		new Thread(new FlashSecurityXMLSocketServer()).start();
	}
	
}
