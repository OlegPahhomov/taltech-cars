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
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    public static final ParameterizedTypeReference<List<Car>> LIST_OF_CARS = new ParameterizedTypeReference<List<Car>>() {
    };

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findAll() {
        ResponseEntity<List<Car>> exchange = restTemplate.exchange("/car", HttpMethod.GET, null, LIST_OF_CARS);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        List<Car> cars = exchange.getBody();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    public void findOne() {
        ResponseEntity<Car> entity = restTemplate.getForEntity("/car/1", Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car car = entity.getBody();
        assertNotNull(car);
        assertEquals("111111111", car.getRegistrationNr());
        assertEquals("VW Golf", car.getModelNr());
        assertEquals(1999, (int) car.getYear());
    }

    @Test
    public void save() {
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
    public void update() {
        ResponseEntity<Car> entity = restTemplate.getForEntity("/car/2", Car.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Car car = entity.getBody();
        assertNotNull(car);
        car.setRegistrationNr("HELLO");
        HttpEntity<Car> carEntity = new HttpEntity<>(car);
        ResponseEntity<Car> exchange = restTemplate.exchange("/car/2", HttpMethod.PUT, carEntity, Car.class);
    }

    @Test
    public void delete() {
    }
}