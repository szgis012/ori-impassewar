package com.war.common;

import java.io.File;
import java.util.ResourceBundle;

/**
 * 资源文件操作服务
 */
public class ConfigurationService {

	private static ResourceBundle rb = null;
	private static final String CONFIG_FILE = "config" + File.separator + "config";

	private ConfigurationService() {
	}

	public static String getProperty(String key){
		if(rb==null){
			rb = ResourceBundle.getBundle(CONFIG_FILE);
		}
		return rb.getString(key);
	}
	
}
