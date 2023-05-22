package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

@Service
public class UserRoleService {

    private final UserRoleRepository roleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRole findRoleById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
