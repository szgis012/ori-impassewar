package com.war.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.war.domain.ProcessQueue;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		
		ThreadTest run01 = new ThreadTest();
		ProcessQueue processQueue1 = new ProcessQueue();
		processQueue1.setCityID(10000);
		run01.setProcessQueue(processQueue1);
		
		ProcessQueue processQueue2 = new ProcessQueue();
		processQueue1.setCityID(20000);
		
		
		Thread  t1 = new Thread(run01,"run01");
		
		run01.setProcessQueue(processQueue2);
		Thread  t2 = new Thread(run01,"run02");
		
		t1.start();
		t2.start();
		
	}
}

class ThreadTest implements Runnable {

	private static int count = 0;
	
	private ProcessQueue processQueue; 
	
	public void setProcessQueue(ProcessQueue processQueue) {
		this.processQueue = processQueue;
	}
	
	
	private String msg;
	private User user;

	@Override
	public void run() {
		
		Lock lock = new ReentrantLock();
		try {
//			lock.lock();
			count++;
			switch (count) {
				case 1:
					Singleton.getSingleton().setId(10000);
					msg = "just do it!10000";
					user = new User();
					user.name = "name:10000";
//					Thread.sleep(2000);
					Singleton.getSingleton().show(msg);
					Singleton.getSingleton().showUserName(user);
					break;
				case 2:
					Singleton.getSingleton().setId(20000);
					msg = "just do it!20000";
					user = new User();
					user.name = "name:20000";
					Singleton.getSingleton().show(msg);
					Singleton.getSingleton().showUserName(user);
					break;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			lock.unlock();
		}
		
		System.out.println(count);
		System.out.println(Thread.currentThread().getName() + ": " + processQueue.getCityID());
		System.out.println(Thread.currentThread().getName() + ": " + Singleton.getSingleton().getId());
	}
	
}

class Singleton {
	
	private static int count = 0;
	private static int countUser = 0;
	
//	private String msg;
	
	private int id ;
	
	private Singleton(){};
	private static final Singleton s = new Singleton();
	
	Lock lock = new ReentrantLock();
	
	public static Singleton getSingleton() {
		return s;
	}
	
	public void show(String msg) throws InterruptedException {
		String msg0 = null;
		
		count++;
		switch (count) {
			case 1:
				msg0 = msg;
				Thread.sleep(2000);
				break;
			case 2:
				msg0 = msg;
				break;
		}
		
		try {
			lock.lock();
			System.out.println(msg0);
		} finally {
			lock.unlock();
		}
		
//		System.out.println(msg);
	}
	
	public void showUserName(User user) throws InterruptedException {
		User user0 = null;
		
		countUser++;
		switch (countUser) {
			case 1:
				user0 = user;
				Thread.sleep(2000);
				break;
			case 2:
				user0 = user;
				break;
		}
		
		try {
			lock.lock();
			System.out.println(user0.name);
			System.out.println(user.name);
		} finally {
			lock.unlock();
		}
	}
	
	public int getId() {
		return s.id;
	}
	public void setId(int id) {
		s.id = id;
	}
}

class User {
	public String name = "Default";
}
