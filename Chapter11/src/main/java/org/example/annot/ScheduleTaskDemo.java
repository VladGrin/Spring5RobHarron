package org.example.annot;

import org.example.annot.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class ScheduleTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTaskDemo.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        CarService carService = ctx.getBean("carService", CarService.class);
//        while (!carService.isDone()) {
//            logger.info("Waiting for scheduled job to end ... ");
//            Thread.sleep(250);
//            ctx.close();
//        }
        System.in.read();
        ctx.close();
    }
}
