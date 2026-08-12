package com.war.domain;

import java.io.Serializable;

/**
 * 测试服务账号
 *
 * @author ghleed
 * @version 1.0
 */
public class ReferenceAccount implements Serializable {
	
	
	 /**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -463017197562330552L;

	private Integer id;
	private String accountName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}