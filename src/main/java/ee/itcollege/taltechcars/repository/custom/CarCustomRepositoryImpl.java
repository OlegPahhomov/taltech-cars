package ee.itcollege.taltechcars.repository.custom;

import ee.itcollege.taltechcars.model.Car;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

public class CarCustomRepositoryImpl implements CarCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarByParams(String modelNr, Integer yearOlder, Boolean available) {
        //   List<Car> list = entityManager.createQuery("" +
        //           "SELECT c from Car c " +
        //      "WHERE lower(c.modelNr) like :modelNr " +
        //      "and c.year > :year", Car.class)
        //      .setParameter("modelNr", "%" + "audi".toLowerCase() + "%")
        //      .setParameter("year", year)
        //      .getResultList();

        Query query = entityManager.createNativeQuery("" +
                "SELECT *\n" +
                "FROM CAR \n" +
                "WHERE true \n" +
                (isNotBlank(modelNr) ? "and lower(MODEL_NR) LIKE :modelNr \n" : "") +
                "and year > :year", Car.class);

        if (isNotBlank(modelNr)) {
            //add a parameter to query
            query = query.setParameter("modelNr", "%" + "audi".toLowerCase() + "%");
        }
        List<Car> resultList = (List<Car>) query
                .setParameter("year", yearOlder)
                .getResultList();
        return resultList;
    }
}
