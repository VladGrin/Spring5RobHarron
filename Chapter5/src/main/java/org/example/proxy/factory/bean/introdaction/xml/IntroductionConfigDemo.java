package org.example.proxy.factory.bean.introdaction.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-xml1.xml");
        ctx.refresh();
        Contact bean = (Contact) ctx.getBean("bean");
        IsModified mod = (IsModified) bean;
        System.out.println("Is Contact?: " + (bean instanceof Contact));
        System.out.println("Is IsModified?: " + (bean instanceof IsModified));
        System.out.println("Has been modified?: " + mod.isModified());

        bean.setName("John Mayer");
        System.out.println("Has been modified?: " + mod.isModified());

        bean.setName("Eric Clapton");
        System.out.println("Has been modified?: " + mod.isModified());
    }
}
