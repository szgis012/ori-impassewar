package com.war.test.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.service.IMapService;
import com.war.webservice.IPayWS;

public class PayWSTest {
	
	private static IPayWS payWS;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		payWS = (IPayWS)SpringService.getApplicationContext().getBean("payWS");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPay() {
		for (int i=0;i<10;i++) {
			System.out.println(payWS.addPlayerMoney("toptong19", 10, "1209BFE9836F1AE5F4441325156AC8A8"));
		}
	}
	
	class Pay extends Thread {

		public void run() {
			super.run();
			System.out.println(payWS.addPlayerMoney("toptong19", 10, "1209BFE9836F1AE5F4441325156AC8A8"));
		}
		
	}
}

