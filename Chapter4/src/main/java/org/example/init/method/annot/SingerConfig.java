package org.example.init.method.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SingerConfig {

    @Lazy
    @Bean(initMethod = "init")
    SingerAnn singerOne() {
        SingerAnn singerOne = new SingerAnn();
        singerOne.setName("John Mayer");
        singerOne.setAge(39);
        return singerOne;
    }

    @Lazy
    @Bean(initMethod = "init")
    SingerAnn singerTwo() {
        SingerAnn singerTwo = new SingerAnn();
        singerTwo.setAge(72);
        return singerTwo;
    }

    @Lazy
    @Bean(initMethod = "init")
    SingerAnn singerThree() {
        SingerAnn singerThree = new SingerAnn();
        singerThree.setName("John Butler");
        return singerThree;
    }
}
