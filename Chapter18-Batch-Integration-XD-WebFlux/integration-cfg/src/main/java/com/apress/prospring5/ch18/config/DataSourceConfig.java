package com.apress.prospring5.ch18.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
					.addScripts("classpath:/org/springframework/batch/core/schema-h2.sql",
							"classpath:support/singer.sql").build();
		} catch (Exception e) {
			log.error("임베디드 데이터베이스의 DataSource 빈을 생성할 수 없습니다!", e);
			return null;
		}
	}
}
