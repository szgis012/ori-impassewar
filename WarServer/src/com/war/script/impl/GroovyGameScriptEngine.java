package com.war.script.impl;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.war.script.IGameScriptContext;
import com.war.script.IGameScriptEngine;


/**
 * Groovy作为游戏脚本的IGameScriptEngine接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class GroovyGameScriptEngine implements IGameScriptEngine {
	
	//游戏脚本运行的上下文
	private IGameScriptContext context;
	//执行Groovy脚本的对象
	@SuppressWarnings("unused")
	private GroovyShell gshell = new GroovyShell();
	//GroovyClassLoader
	private GroovyClassLoader gcLoader = new GroovyClassLoader(GroovyGameScriptEngine.class.getClassLoader());
	//缓存编译的类文件
	private static Map<Integer, Class<Object>> classCache = new HashMap<Integer, Class<Object>>();
	
	
	public GroovyGameScriptEngine(){}
	
	public GroovyGameScriptEngine(IGameScriptContext context){
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public Object executeScript(String fileName) {
		try{
			System.out.println(GroovyGameScriptEngine.class.getClassLoader());
			
			//获得缓存的Class
			Class clazz = classCache.get(fileName.hashCode());
		
			
			if(clazz == null){
				//clazz = gcLoader.parseClass(gcLoader.getResourceAsStream(fileName));
				
				InputStream stream=GroovyGameScriptEngine.class.getResourceAsStream(fileName);
				clazz=gcLoader.parseClass(stream);
				
				//clazz=gcLoader.parseClass(GroovyGameScriptEngine.class.getResourceAsStream(fileName));
				//clazz = gcLoader.parseClass(new File(fileName));
				classCache.put(fileName.hashCode(), clazz);
			}
			
			GroovyObject go = (GroovyObject) clazz.newInstance();
			
			//参数   gy  
			if(context != null)
				go.setProperty(CONTEXT_KEY, context);
			
			return go.invokeMethod("run", null);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public IGameScriptContext getContext() {
		return this.context;
	}

	public void setContext(IGameScriptContext context) {
		this.context = context;
	}

}
