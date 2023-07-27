package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
