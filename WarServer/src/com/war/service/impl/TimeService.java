package com.war.service.impl;

import java.util.Date;

import com.war.service.ITimeService;

public class TimeService implements ITimeService {

	public Date getServerTime() {
		return new Date();
	}

}
