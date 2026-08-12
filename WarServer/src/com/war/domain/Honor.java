package com.war.domain;

import java.io.Serializable;

public class Honor implements Serializable {

	private static final long serialVersionUID = 6203579061719529818L;
	
	/** 军衔编号 */
	private Integer honorID;
	/** 名称 */
	private String name;

	
	public Integer getHonorID() {
		return honorID;
	}

	public void setHonorID(Integer honorID) {
		this.honorID = honorID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}