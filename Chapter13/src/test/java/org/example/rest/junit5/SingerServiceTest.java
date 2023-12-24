package org.example.rest.junit5;

import org.example.rest.config.DataConfig;
import org.example.rest.config.ServiceConfig;
import org.example.rest.config.SimpleTestConfig;
import org.example.rest.entity.Singer;
import org.example.rest.service.SingerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import lombok.extern.slf4j.Slf4j;

@SpringJUnitConfig(classes = {SimpleTestConfig.class, ServiceConfig.class})
@DisplayName("Integration SingerService Test")
@ActiveProfiles("test")
@Slf4j
class SingerServiceTest {

    @Autowired
    SingerService singerService;

    @BeforeAll
    static void setUp() {
        log.info("--> @BeforeAll - executes before executing all test methods in this class");
    }

    @AfterAll
    static void tearDown(){
        log.info("--> @AfterAll - executes before executing all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("--> @BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void dispose() {
        log.info("--> @AfterEach - executes before each test method in this class");
    }

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    void findAll() {
        List<Singer> result = singerService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("should return singer 'John Mayer'")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    void testFindByFirstNameAndLastNameOne() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("John", "Mayer").get(0);
        assertNotNull(result);
    }
}