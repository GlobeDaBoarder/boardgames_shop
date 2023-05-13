package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.model.user.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
