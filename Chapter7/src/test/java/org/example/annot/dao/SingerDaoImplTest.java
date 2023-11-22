package org.example.annot.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.annot.conf.AppConfig;
import org.example.annot.entity.Album;
import org.example.annot.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.example.annot.SpringHibernateDemo.listSingers;
import static org.example.annot.SpringHibernateDemo.listSingersWithAlbum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerDaoImplTest {

    private static final Log logger = LogFactory.getLog(SingerDaoImplTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);
        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());
        Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerDao.save(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerDao.findById(2L);
        assertNotNull(singer);
        singerDao.delete(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }


    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByID() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @After
    public void tearDown() {
        ctx.close();
    }
}