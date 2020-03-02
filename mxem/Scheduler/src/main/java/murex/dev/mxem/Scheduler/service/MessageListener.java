package murex.dev.mxem.Scheduler.service;

import murex.dev.mxem.Scheduler.model.SchedulerRequest;
import murex.dev.mxem.Scheduler.repository.SchedulerRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    SchedulerRequestRepository schedulerRequestRepository;

//    @Autowired
//    SchedulerRequestService schedulerRequestService;

    @RabbitListener(queues = "javainuse.queue")
    public void receiveMessage(final SchedulerRequest message) {
        schedulerRequestRepository.save(message);
        log.info("Received message as generic: {}", message.toString());
    }

}