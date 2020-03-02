package murex.dev.mxem.Environments.service;


import murex.dev.mxem.Environments.model.Request;
import murex.dev.mxem.Environments.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestService implements IRequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request add(Request request){
        requestRepository.save(request);
        return request;
    }

    public Request findById(String id){
        Request req = requestRepository.findById(id).get();
        return req;
    }

    public List<Request> findByName(String id){
        List<Request> req = requestRepository.findByName(id);
        return req;
    }


    public List<Request> findAll(){
        return requestRepository.findAll();
    }


}
