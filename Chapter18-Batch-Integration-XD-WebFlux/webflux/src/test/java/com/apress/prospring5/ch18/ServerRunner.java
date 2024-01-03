package com.apress.prospring5.ch18;

import com.apress.prospring5.ch18.web.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class ServerRunner {

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
		Server server = ctx.getBean(Server.class);
		server.startTomcatServer();
		log.info("Server is running...");

		System.in.read();
		ctx.close();
	}
}
