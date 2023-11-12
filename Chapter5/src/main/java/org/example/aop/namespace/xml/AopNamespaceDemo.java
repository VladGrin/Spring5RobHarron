package org.example.aop.namespace.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:./spring/app-context-aop-xml-01.xml");
//        ctx.load("classpath:spring/app-context-aop-xml-02.xml");
        ctx.load("classpath:spring/app-context-aop-xml-03.xml");
        ctx.refresh();
        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
