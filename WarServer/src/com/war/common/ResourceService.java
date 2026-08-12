package com.war.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 资源文件服务(用户类中出现的用户交互信息)
 * 
 * @author TopTong
 * @version 1.0
 */
public class ResourceService {

	private static Properties prop;

	private static Logger logger = Logger.getLogger(ResourceService.class);
	
	static {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("D:\\Program Files\\apache-tomcat-6.0.16\\webapps\\WebRPG\\WEB-INF\\classes\\messages_classes_zh_CN.properties")));
			//prop.load();
		} catch (IOException e) {
			logger.error("异常：", e);
		}
	}

	/**
	 * 根据key值从资源文件中查找并返回?
	 * @param key
	 * @return
	 */
	public static String getResource(String key) {
		return prop.getProperty(key);
	}

}
