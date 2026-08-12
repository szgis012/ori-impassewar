package com.war.common;

import java.io.File;
import java.util.ResourceBundle;

/**
 * 地图野怪的配置信息
 * 
 * @author TopTong
 * @version 1.0
 */
public class MonsterConfigService {

	private static Object lock = new Object();
	private static MonsterConfigService config = null;
	private static ResourceBundle rb = null;
	private static final String CONFIG_FILE = "config" + File.separator + "monsterConfig";

	private MonsterConfigService() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}

	public static MonsterConfigService getInstance() {
		synchronized (lock) {
			if (null == config) {
				config = new MonsterConfigService();
			}
		}
		return (config);
	}

	public String getValue(String key) {
		return (rb.getString(key));
	}
	
}
