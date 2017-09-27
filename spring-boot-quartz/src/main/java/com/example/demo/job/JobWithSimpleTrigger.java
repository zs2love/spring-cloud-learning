/**
 * 
 */
package com.example.demo.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import com.example.demo.configuration.QuartzSchedule;

/**
 * @author shuai.b.zhang
 *
 */
@Component
@DisallowConcurrentExecution
public class JobWithSimpleTrigger implements Job {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${cron.frequency.jobwithsimpletrigger}")
	private long frequency;
	


	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		logger.info("Running JobWithSimpleTrigger | frequency {}", frequency);
	}

	@Bean(name = "jobWithSimpleTriggerBean")
	public JobDetailFactoryBean sampleJob() {
		return QuartzSchedule.createJobDetail(this.getClass());
	}

	@Bean(name = "jobWithSimpleTriggerBeanTrigger")
	public SimpleTriggerFactoryBean sampleJobTrigger(@Qualifier("jobWithSimpleTriggerBean") JobDetail jobDetail) {
		return QuartzSchedule.createTrigger(jobDetail, frequency);
	}

}
