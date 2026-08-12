package com.war.domain;

import java.io.Serializable;

/**
 * 系统公告
 * @author TopTong
 *
 */
public class SystemNotice implements Serializable {

	private static final long serialVersionUID = 4303603268103817269L;
	
	/** 系统公告编号 */
	private Integer systemNoticeID;
	/** 内容 */
	private String content;


	public Integer getSystemNoticeID() {
		return systemNoticeID;
	}

	public void setSystemNoticeID(Integer systemNoticeID) {
		this.systemNoticeID = systemNoticeID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}