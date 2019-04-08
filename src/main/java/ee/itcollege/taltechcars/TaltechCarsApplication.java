package ee.itcollege.taltechcars;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.repository.CarRepository;
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
    public CommandLineRunner demo(CarRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Car("Jack", 1, "Bauer"));
            repository.save(new Car("Chloe", 1, "O'Brian"));
            repository.save(new Car("Kim", 1, "Bauer"));
            repository.save(new Car("David", 1,"Palmer"));
            repository.save(new Car("Michelle", 1, "Dessler"));
        };
    }
}
