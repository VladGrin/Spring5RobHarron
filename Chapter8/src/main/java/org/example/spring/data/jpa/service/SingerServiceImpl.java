package org.example.spring.data.jpa.service;

import org.example.spring.data.jpa.entity.Singer;
import org.example.spring.data.jpa.repo.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("springJpaSingerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstNarne(String firstName) {
        return null;
    }

    @Override
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }
}
