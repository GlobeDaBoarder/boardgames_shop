package ua.rivnegray.boardgames_shop.service;

import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.IntermediateRegisterResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;

public interface SessionsService {

    TokenDto login(LoginRequestDto loginRequestDto);

    IntermediateRegisterResponseDto register(CreateCustomerUserDto createCustomerUserDto);
}
