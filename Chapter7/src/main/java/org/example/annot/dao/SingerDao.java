package org.example.annot.dao;

import org.example.annot.entity.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer contact);
    void delete(Singer contact);
}
