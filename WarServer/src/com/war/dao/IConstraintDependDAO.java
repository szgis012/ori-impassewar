package com.war.dao;

import java.util.List;

import com.war.domain.ConstraintDepend;

public interface IConstraintDependDAO {

	/**
	 * 创建约束依赖
	 * @param constraintDepend
	 * @return
	 */
	public Integer createConstraintDepend(ConstraintDepend constraintDepend);

	/**
	 * 更新约束依赖
	 * @param constraintDepend
	 */
	public void updateConstraintDepend(ConstraintDepend constraintDepend);

	/**
	 * 根据约束依赖编号删除约束依赖
	 * @param constraintDependID
	 */
	public void deleteConstraintDependByID(Integer constraintDependID);

	/**
	 * 根据约束依赖编号获得约束依赖
	 * @param constraintDependID
	 * @return
	 */
	public ConstraintDepend getConstraintDependByID(Integer constraintDependID);
	
	/**
	 * 根据类型及目标编号获得约束依赖列表
	 * @param type
	 * @param targetID
	 * @return
	 */
	public List<ConstraintDepend> getConstraintDependListByTypeAndTargetID(Integer type,Integer targetID);
	
	
	public ConstraintDepend getConstraintDependByTypeAndTargetIDAndLevel(Integer type,Integer targetID,Integer level);

	/**
	 * 获得约束依赖列表
	 * @return
	 */
	public List<ConstraintDepend> getConstraintDependList();

}