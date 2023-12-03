package org.example.spring.data.jpa.service;

import com.google.common.collect.Lists;
import org.example.spring.data.jpa.entity.SingerAudit;
import org.example.spring.data.jpa.repo.SingerAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public SingerAudit save(SingerAudit singer) {
        return singerAuditRepository.save(singer);
    }
}
