package com.war.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.war.service.impl.MonsterService;

public class MonsterServiceTest {

	
	@Test
	public void MonsterTest()
	{
		MonsterService monsterService=new MonsterService();
		java.util.Map<Integer, Object> monsterMap = new HashMap<Integer, Object>();
		
		monsterMap=monsterService.initMonsterMap();
		
		Iterator<Map.Entry<Integer, Object>> iterator=monsterMap.entrySet().iterator();
		
		while(iterator.hasNext())
		{
			Map.Entry<Integer, Object> map=iterator.next();
			System.out.println(map.getKey()+"-----"+map.getValue());
			
		}
		
		
		
	}
	
	
}
