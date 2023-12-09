package org.example.xml.service;

import com.google.common.collect.Lists;
import org.example.xml.entity.Car;
import org.example.xml.repo.CarRepository;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {

    private boolean done;
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    @Transactional
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public void updateCarAgeJob() {
        List<Car> cars = Lists.newArrayList(carRepository.findAll());
        DateTime currentDate = DateTime.now();
        logger.info("Car age update job started");
        cars.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();
            car.setAge(age);
            save(car);
            logger.info("Car age update -->" + car);
        });
        logger.info("Car age update job completed successfully");
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
