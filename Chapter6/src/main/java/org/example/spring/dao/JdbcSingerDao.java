package org.example.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);

//    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource);
//        PostgresErrorCodesTranslator errorTranslator = new PostgresErrorCodesTranslator();
//        errorTranslator.setDataSource(dataSource);
//        jdbcTemplate.setExceptionTranslator(errorTranslator);
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        if (dataSource == null) {
//            throw new BeanCreationException("Must set dataSource on SingerDao");
//        }
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select first_name || ' ' || last_name from singer where id = ?",
                String.class, id);
    }
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

