package ee.itcollege.taltechcars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    @Autowired
    private LeaseService leaseService;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        //List<Lease> leases = leaseService.findAllCarsOverdue();
        //TalTechNotification notification = new TalTechNotification();
        //notification.message("lala");
        //notification.car(car)

        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

}
