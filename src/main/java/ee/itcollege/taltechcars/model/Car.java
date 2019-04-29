package ee.itcollege.taltechcars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String registrationNr;
    private Integer year;
    private String modelNr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany
    private List<Lease> leases;

    public Car() {
    }

    public Car(String registrationNr, Integer year, String modelNr) {
        this.registrationNr = registrationNr;
        this.year = year;
        this.modelNr = modelNr;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModelNr() {
        return modelNr;
    }

    public void setModelNr(String modelNr) {
        this.modelNr = modelNr;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }
}