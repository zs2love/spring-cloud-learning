/**
 * 
 */
package com.example.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author shuai.b.zhang
 *
 */
@FeignClient(value="mybatis-service")
public interface MybatisClient {
	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	Integer delete(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
