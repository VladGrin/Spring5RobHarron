package org.example.general.configuration.mixed.configuration;

import org.example.general.configuration.annotated.MessageProvider;
import org.example.general.configuration.annotated.MessageRenderer;
import org.example.general.configuration.annotated.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:./spring/app-context-xml-01.xml")
public class AppConfigFive {

    @Autowired
    public MessageProvider provider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {

        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}
