/**
 * 
 */
package com.example.demo.thread;

/**
 * @author johnsmacbook
 *
 */
public class TestInterrupt {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Buffer buff = new Buffer();

		final Writer writer = new Writer(buff);

		final Reader reader = new Reader(buff);
		// Start the writer but writer first wait for 10 secs.
		writer.start();
		reader.start();
		/**
		 * The interrupt method is directly called, but since the writer thread is still
		 * not in block state, the InterruptedException is not thrown.
		 **/
		writer.interrupt();

		// The interrupted status should be false.
		System.out.println(writer.interrupted());

		/**
		 * After 10 secs, after the writer thread is in sleep state, the exception
		 * should be thrown.
		 **/

	}

}
