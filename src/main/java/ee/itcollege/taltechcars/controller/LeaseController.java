package ee.itcollege.taltechcars.controller;


import ee.itcollege.taltechcars.service.LeaseDto;
import ee.itcollege.taltechcars.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lease")
public class LeaseController {

    //todo 2 user can lease out a car (start a lease)
    //todo 2.1 user can lease one car out at a time
    //todo 3 user can return a car
    //todo 4 user can see his leases

    //todo 5 (not in this class) add a cron job
    // 5.1 and custom properties for fun
    //todo 6 (not in this class) add an api request for sth
    //todo 7 (import a csv file)

    @Autowired
    private LeaseService leaseService;

    @GetMapping
    public List<LeaseDto> findAll() {
        try {
            return leaseService.findAll();
        } catch (Exception e) {
            System.out.println(123);
            //todo logging
            return null;
        }
    }

    @PostMapping
    public LeaseDto save(@RequestBody LeaseDto lease) {
        return leaseService.lease(lease);
    }

    @PutMapping("{id}")
    public LeaseDto update(@RequestBody LeaseDto leaseDto, @PathVariable Long id) {
        return leaseService.endLease(leaseDto, id);
    }

}
