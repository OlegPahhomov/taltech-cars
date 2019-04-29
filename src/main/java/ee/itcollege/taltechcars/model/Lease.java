package ee.itcollege.taltechcars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long car;
    private Long user;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;

}