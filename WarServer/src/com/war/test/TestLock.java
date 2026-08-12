package com.war.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
	
	public static void main(String[] args) {
		TestLock testLock = new TestLock();
		TestLock$01 testLock$01 = new TestLock$01(); 
		
		Thread thread = new Thread(new NewThread());
		thread.start();
		
		testLock$01.showMsg("01");
		
		
	}
	
	
}


class TestLock$01 {
	public void showMsg(String msg) {
		System.out.print(msg);
	}
}


class NewThread implements Runnable {
	private final Lock lock = new ReentrantLock();
	
	public void run() {
		try {
			lock.lock();
			TestLock$01 t1 = new TestLock$01();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			t1.showMsg("t001");
		} finally {
			lock.unlock();
		}
		
	}
	
}