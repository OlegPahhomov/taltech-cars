package ee.itcollege.taltechcars.repository;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.Lease;
import ee.itcollege.taltechcars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {

    List<Lease> findByCar(Car car);

    List<Lease> findByUserAndReturnDateNull(User user);
}
