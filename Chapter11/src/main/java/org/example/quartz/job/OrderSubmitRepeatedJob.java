package org.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitRepeatedJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderSubmitRepeatedJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("--- START ORDER SUBMIT REPEATED JOB --- {}", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("--- STOP ORDER SUBMIT REPEATED JOB --- {}", Thread.currentThread().getName());
    }
}
