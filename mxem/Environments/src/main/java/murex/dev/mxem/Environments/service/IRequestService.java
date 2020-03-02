package murex.dev.mxem.Environments.service;

import murex.dev.mxem.Environments.model.Request;

import java.util.List;

public interface IRequestService {

    public Request add(Request request);
    public Request findById(String id);
    public List<Request> findByName(String id);
    public List<Request> findAll();

}


