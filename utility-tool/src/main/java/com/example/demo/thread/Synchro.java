/**
 * 
 */
package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */

public class Synchro {
	public static void main(String[] args) {
		MyObject object = new MyObject();

		// 线程A与线程B 持有的是同一个对象:object
		ThreadA a = new ThreadA(object);
		ThreadB b = new ThreadB(object);
		a.start();
		b.start();
	}
}

class MyObject {

	synchronized public void methodA() {
		// do something....
		System.out.println("fuck a...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void methodB() {
		// do some other thing
		System.out.println("fuck b...");
	}
}

class ThreadA extends Thread {

	private MyObject object;

	public ThreadA(MyObject object) {
		this.object = object;
	}

	// 省略构造方法
	@Override
	public void run() {
		super.run();
		object.methodA();
	}
}

class ThreadB extends Thread {

	private MyObject object;

	public ThreadB(MyObject object) {
		this.object = object;
	}

	// 省略构造方法
	@Override
	public void run() {
		super.run();
		object.methodB();
	}
}
