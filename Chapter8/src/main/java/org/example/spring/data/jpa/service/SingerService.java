package org.example.spring.data.jpa.service;

import org.example.spring.data.jpa.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findByFirstNarne(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
