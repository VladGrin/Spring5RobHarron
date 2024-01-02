package com.apress.prospring5.ch18;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {

    @Override
    public Singer process(Singer singer) throws Exception {
        String firstName = singer.getFirstName().toUpperCase();
        String lastName = singer.getLastName().toUpperCase();
        String song = singer.getSong().toUpperCase();

        Singer transformedSinger = new Singer();
        transformedSinger.setFirstName(firstName);
        transformedSinger.setLastName(lastName);
        transformedSinger.setSong(song);

        log.info("Transformed singer: " + singer + ", Into: " + transformedSinger);

        return transformedSinger;
    }
}
