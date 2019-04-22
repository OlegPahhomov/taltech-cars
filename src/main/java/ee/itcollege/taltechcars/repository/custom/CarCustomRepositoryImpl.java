package ee.itcollege.taltechcars.repository.custom;

import ee.itcollege.taltechcars.model.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CarCustomRepositoryImpl implements CarCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarByModelNrAndYearOlder(String modelNr, Integer year) {
        entityManager.createQuery("SELECT c from Car c", Car.class).getResultList();
        entityManager.createNativeQuery("SELECT * FROM CAR", Car.class).getResultList();
        return null;
    }
}
