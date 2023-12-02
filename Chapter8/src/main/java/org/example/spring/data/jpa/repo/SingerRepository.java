package org.example.spring.data.jpa.repo;

import org.example.spring.data.jpa.entity.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {

    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);

}
