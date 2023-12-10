package org.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitStatCronJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderSubmitStatCronJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("--- START MercuryOrdStat JOB --- {}", Thread.currentThread().getName());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("--- STOP MercuryOrdStat JOB --- {}", Thread.currentThread().getName());
    }
}
