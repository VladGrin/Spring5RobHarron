package org.example.spring.config;

import org.example.spring.dao.SingerDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NamedJdbcCfgTest {
    private static final Logger logger = LoggerFactory.getLogger(NamedJdbcCfgTest.class);

    @Test
    public void testCfg() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        logger.info(singerName);
        assertEquals("John Mayer", singerName);
        ctx.close();
    }
}