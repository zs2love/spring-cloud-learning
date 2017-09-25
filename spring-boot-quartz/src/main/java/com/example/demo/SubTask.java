/**
 * 
 */
package com.example.demo;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * @author shuai.b.zhang
 *
 */
@Service
public class SubTask extends BaseTask {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("this is task one" + new Date());
	}

	@Override
	public String getCronExpression() {
		return "0/10 * * * * ?";
	}
}
