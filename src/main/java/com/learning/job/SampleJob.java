package com.learning.job;

import com.learning.enums.JobDataKey;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

@Slf4j
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            JobDataMap jobDataMap = jobExecutionContext.getTrigger().getJobDataMap();
            int repeatCount = jobDataMap.getIntValue(JobDataKey.REPEAT_COUNT.name());
            if (repeatCount == 0) {
                return;
            }
            int timeInterval = jobDataMap.getInt(JobDataKey.TIME_INTERVAL.name());
            int executeTime = jobDataMap.getInt(JobDataKey.EXECUTE_TIME.name());
            log.info("Job execute: " + repeatCount + " delay: " + (1000L * timeInterval * executeTime));
            jobDataMap.replace(JobDataKey.REPEAT_COUNT.name(), --repeatCount);
            jobDataMap.replace(JobDataKey.EXECUTE_TIME.name(), ++executeTime);
            Trigger curTrigger = jobExecutionContext.getTrigger();
            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .forJob(curTrigger.getJobKey())
                    .startAt(new Date(new Date().getTime() + (1000L * timeInterval * executeTime)))
                    .usingJobData(jobDataMap)
                    .build();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.rescheduleJob(curTrigger.getKey(), newTrigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

}
