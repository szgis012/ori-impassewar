package com.war.domain;

import java.io.Serializable;

public class Name implements Serializable {

	private static final long serialVersionUID = -5759633605658781729L;
	
	/** 名 */
	private String name;
	/** 类型(1.姓 2.名) */
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

}