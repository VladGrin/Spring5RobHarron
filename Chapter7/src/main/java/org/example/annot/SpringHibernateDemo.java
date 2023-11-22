package org.example.annot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.annot.conf.AppConfig;
import org.example.annot.dao.SingerDao;
import org.example.annot.entity.Album;
import org.example.annot.entity.Instrument;
import org.example.annot.entity.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {

    private static final Log logger = LogFactory.getLog(SpringHibernateDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
        Singer singer = singerDao.findById(2L);
        logger.info("SINGER: " + singer);
        singerDao.delete(singer);
        List<Singer> allWithAlbum = singerDao.findAllWithAlbum();
        listSingersWithAlbum(allWithAlbum);
        ctx.close();
    }

    public static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for
                (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }
        }
    }

    public static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }
}
