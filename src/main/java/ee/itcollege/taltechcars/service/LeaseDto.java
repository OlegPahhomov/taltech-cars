package ee.itcollege.taltechcars.service;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.User;

import java.time.LocalDate;

public class LeaseDto {

    private Long id;
    private Car car;
    private User user;
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
