package org.example.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.rest.entity.Singer;
import org.example.rest.entity.Singers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listdata";

    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";

    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";

    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";

    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Before
    public void setUp() {
        assertNotNull(restTemplate);
    }

    @Test
    public void testFindAll() {
        log.info("--> Testing retrieve all singers");
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertEquals(3, singers.getSingers().size());
        listSingers(singers);
    }

    @Test
    public void testFindById() {
        log.info("--> Testing retrieve а singer Ьу id: 1");
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
        assertNotNull(singer);
        log.info(singer.toString());
    }

    @Test
    public void testUpdate() {
        log.info("--> Testing update singer Ьу id: 1");
        Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
        singer.setFirstName("John Clayton");
        restTemplate.put(URL_UPDATE_SINGER, singer, 1);
        log.info("Singer update successfully: " + singer);
    }

    @Test
    public void testDelete() {
        log.info("--> Testing delete singer Ьу id 3");
        restTemplate.delete(URL_DELETE_SINGER, 3);
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        boolean found = false;
        for (Singer s : singers.getSingers()) {
            if (s.getId() == 3) {
                found = true;
            }
        }
        assertFalse(found);
        listSingers(singers);
    }

    @Test
    public void testCreate() {
        log.info("--> Testing create singer");
        Singer singerNew = new Singer();
        singerNew.setFirstName("ВВ");
        singerNew.setLastName("King");
        singerNew.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);
        log.info("Singer created successfully: " + singerNew);
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        listSingers(singers);
    }

    private void listSingers(Singers singers) {
        singers.getSingers().forEach(s -> log.info(s.toString()));
    }
}