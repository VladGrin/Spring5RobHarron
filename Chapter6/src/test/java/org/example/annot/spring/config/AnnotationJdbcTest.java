package org.example.annot.spring.config;

import org.example.annot.spring.dao.SingerDao;
import org.example.old.jdbc.entity.Album;
import org.example.old.jdbc.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AnnotationJdbcTest {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationJdbcTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() throws SQLException {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerDao.findByFirstName("John");
        assertEquals(2, singers.size());
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testSingerInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date((new GregorianCalendar(1991, Calendar.JANUARY, 17)).getTime().getTime()));
        singerDao.insert(singer);
        List<Singer> singers = singerDao.findAll();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void testSingerInsertWithAlbum() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, Calendar.AUGUST, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);
        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.insertWithAlbum(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void testFindFirstNameById() {
        String firstName = singerDao.findFirstNameById(2L);
        assertEquals("Eric", firstName);
        System.out.println("Retrieved value: " + firstName);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null)
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
        });
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}