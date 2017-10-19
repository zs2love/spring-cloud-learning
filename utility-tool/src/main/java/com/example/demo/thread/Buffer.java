package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */
public class Buffer {
	private Object lock;

	public Buffer() {
		lock = this;
	}

	public void write() throws InterruptedException {

		synchronized (lock) {

			long startTime = System.currentTimeMillis();

			System.out.println("开始往这个buff写入数据…");
			while (System.currentTimeMillis()

					- startTime < 10000) {

				
				
			}
			Thread.sleep(20000);
		}

		System.out.println("终于写完了");

	}

	public void read() {

		synchronized (lock) {

			System.out.println("从这个buff读数据");

		}

	}

}
