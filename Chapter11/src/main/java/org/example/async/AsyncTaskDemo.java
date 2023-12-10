package org.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);
        for (int i = 0; i < 5; i++) {
            asyncService.asyncTask();
        }
        Future<String> result1 = asyncService.asyncWithReturn("John Mayer");
        Future<String> result2 = asyncService.asyncWithReturn("Eric Clapton");
        Future<String> resultЗ = asyncService.asyncWithReturn("BB King");
        Thread.sleep(6000);
        logger.info("Resultl: {}", result1.get());
        logger.info(" Result2: {}", result2.get());
        logger.info("ResultЗ: {}", resultЗ.get());
        System.in.read();
        ctx.close();
    }
}
