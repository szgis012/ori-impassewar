package com.war.common;

import org.apache.log4j.Logger;

public class LoggerService {

	private static Logger logger = Logger.getLogger(LoggerService.class);
	
	public static void log(String log) {
		logger.info(log);
	}
	
}
