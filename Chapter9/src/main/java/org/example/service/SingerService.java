package org.example.service;

import org.example.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    long countAll();
}
