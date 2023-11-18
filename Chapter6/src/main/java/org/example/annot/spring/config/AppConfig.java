package org.example.annot.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db/jdbc2.properties")
@ComponentScan(basePackages = "org.example..annot.spring")
public class AppConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${pg.username}")
    private String username;
    @Value("${password}")
    private String password;
}
