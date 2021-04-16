package com.xzm.blog.conf;

import com.xzm.blog.task.BlogTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author xiangzhimin
 * @Description
 * @create 2021-03-21 16:26
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(BlogTask.class).withIdentity("myJob").storeDurably().build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("myJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/20 * * * ?"))
                .startNow()
                .build();
    }

    @Bean
    public Scheduler scheduler(JobDetail jobDetail,Trigger trigger) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
        return scheduler;
    }
}
