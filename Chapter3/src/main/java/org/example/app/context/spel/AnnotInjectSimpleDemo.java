package org.example.app.context.spel;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotInjectSimpleDemo {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-annotation.xml");
        ctx.refresh();
        AnnotInjectSimpleSpel simple = (AnnotInjectSimpleSpel) ctx.getBean("annotInjectSimpleSpel");
        System.out.println(simple);
        ctx.close();
    }
}
