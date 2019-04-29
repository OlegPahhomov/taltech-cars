package ee.itcollege.taltechcars;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.Lease;
import ee.itcollege.taltechcars.model.User;
import ee.itcollege.taltechcars.repository.CarRepository;
import ee.itcollege.taltechcars.repository.LeaseRepository;
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
    public CommandLineRunner initCars(CarRepository carRepository,
                                      UserRepository userRepository,
                                      LeaseRepository leaseRepository) {
        return (args) -> {
            // save a couple of cars
            Car savedVwGolf = carRepository.save(new Car("111111111", 1999, "VW Golf"));
            carRepository.save(new Car("222222222", 2008, "Audi A6"));
            carRepository.save(new Car("333333333", 2014, "BMW 318d"));
            carRepository.save(new Car("444444444", 2016, "Volvo V50"));
            carRepository.save(new Car("555555555", 2018, "Audi A6"));

            // save a couple of students
            User jill = userRepository.save(new User("SweetJill14", "ICS0011"));
            userRepository.save(new User("Chloe123", "ICS0012"));
            userRepository.save(new User("KimTheDragon", "ICS0013"));
            userRepository.save(new User("DavidDavid", "ICS0014"));
            userRepository.save(new User("Mich3ll3", "ICS0015"));

            leaseRepository.save(new Lease(savedVwGolf.getId(), jill.getId()));
            //todo save a lease
        };
    }
}
