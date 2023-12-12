package org.example.quartz.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.quartz.job.DoSapTransformProcJob;
import org.example.quartz.job.OrderSubmitManualJob;
import org.example.quartz.job.OrderSubmitRepeatedJob;
import org.example.quartz.job.OrderSubmitStatCronJob;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.*;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:./application.properties")
public class QuartzConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    private static final String ORDER_SUBMIT_REPEATED_JOB = "orderSubmitRepeatedJob";
    private static final String ORDER_SUBMIT_PROC_CRON_JOB = "orderSubmitProcCronJob";
    private static final String ORDER_SUBMIT_STAT_CRON_JOB = "orderSubmitStatCronJob";
    private static final String ORDER_SUBMIT_MANUAL_JOB = "orderSubmitManualJob";

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${order.submit.proc.cron}")
    private String orderSubmitProcCron;

    @Value("${order.submit.stat.cron}")
    private String orderSubmitStatCron;

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${pg.username}")
    private String username;
    @Value("${password}")
    private String password;

    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    public JobDetailFactoryBean orderSubmitRepeatedJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(OrderSubmitRepeatedJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(ORDER_SUBMIT_REPEATED_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public SimpleTriggerFactoryBean orderSubmitRepeatedTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(orderSubmitRepeatedJob().getObject()));
        trigger.setRepeatInterval(120000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setDescription("Every 2 min.");
        trigger.setName("orderSubmitRepeatedTrigger");
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean orderSubmitProcCronJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DoSapTransformProcJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(ORDER_SUBMIT_PROC_CRON_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean orderSubmitProcCronJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(orderSubmitProcCronJob().getObject()));
        trigger.setDescription("Every day at 4.00 AM");
        trigger.setName("orderSubmitProcCronJobTrigger");
        trigger.setCronExpression(orderSubmitProcCron);
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean orderSubmitStatCronJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(OrderSubmitStatCronJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(ORDER_SUBMIT_STAT_CRON_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean orderSubmitStatCronJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(orderSubmitStatCronJob().getObject()));
        trigger.setDescription("Every 6 min, starting at 2 past the hour");
        trigger.setName("orderSubmitStatCronJobTrigger");
        trigger.setCronExpression(orderSubmitStatCron);
        trigger.afterPropertiesSet();
        return trigger;
    }

    public JobDetailFactoryBean orderSubmitManualJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(OrderSubmitManualJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(ORDER_SUBMIT_MANUAL_JOB);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    public CronTriggerFactoryBean orderSubmitManualJobTrigger() throws ParseException {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(orderSubmitManualJob().getObject()));
        trigger.setDescription("Only manually running");
        trigger.setName("orderSubmitManualJobTrigger");
        trigger.setCronExpression("59 59 23 31 12 ? 2099");
        trigger.afterPropertiesSet();
        return trigger;
    }

    @Bean(destroyMethod = "close")
    public DataSource schedulerDataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName(driverClassName);

            config.setMaximumPoolSize(5);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setPoolName("scheduler-hikari-pool");
            config.setAutoCommit(true);
            config.setConnectionTestQuery("SELECT 1");

            return new HikariDataSource(config);
        } catch (Exception e) {
            logger.error("DataSource bean cannot Ье created!", e);
            return null;
        }
    }

    @Bean(name = "schedulerTransactionManager")
    public DataSourceTransactionManager schedulerTransactionManager() {
        return new DataSourceTransactionManager(schedulerDataSource());
    }

    @Bean
    @DependsOn(value = "dataSourceInitializer")
    public SchedulerFactoryBean batchScheduler(DataSourceTransactionManager schedulerTransactionManager) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", "5");
        quartzProperties.setProperty("org.quartz.dataSource.quartzDataSource.URL", url);
        quartzProperties.setProperty("org.quartz.dataSource.quartzDataSource.driver", driverClassName);
        quartzProperties.setProperty("org.quartz.dataSource.quartzDataSource.user", username);
        quartzProperties.setProperty("org.quartz.dataSource.quartzDataSource.password", password);
        quartzProperties.setProperty("org.quartz.jobStore.dataSource", "quartzDataSource");
        quartzProperties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        quartzProperties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        quartzProperties.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
        quartzProperties.setProperty("org.quartz.jobStore.isClustered", "true");
        quartzProperties.setProperty("org.quartz.jobStore.misfireThreshold", "5000");

        schedulerFactory.setQuartzProperties(quartzProperties);
        schedulerFactory.setJobFactory(springBeanJobFactory());

        try {
            schedulerFactory.setTriggers(
                    orderSubmitRepeatedTrigger().getObject(),
                    orderSubmitProcCronJobTrigger().getObject(),
                    orderSubmitStatCronJobTrigger().getObject(),
                    orderSubmitManualJobTrigger().getObject());
        } catch (ParseException e) {
            logger.error("Failed to setup quartz triggers: {}", e.getMessage());
        }

        schedulerFactory.setTransactionManager(schedulerTransactionManager);
        return schedulerFactory;
    }


}
