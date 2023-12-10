package org.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmitManualJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(OrderSubmitManualJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("--- START DoSapTransformProcAgainJob JOB --- {}", Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("--- STOP DoSapTransformProcAgainJob JOB --- {}", Thread.currentThread().getName());
    }
}
