package org.example.service;

import org.example.spring.data.jpa.config.DataJpaConfig;
import org.example.spring.data.jpa.entity.SingerAudit;
import org.example.spring.data.jpa.service.SingerAuditService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

public class SpringAuditJPADemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    private GenericApplicationContext ctx;
    private SingerAuditService singerAuditService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerAuditService = ctx.getBean(SingerAuditService.class);
        assertNotNull(singerAuditService);
    }

    @Test
    public void testFindAll() {
        List<SingerAudit> singers = singerAuditService.findAll();
        listSingers(singers);
        System.out.println("Add пеw siпger");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("Kiпg");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);
        singer = singerAuditService.findById(1L);
        System.out.println("");
        System.out.println("Siпger with id 4: " + singer);
        System.out.println("");
        System.out.println("Update siпger");
        singer.setFirstName("Johп Claytoп");
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);
    }

    public static void listSingers(List<SingerAudit> singers) {
        System.out.println("");
        System.out.println("Listiпg  siпgers without details:");
        for (SingerAudit audit : singers) {
            System.out.println(audit);
        }
        System.out.println();
    }

    @After
    public void tearDown() {
        if (Objects.nonNull(ctx)) {
            ctx.close();
        }
    }
}