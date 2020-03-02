package murex.dev.mxem.Scheduler.repository;

        import murex.dev.mxem.Scheduler.model.SchedulerRequest;
        import org.springframework.data.mongodb.repository.MongoRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface SchedulerRequestRepository extends MongoRepository<SchedulerRequest, String> {
  //  List<SchedulerRequest> findByName(String name);
}
