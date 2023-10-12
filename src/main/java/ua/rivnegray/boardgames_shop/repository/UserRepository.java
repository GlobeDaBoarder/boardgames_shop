package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    List<User> findByRoles_RoleName(String role);
}
