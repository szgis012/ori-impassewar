package com.war.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class Decoder implements ProtocolDecoder {

	public void decode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput out)
			throws Exception {
		// System.out.println("Decoder->decode->session:" + session);
		// System.out.println("Decoder->decode->ioBuffer:" + ioBuffer);
		// System.out.println("Decoder->decode->out:" + out);
		out.write(ioBuffer);
	}

	public void dispose(IoSession session) throws Exception {
		// System.out.println("Decoder->dispose->session:" + session);
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// System.out.println("Decoder->finishDecode->session:" + session);
		// System.out.println("Decoder->finishDecode->out:" + out);
	}


}
