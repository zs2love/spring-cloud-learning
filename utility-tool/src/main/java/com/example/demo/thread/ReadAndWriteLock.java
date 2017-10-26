/**
 * 
 */
package com.example.demo.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shuai.b.zhang
 *
 */
class ReadReadService {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			} finally {
				lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class WriteWriteService {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ReadAndWriteLock {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			} finally {
				lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(1000 * 10);
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// Read and Write are mutual exclusion.
		ReadAndWriteLock service = new ReadAndWriteLock();
		Thread a = new Thread(service::write);
		a.setName("A");
		a.start();
		Thread.sleep(1000);

		Thread b = new Thread(service::read);
		b.setName("B");
		b.start();

		Thread.sleep(1000 * 30);
	}
}