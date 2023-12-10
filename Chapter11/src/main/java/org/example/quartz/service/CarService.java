package org.example.quartz.service;

import org.example.quartz.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
    boolean isDone();
}
