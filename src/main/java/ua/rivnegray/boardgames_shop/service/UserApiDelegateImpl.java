package ua.rivnegray.boardgames_shop.service;

import generated.user.api.UsersApiDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.user.User;

import java.util.List;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {
    UserService userService;

    @Autowired
    public UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        return UsersApiDelegate.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, User body) {
        return ResponseEntity.ok(userService.updateUser(id, body));
    }
}
