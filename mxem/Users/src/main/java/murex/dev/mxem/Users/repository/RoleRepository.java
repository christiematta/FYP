package murex.dev.mxem.Users.repository;

import murex.dev.mxem.Users.model.Role;
import murex.dev.mxem.Users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(String name);
}
