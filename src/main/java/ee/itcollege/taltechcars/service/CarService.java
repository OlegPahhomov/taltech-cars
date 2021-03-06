package ee.itcollege.taltechcars.service;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.repository.CarRepository;
import ee.itcollege.taltechcars.repository.LeaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private CarValidator carValidator;

    public List<Car> findAll(String modelNr, Integer yearOlder, Boolean available) {
        if (StringUtils.isNotBlank(modelNr) || yearOlder != null || available != null) {
            return carRepository.findCarByParams(modelNr, yearOlder, available);
        }
        return carRepository.findAll();
    }

    public Car findOne(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(this::badRequest);
        //List<Lease> leases = leaseRepository.findByCar(car);
        //car.setLeases(leases);
        return car;
    }

    public Car save(Car car) {
        carValidator.validate(car);
        car.setCreatedAt(LocalDateTime.now());
        return carRepository.save(car);
    }

    public Car update(Car car, Long id) {
        carValidator.validate(car);
        Car dbCar = findOne(id);
        dbCar.setModelNr(car.getModelNr());
        dbCar.setRegistrationNr(car.getRegistrationNr());
        dbCar.setYear(car.getYear());
        dbCar.setUpdatedAt(LocalDateTime.now());
        return carRepository.save(dbCar);
    }

    public void delete(Long id) {
        Car dbCar = findOne(id);
        carRepository.delete(dbCar);
    }

    private ResponseStatusException badRequest() {
        return new ResponseStatusException(BAD_REQUEST, "id doesnt exist");
    }
}
