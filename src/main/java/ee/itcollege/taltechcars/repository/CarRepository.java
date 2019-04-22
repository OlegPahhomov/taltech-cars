package ee.itcollege.taltechcars.repository;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.repository.custom.CarCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, CarCustomRepository {

    List<Car> findByModelNrContainingIgnoreCase(String modelNr);
}
