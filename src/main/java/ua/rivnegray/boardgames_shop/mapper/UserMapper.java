package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.service.UserRoleService;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {UserRoleService.class})
// todo convert to Abstract class to use @Autowired and take care of Spring beans
public interface UserMapper {

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

    @Named("getCustomerRole")
    default Set<UserRole> getCustomerRole(CreateCustomerUserDto createCustomerUserDto, @Context UserRoleService roleService) {
        UserRole userRole = roleService.findRoleByName("ROLE_CUSTOMER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        return roles;
    }

    @Mapping(source = "userCredentials.username", target = "username")
    UserPublicDto toUserPublicDto(UserProfile userProfile);

    UserPublicDto toUserPublicDto(CreateAnyUserDto createAnyUserDto);

    @Mapping(source = "roleIds", target = "roles", qualifiedByName = "mapToRoleSet")
    UserProfile toUserProfile(CreateAnyUserDto createAnyUserDto, @Context UserRoleService roleService);

    @Mapping(target = "roles", source = ".", qualifiedByName = "getCustomerRole")
    UserProfile toUserProfile(CreateCustomerUserDto createCustomerUserDto, @Context UserRoleService roleService);


    UserCredentials toUserCredentials(CreateAnyUserDto createAnyUserDto);
    UserCredentials toUserCredentials(CreateCustomerUserDto createAnyUserDto);

    Address toAddress(AddAndUpdateAddressDto addAndUpdateAddressDto);

    AddressDto toAddressDto(Address addressToAdd);

//    UserFullDto userToUserDto(User user);
//
//    User userDtoToUser(UserFullDto userFullDto);
//
//
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
