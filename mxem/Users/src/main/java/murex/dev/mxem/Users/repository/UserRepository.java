package murex.dev.mxem.Users.repository;

import murex.dev.mxem.Users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
