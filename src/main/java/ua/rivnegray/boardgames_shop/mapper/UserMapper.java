package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.service.UserRoleService;

@Mapper(componentModel = "spring", uses = {UserRoleService.class})
public interface UserMapper {

//    UserFullDto userToUserDto(User user);
//
//    User userDtoToUser(UserFullDto userFullDto);
//
//
//    @Named("mapToRoleSet")
//    default Set<UserRole> mapToRoleSet(Set<Long> ids, @Context UserRoleService roleService) {
//        if (ids == null) {
//            return null;
//        }
//        Set<UserRole> roles = new HashSet<>(ids.size());
//        for (Long id : ids) {
//            roles.add(roleService.findRoleById(id));
//        }
//        return roles;
//    }
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "roles", source = "roleIds", qualifiedByName = "mapToRoleSet")
//    User createAndUpdateUserDtoToUser(CreateAndUpdateUserDto createUserDto, @Context UserRoleService roleService);

//    @Mapping(target = "username", source = "username")
//    @Mapping(target = "roles", source = "roles")
//    @Mapping(target = "email", source = "userProfile.email")
//    @Mapping(target = "phone", source = "userProfile.phone")
//    @Mapping(target = "firstName", source = "userProfile.firstName")
//    @Mapping(target = "lastName", source = "userProfile.lastName")
//    UserPublicDto toUserPublicDTO(UserCredentials userCredentials);
}
