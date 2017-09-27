/**
 * 
 */
package com.example.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import com.example.demo.configuration.BaseTask;

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
