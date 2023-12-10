package org.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DoSapTransformProcJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(DoSapTransformProcJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("--- START DoSapTransformProcJob JOB --- {}", Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("--- STOP DoSapTransformProcJob JOB --- {}", Thread.currentThread().getName());
    }
}
