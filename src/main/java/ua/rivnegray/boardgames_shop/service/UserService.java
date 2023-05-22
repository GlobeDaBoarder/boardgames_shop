package ua.rivnegray.boardgames_shop.service;

import ua.rivnegray.boardgames_shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
