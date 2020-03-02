package murex.dev.mxem.Environments.repository;

import murex.dev.mxem.Environments.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RequestRepository extends MongoRepository<Request, String> {
    List<Request> findByName(String name);
}
