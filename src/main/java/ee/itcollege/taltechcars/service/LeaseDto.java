package ee.itcollege.taltechcars.service;

import ee.itcollege.taltechcars.model.Car;
import ee.itcollege.taltechcars.model.User;

public class LeaseDto {

    private Long id;
    private Car car;
    private User user;

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
}
