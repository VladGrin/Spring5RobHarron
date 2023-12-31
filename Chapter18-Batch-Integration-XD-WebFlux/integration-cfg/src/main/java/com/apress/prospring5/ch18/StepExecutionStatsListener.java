package com.apress.prospring5.ch18;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepExecutionStatsListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("--> Wrote: " + stepExecution.getWriteCount() +
                "items in step: " + stepExecution.getStepName());
        return null;
    }
}
