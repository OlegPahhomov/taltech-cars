package ee.itcollege.taltechcars.repository;

import ee.itcollege.taltechcars.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
}
