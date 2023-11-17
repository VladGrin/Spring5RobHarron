package org.example.spring.dao;

import org.example.old.jdbc.entity.Singer;

import java.util.List;

public interface SingerDao {

    String findNameById(Long id);

    List<Singer> findAll();

    List<Singer> findAllWithAlbums();
}
