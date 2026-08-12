package com.war.script;

/**
 * IGameScriptContext工厂接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IGameScriptContextFactory {
	
	/**
	 * 获得指定玩家的脚本执行上下文信息
	 * @param playerID 玩家编号
	 * @param processType 操作类型(指示脚本做什么样的操作)   检查任务完成情况2,领取任务奖励操作1.
	 * @param params 传递给脚本的特殊参数
	 * @return
	 */
	public IGameScriptContext getContext(Integer playerID,int processType,Object params);
	
}
