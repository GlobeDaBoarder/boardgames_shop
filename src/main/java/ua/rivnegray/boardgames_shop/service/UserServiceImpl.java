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
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User updateUser = optionalUser.get();

            updateUser.setUsername(user.getUsername());
            updateUser.setPassword(user.getPassword());
            updateUser.setEmail(user.getEmail());
            updateUser.setImageUrl(user.getImageUrl());

            updateUser.setRoles(getPersistedRolesIntoSession(user));

            return Optional.of(userRepository.save(updateUser));
        }

        return Optional.empty();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
