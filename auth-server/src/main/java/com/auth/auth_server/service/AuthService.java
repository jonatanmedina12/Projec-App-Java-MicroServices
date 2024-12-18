package com.auth.auth_server.service;


import com.auth.auth_server.dto.TokenDto;
import com.auth.auth_server.dto.UserDto;
import org.springframework.stereotype.Service;


public interface AuthService {

    TokenDto login (UserDto userDto);

    TokenDto validateToken(TokenDto tokenDto);

    UserDto register(UserDto userDto); // Nuevo método

}
