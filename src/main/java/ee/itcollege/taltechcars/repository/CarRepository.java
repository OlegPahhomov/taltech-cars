package ee.itcollege.taltechcars.repository;

import ee.itcollege.taltechcars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
