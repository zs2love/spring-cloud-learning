/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.MybatisClient;

/**
 * @author shuai.b.zhang
 *
 */

@RestController
public class MybatisController {
	@Autowired
	private MybatisClient mybatisClient;

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Integer delete(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
		return mybatisClient.delete(a, b);
	}

}
