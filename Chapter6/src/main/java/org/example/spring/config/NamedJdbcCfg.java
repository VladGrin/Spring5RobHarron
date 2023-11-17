package org.example.spring.config;

import org.example.spring.dao.NamedJdbcSingerDao;
import org.example.spring.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class NamedJdbcCfg {

    private static final Logger logger = LoggerFactory.getLogger(NamedJdbcCfg.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:./db/h2/schema.sql", "classpath:./db/h2/test-data.sql")
                    .build();
        } catch (Exception e) {
            logger.error("EmbeddedJdbcConfig DataSource bean can not be created.", e);
        }
        return null;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SingerDao namedJdbcSingerDao() {
        NamedJdbcSingerDao dao = new NamedJdbcSingerDao();
        dao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return dao;
    }
}
