package org.example.spring.data.jpa.repo;

import org.example.spring.data.jpa.entity.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
