package com.war.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

	public static void main(String[] args) {
		Test2ThreadEngine threadEngine = new Test2ThreadEngine();
		Thread thread = null;
		for (int i = 0; i < 100; i++) {
			thread = new Thread(threadEngine);
			thread.start();
		}
	}
}

class Test2Lock {

	// private constructor
	private Test2Lock(){}
	private final Lock lock = new ReentrantLock();
	
	// singleton
	private static final Test2Lock test2Lock = new Test2Lock();
	
	public static Test2Lock getTest2Lock() { 
		return test2Lock;
	}
	
	public void showTest01() {
		
		try {
			lock.lock();
			
			for (int i = 0; i < 100000; i++) {
				System.out.print("1");
			}
			
		} catch (Exception e) {
			lock.unlock();
		}
		
	}
	
	public void showTest02() {
		try {
			lock.lock();
			
			for (int i = 0; i < 100000; i++) {
				System.out.print("2");
			}
			
		} catch (Exception e) {
			lock.unlock();
		}
	}
	
}

class Test2ThreadEngine implements Runnable {

	public static int count = 0;
	
	public void run() {
		count++;
		Test2Lock test2Lock = Test2Lock.getTest2Lock();
		if (count%2==0)
			test2Lock.showTest01();
		else
			test2Lock.showTest02();
	}
	
}

