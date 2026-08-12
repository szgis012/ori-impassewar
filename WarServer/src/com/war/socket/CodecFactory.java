package com.war.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class CodecFactory extends DemuxingProtocolCodecFactory {

	private Decoder decoder;
	private Encoder encoder;
	
	public CodecFactory() {
		decoder = new Decoder();
		encoder = new Encoder();
	}
	
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

}
