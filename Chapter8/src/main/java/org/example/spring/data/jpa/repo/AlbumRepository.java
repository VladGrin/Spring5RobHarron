package org.example.spring.data.jpa.repo;

import org.example.spring.data.jpa.entity.Album;
import org.example.spring.data.jpa.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findBySinger(Singer singer);

    @Query("select al from Album al where al.title like %:title%")
    List<Album> findByTitle(@Param("title") String t);
}
