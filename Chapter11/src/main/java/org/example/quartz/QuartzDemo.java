package org.example.quartz;

import org.example.quartz.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class QuartzDemo {

    private static final Logger logger = LoggerFactory.getLogger(QuartzDemo.class);

    public static void main(String[] args) throws IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        System.in.read();
        ctx.close();
    }
}
