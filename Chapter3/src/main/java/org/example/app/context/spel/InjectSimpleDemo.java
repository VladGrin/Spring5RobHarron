package org.example.app.context.spel;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimpleDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();
        InjectSimpleSpel simple = (InjectSimpleSpel) ctx.getBean("injectSimpleSpel");
        System.out.println(simple);
        ctx.close();
    }
}
