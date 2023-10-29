package org.example.app.context.annotation;

import org.example.app.context.declare.HelloWorldMessageProvider;
import org.example.app.context.declare.MessageProvider;
import org.example.app.context.declare.MessageRenderer;
import org.example.app.context.declare.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
