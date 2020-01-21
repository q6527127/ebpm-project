package com.ebpm.common.util;
//
//import java.util.Date;
//
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//
//public class TimerQuartzUtil implements Job{
//
//	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//	        System.out.println(Thread.currentThread().getName() + " test job begin " + DateUtil.getSysDate(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
//	    }
//
//	 
//	 public static void main(String[] args) throws InterruptedException, SchedulerException {
//
//	        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//	        // 开始
//	        scheduler.start();
//	        // job 唯一标识 test.test-1
//	        JobKey jobKey = new JobKey("test" , "test-1");
//	        JobDetail jobDetail = JobBuilder.newJob(TimerQuartzUtil.class).withIdentity(jobKey).build();
//	        Trigger trigger = TriggerBuilder.newTrigger()
//	                .withIdentity("test" , "test")
//	                // 延迟一秒执行
//	                .startAt(new Date(System.currentTimeMillis() + 1000))
//	                // 每隔一秒执行 并一直重复
//	        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
//	                .build();
//	        scheduler.scheduleJob(jobDetail , trigger);
//
//	        Thread.sleep(5000);
//	        // 删除job
//	        scheduler.deleteJob(jobKey);
//	    }
//	 
//}
