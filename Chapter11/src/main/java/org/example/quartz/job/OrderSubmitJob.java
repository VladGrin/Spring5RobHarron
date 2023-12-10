package org.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderSubmitJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("--- START ORDER SUBMIT JOB --- {}", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("--- STOP ORDER SUBMIT JOB --- {}", Thread.currentThread().getName());
    }
}
