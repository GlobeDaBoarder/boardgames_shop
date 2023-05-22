package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.request.CreateAndUpdateUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserDto;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.service.UserRoleService;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {UserRoleService.class})
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);


    @Named("mapToRoleSet")
    default Set<UserRole> mapToRoleSet(Set<Long> ids, @Context UserRoleService roleService) {
        if (ids == null) {
            return null;
        }
        Set<UserRole> roles = new HashSet<>(ids.size());
        for (Long id : ids) {
            roles.add(roleService.findRoleById(id));
        }
        return roles;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", source = "roleIds", qualifiedByName = "mapToRoleSet")
    User createAndUpdateUserDtoToUser(CreateAndUpdateUserDto createUserDto, @Context UserRoleService roleService);
}
