package org.example.general.configuration.two.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.general.configuration.annotated"})
public class AppConfigFour {
}
