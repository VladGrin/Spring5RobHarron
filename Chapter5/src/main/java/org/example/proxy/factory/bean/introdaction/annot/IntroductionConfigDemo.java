package org.example.proxy.factory.bean.introdaction.annot;

import org.example.proxy.factory.bean.introdaction.xml.Contact;
import org.example.proxy.factory.bean.introdaction.xml.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

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
