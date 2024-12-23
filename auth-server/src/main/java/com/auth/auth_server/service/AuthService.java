package com.auth.auth_server.service;


import com.auth.auth_server.dto.LoginRequestDto;
import com.auth.auth_server.dto.TokenDto;
import com.auth.auth_server.dto.UserDto;
import org.springframework.stereotype.Service;


public interface AuthService {

    TokenDto login ( LoginRequestDto loginRequestDto );

    TokenDto validateToken(TokenDto tokenDto);

    UserDto register(UserDto userDto); // Nuevo método

}
