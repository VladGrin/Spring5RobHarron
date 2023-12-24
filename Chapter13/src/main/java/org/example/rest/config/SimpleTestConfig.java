package org.example.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.example.rest.init.DBInitializer;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages={"org.example.rest"},
        excludeFilters =  {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {DBInitializer.class})
        })
@Profile("test")
@Slf4j
public class SimpleTestConfig {

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            log.error("EmbeddedJdbcConfig DataSource bean can not be created.", e);
            return null;
        }
    }

}
