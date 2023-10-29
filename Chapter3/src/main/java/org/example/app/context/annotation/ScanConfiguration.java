package org.example.app.context.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"org.example.app.context.declare"})
@Configuration
public class ScanConfiguration {

    @Bean
    public String message() {
        return "Configurable message for SCAN";
    }
}
