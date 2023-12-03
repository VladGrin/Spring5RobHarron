package org.example.spring.data.jpa.service;

import org.example.spring.data.jpa.entity.SingerAudit;

import java.util.List;

public interface SingerAuditService {

    List<SingerAudit> findAll();
    SingerAudit findById(Long id);
    SingerAudit save(SingerAudit singer);
}
