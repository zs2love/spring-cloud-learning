/**
 * 
 */
package com.example.demo.client;

import org.springframework.stereotype.Component;

/**
 * @author shuai.b.zhang
 *
 */
@Component
public class ComputeClientHystrix implements ComputeClient {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.client.ComputeClient#add(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public Integer add(Integer a, Integer b) {
		return -9999;
	}

}
