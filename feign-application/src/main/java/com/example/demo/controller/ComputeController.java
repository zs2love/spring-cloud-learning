/**
 * 
 */
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.ComputeClient;

/**
 * @author shuai.b.zhang
 *
 */

@RestController
public class ComputeController {

	
	@Autowired
    ComputeClient computeClient;
	
	
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }

}
