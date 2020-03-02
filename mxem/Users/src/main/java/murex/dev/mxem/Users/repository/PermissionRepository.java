package murex.dev.mxem.Users.repository;

import murex.dev.mxem.Users.model.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
