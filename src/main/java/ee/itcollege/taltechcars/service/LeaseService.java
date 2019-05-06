package ee.itcollege.taltechcars.service;


import ee.itcollege.taltechcars.model.Lease;
import ee.itcollege.taltechcars.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    public Lease save(Lease lease) {
        return leaseRepository.save(lease);
    }
}
