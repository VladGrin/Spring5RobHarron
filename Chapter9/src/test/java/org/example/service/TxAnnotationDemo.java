package org.example.service;

import org.example.config.DataJpaConfig;
import org.example.config.ServicesConfig;
import org.example.entity.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TxAnnotationDemo {

    @Test
    public void findAll() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);
        assertNotNull(singers);

        System.out.println("Singer count: " + singerService.countAll());

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setVersion(1L);
//        Singer saveSinger = singerService.save(singer);
//        System.out.println("Singer saved successfully: " + saveSinger);

        ctx.close();
    }
}