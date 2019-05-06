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
        Query query = entityManager.createNativeQuery("" +
                "SELECT *\n" +
                "FROM CAR \n" +
                "WHERE true \n" +
                (isNotBlank(modelNr) ? "and lower(MODEL_NR) LIKE :modelNr \n" : "") +
                (yearOlder != null ? "and year > :year \n" : "") +
                (available != null ? "and leased = :leased \n" : "")
                , Car.class);

        if (isNotBlank(modelNr)) {
            query = query.setParameter("modelNr", "%" + modelNr.toLowerCase() + "%");
        }
        if (yearOlder != null) {
            query = query.setParameter("year", yearOlder);
        }
        if (available != null) {
            query = query.setParameter("leased", !available);
        }
        return (List<Car>) query.getResultList();
    }
}
