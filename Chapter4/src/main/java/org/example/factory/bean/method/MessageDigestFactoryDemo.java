package org.example.factory.bean.method;

import org.example.factory.MessageDigester;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactoryDemo {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-xml1.xml");
        ctx.refresh();
        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");
        ctx.close();

    }
}
