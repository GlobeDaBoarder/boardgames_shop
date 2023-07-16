package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.model.UserProfile;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    List<UserProfile> findByRoles_RoleName(String roleName);

    boolean existsByEmail(String email);

    boolean existsByUserCredentials_Username(String username);

    Optional<UserProfile> findByUserCredentials_Username(String username);
}
