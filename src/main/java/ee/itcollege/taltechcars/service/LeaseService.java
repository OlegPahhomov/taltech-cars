package ee.itcollege.taltechcars.service;


import ee.itcollege.taltechcars.config.LeaseConfiguration;
import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.Lease;
import ee.itcollege.taltechcars.model.User;
import ee.itcollege.taltechcars.repository.CarRepository;
import ee.itcollege.taltechcars.repository.LeaseRepository;
import ee.itcollege.taltechcars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LeaseConfiguration leaseConfiguration;

    public LeaseDto lease(LeaseDto lease) {
        Lease dbLease = new Lease();

        //todo validate lease.getUser is not null
        dbLease.setUser(getValidUser(lease));
        Car car = getValidCar(lease);
        dbLease.setCar(car);

        dbLease.setStartDate(LocalDate.now());
        dbLease.setEndDate(LocalDate.now().plusDays(leaseConfiguration.getDuration()));

        car.setLeased(true);
        carRepository.save(car);

        Lease saved = leaseRepository.save(dbLease);
        return convert(saved);
    }

    public List<LeaseDto> findAll() {
        if (0 == 1){
            throw new RuntimeException("Bad exception");
        }

        return leaseRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public LeaseDto endLease(LeaseDto leaseDto, Long id) {
        Optional<Lease> dbLeaseOp = leaseRepository.findById(id);
        Lease dbLease = dbLeaseOp.orElseThrow(RuntimeException::new); // todo 400

        LocalDate returnDate = leaseDto.getReturnDate();
        dbLease.setReturnDate(returnDate);
        Lease saved = leaseRepository.save(dbLease);

        Car car = dbLease.getCar();
        car.setLeased(false);
        carRepository.save(car);

        return convert(saved);
    }

    private User getValidUser(LeaseDto lease) {
        Optional<User> dbUserOp = userRepository.findById(lease.getUser().getId());
        User dbUser = dbUserOp.orElseThrow(RuntimeException::new);
        List<Lease> existingLeases = leaseRepository.findByUserAndReturnDateNull(dbUser);
        if (!existingLeases.isEmpty()){
            throw new ResponseStatusException(BAD_REQUEST, "user already has a car leased");
        }
        return dbUser;
    }

    private Car getValidCar(LeaseDto lease) {
        Optional<Car> dbCarOp = carRepository.findById(lease.getCar().getId());
        Car dbCar = dbCarOp.orElseThrow(RuntimeException::new); //todo return 400

        if (dbCar.isLeased()){
            throw new ResponseStatusException(BAD_REQUEST, "car is already leased");
        }
        return dbCar;
    }

    private LeaseDto convert(Lease lease) {
        LeaseDto leaseDto = new LeaseDto();
        leaseDto.setId(lease.getId());
        leaseDto.setCar(lease.getCar());
        leaseDto.setUser(lease.getUser());
        leaseDto.setReturnDate(lease.getReturnDate());
        return leaseDto;
    }
}
