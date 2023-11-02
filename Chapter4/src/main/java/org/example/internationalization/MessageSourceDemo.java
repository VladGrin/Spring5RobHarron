package org.example.internationalization;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-xml1.xml");
        ctx.refresh();
        Locale english = Locale.ENGLISH;
        Locale german = new Locale("de", "DE");
        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, german));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, german));
        System.out.println(ctx.getMessage("btn", null, english));
        System.out.println(ctx.getMessage("btn", null, german));
        ctx.close();
    }
}
