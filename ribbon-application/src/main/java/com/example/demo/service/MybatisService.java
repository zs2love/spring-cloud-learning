/**
 * 
 */
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author johnsmacbook
 *
 */
@Service
public class MybatisService {
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String deleteService(Integer a, Integer b) {
		return restTemplate.getForEntity("http://MYBATIS-SERVICE/delete?a=" + a + "&b=" + b, String.class).getBody();
	}

	public String addServiceFallback() {
		return "error";
	}
}
