/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.kfayun.app.witkey.service.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 计划任务配置
 * 
 * @author billy (billy_zh@126.com)
 */
//@Configuration
//@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
 
    private static Logger log = LoggerFactory.getLogger(SchedulerConfig.class);
    
    private ScheduledTaskRegistrar taskRegistrar;

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private TaskService taskService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        taskRegistrar.setScheduler(taskExecutor());
        
        Map<Runnable, Trigger> taskMap = new HashMap<>();
        taskMap.put(new UpdateTaskTopTicksJob(), 
            (triggerContext) -> new CronTrigger(appConfig.getTaskTopTicksUpdateCron()).nextExecutionTime(triggerContext));
        taskRegistrar.setTriggerTasks(taskMap);
        log.info("scheduler config ok.");
    }
 
    public class UpdateTaskTopTicksJob implements Runnable {

        @Override
        public void run() {
            log.debug("Schedule updateTaskTopTicksJob running.");
            taskService.updateTaskTopTicks();
        }

    }

    // 使用线程池执行定时任务
    private Executor taskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }
 
    // 提供方法修改cron表达式，并重新配置定时任务
    // public void setCron(String cron) {
    //     taskRegistrar.destroy();
    //     this.cron = cron;
    //     configure();
    // }

}
