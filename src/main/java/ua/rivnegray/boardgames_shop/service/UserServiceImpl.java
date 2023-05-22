package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    private Set<UserRole>  getPersistedRolesIntoSession(User user){
        Set<UserRole> persistentRoles = new HashSet<>();
        for (UserRole role : user.getRoles()) {
            UserRole persistentRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            persistentRoles.add(persistentRole);
        }
        return persistentRoles;
    }

    @Override
    public User createUser(User user) {
        user.setRoles(getPersistedRolesIntoSession(user));
        return userRepository.save(user);
    }



    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
