package org.example.app.context.depend.primary.annot;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DependsOnDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-02.xml");
        ctx.refresh();
        Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
        johnMayer.sing();
        ctx.close();
    }
}
