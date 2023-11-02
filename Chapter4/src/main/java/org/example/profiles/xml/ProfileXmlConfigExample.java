package org.example.profiles.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.getEnvironment().setActiveProfiles("kindergarten");
        ctx.load("classpath:./spring/*-config.xml");
        ctx.refresh();
        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();
        for (Food food : lunchSet) {
            System.out.println("Food: " + food.getName());
        }
        ctx.close();
    }
}
