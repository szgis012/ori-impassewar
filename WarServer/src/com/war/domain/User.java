package com.war.domain;


import java.io.Serializable;

/**
 * 用户信息
 *
 * @author ghleed
 * @version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1734527549653118287L;
	
	/** 用户编号 */
	private Integer userID;
	/** 用户名 */
	private String name;
	/** 用户密码 */
	private String password;
	/** 用户状态(0.未激活 1.正常 2.异常) */
	private Integer state;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

}