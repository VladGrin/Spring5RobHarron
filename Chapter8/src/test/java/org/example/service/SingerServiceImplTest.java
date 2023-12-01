package org.example.service;

import org.example.config.JpaConfig;
import org.example.entity.Album;
import org.example.entity.Instrument;
import org.example.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void findAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void findAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void findById() {
        Singer singer = singerService.findById(1L);
        assertEquals("John", singer.getFirstName());
        listSingersWithAlbum(List.of(singer));
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAllByNativeQuery() {
    }

    public static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for
                (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }
        }
    }

    public static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @After
    public void tearDown() {
        if (Objects.nonNull(ctx)) {
            ctx.close();
        }
    }
}