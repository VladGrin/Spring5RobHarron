package org.example.service;

import org.example.entity.SingerSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<SingerSummary> findAll() {
        return em.createQuery("select new org.example.entity.SingerSummary(s.firstName, s.lastName, a.title) from Singer s left join s.albums a " +
                "where a.releaseDate = (select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)", SingerSummary.class).getResultList();
    }
}
