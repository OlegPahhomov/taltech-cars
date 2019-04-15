package ee.itcollege.taltechcars.controller;


import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @GetMapping("{id}")
    public Car findOne(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(exceptionSupplier());
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        validate(car);
        return carRepository.save(car);
    }

    @PutMapping("{id}")
    public Car update(@RequestBody Car car, @PathVariable Long id) {
        validate(car);
        Car dbCar = findOne(id);
        dbCar.setModelNr(car.getModelNr());
        dbCar.setRegistrationNr(car.getRegistrationNr());
        dbCar.setYear(car.getYear());
        return carRepository.save(dbCar);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Car dbCar = findOne(id);
        carRepository.delete(dbCar);
    }

    private void validate(@RequestBody Car car) {
        if (car.getModelNr() == null){
            throw new ResponseStatusException(BAD_REQUEST, "model nr is null");
        }
        if (car.getRegistrationNr() == null){
            throw new ResponseStatusException(BAD_REQUEST, "registration nr is null");
        }
        if (car.getYear() == null){
            throw new ResponseStatusException(BAD_REQUEST, "year is null");
        }
    }

    private Supplier<ResponseStatusException> exceptionSupplier() {
        return () -> new ResponseStatusException(BAD_REQUEST, "id doesnt exist");
    }
}
