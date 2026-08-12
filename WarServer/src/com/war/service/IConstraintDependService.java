package com.war.service;

import java.util.List;

import com.war.domain.ConstraintDepend;

public interface IConstraintDependService {

	public List<ConstraintDepend> getConstraintDependListByTypeAndTargetID(Integer type,Integer targetID);
	
	public ConstraintDepend getConstraintDependByTypeAndTargetIDAndLevel(
			Integer type, Integer targetID, Integer level);
}
