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

class MyList {

	private List<String> list = new ArrayList<String>();

	public void add() {
		list.add("elements");
	}

	public int size() {
		return list.size();
	}
}

class ThreadC extends Thread {

	private MyList list;

	public ThreadC(MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				list.add();
				System.out.println("添加了" + (i + 1) + "个元素");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadD extends Thread {

	private MyList list;

	public ThreadD(MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (list.size() == 5) {
					System.out.println("==5, 线程D准备退出了");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class RoundRobin {

	public static void main(String[] args) {
		MyList service = new MyList();

		ThreadC a = new ThreadC(service);
		a.setName("C");
		a.start();

		ThreadD b = new ThreadD(service);
		b.setName("D");
		b.start();
	}
}
