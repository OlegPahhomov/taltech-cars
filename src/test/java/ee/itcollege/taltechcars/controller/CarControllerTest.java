package ee.itcollege.taltechcars.controller;

import ee.itcollege.taltechcars.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    public static final ParameterizedTypeReference<List<Car>> LIST_OF_CARS = new ParameterizedTypeReference<List<Car>>() {
    };
    public static final String VW_GOLF = "111111111";
    public static final String AUDI_2008 = "222222222";
    public static final String AUDI_2018 = "555555555";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void application_returns_a_list_of_cars() {
        ResponseEntity<List<Car>> exchange = restTemplate.exchange("/car", HttpMethod.GET, null, LIST_OF_CARS);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<Car> cars = exchange.getBody();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    public void user_can_search_for_cars() {
        ResponseEntity<List<Car>> exchange =
                restTemplate.exchange("/car/?modelNr=audi",
                HttpMethod.GET, null, LIST_OF_CARS);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<Car> cars = exchange.getBody();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
        assertTrue(findCar(cars, AUDI_2008).isPresent());
        assertFalse(findCar(cars, VW_GOLF).isPresent());
    }

    @Test
    public void user_can_search_for_cars_by_model_nr_and_yearOlder() {
        ResponseEntity<List<Car>> exchange =
                restTemplate.exchange("/car/?modelNr=audi&yearOlder=2010",
                        HttpMethod.GET, null, LIST_OF_CARS);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<Car> cars = exchange.getBody();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
        assertTrue(findCar(cars, AUDI_2018).isPresent());
        assertFalse(findCar(cars, AUDI_2008).isPresent());
        assertFalse(findCar(cars, VW_GOLF).isPresent());
    }

    private Optional<Car> findCar(List<Car> cars, String regNum) {
        return cars.stream()
                .filter(c -> c.getRegistrationNr().equals(regNum))
                .findAny();
    }

    @Test
    public void you_can_find_one_car_if_id_exists() {
        ResponseEntity<Car> entity = restTemplate.getForEntity("/car/1", Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car car = entity.getBody();
        assertNotNull(car);
        assertEquals(VW_GOLF, car.getRegistrationNr());
        assertEquals("VW Golf", car.getModelNr());
        assertEquals(1999, (int) car.getYear());
    }

    @Test
    public void you_can_save_a_car() {
        Car newCar = new Car("666", 2016, "Ghost-999");
        ResponseEntity<Car> entity =
                restTemplate.postForEntity("/car", newCar, Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car savedCar = entity.getBody();
        assertNotNull(savedCar);
        assertEquals("666", savedCar.getRegistrationNr());
        assertEquals("Ghost-999", savedCar.getModelNr());
        assertEquals(2016, (int) savedCar.getYear());
    }

    @Test
    public void you_can_update_a_car() {
        ResponseEntity<Car> entity = restTemplate.getForEntity("/car/2", Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car car = entity.getBody();
        assertNotNull(car);
        car.setRegistrationNr("HELLO");
        car.setYear(2020);
        car.setModelNr("Audi A8");
        HttpEntity<Car> carEntity = new HttpEntity<>(car);
        ResponseEntity<Car> exchange = restTemplate.exchange("/car/2", HttpMethod.PUT, carEntity, Car.class);
        Car updatedCar = exchange.getBody();
        assertNotNull(updatedCar);
        assertEquals("HELLO", updatedCar.getRegistrationNr());
        assertEquals("Audi A8", updatedCar.getModelNr());
        assertEquals(2020, (int) updatedCar.getYear());
    }

    @Test
    public void you_can_delete_a_car() {
        ResponseEntity<Car> entity = restTemplate.exchange("/car/3",
                HttpMethod.DELETE, null, Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car car = entity.getBody();
        assertNull(car);
    }
}