package com.auth.auth_server.service;

import com.auth.auth_server.dto.LoginRequestDto;
import com.auth.auth_server.dto.TokenDto;
import com.auth.auth_server.dto.UserDto;
import com.auth.auth_server.entities.UserEntity;
import com.auth.auth_server.helpers.JwtHelper;
import com.auth.auth_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String USER_NOT_FOUND_MSG = "Usuario no encontrado o credenciales inválidas";
    private static final String INVALID_TOKEN_MSG = "Token inválido o expirado";

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private  JwtHelper jwtHelper;
    private  RecaptchaService recaptchaService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper,RecaptchaService recaptchaService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.recaptchaService = recaptchaService;
    }
    @Override
    public UserDto register(UserDto userDto) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El usuario ya existe"
            );
        }

        // Crear nueva entidad de usuario
        UserEntity newUser = UserEntity.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        // Guardar en la base de datos
        UserEntity savedUser = userRepository.save(newUser);

        // Retornar DTO (sin la contraseña)
        return UserDto.builder()
                .username(savedUser.getUsername())
                .build();
    }

    @Override
    public TokenDto login(LoginRequestDto loginRequestDto) {
        try {

            recaptchaService.validateToken(loginRequestDto.getRecaptchaToken());

            UserEntity user = userRepository.findByUsername(loginRequestDto.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.UNAUTHORIZED,
                            USER_NOT_FOUND_MSG
                    ));

            validatePassword(loginRequestDto.getPassword(), user.getPassword());

            String token = jwtHelper.createToken(user.getUsername());

            return TokenDto.builder()
                    .accessToken(token)
                    .username(user.getUsername())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    USER_NOT_FOUND_MSG,
                    e
            );
        }
    }

    @Override
    public TokenDto validateToken(TokenDto tokenDto) {
        try {
            if (jwtHelper.validateToken(tokenDto.getAccessToken())) {
                return TokenDto.builder()
                        .accessToken(tokenDto.getAccessToken())
                        .build();
            }
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    INVALID_TOKEN_MSG
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    INVALID_TOKEN_MSG,
                    e
            );
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    USER_NOT_FOUND_MSG
            );
        }
    }
}