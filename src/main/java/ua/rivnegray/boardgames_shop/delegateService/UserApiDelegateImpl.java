package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.model.user.User;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
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
    public ResponseEntity<User> createUser(User user) {
        User newUser = this.userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(newUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, User body) {
        return ResponseEntity.ok(userService.updateUser(id, body));
    }
}
