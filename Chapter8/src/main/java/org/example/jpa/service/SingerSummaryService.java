package org.example.jpa.service;

import org.example.jpa.entity.SingerSummary;

import java.util.List;

public interface SingerSummaryService {

    List<SingerSummary> findAll();
}
