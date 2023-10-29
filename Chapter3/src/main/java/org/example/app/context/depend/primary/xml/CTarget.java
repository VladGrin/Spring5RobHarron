package org.example.app.context.depend.primary.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CTarget {

    private Foo foo;

    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-04.xml");
        ctx.refresh();
        System.out.println("\nUsing byType:\n");
        CTarget t = (CTarget) ctx.getBean("targetByType");
        ctx.close();
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
    }
}
