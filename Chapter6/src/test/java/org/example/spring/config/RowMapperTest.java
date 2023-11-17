package org.example.spring.config;

import org.example.old.jdbc.entity.Album;
import org.example.old.jdbc.entity.Singer;
import org.example.spring.dao.SingerDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RowMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(RowMapperTest.class);

    @Test
    public void testCfg() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        singers.forEach(singer -> {
            logger.info(String.valueOf(singer));
            if (singer.getAlbums() != null)
                for (Album album : singer.getAlbums()) {
                    logger.info("---" + album);
                }
        });
        ctx.close();
    }
}