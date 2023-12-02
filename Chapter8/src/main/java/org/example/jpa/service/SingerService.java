package org.example.jpa.service;

import org.example.jpa.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);

    List<Singer> findAllByNativeQuery();

    List<Singer> findAllByNativeQueryWithSqlResultSetMapping();

    List<Singer> findByCriteriaQuery(String firstName, String lastName);
}
