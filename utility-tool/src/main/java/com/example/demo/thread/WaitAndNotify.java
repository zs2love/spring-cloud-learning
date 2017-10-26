/**
 * 
 */
package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */
import java.util.ArrayList;
import java.util.List;

class MyList2 {

	private static List<String> list = new ArrayList<String>();

	public static void add() {
		list.add("anyString");
	}

	public static int size() {
		return list.size();
	}
}

class ThreadE extends Thread {

	private Object lock;

	public ThreadE(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				if (MyList2.size() != 5) {
					System.out.println("wait begin " + System.currentTimeMillis());
					lock.wait();
					System.out.println("wait end " + System.currentTimeMillis());
					lock.notify();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

class ThreadF extends Thread {
	private Object lock;

	public ThreadF(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				for (int i = 0; i < 20; i++) {
					MyList2.add();
					if (MyList2.size() == 5) {
						lock.notify();
						System.out.println("已经发出了通知");
						lock.wait();
					}
					System.out.println("添加了" + (i + 1) + "个元素!");
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class WaitAndNotify {

	public static void main(String[] args) {

		try {
			Object lock = new Object();

			ThreadE a = new ThreadE(lock);
			a.start();

			Thread.sleep(50);

			ThreadF b = new ThreadF(lock);
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
