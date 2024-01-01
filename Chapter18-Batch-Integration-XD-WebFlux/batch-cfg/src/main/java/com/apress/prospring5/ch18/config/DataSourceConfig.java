package com.apress.prospring5.ch18.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts(
                            "classpath:/org/springframework/batch/core/schema-h2.sql",
                            "classpath:support/singer.sql").build();
        } catch (Exception e) {
            log.error("EmbeddedJdbcConfig DataSource bean can not be created.", e);
            return null;
        }
    }
}
