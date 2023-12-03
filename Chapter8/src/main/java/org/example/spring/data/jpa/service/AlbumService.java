package org.example.spring.data.jpa.service;

import org.example.spring.data.jpa.entity.Album;
import org.example.spring.data.jpa.entity.Singer;

import java.util.List;

public interface AlbumService {

    List<Album> findBySinger(Singer singer);

    List<Album> findByTitle(String title);
}
