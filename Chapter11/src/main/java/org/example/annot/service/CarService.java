package org.example.annot.service;

import org.example.annot.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
    boolean isDone();
}
