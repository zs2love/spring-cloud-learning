/**
 *
 */
package com.example.demo.model;

import java.io.Serializable;

/**
 * @author shuai.b.zhang
 *
 */
public class Person implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int compareByAge(int a, int b) {
		return a - b;
	}

}
