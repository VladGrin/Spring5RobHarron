package org.example.quartz.config;

import org.example.quartz.job.DoSapTransformProcAgainJob;
import org.example.quartz.job.DoSapTransformProcJob;
import org.example.quartz.job.MercuryOrdStatJob;
import org.example.quartz.job.OrderSubmitJob;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.*;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:./application.properties")
public class QuartzConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    private static final String ORDER_SUBMIT_JOB = "orderSubmitJob";
    private static final String DO_SAP_TRANSFORM_PROC_JOB = "doSapTransformProcJob";
    private static final String DO_SAP_TRANSFORM_PROC_AGAIN_JOB = "doSapTransformProcAgainJob";
    private static final String MERCURY_ORD_STAT_JOB = "mercuryOrdStatJob";

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${do.sap.transform.proc.cron}")
    private String doSapTransformProcCron;

    @Value("${mercury.ord.stat.cron}")
    private String mercuryOrdStatCron;

    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    public JobDetailFactoryBean orderSubmitJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(OrderSubmitJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(ORDER_SUBMIT_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public SimpleTriggerFactoryBean orderSubmitTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(orderSubmitJob().getObject()));
        trigger.setRepeatInterval(120000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setDescription("Every 2 min.");
        trigger.setName("orderSubmitTrigger");
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean doSapTransformProcJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DoSapTransformProcJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(DO_SAP_TRANSFORM_PROC_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean doSapTransformProcJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(doSapTransformProcJob().getObject()));
        trigger.setDescription("Every day at 4.00 AM");
        trigger.setName("doSapTransformProcJobTrigger");
        trigger.setCronExpression(doSapTransformProcCron);
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean doSapTransformProcAgainJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DoSapTransformProcAgainJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(DO_SAP_TRANSFORM_PROC_AGAIN_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean doSapTransformProcAgainJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(doSapTransformProcAgainJob().getObject()));
        trigger.setDescription("Only manually running");
        trigger.setName("doSapTransformProcAgainJobTrigger");
        trigger.setCronExpression("59 59 23 31 12 ? 2099");
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean mercuryOrdStatJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(MercuryOrdStatJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(MERCURY_ORD_STAT_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean mercuryOrdStatJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(mercuryOrdStatJob().getObject()));
        trigger.setDescription("Every 6 min, starting at 2 past the hour");
        trigger.setName("mercuryOrdStatJobTrigger");
        trigger.setCronExpression(mercuryOrdStatCron);
        trigger.afterPropertiesSet();
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean batchScheduler(/*DataSource dataSource,
                                               PlatformTransactionManager transactionManager*/) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", "5");
//        quartzProperties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
//        quartzProperties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
//        quartzProperties.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
//        quartzProperties.setProperty("org.quartz.jobStore.isClustered", "true");
//        quartzProperties.setProperty("org.quartz.jobStore.misfireThreshold", "5000");

        schedulerFactory.setQuartzProperties(quartzProperties);
        schedulerFactory.setJobFactory(springBeanJobFactory());

        try {
            schedulerFactory.setTriggers(
                    orderSubmitTrigger().getObject(),
                    doSapTransformProcJobTrigger().getObject(),
                    doSapTransformProcAgainJobTrigger().getObject(),
                    mercuryOrdStatJobTrigger().getObject());
        } catch (ParseException e) {
            logger.error("Failed to setup quartz triggers: {}", e.getMessage());
        }

//        schedulerFactory.setDataSource(dataSource);
//        schedulerFactory.setTransactionManager(transactionManager);
        return schedulerFactory;
    }
}
