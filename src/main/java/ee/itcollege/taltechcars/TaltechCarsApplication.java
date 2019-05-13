package ee.itcollege.taltechcars;

import com.opencsv.CSVReader;
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

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Car vwGolf = new Car("111111111", 1999, "VW Golf");
            vwGolf.setLeased(true);
            Car savedVwGolf = carRepository.save(vwGolf);
            carRepository.save(new Car("222222222", 2008, "Audi A6"));
            carRepository.save(new Car("333333333", 2014, "BMW 318d"));
            carRepository.save(new Car("444444444", 2016, "Volvo V50"));
            carRepository.save(new Car("555555555", 2018, "Audi A6"));

            //import csv file for users
            List<List<String>> records = new ArrayList<>();
            try (CSVReader csvReader = new CSVReader(new FileReader("./files/users.csv"))) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
            }
            System.out.println("===================");
            System.out.println(records);
            System.out.println("===================");


            // save a couple of students
            User jill = userRepository.save(new User("SweetJill14", "ICS0011"));
            userRepository.save(new User("Chloe123", "ICS0012"));
            userRepository.save(new User("KimTheDragon", "ICS0013"));
            userRepository.save(new User("DavidDavid", "ICS0014"));
            userRepository.save(new User("Mich3ll3", "ICS0015"));

            leaseRepository.save(new Lease(savedVwGolf, jill, LocalDate.now()));
        };
    }
}
