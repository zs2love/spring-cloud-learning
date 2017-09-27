/**
 * 
 */
package com.example.demo.configuration;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuai.b.zhang
 *
 */
public class BaseTask implements Job {

	@Autowired
	private Scheduler scheduler;

	@PostConstruct // 相当于init-method
	public void init() {

		JobKey jobkey = JobKey.jobKey(this.getClass().getSimpleName() + "_Job",
				this.getClass().getSimpleName() + "_group");
		JobDetail jobDetail = null;
		TriggerKey triggerKey = TriggerKey.triggerKey(this.getClass().getSimpleName() + "_Trigger",
				this.getClass().getSimpleName() + "_group");
		Trigger trigger = null;
		try {
			if (!scheduler.checkExists(jobkey)) {
				// jobDetail 获取对应运行类的名称，设置成其Job名称及Job组
				jobDetail = JobBuilder.newJob(this.getClass()).withIdentity(this.getClass().getSimpleName() + "_Job",
						this.getClass().getSimpleName() + "_group").build();

			} else {
				jobDetail = scheduler.getJobDetail(jobkey);
				scheduler.deleteJob(jobkey);
			}
			if (!scheduler.checkExists(triggerKey)) {
				// 通过Cron格式时间定义执行时间
				String cronExpress = getCronExpression();

				// trigger 获取对应运行类的名称，设置成其Trigger名称及Trigger组
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(this.getClass().getSimpleName() + "_Trigger",
								this.getClass().getSimpleName() + "_group")
						.startNow().withSchedule(CronScheduleBuilder.cronSchedule(cronExpress)).build();
			} else {
				trigger = scheduler.getTrigger(triggerKey);
			}

			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 此处的方法没任何意义，意义在其继承的子类中编写自身的逻辑执行代码
	}

	// 设置每个继承子类的执行时间，时间格式必须Cron
	public String getCronExpression() {
		return null;
	}
}
