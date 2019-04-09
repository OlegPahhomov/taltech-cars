package ee.itcollege.taltechcars;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.User;
import ee.itcollege.taltechcars.repository.CarRepository;
import ee.itcollege.taltechcars.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaltechCarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaltechCarsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initCars(CarRepository repository) {
        return (args) -> {
            // save a couple of cars
            repository.save(new Car("111111111", 1999, "VW Golf"));
            repository.save(new Car("222222222", 2008, "Audi A6"));
            repository.save(new Car("333333333", 2014, "BMW 318d"));
            repository.save(new Car("444444444", 2016, "Volvo V50"));
            repository.save(new Car("555555555", 2018, "Audi A6"));
        };
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository repository) {
        return (args) -> {
            // save a couple of students
            repository.save(new User("SweetJill14", "ICS0011"));
            repository.save(new User("Chloe123", "ICS0012"));
            repository.save(new User("KimTheDragon", "ICS0013"));
            repository.save(new User("DavidDavid", "ICS0014"));
            repository.save(new User("Mich3ll3", "ICS0015"));
        };
    }
}
