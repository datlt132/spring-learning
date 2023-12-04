package com.learning.job;

import com.learning.enums.JobDataKey;
import org.quartz.*;
import org.springframework.stereotype.Service;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Service
public class JobService {

    public JobDetail createJobDetail(Class<? extends Job> jobClass, String identity, String description) {
        return JobBuilder.newJob().ofType(jobClass)
                .storeDurably()
                .withIdentity(identity)
                .withDescription(description)
                .build();
    }

    public Trigger createTrigger(JobDetail job, String identity, String description) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobDataKey.REPEAT_COUNT.name(), 3);
        jobDataMap.put(JobDataKey.TIME_INTERVAL.name(), 5);
        jobDataMap.put(JobDataKey.EXECUTE_TIME.name(), 0);
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity(identity)
                .withDescription(description)
                .withSchedule(simpleSchedule().withRepeatCount(1)
                        .withIntervalInSeconds(jobDataMap.getInt(JobDataKey.TIME_INTERVAL.name())))
                .usingJobData(jobDataMap)
                .build();
    }

}
