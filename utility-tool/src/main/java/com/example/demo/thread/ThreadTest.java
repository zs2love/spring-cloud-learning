/**
 * 
 */
package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */
public class ThreadTest {

	public static void main(String[] args) {

		Buffer buff = new Buffer();

		final Writer writer = new Writer(buff);

		final Reader reader = new Reader(buff);

		writer.start();

		reader.start();

		new Thread(new Runnable() {

			@Override

			public void run() {

				long start = System.currentTimeMillis();

				for (;;) {

					// 等5秒钟去中断读

					if (System.currentTimeMillis()

							- start > 5000) {

						System.out.println("不等了，尝试中断");

						reader.interrupt();

						break;

					}

				}

			}

		}).start();

	}

}
