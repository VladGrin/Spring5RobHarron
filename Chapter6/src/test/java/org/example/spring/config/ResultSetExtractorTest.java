package org.example.spring.config;

import org.example.old.jdbc.entity.Album;
import org.example.old.jdbc.entity.Singer;
import org.example.spring.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResultSetExtractorTest {

    @Test
    public void testCfg() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAllWithAlbums();
        assertEquals(3, singers.size());
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null)
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
        });
        ctx.close();
    }
}
