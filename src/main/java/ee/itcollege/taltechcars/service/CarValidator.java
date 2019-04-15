package ee.itcollege.taltechcars.service;

import ee.itcollege.taltechcars.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CarValidator {

    public void validate(@RequestBody Car car) {
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
}
