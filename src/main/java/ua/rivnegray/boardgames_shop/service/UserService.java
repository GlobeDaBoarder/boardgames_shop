//package ua.rivnegray.boardgames_shop.service;
//
//import ua.rivnegray.boardgames_shop.DTO.request.CreateAndUpdateUserDto;
//import ua.rivnegray.boardgames_shop.DTO.response.UserFullDto;
//import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
//import ua.rivnegray.boardgames_shop.DTO.response.UserProfileDto;
//import ua.rivnegray.boardgames_shop.model.User;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserService {
//    List<UserFullDto> getAllSecureUsers();
//    Optional<UserFullDto> getSecureUserById(Long id);
//
//    List<UserPublicDto> getAllUnsecureUsers();
//    Optional<UserPublicDto> getUnsecureUserById(Long id);
//    UserFullDto createUser(CreateAndUpdateUserDto user);
//    UserFullDto updateUser(CreateAndUpdateUserDto user);
//
//    UserFullDto updateUserProfile(final UserProfileDto userProfileDto);
//
//    void deleteUser(Long id);
//}
