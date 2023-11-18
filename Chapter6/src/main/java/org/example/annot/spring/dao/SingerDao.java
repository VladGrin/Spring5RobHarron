package org.example.annot.spring.dao;

import org.example.old.jdbc.entity.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findNameById(Long id);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    List<Singer> findAllWithAlbums();
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Long singerld);
    void insertWithAlbum(Singer singer);
}
