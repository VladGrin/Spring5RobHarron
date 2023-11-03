package org.example.general.configuration.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = ("org.example.general.configuration.annotated"))
public class AppConfigTwo {

    @Autowired
    private MessageProvider provider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {

        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}
