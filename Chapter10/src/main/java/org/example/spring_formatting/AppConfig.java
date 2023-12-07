package org.example.spring_formatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "org.example.spring_formatting")
public class AppConfig {

    @Autowired
    ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public Singer john() throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setPersonalSite(new URL("http://johnmayer.com/"));
        singer.setBirthDate(conversionService.getDateTimeFormatter().parse("1977-10-16", Locale.ENGLISH));
        return singer;
    }
}
