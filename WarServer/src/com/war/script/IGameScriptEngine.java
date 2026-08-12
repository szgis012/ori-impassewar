package com.war.script;

/**
 * 游戏脚本引擎
 * （用来执行游戏脚本）
 *
 * @author ghleed
 * @version 1.0
 */
public interface IGameScriptEngine {
	/**
	 * 游戏脚本引用上下文对象使用的名称
	 */
	public static final String CONTEXT_KEY = "context";
	
	/**
	 * 设置游戏脚本执行的上下文
	 * @param context
	 */
	public void setContext(IGameScriptContext context);
	
	/**
	 * 得到执行脚本的上下文
	 * @return
	 */
	public IGameScriptContext getContext();
	
	/**
	 * 执行给定的游戏脚本
	 * @param fileName 脚本文件名
	 * @return 返回脚本执行结果
	 */
	public Object executeScript(String fileName);
	
}
