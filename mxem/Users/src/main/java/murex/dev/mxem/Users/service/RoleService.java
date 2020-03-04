package murex.dev.mxem.Users.service;

import murex.dev.mxem.Users.exception.PermissionNotFoundException;
import murex.dev.mxem.Users.exception.RoleNotFoundException;
import murex.dev.mxem.Users.exception.UserNotFoundException;
import murex.dev.mxem.Users.model.Permission;
import murex.dev.mxem.Users.model.Role;
import murex.dev.mxem.Users.model.User;
import murex.dev.mxem.Users.repository.PermissionRepository;
import murex.dev.mxem.Users.repository.RoleRepository;
import murex.dev.mxem.Users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;

    public List<Role> findAllRoles() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        return roles;
    }

    @Override
    public Optional<Role> findRoleById(Long id) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            throw new RoleNotFoundException();
        }
        return role;
    }

    public Role findRoleByName(String name){
       return (roleRepository.findByName(name).get(0));

    }

    @Override
    public Set<Permission> findPermissionsForRole(String id) throws RoleNotFoundException {
        return findRoleByName(id).getPermissions();
    }

    @Override
    public Set<User> findUsersForRole(Long id) throws RoleNotFoundException {
        return findRoleById(id).get().getUsers();
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();

    }

    @Override
    public void deleteRoleById(Long id) throws RoleNotFoundException {
        Optional<Role> result = roleRepository.findById(id);
        if (!result.isPresent()) {
            throw new RoleNotFoundException();
        }
        roleRepository.deleteById(id);

    }

    @Override
    public void deletePermissionsForRole(Long id) throws RoleNotFoundException {
        Role role = findRoleById(id).get();
        role.setPermissions(Collections.EMPTY_SET);
        roleRepository.save(role);

    }

    @Override
    public void deleteUsersForRole(Long id) throws RoleNotFoundException {
        Role role = findRoleById(id).get();
        role.setUsers(Collections.EMPTY_SET);
        roleRepository.save(role);

    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) throws RoleNotFoundException {
        Optional<Role> roleInTable = roleRepository.findById(id);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public Role updateNameofRole(Long id, String name) throws RoleNotFoundException {
        Role role= findRoleById(id).get();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Override
    public User addUserForRole(Long roleId, Long userId) throws RoleNotFoundException, UserNotFoundException {
        Optional<Role> roleInTable = roleRepository.findById(roleId);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }

        Optional<User> userInTable = userRepository.findById(userId);
        if (!userInTable.isPresent()) {
            throw new UserNotFoundException();
        }
        roleInTable.get().getUsers().add(userInTable.get());
        roleRepository.save(roleInTable.get());
        return userInTable.get();
    }

    @Override
    public Permission addPermissionForRole(Long roleId, Long permissionId) throws RoleNotFoundException, PermissionNotFoundException {
        Optional<Role> roleInTable = roleRepository.findById(roleId);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }
        Optional<Permission> permissionInTable = permissionRepository.findById(permissionId);
        if (!roleInTable.isPresent()) {
            throw new PermissionNotFoundException();
        }
        roleInTable.get().getPermissions().add(permissionInTable.get());
        roleRepository.save(roleInTable.get());
        return permissionInTable.get();
    }
}
