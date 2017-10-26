/**
 * 
 */
package com.example.demo.thread;

/**
 * @author shuai.b.zhang
 *
 */
public class WakeUpMech {
	private static boolean flags = false;

	public static void main(String[] args) {
		class Person {
			public String name;
			private String gender;

			public void set(String name, String gender) {
				this.name = name;
				this.gender = gender;
			}

			public void get() {
				System.out.println(this.name + "...." + this.gender);
			}
		}
		
		
		final Person p = new Person();
		new Thread(new Runnable() {
			public void run() {
				int x = 0;
				while (true) {
					synchronized (p) {
						System.out.println("Thread 1 Start.");
						if (flags)
							try {
								p.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						;
						if (x == 0) {
							p.set("张三", "男");
						} else {
							p.set("lili", "nv");
						}
						x = (x + 1) % 2;
						flags = true;
						p.notifyAll();
						System.out.println("Thread 1 End.");
					}
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					synchronized (p) {
						System.out.println("Thread 2 Start.");
						if (!flags)
							try {
								
								p.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						;
						p.get();
						flags = false;
						p.notifyAll();
						System.out.println("Thread 2 End.");
					}
				}
			}
		}).start();
	}
}
