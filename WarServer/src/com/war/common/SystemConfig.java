package com.war.common;

import com.war.domain.CityExt;
import com.war.domain.CityHeroExt;
import com.war.domain.GuildExt;

public class SystemConfig {

	/** 注册过滤词数组 */
	public static String[] registerFilterWordArray = null;
	
	/** 聊天过滤词数组 */
	public static String[] chatFilterWordArray = null;
	
	/** 军团扩展信息 */
	public static GuildExt defaultGuildExt = null;
	
	/** 城市扩展信息 */
	public static CityExt defaultCityExt = null;
	
	/** 城市英雄扩展信息 */
	public static CityHeroExt defaultCityHeroExt = null;
}
