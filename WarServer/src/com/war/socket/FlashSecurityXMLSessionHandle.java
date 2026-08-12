package com.war.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class FlashSecurityXMLSessionHandle extends IoHandlerAdapter {

	private static IoBuffer ioBuffer;
	
	private String policyStr = "<?xml version=\"1.0\"?><cross-domain-policy>"
			+ "<allow-access-from domain=\"*\" to-ports=\"*\"/>"
			+ "<allow-http-request-headers-from domain=\"*\" headers=\"*\"/>"
			+ "</cross-domain-policy>\0";
	
	public FlashSecurityXMLSessionHandle() {
		/*StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<cross-domain-policy>");
		xmlBuffer.append("<allow-access-from domain=\"");
		xmlBuffer.append("*");
		xmlBuffer.append("\" to-ports=\"");
		xmlBuffer.append("*");
		xmlBuffer.append("\"/>");
		xmlBuffer.append("</cross-domain-policy>");
		xmlBuffer.append("\0");		
		ioBuffer = IoBuffer.wrap(xmlBuffer.toString().getBytes());*/
		ioBuffer = IoBuffer.wrap(policyStr.toString().getBytes());
	}
	
	public void sessionCreated(IoSession session) throws Exception {
	}

	public void sessionOpened(IoSession session) throws Exception {
	}
	
	public void sessionClosed(IoSession session) throws Exception {
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
		session.write(ioBuffer);
	}

	public void messageSent(IoSession session, Object message) throws Exception {
	}

	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close();
	}
	
}
