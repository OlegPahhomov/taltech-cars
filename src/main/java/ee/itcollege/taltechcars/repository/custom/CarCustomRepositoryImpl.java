package ee.itcollege.taltechcars.repository.custom;

import ee.itcollege.taltechcars.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CarCustomRepositoryImpl implements CarCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarByParams(String modelNr, Integer year, Boolean available) {
        //   List<Car> list = entityManager.createQuery("" +
        //           "SELECT c from Car c " +
        //      "WHERE lower(c.modelNr) like :modelNr " +
        //      "and c.year > :year", Car.class)
        //      .setParameter("modelNr", "%" + "audi".toLowerCase() + "%")
        //      .setParameter("year", year)
        //      .getResultList();


        List<Car> resultList = (List<Car>) entityManager.createNativeQuery("" +
                "SELECT *\n" +
                "FROM CAR \n" +
                "WHERE true \n" +
                "and lower(MODEL_NR) LIKE :modelNr \n" +
                "and year > :year", Car.class)
                .setParameter("modelNr", "%" + "audi".toLowerCase() + "%")
                .setParameter("year", year)
                .getResultList();
        return resultList;
    }
}
