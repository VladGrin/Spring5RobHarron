package com.apress.prospring5.ch18;

import com.apress.prospring5.ch18.web.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.apress.prospring5.ch18"})
@Configuration
public class ServerConfig {

	@Bean
	public Server server() {
		return new Server();
	}
}
