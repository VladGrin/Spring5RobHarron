package org.example.app.context.declare;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:./spring/app-context-xml.xml");
        ctx.load("classpath:./spring/app-context-annotation.xml");
        ctx.refresh();

        MessageProvider messageProvider = ctx.getBean("provider", MessageProvider.class);
        System.out.println(messageProvider.getMessage());

        MessageRenderer messageRenderer = ctx.getBean("renderer", MessageRenderer.class);
        messageRenderer.render();
        ctx.close();
    }
}
