package ee.itcollege.taltechcars.repository.custom;

import ee.itcollege.taltechcars.model.Car;

import java.util.List;

public interface CarCustomRepository {

    List<Car> findCarByModelNrAndYearOlder(String modelNr, Integer year);
}
