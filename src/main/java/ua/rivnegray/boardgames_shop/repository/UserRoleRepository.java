package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.model.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    @Query("""
            select role from UserRole role
            where role.roleName = 'ROLE_CUSTOMER'
    """)
    Optional<UserRole> findCustomerRole();
}
