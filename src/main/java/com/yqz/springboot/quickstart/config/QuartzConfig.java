package com.yqz.springboot.quickstart.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.yqz.springboot.quickstart.task.HelloTask;

@Configuration
public class QuartzConfig {
	/** 
     * attention: 
     * Details：配置定时任务 
     */  
    @Bean(name = "jobDetail")  
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(HelloTask task) {// ScheduleTask为需要执行的任务  
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();  
        /* 
         *  是否并发执行 
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了， 
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行 
         */  
        jobDetail.setConcurrent(false);  
         
        jobDetail.setName("srd-chhliu");// 设置任务的名字  
        jobDetail.setGroup("srd");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用  
          
        /* 
         * 为需要执行的实体类对应的对象 
         */  
        jobDetail.setTargetObject(task);  
          
        /* 
         * sayHello为需要执行的方法 
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法 
         */  
        jobDetail.setTargetMethod("sayHello");  
        return jobDetail;  
    }  
      
    /** 
     * attention: 
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务 
     */  
    @Bean(name = "jobTrigger")  
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {  
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();  
        tigger.setJobDetail(jobDetail.getObject());  
        tigger.setCronExpression("* */33 * * * ?");// 初始时的cron表达式  
        tigger.setName("srd-chhliu");// trigger的name  
        return tigger;  
  
    }  
  
    /** 
     * attention: 
     * Details：定义quartz调度工厂 
     */  
    @Bean(name = "scheduler")  
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {  
        SchedulerFactoryBean bean = new SchedulerFactoryBean();  
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job  
        bean.setOverwriteExistingJobs(true);  
        // 延时启动，应用启动1秒后  
        bean.setStartupDelay(1);  
        // 注册触发器  
        bean.setTriggers(cronJobTrigger);  
        return bean;  
    } 
	 /*// 配置中设定了
    // ① targetMethod: 指定需要定时执行scheduleInfoAction中的simpleJobTest()方法
    // ② concurrent：对于相同的JobDetail，当指定多个Trigger时, 很可能第一个job完成之前，
    // 第二个job就开始了。指定concurrent设为false，多个job不会并发运行，第二个job将不会在第一个job完成之前开始。
    // ③ cronExpression：0/10 * * * * ?表示每10秒执行一次，具体可参考附表。
    // ④ triggers：通过再添加其他的ref元素可在list中放置多个触发器。 scheduleInfoAction中的simpleJobTest()方法
    @Bean(name = "detailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean ();
        bean.setTargetObject (scheduledTasks);
        bean.setTargetMethod ("reportCurrentByCron");
        bean.setConcurrent (false);
        return bean;
    }

    @Bean(name = "cronTriggerBean")
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean){
        CronTriggerBean tigger = new CronTriggerBean ();
        tigger.setJobDetail (detailFactoryBean.getObject ());
        try {
            tigger.setCronExpression ("0/5 * * * * ? ");//每5秒执行一次
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return tigger;

    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(CronTriggerBean[] cronTriggerBean){
        SchedulerFactoryBean bean = new SchedulerFactoryBean ();
        System.err.println (cronTriggerBean[0]);
        bean.setTriggers (cronTriggerBean);
        return bean;
    }
    
    @Bean(name = "schedulerFactoryBean")  
    public SchedulerFactoryBean schedulerFactory(DruidDataSource dataSource) {  
        SchedulerFactoryBean factory = new SchedulerFactoryBean();  
        //TODO 程序启动后，延迟20s执行  
        factory.setStartupDelay(20);  
        factory.setDataSource(dataSource);  
        factory.setQuartzProperties(quartzConfigurationProperties);  
        factory.setTriggers(cronTriggerFactoryBean().getObject());  
      
        return factory;  
    }  
      
      
    @Bean(name = "scheduler")  
    @ConditionalOnBean(SchedulerFactoryBean.class)  
    public Scheduler schedulerBean(SchedulerFactoryBean schedulerFactoryBean) {  
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());  
        Scheduler scheduler = schedulerFactoryBean.getScheduler();  
        return scheduler;  
    }  */
}
