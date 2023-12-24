package org.example.rest.complecs_testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.example.rest.config.DataConfig;
import org.example.rest.config.ServiceConfig;
import org.example.rest.config.ServiceTestConfig;
import org.example.rest.entity.Singer;
import org.example.rest.service.SingerService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class, ServiceConfig.class, DataConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
@Ignore
public class SingerServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    SingerService singerService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet= "E:\\TestProjects\\2023\\Spring5RobHarron\\Chapter13\\src\\main\\java\\org\\example\\rest\\SingerServiceImplTest.xlsx")
    @Test
    public void testFindAll() throws Exception {
        List<Singer> result = singerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @DataSets(setUpDataSet= "src/main/java/org/example/rest/SingerServiceImplTest.xls")
    @Test
    public void testFindByFirstNameAndLastNameOne() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("John", "Mayer").get(0);
        assertNotNull(result);
    }

    @DataSets(setUpDataSet= "src/main/java/org/example/rest/SingerServiceImplTest.xls")
    @Test
    public void testFindByFirstNameAndLastNameTwo() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("BB", "King").get(0);
        assertNull(result);
    }

    @Test
    public void testAddSinger() throws Exception {
        deleteFromTables("SINGER");

        Singer singer = new Singer();
        singer.setFirstName("Stevie");
        singer.setLastName("Vaughan ");

        singerService.save(singer);
        em.flush();

        List<Singer> singers = singerService.findAll();
        assertEquals(1, singers.size());
    }

    @Test(expected=AssertionError.class)
    public void testAddSingerWithJSR349Error() throws Exception {
        deleteFromTables("SINGER");

        Singer singer = new Singer();

        singerService.save(singer);
        em.flush();

        List<Singer> singers = singerService.findAll();
        assertEquals(0, singers.size());
    }
}
