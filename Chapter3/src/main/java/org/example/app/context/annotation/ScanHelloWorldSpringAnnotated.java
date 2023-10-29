package org.example.app.context.annotation;

import org.example.app.context.declare.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScanHelloWorldSpringAnnotated {
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(ScanConfiguration.class);

        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
