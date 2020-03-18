package murex.dev.mxem.Scheduler.service;


import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Scheduler.model.Request;
import murex.dev.mxem.Scheduler.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request findByOperationId(String operationId){
        List<Request> requests = requestRepository.findByOperationId(operationId);
        if(requests.size()==0){
            return null;
        }
        return requests.get(0);
    }

    public List<Request> findByEnvironment(String id){
        log.info("Je suis dans find by environemnts");

        List<Request> requests = requestRepository.findByEnvId(id);
        return requests;
    }

    public List<Request> findAll(){
        return requestRepository.findAll();
    }



}
