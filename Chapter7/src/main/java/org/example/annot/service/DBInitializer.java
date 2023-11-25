package org.example.annot.service;

import org.example.annot.dao.InstrumentDao;
import org.example.annot.dao.SingerDao;
import org.example.annot.entity.Album;
import org.example.annot.entity.Instrument;
import org.example.annot.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

//@Service
public class DBInitializer {

    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerDao singerDao;
    @Autowired
    private InstrumentDao instrumentDao;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization... ");
        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
//        singer.addInstrument(guitar);
//        singer.addInstrument(piano);
        Album albuml = new Album();
        albuml.setTitle("The Search For Everything");
        albuml.setReleaseDate(new java.sql.Date((new GregorianCalendar(2017, 0, 20)).getTime().getTime()));
        singer.addAlbum(albuml);
        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new java.sql.Date((new GregorianCalendar(2009, 10, 17)).getTime().getTime()));
        singer.addAlbum(album2);
        singerDao.save(singer);
        logger.info("Database initialization finished.");
    }
}
