package org.example.service;

import org.example.jpa.config.JpaConfig;
import org.example.jpa.entity.Album;
import org.example.jpa.entity.Singer;
import org.example.jpa.service.SingerService;
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

import static org.example.service.SingerServiceImplTest.listSingers;
import static org.example.service.SingerServiceImplTest.listSingersWithAlbum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerJPATest {
    private static final Logger logger = LoggerFactory.getLogger(SingerJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);
        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);
        singerService.save(singer);
        assertNotNull(singer.getId());
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());

        Album album = singer.getAlbums().stream().filter(a ->
                        a.getTitle().equals("Battle Studies"))
                .findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testFindAllByNativeQuery() {
        List<Singer> singers = singerService.findAllByNativeQuery();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void findAllByNativeQueryWithSqlResultSetMapping() {
        List<Singer> singers = singerService.findAllByNativeQueryWithSqlResultSetMapping();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void tesFindByCriteriaQuery() {
        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        assertEquals(1, singers.size());
        listSingersWithAlbum(singers);
    }

    @After
    public void tearDown() {
        if (Objects.nonNull(ctx)) {
            ctx.close();
        }
    }
}