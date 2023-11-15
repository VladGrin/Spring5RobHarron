package org.example.spring.config;

import org.example.old.jdbc.entity.Singer;
import org.example.spring.dao.JdbcSingerDao;
import org.example.spring.dao.SingerDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class EmbeddedJdbcConfigTest {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfigTest.class);

    @Test
    public void testOne() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/embedded-h2-cfg.xml");
        ctx.refresh();
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testTwo() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
        ctx.close();
    }

    @Test
    public void testTemplate() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        SingerDao singerDao = ctx.getBean("singerDao", JdbcSingerDao.class);
        assertNotNull(singerDao);
        String name = singerDao.findNameById(2L);
        System.out.println(name);
        assertEquals("Eric Clapton", name);
        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from singer where ID = 1");
            ResultSet resultSet = statement.executeQuery();
            Singer singer = new Singer();
            while (resultSet.next()) {
                singer.setId(resultSet.getLong("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
            }
            assertEquals(1L, (long) singer.getId());
            assertEquals("John", singer.getFirstName());
            assertEquals("Mayer", singer.getLastName());
            System.out.println(singer);
            statement.close();
        } catch (Exception e) {
            logger.debug("Something unexpected happened.", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}