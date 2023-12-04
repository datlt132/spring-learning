package com.learning.resource;

import com.learning.base.dto.ServerResponse;
import com.learning.job.JobService;
import com.learning.job.SampleJob;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PutMapping
    public ResponseEntity<ServerResponse> createNewJob() throws SchedulerException {
        JobDetail jobDetail = jobService.createJobDetail(SampleJob.class, "1", "1");
        Trigger trigger = jobService.createTrigger(jobDetail, "1", "1");

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return ResponseEntity.ok(ServerResponse.SUCCESS);
    }

}
