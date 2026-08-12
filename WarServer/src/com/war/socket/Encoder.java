package com.war.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class Encoder implements ProtocolEncoder {

	public void dispose(IoSession session) throws Exception {
		// System.out.println("dispose");
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		// System.out.println("Encoder->message:" + message);
		// System.out.println("Encoder->out:" + out);
		out.write(message);
	}


}
