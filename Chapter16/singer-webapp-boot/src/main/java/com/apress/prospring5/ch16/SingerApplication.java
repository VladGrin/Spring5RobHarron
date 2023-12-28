package com.apress.prospring5.ch16;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SingerApplication {

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(SingerApplication.class, args);
		assert (ctx != null);
		log.info("Application started...");

		System.in.read();
		ctx.close();
	}
}
