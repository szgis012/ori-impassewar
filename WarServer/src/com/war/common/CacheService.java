package com.war.common;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class CacheService {

	private static GeneralCacheAdministrator cacheAdmin;
	
	private CacheService(){};
	
	static {
		cacheAdmin = new GeneralCacheAdministrator();
	}
	
	public static void putToCache(String key,Object content){
		cacheAdmin.putInCache(key, content);
	}
	
	public static Object getFromCache(String key){
		try {
			return cacheAdmin.getFromCache(key);
		} catch (NeedsRefreshException e) {
			cacheAdmin.cancelUpdate(key);
			return null;
		}
	}
	
}
