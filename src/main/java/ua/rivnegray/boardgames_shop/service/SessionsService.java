package ua.rivnegray.boardgames_shop.service;

import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.RegisterCustomerRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.IntermediateRegisterResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;

public interface SessionsService {

    TokenDto login(LoginRequestDto loginRequestDto);

    IntermediateRegisterResponseDto register(RegisterCustomerRequestDto registerCustomerRequestDto);
}
