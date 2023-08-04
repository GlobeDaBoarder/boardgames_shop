package ua.rivnegray.boardgames_shop.service;

import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;

public interface SessionsService {

    LoginResponseDto login(LoginRequestDto loginRequestDto, MapShoppingCartDto mapShoppingCartDto);

    LoginResponseDto register(CreateCustomerUserDto createCustomerUserDto, MapShoppingCartDto mapShoppingCartDto);
}
