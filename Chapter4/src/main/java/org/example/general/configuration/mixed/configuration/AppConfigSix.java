package org.example.general.configuration.mixed.configuration;

import org.example.general.configuration.annotated.ConfigurableMessageProvider;
import org.example.general.configuration.annotated.MessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSix {

    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider("Love on the weekend");
    }
}
