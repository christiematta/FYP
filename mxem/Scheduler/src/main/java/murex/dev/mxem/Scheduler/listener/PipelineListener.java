package murex.dev.mxem.Scheduler.listener;

import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Scheduler.model.Events;
import murex.dev.mxem.Scheduler.model.Request;
import murex.dev.mxem.Scheduler.repository.RequestRepository;
import murex.dev.mxem.Scheduler.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@Slf4j
@EnableScheduling
public class PipelineListener {

    @Autowired
    PipelineService pipelineService;


    @Autowired
    RequestRepository requestRepository;

    public List<Request> getRequests(){
        return requestRepository.findAll();
    }

    @Scheduled(fixedDelay = 10000)
    public void updateEventsforAllRequests() {
        List<Request> stat = getRequests();
        for(Request status : stat){
            Events events = pipelineService.updateEvents(status.getOperation(),status.getOperationId());
            Request request = requestRepository.findByOperationId(status.getOperationId()).get(0);
            events.addWithoutDuplicates(request.getEvents());
           request.setEvents(events.getEvents());
          //  log.info("La request "+request.getName()+" a eu "+request.getEvents());
            requestRepository.save(request);
        }
    }

    @Scheduled(fixedDelay = 10000)
    public void updateStatusforAllRequests() {
        List<Request> stat = getRequests();
        for(Request status : stat){
    status.setStatus(pipelineService.getNewStatus(status.getOperation(),status.getOperationId()));
    System.out.println("J'ai update le status a : "+status.getStatus());
    requestRepository.save(status);
        }
    }
}
