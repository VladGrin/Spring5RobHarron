package org.example.quartz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBaseConfig.class, QuartzConfig.class})
public class AppConfig {

}
