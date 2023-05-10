package ua.rivnegray.boardgames_shop.service;

import ua.rivnegray.boardgames_shop.model.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
