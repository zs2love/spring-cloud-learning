/**
 * 
 */
package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shuai.b.zhang
 *
 */
public class DynamicJob implements Job {

	private final static Logger LOG = LoggerFactory.getLogger(DynamicJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("----- Running Dynamic Job With Simple Trigger ------");
	}

}
