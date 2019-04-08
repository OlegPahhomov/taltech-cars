package ee.itcollege.taltechcars.controller;


import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
