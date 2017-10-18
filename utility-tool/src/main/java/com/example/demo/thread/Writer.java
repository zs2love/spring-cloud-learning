package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */
public class Writer extends Thread {

	private Buffer buff;

	public Writer(Buffer buff) {

		this.buff = buff;

	}

	@Override

	public void run() {

		buff.write();

	}

}
