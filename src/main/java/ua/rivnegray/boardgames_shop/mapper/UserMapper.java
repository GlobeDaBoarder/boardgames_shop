package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.DTO.request.UserDto;
import ua.rivnegray.boardgames_shop.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
