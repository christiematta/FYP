package murex.dev.mxem.Users.service;

import murex.dev.mxem.Users.exception.RoleNotFoundException;
import murex.dev.mxem.Users.exception.UserNotFoundException;
import murex.dev.mxem.Users.repository.RoleRepository;
import murex.dev.mxem.Users.repository.UserRepository;
import murex.dev.mxem.Users.model.Role;
import murex.dev.mxem.Users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> findAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) throws UserNotFoundException {
        Optional<User> user= userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public Set<Role> findRolesForUser(Long id) throws UserNotFoundException {
        return findUserById(id).get().getRoles();
    }


    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteRolesForUser(Long id) throws UserNotFoundException {
        User user=findUserById(id).get();
        user.setRoles(Collections.EMPTY_SET);
        userRepository.save(user);
    }



    @Override
    public Role addRoleForUser(Long userId, Long roleId) throws UserNotFoundException, RoleNotFoundException {
        Optional<User> userInTable = userRepository.findById(userId);
        if (!userInTable.isPresent()) {
            throw new UserNotFoundException();
        }
        Optional<Role> roleInTable = roleRepository.findById(roleId);
        if (!roleInTable.isPresent()) {
            throw new RoleNotFoundException();
        }
        userInTable.get().getRoles().add(roleInTable.get());
        userRepository.save(userInTable.get());
        return roleInTable.get();
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> userInTable = userRepository.findById(id);
        if (!userInTable.isPresent()) {
            throw new UserNotFoundException();
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User updateNameofUser(Long id, String name) throws UserNotFoundException {
        User user =findUserById(id).get();
        user.setName(name);
        return userRepository.save(user);
    }
}
