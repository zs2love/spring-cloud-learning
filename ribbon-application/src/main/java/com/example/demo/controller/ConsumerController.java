/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.ComputeService;
import com.example.demo.service.MybatisService;

/**
 * @author shuai.b.zhang
 *
 */
@RestController
public class ConsumerController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ComputeService computeService;
	@Autowired
	private MybatisService mybatisService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return computeService.addService();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "a") Integer a, @RequestParam(value = "a") Integer b) {
		return mybatisService.deleteService(a, b);
	}

}
