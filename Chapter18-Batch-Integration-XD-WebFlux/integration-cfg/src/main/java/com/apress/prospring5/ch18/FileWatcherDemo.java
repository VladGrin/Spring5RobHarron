package com.apress.prospring5.ch18;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericXmlApplicationContext;

@Slf4j
public class FileWatcherDemo {

    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/integration-config.xml");
//        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(IntegrationConfig.class);
        assert (ctx != null);
        log.info("Application started ...");
        System.in.read();
        ctx.close();
    }
}

