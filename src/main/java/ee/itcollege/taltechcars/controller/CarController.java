package ee.itcollege.taltechcars.controller;


import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    public static final String URL = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

    @Autowired
    private CarService carService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Car> findAll(
            @RequestParam(value = "modelNr", required = false) String modelNr,
            @RequestParam(value = "yearOlder", required = false) Integer yearOlder,
            @RequestParam(value = "available", required = false) Boolean available
    ) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);

        return carService.findAll(modelNr, yearOlder, available);
    }

    @GetMapping("{id}")
    public Car findOne(@PathVariable Long id) {
        return carService.findOne(id);
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        return carService.save(car);
    }

    @PutMapping("{id}")
    public Car update(@RequestBody Car car, @PathVariable Long id) {
        return carService.update(car, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
