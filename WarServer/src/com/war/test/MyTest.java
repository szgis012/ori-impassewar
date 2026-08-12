package com.war.test;


import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;


import com.war.common.SpringService;
import com.war.listener.InitSystemListener;
import com.war.script.IGameScriptContextFactory;
import com.war.script.IGameScriptEngine;
import com.war.script.impl.GameScriptContextFactory;
import com.war.script.impl.GroovyGameScriptEngine;
import com.war.service.impl.HonorService;
import com.war.service.impl.PlayerService;

public class MyTest{
	
	@Test
	public void TaskServiceTest()
	{
		String fileName="1001.gy";
		System.out.println(fileName.hashCode());
		
	}


	@org.junit.Test
	public void TaskScriptTest()  
	{
		System.out.println("ss");
		System.out.println( this.getClass().getResource("/").getPath());
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:../WEB-INF/applicationContext.xml");
		//SpringService.setWebApplictionContext(getWebApplicationContext());

		SpringService.setCtx(new FileSystemXmlApplicationContext("D:/Program Files/Genuitec/Workspaces/WarServer/WebRoot/WEB-INF/applicationContext.xml"));
		
		// 初始化缓存
		InitSystemListener init=new InitSystemListener();
		init.initCache();
		
		IGameScriptEngine gameScriptEngine=new GroovyGameScriptEngine();
		IGameScriptContextFactory gameScriptContextFactory=new GameScriptContextFactory();
		
		gameScriptEngine.setContext(gameScriptContextFactory.getContext(1, 2, null));

		//D:/Program Files/apache-tomcat-5.5.12/webapps/War/WEB-INF/WarServer/script/task/2009.gy
		// /script/task/1001.gy
		
		// new String(out.toString().getBytes(),"gb2312"); 
		
		String result = (String)gameScriptEngine.executeScript("/script/task/1001.gy");
		
		System.out.println(result);

		
		//System.out.println(new String(gameScriptEngine.executeScript("/script/task/1001.gy").toString().getBytes(),"gb2312"));

	}
	
	
	
	
	
	public static String changeCharset(String str, String oldCharset, String newCharset) 
	{
		if (str != null) 
		{
			// 用源字符编码解码字符串
			try {
				return new String(str.getBytes(oldCharset), newCharset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static String change(String str)
	{
		String tempStr=null;
		try 
		{
			tempStr = new String (str.getBytes(),"utf-8");
			tempStr = tempStr.trim();
			tempStr = new String (str.getBytes(),"GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempStr;
		  
	}
	
	@Test
	public void EncodeTest()
	{
		String str="1����������1<font color='#00ff00'>[�����]</font>";
		System.out.println(change(str));
		
	}
	
	
}
