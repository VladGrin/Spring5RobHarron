package org.example.profiles.annot;

import org.example.profiles.xml.FoodProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KindergartenConfig.class, HighschoolConfig.class})
@ActiveProfiles("kindergarten")
public class ProfilesJavaConfigTest {

    @Autowired
    FoodProviderService foodProviderService;

    @Test
    public void testProvider() {
        assertNotNull(foodProviderService.provideLunchSet());
        assertFalse(foodProviderService.provideLunchSet().isEmpty());
        assertEquals(2, foodProviderService.provideLunchSet().size());
    }
}